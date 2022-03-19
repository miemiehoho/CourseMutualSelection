//package edu.gdut.imis.courseMutualSelection.test_rabbitmq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @author miemiehoho
// * @date 2022/3/18 14:06
// */
//@Service
//@Slf4j
//public class MQTopicSender {
//    private static final String EXCHANGE = "topicExchange";
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void send01(Object msg) {
//        log.info("topic01发送消息：" + msg);
//        rabbitTemplate.convertAndSend(EXCHANGE, "queue.red.message", msg);
//    }
//
//    public void send02(Object msg) {
//        log.info("topic02发送消息：" + msg);
//        rabbitTemplate.convertAndSend(EXCHANGE, "black.queue.black.message", msg);
//    }
//
//}
