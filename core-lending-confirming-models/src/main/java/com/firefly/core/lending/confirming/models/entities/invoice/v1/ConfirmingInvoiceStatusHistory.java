package com.firefly.core.lending.confirming.models.entities.invoice.v1;

import com.firefly.core.lending.confirming.interfaces.enums.invoice.v1.InvoiceStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("confirming_invoice_status_history")
public class ConfirmingInvoiceStatusHistory {

    @Id
    @Column("confirming_invoice_status_history_id")
    private UUID confirmingInvoiceStatusHistoryId;

    @Column("confirming_invoice_id")
    private UUID confirmingInvoiceId; // references ConfirmingInvoice

    @Column("old_status")
    private InvoiceStatusEnum oldStatus;

    @Column("new_status")
    private InvoiceStatusEnum newStatus;

    @Column("changed_at")
    private LocalDateTime changedAt;

    @Column("changed_by")
    private String changedBy;

    @Column("reason")
    private String reason;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}