package com.mycompany.beanvalidatorexam;

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
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 既存の Validator を組み合わせる場合.
 * <pre>
 * 新しいValidator を作るわけではなく、ここで指定した Validator を個々に
 * 設定したのと同じことになる。
 * @NotNull(message = "なんかいれろ") とやっても、検証エラー時に "なんかいれろ"
 * とはならない。@NotNull や @Size のエラーメッセージが発生する。
 * </pre>
 * @author hondou
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
@NotNull()
@Size(min = 1, message = "must not empty")
public @interface NotEmpty {
    String message() default ""; // must be empty
    Class<?>[] groups() default {}; // must be empty
    Class<? extends Payload>[] payload() default {}; // must be empty
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotEmpty[] value();
    }
}