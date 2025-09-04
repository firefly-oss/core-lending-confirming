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


package com.firefly.core.lending.confirming.web.controllers.supplier.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.services.supplier.v1.ConfirmingSupplierService;
import com.firefly.core.lending.confirming.interfaces.dtos.supplier.v1.ConfirmingSupplierDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/suppliers")
@Tag(name = "ConfirmingSupplier", description = "Supplier operations under a confirming agreement.")
@RequiredArgsConstructor
public class ConfirmingSupplierController {

    private final ConfirmingSupplierService service;

    @GetMapping
    @Operation(summary = "List or search suppliers under a confirming agreement")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingSupplierDTO>>> findAll(
            @PathVariable UUID confirmingAgreementId,
            @ModelAttribute FilterRequest<ConfirmingSupplierDTO> filterRequest) {
        return service.findAll(confirmingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new supplier record under a confirming agreement")
    public Mono<ResponseEntity<ConfirmingSupplierDTO>> create(
            @PathVariable UUID confirmingAgreementId,
            @Valid @RequestBody ConfirmingSupplierDTO dto) {

        return service.create(confirmingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingSupplierId}")
    @Operation(summary = "Get a supplier record by ID")
    public Mono<ResponseEntity<ConfirmingSupplierDTO>> getById(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingSupplierId) {

        return service.getById(confirmingAgreementId, confirmingSupplierId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingSupplierId}")
    @Operation(summary = "Update a supplier record")
    public Mono<ResponseEntity<ConfirmingSupplierDTO>> update(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingSupplierId,
            @Valid @RequestBody ConfirmingSupplierDTO dto) {

        return service.update(confirmingAgreementId, confirmingSupplierId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingSupplierId}")
    @Operation(summary = "Delete a supplier record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingSupplierId) {

        return service.delete(confirmingAgreementId, confirmingSupplierId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
