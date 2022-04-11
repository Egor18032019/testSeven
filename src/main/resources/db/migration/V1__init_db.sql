create sequence hibernate_sequence start 1 increment 1;
create table users
(
    id         BIGINT not null,
    email      varchar(255),
    surname    varchar(255),
    username   varchar(255),
    patronymic varchar(255),
    phone      BIGINT,
    primary key (id)
);
alter table if exists users
    add constraint user_phone unique (phone)