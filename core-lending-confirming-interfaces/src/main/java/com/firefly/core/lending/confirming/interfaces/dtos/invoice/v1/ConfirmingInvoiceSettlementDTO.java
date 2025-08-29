package com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmingInvoiceSettlementDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long confirmingInvoiceSettlementId;

    @FilterableId
    private Long confirmingInvoiceId;

    @FilterableId
    private Long transactionId;

    private LocalDate settlementDate;
    private BigDecimal settlementAmount;
    private BigDecimal feesDeducted;
    private BigDecimal netReconciliation;
    private Boolean isClosed;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}