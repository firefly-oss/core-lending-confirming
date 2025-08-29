package com.firefly.core.lending.confirming.core.mappers.fee.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.fee.v1.ConfirmingFeeDTO;
import com.firefly.core.lending.confirming.models.entities.fee.v1.ConfirmingFee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:12+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ConfirmingFeeMapperImpl implements ConfirmingFeeMapper {

    @Override
    public ConfirmingFeeDTO toDTO(ConfirmingFee entity) {
        if ( entity == null ) {
            return null;
        }

        ConfirmingFeeDTO.ConfirmingFeeDTOBuilder confirmingFeeDTO = ConfirmingFeeDTO.builder();

        confirmingFeeDTO.confirmingFeeId( entity.getConfirmingFeeId() );
        confirmingFeeDTO.confirmingAgreementId( entity.getConfirmingAgreementId() );
        confirmingFeeDTO.feeType( entity.getFeeType() );
        confirmingFeeDTO.feeRate( entity.getFeeRate() );
        confirmingFeeDTO.minFee( entity.getMinFee() );
        confirmingFeeDTO.maxFee( entity.getMaxFee() );
        confirmingFeeDTO.isActive( entity.getIsActive() );
        confirmingFeeDTO.note( entity.getNote() );
        confirmingFeeDTO.createdAt( entity.getCreatedAt() );
        confirmingFeeDTO.updatedAt( entity.getUpdatedAt() );

        return confirmingFeeDTO.build();
    }

    @Override
    public ConfirmingFee toEntity(ConfirmingFeeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConfirmingFee.ConfirmingFeeBuilder confirmingFee = ConfirmingFee.builder();

        confirmingFee.confirmingFeeId( dto.getConfirmingFeeId() );
        confirmingFee.confirmingAgreementId( dto.getConfirmingAgreementId() );
        confirmingFee.feeType( dto.getFeeType() );
        confirmingFee.feeRate( dto.getFeeRate() );
        confirmingFee.minFee( dto.getMinFee() );
        confirmingFee.maxFee( dto.getMaxFee() );
        confirmingFee.isActive( dto.getIsActive() );
        confirmingFee.note( dto.getNote() );
        confirmingFee.createdAt( dto.getCreatedAt() );
        confirmingFee.updatedAt( dto.getUpdatedAt() );

        return confirmingFee.build();
    }
}
