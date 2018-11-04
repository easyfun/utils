package com.efun.framework.task.enums;

import com.efun.framework.task.mybatis.IntegerValuedEnum;

/**
 * Created by easyfun on 2018/6/19.
 */
public enum PopTaskResult implements IntegerValuedEnum {
    success(1),

    failSleep(2),

    tryNext(3),
    ;

    private int value;

    PopTaskResult(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
