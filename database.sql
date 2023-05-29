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

create table questions (
question_id int primary key unique not null AUTO_INCREMENT,
title varchar(100) not null,
user_id int not null,
content varchar(1000) not null,
creation_date timestamp default current_timestamp not null,
picture varchar(256),
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table list_of_tags (
question_id int not null,
tag varchar(15) not null,
PRIMARY KEY (question_id, tag),
FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

create table question_votes (
question_id int not null,
user_id int not null,
positive_vote boolean,
PRIMARY KEY (question_id, user_id),
FOREIGN KEY (question_id) REFERENCES questions(question_id),
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table answers (
answer_id int primary key unique not null AUTO_INCREMENT,
question_id int not null,
user_id int not null,
content varchar(1000) not null,
creation_date timestamp default current_timestamp not null,
picture varchar(256),
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (question_id) REFERENCES questions(question_id)
);

create table answer_votes (
answer_id int not null,
user_id int not null,
positive_vote boolean,
PRIMARY KEY (answer_id, user_id),
FOREIGN KEY (answer_id) REFERENCES answers(answer_id),
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

insert into users (first_name, last_name, e_mail, pass, picture) values
( "Alexandru", "RT", "test1@yahoo.com", "parola", "https://avatars.githubusercontent.com/u/50906589?v=4" );
insert into users (first_name, last_name, e_mail, pass, phone, banned) values
( "Sergiu", "RT", "test2@yahoo.com", "parola2", "0000000000", true );
insert into users (first_name, last_name, e_mail, pass, moderator) values
( "Cornelia", "RT", "test3@yahoo.com", "parola2", true );

insert into questions (user_id, content, title) values
(2, 'QUESTION Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'TestTitle'), (1, 'TestContent3', 'TestTitle3');

insert into question_votes values
(1, 1, false);

insert into list_of_tags values 
( 1, 'Test1'), (1, 'Test2'), (1, 'Test3'), (2, 'Test2'), (2, 'Test4');

insert into answers (user_id, content, picture, question_id) values
(1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', "https://images.alphacoders.com/127/thumb-1920-1272163.jpg", 1);
insert into answers (user_id, content, question_id) values
(2, '2 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 1);

insert into answer_votes values
(2, 2, true), (2, 3, true);

select * from answers