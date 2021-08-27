INSERT INTO demo_sql
(next_val)
VALUES('1');

INSERT INTO organization
(id, external_id, name, description, domain, enabled,  created_date_time)
VALUES(1, uuid(), 'org1', 'org1 desc', 'domain-a', 1,  CURRENT_TIMESTAMP);


INSERT INTO organization
(id, external_id, name, description, domain, enabled, created_date_time)
VALUES(2, uuid(), 'org2', 'org2 desc', 'domain-b', 1,CURRENT_TIMESTAMP);


INSERT INTO `user`
(id, external_id, first_name, last_name, email,phone_number, password, enabled, created_date_time, role_name)
VALUES('RG_0000000', uuid(), 'fn', 'ln', 'a@a.com', '1234567890', '$2a$10$EogLvC.tLhEnZ1OzaUF3g.Bt3QhWKiXnEEv5DZD0HReOuJJYXmIDe', 1, CURRENT_TIMESTAMP,'ROLE_SUPER_ADMIN');


INSERT INTO user_organizations
(user_id, org_id, external_id, created_date_time)
VALUES('RG_0000000', 1, uuid(), CURRENT_TIMESTAMP);

INSERT INTO user_organizations
(user_id, org_id, external_id, created_date_time)
VALUES('RG_0000000', 2, uuid(), CURRENT_TIMESTAMP);


INSERT INTO driver_information
(user_id,First_Name,Middle_Name,Last_Name,Drivers_Address,Date_Of_Birth,Gender,Relation,
Drivers_License_Id_No,Drivers_License_Expiration,Upload_Photo)
VALUES('RG_0000000','john2','','Mc','Chicago,IL','2000-01-01','M','SELF','DR-123-234-4567','2022-01-01','S3://');


