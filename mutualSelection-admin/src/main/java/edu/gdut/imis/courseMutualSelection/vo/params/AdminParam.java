package edu.gdut.imis.courseMutualSelection.vo.params;

import lombok.Data;

/**
 * @author miemiehoho
 * @date 2022/1/17 2:48
 */
@Data
public class AdminParam {
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
}
