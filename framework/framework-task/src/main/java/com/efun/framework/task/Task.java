package com.efun.framework.task;


import com.efun.framework.task.enums.RetryStrategy;
import com.efun.framework.task.enums.TaskMode;

import java.util.Date;

/**
 * Created by easyfun on 2018/3/27.
 */
public class Task {
    /** 默认最大重试次数 */
    public static final int DEFAULT_MAX_RETRY_TIMES = 15;
    /** 默认重试间隔60s */
    public static final int DEFAULT_RETRY_INTERVAL = 60 * 1000;

    private String taskKey;
    private String param;
    private String handler;
    private String business;
    private RetryStrategy retryStrategy = RetryStrategy.normal;
    private TaskMode taskMode = TaskMode.normal;
    /** 最大重试次数 */
    private int maxRetryTimes = DEFAULT_MAX_RETRY_TIMES;
    /** 重试时间间隔ms */
    private int retryInterval = DEFAULT_RETRY_INTERVAL;

    private Date nextTime = new Date();

    public static Task build(String handler, String taskKey) {
        Task task = new Task();
        task.taskKey = taskKey;
        task.handler = handler;
        return task;
    }

    public static Task build(String handler, String taskKey, int maxRetryTimes) {
        Task task = new Task();
        task.taskKey = taskKey;
        task.handler = handler;
        task.maxRetryTimes = maxRetryTimes;
        return task;
    }

    public static Task build(String handler, String taskKey, int maxRetryTimes, int retryInterval) {
        Task task = new Task();
        task.taskKey = taskKey;
        task.handler = handler;
        task.maxRetryTimes = maxRetryTimes;
        task.retryInterval = retryInterval;
        return task;
    }
    public static Task build(String handler, String taskKey, String param, int maxRetryTimes, int retryInterval) {
        Task task = new Task();
        task.taskKey = taskKey;
        task.handler = handler;
        task.maxRetryTimes = maxRetryTimes;
        task.retryInterval = retryInterval;
        task.param = param;
        return task;
    }

    public static Task build(String handler, String taskKey, String param) {
        Task task = new Task();
        task.taskKey = taskKey;
        task.handler = handler;
        task.param = param;
        return task;
    }

    public static Task build(String handler, String taskKey, String param, String business) {
        Task task = new Task();
        task.taskKey = taskKey;
        task.handler = handler;
        task.param = param;
        task.business = business;
        return task;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public RetryStrategy getRetryStrategy() {
        return retryStrategy;
    }

    public void setRetryStrategy(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    public int getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public void setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(int retryInterval) {
        if (retryInterval < 0) {
            this.retryInterval = DEFAULT_RETRY_INTERVAL;
        }
        this.retryInterval = retryInterval;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public TaskMode getTaskMode() {
        return taskMode;
    }

    public void setTaskMode(TaskMode taskMode) {
        this.taskMode = taskMode;
    }
}
