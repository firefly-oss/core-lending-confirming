package com.firefly.core.lending.confirming.core.services.agreement.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.mappers.agreement.v1.ConfirmingAgreementMapper;
import com.firefly.core.lending.confirming.interfaces.dtos.agreement.v1.ConfirmingAgreementDTO;
import com.firefly.core.lending.confirming.models.entities.agreement.v1.ConfirmingAgreement;
import com.firefly.core.lending.confirming.models.repositories.agreement.v1.ConfirmingAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ConfirmingAgreementServiceImpl implements ConfirmingAgreementService {

    @Autowired
    private ConfirmingAgreementRepository repository;

    @Autowired
    private ConfirmingAgreementMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingAgreementDTO>> findAll(FilterRequest<ConfirmingAgreementDTO> filterRequest) {
        return FilterUtils.createFilter(
                ConfirmingAgreement.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingAgreementDTO> create(ConfirmingAgreementDTO dto) {
        ConfirmingAgreement entity = mapper.toEntity(dto);
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingAgreementDTO> getById(Long confirmingAgreementId) {
        return repository.findById(confirmingAgreementId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingAgreementDTO> update(Long confirmingAgreementId, ConfirmingAgreementDTO dto) {
        return repository.findById(confirmingAgreementId)
                .flatMap(existing -> {
                    ConfirmingAgreement updated = mapper.toEntity(dto);
                    updated.setConfirmingAgreementId(confirmingAgreementId);
                    updated.setCreatedAt(existing.getCreatedAt());
                    updated.setUpdatedAt(java.time.LocalDateTime.now());
                    return repository.save(updated);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long confirmingAgreementId) {
        return repository.deleteById(confirmingAgreementId);
    }
}
