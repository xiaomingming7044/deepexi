package org.hehaoming.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validator;
import java.util.UUID;

@Component
public class ValidationUtils {
    private static Validator validator;
    private static SpringValidatorAdapter springValidator;

    @Autowired
    public void setValidator(Validator validator) {
        ValidationUtils.validator = validator;
        ValidationUtils.springValidator = new SpringValidatorAdapter(validator);
    }

    /**
     * 校验参数，返回详细错误信息
     */
    public static <T> BindingResult validate(T target, Class<?>... groups) {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(target, target.getClass().getName());
        springValidator.validate(target, errors, groups);
        return errors;
    }

    /**
     * 校验参数，如果失败则抛出异常
     */
    public static <T> void validateAndThrow(T target, Class<?>... groups) throws BindException {
        BindingResult errors = validate(target, groups);
        if (errors.hasErrors()) {
            throw new BindException(errors);
        }
    }

    public static void main(String[] args) {
        System.out.println((Math.random()*100)+10);
        System.out.println((Math.random()*10));
        System.out.println((Math.random()*10));
    }

}
