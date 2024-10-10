create table games (
    score integer,
    id bigint not null auto_increment,
    user_id bigint,
    primary key (id)
);

create table users (
    id bigint not null auto_increment,
    email varchar(255) not null unique,
    name varchar(255) not null unique,
    phone_number varchar(255) not null unique,
    primary key (id)
);