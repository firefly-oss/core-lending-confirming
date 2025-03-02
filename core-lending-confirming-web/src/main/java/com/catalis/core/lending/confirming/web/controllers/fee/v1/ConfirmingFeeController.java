package com.catalis.core.lending.confirming.web.controllers.fee.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.confirming.core.services.fee.v1.ConfirmingFeeService;
import com.catalis.core.lending.confirming.interfaces.dtos.fee.v1.ConfirmingFeeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/confirming-agreements/{confirmingAgreementId}/fees")
@Tag(name = "ConfirmingFee", description = "Fee configurations under a confirming agreement.")
@RequiredArgsConstructor
public class ConfirmingFeeController {

    private final ConfirmingFeeService service;

    @GetMapping
    @Operation(summary = "List or search fees for a confirming agreement")
    public Mono<ResponseEntity<PaginationResponse<ConfirmingFeeDTO>>> findAll(
            @PathVariable Long confirmingAgreementId,
            @ModelAttribute FilterRequest<ConfirmingFeeDTO> filterRequest) {

        return service.findAll(confirmingAgreementId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new fee configuration")
    public Mono<ResponseEntity<ConfirmingFeeDTO>> create(
            @PathVariable Long confirmingAgreementId,
            @RequestBody ConfirmingFeeDTO dto) {

        return service.create(confirmingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingFeeId}")
    @Operation(summary = "Get a fee configuration by ID")
    public Mono<ResponseEntity<ConfirmingFeeDTO>> getById(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingFeeId) {

        return service.getById(confirmingAgreementId, confirmingFeeId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingFeeId}")
    @Operation(summary = "Update a fee configuration")
    public Mono<ResponseEntity<ConfirmingFeeDTO>> update(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingFeeId,
            @RequestBody ConfirmingFeeDTO dto) {

        return service.update(confirmingAgreementId, confirmingFeeId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingFeeId}")
    @Operation(summary = "Delete a fee configuration")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long confirmingAgreementId,
            @PathVariable Long confirmingFeeId) {

        return service.delete(confirmingAgreementId, confirmingFeeId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}