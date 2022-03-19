package edu.gdut.imis.courseMutualSelection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author miemiehoho
 * @date 2022/3/18 18:14
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // key序列器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // value 序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // hash类型，key序列器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // hash类型，value序列器
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 注入连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }





}
