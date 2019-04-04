package com.swj.customized.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "任务实体类",description = "任务实体类")
public class Task {
    @ApiModelProperty(value="任务id",name="id")
    private String id;

    @ApiModelProperty(value="任务标题",name="title")
    private String title;

    @ApiModelProperty(value="任务发布用户id",name="userid")
    private String userid;

    @ApiModelProperty(value="任务状态",name="status")
    private Integer status;

    @ApiModelProperty(value="任务创建时间",name="createtime")
    private String createtime;

    @ApiModelProperty(value="任务内容",name="content")
    private String content;

}