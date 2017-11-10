package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by HUFUCHUN on 2017/9/24.
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    //对该方法创建了熔断器功能，并指定了fallbackMethod熔断方法
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name){

           return  restTemplate.getForObject("http://SERVICE-HI/hiribbon?name="+name,String.class);
    }

    public String forwardService(String name){
        return  restTemplate.getForObject("http://SERVICE-HI/home?name="+name,String.class);
    }

    public String hiError(String name){
        /*Long start = System.currentTimeMillis();

        String result = "hi,"+name+",sorry,error!";

        Long end = System.currentTimeMillis();

        System.out.println("+++++spend time++++"+(end-start));

        return result;*/
        return "sorry "+name;
    }
}
