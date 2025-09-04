/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.confirming.models.entities.invoice.v1;

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
@Table("confirming_invoice_settlement")
public class ConfirmingInvoiceSettlement {

    @Id
    @Column("confirming_invoice_settlement_id")
    private UUID confirmingInvoiceSettlementId;

    @Column("confirming_invoice_id")
    private UUID confirmingInvoiceId; // references ConfirmingInvoice

    @Column("transaction_id")
    private UUID transactionId;       // external Payment/Transaction reference

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