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
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ConfirmingInvoiceService {

    /**
     * Retrieves a paginated list of confirming invoices associated with a specific confirming agreement,
     * based on the provided filter criteria.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement to filter invoices by
     * @param filterRequest the filter criteria and pagination parameters used to retrieve the list of confirming invoices
     * @return a reactive Mono containing the paginated response of confirming invoices
     */
    Mono<PaginationResponse<ConfirmingInvoiceDTO>> findAll(UUID confirmingAgreementId,
                                                           FilterRequest<ConfirmingInvoiceDTO> filterRequest);

    /**
     * Creates a new confirming invoice associated with a specific confirming agreement.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement to which the invoice is linked
     * @param dto the ConfirmingInvoiceDTO containing the details of the invoice to be created
     * @return a Mono emitting the created ConfirmingInvoiceDTO object with assigned IDs and details upon successful creation
     */
    Mono<ConfirmingInvoiceDTO> create(UUID confirmingAgreementId, ConfirmingInvoiceDTO dto);

    /**
     * Retrieves a ConfirmingInvoiceDTO associated with the specified confirming agreement ID and confirming invoice ID.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement linked to the invoice
     * @param confirmingInvoiceId the unique identifier of the confirming invoice to be retrieved
     * @return a Mono emitting the ConfirmingInvoiceDTO corresponding to the provided IDs, or an empty Mono if not found
     */
    Mono<ConfirmingInvoiceDTO> getById(UUID confirmingAgreementId, UUID confirmingInvoiceId);

    /**
     * Updates an existing confirming invoice associated with the specified confirming agreement
     * and invoice IDs using the provided details.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the invoice to be updated
     * @param confirmingInvoiceId the unique identifier of the confirming invoice to be updated
     * @param dto the ConfirmingInvoiceDTO containing the updated details for the confirming invoice
     * @return a Mono emitting the updated ConfirmingInvoiceDTO upon successful completion
     */
    Mono<ConfirmingInvoiceDTO> update(UUID confirmingAgreementId, UUID confirmingInvoiceId, ConfirmingInvoiceDTO dto);

    /**
     * Deletes a confirming invoice identified by the given confirming agreement ID and confirming invoice ID.
     * This operation permanently removes the invoice from the system and cannot be undone.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the invoice
     * @param confirmingInvoiceId the unique identifier of the confirming invoice to be deleted
     * @return a Mono signaling the completion of the delete operation or an error if the operation fails
     */
    Mono<Void> delete(UUID confirmingAgreementId, UUID confirmingInvoiceId);
}