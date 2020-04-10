package com.czkj.util.annotation;

import com.czkj.util.valdator.IdCardUtil;
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
@Constraint(validatedBy = {IdCard.CheckValidator.class})
public @interface IdCard {
    String message() default "参数不是身份证号码格式";

    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CheckValidator implements ConstraintValidator<IdCard, Object> {
        String message = "";
        boolean required = false;

        @Override
        public void initialize(IdCard constraintAnnotation) {
            this.message = constraintAnnotation.message();
            this.required = constraintAnnotation.required();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            String valueStr = value.toString();
            String result;
            if (!required) {
                if(StringUtils.isEmpty(valueStr)){
                    return true;
                }else{
                    result = IdCardUtil.validate(valueStr);
                }
            }else{
                if(StringUtils.isEmpty(valueStr)){
                    return false;
                }
                result = IdCardUtil.validate(valueStr);
            }
            if(StringUtils.isEmpty(result)){
                return true;
            }else{
                this.message = result;
                return false;
            }
        }
    }
}
