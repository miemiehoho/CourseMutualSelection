package edu.gdut.imis.courseMutualSelection.validation;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * 限流
 *
 * @author miemiehoho
 * @date 2022/3/19 11:23
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    /**
     * 时间
     *
     * @return
     */
    int second() default 5;

    /**
     * 最大访问次数
     *
     * @return
     */
    int maxCount() default 5;

    boolean needLogin() default false;
}
