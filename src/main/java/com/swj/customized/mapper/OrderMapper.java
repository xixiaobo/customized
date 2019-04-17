package com.swj.customized.mapper;

import com.swj.customized.dto.OrderDto;
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
     * 根据id删除订单
      * @param orderid
     * @return
     */
    int deleteByPrimaryKey(String orderid);
    /**
     * 修改订单
     * @param order
     * @return
     */
    int updateByPrimaryKeySelective(@Param("order") Order order);


    /**
     * 根据任务id修改订单状态
     * @param taskid
     * @return
     */
    int updateStatusByTaskid(@Param("taskid") String taskid,@Param("status") String status,@Param("overtime")String overtime);

    /**
     * 根据条件查询订单
     * @param order
     * @return
     */
    List<OrderDto> selectBySelective(@Param("order")Order order, @Param("sortfield")String sortfield, @Param("sortingdirection")String sortingdirection);

    /**
     * 根据id获取订单
     * @param orderid
     * @return
     */
    OrderDto selectByPrimaryKey(String orderid);

}
