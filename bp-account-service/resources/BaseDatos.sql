
CREATE TABLE IF NOT EXISTS account (
	id bigserial NOT NULL,
	number bigint NOT NULL,
	type varchar(50) NOT NULL,
	balance bigint NOT NULL,
	client_id bigint NOT NULL,
	client_name varchar(200) NOT NULL,
	status bool NOT NULL,
	CONSTRAINT account_pk PRIMARY KEY (id),
	CONSTRAINT ct_account_number_type UNIQUE (number, type)
);

CREATE TABLE IF NOT EXISTS movement (
	id bigserial NOT NULL,
	initial_balance bigint NOT NULL,
	amount bigint NOT NULL,
	account_id bigint NOT NULL,
	date Timestamp NOT NULL,
	CONSTRAINT movement_pk PRIMARY KEY (id)
);

ALTER TABLE movement ADD CONSTRAINT movement_fk FOREIGN KEY (account_id) REFERENCES account(id);
