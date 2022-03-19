package edu.gdut.imis.courseMutualSelection.controller;

import java.util.Arrays;
import java.util.Map;

import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.dao.pojo.Semester;
import edu.gdut.imis.courseMutualSelection.service.SemesterService;


/**
 * 学期表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
@RestController
@RequestMapping("semester")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    /**
     * 列表
     */
    @PostMapping
    public Result list(@RequestBody PageParams pageParams) {
        return semesterService.list(pageParams);
    }


}
