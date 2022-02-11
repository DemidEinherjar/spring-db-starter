CREATE TABLE IF NOT EXISTS drivers(
    license VARCHAR(10) PRIMARY KEY NOT NULL,
    name VARCHAR(30),
    birthday DATE
    );

CREATE TABLE cars(
	vin UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	model VARCHAR(50),
	srp VARCHAR(10),
	owner varchar(10) ,
	yi INT,
	color VARCHAR(30),
	FOREIGN KEY (owner)  REFERENCES drivers (license) ON DELETE SET NULL
	);