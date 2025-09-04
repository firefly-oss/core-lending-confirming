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


package com.firefly.core.lending.confirming.web.controllers.agreement.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.services.agreement.v1.ConfirmingAgreementService;
import com.firefly.core.lending.confirming.interfaces.dtos.agreement.v1.ConfirmingAgreementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/confirming-agreements")
@Tag(name = "ConfirmingAgreement", description = "Operations on confirming agreements (reverse factoring) for a buyer.")
@RequiredArgsConstructor
public class ConfirmingAgreementController {

    private final ConfirmingAgreementService service;

    @GetMapping
    @Operation(summary = "List or search confirming agreements")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingAgreementDTO>>> findAll(
            @ModelAttribute FilterRequest<ConfirmingAgreementDTO> filterRequest) {
        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new confirming agreement")
    public Mono<ResponseEntity<ConfirmingAgreementDTO>> create(@Valid @RequestBody ConfirmingAgreementDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingAgreementId}")
    @Operation(summary = "Get a confirming agreement by ID")
    public Mono<ResponseEntity<ConfirmingAgreementDTO>> getById(@PathVariable UUID confirmingAgreementId) {
        return service.getById(confirmingAgreementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingAgreementId}")
    @Operation(summary = "Update a confirming agreement")
    public Mono<ResponseEntity<ConfirmingAgreementDTO>> update(
            @PathVariable UUID confirmingAgreementId,
            @Valid @RequestBody ConfirmingAgreementDTO dto) {
        return service.update(confirmingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingAgreementId}")
    @Operation(summary = "Delete a confirming agreement")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID confirmingAgreementId) {
        return service.delete(confirmingAgreementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
