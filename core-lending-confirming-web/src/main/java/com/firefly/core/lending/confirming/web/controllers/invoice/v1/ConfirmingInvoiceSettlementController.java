package com.firefly.core.lending.confirming.web.controllers.invoice.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.services.invoice.v1.ConfirmingInvoiceSettlementService;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceSettlementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/invoices/{confirmingInvoiceId}/settlements")
@Tag(name = "ConfirmingInvoiceSettlement", description = "Settlement records for an invoice in a confirming program.")
@RequiredArgsConstructor
public class ConfirmingInvoiceSettlementController {

    private final ConfirmingInvoiceSettlementService service;

    @GetMapping
    @Operation(summary = "List or search settlement records for a confirming invoice")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingInvoiceSettlementDTO>>> findAll(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @ModelAttribute FilterRequest<ConfirmingInvoiceSettlementDTO> filterRequest) {

        return service.findAll(confirmingAgreementId, confirmingInvoiceId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new settlement record (payment from buyer)")
    public Mono<ResponseEntity<ConfirmingInvoiceSettlementDTO>> create(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @RequestBody ConfirmingInvoiceSettlementDTO dto) {

        return service.create(confirmingAgreementId, confirmingInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingInvoiceSettlementId}")
    @Operation(summary = "Get a settlement record by ID")
    public Mono<ResponseEntity<ConfirmingInvoiceSettlementDTO>> getById(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @PathVariable Long confirmingInvoiceSettlementId) {

        return service.getById(confirmingAgreementId, confirmingInvoiceId, confirmingInvoiceSettlementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingInvoiceSettlementId}")
    @Operation(summary = "Update a settlement record")
    public Mono<ResponseEntity<ConfirmingInvoiceSettlementDTO>> update(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @PathVariable Long confirmingInvoiceSettlementId,
            @RequestBody ConfirmingInvoiceSettlementDTO dto) {

        return service.update(confirmingAgreementId, confirmingInvoiceId, confirmingInvoiceSettlementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingInvoiceSettlementId}")
    @Operation(summary = "Delete a settlement record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @PathVariable Long confirmingInvoiceSettlementId) {

        return service.delete(confirmingAgreementId, confirmingInvoiceId, confirmingInvoiceSettlementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
