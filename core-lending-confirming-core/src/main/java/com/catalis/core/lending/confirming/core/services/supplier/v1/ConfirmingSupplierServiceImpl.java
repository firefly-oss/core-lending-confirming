package com.catalis.core.lending.confirming.core.services.supplier.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.confirming.core.mappers.supplier.v1.ConfirmingSupplierMapper;
import com.catalis.core.lending.confirming.interfaces.dtos.supplier.v1.ConfirmingSupplierDTO;
import com.catalis.core.lending.confirming.models.entities.supplier.v1.ConfirmingSupplier;
import com.catalis.core.lending.confirming.models.repositories.supplier.v1.ConfirmingSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ConfirmingSupplierServiceImpl implements ConfirmingSupplierService {

    @Autowired
    private ConfirmingSupplierRepository repository;

    @Autowired
    private ConfirmingSupplierMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingSupplierDTO>> findAll(Long confirmingAgreementId, FilterRequest<ConfirmingSupplierDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingAgreementId(confirmingAgreementId);
        return FilterUtils.createFilter(
                ConfirmingSupplier.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingSupplierDTO> create(Long confirmingAgreementId, ConfirmingSupplierDTO dto) {
        dto.setConfirmingAgreementId(confirmingAgreementId);
        ConfirmingSupplier entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingSupplierDTO> getById(Long confirmingAgreementId, Long confirmingSupplierId) {
        return repository.findById(confirmingSupplierId)
                .filter(supplier -> confirmingAgreementId.equals(supplier.getConfirmingAgreementId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingSupplierDTO> update(Long confirmingAgreementId, Long confirmingSupplierId, ConfirmingSupplierDTO dto) {
        return repository.findById(confirmingSupplierId)
                .filter(supplier -> confirmingAgreementId.equals(supplier.getConfirmingAgreementId()))
                .flatMap(existingSupplier -> {
                    ConfirmingSupplier updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setConfirmingSupplierId(confirmingSupplierId);
                    updatedEntity.setConfirmingAgreementId(confirmingAgreementId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long confirmingAgreementId, Long confirmingSupplierId) {
        return repository.findById(confirmingSupplierId)
                .filter(supplier -> confirmingAgreementId.equals(supplier.getConfirmingAgreementId()))
                .flatMap(repository::delete)
                .then();
    }
}