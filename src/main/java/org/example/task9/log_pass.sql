CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;
DROP TABLE IF EXISTS  log_pass;
CREATE TABLE log_pass
(
    id INT  UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    log_in VARCHAR(255),
	passwrd VARCHAR(255)
);
INSERT INTO log_pass(log_in,passwrd)
VALUES
   ('root','root'),
   ('root1','root1'),
   ('root2','root2')