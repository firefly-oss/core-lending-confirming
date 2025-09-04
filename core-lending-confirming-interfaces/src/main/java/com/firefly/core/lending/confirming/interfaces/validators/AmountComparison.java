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
