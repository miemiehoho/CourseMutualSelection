//package edu.gdut.imis.courseMutualSelection.test_rabbitmq;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * @author miemiehoho
// * @date 2022/3/18 10:45
// */
//@Configuration
//public class RabbitMQFanoutConfig {
//    private static final String QUEUE01 = "queue_fanout01";
//    private static final String QUEUE02 = "queue_fanout02";
//    private static final String EXCHANGE = "fanoutExchange";
//
//
//    @Bean
//    public Queue queue01() {
//        // true:配置队列持久化
//        return new Queue(QUEUE01, true);
//    }
//
//    @Bean
//    public Queue queue02() {
//        // true:配置队列持久化
//        return new Queue(QUEUE02, true);
//    }
//
//    /**
//     * 交换机
//     *
//     * @return
//     */
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange(EXCHANGE);
//    }
//
//    /**
//     * 绑定队列到指定的交换机
//     *
//     * @return
//     */
//    @Bean
//    public Binding binding01() {
//        return BindingBuilder.bind(queue01()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding binding02() {
//        return BindingBuilder.bind(queue02()).to(fanoutExchange());
//    }
//}
