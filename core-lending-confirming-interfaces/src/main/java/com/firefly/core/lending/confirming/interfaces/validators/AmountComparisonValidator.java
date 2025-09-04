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
import java.math.BigDecimal;

/**
 * Validator for the AmountComparison annotation.
 * Validates that one amount is less than or equal to another amount.
 */
public class AmountComparisonValidator implements ConstraintValidator<AmountComparison, Object> {
    
    private String smallerField;
    private String largerField;
    private boolean allowEqual;
    private String customMessage;
    
    @Override
    public void initialize(AmountComparison constraintAnnotation) {
        this.smallerField = constraintAnnotation.smallerField();
        this.largerField = constraintAnnotation.largerField();
        this.allowEqual = constraintAnnotation.allowEqual();
        this.customMessage = constraintAnnotation.customMessage();
    }
    
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null validation
        }
        
        try {
            BigDecimal smallerAmount = getFieldValue(value, smallerField);
            BigDecimal largerAmount = getFieldValue(value, largerField);
            
            // If either amount is null, let other validators handle it
            if (smallerAmount == null || largerAmount == null) {
                return true;
            }
            
            int comparison = smallerAmount.compareTo(largerAmount);
            
            if (allowEqual) {
                return comparison <= 0;
            } else {
                return comparison < 0;
            }
            
        } catch (Exception e) {
            // If we can't access the fields, assume valid and let other validators handle it
            return true;
        }
    }
    
    private BigDecimal getFieldValue(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (BigDecimal) field.get(object);
    }
}
