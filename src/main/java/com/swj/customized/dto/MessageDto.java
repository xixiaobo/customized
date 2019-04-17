package com.swj.customized.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "留言实体类",description = "留言实体类")
public class MessageDto {
    @ApiModelProperty(value="留言id",name="id")
    private Integer id;

    @ApiModelProperty(value="产品id",name="productid")
    private String productid;

    @ApiModelProperty(value="留言用户id",name="userid")
    private String userid;

    @ApiModelProperty(value="留言用户头像",name="userimg")
    private String userimg;

    @ApiModelProperty(value="留言用户名称",name="username")
    private String username;

    @ApiModelProperty(value="留言内容",name="content")
    private String content;

    @ApiModelProperty(value="留言创建时间",name="createtime")
    private String createtime;

}