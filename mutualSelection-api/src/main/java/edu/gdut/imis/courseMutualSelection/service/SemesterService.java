package edu.gdut.imis.courseMutualSelection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdut.imis.courseMutualSelection.dao.pojo.Semester;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import org.springframework.data.domain.Page;

/**
 * 学期表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
public interface SemesterService {

    Result list(PageParams pageParams);

}

