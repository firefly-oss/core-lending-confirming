package com.firefly.core.lending.confirming.core.mappers.invoice.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceStatusHistoryDTO;
import com.firefly.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoiceStatusHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:12+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ConfirmingInvoiceStatusHistoryMapperImpl implements ConfirmingInvoiceStatusHistoryMapper {

    @Override
    public ConfirmingInvoiceStatusHistoryDTO toDTO(ConfirmingInvoiceStatusHistory entity) {
        if ( entity == null ) {
            return null;
        }

        ConfirmingInvoiceStatusHistoryDTO.ConfirmingInvoiceStatusHistoryDTOBuilder confirmingInvoiceStatusHistoryDTO = ConfirmingInvoiceStatusHistoryDTO.builder();

        confirmingInvoiceStatusHistoryDTO.confirmingInvoiceStatusHistoryId( entity.getConfirmingInvoiceStatusHistoryId() );
        confirmingInvoiceStatusHistoryDTO.confirmingInvoiceId( entity.getConfirmingInvoiceId() );
        confirmingInvoiceStatusHistoryDTO.oldStatus( entity.getOldStatus() );
        confirmingInvoiceStatusHistoryDTO.newStatus( entity.getNewStatus() );
        confirmingInvoiceStatusHistoryDTO.changedAt( entity.getChangedAt() );
        confirmingInvoiceStatusHistoryDTO.changedBy( entity.getChangedBy() );
        confirmingInvoiceStatusHistoryDTO.reason( entity.getReason() );
        confirmingInvoiceStatusHistoryDTO.createdAt( entity.getCreatedAt() );
        confirmingInvoiceStatusHistoryDTO.updatedAt( entity.getUpdatedAt() );

        return confirmingInvoiceStatusHistoryDTO.build();
    }

    @Override
    public ConfirmingInvoiceStatusHistory toEntity(ConfirmingInvoiceStatusHistoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConfirmingInvoiceStatusHistory.ConfirmingInvoiceStatusHistoryBuilder confirmingInvoiceStatusHistory = ConfirmingInvoiceStatusHistory.builder();

        confirmingInvoiceStatusHistory.confirmingInvoiceStatusHistoryId( dto.getConfirmingInvoiceStatusHistoryId() );
        confirmingInvoiceStatusHistory.confirmingInvoiceId( dto.getConfirmingInvoiceId() );
        confirmingInvoiceStatusHistory.oldStatus( dto.getOldStatus() );
        confirmingInvoiceStatusHistory.newStatus( dto.getNewStatus() );
        confirmingInvoiceStatusHistory.changedAt( dto.getChangedAt() );
        confirmingInvoiceStatusHistory.changedBy( dto.getChangedBy() );
        confirmingInvoiceStatusHistory.reason( dto.getReason() );
        confirmingInvoiceStatusHistory.createdAt( dto.getCreatedAt() );
        confirmingInvoiceStatusHistory.updatedAt( dto.getUpdatedAt() );

        return confirmingInvoiceStatusHistory.build();
    }
}
