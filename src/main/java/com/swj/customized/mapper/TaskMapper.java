package com.swj.customized.mapper;

import com.swj.customized.bean.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer id);

    List<Task> selectBySelective(Task record);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKeyWithBLOBs(Task record);

    int updateByPrimaryKey(Task record);
}