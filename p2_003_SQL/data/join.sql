create table body (id serial primary key, name varchar(100));
create table engine (id serial primary key, name varchar(100), power smallint);
create table transmission (id serial primary key, name varchar(100), isAutomatic boolean);

insert into body (name) values ('Седан'),('Хардтоп'),('Купе');
insert into transmission (name, isAutomatic) values ('A540H', true),('C160', false);
insert into engine (name, power) values ('3S-FE', 145),('3S-GE',170),('3S-GTE',280);

create table car (id serial primary key, 
				  name varchar(100),
				  body_id int references body(id),
				  engine_id int references body(id),
				  transmission_id int references body(id));
				  
insert into car (name, body_id, engine_id, transmission_id) values
				('Carina ED', 2, 2, 1),
				('Celica Gt-Four', 3, 3, 2);
-- 1.
select c.name as "Car", b.name as "Body", e.name as "Engine", t.name as "Transmission"
from car as c 
left outer join body as b on c.body_id = b.id
left outer join engine as e on c.engine_id = e.id
left outer join transmission as t on c.transmission_id = t.id;

-- 2.
select b.name as "Body", e.name as "Engine", t.name as "Transmission"
from car as c 
full outer join body as b on c.body_id = b.id
full outer join engine as e on c.engine_id = e.id
full outer join transmission as t on c.transmission_id = t.id
where c.id is null;

