package com.firefly.core.lending.confirming.core.services.fee.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.mappers.fee.v1.ConfirmingFeeMapper;
import com.firefly.core.lending.confirming.interfaces.dtos.fee.v1.ConfirmingFeeDTO;
import com.firefly.core.lending.confirming.models.entities.fee.v1.ConfirmingFee;
import com.firefly.core.lending.confirming.models.repositories.fee.v1.ConfirmingFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ConfirmingFeeServiceImpl implements ConfirmingFeeService {

    @Autowired
    private ConfirmingFeeRepository repository;

    @Autowired
    private ConfirmingFeeMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingFeeDTO>> findAll(Long confirmingAgreementId, FilterRequest<ConfirmingFeeDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingAgreementId(confirmingAgreementId);
        return FilterUtils.createFilter(
                ConfirmingFee.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingFeeDTO> create(Long confirmingAgreementId, ConfirmingFeeDTO dto) {
        dto.setConfirmingAgreementId(confirmingAgreementId);
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingFeeDTO> getById(Long confirmingAgreementId, Long confirmingFeeId) {
        return repository.findById(confirmingFeeId)
                .filter(entity -> confirmingAgreementId.equals(entity.getConfirmingAgreementId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingFeeDTO> update(Long confirmingAgreementId, Long confirmingFeeId, ConfirmingFeeDTO dto) {
        return repository.findById(confirmingFeeId)
                .filter(entity -> confirmingAgreementId.equals(entity.getConfirmingAgreementId()))
                .flatMap(existing -> {
                    existing.setFeeType(dto.getFeeType());
                    existing.setFeeRate(dto.getFeeRate());
                    existing.setMinFee(dto.getMinFee());
                    existing.setMaxFee(dto.getMaxFee());
                    existing.setIsActive(dto.getIsActive());
                    existing.setNote(dto.getNote());
                    return repository.save(existing);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long confirmingAgreementId, Long confirmingFeeId) {
        return repository.findById(confirmingFeeId)
                .filter(entity -> confirmingAgreementId.equals(entity.getConfirmingAgreementId()))
                .flatMap(repository::delete);
    }
}