package com.efun.framework.task.dao;

import com.efun.framework.task.entity.TaskPO;

import java.util.List;

public interface TaskDAO {
    int insert(TaskPO record);

    int insertSelective(TaskPO record);

    TaskPO selectByPrimaryKey(Long id);

    List<TaskPO> selectListByTaskKey(String taskKey);

    int updateByPrimaryKeySelective(TaskPO record);

    int updateByPrimaryKey(TaskPO record);
}