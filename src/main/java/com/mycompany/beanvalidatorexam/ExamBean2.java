package com.mycompany.beanvalidatorexam;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ExamBean2 implements GroupValidatable {

    @GroupSequence({ Default.class, grpCreditCard.class })
    interface payByCreditCard{};
    
    @GroupSequence({ Default.class, grpBankTransfer.class })
    interface payByBankTransfer{};
    
    interface grpCreditCard{};
    
    interface grpBankTransfer{};
    
    @NotEmpty // 暗黙に javax.validation.groups.Default が設定されている
    private String name;
    
    @NotEmpty // 暗黙に javax.validation.groups.Default が設定されている
    private String addr;
    
    @NotEmpty // 暗黙に javax.validation.groups.Default が設定されている
    private String paymentMethod;
    
    @NotEmpty(message = "なんか入れろ", groups = {grpCreditCard.class}) // ← message は無視される
    private String cardType;
    
    @NotEmpty(groups = {grpCreditCard.class})
    @CreditCard
    private String cardNo;
    
    @NotEmpty(groups = {grpCreditCard.class})
    private String cardAvail;
    
    @NotEmpty(groups = {grpCreditCard.class})
    private String cardCode;
    
    @NotEmpty(groups = {grpBankTransfer.class})
    private String bankNo;
    
    @NotEmpty(groups = {grpBankTransfer.class})
    private String bankName;
    
    @NotEmpty(groups = {grpBankTransfer.class})
    private String bankNominal;
    
    @Override
    public Class[] validateGroups() {
        switch(paymentMethod) {
            case "creditCard":
                return new Class[]{payByCreditCard.class};
            default:
                return new Class[]{payByBankTransfer.class};
        }
    }
}
