drop table if exists category;
drop table if exists parameters;
drop table if exists products;
create table category(id int primary key auto_increment, title varchar(64));
create table parameters(id int auto_increment primary key, brand varchar(64), color varchar(64), weight double, diagonal double, storage double, ram int);
create table products( id int primary key auto_increment, title varchar(128), price double, parameters int, category int, count int, foreign key(parameters) references parameters(id) on delete set null, foreign key(category) references category(id) on delete set null);
insert into category values (1, "For students"), (2, "Gaming"), (3, "Netbook");
insert into parameters values (1, "ASUS", "gray", 1.15, 13.3, 256, 4), (2, "ACER", "black", 1.75, 17.3, 512, 8), (3, "Samsung", "white", 2.5, 15.6, 1024, 16), (4, "Razer", "black", 2.7, 17.3, 1024, 16);
insert into products values (1, "DELL Zenbook abc123", 500, 1, 3, 5), (2, "ACER Aspire qwe456", 600, 2, 1, 3), (3, "Samsung NoName", 800, 3, 2, 1), (4, "Razer AnotherName", 750, 4, 2, 5);