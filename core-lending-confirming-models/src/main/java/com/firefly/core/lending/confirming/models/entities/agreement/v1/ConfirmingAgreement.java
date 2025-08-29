package com.firefly.core.lending.confirming.models.entities.agreement.v1;

import com.firefly.core.lending.confirming.interfaces.enums.agreement.v1.AgreementStatusEnum;
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
@Table("confirming_agreement")
public class ConfirmingAgreement {

    @Id
    @Column("confirming_agreement_id")
    private Long confirmingAgreementId;

    @Column("contract_id")
    private Long contractId;

    @Column("customer_id")
    private Long customerId;  // Buyer’s ID

    @Column("agreement_status")
    private AgreementStatusEnum agreementStatus;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("credit_limit")
    private BigDecimal creditLimit;

    @Column("supplier_early_payment_option")
    private Boolean supplierEarlyPaymentOption;

    @Column("default_advance_rate")
    private BigDecimal defaultAdvanceRate;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}