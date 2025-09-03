package com.firefly.core.lending.confirming.models.entities.invoice.v1;

import com.firefly.core.lending.confirming.interfaces.enums.invoice.v1.CurrencyCodeEnum;
import com.firefly.core.lending.confirming.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("confirming_invoice")
public class ConfirmingInvoice {

    @Id
    @Column("confirming_invoice_id")
    private UUID confirmingInvoiceId;

    @Column("confirming_agreement_id")
    private UUID confirmingAgreementId;  // references ConfirmingAgreement

    @Column("confirming_supplier_id")
    private UUID confirmingSupplierId;   // references ConfirmingSupplier

    @Column("invoice_number")
    private String invoiceNumber;

    @Column("invoice_date")
    private LocalDate invoiceDate;

    @Column("due_date")
    private LocalDate dueDate;

    @Column("invoice_amount")
    private BigDecimal invoiceAmount;

    @Column("confirmed_amount")
    private BigDecimal confirmedAmount;

    @Column("currency_code")
    private CurrencyCodeEnum currencyCode;

    @Column("invoice_status")
    private InvoiceStatusEnum invoiceStatus;

    @Column("is_confirmed_by_buyer")
    private Boolean isConfirmedByBuyer;

    @Column("confirmation_date")
    private LocalDate confirmationDate;

    @Column("document_reference")
    private UUID documentReference; // pointer to Document Manager references

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}