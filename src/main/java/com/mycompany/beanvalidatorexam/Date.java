/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.beanvalidatorexam;

import com.mycompany.beanvalidatorexam.Date.DateValidator;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DateValidator.class})
public @interface Date {
    String format() default "yyyy-MM-dd";
    Class<?>[] groups() default {};
    String message() default "日付形式にしてください";
    Class<? extends Payload>[] payload() default {};
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Date[] value();
    }
    class DateValidator implements ConstraintValidator<com.mycompany.beanvalidatorexam.Date, String> {
        private DateFormat formatString;
        
        @Override
        public void initialize(Date constraintAnnotation) {
            this.formatString = new SimpleDateFormat(constraintAnnotation.format());
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            try {
                formatString.parse(value);
                return true;
            } catch (ParseException ex) {
                return false;
            }
        }
    }    
}
