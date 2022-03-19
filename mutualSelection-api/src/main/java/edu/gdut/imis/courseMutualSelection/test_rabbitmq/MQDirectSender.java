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
//public class MQDirectSender {
//    private static final String EXCHANGE = "directExchange";
//    private static final String ROUTINGKEY01 = "queue.red";
//    private static final String ROUTINGKEY02 = "queue.balck";
//
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void sendRed(Object msg) {
//        log.info("发送red消息" + msg);
//        rabbitTemplate.convertAndSend(EXCHANGE, ROUTINGKEY01, msg);
//    }
//
//    public void sendBlack(Object msg) {
//        log.info("发送balck消息" + msg);
//        rabbitTemplate.convertAndSend(EXCHANGE, ROUTINGKEY02, msg);
//    }
//
//
//}
