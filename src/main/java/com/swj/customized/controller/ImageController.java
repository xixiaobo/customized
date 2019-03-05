package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swj.customized.bean.Image;
import com.swj.customized.mapper.ImageMapper;
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
@Api(value = "图片接口", description = "图片接口")
@RequestMapping("ImageManage")
@Slf4j
public class ImageController {


    @Resource
    private ImageMapper imageMapper;

    @RequestMapping(value = "addImage", method = RequestMethod.POST)
    @ApiOperation(value = "添加图片", notes = "添加新图片")
    @Transactional
    public JSONObject addImage(@RequestBody Image image){
        JSONObject re = new JSONObject();
        try {
            imageMapper.insertSelective(image);
            re.put("code", "1");
            re.put("Image", "添加图片成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("Image", "添加图片失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "updaImage", method = RequestMethod.PUT)
    @Transactional
    @ApiOperation(value = "修改图片", notes = "修改图片")
    public JSONObject updataImage(@RequestBody Image image) {
        JSONObject re = new JSONObject();
        try {
            imageMapper.updateByPrimaryKeySelective(image);
            re.put("code", "1");
            re.put("Image", "修改图片成功");
        }catch (Exception e){

            re.put("code", "0");
            re.put("Image", "修改图片失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "deleteImage/{imageid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除图片", notes = "根据图片id删除图片")
    @Transactional
    public JSONObject deleteImage(@PathVariable("imageid") int imageid) {
        JSONObject re = new JSONObject();
        try {
            imageMapper.deleteByPrimaryKey(imageid);
            re.put("code", "1");
            re.put("Image", "删除图片成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("Image", "删除图片失败,请联系后台");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "getImageById", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定图片基本信息", notes = "根据图片ID获取指定图片基本信息")
    public JSONObject getImageById(@RequestParam int imageid) {
        JSONObject re = new JSONObject();
        Image c = imageMapper.selectByPrimaryKey(imageid);
        if (c == null) {
            re.put("code", "0");
            re.put("Image", "图片获取失败或图片为空！");
            re.put("result", new JSONObject());
        } else {
            re.put("code", "1");
            re.put("Image", "图片信息查询成功！");
            re.put("result", c);
        }
        return re;
    }

    @ApiOperation(value = "获取所有图片", notes = "获取所有图片或分页获取所有图片")
    @RequestMapping(value = "getAllImage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ispage", value = "是否使用分页", required = true, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int", paramType = "query"),
    })
    public JSONObject getAllImage(@RequestParam(name = "ispage") boolean ispage,
                               @RequestParam(name = "pageNum", required = false) Integer pageNum,
                               @RequestParam(name = "pageSize", required = false) Integer pageSize)
    {
        if (ispage) {
            PageHelper.startPage(pageNum, pageSize);
        }
        JSONObject k = new JSONObject();

        try {
            List<Image> cs = imageMapper.selectBySelective(null);
            k.put("code", 1);
            k.put("Image", "查询成功");
            k.put("result", cs);
            if (ispage) {
                PageInfo<Image> pageInfo = new PageInfo<Image>(cs);
                k.put("result", pageInfo);
            }
        }catch (Exception e){
            k.put("code", 0);
            k.put("Image", "查询失败");
            k.put("result", new JSONObject());
            k.put("Exception",e);
        }
        return k;
    }


}
