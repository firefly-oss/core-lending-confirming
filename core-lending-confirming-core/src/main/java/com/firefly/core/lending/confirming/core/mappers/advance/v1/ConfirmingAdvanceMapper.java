package com.firefly.core.lending.confirming.core.mappers.advance.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.advance.v1.ConfirmingAdvanceDTO;
import com.firefly.core.lending.confirming.models.entities.advance.v1.ConfirmingAdvance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfirmingAdvanceMapper {
    ConfirmingAdvanceDTO toDTO(ConfirmingAdvance entity);
    ConfirmingAdvance toEntity(ConfirmingAdvanceDTO dto);
}