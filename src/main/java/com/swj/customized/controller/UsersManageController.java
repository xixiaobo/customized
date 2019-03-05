package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swj.customized.bean.Classify;
import com.swj.customized.bean.Users;
import com.swj.customized.mapper.UsersMapper;
import com.swj.customized.tool.JSONTool;
import com.swj.customized.tool.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

/**
 * Created by xxb on 2018/10/22.
 */
@RestController
@ResponseBody
@Api(value = "用户接口", description = "用户接口")
@RequestMapping("userManage")
@Slf4j
public class UsersManageController {


    @Resource
    private UsersMapper usersMapper;



    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户", notes = "添加新用户")
    @Transactional
    public JSONObject addUser(@RequestBody Users user, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JSONObject re = new JSONObject();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String password = passwordEncoder.encode(user.getPassword());
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
                re.put("message", "添加成功");
            } else {
                re.put("code", "1");
                re.put("isHave", true);
                re.put("message", "用户已存在！");
            }

        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "添加失败");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "updaUser", method = RequestMethod.PUT)
    @Transactional
    @ApiOperation(value = "修改用户基本信息", notes = "修改用户基本信息")
    public JSONObject updataUser(@RequestBody Users users, HttpServletRequest request) {
        JSONObject re = new JSONObject();
        try {
            usersMapper.updateByPrimaryKeySelective(users);
            re.put("code", "1");
            re.put("message", "修改成功");
        }catch (Exception e){

            re.put("code", "0");
            re.put("message", "修改失败");
            re.put("Exception",e);
        }

        return re;
    }

    @RequestMapping(value = "deleteUser/{userid}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @Transactional
    public JSONObject deleteUser(@PathVariable("userid") String userid, HttpServletRequest request) {
        JSONObject re = new JSONObject();
        try {
            usersMapper.deleteByPrimaryKey(userid);
            re.put("code", "1");
            re.put("message", "删除成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "删除失败,请联系后台");
            re.put("Exception",e);
        }
        return re;
    }

    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定用户基本信息", notes = "根据用户ID获取指定用户基本信息")
    public JSONObject getUserById(@RequestParam String userid, HttpServletRequest request) {
        JSONObject re = new JSONObject();
        Users u = usersMapper.selectByPrimaryKey(userid);
        if (u == null) {
            re.put("code", "0");
            re.put("message", "用户获取失败或用户为空！");
            re.put("result", new JSONObject());
        } else {
            re.put("code", "1");
            re.put("message", "用户信息查询成功！");
            re.put("result", u);
        }
        return re;
    }

    @RequestMapping(value = "getUserByName", method = RequestMethod.GET)
    @ApiOperation(value = "判断用户是否存在", notes = "判断用户是否存在")
    public JSONObject getUserByName(@RequestParam String userName) {
        JSONObject re = new JSONObject();
        try{
            Users data=new Users();
            data.setUsername(userName);
            List<Users> us = usersMapper.selectUserBySelective(data);
            if (us == null||us.size()==0) {
                re.put("code", "1");
                re.put("isHave", false);
                re.put("message", "用户不存在！");
            } else {
                re.put("code", "1");
                re.put("isHave", true);
                re.put("message", "用户已存在！");
            }
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "查询失败,请联系后台");
            re.put("Exception",e);
        }

        return re;
    }

    @RequestMapping(value = "updataUserPassword", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码(传入参数：id，password)")
    public JSONObject updataUserPassword(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        JSONObject re = new JSONObject();
        try {
            Users u = new Users();
            u.setId(jsonObject.getString("id"));
            u.setPassword(passwordEncoder.encode(jsonObject.getString("Password")));
            usersMapper.updateByPrimaryKeySelective(u);
            re.put("code", "1");
            re.put("message", "密码修改成功");
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "密码修改失败");
            re.put("Exception",e);
        }

        return re;
    }

    @RequestMapping(value = "decidePasswprd", method = RequestMethod.PUT)
    @ApiOperation(value = "判断密码是否正确", notes = "判断密码是否正确(传入参数：id，password)")
    public JSONObject decidePasswprd(@RequestBody JSONObject jsonObject) {
        JSONObject re = new JSONObject();
        try{
            Users u = usersMapper.selectByPrimaryKey(jsonObject.getString("id"));
            if (passwordEncoder.matches(jsonObject.getString("password"),u.getPassword())) {
                re.put("code", "1");
                re.put("isRight", true);
            } else {
                re.put("code", "1");
                re.put("isRight", false);
            }
        }catch (Exception e){
            re.put("code", "0");
            re.put("message", "查询失败,请检查参数或联系后台");
            re.put("Exception",e);
        }

        return re;
    }


    @ApiOperation(value = "获取所有用户", notes = "获取所有用户或分页获取所有用户")
    @RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ispage", value = "是否使用分页", required = true, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "查询页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int", paramType = "query"),
    })
    public JSONObject getAllUsers(@RequestParam(name = "ispage") boolean ispage,
                               @RequestParam(name = "pageNum", required = false) Integer pageNum,
                               @RequestParam(name = "pageSize", required = false) Integer pageSize)
    {
        if (ispage) {
            PageHelper.startPage(pageNum, pageSize);
        }
        JSONObject k = new JSONObject();

        try {
            List<Users> u = usersMapper.selectUserBySelective(null);
            k.put("code", 1);
            k.put("message", "查询成功");
            k.put("result", u);
            if (ispage) {
                PageInfo<Users> pageInfo = new PageInfo<Users>(u);
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
