//package edu.gdut.imis.courseMutualSelection.test_rabbitmq;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author miemiehoho
// * @date 2022/3/18 11:51
// */
//@Configuration
//public class RabbitMQDirectConfig {
//    private static final String QUEUE01 = "queue_direct01";
//    private static final String QUEUE02 = "queue_direct02";
//    private static final String EXCHANGE = "directExchange";
//    private static final String ROUTINGKEY01 = "queue.red";
//    private static final String ROUTINGKEY02 = "queue.balck";
//
//    @Bean
//    public Queue queueDirect01() {
//        // true:配置队列持久化
//        return new Queue(QUEUE01, true);
//    }
//
//    @Bean
//    public Queue queueDirect02() {
//        // true:配置队列持久化
//        return new Queue(QUEUE02, true);
//    }
//
//    /**
//     * 交换机
//     */
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding bindingDirect01() {
//        return BindingBuilder.bind(queueDirect01()).to(directExchange()).with(ROUTINGKEY01);
//    }
//
//    @Bean
//    public Binding bindingDirect02() {
//        return BindingBuilder.bind(queueDirect02()).to(directExchange()).with(ROUTINGKEY02);
//    }
//}
