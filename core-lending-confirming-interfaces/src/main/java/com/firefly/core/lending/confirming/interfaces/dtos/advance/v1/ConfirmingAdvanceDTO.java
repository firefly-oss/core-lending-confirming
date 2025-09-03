package com.firefly.core.lending.confirming.interfaces.dtos.advance.v1;

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
public class ConfirmingAdvanceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID confirmingAdvanceId;

    @NotNull(message = "Confirming invoice ID is required")
    @FilterableId
    private UUID confirmingInvoiceId;

    @NotNull(message = "Transaction ID is required")
    @FilterableId
    private UUID transactionId;

    @NotNull(message = "Advance amount is required")
    @DecimalMin(value = "0.01", message = "Advance amount must be a positive amount")
    private BigDecimal advanceAmount;

    @NotNull(message = "Advance date is required")
    private LocalDate advanceDate;

    @NotNull(message = "Final advance flag is required")
    private Boolean isFinalAdvance;

    @Size(max = 500, message = "Note cannot exceed 500 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}