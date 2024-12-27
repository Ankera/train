package com.ankers.member.controller;

import com.ankers.common.resp.CommonResp;
import com.ankers.member.req.MemberRegisterReq;
import com.ankers.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource(name = "memberService")
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        CommonResp<Integer> resp = new CommonResp<>();
        int count = memberService.count();
        resp.setContent(count);
        resp.setSuccess(true);
        resp.setMessage("查询成功");
        return resp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(MemberRegisterReq req) {
        CommonResp<Long> resp = new CommonResp<>();
        resp.setContent(memberService.register(req));
        resp.setSuccess(true);
        resp.setMessage("查询成功");
        return resp;
    }
}
