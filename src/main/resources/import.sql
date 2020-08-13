drop table if exists products;
drop table if exists category;
drop table if exists orders;
drop table if exists addresses;
drop table if exists users;
drop table if exists delivery_methods;
drop table if exists delivery_statuses;
drop table if exists payment_methods;
drop table if exists payment_statuses;
drop table if exists order_items;
create table category(id int primary key auto_increment, title varchar(64));
create table delivery_statuses(id int primary key auto_increment, title varchar(64));
create table payment_statuses(id int primary key auto_increment, title varchar(64));
create table delivery_methods(id int primary key auto_increment, title varchar(64));
create table payment_methods(id int primary key auto_increment, title varchar(64));
create table users(id int primary key auto_increment, name varchar(64), surname varchar(64), birthday varchar(64), email varchar(64), password varchar(64), role varchar(64));
create table addresses(id int primary key auto_increment, country varchar(64), city varchar(64), street varchar(64), postcode int, building int, apart_number int, user int, foreign key(user) references users(id) on delete set null);
create table orders(id int primary key auto_increment, user int, address varchar(128), payment_method int, delivery_method int, payment_status int, delivery_status int, foreign key(user) references users(id) on delete set null, foreign key(payment_method) references payment_methods(id) on delete set null,foreign key(delivery_method) references delivery_methods(id) on delete set null,foreign key(payment_status) references payment_statuses(id) on delete set null,foreign key(delivery_status) references delivery_statuses(id) on delete set null);
create table products(id int primary key auto_increment,title varchar(128),price double, category int,count int, brand varchar(64), color varchar(64), weight double, diagonal double, storage double, ram int, foreign key(category) references category(id) on delete set null);
create table order_items(id int primary key auto_increment, order_id int, title varchar(128), price double, category varchar(64), count int, brand varchar(64), color varchar(64), weight double, diagonal double, storage double, ram int);
insert into category values (1, "For students"), (2, "Gaming"), (3, "Netbook");
insert into delivery_methods values (1, "Pickup"), (2, "Courier");
insert into delivery_statuses values (1, "Waiting for payment"), (2, "Waiting for loading"), (3, "Loaded"), (4, "Delivered");
insert into payment_methods values (1, "Cash"), (2, "Card");
insert into payment_statuses values (1, "Payed"), (2, "Waiting for payment");
insert into products (title, price, category, count, brand, color, weight, diagonal, storage, ram) values ("DELL Zenbook abc123", 500, 1, 5, "ASUS", "gray", 1.15, 13.3, 256, 4), ("ACER Aspire qwe456", 600, 3, 3, "ACER", "black", 1.75, 17.3, 512, 8), ("Samsung NoName", 800, 2, 1, "Samsung", "white", 2.5, 15.6, 1024, 16), ("Razer AnotherName", 750, 2, 5, "Razer", "black", 2.7, 17.3, 1024, 16);