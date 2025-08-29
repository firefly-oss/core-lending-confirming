package com.firefly.core.lending.confirming.core.mappers.agreement.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.agreement.v1.ConfirmingAgreementDTO;
import com.firefly.core.lending.confirming.models.entities.agreement.v1.ConfirmingAgreement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfirmingAgreementMapper {
    ConfirmingAgreementDTO toDTO(ConfirmingAgreement entity);
    ConfirmingAgreement toEntity(ConfirmingAgreementDTO dto);
}
