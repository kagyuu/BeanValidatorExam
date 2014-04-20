package com.mycompany.beanvalidatorexam;

import com.mycompany.beanvalidatorexam.CompositEqual.DateValidator;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DateValidator.class})
public @interface CompositEqual {
    Class<?>[] groups() default {};
    String message() default "Is not equal";
    Class<? extends Payload>[] payload() default {};
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CompositEqual[] value();
    }
    class DateValidator implements ConstraintValidator<com.mycompany.beanvalidatorexam.CompositEqual, Composit> {
        
        @Override
        public void initialize(CompositEqual constraintAnnotation) {
        }

        @Override
        public boolean isValid(Composit value, ConstraintValidatorContext context) {
            return value.getA().equals(value.getB());
        }
    }
}
