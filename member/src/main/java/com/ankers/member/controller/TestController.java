package com.ankers.member.controller;

import com.ankers.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/hello")
    public String hello() {
        int count = memberService.count();
        return "hello world => " + count;
    }
}
