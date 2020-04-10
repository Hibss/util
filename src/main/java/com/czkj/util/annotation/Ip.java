package com.czkj.util.annotation;

import com.czkj.util.valdator.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author steven.sheng
 * @Date 2020/4/10/01011:15
 */
@Target({ElementType.FIELD, ElementType.PARAMETER,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Constraint(validatedBy = {Ip.CheckValidator.class})
public @interface Ip {
    String message() default "参数不是ip格式";

    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CheckValidator implements ConstraintValidator<Ip, Object> {
        String message = "";
        boolean required = false;

        @Override
        public void initialize(Ip constraintAnnotation) {
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
                    return ValidatorUtil.checkIp(valueStr);
                }
            }else{
                return ValidatorUtil.checkIp(valueStr);
            }
        }
    }
}
