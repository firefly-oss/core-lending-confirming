package com.firefly.core.lending.confirming.core.mappers.fee.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.fee.v1.ConfirmingFeeDTO;
import com.firefly.core.lending.confirming.models.entities.fee.v1.ConfirmingFee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfirmingFeeMapper {
    ConfirmingFeeDTO toDTO(ConfirmingFee entity);
    ConfirmingFee toEntity(ConfirmingFeeDTO dto);
}