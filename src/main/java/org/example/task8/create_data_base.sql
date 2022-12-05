CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;
DROP TABLE IF EXISTS  people;
CREATE TABLE people
(
    id INT  UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    surname VARCHAR(255),
    birthdate CHAR(8),
    birthplace VARCHAR(255),
    salary DECIMAL(10,2),
    status VARCHAR(255)
);
INSERT INTO People(firstname,surname,birthdate,birthplace,salary,status)
VALUES 
   ('John','Do', '12.02.99', 'Canada', 50000, 'SINGLE'),
   ('Salim','Salem','30.08.97', 'Morocco', 65000, 'MARRIED'),
   ('Yuri','Puturin','01.01.76', 'Russia', 70000, 'DIVORCED'),
   ('Juan','De La Cruiz', '25.11.01', 'Mexico',55000, 'MARRIED');
