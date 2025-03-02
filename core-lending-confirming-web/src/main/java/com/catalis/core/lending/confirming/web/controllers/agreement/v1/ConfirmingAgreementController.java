package com.catalis.core.lending.confirming.web.controllers.agreement.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.confirming.core.services.agreement.v1.ConfirmingAgreementService;
import com.catalis.core.lending.confirming.interfaces.dtos.agreement.v1.ConfirmingAgreementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<ConfirmingAgreementDTO>> create(@RequestBody ConfirmingAgreementDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{confirmingAgreementId}")
    @Operation(summary = "Get a confirming agreement by ID")
    public Mono<ResponseEntity<ConfirmingAgreementDTO>> getById(@PathVariable Long confirmingAgreementId) {
        return service.getById(confirmingAgreementId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{confirmingAgreementId}")
    @Operation(summary = "Update a confirming agreement")
    public Mono<ResponseEntity<ConfirmingAgreementDTO>> update(
            @PathVariable Long confirmingAgreementId,
            @RequestBody ConfirmingAgreementDTO dto) {
        return service.update(confirmingAgreementId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{confirmingAgreementId}")
    @Operation(summary = "Delete a confirming agreement")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long confirmingAgreementId) {
        return service.delete(confirmingAgreementId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
