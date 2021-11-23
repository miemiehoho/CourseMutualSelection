package edu.gdut.imis.courseMutualSelection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.service.LoginService;
import edu.gdut.imis.courseMutualSelection.vo.LoginUserVo;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import edu.gdut.imis.courseMutualSelection.dao.mapper.UserMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginService loginService;

    @Override
    public User findUser(String account, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, account);
        queryWrapper.eq(User::getPassword, password);
        queryWrapper.select(User::getAccount, User::getPassword, User::getNickname);
        queryWrapper.last("limit 1");
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1.token合法性校验
         *  是否为空、解析是否成功、redis是否存在
         * 2.如果校验失败返回错误
         * 3.如果成功返回对应结果：UserVo
         */
        User user = loginService.checkToken(token);
        if (user == null) {
            return Result.fail(ErrorStatus.TOKEN_ERROR.getCode(), ErrorStatus.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(user, loginUserVo);
        return Result.success(loginUserVo);
    }
}