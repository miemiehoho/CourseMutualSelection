package edu.gdut.imis.courseMutualSelection.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author miemiehoho
 * @date 2022/3/16 13:24
 */
public class IsStudentValidator implements ConstraintValidator<IsStudentID, String> {

    private boolean required = false;

    @Override
    public void initialize(IsStudentID constraintAnnotation) {
        required = constraintAnnotation.requeird();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return true;
        }
        return true;
    }


}
