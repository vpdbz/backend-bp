
CREATE TABLE IF NOT EXISTS client (
	id bigserial NOT NULL,
	"name" varchar(200) NOT NULL,
	gender varchar(50) NOT NULL,
	age int4 NOT NULL,
	address varchar(300) NOT NULL,
	phone varchar(15) NOT NULL,
	"password" varchar(40) NOT NULL,
	status bool NOT NULL,
	CONSTRAINT client_pk PRIMARY KEY (id),
	CONSTRAINT ct_client_name UNIQUE (name)
);
