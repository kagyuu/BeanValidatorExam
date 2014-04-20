package com.mycompany.beanvalidatorexam;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseBean<T> implements Serializable {

    private boolean success;
    private String message;
    private Map<Path, String> errors;
    
    public void setValidationError(Set<ConstraintViolation<T>> verrors) {
        errors = new HashMap<>();
        for (ConstraintViolation<T> verror : verrors) {
            errors.put(verror.getPropertyPath(), verror.getMessage());
        }
    }
}
