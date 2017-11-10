package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by HUFUCHUN on 2017/11/1.
 */
@Component
public class SchedualServiceHiHystrix implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(@RequestParam(value = "name") String name) {
        return "sorry "+name;
    }


}
