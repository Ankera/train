package com.ankers.business.controller;

import com.ankers.business.req.ConfirmOrderDoReq;
import com.ankers.business.req.ConfirmOrderQueryReq;
import com.ankers.business.resp.ConfirmOrderQueryResp;
import com.ankers.business.service.ConfirmOrderService;
import com.ankers.common.resp.CommonResp;
import com.ankers.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/confirm-order")
public class ConfirmOrderController {

    @Resource
    private ConfirmOrderService confirmOrderService;

    @PostMapping("/do")
    public CommonResp<Object> doConfirm(@Valid @RequestBody ConfirmOrderDoReq req) {
        confirmOrderService.doConfirm(req);
        return new CommonResp<>();
    }
}
