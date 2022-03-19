package edu.gdut.imis.courseMutualSelection.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.dao.pojo.CourseImformation;
import edu.gdut.imis.courseMutualSelection.service.CourseImformationService;


/**
 * 课程表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
@RestController
@RequestMapping("courseMutualSelection/courseimformation")
public class CourseImformationController {
    @Autowired
    private CourseImformationService courseImformationService;



}
