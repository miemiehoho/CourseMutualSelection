package edu.gdut.imis.courseMutualSelection.vo.params;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author miemiehoho
 * @date 2022/1/17 5:54
 */
@Data
public class TeacherParam {

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

}
