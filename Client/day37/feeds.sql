CREATE database csf_day37_railway;

USE csf_day37_railway;

CREATE TABLE posts (
  post_id char(8) NOT NULL,
  picture mediumblob,
  title varchar(32) NOT NULL,
  comment varchar(128) DEFAULT NULL,
  comment2 varchar(128) DEFAULT NULL,
  comment3 varchar(128) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
  );