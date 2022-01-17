package edu.gdut.imis.courseMutualSelection.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 管理员信息表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
@Data
@TableName("cms_admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 角色（教务员、系主任、教师）
     */
    private Integer role;
    /**
     * 账号-工号
     */
    private String account;
    /**
     * 姓名
     */
    private String nickname;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 最后登录时间
     */
    private Long lastLogin;
    /**
     * 创建人
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 最后修改人
     */
    private Long lastUserId;
    /**
     * 最后修改时间
     */
    private Long lastTime;
    /**
     * 是否删除
     */
    private Integer deleted;

}
