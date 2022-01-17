package edu.gdut.imis.courseMutualSelection.controller;

import edu.gdut.imis.courseMutualSelection.service.QiniuService;
import edu.gdut.imis.courseMutualSelection.utils.QiniuUtils;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author miemiehoho
 * @date 2022/1/16 23:01
 */
@Api(value = "文件上传", tags = "文件上传")
@RestController
@RequestMapping("file")
public class UploadController {

    @Autowired
    private QiniuService qiniuService;

    @PostMapping
    public Result upload(@RequestParam MultipartFile file) {
        return qiniuService.upload(file);
    }
}
