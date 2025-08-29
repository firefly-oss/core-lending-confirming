package com.firefly.core.lending.confirming.core.services.invoice.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceSettlementDTO;
import reactor.core.publisher.Mono;

public interface ConfirmingInvoiceSettlementService {

    /**
     * Retrieves a paginated list of confirming invoice settlements associated with a specific confirming
     * agreement and confirming invoice, based on the provided filter criteria.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement to filter settlements by
     * @param confirmingInvoiceId the unique identifier of the confirming invoice to filter settlements by
     * @param filterRequest the filter criteria and pagination parameters used to retrieve the list of confirming invoice settlements
     * @return a reactive Mono containing the paginated response of confirming invoice settlements
     */
    Mono<PaginationResponse<ConfirmingInvoiceSettlementDTO>> findAll(Long confirmingAgreementId,
                                                                     Long confirmingInvoiceId,
                                                                     FilterRequest<ConfirmingInvoiceSettlementDTO> filterRequest);

    /**
     * Creates a new confirming invoice settlement associated with a specific confirming agreement
     * and confirming invoice.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the settlement
     * @param confirmingInvoiceId the unique identifier of the confirming invoice associated with the settlement
     * @param dto the ConfirmingInvoiceSettlementDTO object containing the details of the settlement to be created
     * @return a Mono emitting the created ConfirmingInvoiceSettlementDTO object with assigned IDs and details upon successful creation
     */
    Mono<ConfirmingInvoiceSettlementDTO> create(Long confirmingAgreementId,
                                                Long confirmingInvoiceId,
                                                ConfirmingInvoiceSettlementDTO dto);

    /**
     * Retrieves a ConfirmingInvoiceSettlementDTO based on the provided confirming agreement ID,
     * confirming invoice ID, and confirming invoice settlement ID.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement
     * @param confirmingInvoiceId the unique identifier of the confirming invoice
     * @param confirmingInvoiceSettlementId the unique identifier of the confirming invoice settlement to be retrieved
     * @return a Mono emitting the ConfirmingInvoiceSettlementDTO corresponding to the given IDs, or an empty Mono if not found
     */
    Mono<ConfirmingInvoiceSettlementDTO> getById(Long confirmingAgreementId,
                                                 Long confirmingInvoiceId,
                                                 Long confirmingInvoiceSettlementId);

    /**
     * Updates an existing confirming invoice settlement record associated with the specified confirming agreement ID,
     * confirming invoice ID, and confirming invoice settlement ID using the provided updated details.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the invoice settlement
     * @param confirmingInvoiceId the unique identifier of the confirming invoice associated with the settlement
     * @param confirmingInvoiceSettlementId the unique identifier of the confirming invoice settlement to be updated
     * @param dto the ConfirmingInvoiceSettlementDTO containing the updated details for the confirming invoice settlement
     * @return a Mono emitting the updated ConfirmingInvoiceSettlementDTO upon successful completion
     */
    Mono<ConfirmingInvoiceSettlementDTO> update(Long confirmingAgreementId,
                                                Long confirmingInvoiceId,
                                                Long confirmingInvoiceSettlementId,
                                                ConfirmingInvoiceSettlementDTO dto);

    /**
     * Deletes a confirming invoice settlement identified by the given confirming agreement ID,
     * confirming invoice ID, and confirming invoice settlement ID. This action is irreversible
     * and permanently removes the settlement entry from the system.
     *
     * @param confirmingAgreementId the unique identifier of the confirming agreement associated with the settlement to be deleted
     * @param confirmingInvoiceId the unique identifier of the confirming invoice to which the settlement belongs
     * @param confirmingInvoiceSettlementId the unique identifier of the confirming invoice settlement to be deleted
     * @return a Mono signaling the completion of the delete operation or an error if the operation fails
     */
    Mono<Void> delete(Long confirmingAgreementId, Long confirmingInvoiceId, Long confirmingInvoiceSettlementId);
}
