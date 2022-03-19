package edu.gdut.imis.courseMutualSelection.rabbitmq;

import com.alibaba.fastjson.JSON;
import edu.gdut.imis.courseMutualSelection.dao.pojo.CourseSelect;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.service.CourseSelectService;
import edu.gdut.imis.courseMutualSelection.service.UserCourseService;
import edu.gdut.imis.courseMutualSelection.utils.UserThreadLocal;
import edu.gdut.imis.courseMutualSelection.vo.SeckillMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author miemiehoho
 * @date 2022/3/18 15:19
 */
@Service
public class MQReceiver {
    private static final String QUEUE = "seckillQueue";

    @Autowired
    private CourseSelectService courseSelectService;
    @Autowired
    private UserCourseService userCourseService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RabbitListener(queues = QUEUE)
    public void seckillMessageReceiver(String message) {
        SeckillMessage seckillMessage = JSON.parseObject(message, SeckillMessage.class);
        User user = seckillMessage.getUser();
        Long courseSelectId = seckillMessage.getCourseSelectId();
        // 判断库存
        CourseSelect courseSelect = courseSelectService.findCourseSelectById(courseSelectId);
        if (courseSelect.getSelectedNum() <= 0) {
            return;
        }
        // 判断是否重复抢购
        String userCourse = redisTemplate.opsForValue().get("userCourse_" + user.getId() + ":" + courseSelectId);
        if (userCourse != null) {
            return;
        }
        // 下单操作
        userCourseService.seckillCourseSelect(user, courseSelect);

    }
}
