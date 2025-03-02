package com.catalis.core.lending.confirming.interfaces.dtos.invoice.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.confirming.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmingInvoiceStatusHistoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long confirmingInvoiceStatusHistoryId;

    @FilterableId
    private Long confirmingInvoiceId;

    private InvoiceStatusEnum oldStatus;
    private InvoiceStatusEnum newStatus;
    private LocalDateTime changedAt;
    private String changedBy;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}