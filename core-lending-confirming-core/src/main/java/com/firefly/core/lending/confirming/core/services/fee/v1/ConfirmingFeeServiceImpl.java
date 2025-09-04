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

import java.util.UUID;

@Service
@Transactional
public class ConfirmingFeeServiceImpl implements ConfirmingFeeService {

    @Autowired
    private ConfirmingFeeRepository repository;

    @Autowired
    private ConfirmingFeeMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingFeeDTO>> findAll(UUID confirmingAgreementId, FilterRequest<ConfirmingFeeDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingAgreementId(confirmingAgreementId);
        return FilterUtils.createFilter(
                ConfirmingFee.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingFeeDTO> create(UUID confirmingAgreementId, ConfirmingFeeDTO dto) {
        dto.setConfirmingAgreementId(confirmingAgreementId);
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingFeeDTO> getById(UUID confirmingAgreementId, UUID confirmingFeeId) {
        return repository.findById(confirmingFeeId)
                .filter(entity -> confirmingAgreementId.equals(entity.getConfirmingAgreementId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingFeeDTO> update(UUID confirmingAgreementId, UUID confirmingFeeId, ConfirmingFeeDTO dto) {
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
    public Mono<Void> delete(UUID confirmingAgreementId, UUID confirmingFeeId) {
        return repository.findById(confirmingFeeId)
                .filter(entity -> confirmingAgreementId.equals(entity.getConfirmingAgreementId()))
                .flatMap(repository::delete);
    }
}