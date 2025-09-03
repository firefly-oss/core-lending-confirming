package com.firefly.core.lending.confirming.interfaces.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates that one amount field is less than or equal to another amount field.
 * This annotation should be applied at the class level.
 */
@Documented
@Constraint(validatedBy = AmountComparisonValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountComparison {
    String message() default "Amount comparison validation failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * The name of the smaller amount field
     */
    String smallerField();
    
    /**
     * The name of the larger amount field
     */
    String largerField();
    
    /**
     * Whether the amounts can be equal
     */
    boolean allowEqual() default true;
    
    /**
     * Custom message for the validation
     */
    String customMessage() default "";
}
