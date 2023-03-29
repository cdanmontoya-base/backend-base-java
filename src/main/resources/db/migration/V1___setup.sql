CREATE TABLE accounts
(
    id  uuid PRIMARY KEY,
    email VARCHAR
);

CREATE TABLE cellphones (
  account_id uuid NOT NULL,
  number VARCHAR(20) NOT NULL,

  CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

COMMENT ON TABLE accounts IS 'Stores all accounts';
COMMENT ON COLUMN accounts.id IS 'Unique identifier for each account';
COMMENT ON COLUMN accounts.email IS 'Account email';

COMMENT ON TABLE cellphones IS 'Cellphone numbers owned by an account';
COMMENT ON COLUMN cellphones.account_id IS 'Account to whom the cellphone number belongs';
COMMENT ON COLUMN cellphones.number IS 'Cellphone number';