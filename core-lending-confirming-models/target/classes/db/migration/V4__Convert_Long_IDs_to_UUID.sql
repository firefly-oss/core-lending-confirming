-- V4 - CONVERT ALL LONG ID FIELDS TO UUID
-- This migration converts all BIGSERIAL primary keys and BIGINT foreign keys to UUID

-- Enable UUID extension if not already enabled
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ========================================================================
-- STEP 1: Add new UUID columns alongside existing BIGINT columns
-- ========================================================================

-- confirming_agreement table
ALTER TABLE confirming_agreement
ADD COLUMN confirming_agreement_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN contract_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN customer_uuid UUID DEFAULT uuid_generate_v4();

-- confirming_supplier table
ALTER TABLE confirming_supplier
ADD COLUMN confirming_supplier_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN confirming_agreement_uuid UUID,
ADD COLUMN supplier_customer_uuid UUID DEFAULT uuid_generate_v4();

-- confirming_invoice table
ALTER TABLE confirming_invoice
ADD COLUMN confirming_invoice_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN confirming_agreement_uuid UUID,
ADD COLUMN confirming_supplier_uuid UUID,
ADD COLUMN document_reference_uuid UUID DEFAULT uuid_generate_v4();

-- confirming_advance table
ALTER TABLE confirming_advance
ADD COLUMN confirming_advance_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN confirming_invoice_uuid UUID,
ADD COLUMN transaction_uuid UUID DEFAULT uuid_generate_v4();

-- confirming_fee table
ALTER TABLE confirming_fee 
ADD COLUMN confirming_fee_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN confirming_agreement_uuid UUID;

-- confirming_invoice_settlement table
ALTER TABLE confirming_invoice_settlement
ADD COLUMN confirming_invoice_settlement_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN confirming_invoice_uuid UUID,
ADD COLUMN transaction_uuid UUID DEFAULT uuid_generate_v4();

-- confirming_invoice_status_history table
ALTER TABLE confirming_invoice_status_history 
ADD COLUMN confirming_invoice_status_history_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN confirming_invoice_uuid UUID;

-- ========================================================================
-- STEP 2: Populate UUID foreign key columns with corresponding UUIDs
-- ========================================================================

-- Update confirming_supplier foreign keys
UPDATE confirming_supplier cs 
SET confirming_agreement_uuid = ca.confirming_agreement_uuid
FROM confirming_agreement ca 
WHERE cs.confirming_agreement_id = ca.confirming_agreement_id;

-- Update confirming_invoice foreign keys
UPDATE confirming_invoice ci 
SET confirming_agreement_uuid = ca.confirming_agreement_uuid,
    confirming_supplier_uuid = cs.confirming_supplier_uuid
FROM confirming_agreement ca, confirming_supplier cs
WHERE ci.confirming_agreement_id = ca.confirming_agreement_id 
  AND ci.confirming_supplier_id = cs.confirming_supplier_id;

-- Update confirming_advance foreign keys
UPDATE confirming_advance ca 
SET confirming_invoice_uuid = ci.confirming_invoice_uuid
FROM confirming_invoice ci 
WHERE ca.confirming_invoice_id = ci.confirming_invoice_id;

-- Update confirming_fee foreign keys
UPDATE confirming_fee cf 
SET confirming_agreement_uuid = ca.confirming_agreement_uuid
FROM confirming_agreement ca 
WHERE cf.confirming_agreement_id = ca.confirming_agreement_id;

-- Update confirming_invoice_settlement foreign keys
UPDATE confirming_invoice_settlement cis 
SET confirming_invoice_uuid = ci.confirming_invoice_uuid
FROM confirming_invoice ci 
WHERE cis.confirming_invoice_id = ci.confirming_invoice_id;

-- Update confirming_invoice_status_history foreign keys
UPDATE confirming_invoice_status_history cish 
SET confirming_invoice_uuid = ci.confirming_invoice_uuid
FROM confirming_invoice ci 
WHERE cish.confirming_invoice_id = ci.confirming_invoice_id;

-- ========================================================================
-- STEP 3: Drop existing foreign key constraints
-- ========================================================================

ALTER TABLE confirming_supplier DROP CONSTRAINT IF EXISTS fk_supplier_agreement;
ALTER TABLE confirming_invoice DROP CONSTRAINT IF EXISTS fk_invoice_agreement;
ALTER TABLE confirming_invoice DROP CONSTRAINT IF EXISTS fk_invoice_supplier;
ALTER TABLE confirming_advance DROP CONSTRAINT IF EXISTS fk_advance_invoice;
ALTER TABLE confirming_fee DROP CONSTRAINT IF EXISTS fk_fee_agreement;
ALTER TABLE confirming_invoice_settlement DROP CONSTRAINT IF EXISTS fk_settlement_invoice;
ALTER TABLE confirming_invoice_status_history DROP CONSTRAINT IF EXISTS fk_inv_status_hist;

