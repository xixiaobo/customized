package com.swj.customized.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "图片实体类",description = "图片实体类")
public class Image {
    @ApiModelProperty(value="图片id",name="id")
    private Integer id;

    @ApiModelProperty(value="产品id",name="productid")
    private String productid;

    @ApiModelProperty(value="图片base64数据",name="createtime")
    private String createtime;

    @ApiModelProperty(value="图片创建时间",name="imagebase64")
    private String imagebase64;


}