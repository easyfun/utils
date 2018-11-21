package com.efun.framework.common.log;

import com.efun.framework.common.enums.Result;

import java.util.Date;

public class BusinessLog {
    //请求id,uuid
    private String applyId;

    //请求类型
    private String applyType;

    //处理器
    private String handler;

    //请求参数
    private String applyParam;

    //结果参数
    private String resultParam;

    //处理结果
    private Result result;

    //失败码
    private String failCode;

    //失败原因
    private String failReason;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTimd;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getApplyParam() {
        return applyParam;
    }

    public void setApplyParam(String applyParam) {
        this.applyParam = applyParam;
    }

    public String getResultParam() {
        return resultParam;
    }

    public void setResultParam(String resultParam) {
        this.resultParam = resultParam;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getFailCode() {
        return failCode;
    }

    public void setFailCode(String failCode) {
        this.failCode = failCode;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTimd() {
        return updateTimd;
    }

    public void setUpdateTimd(Date updateTimd) {
        this.updateTimd = updateTimd;
    }
}
