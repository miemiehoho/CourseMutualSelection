package edu.gdut.imis.courseMutualSelection.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdut.imis.courseMutualSelection.dao.pojo.Permission;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.service.LoginService;
import edu.gdut.imis.courseMutualSelection.utils.AdminThreadLocal;
import edu.gdut.imis.courseMutualSelection.vo.LoginAdminVo;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.AdminParam;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.TeacherParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import edu.gdut.imis.courseMutualSelection.dao.mapper.AdminMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.Admin;
import edu.gdut.imis.courseMutualSelection.service.AdminService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private static final String slat = "miemiehoho!@#";// 加密盐

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Admin findAdmin(String account, String password) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAccount, account);
        queryWrapper.eq(Admin::getPassword, password);
        queryWrapper.select(Admin::getId, Admin::getRole, Admin::getAccount, Admin::getNickname, Admin::getSex, Admin::getSex, Admin::getEmail
                , Admin::getPhoneNumber, Admin::getLastLogin);
        queryWrapper.last("limit 1");
        return adminMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findAdminByToken(String token) {
        /**
         * 1.token合法性校验
         *  是否为空、解析是否成功、redis是否存在
         * 2.如果校验失败返回错误
         * 3.如果成功返回对应结果：UserVo
         */
        Admin admin = loginService.checkToken(token);
        if (admin == null) {
            return Result.fail(ErrorStatus.TOKEN_ERROR.getCode(), ErrorStatus.TOKEN_ERROR.getMsg());
        }
        LoginAdminVo loginAdminVo = new LoginAdminVo();
        BeanUtils.copyProperties(admin, loginAdminVo);
        loginAdminVo.setId(String.valueOf(admin.getId()));
        return Result.success(loginAdminVo);
    }

    @Override
    public Result updateAdmin(String token, AdminParam adminParam) {
        if (adminParam == null) {
            return Result.success(null);
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminParam, admin);
        admin.setId(AdminThreadLocal.get().getId());
        if (adminParam.getPassword() != null) {
            admin.setPassword(DigestUtils.md5Hex(adminParam.getPassword() + slat));
        }
        adminMapper.updateById(admin);
        // 更新 token
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(admin), 1, TimeUnit.DAYS);
        return Result.success(null);
    }

    @Override
    public Result listTeacher(PageParams pageParams) {
        Page<Admin> page = new Page<>(pageParams.getPage(), pageParams.getPage_size());
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        Page<Admin> adminPage = adminMapper.selectPage(page, queryWrapper);
        List<Admin> adminList = adminPage.getRecords();
        return Result.success(copyList(adminList));
    }

    @Override
    public Result createTeacher(TeacherParam teacherParam) {
        if (AdminThreadLocal.get().getRole() != 1) {
            return Result.fail(ErrorStatus.ACCESS_DENIED.getCode(), ErrorStatus.ACCESS_DENIED.getMsg());
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(teacherParam, admin);
        admin.setPassword(DigestUtils.md5Hex(teacherParam.getAccount() + slat));
        this.adminMapper.insert(admin);
        return Result.success(null);
    }

    @Override
    public Admin findAdminByAccount(String account) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAccount, account);
        queryWrapper.last("limit 1");
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public List<Permission> findPermissionByRoleId(int roleId) {
        return adminMapper.findPermissionByRoleId(roleId);
    }

    private List<LoginAdminVo> copyList(List<Admin> adminList) {
        List<LoginAdminVo> adminVoList = new ArrayList<>();
        for (Admin admin : adminList) {
            adminVoList.add(copy(admin));
        }
        return adminVoList;
    }

    private LoginAdminVo copy(Admin admin) {
        LoginAdminVo adminVo = new LoginAdminVo();
        BeanUtils.copyProperties(admin, adminVo);
        adminVo.setId(String.valueOf(admin.getId()));
        adminVo.setLastLogin(String.valueOf(admin.getLastLogin()));
        return adminVo;
    }


}