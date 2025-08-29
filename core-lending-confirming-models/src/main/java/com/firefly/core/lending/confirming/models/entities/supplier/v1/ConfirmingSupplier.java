package com.firefly.core.lending.confirming.models.entities.supplier.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("confirming_supplier")
public class ConfirmingSupplier {

    @Id
    @Column("confirming_supplier_id")
    private Long confirmingSupplierId;

    @Column("confirming_agreement_id")
    private Long confirmingAgreementId;

    @Column("supplier_customer_id")
    private Long supplierCustomerId; // External reference for the supplier

    @Column("supplier_name")
    private String supplierName;

    @Column("supplier_individual_limit")
    private BigDecimal supplierIndividualLimit;

    @Column("can_request_early_payment")
    private Boolean canRequestEarlyPayment;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}