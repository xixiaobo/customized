package com.swj.customized.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "产品实体类",description = "产品实体类")
public class Product {
    @ApiModelProperty(value="产品id",name="id")
    private Integer id;

    @ApiModelProperty(value="产品名称",name="productname")
    private String productname;

    @ApiModelProperty(value="分类id",name="classid")
    private Integer classid;

    @ApiModelProperty(value="用户id",name="userid")
    private String userid;

    @ApiModelProperty(value="产品介绍",name="content")
    private String content;

    @ApiModelProperty(value="产品图片id数组（逗号分隔）",name="imageids")
    private String imageids;

    @ApiModelProperty(value="产品创建时间",name="createtime")
    private String createtime;

}