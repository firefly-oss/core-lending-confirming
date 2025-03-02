package com.catalis.core.lending.confirming.core.mappers.supplier.v1;

import com.catalis.core.lending.confirming.interfaces.dtos.supplier.v1.ConfirmingSupplierDTO;
import com.catalis.core.lending.confirming.models.entities.supplier.v1.ConfirmingSupplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfirmingSupplierMapper {
    ConfirmingSupplierDTO toDTO(ConfirmingSupplier entity);
    ConfirmingSupplier toEntity(ConfirmingSupplierDTO dto);
}