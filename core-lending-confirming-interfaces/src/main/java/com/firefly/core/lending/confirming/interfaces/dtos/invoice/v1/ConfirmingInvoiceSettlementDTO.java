package com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidDate;
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
public class ConfirmingInvoiceSettlementDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID confirmingInvoiceSettlementId;

    @NotNull(message = "Confirming invoice ID is required")
    @FilterableId
    private UUID confirmingInvoiceId;

    @NotNull(message = "Transaction ID is required")
    @FilterableId
    private UUID transactionId;

    @NotNull(message = "Settlement date is required")
    private LocalDate settlementDate;

    @NotNull(message = "Settlement amount is required")
    @DecimalMin(value = "0.0", message = "Settlement amount must be a valid amount")
    private BigDecimal settlementAmount;

    @DecimalMin(value = "0.0", message = "Fees deducted must be a valid amount")
    private BigDecimal feesDeducted;

    private BigDecimal netReconciliation;

    @NotNull(message = "Closed status is required")
    private Boolean isClosed;

    @Size(max = 500, message = "Note cannot exceed 500 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}