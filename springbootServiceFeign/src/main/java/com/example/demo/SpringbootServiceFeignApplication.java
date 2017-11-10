package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients    //开启Feign功能
@EnableHystrixDashboard  //开启仪表盘
public class SpringbootServiceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceFeignApplication.class, args);
	}
}
