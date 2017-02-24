package com.boot.test;

import com.boot.test.model.Demo;
import com.boot.test.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by tao on 2017/2/13.
 */
//// SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringJUnit4ClassRunner.class)

//// 指定我们SpringBoot工程的Application启动类
@SpringBootTest(classes = Example.class)

public class DemoTest {

    @Resource
    private DemoService demoService;
    @Autowired
    private RedisOperations<Object,Object> redisTemplate;;



    @Test
    public void test(){
        Demo demo = new Demo();
        demo.setName("刘涛");
        redisTemplate.opsForValue().set("test",demo);
        Demo obj = (Demo)   redisTemplate.opsForValue().get("test");
        System.out.println(obj.getName());
    }
}
