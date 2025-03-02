package com.catalis.core.lending.confirming.models.entities.invoice.v1;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("confirming_invoice_settlement")
public class ConfirmingInvoiceSettlement {

    @Id
    @Column("confirming_invoice_settlement_id")
    private Long confirmingInvoiceSettlementId;

    @Column("confirming_invoice_id")
    private Long confirmingInvoiceId; // references ConfirmingInvoice

    @Column("transaction_id")
    private Long transactionId;       // external Payment/Transaction reference

    @Column("settlement_date")
    private LocalDate settlementDate;

    @Column("settlement_amount")
    private BigDecimal settlementAmount;

    @Column("fees_deducted")
    private BigDecimal feesDeducted;

    @Column("net_reconciliation")
    private BigDecimal netReconciliation;

    @Column("is_closed")
    private Boolean isClosed;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}