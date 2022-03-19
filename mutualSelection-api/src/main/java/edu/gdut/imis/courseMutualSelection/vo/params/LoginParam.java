package edu.gdut.imis.courseMutualSelection.vo.params;

import edu.gdut.imis.courseMutualSelection.validation.IsStudentID;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 登录参数
 *
 * @author miemiehoho
 * @date 2021/11/22 23:42
 */
@Data
public class LoginParam {

    @NotNull
    @IsStudentID
    private String account;

    @NotNull
    @Length(min = 4)
    private String password;
}
