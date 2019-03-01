package com.swj.customized.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户实体类",description = "用户实体类")
public class Users {

    @ApiModelProperty(value="用户id",name="id")
    private String id;

    @ApiModelProperty(value="用户登录名",name="username")
    private String username;

    @ApiModelProperty(value="用户登录密码",name="password")
    private String password;

    @ApiModelProperty(value="用户真实姓名",name="realname")
    private String realname;

    @ApiModelProperty(value="手机号",name="phone")
    private String phone;

    @ApiModelProperty(value="邮箱",name="email")
    private String email;

    @ApiModelProperty(value="角色",name="role")
    private String role;

    @ApiModelProperty(value="用户创建时间",name="createtime")
    private String createtime;

}