DROP DATABASE IF EXISTS userlogindb;
CREATE DATABASE userlogindb DEFAULT CHARACTER SET utf8;
USE userlogindb;

##创建用户表
CREATE TABLE table_user (
   user_id   INT AUTO_INCREMENT PRIMARY KEY,
   user_name VARCHAR(30),
   password  VARCHAR(32),
   credits INT,
   last_visit datetime,
   last_ip  VARCHAR(23)
)ENGINE=InnoDB; 

##创建用户登录日志表
CREATE TABLE table_login_log (
   login_log_id  INT AUTO_INCREMENT PRIMARY KEY,
   user_id   INT,
   ip  VARCHAR(23),
   login_datetime datetime
)ENGINE=InnoDB; 

##插入初始化数据
INSERT INTO table_user (user_name, password) 
             VALUES('admin', '123456');
COMMIT;