package com.catalis.core.lending.confirming.interfaces.dtos.agreement.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.confirming.interfaces.enums.agreement.v1.AgreementStatusEnum;
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
public class ConfirmingAgreementDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long confirmingAgreementId;

    @FilterableId
    private Long contractId;

    @FilterableId
    private Long customerId;                      // The buyer’s ID

    private AgreementStatusEnum agreementStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal creditLimit;
    private Boolean supplierEarlyPaymentOption;
    private BigDecimal defaultAdvanceRate;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}