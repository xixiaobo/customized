package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swj.customized.bean.Order;
import com.swj.customized.mapper.OrderMapper;
import com.swj.customized.tool.JSONTool;
import com.swj.customized.tool.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xxb on 2018/10/22.
 */
@RestController
@ResponseBody
@Api(value = "订单接口", description = "订单接口")
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
        order.setId(null);
        order.setStatus(0);
        try {
            Order order1=new Order();
            order1.setTaskid(order.getTaskid());
            List<Order> orders=orderMapper.selectBySelective(order1);
            if (orders.size()>0){
                for (Order o:orders){
                    if (o.getStatus()!=3){
                        re.put("code", "0");
                        re.put("message", "该任务下已经存在订单");
                        return re;
                    }
                }
            }
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
    @ApiOperation(value = "修改订单状态", notes = "修改订单状态（0:新订单；1：待确认完成；2：已完成；3：放弃任务）")
    public JSONObject updataOrder(@RequestBody Order order) {
        JSONObject re = new JSONObject();
        try {
            Order order1=new Order();
            order1.setId(order.getId());
            order1.setStatus(order.getStatus());
            if (order.getStatus()==3||order.getStatus()==2){
                order1.setOvertime(TimeUtil.getNewDateString());
            }
            orderMapper.updateByPrimaryKeySelective(order1);
            re.put("code", "1");
            re.put("message", "修改订单状态成功");
        }catch (Exception e){

            re.put("code", "0");
            re.put("message", "修改订单状态失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "deleteOrder/{orderid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除订单", notes = "根据订单id删除订单")
    @Transactional
    public JSONObject deleteOrder(@PathVariable("orderid") int orderid) {
        JSONObject re = new JSONObject();
        try {
            orderMapper.deleteByPrimaryKey(orderid);
            re.put("code", "1");
            re.put("message", "删除订单成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "删除订单失败,请联系后台");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "getOrderById", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定订单基本信息", notes = "根据订单ID获取指定订单基本信息")
    public JSONObject getOrderById(@RequestParam int orderid) {
        JSONObject re = new JSONObject();
        Order c = orderMapper.selectByPrimaryKey(orderid);
        if (c == null) {
            re.put("code", "0");
            re.put("message", "订单获取失败或订单为空！");
            re.put("user", new JSONObject());
        } else {
            re.put("code", "1");
            re.put("message", "订单信息查询成功！");
            re.put("result", JSONTool.ObjectToJSONObject(c));
        }
        return re;
    }

    @ApiOperation(value = "获取所有订单", notes = "获取所有订单或分页获取所有订单")
    @RequestMapping(value = "getAllOrder", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ispage", value = "是否使用分页", required = true, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int", paramType = "query"),
    })
    public JSONObject getAllOrder(@RequestParam(name = "ispage") boolean ispage,
                               @RequestParam(name = "pageNum", required = false) Integer pageNum,
                               @RequestParam(name = "pageSize", required = false) Integer pageSize)
    {
        if (ispage) {
            PageHelper.startPage(pageNum, pageSize);
        }
        JSONObject k = new JSONObject();

        try {
            List<Order> cs = orderMapper.selectBySelective(null);
            k.put("code", 1);
            k.put("message", "查询成功");
            k.put("result", cs);
            if (ispage) {
                PageInfo<Order> pageInfo = new PageInfo<Order>(cs);
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
