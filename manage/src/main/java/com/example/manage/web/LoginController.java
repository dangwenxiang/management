package com.example.manage.web;

import com.example.manage.auth.TokenManager;
import com.example.manage.elastic.ExampleUser;
import com.example.manage.mapper.ExampleUserMapper;
import com.example.manage.model.LoginReqModel;
import com.example.manage.model.UpdatePasswordModel;
import com.example.manage.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @program: pangolin
 * @author: dwx
 * @create: 2019-09-06 11:21
 **/
@RestController
@Api(value = "用户登录")
@RequestMapping("/loginController")
public class LoginController {

    @Resource
    private ExampleUserMapper exampleUserMapper;

    @Autowired
    private TokenManager tokenManager;
    /**
     * 1.密码
     * 2.验证码
     * 3.输入三次报错（待定）
     * 4.三个月内必须修改密码
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public ResponseEntity<String> login(@RequestBody LoginReqModel loginReqModel) throws Exception {
        ExampleUser user = new ExampleUser();
        user.setUserName(loginReqModel.getUserName());
        ExampleUser findUser = exampleUserMapper.selectOne(user);
        if (Objects.isNull(findUser)){
            throw new Exception("此账户不存在，请核对后重新登录");
        }
        if (!Objects.equals(MD5.MD5Encode(loginReqModel.getUserPassword()),findUser.getUserPassword())){
            throw new Exception("用户登录密码错误，请核对后再重新登录");
        }
        //生成token，保存至redis
        String token = tokenManager.createToken(user);
        //TODO 资源获取
        return ResponseEntity.ok().body(token);
    }

    /**
     * 修改密码
     */

    @PostMapping(value = "/updatePassword")
    @ApiOperation(value = "修改密码")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordModel passwordModel) throws Exception {
        //新旧密码比对
        ExampleUser user = new ExampleUser();
        user.setUserName(passwordModel.getUserName());
        ExampleUser findUser = exampleUserMapper.selectOne(user);
        if (Objects.equals(MD5.MD5Encode(passwordModel.getUserNewPassword()),findUser.getUserPassword())){
            throw new Exception("俩次密码输入相同，请重新输入");
        }
        //满足
        user.setUserPassword(MD5.MD5Encode(passwordModel.getUserNewPassword()));
        exampleUserMapper.updateByPrimaryKey(user);
        return ResponseEntity.ok().body(null);
    }

}