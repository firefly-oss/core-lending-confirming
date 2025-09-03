package com.firefly.core.lending.confirming.core.services.invoice.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.mappers.invoice.v1.ConfirmingInvoiceStatusHistoryMapper;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceStatusHistoryDTO;
import com.firefly.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoiceStatusHistory;
import com.firefly.core.lending.confirming.models.repositories.invoice.v1.ConfirmingInvoiceStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class ConfirmingInvoiceStatusHistoryServiceImpl implements ConfirmingInvoiceStatusHistoryService {

    @Autowired
    private ConfirmingInvoiceStatusHistoryRepository repository;

    @Autowired
    private ConfirmingInvoiceStatusHistoryMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingInvoiceStatusHistoryDTO>> findAll(UUID confirmingAgreementId, UUID confirmingInvoiceId, FilterRequest<ConfirmingInvoiceStatusHistoryDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingInvoiceId(confirmingInvoiceId);
        return FilterUtils.createFilter(
                ConfirmingInvoiceStatusHistory.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingInvoiceStatusHistoryDTO> create(UUID confirmingAgreementId, UUID confirmingInvoiceId, ConfirmingInvoiceStatusHistoryDTO dto) {
        dto.setConfirmingInvoiceId(confirmingInvoiceId);
        ConfirmingInvoiceStatusHistory entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceStatusHistoryDTO> getById(UUID confirmingAgreementId, UUID confirmingInvoiceId, UUID confirmingInvoiceStatusHistoryId) {
        return repository.findById(confirmingInvoiceStatusHistoryId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceStatusHistoryDTO> update(UUID confirmingAgreementId, UUID confirmingInvoiceId, UUID confirmingInvoiceStatusHistoryId, ConfirmingInvoiceStatusHistoryDTO dto) {
        return repository.findById(confirmingInvoiceStatusHistoryId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .flatMap(existingEntity -> {
                    dto.setConfirmingInvoiceStatusHistoryId(confirmingInvoiceStatusHistoryId);
                    dto.setConfirmingInvoiceId(confirmingInvoiceId);
                    ConfirmingInvoiceStatusHistory updatedEntity = mapper.toEntity(dto);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID confirmingAgreementId, UUID confirmingInvoiceId, UUID confirmingInvoiceStatusHistoryId) {
        return repository.findById(confirmingInvoiceStatusHistoryId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .flatMap(repository::delete);
    }
}