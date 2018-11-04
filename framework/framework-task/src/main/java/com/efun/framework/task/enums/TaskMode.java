package com.efun.framework.task.enums;

import com.efun.framework.task.mybatis.IntegerValuedEnum;

/**
 * Created by easyfun on 2018/3/27.
 */
public enum TaskMode implements IntegerValuedEnum {
    normal(1),
    special(2),
    ;

    private TaskMode(int value) {
        this.value = value;
    }

    private int value;

    @Override
    public int getValue() {
        return value;
    }

}
