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
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.interfaces.dtos.supplier.v1.ConfirmingSupplierDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ConfirmingSupplierService {

    /**
     * Retrieves a paginated list of confirming suppliers associated with a specific confirming agreement,
     * based on the given filter criteria.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement to filter suppliers by
     * @param filterRequest the filter criteria and pagination information used to retrieve the suppliers
     * @return a reactive Mono containing the paginated response of confirming suppliers
     */
    Mono<PaginationResponse<ConfirmingSupplierDTO>> findAll(UUID confirmingAgreementId,
                                                            FilterRequest<ConfirmingSupplierDTO> filterRequest);

    /**
     * Creates a new Confirming Supplier associated with a specific Confirming Agreement.
     *
     * @param confirmingAgreementId the ID of the Confirming Agreement to which the supplier will be linked
     * @param dto the ConfirmingSupplierDTO object containing the details of the supplier to be created
     * @return a Mono emitting the created ConfirmingSupplierDTO object, including assigned IDs and other details
     */
    Mono<ConfirmingSupplierDTO> create(UUID confirmingAgreementId, ConfirmingSupplierDTO dto);

    /**
     * Retrieves a ConfirmingSupplierDTO based on the given confirming agreement ID and confirming supplier ID.
     *
     * @param confirmingAgreementId the ID of the confirming agreement associated with the supplier
     * @param confirmingSupplierId the ID of the confirming supplier to be retrieved
     * @return a Mono emitting the ConfirmingSupplierDTO corresponding to the provided IDs, or an empty Mono if not found
     */
    Mono<ConfirmingSupplierDTO> getById(UUID confirmingAgreementId, UUID confirmingSupplierId);

    /**
     * Updates an existing confirming supplier record identified by its confirming agreement ID and confirming supplier ID.
     *
     * @param confirmingAgreementId the ID of the confirming agreement this supplier is associated with
     * @param confirmingSupplierId the unique ID of the confirming supplier to be updated
     * @param dto the ConfirmingSupplierDTO containing the updated information for the confirming supplier
     * @return a Mono emitting the updated ConfirmingSupplierDTO upon successful completion
     */
    Mono<ConfirmingSupplierDTO> update(UUID confirmingAgreementId, UUID confirmingSupplierId, ConfirmingSupplierDTO dto);

    /**
     * Deletes a confirming supplier identified by the given confirming agreement ID
     * and confirming supplier ID. This operation is irreversible.
     *
     * @param confirmingAgreementId the ID of the confirming agreement to which the supplier belongs
     * @param confirmingSupplierId the ID of the confirming supplier to be deleted
     * @return a Mono signaling the completion of the delete operation or an error if the operation fails
     */
    Mono<Void> delete(UUID confirmingAgreementId, UUID confirmingSupplierId);
}
