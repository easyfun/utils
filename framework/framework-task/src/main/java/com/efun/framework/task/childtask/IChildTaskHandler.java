package com.efun.framework.task.childtask;

import com.efun.framework.task.entity.TaskPO;
import com.efun.framework.task.handler.TaskExecuteResult;

public interface IChildTaskHandler {
    TaskExecuteResult execute(ChildTaskContent content, TaskPO taskPO);
}
