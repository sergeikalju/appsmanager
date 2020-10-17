INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App1', 1, 'JAVA', 'App1 description', 100.23, '2020-10-12 10:00');
INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App2', 1, 'PHP', 'App2 description', 222.22, '2020-04-12 10:00');
INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App3', 1, 'BOX', 'App3 description', 23.23, '2020-10-12 10:00');
INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App4', 2, 'PHP', 'App4 description', 3443.22, '2020-04-12 10:00');
INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App5', 2, 'OS_COMPONENT', 'App5 description', 345.23, '2020-10-12 10:00');
INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App6', 2, 'OS_COMPONENT', 'App6 description', 2223242.22, '2020-11-12 10:00');
INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App7', 3, 'JAVA', 'App7 description', 234234.23, '2020-10-14 10:00');
INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App8', 4, 'DATABASE_ENGINE', 'App8 description', 235.25, '2020-04-23 10:00');
INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App9', 4, 'JAVA', 'App9 description', 235.263, '2020-06-12 10:00');
INSERT INTO applications (name,	app_group, app_type, description, app_cost, last_modified) 
	VALUES ('App10', 5, 'DATABASE_ENGINE', 'App10 description', 23.262, '2019-04-12 10:00');
	
	
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (1, 'service1', 'HTTP', 'REST', 'service 1 description', '2020-04-12 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (2, 'service2', 'SSH', 'REST', 'service 2 description', '2020-05-12 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (1, 'service3', 'SSH', 'SOAP', 'service 3 description', '2020-06-12 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (1, 'service4', 'HTTP', 'REST', 'service 4 description', '2020-04-12 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (2, 'service5', 'JDBC', 'REST', 'service 5 description', '2020-05-13 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (3, 'service6', 'SSH', 'SOAP', 'service 6 description', '2020-06-19 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (3, 'service7', 'HTTP', 'REST', 'service 7 description', '2020-04-18 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (4, 'service8', 'SSH', 'REST', 'service 8 description', '2020-05-16 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (4, 'service9', 'JDBC', 'SOAP', 'service 9 description', '2020-06-11 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (5, 'service10', 'ODBC', 'REST', 'service 10 description', '2020-04-14 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (2, 'servic11', 'SSH', 'REST', 'service11 description', '2020-03-15 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (3, 'service12', 'SAML', 'SOAP', 'service 12 description', '2020-12-12 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (1, 'service13', 'HTTP', 'REST', 'service 13 description', '2020-08-12 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (2, 'service14', 'JDBC', 'REST', 'service 14 description', '2020-02-12 10:00');
INSERT INTO services (app_code,	name, type, sub_type, description, last_modified) 
	VALUES (1, 'service15', 'ODBC', 'SOAP', 'service 15 description', '2018-06-12 10:00');