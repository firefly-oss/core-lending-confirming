/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.confirming.interfaces.dtos.fee.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.core.lending.confirming.interfaces.enums.fee.v1.FeeTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmingFeeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID confirmingFeeId;

    @NotNull(message = "Confirming agreement ID is required")
    @FilterableId
    private UUID confirmingAgreementId;

    @NotNull(message = "Fee type is required")
    private FeeTypeEnum feeType;

    @NotNull(message = "Fee rate is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Fee rate must be non-negative")
    @DecimalMax(value = "1.0", inclusive = true, message = "Fee rate cannot exceed 100%")
    private BigDecimal feeRate;

    @DecimalMin(value = "0.0", message = "Minimum fee must be a valid amount")
    private BigDecimal minFee;

    @DecimalMin(value = "0.0", message = "Maximum fee must be a valid amount")
    private BigDecimal maxFee;

    @NotNull(message = "Active status is required")
    private Boolean isActive;

    @Size(max = 500, message = "Note cannot exceed 500 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}