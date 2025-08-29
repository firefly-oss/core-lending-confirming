package com.firefly.core.lending.confirming.core.mappers.agreement.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.agreement.v1.ConfirmingAgreementDTO;
import com.firefly.core.lending.confirming.models.entities.agreement.v1.ConfirmingAgreement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:20:57+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ConfirmingAgreementMapperImpl implements ConfirmingAgreementMapper {

    @Override
    public ConfirmingAgreementDTO toDTO(ConfirmingAgreement entity) {
        if ( entity == null ) {
            return null;
        }

        ConfirmingAgreementDTO.ConfirmingAgreementDTOBuilder confirmingAgreementDTO = ConfirmingAgreementDTO.builder();

        confirmingAgreementDTO.confirmingAgreementId( entity.getConfirmingAgreementId() );
        confirmingAgreementDTO.contractId( entity.getContractId() );
        confirmingAgreementDTO.customerId( entity.getCustomerId() );
        confirmingAgreementDTO.agreementStatus( entity.getAgreementStatus() );
        confirmingAgreementDTO.startDate( entity.getStartDate() );
        confirmingAgreementDTO.endDate( entity.getEndDate() );
        confirmingAgreementDTO.creditLimit( entity.getCreditLimit() );
        confirmingAgreementDTO.supplierEarlyPaymentOption( entity.getSupplierEarlyPaymentOption() );
        confirmingAgreementDTO.defaultAdvanceRate( entity.getDefaultAdvanceRate() );
        confirmingAgreementDTO.remarks( entity.getRemarks() );
        confirmingAgreementDTO.createdAt( entity.getCreatedAt() );
        confirmingAgreementDTO.updatedAt( entity.getUpdatedAt() );

        return confirmingAgreementDTO.build();
    }

    @Override
    public ConfirmingAgreement toEntity(ConfirmingAgreementDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConfirmingAgreement.ConfirmingAgreementBuilder confirmingAgreement = ConfirmingAgreement.builder();

        confirmingAgreement.confirmingAgreementId( dto.getConfirmingAgreementId() );
        confirmingAgreement.contractId( dto.getContractId() );
        confirmingAgreement.customerId( dto.getCustomerId() );
        confirmingAgreement.agreementStatus( dto.getAgreementStatus() );
        confirmingAgreement.startDate( dto.getStartDate() );
        confirmingAgreement.endDate( dto.getEndDate() );
        confirmingAgreement.creditLimit( dto.getCreditLimit() );
        confirmingAgreement.supplierEarlyPaymentOption( dto.getSupplierEarlyPaymentOption() );
        confirmingAgreement.defaultAdvanceRate( dto.getDefaultAdvanceRate() );
        confirmingAgreement.remarks( dto.getRemarks() );
        confirmingAgreement.createdAt( dto.getCreatedAt() );
        confirmingAgreement.updatedAt( dto.getUpdatedAt() );

        return confirmingAgreement.build();
    }
}
