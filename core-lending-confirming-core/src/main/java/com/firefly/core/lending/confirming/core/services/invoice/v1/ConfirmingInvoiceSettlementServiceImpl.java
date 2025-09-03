package com.firefly.core.lending.confirming.core.services.invoice.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.mappers.invoice.v1.ConfirmingInvoiceSettlementMapper;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceSettlementDTO;
import com.firefly.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoiceSettlement;
import com.firefly.core.lending.confirming.models.repositories.invoice.v1.ConfirmingInvoiceSettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class ConfirmingInvoiceSettlementServiceImpl implements ConfirmingInvoiceSettlementService {

    @Autowired
    private ConfirmingInvoiceSettlementRepository repository;

    @Autowired
    private ConfirmingInvoiceSettlementMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingInvoiceSettlementDTO>> findAll(UUID confirmingAgreementId, UUID confirmingInvoiceId, FilterRequest<ConfirmingInvoiceSettlementDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingInvoiceId(confirmingInvoiceId);
        return FilterUtils.createFilter(
                ConfirmingInvoiceSettlement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingInvoiceSettlementDTO> create(UUID confirmingAgreementId, UUID confirmingInvoiceId, ConfirmingInvoiceSettlementDTO dto) {
        dto.setConfirmingInvoiceId(confirmingInvoiceId);
        ConfirmingInvoiceSettlement entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceSettlementDTO> getById(UUID confirmingAgreementId, UUID confirmingInvoiceId, UUID confirmingInvoiceSettlementId) {
        return repository.findById(confirmingInvoiceSettlementId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceSettlementDTO> update(UUID confirmingAgreementId, UUID confirmingInvoiceId, UUID confirmingInvoiceSettlementId, ConfirmingInvoiceSettlementDTO dto) {
        return repository.findById(confirmingInvoiceSettlementId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .flatMap(entity -> {
                    ConfirmingInvoiceSettlement updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setConfirmingInvoiceSettlementId(confirmingInvoiceSettlementId);
                    updatedEntity.setConfirmingInvoiceId(confirmingInvoiceId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID confirmingAgreementId, UUID confirmingInvoiceId, UUID confirmingInvoiceSettlementId) {
        return repository.findById(confirmingInvoiceSettlementId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .flatMap(repository::delete);
    }
}
