package com.mycompany.beanvalidatorexam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ExamBean implements Serializable {

    /**
     * True かどうか検証する.
     * @AssertTrue、@AssertFalse、@Null は、通常単独では使わない。
     * group と一緒に使い、特定の場合には true/false/null になることを検証する
     */
    @AssertTrue
    private boolean agree;

    /**
     * 数値の大小・桁数検証.
     * BigDecimal のほかに int、String の検証も可能.
     */
    @DecimalMax(value = "100", message = "価格は、{value} 以下にしてください")
    @DecimalMin(value = "0", message = "{priceMin}")
    @Digits(integer = 3, fraction = 2)
    @NotNull
    private BigDecimal price;

    /**
     * 数値の大小検証.
     * int のほかに String の検証が可能.
     */
    @Max(value = 500)
    @Min(value = 0)
    @NotNull
    private int amount;

    /**
     * 文字列長の検証.
     * String#length() のほかに Collection#size() の検証もできる.
     */
    @Size(min = 0, max = 255)
    @NotNull
    private String item;

    /**
     * 正規表現の検証.
     */
    @Pattern(regexp = "[0-9]+[.][A-Z]")
    @NotNull
    private String code;

    /**
     * Annotationは、getterにつけてもよい
     */
    private String payDateString;
    
    /**
     * 未来日検証.
     * Date、Calendar の検証ができる
     * @Future のほかに @Past で過去日かどうかを検証することができる。
     * どっちも実際には使わないだろーけど
     * @return 支払日
     */
    @Future
    @NotNull
    public Date getPayDate() {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd").parse(this.payDateString));
        } catch (ParseException | NullPointerException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
