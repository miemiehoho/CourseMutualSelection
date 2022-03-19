package edu.gdut.imis.courseMutualSelection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdut.imis.courseMutualSelection.dao.pojo.CourseSelect;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.dao.pojo.UserCourse;
import edu.gdut.imis.courseMutualSelection.vo.Result;

/**
 * 学生选课表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
public interface UserCourseService extends IService<UserCourse> {

    UserCourse findByCourseSelectId(Long id);

    UserCourse seckillCourseSelect(User user, CourseSelect courseSelect);

    Result userCourselist();

    Result selectUserCourse(String selectCourseId);
}

