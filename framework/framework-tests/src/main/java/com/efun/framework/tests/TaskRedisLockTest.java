package com.efun.framework.tests;

import com.efun.framework.task.util.TaskRedisLock;
import com.efun.framework.test.SpringTestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskRedisLockTest extends SpringTestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRedisLockTest.class);

    @Autowired
    private TaskRedisLock taskRedisLock;

    @Test
    public void lockTimeout() {
        LOGGER.debug("lock={}",taskRedisLock.lockTimeout("easyfun", 100));
    }

    @Test
    public void unlock() {
        taskRedisLock.unlock("easyfun");
    }
}
