package com.swj.customized.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "订单实体类",description = "订单实体类")
public class OrderDto {
    @ApiModelProperty(value="订单id",name="id")
    private String id;

    @ApiModelProperty(value="任务id",name="taskid")
    private String taskid;

    @ApiModelProperty(value="接单用户id",name="orderuserid")
    private String orderuserid;
    @ApiModelProperty(value="接单用户名称",name="username")
    private String username;
    @ApiModelProperty(value="接单用户头像",name="userimg")
    private String userimg;
    @ApiModelProperty(value="订单状态（0：待确认；1：确认；-2：拒绝；-1：失效）",name="status")
    private String status;

    @ApiModelProperty(value="接单说明",name="receipt_explain")
    private String receipt_explain;

    @ApiModelProperty(value="订单创建时间",name="createtime")
    private String createtime;

    @ApiModelProperty(value="订单结束时间",name="overtime")
    private String overtime;

    @ApiModelProperty(value="是否删除",name="isdelete")
    private String isdelete;
}