package edu.gdut.imis.courseMutualSelection.controller;

import java.util.Arrays;
import java.util.Map;

import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.TopicInformationParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.dao.pojo.TopicInformation;
import edu.gdut.imis.courseMutualSelection.service.TopicInformationService;


/**
 * 选题表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
@Api(value = "选题", tags = "选题")
@RestController
@RequestMapping("class")
public class TopicInformationController {
    @Autowired
    private TopicInformationService topicInformationService;

    @ApiOperation("创建选题")
    @PostMapping
    public Result create(@RequestBody TopicInformationParam topicInformationParam) {
        return topicInformationService.create(topicInformationParam);
    }

    @ApiOperation("选题列表")
    @PostMapping("list")
    public Result listTopic(@RequestBody PageParams pageParams) {
        return topicInformationService.listTopic(pageParams);
    }
}
