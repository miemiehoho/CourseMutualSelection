package edu.gdut.imis.courseMutualSelection.controller;


import com.wf.captcha.ArithmeticCaptcha;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.utils.UserThreadLocal;
import edu.gdut.imis.courseMutualSelection.validation.AccessLimit;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.service.CourseSelectService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 可选课程表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
@Slf4j
@Api(value = "可选课程表", tags = "可选课程表")
@RestController
@RequestMapping("courseselect")
public class CourseSelectController {
    @Autowired
    private CourseSelectService courseSelectService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 列表
     */
    @ApiOperation("可选课程表列表")
    @PostMapping
    @AccessLimit(second = 5, maxCount = 5)
    public Result list(@RequestBody PageParams pageParams) {
        return courseSelectService.list(pageParams);
    }

    @ApiOperation("获取课程详细信息")
    @GetMapping("{id}")
    public Result findById(@PathVariable("id") Long id) {
        return courseSelectService.findById(id);
    }

    @ApiOperation("抢课")
    @PostMapping("{path}/doSeckill")
    public Result doSeckill(@PathVariable String path, Long courseSelectId) {
        if (!courseSelectService.checkPath(UserThreadLocal.get().getId(), path, courseSelectId)) {
            return Result.fail(ErrorStatus.ILLEGAL_REQUEST.getCode(), ErrorStatus.ILLEGAL_REQUEST.getMsg());
        }
        return courseSelectService.doSeckill(courseSelectId);
    }

    @ApiOperation("获取抢课地址")
    @GetMapping("getPath/{id}")
    public Result getPath(@PathVariable("id") Long courseSelectId, String captcha) {
        boolean check = courseSelectService.checkCaptch(courseSelectId, captcha);
        if (!check) {
            return Result.fail(ErrorStatus.PARAMS_ERROR.getCode(), ErrorStatus.PARAMS_ERROR.getMsg());
        }
        return courseSelectService.getPath(courseSelectId);
    }

    @ApiOperation("生成验证码")
    @GetMapping("/captcha")
    @AccessLimit(second = 5, maxCount = 5)
    public void captcha(Long courseSelectId, HttpServletResponse response) {
        if (courseSelectId < 0) {
            throw new RuntimeException(ErrorStatus.ILLEGAL_REQUEST.getMsg());
        }
        // 设置请求头为输出图片的类型
        response.setContentType("image/jpg");
        response.setHeader("Paragm", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 生成验证码，将结果存入redis
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 32, 3);
        redisTemplate.opsForValue().set("capcha:" + UserThreadLocal.get().getId() + ":" + courseSelectId, captcha.text(), 300, TimeUnit.SECONDS);
        try {
            captcha.out(response.getOutputStream());
        } catch (IOException e) {
            log.info("验证码生成失败");
        }
    }


}
