package com.efun.framework.task.enums;

import com.efun.framework.task.mybatis.IntegerValuedEnum;

public enum TaskResult implements IntegerValuedEnum {
    success(1),
    fail(2),
    retry(3),
    /** 迁移到其他队列 */
    move(4),
    ;

    private int value;

    private TaskResult(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return 0;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
