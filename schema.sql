create table user
(
    id       int auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null
);
create table window
(
    id       int auto_increment
        primary key,
    name     varchar(255) not null,
    position varchar(255) not null,
    state    varchar(255) not null,
    angle    int          not null,
    image    varchar(255) null
);