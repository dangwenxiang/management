package com.example.manage.web;

import com.example.manage.auth.TokenManager;
import com.example.manage.entity.ExampleUser;
import com.example.manage.mapper.ExampleUserMapper;
import com.example.manage.model.UserModel;
import com.example.manage.utils.Constants;
import com.example.manage.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @program: pangolin
 * @author: dwx
 * @create: 2019-08-23 16:58
 **/
@RestController
@Api(value = "用户管理")
@RequestMapping("/userController")
public class UserController {

    @Resource
    private ExampleUserMapper exampleUserMapper;

    @Autowired
    private TokenManager tokenManager;

    @GetMapping(value = "/getUsers")
    @ApiOperation(value = "获取用户")
    public ResponseEntity<List<ExampleUser>> getUsers() {
        List<ExampleUser> userModels = exampleUserMapper.selectAll();
        return ResponseEntity.ok().body(userModels);
    }

    /**
     * 新增用户
     */
    @PostMapping(value = "/createUser")
    @ApiOperation(value = "新增用户")
    public ResponseEntity<Void> createUser(@RequestBody UserModel userModel,@RequestHeader(Constants.AUTHORIZATION) String authorization ) throws Exception {
        ExampleUser exampleUser = tokenManager.getAuthUser(authorization);
        ExampleUser user = new ExampleUser();
        //进行唯一性校验
        user.setUserName(userModel.getUserName());
        ExampleUser findUser = exampleUserMapper.selectOne(user);
        if (Objects.nonNull(findUser)) {
            throw new Exception("用户名重复，请重新输入");
        }
        //进行加密
        user.setUserPassword(MD5.MD5Encode(userModel.getUserPassword()));
        exampleUserMapper.insert(user);
        return ResponseEntity.ok().body(null);
    }

    /**
     * 更新用户
     */
    @PostMapping(value = "/updateUser")
    @ApiOperation(value = "更新用户")
    public ResponseEntity<Void> updateUser(@RequestBody UserModel userModel) throws Exception {
        ExampleUser user = exampleUserMapper.selectByPrimaryKey(userModel.getUserId());
        if (Objects.nonNull(user)) {
            if (Objects.equals(user.getUserName(), userModel.getUserName())) {
                throw new Exception("用户名重复，请重新输入");
            }
            BeanUtils.copyProperties(userModel, user);
            exampleUserMapper.updateByPrimaryKey(user);
        }
        return ResponseEntity.ok().body(null);
    }

    /**
     * 用户状态更改
     */
    @PostMapping(value = "/updateUserStatus")
    @ApiOperation(value = "用户状态更改")
    public ResponseEntity<Void> updateUserStatus(@RequestBody UserModel userModel) {
        ExampleUser user = exampleUserMapper.selectByPrimaryKey(userModel.getUserId());
        if (Objects.nonNull(user)) {
            user.setUserStatus(userModel.getUserStatus());
            exampleUserMapper.updateByPrimaryKey(user);
        }

        return ResponseEntity.ok().body(null);

    }


}
