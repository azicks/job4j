create table product(id serial primary key,
					 name varchar(2000), 
					 type_id int,
					 expired_date date,
					 price money);
					 
create table type(id serial primary key, name varchar(2000));

insert into type (name) values ('СЫР'),('МОЛОКО');

select * from type;

insert into product (name, type_id, expired_date, price) values
					('Мороженое', 2, '2020-02-01', 50),
					('Пармезан', 1, '2020-12-01', 300);
					
select * from product;
--------------------------------------------------------------
-- 1.
select * from product where type_id = (select id from type where name = 'СЫР');

-- 2.
select * from product where name like 'Мороженое';

-- 3.
select * from product where extract(month from expired_date) = extract(month from now()) + 1;

-- 4.
select * from product order by price desc limit 1;

-- 5.
select * from product where type_id = (select id from type where name = 'СЫР') or 
							type_id = (select id from type where name = 'МОЛОКО');
-- 6.
-- Не понял, в таблице нет поля количества продуктов, напишу запрос на вывод типа продуктов дешевле 100
select t.name from product as p
inner join type as t on p.type_id = t.id and p.price < '100';

-- 7.
select * from product as p 
full join type as t on t.id = p.type_id;
