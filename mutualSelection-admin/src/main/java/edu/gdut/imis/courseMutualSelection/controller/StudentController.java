package edu.gdut.imis.courseMutualSelection.controller;

import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.service.UserService;

import java.util.List;


/**
 * 学生信息表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
@Api(value = "学生管理", tags = "学生管理")
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private UserService userService;

    @ApiOperation("查询所有学生信息")
    @PostMapping
    public Result listStudent(@RequestBody PageParams pageParams) {
        return userService.listStudent(pageParams);
    }

    @ApiOperation("新增学生")
    @PostMapping("createStudent")
    public Result createStudent(@RequestBody UserParam userParam) {
        return userService.createStudent(userParam);
    }

    @ApiOperation("批量新增学生")
    @PostMapping("createStudentList")
    public Result createStudentList(@RequestBody List<UserParam> userParamList) {
        return userService.createStudentList(userParamList);
    }

}
