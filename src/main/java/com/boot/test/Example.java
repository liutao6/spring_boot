package com.boot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/2/10.
 */
@SpringBootApplication//spinrg启动所需
//@ServletComponentScan//扫描对应的servlet包（监听，过滤等）
//@ComponentScan(basePackages={"cn.test","org.test","com.boot.test"}) 指定扫描的位置。(默认扫描本类同包及子包)
public class Example extends SpringBootServletInitializer {





    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // TODO Auto-generated method stub
        return builder.sources(Example.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}
