package com.lhn;


import com.lhn.dao.MiaoShaUserDao;
import com.lhn.domain.MiaoshaUser;
import com.lhn.utils.MD5Util;
import com.lhn.utils.UserKey;
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

    @Autowired
    private MiaoShaUserDao userDao;
    @Test
    void contextLoads() {
    }

    @Test
    void testRedis(){
        MiaoshaUser user = new MiaoshaUser();
        user.setId("18371935971");
        user.setPassword("123456");
        user.setNickname("李浩楠");
        redisTemplate.opsForValue().set(UserKey.getUserKeyById(user),user);
        System.out.println(redisTemplate.opsForValue().get(UserKey.getUserKeyById(user)));
    }

    @Test
    void testUserDao(){
        System.out.println(userDao.getUserById("18371935971"));
    }

    @Test
    void testMD5(){
        String formPassWord = MD5Util.inputPassWordToFormPassWord("123456");
        System.out.println(formPassWord);
        String dbPassWord = MD5Util.formPassWordToDbPassWord(formPassWord,"lhnwithcs9");
        System.out.println(dbPassWord);
    }
}
