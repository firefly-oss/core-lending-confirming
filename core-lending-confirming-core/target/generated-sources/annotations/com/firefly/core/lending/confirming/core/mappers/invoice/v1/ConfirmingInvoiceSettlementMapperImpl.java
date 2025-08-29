package com.firefly.core.lending.confirming.core.mappers.invoice.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceSettlementDTO;
import com.firefly.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoiceSettlement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:12+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ConfirmingInvoiceSettlementMapperImpl implements ConfirmingInvoiceSettlementMapper {

    @Override
    public ConfirmingInvoiceSettlementDTO toDTO(ConfirmingInvoiceSettlement entity) {
        if ( entity == null ) {
            return null;
        }

        ConfirmingInvoiceSettlementDTO.ConfirmingInvoiceSettlementDTOBuilder confirmingInvoiceSettlementDTO = ConfirmingInvoiceSettlementDTO.builder();

        confirmingInvoiceSettlementDTO.confirmingInvoiceSettlementId( entity.getConfirmingInvoiceSettlementId() );
        confirmingInvoiceSettlementDTO.confirmingInvoiceId( entity.getConfirmingInvoiceId() );
        confirmingInvoiceSettlementDTO.transactionId( entity.getTransactionId() );
        confirmingInvoiceSettlementDTO.settlementDate( entity.getSettlementDate() );
        confirmingInvoiceSettlementDTO.settlementAmount( entity.getSettlementAmount() );
        confirmingInvoiceSettlementDTO.feesDeducted( entity.getFeesDeducted() );
        confirmingInvoiceSettlementDTO.netReconciliation( entity.getNetReconciliation() );
        confirmingInvoiceSettlementDTO.isClosed( entity.getIsClosed() );
        confirmingInvoiceSettlementDTO.note( entity.getNote() );
        confirmingInvoiceSettlementDTO.createdAt( entity.getCreatedAt() );
        confirmingInvoiceSettlementDTO.updatedAt( entity.getUpdatedAt() );

        return confirmingInvoiceSettlementDTO.build();
    }

    @Override
    public ConfirmingInvoiceSettlement toEntity(ConfirmingInvoiceSettlementDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConfirmingInvoiceSettlement.ConfirmingInvoiceSettlementBuilder confirmingInvoiceSettlement = ConfirmingInvoiceSettlement.builder();

        confirmingInvoiceSettlement.confirmingInvoiceSettlementId( dto.getConfirmingInvoiceSettlementId() );
        confirmingInvoiceSettlement.confirmingInvoiceId( dto.getConfirmingInvoiceId() );
        confirmingInvoiceSettlement.transactionId( dto.getTransactionId() );
        confirmingInvoiceSettlement.settlementDate( dto.getSettlementDate() );
        confirmingInvoiceSettlement.settlementAmount( dto.getSettlementAmount() );
        confirmingInvoiceSettlement.feesDeducted( dto.getFeesDeducted() );
        confirmingInvoiceSettlement.netReconciliation( dto.getNetReconciliation() );
        confirmingInvoiceSettlement.isClosed( dto.getIsClosed() );
        confirmingInvoiceSettlement.note( dto.getNote() );
        confirmingInvoiceSettlement.createdAt( dto.getCreatedAt() );
        confirmingInvoiceSettlement.updatedAt( dto.getUpdatedAt() );

        return confirmingInvoiceSettlement.build();
    }
}
