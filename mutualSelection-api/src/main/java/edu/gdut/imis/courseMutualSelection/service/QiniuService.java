package edu.gdut.imis.courseMutualSelection.service;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author miemiehoho
 * @date 2022/1/16 23:11
 */
public interface QiniuService {

    Result upload(MultipartFile file);
}
