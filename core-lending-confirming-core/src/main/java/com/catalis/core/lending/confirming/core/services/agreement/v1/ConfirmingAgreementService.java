package com.catalis.core.lending.confirming.core.services.agreement.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.confirming.interfaces.dtos.agreement.v1.ConfirmingAgreementDTO;
import reactor.core.publisher.Mono;

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
    Mono<ConfirmingAgreementDTO> getById(Long confirmingAgreementId);

    /**
     * Updates an existing ConfirmingAgreement identified by the given ID with the provided details.
     *
     * @param confirmingAgreementId the unique identifier of the ConfirmingAgreement to be updated
     * @param dto the ConfirmingAgreementDTO containing updated details
     * @return a Mono emitting the updated ConfirmingAgreementDTO upon successful completion
     */
    Mono<ConfirmingAgreementDTO> update(Long confirmingAgreementId, ConfirmingAgreementDTO dto);

    /**
     * Deletes a confirming agreement based on the given confirming agreement ID.
     *
     * @param confirmingAgreementId the ID of the confirming agreement to be deleted
     * @return a Mono signaling completion of the delete operation, or an error if the operation fails
     */
    Mono<Void> delete(Long confirmingAgreementId);
}