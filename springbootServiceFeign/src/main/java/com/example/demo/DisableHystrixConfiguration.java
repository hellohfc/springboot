package com.example.demo;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Created by HUFUCHUN on 2017/11/2.
 * 创建一个关闭Hystrix的配置类
 */
public class DisableHystrixConfiguration {
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }
}
