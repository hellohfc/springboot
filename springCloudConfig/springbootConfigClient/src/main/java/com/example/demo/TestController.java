package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HUFUCHUN on 2017/11/9.
 */
@RefreshScope
@RestController
public class TestController {
    @Value("${foo}")
    private String from;
    @RequestMapping("/from")
    public String from() {
        return this.from;
    }
}
