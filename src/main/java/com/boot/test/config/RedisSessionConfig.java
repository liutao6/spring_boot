package com.boot.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by tao on 2017/2/13.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60) //1分钟失效
public class RedisSessionConfig {
}
