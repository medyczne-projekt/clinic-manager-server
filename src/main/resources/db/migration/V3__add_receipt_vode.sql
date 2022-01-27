ALTER TABLE receipts ADD code VARCHAR(100);
UPDATE receipts SET code='' WHERE code IS NULL;