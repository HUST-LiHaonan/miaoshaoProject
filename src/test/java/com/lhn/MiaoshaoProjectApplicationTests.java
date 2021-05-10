package com.lhn;


import com.lhn.domain.MiaoshaUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        MiaoshaUser user = new MiaoshaUser();
        user.setId(1L);
        user.setPassword("123456");
        user.setNickname("李浩楠");
        
    }
}
