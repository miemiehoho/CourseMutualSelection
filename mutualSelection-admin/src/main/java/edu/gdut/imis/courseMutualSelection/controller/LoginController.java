package edu.gdut.imis.courseMutualSelection.controller;

import edu.gdut.imis.courseMutualSelection.service.LoginService;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miemiehoho
 * @date 2021/11/22 23:40
 */
@Api(value = "登录", tags = "登录")
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("登录")
    @PostMapping
    public Result login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }
}
