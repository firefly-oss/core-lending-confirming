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