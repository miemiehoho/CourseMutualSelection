package edu.gdut.imis.courseMutualSelection.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.service.UserCourseService;


/**
 * 学生选课表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
@Api(value = "已经选课", tags = "已选选课")
@RestController
@RequestMapping("usercourse")
public class UserCourseController {
    @Autowired
    private UserCourseService userCourseService;

    @ApiOperation("查询选课状态")
    @GetMapping("detail/{selectCourseId}")
    public Result selectUserCourse(@PathVariable String selectCourseId) {
        return userCourseService.selectUserCourse(selectCourseId);
    }

    @ApiOperation("已选选课列表")
    @GetMapping("list")
    public Result list() {
        return userCourseService.userCourselist();
    }

}
