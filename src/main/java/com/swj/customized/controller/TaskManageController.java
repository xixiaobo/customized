package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swj.customized.bean.Order;
import com.swj.customized.bean.Task;
import com.swj.customized.mapper.OrderMapper;
import com.swj.customized.mapper.TaskMapper;
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
@Api(value = "任务接口", description = "任务接口")
@RequestMapping("TaskManage")
@Slf4j
public class TaskManageController {


    @Resource
    private TaskMapper taskMapper;


    @Resource
    private OrderMapper orderMapper;

    @RequestMapping(value = "addTask", method = RequestMethod.POST)
    @ApiOperation(value = "添加任务", notes = "添加新任务")
    @Transactional
    public JSONObject addTask(@RequestBody Task task){
        JSONObject re = new JSONObject();
        task.setId(null);
        try {
            task.setCreatetime(TimeUtil.getNewDateString());
            taskMapper.insertSelective(task);
            re.put("code", "1");
            re.put("message", "添加任务成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "添加任务失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "updaTask", method = RequestMethod.PUT)
    @Transactional
    @ApiOperation(value = "修改任务", notes = "修改任务")
    public JSONObject updataTask(@RequestBody Task task) {
        JSONObject re = new JSONObject();
        try {
            taskMapper.updateByPrimaryKeySelective(task);
            re.put("code", "1");
            re.put("message", "修改任务成功");
        }catch (Exception e){

            re.put("code", "0");
            re.put("message", "修改任务失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "deleteTask/{taskid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除任务", notes = "根据任务id删除任务")
    @Transactional
    public JSONObject deleteTask(@PathVariable("taskid") int taskid) {
        JSONObject re = new JSONObject();
        try {
            Order order1=new Order();
            order1.setTaskid(taskid);
            List<Order> orders=orderMapper.selectBySelective(order1);
            if (orders.size()>0){
                for (Order o:orders){
                    if (o.getStatus()!=3||o.getStatus()!=2){
                        re.put("code", "0");
                        re.put("message", "该任务下已经存在未完成订单");
                        return re;
                    }
                }
            }
            taskMapper.deleteByPrimaryKey(taskid);
            orderMapper.deleteByTaskId(taskid);
            re.put("code", "1");
            re.put("message", "删除任务成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "删除任务失败,请联系后台");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "getTaskById", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定任务基本信息", notes = "根据任务ID获取指定任务基本信息")
    public JSONObject getTaskById(@RequestParam int taskid) {
        JSONObject re = new JSONObject();
        Task c = taskMapper.selectByPrimaryKey(taskid);
        if (c == null) {
            re.put("code", "0");
            re.put("message", "任务获取失败或任务为空！");
            re.put("result", new JSONObject());
        } else {
            re.put("code", "1");
            re.put("message", "任务信息查询成功！");
            re.put("result", c);
        }
        return re;
    }

    @ApiOperation(value = "获取所有任务", notes = "获取所有任务或分页获取所有任务")
    @RequestMapping(value = "getAllTask", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ispage", value = "是否使用分页", required = true, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int", paramType = "query"),
    })
    public JSONObject getAllTask(@RequestParam(name = "ispage") boolean ispage,
                               @RequestParam(name = "pageNum", required = false) Integer pageNum,
                               @RequestParam(name = "pageSize", required = false) Integer pageSize)
    {
        if (ispage) {
            PageHelper.startPage(pageNum, pageSize);
        }
        JSONObject k = new JSONObject();

        try {
            List<Task> cs = taskMapper.selectBySelective(null);
            k.put("code", 1);
            k.put("message", "查询成功");
            k.put("result", cs);
            if (ispage) {
                PageInfo<Task> pageInfo = new PageInfo<Task>(cs);
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
