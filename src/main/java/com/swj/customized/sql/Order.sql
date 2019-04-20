create table c_classify
(
	id         int auto_increment comment 'id'
		primary key,
	classname  varchar(50) default '' not null comment 'classname',
	classnum   int         default -1 not null comment 'classnum',
	createtime varchar(50) default '' not null comment 'createtime'
)
	comment 'c_classify' charset = utf8mb4;

create table c_image
(
	id          int auto_increment comment 'id'
		primary key,
	productid   varchar(50) default '' not null comment 'productid',
	createtime  varchar(50) default '' not null comment 'createtime',
	imagebase64 longtext               not null comment 'imagebase64'
)
	comment 'c_image' charset = utf8mb4;

create table c_message
(
	id         int auto_increment comment 'id'
		primary key,
	productid  varchar(50) default '' not null comment 'productid',
	userid     varchar(50) default '' not null comment 'userid',
	content    longtext               not null comment 'content',
	createtime varchar(50) default '' not null comment 'createtime'
)
	comment 'c_message' charset = utf8mb4;

create table c_order
(
	id              varchar(50)             not null comment 'id'
		primary key,
	taskid          varchar(50) default ''  not null comment 'taskid',
	orderuserid     varchar(50) default ''  not null comment 'orderuserid',
	status          varchar(50) default ''  not null comment 'status',
	receipt_explain longtext                null comment 'receipt_explain',
	createtime      varchar(50) default ''  not null comment 'createtime',
	overtime        varchar(50) default ''  not null comment 'overtime',
	isdelete        varchar(50) default '0' not null comment 'isdelete'
)
	comment 'c_order' charset = utf8mb4;

create table c_product
(
	id              varchar(50)   not null comment 'id'
		primary key,
	productname     varchar(50)   not null comment 'productname',
	classid         int           not null comment 'classid',
	userid          varchar(50)   not null comment 'userid',
	productabstract varchar(255)  null comment 'productabstract',
	content         longtext      null comment 'content',
	default_image   longtext      null comment 'defaultImage',
	createtime      varchar(50)   null comment 'createtime',
	productscore    double(16, 4) null comment 'productscore',
	productscorenum int           null comment 'productscorenum'
)
	comment 'c_product' charset = utf8mb4;

create table c_product_score
(
	id         int auto_increment
		primary key,
	product_id varchar(50)    null,
	user_id    varchar(255)   null,
	score      double(255, 2) null
);

create table c_task
(
	id           varchar(50)             not null comment 'id'
		primary key,
	title        varchar(50)             null comment 'title',
	userid       varchar(50)             null comment 'userid',
	status       varchar(50)             null comment 'status',
	content      longtext                null comment 'content',
	minsalary    varchar(50)             null comment 'minsalary',
	maxsalary    varchar(50)             null comment 'maxsalary',
	createtime   varchar(50)             null comment 'createtime',
	overtime     varchar(50)             null comment 'overtime',
	isdelete     varchar(50) default '0' not null comment 'isdelete',
	classid      int                     null comment 'classid',
	taskdeadline varchar(50)             not null comment 'taskdeadline'
)
	comment 'c_task' charset = utf8mb4;

create table c_users
(
	id         varchar(50)             not null comment 'id'
		primary key,
	username   varchar(50)  default '' not null comment 'username',
	password   varchar(255) default '' not null comment 'password',
	userimg    longtext                not null comment 'userimg',
	realname   varchar(50)  default '' not null comment 'realname',
	phone      varchar(50)  default '' not null comment 'phone',
	email      varchar(50)  default '' not null comment 'email',
	role       varchar(50)  default '' not null comment 'role',
	createtime varchar(50)  default '' not null comment 'createtime'
)
	comment 'c_users' charset = utf8mb4;

