package edu.gdut.imis.courseMutualSelection.controller;

import edu.gdut.imis.courseMutualSelection.service.LoginService;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Random;
import java.util.UUID;

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

    @ApiOperation("测试")
    @GetMapping
    public Result register() throws Exception {
        OutputStream outputStream = new FileOutputStream(new File("d://account.txt"));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        for (int i = 0; i < 5000; i++) {
            String account = String.valueOf(UUID.randomUUID());
            String password = String.valueOf(UUID.randomUUID());
            LoginParam loginParam = new LoginParam();
            loginParam.setAccount(account);
            loginParam.setPassword(password);
            Result result = loginService.register(loginParam);
            writer.write((String) result.getData());
            writer.newLine();
        }
        writer.close();
        outputStream.close();
        outputStreamWriter.close();
        return Result.success(null);
    }
}
