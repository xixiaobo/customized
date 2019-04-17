package com.swj.customized.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "任务实体类",description = "任务实体类")
public class TaskDto {
    @ApiModelProperty(value="任务id",name="id")
    private String id;
    @ApiModelProperty(value="任务标题",name="title")
    private String title;
    @ApiModelProperty(value="任务发布用户id",name="userid")
    private String userid;
    @ApiModelProperty(value="任务发布用户名称",name="username")
    private String username;
    @ApiModelProperty(value="任务发布用户头像",name="userimg")
    private String userimg;
    @ApiModelProperty(value="分类id",name="classid")
    private Integer classid;
    @ApiModelProperty(value="分类名称",name="classname")
    private String classname;
    @ApiModelProperty(value="任务状态（0：发布中；1：已接单；2：已关闭）",name="status")
    private String status;
    @ApiModelProperty(value="任务内容",name="content")
    private String content;
    @ApiModelProperty(value="最低薪酬",name="minsalary")
    private String minsalary;
    @ApiModelProperty(value="最低薪酬",name="maxsalary")
    private String maxsalary;
    @ApiModelProperty(value="应征人数",name="applicantsnum")
    private Integer applicantsnum;
    @ApiModelProperty(value="任务截止时间",name="taskdeadline")
    private String taskdeadline;
    @ApiModelProperty(value="任务创建时间",name="createtime")
    private String createtime;
    @ApiModelProperty(value="任务结束时间",name="createtime")
    private String overtime;
    @ApiModelProperty(value="是否删除",name="isdelete")
    private String isdelete;


}