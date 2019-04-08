package com.swj.customized.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.swj.customized.bean.Task;

@Mapper
public interface TaskMapper {
    /**
     * 添加任务
     * @param task
     * @return
     */
    int insert(@Param("task") Task task);

    /**
     * 添加任务
     * @param task
     * @return
     */
    int insertSelective(@Param("task") Task task);

    /**
     * 批量添加任务
     * @param tasks
     * @return
     */
    int insertList(@Param("tasks") List<Task> tasks);

    /**
     * 修改任务
     * @param task
     * @return
     */
    int updateByPrimaryKeySelective(@Param("task") Task task);

    /**
     * 根据id删除任务
     * @param taskid
     * @return
     */
    int deleteByPrimaryKey(String taskid);

    /**
     * 根据id获取任务
     * @param taskid
     * @return
     */
    Task selectByPrimaryKey(String taskid);

    List<Task> selectBySelective(Task task);
}
