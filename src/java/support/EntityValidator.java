/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rice Pavel
 */
@Component
public class EntityValidator {
  
   public <T> void validate(List<String> errors, T ent) {
        javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(ent);
        for (ConstraintViolation<T> viol : constraintViolations) {
            errors.add(viol.getMessage());
        }
    }
  
}
