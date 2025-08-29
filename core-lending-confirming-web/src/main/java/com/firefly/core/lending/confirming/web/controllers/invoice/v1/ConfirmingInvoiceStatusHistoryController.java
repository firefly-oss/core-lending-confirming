package com.firefly.core.lending.confirming.web.controllers.invoice.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.services.invoice.v1.ConfirmingInvoiceStatusHistoryService;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceStatusHistoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/status-history")
@Tag(name = "ConfirmingInvoiceStatusHistory", description = "Tracks the status transitions of a confirming invoice.")
@RequiredArgsConstructor
public class ConfirmingInvoiceStatusHistoryController {

    private final ConfirmingInvoiceStatusHistoryService service;

    @GetMapping
    @Operation(summary = "List or search invoice status history entries")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingInvoiceStatusHistoryDTO>>> findAll(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @ModelAttribute FilterRequest<ConfirmingInvoiceStatusHistoryDTO> filterRequest) {

        return service.findAll(confirmingAgreementId, confirmingInvoiceId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new invoice status history entry")
    public Mono<ResponseEntity<ConfirmingInvoiceStatusHistoryDTO>> create(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @RequestBody ConfirmingInvoiceStatusHistoryDTO dto) {

        return service.create(confirmingAgreementId, confirmingInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingInvoiceStatusHistoryId}")
    @Operation(summary = "Get an invoice status history entry by ID")
    public Mono<ResponseEntity<ConfirmingInvoiceStatusHistoryDTO>> getById(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @PathVariable Long confirmingInvoiceStatusHistoryId) {

        return service.getById(confirmingAgreementId, confirmingInvoiceId, confirmingInvoiceStatusHistoryId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingInvoiceStatusHistoryId}")
    @Operation(summary = "Update an invoice status history entry")
    public Mono<ResponseEntity<ConfirmingInvoiceStatusHistoryDTO>> update(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @PathVariable Long confirmingInvoiceStatusHistoryId,
            @RequestBody ConfirmingInvoiceStatusHistoryDTO dto) {

        return service.update(confirmingAgreementId, confirmingInvoiceId, confirmingInvoiceStatusHistoryId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingInvoiceStatusHistoryId}")
    @Operation(summary = "Delete an invoice status history entry")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @PathVariable Long confirmingInvoiceStatusHistoryId) {

        return service.delete(confirmingAgreementId, confirmingInvoiceId, confirmingInvoiceStatusHistoryId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}