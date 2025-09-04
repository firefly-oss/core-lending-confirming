/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.confirming.core.services.supplier.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.mappers.supplier.v1.ConfirmingSupplierMapper;
import com.firefly.core.lending.confirming.interfaces.dtos.supplier.v1.ConfirmingSupplierDTO;
import com.firefly.core.lending.confirming.models.entities.supplier.v1.ConfirmingSupplier;
import com.firefly.core.lending.confirming.models.repositories.supplier.v1.ConfirmingSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class ConfirmingSupplierServiceImpl implements ConfirmingSupplierService {

    @Autowired
    private ConfirmingSupplierRepository repository;

    @Autowired
    private ConfirmingSupplierMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingSupplierDTO>> findAll(UUID confirmingAgreementId, FilterRequest<ConfirmingSupplierDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingAgreementId(confirmingAgreementId);
        return FilterUtils.createFilter(
                ConfirmingSupplier.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingSupplierDTO> create(UUID confirmingAgreementId, ConfirmingSupplierDTO dto) {
        dto.setConfirmingAgreementId(confirmingAgreementId);
        ConfirmingSupplier entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingSupplierDTO> getById(UUID confirmingAgreementId, UUID confirmingSupplierId) {
        return repository.findById(confirmingSupplierId)
                .filter(supplier -> confirmingAgreementId.equals(supplier.getConfirmingAgreementId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingSupplierDTO> update(UUID confirmingAgreementId, UUID confirmingSupplierId, ConfirmingSupplierDTO dto) {
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
    public Mono<Void> delete(UUID confirmingAgreementId, UUID confirmingSupplierId) {
        return repository.findById(confirmingSupplierId)
                .filter(supplier -> confirmingAgreementId.equals(supplier.getConfirmingAgreementId()))
                .flatMap(repository::delete)
                .then();
    }
}