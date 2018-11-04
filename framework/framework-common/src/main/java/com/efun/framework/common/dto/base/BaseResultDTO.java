package com.efun.framework.common.dto.base;

import com.efun.framework.common.enums.Result;

import java.io.Serializable;

public class BaseResultDTO implements Serializable {
	private static final long serialVersionUID = 2045849073156171052L;
	private Result result;
	private String failCode;
	private String failReason;
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

	public BaseResultDTO() {

	}

	private BaseResultDTO(Result resultStatus, String failCode, String failReason) {
		this.result = resultStatus;
		this.failCode = failCode;
		this.failReason = failReason;
	}

	public static BaseResultDTO success() {
		return new BaseResultDTO(Result.success, null, null);
	}

	public static BaseResultDTO accepted() {
		return new BaseResultDTO(Result.accepted, null, null);
	}

	public static BaseResultDTO fail() {
		return new BaseResultDTO(Result.fail, null, null);
	}

	public static BaseResultDTO fail(String failCode) {
		return new BaseResultDTO(Result.fail, failCode, null);
	}

	public static BaseResultDTO fail(String failCode, String failReason) {
		return new BaseResultDTO(Result.fail, failCode, failReason);
	}

}
