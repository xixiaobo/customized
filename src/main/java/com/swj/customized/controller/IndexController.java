package com.swj.customized.controller;

import com.alibaba.fastjson.JSONObject;
import com.swj.customized.bean.Users;
import com.swj.customized.mapper.UsersMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by xxb on 2018/10/23.
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String index() {
        return "/swagger-ui.html";
    }

}