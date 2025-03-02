package com.catalis.core.lending.confirming.core.mappers.invoice.v1;

import com.catalis.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceDTO;
import com.catalis.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfirmingInvoiceMapper {
    ConfirmingInvoiceDTO toDTO(ConfirmingInvoice entity);
    ConfirmingInvoice toEntity(ConfirmingInvoiceDTO dto);
}
