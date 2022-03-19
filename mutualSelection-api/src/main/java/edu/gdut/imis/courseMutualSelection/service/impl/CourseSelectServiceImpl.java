package edu.gdut.imis.courseMutualSelection.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gdut.imis.courseMutualSelection.dao.mapper.CourseSelectMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.CourseSelect;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.dao.pojo.UserCourse;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.rabbitmq.MQSender;
import edu.gdut.imis.courseMutualSelection.utils.UserThreadLocal;
import edu.gdut.imis.courseMutualSelection.vo.CourseSelectVo;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.SeckillMessage;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import edu.gdut.imis.courseMutualSelection.service.CourseSelectService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Transactional
@Service("courseSelectService")
public class CourseSelectServiceImpl extends ServiceImpl<CourseSelectMapper, CourseSelect> implements CourseSelectService, InitializingBean {


    @Autowired
    private CourseSelectMapper courseSelectMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MQSender mqSender;
    private Map<Long, Boolean> emptyStockMap = new HashMap<>();

    @Autowired
    private RedisScript script;

    @Override
    public Result list(PageParams pageParams) {
        LambdaQueryWrapper<CourseSelect> queryWrapper = new LambdaQueryWrapper<>();
        Page<CourseSelect> page = new Page<>(pageParams.getPage(), pageParams.getPage_size());
        Page<CourseSelect> courseSelectPage = courseSelectMapper.selectPage(page, queryWrapper);
        List<CourseSelect> courseSelectList = courseSelectPage.getRecords();
        return Result.success(copyList(courseSelectList));
    }

    @Override
    public Result findById(Long id) {
        return Result.success(findCourseSelectById(id));
    }

    @Override
    public Result doSeckill(Long courseSelectId) {

        // 判断是否重复抢购
        UserCourse userCourse = (UserCourse) redisTemplate.opsForValue().get("userCourse_" + UserThreadLocal.get().getId() + ":" + courseSelectId);
        if (userCourse != null) {
            return Result.fail(ErrorStatus.REPECT_SELECT.getCode(), ErrorStatus.REPECT_SELECT.getMsg());
        }
        // 通过内存标记，判断库存是否为空,减少redis的访问
        if (emptyStockMap.get(courseSelectId)) {
            return Result.fail(ErrorStatus.SELECT_UNDERSTOCK.getCode(), ErrorStatus.SELECT_UNDERSTOCK.getMsg());
        }
        // 预减库存,返回现有库存：如果库存大于零，redis库存减一，并返回减一之前的库存
        Long stock = (Long) redisTemplate.execute(script, Collections.singletonList("courseSelect_" + courseSelectId), "");
//        Long stock = redisTemplate.opsForValue().decrement("courseSelect_" + courseSelectId);
        if (stock < 0) {
            emptyStockMap.put(courseSelectId, Boolean.TRUE);
            redisTemplate.opsForValue().increment("courseSelect_" + courseSelectId);// 加回0
            return Result.fail(ErrorStatus.SELECT_UNDERSTOCK.getCode(), ErrorStatus.SELECT_UNDERSTOCK.getMsg());
        }
        SeckillMessage seckillMessage = new SeckillMessage(UserThreadLocal.get(), courseSelectId);
        mqSender.seckillMessageSend(JSON.toJSONString(seckillMessage));
        return Result.success(ErrorStatus.SELECT_QUEUE);
    }
//    @Override
//    public Result doSeckill(Long id) {
//        /**
//         *  判断库存
//         *  判断是否重复抢购
//         *  减库存
//         *  生成订单
//         */
//        CourseSelect courseSelect = findCourseSelectById(id);
//        // 判断库存
//        if (courseSelect.getSelectedNum() <= 0) {
//            return Result.fail(ErrorStatus.SELECT_UNDERSTOCK.getCode(), ErrorStatus.SELECT_UNDERSTOCK.getMsg());
//        }
//        // 判断是否重复抢购
//        String userCourse = redisTemplate.opsForValue().get("userCourse_" + UserThreadLocal.get().getId() + ":" + courseSelect.getId());
//        if (userCourse != null) {
//            return Result.fail(ErrorStatus.REPECT_SELECT.getCode(), ErrorStatus.REPECT_SELECT.getMsg());
//        }
//        // 减库存,创建选课信息
//        return Result.success(seckillCourseSelect(courseSelect));
//    }


    public CourseSelect findCourseSelectById(Long id) {
        CourseSelect courseSelect = courseSelectMapper.selectById(id);
        return courseSelect;
    }

    /**
     * 获取抢课地址,创建抢课地址，存入redis
     *
     * @param courseSelectId
     * @return
     */
    @Override
    public Result getPath(Long courseSelectId) {
        User user = UserThreadLocal.get();
        String uuid = DigestUtils.md5Hex(String.valueOf(UUID.randomUUID()));
        redisTemplate.opsForValue().set("seckillPath" + user.getId() + ":" + courseSelectId, uuid, 60, TimeUnit.SECONDS);
        return Result.success(uuid);
    }

    /**
     * 校验秒杀地址
     *
     * @param userId
     * @param path
     * @param courseSelectId
     * @return
     */
    @Override
    public boolean checkPath(Long userId, String path, Long courseSelectId) {
        if (courseSelectId < 0 || StringUtils.isEmpty(path)) {
            return false;
        }
        String redisPath = (String) redisTemplate.opsForValue().get("seckillPath" + userId + ":" + courseSelectId);
        return path.equals(redisPath);
    }

    @Override
    public boolean checkCaptch(Long courseSelectId, String captcha) {
        if (courseSelectId < 0 || StringUtils.isEmpty(captcha)) {
            return false;
        }
        String redisCaptch = (String) redisTemplate.opsForValue().get("capcha:" + UserThreadLocal.get().getId() + ":" + courseSelectId);
        return captcha.equals(redisCaptch);
    }


    private List<CourseSelectVo> copyList(List<CourseSelect> courseSelectList) {
        List<CourseSelectVo> list = new ArrayList<>();
        for (CourseSelect courseSelect : courseSelectList) {
            list.add(copy(courseSelect));
        }
        return list;
    }

    private CourseSelectVo copy(CourseSelect courseSelect) {
        CourseSelectVo courseSelectVo = new CourseSelectVo();
        BeanUtils.copyProperties(courseSelect, courseSelectVo);
        return courseSelectVo;
    }

    /**
     * 预热，系统初始化，加载商品库存到redis
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<CourseSelect> courseSelectList = courseSelectMapper.selectList(new LambdaQueryWrapper<>());
        if (CollectionUtils.isEmpty(courseSelectList)) {
            return;
        }
        courseSelectList.forEach(courseSelect -> {
            emptyStockMap.put(courseSelect.getId(), Boolean.FALSE);
            redisTemplate.opsForValue().set("courseSelect_" + courseSelect.getId(), courseSelect.getSelectedNum());
        });
    }
}