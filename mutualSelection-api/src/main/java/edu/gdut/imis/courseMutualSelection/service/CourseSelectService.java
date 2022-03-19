package edu.gdut.imis.courseMutualSelection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdut.imis.courseMutualSelection.dao.pojo.CourseSelect;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;

/**
 * 可选课程表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
public interface CourseSelectService extends IService<CourseSelect> {

    Result list(PageParams pageParams);

    Result findById(Long id);

    Result doSeckill(Long id);

    CourseSelect findCourseSelectById(Long id);

    Result getPath(Long courseSelectId);

    boolean checkPath(Long userId, String path, Long courseSelectId);

    boolean checkCaptch(Long courseSelectId, String captcha);
}

