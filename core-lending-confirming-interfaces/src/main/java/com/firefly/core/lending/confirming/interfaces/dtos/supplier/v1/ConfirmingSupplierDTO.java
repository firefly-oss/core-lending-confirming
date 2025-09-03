package com.firefly.core.lending.confirming.interfaces.dtos.supplier.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmingSupplierDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID confirmingSupplierId;

    @NotNull(message = "Confirming agreement ID is required")
    @FilterableId
    private UUID confirmingAgreementId;

    @NotNull(message = "Supplier customer ID is required")
    @FilterableId
    private UUID supplierCustomerId;

    @NotBlank(message = "Supplier name is required")
    @Size(min = 2, max = 255, message = "Supplier name must be between 2 and 255 characters")
    private String supplierName;

    @DecimalMin(value = "0.0", message = "Supplier individual limit must be a valid amount")
    private BigDecimal supplierIndividualLimit;

    @NotNull(message = "Can request early payment flag is required")
    private Boolean canRequestEarlyPayment;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}