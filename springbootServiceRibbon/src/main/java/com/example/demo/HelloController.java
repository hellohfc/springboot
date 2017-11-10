package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HUFUCHUN on 2017/9/24.
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }

    @RequestMapping("/home/hi")
    public String home(@RequestParam String name){
        return helloService.forwardService(name);
    }
}
