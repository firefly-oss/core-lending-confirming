package com.firefly.core.lending.confirming.core.mappers.advance.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.advance.v1.ConfirmingAdvanceDTO;
import com.firefly.core.lending.confirming.models.entities.advance.v1.ConfirmingAdvance;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:12+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ConfirmingAdvanceMapperImpl implements ConfirmingAdvanceMapper {

    @Override
    public ConfirmingAdvanceDTO toDTO(ConfirmingAdvance entity) {
        if ( entity == null ) {
            return null;
        }

        ConfirmingAdvanceDTO.ConfirmingAdvanceDTOBuilder confirmingAdvanceDTO = ConfirmingAdvanceDTO.builder();

        confirmingAdvanceDTO.confirmingAdvanceId( entity.getConfirmingAdvanceId() );
        confirmingAdvanceDTO.confirmingInvoiceId( entity.getConfirmingInvoiceId() );
        confirmingAdvanceDTO.transactionId( entity.getTransactionId() );
        confirmingAdvanceDTO.advanceAmount( entity.getAdvanceAmount() );
        confirmingAdvanceDTO.advanceDate( entity.getAdvanceDate() );
        confirmingAdvanceDTO.isFinalAdvance( entity.getIsFinalAdvance() );
        confirmingAdvanceDTO.note( entity.getNote() );
        confirmingAdvanceDTO.createdAt( entity.getCreatedAt() );
        confirmingAdvanceDTO.updatedAt( entity.getUpdatedAt() );

        return confirmingAdvanceDTO.build();
    }

    @Override
    public ConfirmingAdvance toEntity(ConfirmingAdvanceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConfirmingAdvance.ConfirmingAdvanceBuilder confirmingAdvance = ConfirmingAdvance.builder();

        confirmingAdvance.confirmingAdvanceId( dto.getConfirmingAdvanceId() );
        confirmingAdvance.confirmingInvoiceId( dto.getConfirmingInvoiceId() );
        confirmingAdvance.transactionId( dto.getTransactionId() );
        confirmingAdvance.advanceAmount( dto.getAdvanceAmount() );
        confirmingAdvance.advanceDate( dto.getAdvanceDate() );
        confirmingAdvance.isFinalAdvance( dto.getIsFinalAdvance() );
        confirmingAdvance.note( dto.getNote() );
        confirmingAdvance.createdAt( dto.getCreatedAt() );
        confirmingAdvance.updatedAt( dto.getUpdatedAt() );

        return confirmingAdvance.build();
    }
}
