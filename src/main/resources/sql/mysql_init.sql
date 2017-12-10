create database smallmin;

use database smallmin;

create table category(
	id int(8) NOT NULL primary key auto_increment,
	content varchar(50) NOT NULL
);

create table tag(
	id int(8) NOT NULL primary key auto_increment,
	content varchar(50) NOT NULL
);

create table topic(
	id int(8) NOT NULL primary key auto_increment,
	title varchar(200) NOT NULL,
	author_id int(8) NOT NULL,
	category_id int(8) NOT NULL,
	priority int(8) NOT NULL,
	edit_time timestamp NOT NULL 
);