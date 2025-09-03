package com.firefly.core.lending.confirming.interfaces.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validates that a date range is valid (start date is before or equal to end date).
 * This annotation should be applied at the class level.
 */
@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {
    String message() default "Start date must be before or equal to end date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * The name of the start date field
     */
    String startDateField();
    
    /**
     * The name of the end date field
     */
    String endDateField();
    
    /**
     * Whether the start date can be equal to the end date
     */
    boolean allowEqual() default true;
}
