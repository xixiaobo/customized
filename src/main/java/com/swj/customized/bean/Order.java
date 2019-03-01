package com.swj.customized.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "订单实体类",description = "订单实体类")
public class Order {
    @ApiModelProperty(value="订单id",name="id")
    private Integer id;

    @ApiModelProperty(value="任务id",name="taskid")
    private Integer taskid;

    @ApiModelProperty(value="接单用户id",name="orderuserid")
    private String orderuserid;

    @ApiModelProperty(value="订单状态",name="status")
    private Integer status;

    @ApiModelProperty(value="订单创建时间",name="createtime")
    private String createtime;

    @ApiModelProperty(value="订单完成时间",name="overtime")
    private String overtime;
}