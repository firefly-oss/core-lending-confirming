package com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidDate;
import com.firefly.core.lending.confirming.interfaces.enums.invoice.v1.CurrencyCodeEnum;
import com.firefly.core.lending.confirming.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import com.firefly.core.lending.confirming.interfaces.validators.AmountComparison;
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
@AmountComparison(
    smallerField = "confirmedAmount",
    largerField = "invoiceAmount",
    message = "Confirmed amount cannot exceed invoice amount"
)
public class ConfirmingInvoiceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID confirmingInvoiceId;

    @NotNull(message = "Confirming agreement ID is required")
    @FilterableId
    private UUID confirmingAgreementId;

    @NotNull(message = "Confirming supplier ID is required")
    @FilterableId
    private UUID confirmingSupplierId;

    @NotBlank(message = "Invoice number is required")
    @Size(min = 1, max = 100, message = "Invoice number must be between 1 and 100 characters")
    private String invoiceNumber;

    @NotNull(message = "Invoice date is required")
    @PastOrPresent(message = "Invoice date cannot be in the future")
    private LocalDate invoiceDate;

    @NotNull(message = "Due date is required")
    @Future(message = "Due date must be in the future")
    private LocalDate dueDate;
    @NotNull(message = "Invoice amount is required")
    @DecimalMin(value = "0.01", message = "Invoice amount must be a positive amount")
    private BigDecimal invoiceAmount;

    @DecimalMin(value = "0.0", message = "Confirmed amount must be a valid amount")
    private BigDecimal confirmedAmount;

    @NotNull(message = "Currency code is required")
    private CurrencyCodeEnum currencyCode;

    @NotNull(message = "Invoice status is required")
    private InvoiceStatusEnum invoiceStatus;

    @NotNull(message = "Confirmed by buyer flag is required")
    private Boolean isConfirmedByBuyer;
    private LocalDate confirmationDate;

    @FilterableId
    private UUID documentReference;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}