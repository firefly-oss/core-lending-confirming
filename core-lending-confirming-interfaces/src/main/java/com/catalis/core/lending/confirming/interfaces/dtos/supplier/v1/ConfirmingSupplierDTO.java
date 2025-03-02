package com.catalis.core.lending.confirming.interfaces.dtos.supplier.v1;

import com.catalis.common.core.filters.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmingSupplierDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long confirmingSupplierId;

    @FilterableId
    private Long confirmingAgreementId;

    @FilterableId
    private Long supplierCustomerId;

    private String supplierName;
    private BigDecimal supplierIndividualLimit;
    private Boolean canRequestEarlyPayment;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}