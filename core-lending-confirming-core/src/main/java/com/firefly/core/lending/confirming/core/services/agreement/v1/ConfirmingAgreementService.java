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
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.interfaces.dtos.agreement.v1.ConfirmingAgreementDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ConfirmingAgreementService {

    /**
     * Retrieves a paginated list of confirming agreements based on the provided filter criteria.
     *
     * @param filterRequest the filter criteria and pagination parameters used to fetch the list of confirming agreements
     * @return a reactive Mono containing the paginated response of confirming agreements
     */
    Mono<PaginationResponse<ConfirmingAgreementDTO>> findAll(FilterRequest<ConfirmingAgreementDTO> filterRequest);

    /**
     * Creates a new Confirming Agreement based on the provided data.
     *
     * @param dto the ConfirmingAgreementDTO object that contains the details of the confirming agreement to be created
     * @return a Mono emitting the created ConfirmingAgreementDTO object, which includes the newly assigned ID and other details
     */
    Mono<ConfirmingAgreementDTO> create(ConfirmingAgreementDTO dto);

    /**
     * Retrieves a ConfirmingAgreementDTO by its ID.
     *
     * @param confirmingAgreementId the ID of the confirming agreement to retrieve
     * @return a Mono emitting the ConfirmingAgreementDTO associated with the given ID, or an empty Mono if not found
     */
    Mono<ConfirmingAgreementDTO> getById(UUID confirmingAgreementId);

    /**
     * Updates an existing ConfirmingAgreement identified by the given ID with the provided details.
     *
     * @param confirmingAgreementId the unique identifier of the ConfirmingAgreement to be updated
     * @param dto the ConfirmingAgreementDTO containing updated details
     * @return a Mono emitting the updated ConfirmingAgreementDTO upon successful completion
     */
    Mono<ConfirmingAgreementDTO> update(UUID confirmingAgreementId, ConfirmingAgreementDTO dto);

    /**
     * Deletes a confirming agreement based on the given confirming agreement ID.
     *
     * @param confirmingAgreementId the ID of the confirming agreement to be deleted
     * @return a Mono signaling completion of the delete operation, or an error if the operation fails
     */
    Mono<Void> delete(UUID confirmingAgreementId);
}