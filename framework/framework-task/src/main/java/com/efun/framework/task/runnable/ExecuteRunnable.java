package com.efun.framework.task.runnable;

import com.alibaba.fastjson.JSON;
import com.efun.framework.task.entity.PopTask;
import com.efun.framework.task.entity.TaskPO;
import com.efun.framework.task.entity.builder.TaskPOBuilder;
import com.efun.framework.task.enums.PopTaskResult;
import com.efun.framework.task.enums.RetryStrategy;
import com.efun.framework.task.enums.TaskStatus;
import com.efun.framework.task.handler.ITaskHandler;
import com.efun.framework.task.handler.TaskExecuteResult;
import com.efun.framework.task.util.DateUtil;
import com.efun.framework.task.util.ThreadUtil;
import com.efun.framework.task.TaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

import static com.efun.framework.task.enums.TaskResult.*;

public class ExecuteRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteRunnable.class);

    private Map<String, ITaskHandler> taskHandlerMap;
    private TaskManager taskManager;

    public ExecuteRunnable(Map<String, ITaskHandler> taskHandlerMap, TaskManager taskManager) {
        this.taskHandlerMap = taskHandlerMap;
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                PopTask popTask = taskManager.popExecutingTask(null, new Date());

                if (PopTaskResult.failSleep == popTask.getPopTaskResult()) {
                    ThreadUtil.safeSleep(5000);
                    continue;
                }

                if (PopTaskResult.success == popTask.getPopTaskResult()) {
                    TaskPO taskPO = popTask.getTaskPO();
                    ITaskHandler handler = taskHandlerMap.get(taskPO.getHandler());
                    if (null == handler) {
                        LOGGER.error("任务handler不存在, handlerName={}", taskPO.getHandler());
                        taskManager.deleteTask(null, taskPO.getTaskKey());
                        continue;
                    }

                    // 执行任务
                    executeTask(handler, taskPO);
                }
            } catch (Exception e) {
                LOGGER.error("任务执行出错.", e);
                ThreadUtil.safeSleep(5000);
            }
        }
    }

    private void executeTask(ITaskHandler handler, TaskPO taskPO) {
        Date current = new Date();
        TaskPOBuilder.updateLastTime(taskPO, current);
        if (null == taskPO.getFirstTime()) {
            taskPO.setFirstTime(new Date());
        }
        taskPO.setRetriedTimes(taskPO.getRetriedTimes() + 1);

        TaskPO copyTaskPO = TaskPOBuilder.copy(taskPO);
        TaskExecuteResult result = null;
        try {
            result = handler.execute(copyTaskPO, null);
        } catch (Exception e) {
            LOGGER.error("执行任务出错,executing queque process error. taskPO={}", JSON.toJSONString(taskPO), e);
            exceptionRetryTask(taskPO);
            return;
        }

        switch (result.getTaskResult()) {
            case success:
                successTask(result, taskPO);
                break;
            case fail:
                failTask(result, taskPO);
                break;
            case retry:
                retryTask(result, taskPO);
                break;
            case move:
                break;
        }
    }

    private void successTask(TaskExecuteResult result, TaskPO taskPO) {
        Date current = new Date();
        TaskPOBuilder.updateDoneTime(taskPO, current);
        taskPO.setTaskStatus(TaskStatus.successful);
        taskPO.setProgress(result.getProgress());

        taskManager.successTask(taskPO);
    }

    private void failTask(TaskExecuteResult result, TaskPO taskPO) {
        Date current = new Date();
        TaskPOBuilder.updateDoneTime(taskPO, current);
        taskPO.setTaskStatus(TaskStatus.failed);
        taskPO.setProgress(result.getProgress());

        taskManager.failTask(taskPO);
    }

    private void retryTask(TaskExecuteResult result, TaskPO taskPO) {
        taskPO.setProgress(result.getProgress());
        exceptionRetryTask(taskPO);
    }

    private void exceptionRetryTask(TaskPO taskPO) {
        Date current = new Date();

        taskPO.setNextTime(DateUtil.addMiliSeconds(taskPO.getNextTime(), taskPO.getRetryInterval()));

        if (taskPO.getRetryStrategy() == RetryStrategy.normal && taskPO.getRetriedTimes() >= taskPO.getMaxRetryTimes()) {
            TaskPOBuilder.updateDoneTime(taskPO, current);
            taskPO.setTaskStatus(TaskStatus.moreRetryFailed);
            taskManager.failTask(taskPO);
        } else {
            taskPO.setTaskStatus(TaskStatus.retrying);
            taskManager.retryTask(taskPO);
        }
    }

}
