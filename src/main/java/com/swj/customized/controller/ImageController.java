package com.swj.customized.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swj.customized.bean.Image;
import com.swj.customized.mapper.ImageMapper;
import com.swj.customized.tool.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @RequestMapping(value = "updataImagesByProductid", method = RequestMethod.PUT)
    @ApiOperation(value = "修改产品的图片", notes = "修改产品的图片")
    @Transactional
    public JSONObject updataImagesByProductid(@RequestBody JSONObject request){
        JSONObject re = new JSONObject();
        try {
            String productid=request.getString("productid");
            if(productid==null){
                re.put("code", "0");
                re.put("Image", "产品id为空");
            }else {
                String creattime= TimeUtil.getNewDateString();
                JSONArray deleteId=request.getJSONArray("deleteImagesId");
                JSONArray addImages=request.getJSONArray("addImages");
                List<Integer> ids=new ArrayList<>();
                for (int i=0;i<deleteId.size();i++){
                    ids.add(deleteId.getInteger(i));
                }
                imageMapper.deleteList(ids);
                List<Image> newimages=new ArrayList<>();
                for (int i=0;i<addImages.size();i++) {
                    Image image=new Image();
                    image.setImagebase64(addImages.getString(i));
                    image.setProductid(productid);
                    image.setCreatetime(creattime);
                    newimages.add(image);
                }
                imageMapper.insertList(newimages);
                re.put("code", "1");
                re.put("Image", "产品图片修改成功");
            }
        }catch (Exception e){
            re.put("code", "0");
            re.put("Image", "添加图片失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "addImages/{productid}", method = RequestMethod.POST)
    @ApiOperation(value = "批量添加图片", notes = "批量添加新图片")
    @Transactional
    public JSONObject addImage(@PathVariable("productid")String productid, @RequestBody List<Image> images){
        JSONObject re = new JSONObject();
        try {
            List<Image> newimages=new ArrayList<>();
            for (Image image:images) {
                image.setProductid(productid);
                image.setCreatetime(DateTime.now().toString("yyyyMMddHHmmss"));
                newimages.add(image);
            }
            imageMapper.insertList(newimages);
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

    @ApiOperation(value = "获取指定图片", notes = "根据条件获取指定图片")
    @RequestMapping(value = "getAllImage", method = RequestMethod.POST)
    public JSONObject getAllImage(@RequestBody Image image)
    {
        JSONObject k = new JSONObject();

        try {
            List<Image> cs = imageMapper.selectBySelective(image);
            k.put("code", 1);
            k.put("Image", "查询成功");
            k.put("result", cs);
        }catch (Exception e){
            k.put("code", 0);
            k.put("Image", "查询失败");
            k.put("result", new JSONObject());
            k.put("Exception",e);
        }
        return k;
    }


}
