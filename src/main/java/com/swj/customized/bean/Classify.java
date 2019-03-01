package com.swj.customized.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分类实体类",description = "分类实体类")
public class Classify {
    @ApiModelProperty(value="分类id",name="id")
    private Integer id;

    @ApiModelProperty(value="分类名称",name="classname")
    private String classname;

    @ApiModelProperty(value="分类下产品数量",name="classnum")
    private Integer classnum;

    @ApiModelProperty(value="分类创建时间",name="createtime")
    private String createtime;

}