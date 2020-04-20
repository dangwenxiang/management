package com.example.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Date 2020/4/16 11:04
 * @Created by dwx
 */
@Data
@ApiModel("密码修改实体")
public class UpdatePasswordModel {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户旧密码")
    private String userOldPassword;
    @ApiModelProperty(value = "用户新密码")
    private String userNewPassword;

}
