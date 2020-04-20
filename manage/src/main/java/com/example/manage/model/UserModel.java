package com.example.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: pangolin
 * @author: dwx
 * @create: 2019-09-06 10:36
 **/
@Data
@ApiModel("用户实体类")
public class UserModel {
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户状态")
    private Integer userStatus;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;


}
