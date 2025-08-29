-- V3 - CASTS USING "WITH INOUT AS IMPLICIT" FOR ALL ENUM TYPES

-------------------------
-- agreement_status
-------------------------
CREATE CAST (varchar AS agreement_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- currency_code
-------------------------
CREATE CAST (varchar AS currency_code)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- invoice_status
-------------------------
CREATE CAST (varchar AS invoice_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- fee_type
-------------------------
CREATE CAST (varchar AS fee_type)
    WITH INOUT
    AS IMPLICIT;