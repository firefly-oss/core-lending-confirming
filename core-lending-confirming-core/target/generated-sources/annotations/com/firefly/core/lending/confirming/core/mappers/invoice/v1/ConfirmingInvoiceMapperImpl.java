package com.firefly.core.lending.confirming.core.mappers.invoice.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceDTO;
import com.firefly.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoice;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:37:53+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ConfirmingInvoiceMapperImpl implements ConfirmingInvoiceMapper {

    @Override
    public ConfirmingInvoiceDTO toDTO(ConfirmingInvoice entity) {
        if ( entity == null ) {
            return null;
        }

        ConfirmingInvoiceDTO.ConfirmingInvoiceDTOBuilder confirmingInvoiceDTO = ConfirmingInvoiceDTO.builder();

        confirmingInvoiceDTO.confirmingInvoiceId( entity.getConfirmingInvoiceId() );
        confirmingInvoiceDTO.confirmingAgreementId( entity.getConfirmingAgreementId() );
        confirmingInvoiceDTO.confirmingSupplierId( entity.getConfirmingSupplierId() );
        confirmingInvoiceDTO.invoiceNumber( entity.getInvoiceNumber() );
        confirmingInvoiceDTO.invoiceDate( entity.getInvoiceDate() );
        confirmingInvoiceDTO.dueDate( entity.getDueDate() );
        confirmingInvoiceDTO.invoiceAmount( entity.getInvoiceAmount() );
        confirmingInvoiceDTO.confirmedAmount( entity.getConfirmedAmount() );
        confirmingInvoiceDTO.currencyCode( entity.getCurrencyCode() );
        confirmingInvoiceDTO.invoiceStatus( entity.getInvoiceStatus() );
        confirmingInvoiceDTO.isConfirmedByBuyer( entity.getIsConfirmedByBuyer() );
        confirmingInvoiceDTO.confirmationDate( entity.getConfirmationDate() );
        confirmingInvoiceDTO.documentReference( entity.getDocumentReference() );
        confirmingInvoiceDTO.createdAt( entity.getCreatedAt() );
        confirmingInvoiceDTO.updatedAt( entity.getUpdatedAt() );

        return confirmingInvoiceDTO.build();
    }

    @Override
    public ConfirmingInvoice toEntity(ConfirmingInvoiceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConfirmingInvoice.ConfirmingInvoiceBuilder confirmingInvoice = ConfirmingInvoice.builder();

        confirmingInvoice.confirmingInvoiceId( dto.getConfirmingInvoiceId() );
        confirmingInvoice.confirmingAgreementId( dto.getConfirmingAgreementId() );
        confirmingInvoice.confirmingSupplierId( dto.getConfirmingSupplierId() );
        confirmingInvoice.invoiceNumber( dto.getInvoiceNumber() );
        confirmingInvoice.invoiceDate( dto.getInvoiceDate() );
        confirmingInvoice.dueDate( dto.getDueDate() );
        confirmingInvoice.invoiceAmount( dto.getInvoiceAmount() );
        confirmingInvoice.confirmedAmount( dto.getConfirmedAmount() );
        confirmingInvoice.currencyCode( dto.getCurrencyCode() );
        confirmingInvoice.invoiceStatus( dto.getInvoiceStatus() );
        confirmingInvoice.isConfirmedByBuyer( dto.getIsConfirmedByBuyer() );
        confirmingInvoice.confirmationDate( dto.getConfirmationDate() );
        confirmingInvoice.documentReference( dto.getDocumentReference() );
        confirmingInvoice.createdAt( dto.getCreatedAt() );
        confirmingInvoice.updatedAt( dto.getUpdatedAt() );

        return confirmingInvoice.build();
    }
}
