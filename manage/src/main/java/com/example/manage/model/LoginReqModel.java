package com.example.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Date 2020/4/14 13:51
 * @Created by dwx
 */
@Data
@ApiModel(value = "用户登录请求参数")
public class LoginReqModel {
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;
}
