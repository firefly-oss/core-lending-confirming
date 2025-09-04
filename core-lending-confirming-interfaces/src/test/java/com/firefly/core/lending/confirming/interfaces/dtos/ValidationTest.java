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


package com.firefly.core.lending.confirming.interfaces.dtos;

import com.firefly.core.lending.confirming.interfaces.dtos.agreement.v1.ConfirmingAgreementDTO;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceDTO;
import com.firefly.core.lending.confirming.interfaces.dtos.supplier.v1.ConfirmingSupplierDTO;
import com.firefly.core.lending.confirming.interfaces.enums.agreement.v1.AgreementStatusEnum;
import com.firefly.core.lending.confirming.interfaces.enums.invoice.v1.CurrencyCodeEnum;
import com.firefly.core.lending.confirming.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to verify that validation annotations work correctly on DTOs.
 */
public class ValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testConfirmingAgreementDTO_ValidData_ShouldPass() {
        ConfirmingAgreementDTO dto = ConfirmingAgreementDTO.builder()
                .contractId(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .agreementStatus(AgreementStatusEnum.ACTIVE)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(30))
                .creditLimit(new BigDecimal("10000.00"))
                .supplierEarlyPaymentOption(true)
                .defaultAdvanceRate(new BigDecimal("0.8"))
                .remarks("Test remarks")
                .build();

        Set<ConstraintViolation<ConfirmingAgreementDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid DTO should not have validation errors");
    }

    @Test
    void testConfirmingAgreementDTO_InvalidData_ShouldFail() {
        ConfirmingAgreementDTO dto = ConfirmingAgreementDTO.builder()
                .contractId(null) // Required field
                .customerId(UUID.randomUUID())
                .agreementStatus(null) // Required field
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().minusDays(1)) // End date in the past
                .creditLimit(new BigDecimal("-100.00")) // Negative amount
                .supplierEarlyPaymentOption(null) // Required field
                .defaultAdvanceRate(new BigDecimal("1.5")) // Rate > 100%
                .remarks("A".repeat(1001)) // Too long
                .build();

        Set<ConstraintViolation<ConfirmingAgreementDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have validation errors");
        
        // Check that we have multiple violations
        assertTrue(violations.size() >= 5, "Should have multiple validation errors");
    }

    @Test
    void testConfirmingSupplierDTO_ValidData_ShouldPass() {
        ConfirmingSupplierDTO dto = ConfirmingSupplierDTO.builder()
                .confirmingAgreementId(UUID.randomUUID())
                .supplierCustomerId(UUID.randomUUID())
                .supplierName("Test Supplier")
                .supplierIndividualLimit(new BigDecimal("5000.00"))
                .canRequestEarlyPayment(true)
                .remarks("Test remarks")
                .build();

        Set<ConstraintViolation<ConfirmingSupplierDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid DTO should not have validation errors");
    }

    @Test
    void testConfirmingSupplierDTO_InvalidData_ShouldFail() {
        ConfirmingSupplierDTO dto = ConfirmingSupplierDTO.builder()
                .confirmingAgreementId(null) // Required field
                .supplierCustomerId(UUID.randomUUID())
                .supplierName("") // Blank name
                .supplierIndividualLimit(new BigDecimal("-100.00")) // Negative amount
                .canRequestEarlyPayment(null) // Required field
                .remarks("A".repeat(1001)) // Too long
                .build();

        Set<ConstraintViolation<ConfirmingSupplierDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Invalid DTO should have validation errors");
    }

    @Test
    void testConfirmingInvoiceDTO_ValidData_ShouldPass() {
        ConfirmingInvoiceDTO dto = ConfirmingInvoiceDTO.builder()
                .confirmingAgreementId(UUID.randomUUID())
                .confirmingSupplierId(UUID.randomUUID())
                .invoiceNumber("INV-001")
                .invoiceDate(LocalDate.now().minusDays(1))
                .dueDate(LocalDate.now().plusDays(30))
                .invoiceAmount(new BigDecimal("1000.00"))
                .confirmedAmount(new BigDecimal("800.00"))
                .currencyCode(CurrencyCodeEnum.EUR)
                .invoiceStatus(InvoiceStatusEnum.CONFIRMED)
                .isConfirmedByBuyer(false)
                .build();

        Set<ConstraintViolation<ConfirmingInvoiceDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid DTO should not have validation errors");
    }

    @Test
    void testConfirmingInvoiceDTO_ConfirmedAmountExceedsInvoiceAmount_ShouldFail() {
        ConfirmingInvoiceDTO dto = ConfirmingInvoiceDTO.builder()
                .confirmingAgreementId(UUID.randomUUID())
                .confirmingSupplierId(UUID.randomUUID())
                .invoiceNumber("INV-001")
                .invoiceDate(LocalDate.now().minusDays(1))
                .dueDate(LocalDate.now().plusDays(30))
                .invoiceAmount(new BigDecimal("1000.00"))
                .confirmedAmount(new BigDecimal("1200.00")) // Exceeds invoice amount
                .currencyCode(CurrencyCodeEnum.EUR)
                .invoiceStatus(InvoiceStatusEnum.CONFIRMED)
                .isConfirmedByBuyer(false)
                .build();

        Set<ConstraintViolation<ConfirmingInvoiceDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "DTO with confirmed amount exceeding invoice amount should have validation errors");
        
        // Check that the custom validation message is present
        boolean hasAmountComparisonError = violations.stream()
                .anyMatch(v -> v.getMessage().contains("Confirmed amount cannot exceed invoice amount"));
        assertTrue(hasAmountComparisonError, "Should have amount comparison validation error");
    }
}
