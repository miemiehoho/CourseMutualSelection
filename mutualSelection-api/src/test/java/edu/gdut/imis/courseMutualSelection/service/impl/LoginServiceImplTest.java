package edu.gdut.imis.courseMutualSelection.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author miemiehoho
 * @date 2021/11/23 23:26
 */
@SpringBootTest
class LoginServiceImplTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @org.junit.jupiter.api.Test
    void login() {
        String password = "root";
        String slat = String.valueOf(password.hashCode() + "miemie!#");// 加密盐
        password = DigestUtils.md5Hex(password + slat);
        System.out.println("密码：" + password);
        System.out.println(Character.valueOf((char) 101));
    }


    @Test
    public void testLock01() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 占位，如果 key不存在才可以设置成功
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1");
        // 如果占位成功，进行正常操作
        if (isLock) {
            valueOperations.set("name", "abc");
            String name = (String) valueOperations.get("name");
            System.out.println("name:" + name);
            // 操作成功，删除锁
//            redisTemplate.delete("k1");
        } else {
            System.out.println("有线程在使用");
        }
    }

    /**
     * 为锁设置结束时间，避免执行中途出现异常导致无法删除锁
     * <p>
     * 为锁设置结束时间存在的问题：可能事务还没结束，锁就释放了
     */
    @Test
    public void testLock02() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 占位，如果 key不存在才可以设置成功
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1", 5, TimeUnit.MILLISECONDS);
        // 如果占位成功，进行正常操作
        if (isLock) {
            valueOperations.set("name", "abc");
            String name = (String) valueOperations.get("name");
            System.out.println("name:" + name);
            Integer.parseInt("xxxx");
            // 操作成功，删除锁
            redisTemplate.delete("k1");
        } else {
            System.out.println("有线程在使用");
        }
    }

    /**
     * 为锁设置随机值，通过比较随机值是否一样，确定是不是自己的锁，避免删除了别的锁（类似于乐观锁）
     * 存在的问题：步骤繁琐：1.加锁   2.获取锁 3.比较锁  4.删除锁
     */
    @Test
    public void testLock03() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 占位，如果 key不存在才可以设置成功
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1", 5, TimeUnit.MILLISECONDS);
        // 如果占位成功，进行正常操作
        if (isLock) {
            valueOperations.set("name", "abc");
            String name = (String) valueOperations.get("name");
            System.out.println("name:" + name);
            Integer.parseInt("xxxx");
            // 操作成功，删除锁
            redisTemplate.delete("k1");
        } else {
            System.out.println("有线程在使用");
        }
    }

    @Autowired
    private RedisScript<Boolean> script;

    @Test
    public void testLock04() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String value = UUID.randomUUID().toString();
        // 添加过期时间，防止应用在运行过程中出现异常，导致锁无法得到释放
        Boolean isLock = valueOperations.setIfAbsent("k1", value, 6, TimeUnit.SECONDS);

        if (isLock) {// 没人占位，即拿到了锁
            valueOperations.set("name", "xxxxx");
            System.out.println("name = " + valueOperations.get("name"));
            System.out.println(valueOperations.get("k1"));
            // 释放锁：执行lua脚本，传入 一个 key-value集合
            Boolean result = (Boolean) redisTemplate.execute(script, Collections.singletonList("k1"), value);
            System.out.println(result);
        } else {// 有人占位，暂缓操作
            System.out.println("有线程在使用，请稍后");
        }
    }

}