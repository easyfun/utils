package com.efun.framework.tests;

import com.efun.framework.task.TaskHandler;
import com.efun.framework.task.entity.TaskPO;
import com.efun.framework.task.enums.TaskResult;
import com.efun.framework.task.handler.TaskExecuteResult;
import com.efun.framework.task.handler.ITaskHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TaskHandler
public class TestTaskHandler implements ITaskHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestTaskHandler.class);

    @Override
    public TaskExecuteResult execute(TaskPO taskPO, Object params) {
        LOGGER.info("hello test task");

        TaskExecuteResult result = new TaskExecuteResult();
        result.setTaskResult(TaskResult.success);
        result.setProgress(0);
        return result;
    }
}
