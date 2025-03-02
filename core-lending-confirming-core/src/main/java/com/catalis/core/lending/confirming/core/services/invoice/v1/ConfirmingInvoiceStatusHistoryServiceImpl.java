package com.catalis.core.lending.confirming.core.services.invoice.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.confirming.core.mappers.invoice.v1.ConfirmingInvoiceStatusHistoryMapper;
import com.catalis.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceStatusHistoryDTO;
import com.catalis.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoiceStatusHistory;
import com.catalis.core.lending.confirming.models.repositories.invoice.v1.ConfirmingInvoiceStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ConfirmingInvoiceStatusHistoryServiceImpl implements ConfirmingInvoiceStatusHistoryService {

    @Autowired
    private ConfirmingInvoiceStatusHistoryRepository repository;

    @Autowired
    private ConfirmingInvoiceStatusHistoryMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingInvoiceStatusHistoryDTO>> findAll(Long confirmingAgreementId, Long confirmingInvoiceId, FilterRequest<ConfirmingInvoiceStatusHistoryDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingInvoiceId(confirmingInvoiceId);
        return FilterUtils.createFilter(
                ConfirmingInvoiceStatusHistory.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingInvoiceStatusHistoryDTO> create(Long confirmingAgreementId, Long confirmingInvoiceId, ConfirmingInvoiceStatusHistoryDTO dto) {
        dto.setConfirmingInvoiceId(confirmingInvoiceId);
        ConfirmingInvoiceStatusHistory entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceStatusHistoryDTO> getById(Long confirmingAgreementId, Long confirmingInvoiceId, Long confirmingInvoiceStatusHistoryId) {
        return repository.findById(confirmingInvoiceStatusHistoryId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceStatusHistoryDTO> update(Long confirmingAgreementId, Long confirmingInvoiceId, Long confirmingInvoiceStatusHistoryId, ConfirmingInvoiceStatusHistoryDTO dto) {
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
    public Mono<Void> delete(Long confirmingAgreementId, Long confirmingInvoiceId, Long confirmingInvoiceStatusHistoryId) {
        return repository.findById(confirmingInvoiceStatusHistoryId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .flatMap(repository::delete);
    }
}