package edu.gdut.imis.courseMutualSelection.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author miemiehoho
 * @date 2022/1/17 5:20
 */
@Data
public class UserVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 账号-学号
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
     * 年级
     */
    private Integer grade;
    /**
     * 专业班级
     */
    private String professionalClass;
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
