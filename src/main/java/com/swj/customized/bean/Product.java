package com.swj.customized.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "产品实体类",description = "产品实体类")
public class Product {
    @ApiModelProperty(value="产品id",name="id")
    private String id;

    @ApiModelProperty(value="产品名称",name="productname")
    private String productname;

    @ApiModelProperty(value="分类id",name="classid")
    private Integer classid;
    @ApiModelProperty(value="分类名称",name="classid")
    private String classname;

    @ApiModelProperty(value="用户id",name="userid")
    private String userid;
    @ApiModelProperty(value="用户名称",name="userid")
    private String username;

    @ApiModelProperty(value="产品介绍",name="content")
    private String content;

    @ApiModelProperty(value="产品默认图片",name="imageids")
    private String defaultImage;

    @ApiModelProperty(value="产品评分",name="productscore")
    private Double productscore;

    @ApiModelProperty(value="产品评分人数",name="productscore")
    private Integer productscorenum;

    @ApiModelProperty(value="产品创建时间",name="createtime")
    private String createtime;

    private String sortfield;

    private String sortingdirection;

}