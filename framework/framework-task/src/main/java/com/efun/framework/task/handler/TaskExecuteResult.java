package com.efun.framework.task.handler;

import com.efun.framework.task.enums.TaskResult;

public class TaskExecuteResult {
    private TaskResult taskResult;
    private int progress;

    public TaskExecuteResult() {
        taskResult = TaskResult.success;
        progress = 0;
    }

    public TaskExecuteResult(TaskResult taskResult, int progress) {
        this.taskResult = taskResult;
        this.progress = progress;
    }

    public TaskResult getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(TaskResult taskResult) {
        this.taskResult = taskResult;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public static TaskExecuteResult success() {
        return new TaskExecuteResult(TaskResult.success, 0);
    }

    public static TaskExecuteResult success(int progress) {
        return new TaskExecuteResult(TaskResult.success, progress);
    }

    public static TaskExecuteResult fail() {
        return new TaskExecuteResult(TaskResult.fail, 0);
    }

    public static TaskExecuteResult fail(int progress) {
        return new TaskExecuteResult(TaskResult.fail, progress);
    }

    public static TaskExecuteResult retry() {
        return new TaskExecuteResult(TaskResult.retry, 0);
    }

    public static TaskExecuteResult retry(int progress) {
        return new TaskExecuteResult(TaskResult.retry, progress);
    }

}
