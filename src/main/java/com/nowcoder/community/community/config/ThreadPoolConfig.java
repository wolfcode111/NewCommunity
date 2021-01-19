package com.nowcoder.community.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling  //加上才会启动
@EnableAsync      //用这个注解才会启动
public class ThreadPoolConfig {
}
