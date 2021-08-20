package com.hhrhub.authz.core.common;

import com.hhrhub.authz.core.enums.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = -6204108856879594781L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private Integer code;
    private String msg;
    private long ts;

    public Result() {

    }

    public Result(ResultType resultType) {
        this(resultType, null);
    }

    public Result(ResultType resultType, T data) {
        this.data = data;
        this.code = resultType.getCode();
        this.msg = resultType.getMsg();
        this.ts = System.currentTimeMillis();
    }

    public static Result<?> succeed() {
        return new Result<>(ResultType.SUCCESS, null);
    }

    public static <T> Result<T> succeed(T model) {
        return new Result<>(ResultType.SUCCESS, model);
    }

    public static <T> Result<T> failed(ResultType resultType) {
        return new Result<>(resultType);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return code == 0;
    }

}


