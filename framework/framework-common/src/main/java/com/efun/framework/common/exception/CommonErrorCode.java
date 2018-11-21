package com.efun.framework.common.exception;

/**
 * Created by easyfun on 2018/4/28.
 */
public enum CommonErrorCode implements ErrorCode {
    /**
     * 请求参数错误
     */
    paramError("common00000000"),

    ;

    private String errorCode;

    private CommonErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getFailCode() {
        return errorCode;
    }
}
