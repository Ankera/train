package com.ankers.member.controller;

import com.ankers.common.resp.CommonResp;
import com.ankers.member.req.MemberLoginReq;
import com.ankers.member.req.MemberRegisterReq;
import com.ankers.member.req.MemberSendCodeReq;
import com.ankers.member.resp.MemberLoginResp;
import com.ankers.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        CommonResp<Long> resp = new CommonResp<>();
        resp.setContent(memberService.register(req));
        resp.setSuccess(true);
        resp.setMessage("查询成功");
        return resp;
    }

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
