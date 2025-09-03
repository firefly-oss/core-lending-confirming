package com.firefly.core.lending.confirming.interfaces.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDate;

/**
 * Validator for the DateRange annotation.
 * Validates that a start date is before or equal to an end date.
 */
public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {
    
    private String startDateField;
    private String endDateField;
    private boolean allowEqual;
    
    @Override
    public void initialize(DateRange constraintAnnotation) {
        this.startDateField = constraintAnnotation.startDateField();
        this.endDateField = constraintAnnotation.endDateField();
        this.allowEqual = constraintAnnotation.allowEqual();
    }
    
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null validation
        }
        
        try {
            LocalDate startDate = getFieldValue(value, startDateField);
            LocalDate endDate = getFieldValue(value, endDateField);
            
            // If either date is null, let other validators handle it
            if (startDate == null || endDate == null) {
                return true;
            }
            
            if (allowEqual) {
                return !startDate.isAfter(endDate);
            } else {
                return startDate.isBefore(endDate);
            }
            
        } catch (Exception e) {
            // If we can't access the fields, assume valid and let other validators handle it
            return true;
        }
    }
    
    private LocalDate getFieldValue(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (LocalDate) field.get(object);
    }
}
