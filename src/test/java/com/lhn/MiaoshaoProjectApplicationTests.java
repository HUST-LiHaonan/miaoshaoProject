package com.lhn;


import com.lhn.dao.MiaoShaUserDao;
import com.lhn.domain.MiaoshaUser;
import com.lhn.rabbitmq.MQReceiver;
import com.lhn.rabbitmq.MQSender;
import com.lhn.utils.MD5Util;
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
    void testUserDao(){
        System.out.println(userDao.getUserById("13872766128"));
    }

    @Test
    void testMD5(){
        String formPassWord = MD5Util.inputPassWordToFormPassWord("lihaonan1");
        System.out.println(formPassWord);
        String dbPassWord = MD5Util.formPassWordToDbPassWord(formPassWord,"1q2w3e4r5t");
        System.out.println(dbPassWord);
    }


}
