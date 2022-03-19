package edu.gdut.imis.courseMutualSelection.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.CourseSelect;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.service.CourseSelectService;
import edu.gdut.imis.courseMutualSelection.utils.UserThreadLocal;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import edu.gdut.imis.courseMutualSelection.dao.mapper.UserCourseMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.UserCourse;
import edu.gdut.imis.courseMutualSelection.service.UserCourseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;


@Service("userCourseService")
public class UserCourseServiceImpl extends ServiceImpl<UserCourseMapper, UserCourse> implements UserCourseService {


    @Autowired
    private UserCourseMapper userCourseMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CourseSelectService courseSelectService;


    // 查询订单
    @Override
    public UserCourse findByCourseSelectId(Long id) {
        LambdaQueryWrapper<UserCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserCourse::getUserId, UserThreadLocal.get().getId());
        queryWrapper.eq(UserCourse::getCourseSelectId, id);
        UserCourse userCourse = userCourseMapper.selectOne(queryWrapper);
        return userCourse;
    }

    /**
     * 减库存，生成秒杀订单
     * 1.更新库存：判断库存是否大于零，大于零则更新库存并创建订单
     * 2.生成订单
     * 3.订单存入 reids
     * 4.返回订单给用户
     *
     * @param courseSelect
     * @return
     */
    @Override
    @Transactional
    public UserCourse seckillCourseSelect(User user, CourseSelect courseSelect) {
        // 判断库存是否大于零,大于零才减库存

        //        courseSelectMapper.updateById(courseSelect);
        boolean update = courseSelectService.update(new UpdateWrapper<CourseSelect>()
                .setSql("selected_num = " + "selected_num-1")
                .eq("id", courseSelect.getId())
                .gt("selected_num", 0));
        // 库存不够了
        if (!update) {
            redisTemplate.opsForValue().set("isStockEmpty", "0");
            return null;
        }
        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(user.getId());
        userCourse.setCourseSelectId(courseSelect.getId());
        userCourse.setCreateTime(System.currentTimeMillis());
        boolean save = save(userCourse);
        if (!save) {
            return null;
        }
        // 订单放入 redis
        redisTemplate.opsForValue().set("userCourse_" + user.getId() + ":" + courseSelect.getId(), userCourse);
        return userCourse;
    }

    @Override
    public Result userCourselist() {
        return null;
    }

    @Override
    public Result selectUserCourse(String selectCourseId) {
        LambdaQueryWrapper<UserCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserCourse::getUserId, UserThreadLocal.get().getId());
        queryWrapper.eq(UserCourse::getCourseSelectId, selectCourseId);
        UserCourse userCourse = userCourseMapper.selectOne(queryWrapper);
        if (userCourse != null) {
            return Result.success(userCourse.getId());
        }
        if (redisTemplate.hasKey("isStockEmpty" + selectCourseId)) {
            return Result.fail(ErrorStatus.SELECT_UNDERSTOCK.getCode(), ErrorStatus.SELECT_UNDERSTOCK.getMsg());
        }
        return Result.success(ErrorStatus.SELECT_QUEUE);
    }


}