-- ========================================================================
-- STEP 4: Drop old BIGINT columns and rename UUID columns
-- ========================================================================

-- confirming_agreement
ALTER TABLE confirming_agreement DROP COLUMN confirming_agreement_id;
ALTER TABLE confirming_agreement DROP COLUMN contract_id;
ALTER TABLE confirming_agreement DROP COLUMN customer_id;
ALTER TABLE confirming_agreement RENAME COLUMN confirming_agreement_uuid TO confirming_agreement_id;
ALTER TABLE confirming_agreement RENAME COLUMN contract_uuid TO contract_id;
ALTER TABLE confirming_agreement RENAME COLUMN customer_uuid TO customer_id;

-- confirming_supplier
ALTER TABLE confirming_supplier DROP COLUMN confirming_supplier_id;
ALTER TABLE confirming_supplier DROP COLUMN confirming_agreement_id;
ALTER TABLE confirming_supplier DROP COLUMN supplier_customer_id;
ALTER TABLE confirming_supplier RENAME COLUMN confirming_supplier_uuid TO confirming_supplier_id;
ALTER TABLE confirming_supplier RENAME COLUMN confirming_agreement_uuid TO confirming_agreement_id;
ALTER TABLE confirming_supplier RENAME COLUMN supplier_customer_uuid TO supplier_customer_id;

-- confirming_invoice
ALTER TABLE confirming_invoice DROP COLUMN confirming_invoice_id;
ALTER TABLE confirming_invoice DROP COLUMN confirming_agreement_id;
ALTER TABLE confirming_invoice DROP COLUMN confirming_supplier_id;
ALTER TABLE confirming_invoice DROP COLUMN document_reference;
ALTER TABLE confirming_invoice RENAME COLUMN confirming_invoice_uuid TO confirming_invoice_id;
ALTER TABLE confirming_invoice RENAME COLUMN confirming_agreement_uuid TO confirming_agreement_id;
ALTER TABLE confirming_invoice RENAME COLUMN confirming_supplier_uuid TO confirming_supplier_id;
ALTER TABLE confirming_invoice RENAME COLUMN document_reference_uuid TO document_reference;

-- confirming_advance
ALTER TABLE confirming_advance DROP COLUMN confirming_advance_id;
ALTER TABLE confirming_advance DROP COLUMN confirming_invoice_id;
ALTER TABLE confirming_advance DROP COLUMN transaction_id;
ALTER TABLE confirming_advance RENAME COLUMN confirming_advance_uuid TO confirming_advance_id;
ALTER TABLE confirming_advance RENAME COLUMN confirming_invoice_uuid TO confirming_invoice_id;
ALTER TABLE confirming_advance RENAME COLUMN transaction_uuid TO transaction_id;

-- confirming_fee
ALTER TABLE confirming_fee DROP COLUMN confirming_fee_id;
ALTER TABLE confirming_fee DROP COLUMN confirming_agreement_id;
ALTER TABLE confirming_fee RENAME COLUMN confirming_fee_uuid TO confirming_fee_id;
ALTER TABLE confirming_fee RENAME COLUMN confirming_agreement_uuid TO confirming_agreement_id;

-- confirming_invoice_settlement
ALTER TABLE confirming_invoice_settlement DROP COLUMN confirming_invoice_settlement_id;
ALTER TABLE confirming_invoice_settlement DROP COLUMN confirming_invoice_id;
ALTER TABLE confirming_invoice_settlement DROP COLUMN transaction_id;
ALTER TABLE confirming_invoice_settlement RENAME COLUMN confirming_invoice_settlement_uuid TO confirming_invoice_settlement_id;
ALTER TABLE confirming_invoice_settlement RENAME COLUMN confirming_invoice_uuid TO confirming_invoice_id;
ALTER TABLE confirming_invoice_settlement RENAME COLUMN transaction_uuid TO transaction_id;

-- confirming_invoice_status_history
ALTER TABLE confirming_invoice_status_history DROP COLUMN confirming_invoice_status_history_id;
ALTER TABLE confirming_invoice_status_history DROP COLUMN confirming_invoice_id;
ALTER TABLE confirming_invoice_status_history RENAME COLUMN confirming_invoice_status_history_uuid TO confirming_invoice_status_history_id;
ALTER TABLE confirming_invoice_status_history RENAME COLUMN confirming_invoice_uuid TO confirming_invoice_id;

-- ========================================================================
-- STEP 5: Add primary key constraints for UUID columns
-- ========================================================================

