package com.mycompany.beanvalidatorexam;

import com.mycompany.beanvalidatorexam.CreditCard.DateValidator;
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
public @interface CreditCard {
    Class<?>[] groups() default {};
    String algorithm() default "Luhn";
    String message() default "Invalid Card Number";
    Class<? extends Payload>[] payload() default {};
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CreditCard[] value();
    }
    class DateValidator implements ConstraintValidator<com.mycompany.beanvalidatorexam.CreditCard, String> {
        private String algorithm;
        
        @Override
        public void initialize(CreditCard constraintAnnotation) {
            this.algorithm = constraintAnnotation.algorithm();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (null == value) {
                return true;
            }
            switch(algorithm) {
                default:
                    return LuhnCheck(value);
            }
        }
        
        /**
         * LuhuCheckSum.
         * <pre>
         * (1) 右端の桁が 1 桁目
         * (2) 偶数桁の値を 2 倍する
         * (3) 各桁の値を合計する。(2) で 2 桁になった場合には、別の桁にする。
         * (4) (3)で求めた合計値が 10 の倍数なら合格
         * 
         * (例) Luhu(3,8) = (0 == (3+1+6) % 10)
         * </pre>
         * @param cardNoString クレジットカード番号の候補
         * @return true : 合格 <br/> false : 不合格 
         */
        private boolean LuhnCheck(String cardNoString) {
            boolean evenDigit = false;
            int sum = 0;
            char[] cardNo = cardNoString.toCharArray();
            for (char digitChar : cardNo) {
                if ('-' == digitChar) {
                    continue;
                }
                if (digitChar < '0' || '9' < digitChar) {
                    return false;
                }
                int digit = digitChar - '0';
                
                if (evenDigit) {
                    digit = digit * 2;
                    sum += (digit / 10) + (digit % 10);
                } else {
                    sum += digit;
                }
                
                evenDigit = !evenDigit;
            }
            return 0 == (sum % 10);
        }
    }    
}
