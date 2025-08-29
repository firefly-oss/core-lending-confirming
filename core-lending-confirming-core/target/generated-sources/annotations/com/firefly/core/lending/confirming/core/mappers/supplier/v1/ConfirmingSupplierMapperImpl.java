package com.firefly.core.lending.confirming.core.mappers.supplier.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.supplier.v1.ConfirmingSupplierDTO;
import com.firefly.core.lending.confirming.models.entities.supplier.v1.ConfirmingSupplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:20:57+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ConfirmingSupplierMapperImpl implements ConfirmingSupplierMapper {

    @Override
    public ConfirmingSupplierDTO toDTO(ConfirmingSupplier entity) {
        if ( entity == null ) {
            return null;
        }

        ConfirmingSupplierDTO.ConfirmingSupplierDTOBuilder confirmingSupplierDTO = ConfirmingSupplierDTO.builder();

        confirmingSupplierDTO.confirmingSupplierId( entity.getConfirmingSupplierId() );
        confirmingSupplierDTO.confirmingAgreementId( entity.getConfirmingAgreementId() );
        confirmingSupplierDTO.supplierCustomerId( entity.getSupplierCustomerId() );
        confirmingSupplierDTO.supplierName( entity.getSupplierName() );
        confirmingSupplierDTO.supplierIndividualLimit( entity.getSupplierIndividualLimit() );
        confirmingSupplierDTO.canRequestEarlyPayment( entity.getCanRequestEarlyPayment() );
        confirmingSupplierDTO.remarks( entity.getRemarks() );
        confirmingSupplierDTO.createdAt( entity.getCreatedAt() );
        confirmingSupplierDTO.updatedAt( entity.getUpdatedAt() );

        return confirmingSupplierDTO.build();
    }

    @Override
    public ConfirmingSupplier toEntity(ConfirmingSupplierDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConfirmingSupplier.ConfirmingSupplierBuilder confirmingSupplier = ConfirmingSupplier.builder();

        confirmingSupplier.confirmingSupplierId( dto.getConfirmingSupplierId() );
        confirmingSupplier.confirmingAgreementId( dto.getConfirmingAgreementId() );
        confirmingSupplier.supplierCustomerId( dto.getSupplierCustomerId() );
        confirmingSupplier.supplierName( dto.getSupplierName() );
        confirmingSupplier.supplierIndividualLimit( dto.getSupplierIndividualLimit() );
        confirmingSupplier.canRequestEarlyPayment( dto.getCanRequestEarlyPayment() );
        confirmingSupplier.remarks( dto.getRemarks() );
        confirmingSupplier.createdAt( dto.getCreatedAt() );
        confirmingSupplier.updatedAt( dto.getUpdatedAt() );

        return confirmingSupplier.build();
    }
}
