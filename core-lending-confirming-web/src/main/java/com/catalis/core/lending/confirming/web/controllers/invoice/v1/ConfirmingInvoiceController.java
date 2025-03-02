package com.catalis.core.lending.confirming.web.controllers.invoice.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.confirming.core.services.invoice.v1.ConfirmingInvoiceService;
import com.catalis.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/invoices")
@Tag(name = "ConfirmingInvoice", description = "Invoices confirmed by the buyer, under a confirming agreement.")
@RequiredArgsConstructor
public class ConfirmingInvoiceController {

    private final ConfirmingInvoiceService service;

    @GetMapping
    @Operation(summary = "List or search confirming invoices for an agreement")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingInvoiceDTO>>> findAll(
            @PathVariable Long confirmingAgreementId,
            @ModelAttribute FilterRequest<ConfirmingInvoiceDTO> filterRequest) {

        return service.findAll(confirmingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new confirming invoice")
    public Mono<ResponseEntity<ConfirmingInvoiceDTO>> create(
            @PathVariable Long confirmingAgreementId,
            @RequestBody ConfirmingInvoiceDTO dto) {

        return service.create(confirmingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingInvoiceId}")
    @Operation(summary = "Get a confirming invoice by ID")
    public Mono<ResponseEntity<ConfirmingInvoiceDTO>> getById(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId) {

        return service.getById(confirmingAgreementId, confirmingInvoiceId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingInvoiceId}")
    @Operation(summary = "Update a confirming invoice")
    public Mono<ResponseEntity<ConfirmingInvoiceDTO>> update(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId,
            @RequestBody ConfirmingInvoiceDTO dto) {

        return service.update(confirmingAgreementId, confirmingInvoiceId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingInvoiceId}")
    @Operation(summary = "Delete a confirming invoice")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingInvoiceId) {

        return service.delete(confirmingAgreementId, confirmingInvoiceId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
