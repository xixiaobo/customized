package com.swj.customized.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.swj.customized.bean.Users;
import com.swj.customized.mapper.UsersMapper;
import com.swj.customized.tool.FileUtil;
import com.swj.customized.tool.JSONTool;
import com.swj.customized.tool.TimeUtil;
import com.swj.customized.tool.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

/**
 * Created by xxb on 2018/10/23.
 */
@RestController
@ResponseBody
@Slf4j
@Api(value = "JSON文件操作接口", description ="JSON文件操作接口相关类")
public class JSONController {

    @RequestMapping(value = "/readJSON/{jsonFile}", method = RequestMethod.GET)
    @ApiOperation(value = "读取JSON文件", notes = "读取JSON文件")
    public JSONObject readJSON(@PathVariable String jsonFile) {

        JSONObject result =new JSONObject();
        List<String> list=FileUtil.readFile(Utils.getpath()+"/webjson/"+jsonFile);
        if(list.size()!=0){
            StringBuilder stringBuilder = new StringBuilder();
            for (String d:list) {
                stringBuilder.append(d);
            }
            try {
                result.put("data", JSON.parseObject(stringBuilder.toString()));
            }catch (Exception e){
                result.put("data", new JSONObject());
            }
        }else {
            result.put("data", new JSONObject());
        }

        result.put("code", "1");
        result.put("message", "数据获取成功");
        return result;
    }


    @RequestMapping(value = "/writeJSON/{jsonFile}", method = RequestMethod.POST)
    @ApiOperation(value = "写入json文件", notes = "写入json文件")
    public JSONObject writeJSON(@PathVariable String jsonFile,@RequestBody JSONObject data) {
        JSONObject result =new JSONObject();
        FileUtil.writeFile(Utils.getpath()+"/webjson/"+jsonFile,data.toJSONString(),false);
        result.put("code", "1");
        result.put("message", "写入成功");
        return result;
    }

}