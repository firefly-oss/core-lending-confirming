-- V1 - CREATE ENUMS FOR CONFIRMING (REVERSE FACTORING)

-- confirming_agreement -> agreement_status
CREATE TYPE agreement_status AS ENUM (
    'ACTIVE',
    'CLOSED',
    'SUSPENDED',
    'TERMINATED'
);

-- confirming_invoice -> currency_code
CREATE TYPE currency_code AS ENUM (
    'EUR',
    'USD',
    'GBP',
    'CHF'
);

-- confirming_invoice -> invoice_status
CREATE TYPE invoice_status AS ENUM (
    'CONFIRMED',
    'EARLY_PAID',
    'SETTLED',
    'CANCELED',
    'DISPUTED',
    'DEFAULTED'
);

-- confirming_fee -> fee_type
CREATE TYPE fee_type AS ENUM (
    'DISCOUNT_FEE',
    'ADMIN_FEE',
    'SERVICE_FEE',
    'LATE_FEE',
    'EARLY_PAYMENT_FEE'
);