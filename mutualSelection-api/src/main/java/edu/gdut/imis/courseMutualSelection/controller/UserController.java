package edu.gdut.imis.courseMutualSelection.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.service.UserService;


/**
 * 学生信息表
 *
 * @author miemiehoho
 * @date 2021-11-22 23:31:32
 */
@Api(value = "用户", tags = "用户")
@RestController
@RequestMapping("courseMutualSelection/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("获取用户信息")
    @PostMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token) {
        return userService.findUserByToken(token);
    }

}
