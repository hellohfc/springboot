package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HUFUCHUN on 2017/10/14.
 */
@RestController
public class HiController {
    @Autowired
     SchedualServiceHi schedualServiceHi;

    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @RequestMapping(value = "hi2",method = RequestMethod.GET)
    public String sayHello(){
        StringBuilder sb = new StringBuilder();
     //   sb.append(schedualServiceHi.hello(new User("hfc",23))).append("\n");
       // sb.append(schedualServiceHi.hello("hfc123",23)).append("\n");
        return sb.toString();
    }
}
