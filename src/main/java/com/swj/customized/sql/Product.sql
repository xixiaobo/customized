-- auto Generated on 2019-04-05
DROP TABLE IF EXISTS c_product;
CREATE TABLE c_product(
	id VARCHAR (50) NOT NULL  COMMENT 'id',
	productname VARCHAR (50)  COMMENT 'productname',
	classid INT (11)   COMMENT 'classid',
	userid VARCHAR (50)   COMMENT 'userid',
	productabstract VARCHAR (255)   COMMENT 'productabstract',
	content LONGTEXT  COMMENT 'content',
	default_image LONGTEXT   COMMENT 'defaultImage',
	createtime VARCHAR (50) NOT NULL COMMENT 'createtime',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'c_product';
