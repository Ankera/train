package com.ankers.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.ankers.common.context.LoginMemberContext;
import com.ankers.common.util.SnowUtil;
import com.ankers.member.domain.Passenger;
import com.ankers.member.mapper.PassengerMapper;
import com.ankers.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
//        if (ObjectUtil.isNull(passenger.getId())) {
////            passenger.setMemberId(Logger);
//        }
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
