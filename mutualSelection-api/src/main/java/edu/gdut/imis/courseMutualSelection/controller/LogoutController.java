package edu.gdut.imis.courseMutualSelection.controller;

import edu.gdut.imis.courseMutualSelection.service.LoginService;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author miemiehoho
 * @date 2022/1/16 17:44
 */

@Api(value = "注销登录", tags = "注销登录")
@RestController
@RequestMapping("logout")
public class LogoutController {
    @Autowired
    private LoginService loginService;

    @ApiOperation("注销")
    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }
}
