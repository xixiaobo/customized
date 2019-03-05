package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swj.customized.bean.Message;
import com.swj.customized.mapper.MessageMapper;
import com.swj.customized.tool.JSONTool;
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
@Api(value = "留言接口", description = "留言接口")
@RequestMapping("messageManage")
@Slf4j
public class MessageController {


    @Resource
    private MessageMapper messageMapper;

    @RequestMapping(value = "addMessage", method = RequestMethod.POST)
    @ApiOperation(value = "添加留言", notes = "添加新留言")
    @Transactional
    public JSONObject addMessage(@RequestBody Message message){
        JSONObject re = new JSONObject();
        try {
            messageMapper.insertSelective(message);
            re.put("code", "1");
            re.put("message", "添加留言成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "添加留言失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "updaMessage", method = RequestMethod.PUT)
    @Transactional
    @ApiOperation(value = "修改留言", notes = "修改留言")
    public JSONObject updataMessage(@RequestBody Message message) {
        JSONObject re = new JSONObject();
        try {
            messageMapper.updateByPrimaryKeySelective(message);
            re.put("code", "1");
            re.put("message", "修改留言成功");
        }catch (Exception e){

            re.put("code", "0");
            re.put("message", "修改留言失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "deleteMessage/{messageid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除留言", notes = "根据留言id删除留言")
    @Transactional
    public JSONObject deleteMessage(@PathVariable("messageid") int messageid) {
        JSONObject re = new JSONObject();
        try {
            messageMapper.deleteByPrimaryKey(messageid);
            re.put("code", "1");
            re.put("message", "删除留言成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "删除留言失败,请联系后台");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "getMessageById", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定留言基本信息", notes = "根据留言ID获取指定留言基本信息")
    public JSONObject getMessageById(@RequestParam int messageid) {
        JSONObject re = new JSONObject();
        Message c = messageMapper.selectByPrimaryKey(messageid);
        if (c == null) {
            re.put("code", "0");
            re.put("message", "留言获取失败或留言为空！");
            re.put("result", new JSONObject());
        } else {
            re.put("code", "1");
            re.put("message", "留言信息查询成功！");
            re.put("result", c);
        }
        return re;
    }

    @ApiOperation(value = "获取所有留言", notes = "获取所有留言或分页获取所有留言")
    @RequestMapping(value = "getAllMessage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ispage", value = "是否使用分页", required = true, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int", paramType = "query"),
    })
    public JSONObject getAllMessage(@RequestParam(name = "ispage") boolean ispage,
                               @RequestParam(name = "pageNum", required = false) Integer pageNum,
                               @RequestParam(name = "pageSize", required = false) Integer pageSize)
    {
        if (ispage) {
            PageHelper.startPage(pageNum, pageSize);
        }
        JSONObject k = new JSONObject();

        try {
            List<Message> cs = messageMapper.selectBySelective(null);
            k.put("code", 1);
            k.put("message", "查询成功");
            k.put("result", cs);
            if (ispage) {
                PageInfo<Message> pageInfo = new PageInfo<Message>(cs);
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
