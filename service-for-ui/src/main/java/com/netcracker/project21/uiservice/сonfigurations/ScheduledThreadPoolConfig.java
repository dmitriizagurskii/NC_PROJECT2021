package com.netcracker.project21.uiservice.сonfigurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
public class ScheduledThreadPoolConfig {

    public final int CORE_POOL_SIZE = 1;

    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
        return new ScheduledThreadPoolExecutor(CORE_POOL_SIZE);
    }
}
