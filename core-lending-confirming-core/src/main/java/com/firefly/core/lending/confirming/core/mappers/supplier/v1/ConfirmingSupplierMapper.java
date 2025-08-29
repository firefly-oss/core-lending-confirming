package com.firefly.core.lending.confirming.core.mappers.supplier.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.supplier.v1.ConfirmingSupplierDTO;
import com.firefly.core.lending.confirming.models.entities.supplier.v1.ConfirmingSupplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfirmingSupplierMapper {
    ConfirmingSupplierDTO toDTO(ConfirmingSupplier entity);
    ConfirmingSupplier toEntity(ConfirmingSupplierDTO dto);
}