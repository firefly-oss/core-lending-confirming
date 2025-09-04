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
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.interfaces.dtos.fee.v1.ConfirmingFeeDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ConfirmingFeeService {

    /**
     * Retrieves a paginated list of confirming fees associated with a specific confirming agreement,
     * based on the provided filter criteria.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement to filter fees by
     * @param filterRequest the filter criteria and pagination parameters used to retrieve the list of confirming fees
     * @return a reactive Mono containing the paginated response of confirming fees
     */
    Mono<PaginationResponse<ConfirmingFeeDTO>> findAll(UUID confirmingAgreementId,
                                                       FilterRequest<ConfirmingFeeDTO> filterRequest);

    /**
     * Creates a new Confirming Fee associated with a specific Confirming Agreement.
     *
     * @param confirmingAgreementId the unique identifier of the Confirming Agreement to which the fee is linked
     * @param dto the ConfirmingFeeDTO object containing the details of the fee to be created
     * @return a Mono emitting the created ConfirmingFeeDTO object with assigned IDs and details upon successful creation
     */
    Mono<ConfirmingFeeDTO> create(UUID confirmingAgreementId, ConfirmingFeeDTO dto);

    /**
     * Retrieves a ConfirmingFeeDTO based on the provided confirming agreement ID and confirming fee ID.
     *
     * @param confirmingAgreementId the ID of the confirming agreement to which the fee is associated
     * @param confirmingFeeId the ID of the confirming fee to be retrieved
     * @return a Mono emitting the ConfirmingFeeDTO corresponding to the given IDs, or an empty Mono if not found
     */
    Mono<ConfirmingFeeDTO> getById(UUID confirmingAgreementId, UUID confirmingFeeId);

    /**
     * Updates an existing confirming fee record associated with the specified confirming agreement and fee IDs.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the fee
     * @param confirmingFeeId the unique identifier of the confirming fee to be updated
     * @param dto the ConfirmingFeeDTO containing the updated details for the confirming fee
     * @return a Mono emitting the updated ConfirmingFeeDTO upon successful completion
     */
    Mono<ConfirmingFeeDTO> update(UUID confirmingAgreementId, UUID confirmingFeeId, ConfirmingFeeDTO dto);

    /**
     * Deletes a confirming fee identified by the given confirming agreement ID and confirming fee ID.
     * This operation is irreversible and permanently removes the fee entry from the system.
     *
     * @param confirmingAgreementId the ID of the confirming agreement associated with the fee to be deleted
     * @param confirmingFeeId the unique ID of the confirming fee to be deleted
     * @return a Mono signaling the completion of the delete operation or an error if the operation fails
     */
    Mono<Void> delete(UUID confirmingAgreementId, UUID confirmingFeeId);
}