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


package com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1;

import com.firefly.core.lending.confirming.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmingInvoiceStatusHistoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID confirmingInvoiceStatusHistoryId;

    @NotNull(message = "Confirming invoice ID is required")
    @FilterableId
    private UUID confirmingInvoiceId;

    @NotNull(message = "Old status is required")
    private InvoiceStatusEnum oldStatus;

    @NotNull(message = "New status is required")
    private InvoiceStatusEnum newStatus;

    @NotNull(message = "Changed at timestamp is required")
    private LocalDateTime changedAt;

    @NotBlank(message = "Changed by is required")
    @Size(min = 1, max = 100, message = "Changed by must be between 1 and 100 characters")
    private String changedBy;

    @Size(max = 500, message = "Reason cannot exceed 500 characters")
    private String reason;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}