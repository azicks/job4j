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
-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product where type_id = (select id from type where name = 'СЫР');

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where name like 'Мороженое';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product where extract(month from expired_date) = extract(month from now()) + 1;

-- 4. Написать запрос, который выводит самый дорогой продукт.
select * from product order by price desc limit 1;

-- 5.  Написать запрос, который выводит количество всех продуктов определенного типа.
select count(id) from product where type_id = (select id from type where name = 'СЫР') ; 

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product where type_id in (select id from type where name in ('СЫР', 'МОЛОКО'));

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
select name from type where id in (select type_id from product group by type_id having(count(*) < 10));

-- 8. Вывести все продукты и их тип.
select * from product as p 
full join type as t on t.id = p.type_id;
