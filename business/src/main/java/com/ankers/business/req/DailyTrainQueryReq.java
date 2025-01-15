package com.ankers.business.req;

import com.ankers.common.req.PageReq;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DailyTrainQueryReq extends PageReq {

    /**
     * get 请求用 DateTimeFormat
     * post 请求用 JsonFormat
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date date;

    private String code;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
