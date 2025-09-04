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


package com.firefly.core.lending.confirming.core.services.invoice.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.core.mappers.invoice.v1.ConfirmingInvoiceMapper;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceDTO;
import com.firefly.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoice;
import com.firefly.core.lending.confirming.models.repositories.invoice.v1.ConfirmingInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class ConfirmingInvoiceServiceImpl implements ConfirmingInvoiceService {

    @Autowired
    private ConfirmingInvoiceRepository repository;

    @Autowired
    private ConfirmingInvoiceMapper mapper;

    @Override
    public Mono<PaginationResponse<ConfirmingInvoiceDTO>> findAll(UUID confirmingAgreementId, FilterRequest<ConfirmingInvoiceDTO> filterRequest) {
        filterRequest.getFilters().setConfirmingAgreementId(confirmingAgreementId);
        return FilterUtils.createFilter(
                ConfirmingInvoice.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ConfirmingInvoiceDTO> create(UUID confirmingAgreementId, ConfirmingInvoiceDTO dto) {
        dto.setConfirmingAgreementId(confirmingAgreementId);
        ConfirmingInvoice entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceDTO> getById(UUID confirmingAgreementId, UUID confirmingInvoiceId) {
        return repository.findById(confirmingInvoiceId)
                .filter(entity -> entity.getConfirmingAgreementId().equals(confirmingAgreementId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ConfirmingInvoiceDTO> update(UUID confirmingAgreementId, UUID confirmingInvoiceId, ConfirmingInvoiceDTO dto) {
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
    public Mono<Void> delete(UUID confirmingAgreementId, UUID confirmingInvoiceId) {
        return repository.findById(confirmingInvoiceId)
                .filter(entity -> entity.getConfirmingAgreementId().equals(confirmingAgreementId))
                .flatMap(repository::delete);
    }
}