package com.bw.weidushop.bean;


public class Result<T> {
    private String message="请求失败";
    private String status="-1";
    private T result;//请求结果
    public String orderId;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