ALTER TABLE confirming_agreement ADD CONSTRAINT pk_confirming_agreement PRIMARY KEY (confirming_agreement_id);
ALTER TABLE confirming_supplier ADD CONSTRAINT pk_confirming_supplier PRIMARY KEY (confirming_supplier_id);
ALTER TABLE confirming_invoice ADD CONSTRAINT pk_confirming_invoice PRIMARY KEY (confirming_invoice_id);
ALTER TABLE confirming_advance ADD CONSTRAINT pk_confirming_advance PRIMARY KEY (confirming_advance_id);
ALTER TABLE confirming_fee ADD CONSTRAINT pk_confirming_fee PRIMARY KEY (confirming_fee_id);
ALTER TABLE confirming_invoice_settlement ADD CONSTRAINT pk_confirming_invoice_settlement PRIMARY KEY (confirming_invoice_settlement_id);
ALTER TABLE confirming_invoice_status_history ADD CONSTRAINT pk_confirming_invoice_status_history PRIMARY KEY (confirming_invoice_status_history_id);

-- ========================================================================
-- STEP 6: Add foreign key constraints for UUID columns
-- ========================================================================

ALTER TABLE confirming_supplier
ADD CONSTRAINT fk_supplier_agreement
FOREIGN KEY (confirming_agreement_id)
REFERENCES confirming_agreement (confirming_agreement_id);

ALTER TABLE confirming_invoice
ADD CONSTRAINT fk_invoice_agreement
FOREIGN KEY (confirming_agreement_id)
REFERENCES confirming_agreement (confirming_agreement_id);

ALTER TABLE confirming_invoice
ADD CONSTRAINT fk_invoice_supplier
FOREIGN KEY (confirming_supplier_id)
REFERENCES confirming_supplier (confirming_supplier_id);

ALTER TABLE confirming_advance
ADD CONSTRAINT fk_advance_invoice
FOREIGN KEY (confirming_invoice_id)
REFERENCES confirming_invoice (confirming_invoice_id);

ALTER TABLE confirming_fee
ADD CONSTRAINT fk_fee_agreement
FOREIGN KEY (confirming_agreement_id)
REFERENCES confirming_agreement (confirming_agreement_id);

ALTER TABLE confirming_invoice_settlement
ADD CONSTRAINT fk_settlement_invoice
FOREIGN KEY (confirming_invoice_id)
REFERENCES confirming_invoice (confirming_invoice_id);

ALTER TABLE confirming_invoice_status_history
ADD CONSTRAINT fk_inv_status_hist
FOREIGN KEY (confirming_invoice_id)
REFERENCES confirming_invoice (confirming_invoice_id);

-- ========================================================================
-- STEP 7: Set NOT NULL constraints where appropriate
-- ========================================================================

ALTER TABLE confirming_agreement ALTER COLUMN confirming_agreement_id SET NOT NULL;
ALTER TABLE confirming_agreement ALTER COLUMN contract_id SET NOT NULL;
ALTER TABLE confirming_agreement ALTER COLUMN customer_id SET NOT NULL;
ALTER TABLE confirming_supplier ALTER COLUMN confirming_supplier_id SET NOT NULL;
ALTER TABLE confirming_supplier ALTER COLUMN confirming_agreement_id SET NOT NULL;
ALTER TABLE confirming_supplier ALTER COLUMN supplier_customer_id SET NOT NULL;
ALTER TABLE confirming_invoice ALTER COLUMN confirming_invoice_id SET NOT NULL;
ALTER TABLE confirming_invoice ALTER COLUMN confirming_agreement_id SET NOT NULL;
ALTER TABLE confirming_invoice ALTER COLUMN confirming_supplier_id SET NOT NULL;
ALTER TABLE confirming_invoice ALTER COLUMN document_reference SET NOT NULL;
ALTER TABLE confirming_advance ALTER COLUMN confirming_advance_id SET NOT NULL;
ALTER TABLE confirming_advance ALTER COLUMN confirming_invoice_id SET NOT NULL;
ALTER TABLE confirming_advance ALTER COLUMN transaction_id SET NOT NULL;
ALTER TABLE confirming_fee ALTER COLUMN confirming_fee_id SET NOT NULL;
ALTER TABLE confirming_fee ALTER COLUMN confirming_agreement_id SET NOT NULL;
ALTER TABLE confirming_invoice_settlement ALTER COLUMN confirming_invoice_settlement_id SET NOT NULL;
ALTER TABLE confirming_invoice_settlement ALTER COLUMN confirming_invoice_id SET NOT NULL;
ALTER TABLE confirming_invoice_settlement ALTER COLUMN transaction_id SET NOT NULL;
ALTER TABLE confirming_invoice_status_history ALTER COLUMN confirming_invoice_status_history_id SET NOT NULL;
ALTER TABLE confirming_invoice_status_history ALTER COLUMN confirming_invoice_id SET NOT NULL;
