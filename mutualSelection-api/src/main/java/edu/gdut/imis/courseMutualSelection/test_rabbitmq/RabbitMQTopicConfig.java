//package edu.gdut.imis.courseMutualSelection.test_rabbitmq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author miemiehoho
// * @date 2022/3/18 14:01
// */
//@Configuration
//public class RabbitMQTopicConfig {
//    private static final String QUEUE01 = "queue_topic01";
//    private static final String QUEUE02 = "queue_topic02";
//    private static final String EXCHANGE = "topicExchange";
//    private static final String ROUTINGKEY01 = "#.queue.#";
//    private static final String ROUTINGKEY02 = "*.queue.#";
//
//    @Bean
//    public Queue queueTopic01() {
//        // true:配置队列持久化
//        return new Queue(QUEUE01, true);
//    }
//
//    @Bean
//    public Queue queueTopic02() {
//        // true:配置队列持久化
//        return new Queue(QUEUE02, true);
//    }
//
//    /**
//     * 交换机
//     */
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding bindingTopic01() {
//        return BindingBuilder.bind(queueTopic01()).to(topicExchange()).with(ROUTINGKEY01);
//    }
//
//    @Bean
//    public Binding bindingTopic02() {
//        return BindingBuilder.bind(queueTopic02()).to(topicExchange()).with(ROUTINGKEY02);
//    }
//}
