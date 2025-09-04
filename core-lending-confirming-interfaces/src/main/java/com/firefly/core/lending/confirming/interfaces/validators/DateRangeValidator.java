/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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
