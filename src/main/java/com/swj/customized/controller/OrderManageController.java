package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swj.customized.bean.Order;
import com.swj.customized.dto.OrderDto;
import com.swj.customized.mapper.OrderMapper;
import com.swj.customized.tool.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by xxb on 2018/10/22.
 */
@RestController
@ResponseBody
@Api(value = "订单接口" )
@RequestMapping("OrderManage")
@Slf4j
public class OrderManageController {


    @Resource
    private OrderMapper orderMapper;

    @RequestMapping(value = "addOrder", method = RequestMethod.POST)
    @ApiOperation(value = "添加订单", notes = "添加新订单")
    @Transactional
    public JSONObject addOrder(@RequestBody Order order){
        JSONObject re = new JSONObject();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        order.setId(uuid);
        order.setStatus("0");
        try {
            order.setCreatetime(TimeUtil.getNewDateString());
            orderMapper.insertSelective(order);
            re.put("code", "1");
            re.put("message", "添加订单成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "添加订单失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "updaOrder", method = RequestMethod.PUT)
    @Transactional
    @ApiOperation(value = "修改订单", notes = "修改订单,状态（0：待确认；1：确认；-2：拒绝；-1：失效）")
    public JSONObject updataOrder(@RequestBody Order order) {
        JSONObject re = new JSONObject();
        try {
            if (order.getStatus().equals("1") || order.getStatus().equals("-1") || order.getStatus().equals("-2")){
                order.setOvertime(TimeUtil.getNewDateString());
                if(order.getStatus().equals("1")){
                    OrderDto o=orderMapper.selectByPrimaryKey(order.getId());
                    String taskid=o.getTaskid();
                    orderMapper.updateStatusByTaskid(taskid,"-2",TimeUtil.getNewDateString());
                }
            }
            orderMapper.updateByPrimaryKeySelective(order);
            re.put("code", "1");
            re.put("message", "修改订单状态成功");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            re.put("code", "0");
            re.put("message", "修改订单状态失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "deleteOrder/{orderid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除订单", notes = "根据订单id删除订单")
    @Transactional
    public JSONObject deleteOrder(@PathVariable("orderid") String orderid) {
        JSONObject re = new JSONObject();
        try {
            Order order1=new Order();
            order1.setId(orderid);
            order1.setIsdelete("1");
            orderMapper.updateByPrimaryKeySelective(order1);
            re.put("code", "1");
            re.put("message", "删除订单成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "删除订单失败,请联系后台");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "realdeleteOrder/{orderid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "真删除订单", notes = "根据订单id删除订单")
    @Transactional
    public JSONObject realdeleteOrder(@PathVariable("orderid") String orderid) {
        JSONObject re = new JSONObject();
        try {
            OrderDto o=orderMapper.selectByPrimaryKey(orderid);
            if(o.getStatus().equals("0")){
                orderMapper.deleteByPrimaryKey(orderid);
                re.put("code", "1");
                re.put("message", "删除订单成功");
            }else {
                re.put("code", "0");
                re.put("message", "删除订单失败，订单状态已变更");
            }

        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "删除订单失败,请联系后台");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "getOrderById", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定订单基本信息", notes = "根据订单ID获取指定订单基本信息")
    public JSONObject getOrderById(@RequestParam String orderid) {
        JSONObject re = new JSONObject();
        OrderDto c = orderMapper.selectByPrimaryKey(orderid);
        if (c == null) {
            re.put("code", "0");
            re.put("message", "订单获取失败或订单为空！");
            re.put("result", new JSONObject());
        } else {
            re.put("code", "1");
            re.put("message", "订单信息查询成功！");
            re.put("result", c);
        }
        return re;
    }

    @ApiOperation(value = "获取所有订单", notes = "获取所有订单或分页获取所有订单")
    @RequestMapping(value = "getAllOrder", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ispage", value = "是否使用分页", required = true, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询页数",   dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",  dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sortfield", value = "排序字段名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sortingdirection", value = "排序方式", dataType = "String", paramType = "query"),
    })
    public JSONObject getAllTask(@RequestParam(name = "ispage") boolean ispage,
                                 @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(name = "sortfield", required = false) String sortfield,
                                 @RequestParam(name = "sortingdirection", required = false) String sortingdirection,
                                 @RequestBody Order order)
    {
        if (ispage) {
            PageHelper.startPage(pageNum, pageSize);
        }
        JSONObject k = new JSONObject();

        try {
            List<OrderDto> cs = orderMapper.selectBySelective(order,sortfield,sortingdirection);
            k.put("code", 1);
            k.put("message", "查询成功");
            k.put("result", cs);
            if (ispage) {
                PageInfo<OrderDto> pageInfo = new PageInfo<>(cs);
                k.put("result", pageInfo);
            }
        }catch (Exception e){
            k.put("code", 0);
            k.put("message", "查询失败");
            k.put("result", new JSONObject());
            k.put("Exception",e);
        }
        return k;
    }


}
