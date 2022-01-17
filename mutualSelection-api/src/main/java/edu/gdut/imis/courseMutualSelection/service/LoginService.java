package edu.gdut.imis.courseMutualSelection.service;

import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.LoginParam;

/**
 * @author miemiehoho
 * @date 2021/11/22 23:46
 */
public interface LoginService {

    public Result login(LoginParam loginParam);

    User checkToken(String token);

    Result logout(String token);
}
