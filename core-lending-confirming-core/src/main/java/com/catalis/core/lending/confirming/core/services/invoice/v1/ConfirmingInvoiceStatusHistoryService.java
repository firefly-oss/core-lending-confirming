package com.catalis.core.lending.confirming.core.services.invoice.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceStatusHistoryDTO;
import reactor.core.publisher.Mono;

public interface ConfirmingInvoiceStatusHistoryService {

    /**
     * Retrieves a paginated list of confirming invoice status history entries associated with
     * a specific confirming agreement and confirming invoice, based on the provided filter criteria.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement
     * @param confirmingInvoiceId the unique identifier of the confirming invoice
     * @param filterRequest the filter criteria and pagination parameters used to retrieve the list of confirming invoice status history entries
     * @return a reactive Mono containing the paginated response of confirming invoice status history entries
     */
    Mono<PaginationResponse<ConfirmingInvoiceStatusHistoryDTO>> findAll(Long confirmingAgreementId,
                                                                        Long confirmingInvoiceId,
                                                                        FilterRequest<ConfirmingInvoiceStatusHistoryDTO> filterRequest);

    /**
     * Creates a new Confirming Invoice Status History record associated with a specific
     * Confirming Agreement and Confirming Invoice.
     *
     * @param confirmingAgreementId the unique identifier of the Confirming Agreement to which the status history is linked
     * @param confirmingInvoiceId the unique identifier of the Confirming Invoice to which the status history is associated
     * @param dto the ConfirmingInvoiceStatusHistoryDTO object containing the details of the status history to be created
     * @return a Mono emitting the created ConfirmingInvoiceStatusHistoryDTO object with assigned IDs and details upon successful creation
     */
    Mono<ConfirmingInvoiceStatusHistoryDTO> create(Long confirmingAgreementId,
                                                   Long confirmingInvoiceId,
                                                   ConfirmingInvoiceStatusHistoryDTO dto);

    /**
     * Retrieves a ConfirmingInvoiceStatusHistoryDTO based on the provided confirming agreement ID,
     * confirming invoice ID, and confirming invoice status history ID.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement to which the invoice belongs
     * @param confirmingInvoiceId the unique identifier of the confirming invoice for which the status history is being retrieved
     * @param confirmingInvoiceStatusHistoryId the unique identifier of the confirming invoice status history to be retrieved
     * @return a Mono emitting the ConfirmingInvoiceStatusHistoryDTO corresponding to the given IDs, or an empty Mono if not found
     */
    Mono<ConfirmingInvoiceStatusHistoryDTO> getById(Long confirmingAgreementId,
                                                    Long confirmingInvoiceId,
                                                    Long confirmingInvoiceStatusHistoryId);

    /**
     * Updates an existing confirming invoice status history entry associated with a specific
     * confirming agreement, confirming invoice, and status history ID using the provided details.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated
     *                               with the confirming invoice status history
     * @param confirmingInvoiceId the unique identifier of the confirming invoice associated
     *                             with the confirming invoice status history
     * @param confirmingInvoiceStatusHistoryId the unique identifier of the confirming invoice
     *                                          status history to be updated
     * @param dto the ConfirmingInvoiceStatusHistoryDTO object containing the updated details for
     *            the confirming invoice status history
     * @return a Mono emitting the updated ConfirmingInvoiceStatusHistoryDTO upon successful completion
     */
    Mono<ConfirmingInvoiceStatusHistoryDTO> update(Long confirmingAgreementId,
                                                   Long confirmingInvoiceId,
                                                   Long confirmingInvoiceStatusHistoryId,
                                                   ConfirmingInvoiceStatusHistoryDTO dto);

    /**
     * Deletes a confirming invoice status history identified by the given confirming agreement ID,
     * confirming invoice ID, and confirming invoice status history ID. This operation is irreversible
     * and permanently removes the specified entry from the system.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the status history to be deleted
     * @param confirmingInvoiceId the unique identifier of the confirming invoice linked to the status history to be deleted
     * @param confirmingInvoiceStatusHistoryId the unique identifier of the confirming invoice status history to be deleted
     * @return a Mono signaling the completion of the delete operation or an error if the operation fails
     */
    Mono<Void> delete(Long confirmingAgreementId, Long confirmingInvoiceId, Long confirmingInvoiceStatusHistoryId);
}
