package com.swj.customized.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.swj.customized.bean.Order;

@Mapper
public interface OrderMapper {
    /**
     * 添加订单
     * @param order
     * @return
     */
    int insert(@Param("order") Order order);

    /**
     * 添加订单
     * @param order
     * @return
     */
    int insertSelective(@Param("order") Order order);

    /**
     * 批量添加订单
     * @param orders
     * @return
     */
    int insertList(@Param("orders") List<Order> orders);

    /**
     * 修改订单
     * @param order
     * @return
     */
    int updateByPrimaryKeySelective(@Param("order") Order order);

    /**
     * 根据条件查询订单
     * @param order1
     * @return
     */
    List<Order> selectBySelective(Order order1);

    /**
     * 根据id删除订单
     * @param orderid
     * @return
     */
    int deleteByPrimaryKey(String orderid);

    /**
     * 根据id获取订单
     * @param orderid
     * @return
     */
    Order selectByPrimaryKey(String orderid);

}
