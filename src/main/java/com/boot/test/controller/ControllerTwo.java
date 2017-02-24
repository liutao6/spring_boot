package com.boot.test.controller;

import com.boot.test.model.Demo;
import com.boot.test.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/2/10.
 */
@Api(value = "ControllerTwo", description = "ControllerTwo描述")
@RestController
@RequestMapping("/controller_two")
public class ControllerTwo {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DemoService demoService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试保存数据方法.
     *
     * @return
     */
    @PostMapping("/save/{name}")
    @ApiOperation(value="测试保存数据方法", notes="测试保存数据方法详细描述")//swagger的配置
    public String save(@ApiParam(required=true, name="name", value="姓名") @PathVariable String name) {
        demoService.save(name);//保存数据.
        return "success";
    }

    /**
     * 测试获取数据方法.
     *
     * @return
     */
    @GetMapping("/getById/{id}")
    @ApiOperation(value="测试获取数据方法", notes="测试获取数据方法详细描述")//swagger的配置
    public Demo getById(@ApiParam(required=true, name="id", value="id") @PathVariable Long id, HttpSession session) {
        Demo demo = demoService.getDemoById(id);
        session.setAttribute("user",demo);
        return demo;//保存数据.
    }
    /**
     * 返回demo数据:
     * 请求地址：http://127.0.0.1:8080/demo/getDemo
     *
     * @return
     */
    @GetMapping("/getDemo")
    @ApiOperation(value="返回demo数据", notes="返回demo数据详细描述")//swagger的配置
    public Demo getDemo() {
        Demo demo = new Demo();
        demo.setId(1L);
        demo.setName("Angel");
        return demo;
    }

    /**
     * 异常
     *
     * @return
     */
    @GetMapping("/zeroException")
    @ApiOperation(value="异常", notes="异常详细描述")//swagger的配置
    public int zeroException() {
        return 100 / 0;
    }

}
