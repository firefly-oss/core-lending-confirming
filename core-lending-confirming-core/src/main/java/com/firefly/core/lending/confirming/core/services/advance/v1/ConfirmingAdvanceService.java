package com.firefly.core.lending.confirming.core.services.advance.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.interfaces.dtos.advance.v1.ConfirmingAdvanceDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ConfirmingAdvanceService {

    /**
     * Retrieves a paginated list of confirming advances associated with a specific confirming agreement
     * and confirming invoice, based on the provided filter criteria.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement to filter advances by
     * @param confirmingInvoiceId the unique identifier of the confirming invoice to filter advances by
     * @param filterRequest the filter criteria and pagination parameters used to retrieve the list of confirming advances
     * @return a reactive Mono containing the paginated response of confirming advances
     */
    Mono<PaginationResponse<ConfirmingAdvanceDTO>> findAll(UUID confirmingAgreementId,
                                                           UUID confirmingInvoiceId,
                                                           FilterRequest<ConfirmingAdvanceDTO> filterRequest);

    /**
     * Creates a new confirming advance associated with a specific confirming agreement and confirming invoice.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement
     * @param confirmingInvoiceId the unique identifier of the confirming invoice
     * @param dto the ConfirmingAdvanceDTO object containing the details of the advance to be created
     * @return a Mono emitting the created ConfirmingAdvanceDTO object with assigned IDs and details upon successful creation
     */
    Mono<ConfirmingAdvanceDTO> create(UUID confirmingAgreementId,
                                      UUID confirmingInvoiceId,
                                      ConfirmingAdvanceDTO dto);

    /**
     * Retrieves a ConfirmingAdvanceDTO based on the provided confirming agreement ID,
     * confirming invoice ID, and confirming advance ID.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the advance
     * @param confirmingInvoiceId the unique identifier of the confirming invoice linked to the advance
     * @param confirmingAdvanceId the unique identifier of the confirming advance to be retrieved
     * @return a Mono emitting the ConfirmingAdvanceDTO corresponding to the given IDs, or an empty Mono if not found
     */
    Mono<ConfirmingAdvanceDTO> getById(UUID confirmingAgreementId,
                                       UUID confirmingInvoiceId,
                                       UUID confirmingAdvanceId);

    /**
     * Updates an existing confirming advance associated with a specified confirming agreement,
     * invoice, and advance IDs using the provided details.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the confirming advance
     * @param confirmingInvoiceId the unique identifier of the confirming invoice associated with the confirming advance
     * @param confirmingAdvanceId the unique identifier of the confirming advance to be updated
     * @param dto the ConfirmingAdvanceDTO containing the updated details for the confirming advance
     * @return a Mono emitting the updated ConfirmingAdvanceDTO upon successful completion
     */
    Mono<ConfirmingAdvanceDTO> update(UUID confirmingAgreementId,
                                      UUID confirmingInvoiceId,
                                      UUID confirmingAdvanceId,
                                      ConfirmingAdvanceDTO dto);

    /**
     * Deletes a confirming advance identified by the given confirming agreement ID,
     * confirming invoice ID, and confirming advance ID. This operation is irreversible
     * and permanently removes the advance entry from the system.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the advance to be deleted
     * @param confirmingInvoiceId the unique identifier of the confirming invoice linked to the advance
     * @param confirmingAdvanceId the unique identifier of the confirming advance to be deleted
     * @return a Mono signaling the completion of the delete operation or an error if the operation fails
     */
    Mono<Void> delete(UUID confirmingAgreementId,
                      UUID confirmingInvoiceId,
                      UUID confirmingAdvanceId);
}