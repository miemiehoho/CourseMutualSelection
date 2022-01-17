package edu.gdut.imis.courseMutualSelection.controller;

import edu.gdut.imis.courseMutualSelection.service.AdminService;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.TeacherParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miemiehoho
 * @date 2022/1/17 5:31
 */
@Api(value = "教师管理", tags = "教师管理")
@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private AdminService adminService;

    @ApiOperation("查询所有教师信息")
    @PostMapping
    public Result listTeacher(@RequestBody PageParams pageParams) {
        return adminService.listTeacher(pageParams);
    }

    @ApiOperation("新建教师信息")
    @PostMapping("createTeacher")
    public Result createTeacher(@RequestBody TeacherParam teacherParam) {
        return adminService.createTeacher(teacherParam);
    }
}
