package edu.gdut.imis.courseMutualSelection.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.service.LoginService;
import edu.gdut.imis.courseMutualSelection.utils.UserThreadLocal;
import edu.gdut.imis.courseMutualSelection.vo.LoginUserVo;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.UserParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import edu.gdut.imis.courseMutualSelection.dao.mapper.UserMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.service.UserService;

import java.util.concurrent.TimeUnit;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private LoginService loginService;

    @Override
    public User findUser(String account, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, account);
        queryWrapper.eq(User::getPassword, password);
        queryWrapper.select(User::getId, User::getAccount, User::getNickname, User::getSex,
                User::getGrade, User::getProfessionalClass, User::getEmail,
                User::getPhoneNumber, User::getStatus, User::getLastLogin);
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

    @Override
    public Result updateUser(String token, UserParam userParam) {
        if (userParam == null) {
            return Result.success(null);
        }
        User user = UserThreadLocal.get();
        user.setEmail(userParam.getEmail());
        user.setPhoneNumber(userParam.getPhoneNumber());
        userMapper.updateById(user);
        // 更新 token
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);
        UserThreadLocal.put(user);
        return Result.success(null);
    }
}