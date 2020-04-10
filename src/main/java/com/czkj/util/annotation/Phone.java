package com.czkj.util.annotation;

import com.czkj.util.valdator.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.*;
import java.lang.annotation.*;

/**
 * 验证整数格式
 *
 * @Author steven.sheng
 * @Date 2020/4/9/00915:41
 */
@Target({ElementType.FIELD, ElementType.PARAMETER,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Constraint(validatedBy = {Phone.CheckValidator.class})
@ReportAsSingleViolation
public @interface Phone {
    String message() default "参数不是电话号码格式";

    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CheckValidator implements ConstraintValidator<Phone, Object> {
        String message = "";
        boolean required = false;

        @Override
        public void initialize(Phone constraintAnnotation) {
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
                    return ValidatorUtil.checkMobile(valueStr);
                }
            }else{
                return ValidatorUtil.checkMobile(valueStr);
            }
        }
    }
}
