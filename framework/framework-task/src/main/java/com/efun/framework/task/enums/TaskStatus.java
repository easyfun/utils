package com.efun.framework.task.enums;

import com.efun.framework.task.mybatis.IntegerValuedEnum;

/**
 * Created by easyfun on 2018/3/27.
 */
public enum TaskStatus implements IntegerValuedEnum {
    accepted(1),
    retrying(2),
    paused(3),
    cancelled(4),
    successful(5),
    failed(6),
    /** 重试次数失败 */
    moreRetryFailed(7)
    ;

    private TaskStatus(int value) {
        this.value = value;
    }

    private int value;

    @Override
    public int getValue() {
        return value;
    }

    public static TaskStatus buildTaskStatus(String name) {
        if (name.equals(accepted.name())) {
            return accepted;
        }
        if (name.equals(retrying.name())) {
            return retrying;
        }
        if (name.equals(paused.name())) {
            return paused;
        }
        if (name.equals(cancelled.name())) {
            return cancelled;
        }
        if (name.equals(successful.name())) {
            return successful;
        }
        if (name.equals(failed.name())) {
            return failed;
        }
        if (name.equals(moreRetryFailed)) {
            return moreRetryFailed;
        }
        return null;
    }
}
