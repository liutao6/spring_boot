package com.boot.test.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Administrator on 2017/2/10.
 * 定时任务
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Scheduled(cron = "0/20 * * * * ?")//20s
    public void scheduled() {
        System.out.println("scheduled");
    }
}
