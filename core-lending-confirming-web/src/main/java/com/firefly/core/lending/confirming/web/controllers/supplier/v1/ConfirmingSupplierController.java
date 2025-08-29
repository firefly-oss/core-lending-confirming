package com.firefly.core.lending.confirming.web.controllers.supplier.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.services.supplier.v1.ConfirmingSupplierService;
import com.firefly.core.lending.confirming.interfaces.dtos.supplier.v1.ConfirmingSupplierDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/suppliers")
@Tag(name = "ConfirmingSupplier", description = "Supplier operations under a confirming agreement.")
@RequiredArgsConstructor
public class ConfirmingSupplierController {

    private final ConfirmingSupplierService service;

    @GetMapping
    @Operation(summary = "List or search suppliers under a confirming agreement")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingSupplierDTO>>> findAll(
            @PathVariable Long confirmingAgreementId,
            @ModelAttribute FilterRequest<ConfirmingSupplierDTO> filterRequest) {
        return service.findAll(confirmingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new supplier record under a confirming agreement")
    public Mono<ResponseEntity<ConfirmingSupplierDTO>> create(
            @PathVariable Long confirmingAgreementId,
            @RequestBody ConfirmingSupplierDTO dto) {

        return service.create(confirmingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingSupplierId}")
    @Operation(summary = "Get a supplier record by ID")
    public Mono<ResponseEntity<ConfirmingSupplierDTO>> getById(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingSupplierId) {

        return service.getById(confirmingAgreementId, confirmingSupplierId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingSupplierId}")
    @Operation(summary = "Update a supplier record")
    public Mono<ResponseEntity<ConfirmingSupplierDTO>> update(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingSupplierId,
            @RequestBody ConfirmingSupplierDTO dto) {

        return service.update(confirmingAgreementId, confirmingSupplierId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingSupplierId}")
    @Operation(summary = "Delete a supplier record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingSupplierId) {

        return service.delete(confirmingAgreementId, confirmingSupplierId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
