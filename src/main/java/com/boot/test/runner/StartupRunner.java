package com.boot.test.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by tao on 2017/2/13.
 */
@Component
@Order(value = 0)//服务启动的执行顺序，从小开始
public class StartupRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.err.println(">>服务启动执行，执行加载数据等操作<<");
    }
}
