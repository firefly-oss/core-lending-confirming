package com.catalis.core.lending.confirming.core.mappers.fee.v1;

import com.catalis.core.lending.confirming.interfaces.dtos.fee.v1.ConfirmingFeeDTO;
import com.catalis.core.lending.confirming.models.entities.fee.v1.ConfirmingFee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfirmingFeeMapper {
    ConfirmingFeeDTO toDTO(ConfirmingFee entity);
    ConfirmingFee toEntity(ConfirmingFeeDTO dto);
}