package edu.gdut.imis.courseMutualSelection.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.dao.pojo.UserTopic;
import edu.gdut.imis.courseMutualSelection.service.UserTopicService;


/**
 * 学生选题表
 *
 * @author miemiehoho
 * @date 2022-01-16 17:36:38
 */
@Api(value = "学生选题", tags = "学生选题")
@RestController
@RequestMapping("usertopic")
public class UserTopicController {
    @Autowired
    private UserTopicService userTopicService;

    @ApiOperation("选题")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long topicId) {
        return userTopicService.select(topicId);
    }

    @ApiOperation("查看已选选题")
    @GetMapping("getSelect")
    public Result getSelect() {
        return userTopicService.getSelect();
    }

    @ApiOperation("选题状态")
    @GetMapping("selectStatus/{id}")
    public Result selectStatus(@PathVariable("id") Long topicId) {
        return userTopicService.selectStatus(topicId);
    }


    @ApiOperation("上传开题报告")
    @PostMapping("uploadReport")
    public Result uploadReport(@RequestParam String reportURL, @RequestParam Long userTopicId) {
        return userTopicService.uploadReport(userTopicId, reportURL);
    }
}
