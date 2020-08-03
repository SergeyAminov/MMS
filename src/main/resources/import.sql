drop table if exists products;
drop table if exists category;
drop table if exists parameters;
drop table if exists orders;
drop table if exists addresses;
drop table if exists users;
drop table if exists delivery_methods;
drop table if exists delivery_statuses;
drop table if exists payment_methods;
drop table if exists payment_statuses;
create table category(id int primary key auto_increment, title varchar(64));
create table parameters(id int auto_increment primary key, brand varchar(64), color varchar(64), weight double, diagonal double, storage double, ram int);
create table delivery_statuses(id int primary key auto_increment, title varchar(64));
create table payment_statuses(id int primary key auto_increment, title varchar(64));
create table delivery_methods(id int primary key auto_increment, title varchar(64));
create table payment_methods(id int primary key auto_increment, title varchar(64));
create table users(id int primary key auto_increment, name varchar(64), surname varchar(64), birthday varchar(64), email varchar(64), password varchar(64), role varchar(64));
create table addresses(id int primary key auto_increment, country varchar(64), city varchar(64), street varchar(64), postcode int, building int, apart_number int, user int, foreign key(user) references users(id) on delete set null);
create table orders(id int primary key auto_increment,user int, address int,payment_method int,delivery_method int,payment_status int,delivery_status int,foreign key(user) references users(id) on delete set null,foreign key(address) references addresses(id) on delete set null,foreign key(payment_method) references payment_methods(id) on delete set null,foreign key(delivery_method) references delivery_methods(id) on delete set null,foreign key(payment_status) references payment_statuses(id) on delete set null,foreign key(delivery_status) references delivery_statuses(id) on delete set null);
create table products(id int primary key auto_increment,title varchar(128),price double,parameters int,order_id int default null,category int,count int,foreign key(parameters) references parameters(id) on delete set null,foreign key(category) references category(id) on delete set null,foreign key(order_id) references orders(id) on delete set null);
insert into category values (1, "For students"), (2, "Gaming"), (3, "Netbook");
insert into parameters values (1, "ASUS", "gray", 1.15, 13.3, 256, 4), (2, "ACER", "black", 1.75, 17.3, 512, 8), (3, "Samsung", "white", 2.5, 15.6, 1024, 16), (4, "Razer", "black", 2.7, 17.3, 1024, 16);
insert into products (id, title, price, parameters, category, count) values (1, "DELL Zenbook abc123", 500, 1, 3, 5), (2, "ACER Aspire qwe456", 600, 2, 1, 3), (3, "Samsung NoName", 800, 3, 2, 1), (4, "Razer AnotherName", 750, 4, 2, 5);