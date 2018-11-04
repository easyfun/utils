package com.efun.framework.task.entity.builder;

import com.efun.framework.task.entity.PopTask;
import com.efun.framework.task.entity.TaskPO;
import com.efun.framework.task.enums.PopTaskResult;

/**
 * Created by easyfun on 2018/6/19.
 */
public class PopTaskBuilder {
    public static PopTask build(TaskPO taskPO, PopTaskResult popTaskResult) {
        PopTask popTask = new PopTask();
        popTask.setTaskPO(taskPO);
        popTask.setPopTaskResult(popTaskResult);
        return popTask;
    }
}
