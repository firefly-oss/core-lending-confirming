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


package com.firefly.core.lending.confirming.core.services.advance.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.mappers.advance.v1.ConfirmingAdvanceMapper;
import com.firefly.core.lending.confirming.interfaces.dtos.advance.v1.ConfirmingAdvanceDTO;
import com.firefly.core.lending.confirming.models.entities.advance.v1.ConfirmingAdvance;
import com.firefly.core.lending.confirming.models.repositories.advance.v1.ConfirmingAdvanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ConfirmingAdvanceServiceImpl implements ConfirmingAdvanceService {

    @Autowired
    private ConfirmingAdvanceRepository repository;

    @Autowired
    private ConfirmingAdvanceMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingAdvanceDTO>> findAll(UUID confirmingAgreementId, UUID confirmingInvoiceId, FilterRequest<ConfirmingAdvanceDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingInvoiceId(confirmingInvoiceId);
        return FilterUtils.createFilter(
                ConfirmingAdvance.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingAdvanceDTO> create(UUID confirmingAgreementId, UUID confirmingInvoiceId, ConfirmingAdvanceDTO dto) {
        dto.setConfirmingInvoiceId(confirmingInvoiceId);
        ConfirmingAdvance entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingAdvanceDTO> getById(UUID confirmingAgreementId, UUID confirmingInvoiceId, UUID confirmingAdvanceId) {
        return repository.findById(confirmingAdvanceId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingAdvanceDTO> update(UUID confirmingAgreementId, UUID confirmingInvoiceId, UUID confirmingAdvanceId, ConfirmingAdvanceDTO dto) {
        return repository.findById(confirmingAdvanceId)
                .flatMap(entity -> {
                    if (!entity.getConfirmingInvoiceId().equals(confirmingInvoiceId)) {
                        return Mono.empty();
                    }
                    mapper.toEntity(dto).setConfirmingAdvanceId(entity.getConfirmingAdvanceId());
                    entity.setUpdatedAt(LocalDateTime.now());
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID confirmingAgreementId, UUID confirmingInvoiceId, UUID confirmingAdvanceId) {
        return repository.findById(confirmingAdvanceId)
                .filter(entity -> entity.getConfirmingInvoiceId().equals(confirmingInvoiceId))
                .flatMap(repository::delete);
    }
}