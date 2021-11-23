package edu.gdut.imis.courseMutualSelection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.vo.Result;

/**
 * 学生信息表
 *
 * @author miemiehoho
 * @date 2021-11-22 23:31:32
 */
public interface UserService extends IService<User> {

    User findUser(String account, String password);

    Result findUserByToken(String token);
}

