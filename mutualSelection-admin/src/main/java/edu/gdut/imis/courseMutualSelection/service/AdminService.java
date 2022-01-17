package edu.gdut.imis.courseMutualSelection.service;

import edu.gdut.imis.courseMutualSelection.dao.pojo.Admin;
import edu.gdut.imis.courseMutualSelection.dao.pojo.Permission;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.AdminParam;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.TeacherParam;

import java.util.List;

/**
 * 管理员信息表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
public interface AdminService {

    Admin findAdmin(String account, String password);

    Result findAdminByToken(String token);

    Result updateAdmin(String token, AdminParam adminParam);

    Result listTeacher(PageParams pageParams);

    Result createTeacher(TeacherParam teacherParam);

    Admin findAdminByAccount(String account);

    List<Permission> findPermissionByRoleId(int roleId);
}

