drop table if exists category;
drop table if exists parameters;
drop table if exists products;

create table category(
    category varchar(64) primary key
);

create table parameters(
    id int auto_increment primary key,
    brand varchar(64),
    color varchar(64),
    weight double,
    diagonal double,
    storage double,
    ram int
);

create table products(
    id int primary key auto_increment,
    title varchar(128),
    price double,
    parameters int,
    category varchar(64),
    count int,
    foreign key(parameters) references parameters(id) on delete set null,
    foreign key(category) references category(category) on delete set null
);