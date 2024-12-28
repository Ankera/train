package com.ankers.member.service;

import com.ankers.common.util.SnowUtil;
import com.ankers.member.domain.Member;
import com.ankers.member.mapper.MemberMapper;
import com.ankers.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(MemberRegisterReq mobile) {
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile.getMobile());
        memberMapper.insert(member);
        return member.getId();
    }
}
