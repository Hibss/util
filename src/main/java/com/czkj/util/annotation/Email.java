package com.czkj.util.annotation;

import com.czkj.util.valdator.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.*;
import java.lang.annotation.*;

/**
 * @Author steven.sheng
 * @Date 2020/4/10/01011:15
 */
@Target({ElementType.FIELD, ElementType.PARAMETER,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Constraint(validatedBy = {Email.CheckValidator.class})
public @interface Email {
    String message() default "参数不是邮箱格式";

    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CheckValidator implements ConstraintValidator<Email, Object> {
        String message = "";
        boolean required = false;

        @Override
        public void initialize(Email constraintAnnotation) {
            this.message = constraintAnnotation.message();
            this.required = constraintAnnotation.required();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            String valueStr = value.toString();
            if (!required) {
                if(StringUtils.isEmpty(valueStr)){
                    return true;
                }else{
                    return ValidatorUtil.checkEmail(valueStr);
                }
            }else{
                return ValidatorUtil.checkEmail(valueStr);
            }
        }
    }
}
