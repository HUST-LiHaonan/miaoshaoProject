package com.lhn;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MiaoshaoProjectApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    void contextLoads() {
    }

    @Test
    void testRedis(){
        while (!redisTemplate.opsForValue().setIfAbsent("name", "mylock", 10, TimeUnit.SECONDS)){
            System.out.println("当前有人正在获取锁");
        }
        System.out.println("已经获取到锁");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}
