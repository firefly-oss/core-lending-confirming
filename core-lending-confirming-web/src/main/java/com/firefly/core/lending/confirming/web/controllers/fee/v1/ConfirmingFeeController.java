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


package com.firefly.core.lending.confirming.web.controllers.fee.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.services.fee.v1.ConfirmingFeeService;
import com.firefly.core.lending.confirming.interfaces.dtos.fee.v1.ConfirmingFeeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/fees")
@Tag(name = "ConfirmingFee", description = "Fee configurations under a confirming agreement.")
@RequiredArgsConstructor
public class ConfirmingFeeController {

    private final ConfirmingFeeService service;

    @GetMapping
    @Operation(summary = "List or search fees for a confirming agreement")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingFeeDTO>>> findAll(
            @PathVariable UUID confirmingAgreementId,
            @ModelAttribute FilterRequest<ConfirmingFeeDTO> filterRequest) {

        return service.findAll(confirmingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new fee configuration")
    public Mono<ResponseEntity<ConfirmingFeeDTO>> create(
            @PathVariable UUID confirmingAgreementId,
            @Valid @RequestBody ConfirmingFeeDTO dto) {

        return service.create(confirmingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingFeeId}")
    @Operation(summary = "Get a fee configuration by ID")
    public Mono<ResponseEntity<ConfirmingFeeDTO>> getById(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingFeeId) {

        return service.getById(confirmingAgreementId, confirmingFeeId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingFeeId}")
    @Operation(summary = "Update a fee configuration")
    public Mono<ResponseEntity<ConfirmingFeeDTO>> update(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingFeeId,
            @Valid @RequestBody ConfirmingFeeDTO dto) {

        return service.update(confirmingAgreementId, confirmingFeeId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingFeeId}")
    @Operation(summary = "Delete a fee configuration")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingFeeId) {

        return service.delete(confirmingAgreementId, confirmingFeeId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}