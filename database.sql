drop schema if exists sdAssignmentRTX ;
create schema sdAssignmentRTX;
use sdAssignmentRTX;

create table users (
user_id int primary key unique not null AUTO_INCREMENT,
first_name varchar(50) not null,
last_name varchar(50) not null,
e_mail varchar(128) unique not null,
pass varchar(64) not null,
phone varchar(15),
picture varchar(256) not null default 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png',
moderator boolean default false,
banned boolean default false
);

create table posts (
post_id int primary key unique not null AUTO_INCREMENT,
user_id int not null,
title varchar(100) not null,
content varchar(1000) not null,
creation_date timestamp default current_timestamp not null,
picture varchar(256),
question boolean default false
);

create table list_of_answers (
question_id int not null,
answer_id int not null,
PRIMARY KEY (question_id, answer_id)
);

create table list_of_tags (
question_id int not null,
tag varchar(15) not null,
PRIMARY KEY (question_id, tag)
);

create table votes (
post_id int not null,
user_id int not null,
positive_vote boolean,
PRIMARY KEY (post_id, user_id)
);

insert into users (first_name, last_name, e_mail, pass) values
( "Alexandru", "Radu-Todor", "test1@yahoo.com", "parola" );
insert into users (first_name, last_name, e_mail, pass, phone) values
( "Sergiu", "Radu-Todor", "test2@yahoo.com", "parola2", "0000000000" );
insert into users (first_name, last_name, e_mail, pass, moderator) values
( "Cornelia", "Radu-Todor", "test3@yahoo.com", "parola2", true );

insert into list_of_tags values 
( 1, 'Test1'), (1, 'Test2'), (2, 'Test3'), (3, 'Test4');

insert into posts (user_id, title, content, question) values
(2, 'TestTitle', 'TestContent', true), (1, 'TestTitle2', 'TestContent2', false), (1, 'TestTitle3', 'TestContent3', true);

insert into list_of_answers values
(3, 2);

insert into votes values
(2, 1, true), (2, 3, true), (1, 1, false);

select * from posts