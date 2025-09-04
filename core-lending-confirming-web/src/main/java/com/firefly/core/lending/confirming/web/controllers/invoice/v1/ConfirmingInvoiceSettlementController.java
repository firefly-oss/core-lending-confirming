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
import com.firefly.core.lending.confirming.core.services.invoice.v1.ConfirmingInvoiceSettlementService;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceSettlementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/settlements")
@Tag(name = "ConfirmingInvoiceSettlement", description = "Settlement records for an invoice in a confirming program.")
@RequiredArgsConstructor
public class ConfirmingInvoiceSettlementController {

    private final ConfirmingInvoiceSettlementService service;

    @GetMapping
    @Operation(summary = "List or search settlement records for a confirming invoice")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingInvoiceSettlementDTO>>> findAll(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @ModelAttribute FilterRequest<ConfirmingInvoiceSettlementDTO> filterRequest) {

        return service.findAll(confirmingAgreementId, confirmingInvoiceId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new settlement record (payment from buyer)")
    public Mono<ResponseEntity<ConfirmingInvoiceSettlementDTO>> create(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @Valid @RequestBody ConfirmingInvoiceSettlementDTO dto) {

        return service.create(confirmingAgreementId, confirmingInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingInvoiceSettlementId}")
    @Operation(summary = "Get a settlement record by ID")
    public Mono<ResponseEntity<ConfirmingInvoiceSettlementDTO>> getById(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @PathVariable UUID confirmingInvoiceSettlementId) {

        return service.getById(confirmingAgreementId, confirmingInvoiceId, confirmingInvoiceSettlementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingInvoiceSettlementId}")
    @Operation(summary = "Update a settlement record")
    public Mono<ResponseEntity<ConfirmingInvoiceSettlementDTO>> update(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @PathVariable UUID confirmingInvoiceSettlementId,
            @Valid @RequestBody ConfirmingInvoiceSettlementDTO dto) {

        return service.update(confirmingAgreementId, confirmingInvoiceId, confirmingInvoiceSettlementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingInvoiceSettlementId}")
    @Operation(summary = "Delete a settlement record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @PathVariable UUID confirmingInvoiceSettlementId) {

        return service.delete(confirmingAgreementId, confirmingInvoiceId, confirmingInvoiceSettlementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
