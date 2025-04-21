package com.catalis.core.lending.confirming.interfaces.dtos.invoice.v1;

import com.catalis.core.lending.confirming.interfaces.enums.invoice.v1.CurrencyCodeEnum;
import com.catalis.core.lending.confirming.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import com.catalis.core.utils.annotations.FilterableId;
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
public class ConfirmingInvoiceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long confirmingInvoiceId;

    @FilterableId
    private Long confirmingAgreementId;

    @FilterableId
    private Long confirmingSupplierId;

    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private BigDecimal invoiceAmount;
    private BigDecimal confirmedAmount;
    private CurrencyCodeEnum currencyCode;
    private InvoiceStatusEnum invoiceStatus;
    private Boolean isConfirmedByBuyer;
    private LocalDate confirmationDate;

    @FilterableId
    private Long documentReference;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}