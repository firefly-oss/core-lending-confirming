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

import java.util.UUID;

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
    public Mono<ConfirmingAgreementDTO> getById(UUID confirmingAgreementId) {
        return repository.findById(confirmingAgreementId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingAgreementDTO> update(UUID confirmingAgreementId, ConfirmingAgreementDTO dto) {
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
    public Mono<Void> delete(UUID confirmingAgreementId) {
        return repository.deleteById(confirmingAgreementId);
    }
}
