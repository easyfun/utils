package com.efun.framework.common.enums;

import com.efun.framework.common.mybatis.IntegerValuedEnum;

public enum UserAgent implements IntegerValuedEnum {
	browser(1),
	ios(2),
	android(3),
	;

	private int value;

	private UserAgent(int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}
}
