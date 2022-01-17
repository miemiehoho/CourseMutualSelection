package edu.gdut.imis.courseMutualSelection.controller;

import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.UserTopicParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.service.UserTopicService;


/**
 * 学生选题表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
@Api(value = "学生选题", tags = "学生选题")
@RestController
@RequestMapping("picker")
public class UserTopicController {

    @Autowired
    private UserTopicService userTopicService;

    @ApiOperation("查询选题情况")
    @PostMapping
    public Result list(@RequestBody PageParams pageParams) {
        return userTopicService.list(pageParams);
    }

    @ApiOperation("批阅选题")
    @PostMapping("commit")
    public Result commit(@RequestBody UserTopicParam userTopicParam) {
        return userTopicService.commit(userTopicParam);
    }

}
