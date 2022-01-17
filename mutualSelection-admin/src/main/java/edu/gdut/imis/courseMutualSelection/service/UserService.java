package edu.gdut.imis.courseMutualSelection.service;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.UserParam;

import java.util.List;

/**
 * 学生信息表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
public interface UserService {

    Result listStudent(PageParams pageParams);

    Result createStudent(UserParam userParam);

    Result createStudentList(List<UserParam> userParamList);
}

