/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.beanvalidatorexam;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author atsushi
 */
@Data
@NoArgsConstructor
public class ExamBean3 {
    @NotNull
    private String password;
    
    @NotNull
    private String confirm;
    
    @CompositEqual
    public Composit getPasswordAndConfirm() {
       return new Composit(password, confirm);
   } 
}
