package edu.gdut.imis.courseMutualSelection.service.impl;

import com.alibaba.fastjson.JSON;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.service.LoginService;
import edu.gdut.imis.courseMutualSelection.service.UserService;
import edu.gdut.imis.courseMutualSelection.utils.JWTUtils;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author miemiehoho
 * @date 2021/11/22 23:48
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    private static final String slat = "miemiehoho!@#";// 加密盐
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1.检查参数是否合法
         * 2.根据用户、密码查询用户表
         * 3.如果不存在，登录失败
         * 4.如果存在,jwt生成token返回给前端
         * 5.token放入redis:token:user ,设置过期时间
         * 6.登陆认证的时候先认证token字符串是否合法,然后去redis认证是否存在
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorStatus.PARAMS_ERROR.getCode(), ErrorStatus.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password + slat);
        User user = userService.findUser(account, password);
        if (user == null) {
            return Result.fail(ErrorStatus.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorStatus.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public User checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null) {
            return null;
        }
        String userJosn = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJosn)) {
            return null;
        }
        User user = JSON.parseObject(userJosn, User.class);
        return user;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }

}
