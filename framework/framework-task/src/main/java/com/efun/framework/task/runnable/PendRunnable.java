package com.efun.framework.task.runnable;

import com.efun.framework.task.entity.PopTask;
import com.efun.framework.task.entity.TaskPO;
import com.efun.framework.task.enums.PopTaskResult;
import com.efun.framework.task.enums.TaskStatus;
import com.efun.framework.task.util.TaskRedisLock;
import com.efun.framework.task.util.ThreadUtil;
import com.efun.framework.task.TaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class PendRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(PendRunnable.class);

    private TaskManager taskManager;

    private TaskRedisLock taskRedisLock;

    public PendRunnable(TaskManager taskManager, TaskRedisLock taskRedisLock) {
        this.taskManager = taskManager;
        this.taskRedisLock = taskRedisLock;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            boolean lock = false;
            PopTask popTask = null;

            try {
                lock = taskRedisLock.lockPendingTask(TaskRedisLock.LOCK_TASK_PENDING);
                if (false == lock) {
                    LOGGER.debug("获取t_task:pending:zset执行权失败");
                    continue;
                }

                popTask = taskManager.popPendingTask(null, new Date());
                if (PopTaskResult.success == popTask.getPopTaskResult()) {
                    executeTask(popTask.getTaskPO());
                }
            } catch (Exception e) {
                LOGGER.error("任务执行出错.", e);
            } finally {
                if (lock) {
                    taskRedisLock.unlockPendingTask(TaskRedisLock.LOCK_TASK_PENDING);
                }

                if (null == popTask) {
                    ThreadUtil.safeSleep(5000);
                } else if (null != popTask && PopTaskResult.failSleep == popTask.getPopTaskResult()) {
                    ThreadUtil.safeSleep(5000);
                }
            }
        }
     }

    private void executeTask(TaskPO taskPO) {
        if (taskPO.getTaskStatus() == TaskStatus.accepted.getValue() || taskPO.getTaskStatus() == TaskStatus.retrying.getValue()) {
            // 超时补任务
            timeoutSupplyRetryTask(taskPO);
            return;
        }

        if (taskPO.getTaskStatus() == TaskStatus.paused.getValue() || taskPO.getTaskStatus() == TaskStatus.cancelled.getValue()) {
            // TODO
            return;
        }

        if (taskPO.getTaskStatus() == TaskStatus.successful.getValue()) {
            successFinishTask(taskPO);
            return;
        }
        if (taskPO.getTaskStatus() == TaskStatus.failed.getValue()) {
            failFinishTask(taskPO);
            return;
        }
        if (taskPO.getTaskStatus() == TaskStatus.moreRetryFailed.getValue()) {
            moreRetryFailFinishTask(taskPO);
            return;
        }
    }

    private void successFinishTask(TaskPO taskPO) {
//        Date current = new Date();
//        TaskPOBuilder.updateDoneTime(taskPO, current);
//        taskPO.setTaskStatus(TaskStatus.successful.getValue());

        taskManager.finishTask(taskPO);
    }

    private void moreRetryFailFinishTask(TaskPO taskPO) {
//        Date current = new Date();
//        TaskPOBuilder.updateDoneTime(taskPO, current);
//        taskPO.setTaskStatus(TaskStatus.moreRetryFailed.getValue());

        taskManager.finishTask(taskPO);
    }

    private void failFinishTask(TaskPO taskPO) {
//        Date current = new Date();
//        TaskPOBuilder.updateDoneTime(taskPO, current);
//        taskPO.setTaskStatus(TaskStatus.failed.getValue());

        taskManager.finishTask(taskPO);
    }

    private void timeoutSupplyRetryTask(TaskPO taskPO) {
        Date current = new Date();
        taskPO.setNextTime(current);
        taskPO.setUpdatedTime(current);
        taskManager.timeoutSupplyRetryTask(taskPO);
    }

}
