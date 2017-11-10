package com.example.demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HUFUCHUN on 2017/10/14.
 */
@FeignClient(value = "service-hi",fallback = SchedualServiceHiHystrix.class)
//定义一个feign接口，通过@ FeignClient（value=“服务名”），来指定调用哪个服务。比如在代码中调用了service-hi服务的“/hi”接口

public interface SchedualServiceHi {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value="name")String name);

    /*@RequestMapping(value="hellouser",method = RequestMethod.GET)
    public User hello(@RequestHeader(value = "name") String name, @RequestHeader(value = "age") Integer age);

    @RequestMapping(value="hellouser1",method = RequestMethod.POST)
    public String hello(@RequestBody User user);*/
}
