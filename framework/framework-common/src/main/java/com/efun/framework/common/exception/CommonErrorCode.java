package com.efun.framework.common.exception;

/**
 * Created by easyfun on 2018/4/28.
 */
public enum CommonErrorCode implements ErrorCode {
    /**
     * 请求参数错误
     */
    requestParamError("common00000000"),

    /**
     * 插入数据错误
     * */
    insertDataError("common90000000"),

    /**
     * 修改数据错误
     * */
    updateDataError("common90000000"),

    ;

    private String errorCode;

    private String errorReason;

    CommonErrorCode(String errorCode) {
        this.errorCode = errorCode;
        this.errorReason = this.name();
    }

    @Override
    public String getFailCode() {
        return errorCode;
    }

    @Override
    public String getFailReason() {
        return errorReason;
    }
}
