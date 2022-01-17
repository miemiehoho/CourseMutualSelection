package edu.gdut.imis.courseMutualSelection.controller;

import java.util.Arrays;
import java.util.Map;

import edu.gdut.imis.courseMutualSelection.vo.params.AdminParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.dao.pojo.Admin;
import edu.gdut.imis.courseMutualSelection.service.AdminService;


/**
 * 管理员信息表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
@Api(value = "老师", tags = "老师")
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ApiOperation("获取老师信息")
    @PostMapping("searchSelf")
    public Result currentAdmin(@RequestHeader("Authorization") String token) {
        return adminService.findAdminByToken(token);
    }

    @ApiOperation("修改老师信息")
    @PostMapping("updateSelf")
    public Result updateAdmin(@RequestHeader("Authorization") String token, @RequestBody AdminParam adminParam) {
        return adminService.updateAdmin(token, adminParam);
    }

}
