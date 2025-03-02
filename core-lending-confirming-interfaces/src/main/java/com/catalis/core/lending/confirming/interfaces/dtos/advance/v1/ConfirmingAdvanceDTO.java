package com.catalis.core.lending.confirming.interfaces.dtos.advance.v1;

import com.catalis.common.core.filters.FilterableId;
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
public class ConfirmingAdvanceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long confirmingAdvanceId;

    @FilterableId
    private Long confirmingInvoiceId;

    @FilterableId
    private Long transactionId;

    private BigDecimal advanceAmount;
    private LocalDate advanceDate;
    private Boolean isFinalAdvance;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}