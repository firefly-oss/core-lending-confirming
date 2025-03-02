-- V2 - CREATE TABLES FOR THE CONFIRMING (REVERSE FACTORING) SUBMODULE

-- ========================================================================
-- TABLE: confirming_agreement
-- ========================================================================
CREATE TABLE IF NOT EXISTS confirming_agreement (
                                                    confirming_agreement_id  BIGSERIAL PRIMARY KEY,
                                                    contract_id              BIGINT, -- external reference (Contract domain)
                                                    customer_id              BIGINT, -- The buyer's ID (no direct FK)
                                                    agreement_status         agreement_status NOT NULL,
                                                    start_date               DATE,
                                                    end_date                 DATE,
                                                    credit_limit             DECIMAL(18,2),
    supplier_early_payment_option BOOLEAN DEFAULT FALSE,
    default_advance_rate     DECIMAL(5,2) DEFAULT 80.0,
    remarks                  TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: confirming_supplier
-- ========================================================================
CREATE TABLE IF NOT EXISTS confirming_supplier (
                                                   confirming_supplier_id   BIGSERIAL PRIMARY KEY,
                                                   confirming_agreement_id  BIGINT NOT NULL,
                                                   supplier_customer_id     BIGINT,     -- external ID to the supplier
                                                   supplier_name            VARCHAR(255),
    supplier_individual_limit DECIMAL(18,2),
    can_request_early_payment BOOLEAN DEFAULT TRUE,
    remarks                  TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_supplier_agreement
    FOREIGN KEY (confirming_agreement_id)
    REFERENCES confirming_agreement (confirming_agreement_id)
    );

-- ========================================================================
-- TABLE: confirming_invoice
-- ========================================================================
CREATE TABLE IF NOT EXISTS confirming_invoice (
                                                  confirming_invoice_id    BIGSERIAL PRIMARY KEY,
                                                  confirming_agreement_id  BIGINT NOT NULL,
                                                  confirming_supplier_id   BIGINT NOT NULL,
                                                  invoice_number           VARCHAR(100) NOT NULL,
    invoice_date             DATE NOT NULL,
    due_date                 DATE,
    invoice_amount           DECIMAL(18,2) NOT NULL,
    confirmed_amount         DECIMAL(18,2) NOT NULL,
    currency_code            currency_code NOT NULL,
    invoice_status           invoice_status NOT NULL,
    is_confirmed_by_buyer    BOOLEAN DEFAULT FALSE,
    confirmation_date        DATE,
    document_reference       BIGINT, -- pointer to Document Manager references
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_invoice_agreement
    FOREIGN KEY (confirming_agreement_id)
    REFERENCES confirming_agreement (confirming_agreement_id),
    CONSTRAINT fk_invoice_supplier
    FOREIGN KEY (confirming_supplier_id)
    REFERENCES confirming_supplier (confirming_supplier_id)
    );

-- ========================================================================
-- TABLE: confirming_advance
-- ========================================================================
CREATE TABLE IF NOT EXISTS confirming_advance (
                                                  confirming_advance_id    BIGSERIAL PRIMARY KEY,
                                                  confirming_invoice_id    BIGINT NOT NULL,
                                                  transaction_id           BIGINT,  -- external reference for the payment
                                                  advance_amount           DECIMAL(18,2) NOT NULL,
    advance_date             DATE NOT NULL,
    is_final_advance         BOOLEAN DEFAULT FALSE,
    note                     TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_advance_invoice
    FOREIGN KEY (confirming_invoice_id)
    REFERENCES confirming_invoice (confirming_invoice_id)
    );

-- ========================================================================
-- TABLE: confirming_fee
-- ========================================================================
CREATE TABLE IF NOT EXISTS confirming_fee (
                                              confirming_fee_id        BIGSERIAL PRIMARY KEY,
                                              confirming_agreement_id  BIGINT NOT NULL,
                                              fee_type                 fee_type NOT NULL,
                                              fee_rate                 DECIMAL(9,4) DEFAULT 0,
    min_fee                  DECIMAL(18,2),
    max_fee                  DECIMAL(18,2),
    is_active                BOOLEAN DEFAULT TRUE,
    note                     TEXT,
    created_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at               TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_fee_agreement
    FOREIGN KEY (confirming_agreement_id)
    REFERENCES confirming_agreement (confirming_agreement_id)
    );

-- ========================================================================
-- TABLE: confirming_invoice_settlement
-- ========================================================================
CREATE TABLE IF NOT EXISTS confirming_invoice_settlement (
                                                             confirming_invoice_settlement_id BIGSERIAL PRIMARY KEY,
                                                             confirming_invoice_id      BIGINT NOT NULL,
                                                             transaction_id             BIGINT,  -- external reference to Payment domain
                                                             settlement_date            DATE NOT NULL,
                                                             settlement_amount          DECIMAL(18,2) NOT NULL,
    fees_deducted              DECIMAL(18,2) DEFAULT 0,
    net_reconciliation         DECIMAL(18,2) DEFAULT 0,
    is_closed                  BOOLEAN DEFAULT FALSE,
    note                       TEXT,
    created_at                 TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                 TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_settlement_invoice
    FOREIGN KEY (confirming_invoice_id)
    REFERENCES confirming_invoice (confirming_invoice_id)
    );

-- ========================================================================
-- TABLE: confirming_invoice_status_history
-- ========================================================================
CREATE TABLE IF NOT EXISTS confirming_invoice_status_history (
                                                                 confirming_invoice_status_history_id BIGSERIAL PRIMARY KEY,
                                                                 confirming_invoice_id                BIGINT NOT NULL,
                                                                 old_status                           invoice_status,
                                                                 new_status                           invoice_status NOT NULL,
                                                                 changed_at                           TIMESTAMP NOT NULL DEFAULT NOW(),
    changed_by                           VARCHAR(100),
    reason                               TEXT,
    created_at                           TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                           TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_inv_status_hist
    FOREIGN KEY (confirming_invoice_id)
    REFERENCES confirming_invoice (confirming_invoice_id)
    );
