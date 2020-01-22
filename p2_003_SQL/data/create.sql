--create database db1;

create table rule (id serial primary key, rule_name varchar(100));
create table role (id serial primary key, role_name varchar(100));
create table role_rules (role_id int references role(id), 
						 rule_id int references rule(id));
create table users (id serial primary key, name varchar(2000),
				   role_id int references role(id));
create table category (id serial primary key, type int);
create table status (id serial primary key, status varchar(200));
create table item (
		id serial primary key, 
		item_name varchar (100),
		user_id int references users(id),
		category_id int references category(id),
		status_id int references status(id));
create table attachs (id serial primary key, item_id int references item(id));
create table commentos (id serial primary key,
						comment_text text,
					    item_id int references item(id));
						
						
				
insert into rule(rule_name) values ('Rule_1');
insert into role(role_name) values ('Role_1');
insert into users(name, role_id) values ('user 1', 
										 (select id from role where role_name = 'Role_1' limit 1));
insert into category (type) values (1);
insert into status (status) values ('Status_1');
insert into item (item_name, user_id, category_id, status_id) values ('Item_1', 1, 1,
														  (select id from status where status = 'Status_1' limit 1));
														  