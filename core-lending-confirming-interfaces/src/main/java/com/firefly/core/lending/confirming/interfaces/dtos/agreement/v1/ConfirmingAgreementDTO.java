package com.firefly.core.lending.confirming.interfaces.dtos.agreement.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidDate;
import com.firefly.core.lending.confirming.interfaces.enums.agreement.v1.AgreementStatusEnum;
import com.firefly.core.lending.confirming.interfaces.validators.DateRange;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DateRange(startDateField = "startDate", endDateField = "endDate", message = "Start date must be before or equal to end date")
public class ConfirmingAgreementDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID confirmingAgreementId;

    @NotNull(message = "Contract ID is required")
    @FilterableId
    private UUID contractId;

    @NotNull(message = "Customer ID is required")
    @FilterableId
    private UUID customerId;                      // The buyer’s ID

    @NotNull(message = "Agreement status is required")
    private AgreementStatusEnum agreementStatus;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;
    @NotNull(message = "Credit limit is required")
    @DecimalMin(value = "0.01", message = "Credit limit must be a positive amount")
    private BigDecimal creditLimit;
    @NotNull(message = "Supplier early payment option is required")
    private Boolean supplierEarlyPaymentOption;
    @NotNull(message = "Default advance rate is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Default advance rate must be non-negative")
    @DecimalMax(value = "1.0", inclusive = true, message = "Default advance rate cannot exceed 100%")
    private BigDecimal defaultAdvanceRate;
    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}