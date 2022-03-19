//package edu.gdut.imis.courseMutualSelection.test_rabbitmq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @author miemiehoho
// * @date 2022/3/18 10:47
// */
//@Service
//@Slf4j
//public class MQSender {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void send(Object msg) {
//        log.info("发送消息" + msg);
//        rabbitTemplate.convertAndSend("fanoutExchange", "", msg);
//    }
//
//
//
//
//}
