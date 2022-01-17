package edu.gdut.imis.courseMutualSelection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdut.imis.courseMutualSelection.dao.pojo.Admin;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.utils.AdminThreadLocal;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.UserVo;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.UserParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gdut.imis.courseMutualSelection.dao.mapper.UserMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private static final String slat = "miemiehoho!@#";// 加密盐

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result listStudent(PageParams pageParams) {
        Page<User> page = new Page<>(pageParams.getPage(), pageParams.getPage_size());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        List<User> userList = userPage.getRecords();
        return Result.success(copyList(userList));
    }

    @Override
    public Result createStudent(UserParam userParam) {
        Admin admin = AdminThreadLocal.get();
        if (admin.getRole() != 1) {
            return Result.fail(ErrorStatus.ACCESS_DENIED.getCode(), ErrorStatus.ACCESS_DENIED.getMsg());
        }
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setPassword(DigestUtils.md5Hex(userParam.getAccount() + slat));
        this.userMapper.insert(user);
        return Result.success(null);
    }

    @Override
    public Result createStudentList(List<UserParam> userParamList) {
        for (UserParam userParam : userParamList) {
            createStudent(userParam);
        }
        return Result.success(null);
    }

    private List<UserVo> copyList(List<User> userList) {
        List<UserVo> userVoList = new ArrayList<>();
        for (User user : userList) {
            userVoList.add(copy(user));
        }
        return userVoList;
    }

    private UserVo copy(User user) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setId(String.valueOf(user.getId()));
        userVo.setLastLogin(String.valueOf(user.getLastLogin()));
        return userVo;
    }
}