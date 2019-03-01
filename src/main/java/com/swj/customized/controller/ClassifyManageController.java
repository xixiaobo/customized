package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swj.customized.bean.Classify;
import com.swj.customized.mapper.ClassifyMapper;
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
@Api(value = "分类接口", description = "分类接口")
@RequestMapping("classifyManage")
@Slf4j
public class ClassifyManageController {


    @Resource
    private ClassifyMapper classifyMapper;

    @RequestMapping(value = "addClassify", method = RequestMethod.POST)
    @ApiOperation(value = "添加分类", notes = "添加新分类")
    @Transactional
    public JSONObject addClassify(@RequestBody Classify classify){
        JSONObject re = new JSONObject();
        classify.setId(null);
        try {
            Classify classify1=new Classify();
            classify1.setClassname(classify.getClassname());
            List<Classify> classifies=classifyMapper.selectBySelective(classify1);
            if (classifies.size()>0){
                re.put("code", "0");
                re.put("message", "分类已经存在");
            }else {
                classify.setCreatetime(TimeUtil.getNewDateString());
                classifyMapper.insertSelective(classify);
                re.put("code", "1");
                re.put("message", "添加分类成功");
                re.put("result", JSONTool.ObjectToJSONObject(classify));
            }
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "添加分类失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "updaClassify", method = RequestMethod.PUT)
    @Transactional
    @ApiOperation(value = "修改分类", notes = "修改分类")
    public JSONObject updataClassify(@RequestBody Classify classify) {
        JSONObject re = new JSONObject();
        try {
            Classify classify1=new Classify();
            classify1.setClassname(classify.getClassname());
            List<Classify> classifies=classifyMapper.selectBySelective(classify1);
            if (classifies.size()>0){
                for (Classify c:classifies){
                    if (c.getId()!=classify.getId()){
                        re.put("code", "0");
                        re.put("message", "分类已经存在");
                        return re;
                    }
                }
            }
            classifyMapper.updateByPrimaryKeySelective(classify);
            re.put("code", "1");
            re.put("message", "修改分类成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "修改分类失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "deleteClassify/{classifyid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除分类", notes = "根据分类id删除分类")
    @Transactional
    public JSONObject deleteClassify(@PathVariable("classifyid") int classifyid) {
        JSONObject re = new JSONObject();
        try {
            Classify classify=classifyMapper.selectByPrimaryKey(classifyid);
            if (classify.getClassnum()>0){
                re.put("code", "0");
                re.put("message", "删除分类失败，分类下有产品");
                return re;
            }
            classifyMapper.deleteByPrimaryKey(classifyid);
            re.put("code", "1");
            re.put("message", "删除分类成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "删除分类失败,请联系后台");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "getClassifyById", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定分类基本信息", notes = "根据分类ID获取指定分类基本信息")
    public JSONObject getClassifyById(@RequestParam int classifyid) {
        JSONObject re = new JSONObject();
        Classify c = classifyMapper.selectByPrimaryKey(classifyid);
        if (c == null) {
            re.put("code", "0");
            re.put("message", "分类获取失败或分类为空！");
            re.put("user", new JSONObject());
        } else {
            re.put("code", "1");
            re.put("message", "分类信息查询成功！");
            re.put("result", JSONTool.ObjectToJSONObject(c));
        }
        return re;
    }

    @RequestMapping(value = "getClassifyByName", method = RequestMethod.GET)
    @ApiOperation(value = "判断分类是否存在", notes = "判断分类是否存在")
    public JSONObject getClassifyByName(@RequestParam String classname) {
        JSONObject re = new JSONObject();
        try{
            Classify data=new Classify();
            data.setClassname(classname);
            List<Classify> cs = classifyMapper.selectBySelective(data);
            if (cs == null||cs.size()==0) {
                re.put("code", "1");
                re.put("isHave", false);
                re.put("message", "分类不存在！");
            } else {
                re.put("code", "1");
                re.put("isHave", true);
                re.put("message", "分类已存在！");
            }
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "查询失败,请联系后台");
            re.put("Exception",e);
        }

        return re;
    }


    @ApiOperation(value = "获取所有分类", notes = "获取所有分类或分页获取所有分类")
    @RequestMapping(value = "getAllClassify", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ispage", value = "是否使用分页", required = true, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int", paramType = "query"),
    })
    public JSONObject getAllClassify(@RequestParam(name = "ispage") boolean ispage,
                               @RequestParam(name = "pageNum", required = false) Integer pageNum,
                               @RequestParam(name = "pageSize", required = false) Integer pageSize)
    {
        if (ispage) {
            PageHelper.startPage(pageNum, pageSize);
        }
        JSONObject k = new JSONObject();

        try {
            List<Classify> cs = classifyMapper.selectBySelective(null);
            k.put("code", 1);
            k.put("message", "查询成功");
            k.put("result", cs);
            if (ispage) {
                PageInfo<Classify> pageInfo = new PageInfo<Classify>(cs);
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
