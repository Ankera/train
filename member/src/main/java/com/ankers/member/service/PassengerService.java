package com.ankers.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
//import com.github.pagehelper.PageHelper;
import com.ankers.common.context.LoginMemberContext;
import com.ankers.common.req.PageResp;
import com.ankers.common.util.SnowUtil;
import com.ankers.member.domain.Passenger;
import com.ankers.member.domain.PassengerExample;
import com.ankers.member.mapper.PassengerMapper;
import com.ankers.member.req.PassengerQueryReq;
import com.ankers.member.req.PassengerSaveReq;
import com.ankers.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);

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

    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        Long memberId = req.getMemberId();
        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(memberId);
        }

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());

        List<Passenger> passengers = passengerMapper.selectByExample(passengerExample);
        List<PassengerQueryResp> list = BeanUtil.copyToList(passengers, PassengerQueryResp.class);

        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(100L);
        pageResp.setList(list);
        return pageResp;
    }
}