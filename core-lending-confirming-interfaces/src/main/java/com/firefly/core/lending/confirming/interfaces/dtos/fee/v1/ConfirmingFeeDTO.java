package com.firefly.core.lending.confirming.interfaces.dtos.fee.v1;

import com.firefly.core.lending.confirming.interfaces.enums.fee.v1.FeeTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
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
public class ConfirmingFeeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long confirmingFeeId;

    @FilterableId
    private Long confirmingAgreementId;

    private FeeTypeEnum feeType;
    private BigDecimal feeRate;
    private BigDecimal minFee;
    private BigDecimal maxFee;
    private Boolean isActive;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}