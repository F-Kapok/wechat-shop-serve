package com.fans.dto.validators;

import com.fans.annotation.TokenPassword;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * className: TokenPasswordValidator
 *
 * @author k
 * @version 1.0
 * @description 密码校验器
 * @date 2020-05-31 21:00
 **/
public class TokenPasswordValidator implements ConstraintValidator<TokenPassword, String> {

    private Integer min;
    private Integer max;

    @Override
    public void initialize(TokenPassword constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(password)) {
            return true;
        }
        return false;
    }
}
