-- Create customers
insert into customer (first_name, last_name)
values ('Barack', 'Obama');

insert into customer (first_name, last_name)
values ('Mr', 'Bean');

insert into customer (first_name, last_name)
values ('Chuck', 'Norris');

insert into customer (first_name, last_name)
values ('John', 'Cena');

-- Create Transactions
insert into transactions (amount, customer_id, date)
values (100,
        (select customer_id from customer where first_name = 'Barack' and last_name = 'Obama'),
        '2021-04-01');

insert into transactions (amount, customer_id, date)
values (200,
        (select customer_id from customer where first_name = 'Barack' and last_name = 'Obama'),
        '2021-04-03');

insert into transactions (amount, customer_id, date)
values (10,
        (select customer_id from customer where first_name = 'Barack' and last_name = 'Obama'),
        '2021-04-12');

insert into transactions (amount, customer_id, date)
values (1283,
        (select customer_id from customer where first_name = 'Barack' and last_name = 'Obama'),
        '2021-04-17');

insert into transactions (amount, customer_id, date)
values (123,
        (select customer_id from customer where first_name = 'Barack' and last_name = 'Obama'),
        '2021-12-01');

insert into transactions (amount, customer_id, date)
values (99,
        (select customer_id from customer where first_name = 'Barack' and last_name = 'Obama'),
        '2021-7-12');

insert into transactions (amount, customer_id, date)
values (50,
        (select customer_id from customer where first_name = 'John' and last_name = 'Cena'),
        '2021-07-01');

insert into transactions (amount, customer_id, date)
values (27,
        (select customer_id from customer where first_name = 'John' and last_name = 'Cena'),
        '2021-07-01');

insert into transactions (amount, customer_id, date)
values (123,
        (select customer_id from customer where first_name = 'John' and last_name = 'Cena'),
        '2021-11-01');

insert into transactions (amount, customer_id, date)
values (98,
        (select customer_id from customer where first_name = 'John' and last_name = 'Cena'),
        '2021-03-01');

insert into transactions (amount, customer_id, date)
values (991,
        (select customer_id from customer where first_name = 'Mr' and last_name = 'Bean'),
        '2021-01-11');

insert into transactions (amount, customer_id, date)
values (2,
        (select customer_id from customer where first_name = 'Chuck' and last_name = 'Norris'),
        '2021-07-12');







