-- auto Generated on 2019-04-10
DROP TABLE IF EXISTS c_task;
CREATE TABLE c_task(
	id VARCHAR (50) NOT NULL  COMMENT 'id',
	title VARCHAR (50)   COMMENT 'title',
	userid VARCHAR (50)   COMMENT 'userid',
	status VARCHAR (50)   COMMENT 'status',
	content LONGTEXT   COMMENT 'content',
	minsalary VARCHAR (50)   COMMENT 'minsalary',
	maxsalary VARCHAR (50)  COMMENT 'maxsalary',
	createtime VARCHAR (50)   COMMENT 'createtime',
	overtime VARCHAR (50)   COMMENT 'overtime',
	isdelete VARCHAR (50) NOT NULL DEFAULT '0' COMMENT 'isdelete',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'c_task';
