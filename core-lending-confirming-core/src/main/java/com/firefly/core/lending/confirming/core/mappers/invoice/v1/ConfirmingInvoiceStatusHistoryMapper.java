package com.firefly.core.lending.confirming.core.mappers.invoice.v1;

import com.firefly.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceStatusHistoryDTO;
import com.firefly.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoiceStatusHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfirmingInvoiceStatusHistoryMapper {
    ConfirmingInvoiceStatusHistoryDTO toDTO(ConfirmingInvoiceStatusHistory entity);
    ConfirmingInvoiceStatusHistory toEntity(ConfirmingInvoiceStatusHistoryDTO dto);
}