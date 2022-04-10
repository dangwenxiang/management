package com.example.manage.elastic;

import com.example.manage.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Table;

/**
 * @program: pangolin
 * @author: dwx
 * @create: 2019-09-06 10:07
 **/
@Data
@ApiModel("用户")
@Document(indexName = "user")
public class ExampleUser extends BaseEntity {
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户状态", notes = "0-正常，1-暂停使用")
    private Integer userStatus;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;





}
