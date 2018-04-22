-- remote
create user bulksms@localhost identified by 'bulksms@123';
create user bulksms@'%' identified by 'bulksms@123';
create database bulksms;
grant all on bulksms.* to bulksms@localhost;
grant all on bulksms.* to bulksms@'%';
-- set global general_log=1;