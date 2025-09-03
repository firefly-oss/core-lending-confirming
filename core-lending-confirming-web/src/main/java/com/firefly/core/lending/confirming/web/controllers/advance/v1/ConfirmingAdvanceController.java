package com.firefly.core.lending.confirming.web.controllers.advance.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.services.advance.v1.ConfirmingAdvanceService;
import com.firefly.core.lending.confirming.interfaces.dtos.advance.v1.ConfirmingAdvanceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/advances")
@Tag(name = "ConfirmingAdvance", description = "Early payment advances for a confirming invoice.")
@RequiredArgsConstructor
public class ConfirmingAdvanceController {

    private final ConfirmingAdvanceService service;

    @GetMapping
    @Operation(summary = "List or search advances for a confirming invoice")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingAdvanceDTO>>> findAll(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @ModelAttribute FilterRequest<ConfirmingAdvanceDTO> filterRequest) {

        return service.findAll(confirmingAgreementId, confirmingInvoiceId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new early payment advance record")
    public Mono<ResponseEntity<ConfirmingAdvanceDTO>> create(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @Valid @RequestBody ConfirmingAdvanceDTO dto) {

        return service.create(confirmingAgreementId, confirmingInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingAdvanceId}")
    @Operation(summary = "Get an advance record by ID")
    public Mono<ResponseEntity<ConfirmingAdvanceDTO>> getById(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @PathVariable UUID confirmingAdvanceId) {

        return service.getById(confirmingAgreementId, confirmingInvoiceId, confirmingAdvanceId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingAdvanceId}")
    @Operation(summary = "Update an advance record")
    public Mono<ResponseEntity<ConfirmingAdvanceDTO>> update(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @PathVariable UUID confirmingAdvanceId,
            @Valid @RequestBody ConfirmingAdvanceDTO dto) {

        return service.update(confirmingAgreementId, confirmingInvoiceId, confirmingAdvanceId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingAdvanceId}")
    @Operation(summary = "Delete an advance record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID confirmingAgreementId,
            @PathVariable UUID confirmingInvoiceId,
            @PathVariable UUID confirmingAdvanceId) {

        return service.delete(confirmingAgreementId, confirmingInvoiceId, confirmingAdvanceId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}