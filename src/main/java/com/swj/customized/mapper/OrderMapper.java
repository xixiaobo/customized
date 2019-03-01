package com.swj.customized.mapper;

import com.swj.customized.bean.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByTaskId(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectBySelective(Order record);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}