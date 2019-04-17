package com.swj.customized.mapper;

import com.swj.customized.dto.TaskDto;
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
     * 根据id获取任务
     * @param taskid
     * @return
     */
    TaskDto selectByPrimaryKey(String taskid);

    List<TaskDto> selectBySelective(@Param("task") Task task,@Param("sortfield")String sortfield,@Param("sortingdirection")String sortingdirection);
}
