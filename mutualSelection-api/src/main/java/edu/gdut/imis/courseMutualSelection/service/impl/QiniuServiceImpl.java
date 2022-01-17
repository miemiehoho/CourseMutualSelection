package edu.gdut.imis.courseMutualSelection.service.impl;

import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.service.QiniuService;
import edu.gdut.imis.courseMutualSelection.utils.QiniuUtils;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author miemiehoho
 * @date 2022/1/16 23:11
 */
@Service
public class QiniuServiceImpl implements QiniuService {
    @Autowired
    private QiniuUtils qiniuUtils;

    @Override
    public Result upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();// 获得原始文件名
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");// 获取文件后缀生成唯一文件名
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) {
            return Result.success(qiniuUtils.url + fileName);
        }
        return Result.fail(ErrorStatus.UPLOAD_ERROR.getCode(), ErrorStatus.UPLOAD_ERROR.getMsg());
    }
}
