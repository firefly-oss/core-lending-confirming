package com.catalis.core.lending.confirming.core.services.invoice.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.confirming.core.mappers.invoice.v1.ConfirmingInvoiceMapper;
import com.catalis.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceDTO;
import com.catalis.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoice;
import com.catalis.core.lending.confirming.models.repositories.invoice.v1.ConfirmingInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ConfirmingInvoiceServiceImpl implements ConfirmingInvoiceService {

    @Autowired
    private ConfirmingInvoiceRepository repository;

    @Autowired
    private ConfirmingInvoiceMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingInvoiceDTO>> findAll(Long confirmingAgreementId, FilterRequest<ConfirmingInvoiceDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingAgreementId(confirmingAgreementId);
        return FilterUtils.createFilter(
                ConfirmingInvoice.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingInvoiceDTO> create(Long confirmingAgreementId, ConfirmingInvoiceDTO dto) {
        dto.setConfirmingAgreementId(confirmingAgreementId);
        ConfirmingInvoice entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceDTO> getById(Long confirmingAgreementId, Long confirmingInvoiceId) {
        return repository.findById(confirmingInvoiceId)
                .filter(entity -> entity.getConfirmingAgreementId().equals(confirmingAgreementId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceDTO> update(Long confirmingAgreementId, Long confirmingInvoiceId, ConfirmingInvoiceDTO dto) {
        return repository.findById(confirmingInvoiceId)
                .filter(entity -> entity.getConfirmingAgreementId().equals(confirmingAgreementId))
                .flatMap(existingEntity -> {
                    ConfirmingInvoice updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setConfirmingInvoiceId(confirmingInvoiceId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long confirmingAgreementId, Long confirmingInvoiceId) {
        return repository.findById(confirmingInvoiceId)
                .filter(entity -> entity.getConfirmingAgreementId().equals(confirmingAgreementId))
                .flatMap(repository::delete);
    }
}