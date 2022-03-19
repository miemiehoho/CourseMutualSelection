package edu.gdut.imis.courseMutualSelection.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * @author miemiehoho
 * @date 2022/3/16 13:20
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {}
)
public @interface IsStudentID {

    boolean requeird() default true;
    boolean isValid() default true;

    String message() default "学生号码格式错误";

}
