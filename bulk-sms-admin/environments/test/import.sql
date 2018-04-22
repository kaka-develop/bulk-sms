
-- Default data with format, which is suitable with hibernate
-- Insert partners
INSERT INTO Partner(about,brand_name,created_date,name,trigger_deliver_api) VALUES ('about admin','admin brand','2016-10-9 00:00:00','admin','admin api'), ('about2','brand2','2016-10-9 00:00:00','name2','api2'), ('about3','brand3','2016-10-9 00:00:00','name3','api3');

-- Insert partner_admin_role
INSERT INTO partner_admin_role(created_date,description,role) VALUES ('2016-10-9 00:00:00','PARTNER_ADMIN',1), ('2016-10-9 00:00:00','USER',2);

-- Insert account token
INSERT INTO partner_token(created_date,token) VALUES('2016-09-26 00:00:00','11da75c4-4832-4ea9-8b6f-b57ad662f12f'), ('2016-09-26 00:00:00','11da75c4-4832-sas12-8b6f-b57ad662f12f'), ('2016-09-26 00:00:00','11da75c4-4832-4ea9-8b6f-2131asas121');

-- Insert partner_admin_account
INSERT INTO partner_admin_account(info,email,password,username,phone_number, partner_id,partner_admin_role_id,created_date,config_notify_by_app,config_notify_by_email,config_notify_by_phone,partner_token_id,enable, avatar) VALUES ('admin info','admin@gmail.com','admin','admin username','0123456789',1,1,'2016-10-9 00:00:00',0,1,0,1,TRUE,'avatar.jpg'), ('user info','user@gmail.com','user','username2','phone2',1,2,'2016-10-9 00:00:00',0,1,0,2,FALSE,'avatar.jpg'), ('user info','email3','pass3','username3','phone3',2,2,'2016-10-9 00:00:00',0,1,0,3,FALSE,'avatar.jpg'), ('young kaka info','youngoptimalkaka@gmail.com','kaka','kaka username','0123456789',1,1,'2016-10-9 00:00:00',0,1,0,1,TRUE,'avatar.jpg');

-- Insert partner_group_contact
INSERT INTO  partner_group_contact(name,info,created_date,partner_id) VALUES  ('name1','info1','2016-10-9 00:00:00',1), ('name2','info2','2016-10-9 00:00:00',1), ('name2','info3','2016-10-9 00:00:00',2);

-- Insert partner_contact
INSERT INTO partner_contact(phone_number,info,created_date,partner_id) VALUES ('phone1','info1','2016-10-9 00:00:00',1), ('phone2','info2','2016-10-9 00:00:00',1), ('phone3','info3','2016-10-9 00:00:00',2);

-- Insert group_n_contact
INSERT INTO group_n_contact(partner_contact_id,partner_group_contact_id,created_date) VALUES (1,1,'2014-02-20'), (2,2,'2014-02-20'),(1,2,'2014-02-20');


INSERT INTO Bundle(created_date,price,quantity, renewal_date, name) VALUES ('2016-09-26 00:00:00','1000', '500', '2016-09-26 00:00:00', 'Bundle 1'), ('2016-09-26 00:00:00','2000', '1000', '2016-09-26 00:00:00', 'Bundle 2');

INSERT INTO bundle_history(bundle_id,partner_id,created_date) VALUES (1, 1, '2016-10-9 00:00:00'), (1, 2, '2016-10-9 00:00:00');


INSERT INTO bundle_extend(bundle_id,partner_id,state,updated_date) VALUES (1, 1, 'RENEWAL_WAITING', '2016-10-9 00:00:00'), (1, 2, 'RENEWAL_WAITING', '2016-10-9 00:00:00');


-- Insert blacklist_numbers
INSERT INTO blacklist_numbers(created_date,number,partner_id) VALUES ('2016-09-26 00:00:00','0123456789',1), ('2016-09-26 00:00:00','9123456780',1),('2016-09-26 00:00:00','0123456789',2);
