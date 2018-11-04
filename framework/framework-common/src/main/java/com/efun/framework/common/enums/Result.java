package com.efun.framework.common.enums;

import com.efun.framework.common.mybatis.IntegerValuedEnum;

public enum Result implements IntegerValuedEnum {
	/** 成功状态码 */
	success(0),

	/** 失败状态码 */
	fail(10),

	/** 已受理状态码 */
	accepted(20)

	;

	private int value;

	private Result(int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}
}
