//package edu.gdut.imis.courseMutualSelection.test_rabbitmq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
///**
// * @author miemiehoho
// * @date 2022/3/18 10:48
// */
//@Service
//@Slf4j
//public class MQReceiver {
//
//
//    @RabbitListener(queues = "queue_fanout01")
//    public void receive01(Object msg) {
//        log.info("queue_fanout01 接受消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout02")
//    public void receive02(Object msg) {
//        log.info("queue_fanout02 接受消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_direct01")
//    public void receiveDirect01(Object msg) {
//        log.info("queue_Direct01 接受消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_direct02")
//    public void receiveDirect02(Object msg) {
//        log.info("queue_Direct02 接受消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_topic01")
//    public void receiveTopic01(Object msg) {
//        log.info("queue_topic01 接收消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_topic02")
//    public void receiveTopic02(Object msg) {
//        log.info("queue_topic02 接收消息：" + msg);
//    }
//}
