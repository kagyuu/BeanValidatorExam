package com.mycompany.beanvalidatorexam;

import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("exam")
@RequestScoped
public class BVExamResource {

    @Context
    private UriInfo context;

    public BVExamResource() {
    }

    @POST
    @Path("order")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseBean orderStock(ExamBean bean) {
        
        ResponseBean<ExamBean> res = new ResponseBean();
        
        // We can't @Inject Validator on Glassfish 4.0, 4.0.1 may be fix it. 
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<ExamBean>> violations = validator.validate(bean);
        System.out.println(violations.toString());
        if (!violations.isEmpty()) {
            // There are some validation errors
            res.setSuccess(false);
            res.setValidationError(violations);
            return res;
        }
        
        res.setSuccess(true);
        return res;
    }
    
    @POST
    @Path("pay")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseBean payment(ExamBean2 bean) {
        
        ResponseBean<ExamBean2> res = new ResponseBean();
        
        // We can't @Inject Validator on Glassfish 4.0, 4.0.1 may be fix it. 
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<ExamBean2>> violations = validator.validate(bean, bean.validateGroups());
        System.out.println(violations.toString());
        if (!violations.isEmpty()) {
            // There are some validation errors
            res.setSuccess(false);
            res.setValidationError(violations);
            return res;
        }
        
        res.setSuccess(true);
        return res;
    }
    
    @POST
    @Path("password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseBean passwordChange(ExamBean3 bean) {
        
        ResponseBean<ExamBean3> res = new ResponseBean();
        
        // We can't @Inject Validator on Glassfish 4.0, 4.0.1 may be fix it. 
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<ExamBean3>> violations = validator.validate(bean);
        System.out.println(violations.toString());
        if (!violations.isEmpty()) {
            // There are some validation errors
            res.setSuccess(false);
            res.setValidationError(violations);
            return res;
        }
        
        res.setSuccess(true);
        return res;
    }
}
