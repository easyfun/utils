package com.efun.framework.task.handler;

import com.efun.framework.task.entity.TaskPO;

public interface ITaskHandler {
    /**
     * 注意taskPO不可变
     * @param taskPO
     * @return
     */
    TaskExecuteResult execute(TaskPO taskPO, Object params);
}
