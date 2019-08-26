package com.example.manage.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: pangolin
 * @author: dwx
 * @create: 2019-08-23 16:58
 **/
@RestController
@Api(value = "用户管理")
@RequestMapping("/userController")
public class UserController {

    @GetMapping(value = "getUsers")
    @ApiOperation(value = "获取用户")
    public ResponseEntity String(){
        return null;
    }

}
