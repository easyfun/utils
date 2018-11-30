package com.efun.framework.common.exception;

import java.text.ParseException;

/**
 * Created by easyfun on 2018/4/28.
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    private String errorMessage;

    public BaseException(ErrorCode errorCode) {
        this(errorCode, null, null);
    }

    public BaseException(ErrorCode errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public BaseException(ErrorCode errorCode, String errorMessage, Throwable e) {
        super(String.format("errorCodeName=%s,errorCode=%s,errorMessage=%s", errorCode, errorCode.getFailCode(), errorMessage), e);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(ErrorCode errorCode, Throwable e) {
        super(String.format("errorCode=%s", errorCode.getFailCode()), e);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static ErrorCode getErrorCode(Throwable e){
        if( ParseException.class.isAssignableFrom(e.getClass()) ){
            return CommonErrorCode.paramError;
        }

        Throwable parentCause = e;
        Throwable cause = parentCause.getCause();
        while( null != cause ){
            parentCause = cause;
            cause = cause.getCause();
        }

        if(parentCause instanceof BaseException ){
            return ((BaseException) parentCause).getErrorCode();
        }else {
            return SystemErrorCode.systemException;
        }
    }

    public static String getFailCode(Throwable e){
        ErrorCode errorCode = getErrorCode(e);
        return errorCode.getFailCode();
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

}
