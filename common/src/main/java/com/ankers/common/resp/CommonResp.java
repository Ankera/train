package com.ankers.common.resp;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class CommonResp<T> {

    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    private T content;

    public CommonResp() {
    }

    public CommonResp(boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }

    public CommonResp(T content) {
        this.content = content;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CommonResp{");
        sb.append("success=").append(success);
        sb.append(", message='").append(message).append('\'');
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }

    public static class PageReq {

        @NotNull(message = "【页码】不能为空")
        private Integer page;

        @NotNull(message = "【每页条数】不能为空")
        @Max(value = 100, message = "【每页条数】不能超过100")
        private Integer size;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        @Override
        public String toString() {
            return "PageReq{" +
                    "page=" + page +
                    ", size=" + size +
                    '}';
        }
    }
}
