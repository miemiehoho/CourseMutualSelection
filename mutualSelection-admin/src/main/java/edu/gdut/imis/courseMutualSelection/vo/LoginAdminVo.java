package edu.gdut.imis.courseMutualSelection.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author miemiehoho
 * @date 2021/11/23 23:22
 */
@Data
public class LoginAdminVo {

    /**
     * 主键
     */
    private String id;
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
     * 状态
     */
    private Integer status;
    /**
     * 最后登录时间
     */
    private String lastLogin;


}
