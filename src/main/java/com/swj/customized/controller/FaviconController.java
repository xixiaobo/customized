package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.swj.customized.tool.FileUtil;
import com.swj.customized.tool.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by xxb on 2018/10/23.
 */
@RestController
@ResponseBody
@Slf4j
@Api(value = "favicon操作接口", description ="favicon操作接口相关类")
public class FaviconController {


    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ApiOperation(value = "base64数据上传图片", notes = "base64数据上传图片，{imageName，base64}")
    public JSONObject uploadImage(@RequestBody JSONObject data) {
        JSONObject result =new JSONObject();
        String imageName=data.getString("imageName");
        String base64=data.getString("base64");
        String add = GenerateImage(base64,imageName);
        if(add!=null){
            result.put("code", "1");
            result.put("message", "上传成功");
            result.put("filepath", add);
        }else {
            result.put("code", "0");
            result.put("message", "上传失败");
        }

        return result;
    }

    //base64字符串转化成图片
    private static String GenerateImage(String imgStr,String filename)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            String newimgStr=imgStr.split("base64,")[1];
            //Base64解码
            byte[] b = decoder.decodeBuffer(newimgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0){
                    //调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = Utils.getpath()+"/img/"+filename;//新生成的图片
            FileUtil.mkdirFiles(imgFilePath);
            File file = new File(imgFilePath);
            if(file.exists()){
                file.delete();
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return "/image/"+filename;
        }
        catch (Exception e)
        {
            return null;
        }
    }

}