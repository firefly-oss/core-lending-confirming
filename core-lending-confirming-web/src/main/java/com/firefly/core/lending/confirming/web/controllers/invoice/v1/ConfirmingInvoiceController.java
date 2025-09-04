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


package com.firefly.core.lending.confirming.web.controllers.invoice.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.services.invoice.v1.ConfirmingInvoiceService;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/invoices")
@Tag(name = "ConfirmingInvoice", description = "Invoices confirmed by the buyer, under a confirming agreement.")
@RequiredArgsConstructor
public class ConfirmingInvoiceController {

    private final ConfirmingInvoiceService service;

    @GetMapping
    @Operation(summary = "List or search confirming invoices for an agreement")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingInvoiceDTO>>> findAll(
            @PathVariable UUID confirmingAgreementId,
            @ModelAttribute FilterRequest<ConfirmingInvoiceDTO> filterRequest) {

        return service.findAll(confirmingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new confirming invoice")
    public Mono<ResponseEntity<ConfirmingInvoiceDTO>> create(
            @PathVariable UUID confirmingAgreementId,
            @Valid @RequestBody ConfirmingInvoiceDTO dto) {

        return service.create(confirmingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingInvoiceId}")
    @Operation(summary = "Get a confirming invoice by ID")
    public Mono<ResponseEntity<ConfirmingInvoiceDTO>> getById(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId) {

        return service.getById(confirmingAgreementId, confirmingInvoiceId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingInvoiceId}")
    @Operation(summary = "Update a confirming invoice")
    public Mono<ResponseEntity<ConfirmingInvoiceDTO>> update(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @Valid @RequestBody ConfirmingInvoiceDTO dto) {

        return service.update(confirmingAgreementId, confirmingInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingInvoiceId}")
    @Operation(summary = "Delete a confirming invoice")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId) {

        return service.delete(confirmingAgreementId, confirmingInvoiceId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
