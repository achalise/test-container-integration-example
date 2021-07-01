-- CREATE SCHEMA test;
USE test;
CREATE TABLE transaction
(
   id VARCHAR(64) NOT NULL,
   name VARCHAR(256),
   description VARCHAR(256),
   PRIMARY KEY (`id`)
)