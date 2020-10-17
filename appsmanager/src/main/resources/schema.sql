DROP DATABASE IF EXISTS appsmanager;
CREATE DATABASE appsmanager;

DROP TABLE IF EXISTS applications CASCADE;
CREATE TABLE applications(
    app_code SERIAL PRIMARY KEY,
    name VARCHAR(50),
	app_group INT,
	app_type VARCHAR(50),
	description VARCHAR(50),
	app_cost NUMERIC(10, 2),
	last_modified TIMESTAMP
);

DROP TABLE IF EXISTS services;
CREATE TABLE services(
    app_code INT,
    service_code SERIAL PRIMARY KEY,
    name VARCHAR(50),
	type VARCHAR(50),
	sub_type VARCHAR(50),
	description VARCHAR(50),
	last_modified TIMESTAMP,
	FOREIGN KEY (app_code) REFERENCES applications (app_code) ON DELETE CASCADE ON UPDATE CASCADE
);
