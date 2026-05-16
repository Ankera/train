package com.ankers.batch.controller;

import com.ankers.batch.feign.BusinessFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    BusinessFeign businessFeign;

    @GetMapping("/hello")
    public String hello() {
        String hello = businessFeign.hello();
        return "hello world batch" + " => " + hello;
    }
}
