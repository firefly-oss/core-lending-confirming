package com.catalis.core.lending.confirming.core.mappers.invoice.v1;

import com.catalis.core.lending.confirming.interfaces.dtos.invoice.v1.ConfirmingInvoiceSettlementDTO;
import com.catalis.core.lending.confirming.models.entities.invoice.v1.ConfirmingInvoiceSettlement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfirmingInvoiceSettlementMapper {
    ConfirmingInvoiceSettlementDTO toDTO(ConfirmingInvoiceSettlement entity);
    ConfirmingInvoiceSettlement toEntity(ConfirmingInvoiceSettlementDTO dto);
}