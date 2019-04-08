package com.swj.customized.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : xixiaobo@gmail.com
 * @date : 2019/4/5 22:30
 * @Description:
 */
@Data
@ApiModel(value = "产品详情实体类",description = "产品详情实体类")
public class ProductDto {
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

    @ApiModelProperty(value="产品简介",name="productabstract")
    private String productabstract;

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

    @ApiModelProperty(value="排序字段",name="sortfield")
    private String sortfield;

    @ApiModelProperty(value="排序类型",name="sortingdirection")
    private String sortingdirection;
}
