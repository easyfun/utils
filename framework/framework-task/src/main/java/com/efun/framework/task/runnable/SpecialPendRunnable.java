package com.efun.framework.task.runnable;

import com.efun.framework.task.TaskRedisKey;
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

public class SpecialPendRunnable implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialPendRunnable.class);

    private TaskManager taskManager;
    private TaskRedisLock taskRedisLock;
    private String handler = TaskRedisKey.TASK_PENDING_SPECIAL;

    public SpecialPendRunnable(TaskManager taskManager, TaskRedisLock taskRedisLock) {
        this.taskManager = taskManager;
        this.taskRedisLock = taskRedisLock;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            boolean lock = false;
            PopTask popTask = null;
            String lockId = taskRedisLock.buildSpecialTaskLockId(handler);
            try {
                lock = taskRedisLock.lockPendingTask(lockId);
                if (false == lock) {
                    LOGGER.debug("获取t_task:special:pending:zset执行权失败");
                    continue;
                }

                popTask = taskManager.popPendingTask(handler, new Date());
                if (PopTaskResult.success == popTask.getPopTaskResult()) {
                    // 执行任务
                    executeTask(popTask.getTaskPO());
                }
            } catch (Exception e) {
                LOGGER.error("任务执行出错.", e);
            } finally {
                if (lock) {
                    taskRedisLock.unlockPendingTask(lockId);
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
        if (taskPO.getTaskStatus() == TaskStatus.accepted || taskPO.getTaskStatus() == TaskStatus.retrying) {
            // 超时补任务
            timeoutSupplyRetryTask(taskPO);
            return;
        }

        if (taskPO.getTaskStatus() == TaskStatus.paused || taskPO.getTaskStatus() == TaskStatus.cancelled) {
            // TODO
            return;
        }

        if (taskPO.getTaskStatus() == TaskStatus.successful) {
            successFinishTask(taskPO);
            return;
        }
        if (taskPO.getTaskStatus() == TaskStatus.failed) {
            failFinishTask(taskPO);
            return;
        }
        if (taskPO.getTaskStatus() == TaskStatus.moreRetryFailed) {
            moreRetryFailFinishTask(taskPO);
            return;
        }
    }

    private void successFinishTask(TaskPO taskPO) {
//        Date current = new Date();
//        TaskPOBuilder.updateDoneTime(taskPO, current);
//        taskPO.setTaskStatus(TaskStatus.successful);

        taskManager.finishTask(taskPO);
    }

    private void moreRetryFailFinishTask(TaskPO taskPO) {
//        Date current = new Date();
//        TaskPOBuilder.updateDoneTime(taskPO, current);
//        taskPO.setTaskStatus(TaskStatus.moreRetryFailed);

        taskManager.finishTask(taskPO);
    }

    private void failFinishTask(TaskPO taskPO) {
//        Date current = new Date();
//        TaskPOBuilder.updateDoneTime(taskPO, current);
//        taskPO.setTaskStatus(TaskStatus.failed);

        taskManager.finishTask(taskPO);
    }

    private void timeoutSupplyRetryTask(TaskPO taskPO) {
        Date current = new Date();
        taskPO.setNextTime(current);
        taskPO.setUpdatedTime(current);
        taskManager.timeoutSupplyRetryTask(taskPO);
    }

}
