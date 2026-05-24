package com.ankers.member.controller.admin;

import com.ankers.common.resp.CommonResp;
import com.ankers.common.resp.PageResp;
import com.ankers.member.req.TicketQueryReq;
import com.ankers.member.resp.TicketQueryResp;
import com.ankers.member.service.TicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/ticket")
public class TicketAdminController {

    @Resource
    private TicketService ticketService;

    @GetMapping("/query-list")
    public CommonResp<PageResp<TicketQueryResp>> queryList(@Valid TicketQueryReq req) {
        PageResp<TicketQueryResp> list = ticketService.queryList(req);
        return new CommonResp<>(list);
    }

}
