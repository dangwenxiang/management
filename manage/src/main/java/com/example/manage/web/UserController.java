package com.example.manage.web;

import com.example.manage.entity.ExampleUser;
import com.example.manage.mapper.ExampleUserMapper;
import com.example.manage.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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

    @GetMapping(value = "getUsers")
    @ApiOperation(value = "获取用户")
    public ResponseEntity<List<ExampleUser>> getUsers(){
        List<ExampleUser> userModels = exampleUserMapper.selectAll();
        return ResponseEntity.ok().body(userModels);
    }

    /**
     * 新增用户
     */
    @PostMapping(value = "createUser")
    @ApiOperation(value = "新增用户")
    public ResponseEntity<Void> createUser(@RequestBody UserModel userModel){
        ExampleUser user = new ExampleUser();
        user.setUserName(userModel.getUserName());
        user.setUserPassword(userModel.getUserPassword());
        exampleUserMapper.insert(user);
        return ResponseEntity.ok().body(null);
    }

    /**
     * 更新用户
     */
    @PostMapping(value = "updateUser")
    @ApiOperation(value = "更新用户")
    public ResponseEntity<Void> updateUser(@RequestBody UserModel userModel ){
        ExampleUser user = exampleUserMapper.selectByPrimaryKey(userModel.getUserId());
        if (Objects.nonNull(user)){
            BeanUtils.copyProperties(userModel,user);
            exampleUserMapper.updateByPrimaryKey(user);
        }
        return ResponseEntity.ok().body(null);
    }


}
