package edu.gdut.imis.courseMutualSelection.rabbitmq;

import edu.gdut.imis.courseMutualSelection.vo.SeckillMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author miemiehoho
 * @date 2022/3/18 15:14
 */
@Service
public class MQSender {
    private static final String EXCHANGE = "seckillExchange";
    private static final String ROUTINGKEY = "seckill";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void seckillMessageSend(String seckillMessage) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTINGKEY, seckillMessage);
    }
}
