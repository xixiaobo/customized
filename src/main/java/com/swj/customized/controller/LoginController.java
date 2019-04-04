package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.swj.customized.bean.Users;
import com.swj.customized.mapper.UsersMapper;
import com.swj.customized.tool.JSONTool;
import com.swj.customized.tool.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
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
@Api(value = "登录注册接口", description ="登录注册接口相关类")
public class LoginController {


    @Resource
    private UsersMapper usersMapper;

    /**
     * 登陆
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", notes = "登录用户接口,jsonObject={username,password}")
    public JSONObject login(@RequestBody JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request) {
        log.info("登录信息：" + jsonObject);
        JSONObject result = new JSONObject();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        try {
            Users data=new Users();
            data.setUsername(username);
            List<Users> us = usersMapper.selectUserBySelective(data);
            if(us==null||us.size()==0){
                result.put("code", "0");
                result.put("message", "用户名或密码错误");
            }else {
                if(new BCryptPasswordEncoder().matches(password,us.get(0).getPassword())){
                    response.addCookie(new Cookie("userid",us.get(0).getId()));
                    response.addCookie(new Cookie("role",us.get(0).getRole()));
                    result.put("code", "1");
                    result.put("message", "登录成功");
                    result.put("result", JSONTool.ObjectToJSONObject(us.get(0)));
                }else {
                    result.put("code", "0");
                    result.put("message", "用户名或密码错误");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", "0");
            result.put("message", "服务器出错登录失败");
            result.put("Exception",e);
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "用户登录", notes = "登录用户接口,jsonObject={username,password}")
    public JSONObject logout(HttpServletResponse response, HttpServletRequest request) {

        JSONObject result =new JSONObject();
        response.addCookie(new Cookie("userid",null));
        response.addCookie(new Cookie("role",null));
        result.put("code", "1");
        result.put("message", "登出成功");
        return result;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ApiOperation(value = "注册用户", notes = "注册新用户")
    public JSONObject register(@RequestBody Users user, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JSONObject re = new JSONObject();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setId(uuid);
        user.setPassword(password);
        user.setCreatetime(TimeUtil.getNewDateString());
        try {
            Users data=new Users();
            data.setUsername(user.getUsername());
            List<Users> us = usersMapper.selectUserBySelective(data);
            if (us == null||us.size()==0) {
                usersMapper.insertSelective(user);
                re.put("code", "1");
                re.put("message", "注册成功");
            } else {
                re.put("code", "1");
                re.put("isHave", true);
                re.put("message", "用户已存在！");
            }
        } catch (Exception e) {
            re.put("code", "0");
            re.put("message", "注册失败");
            re.put("Exception", e);
        }
        return re;
    }

}