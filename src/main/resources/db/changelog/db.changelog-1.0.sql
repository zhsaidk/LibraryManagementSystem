--liquibase formatted sql

--changeset zhavokhir:1.1
create table if not exists person
(
    id           BIGSERIAL PRIMARY KEY,
    firstname    VARCHAR(124),
    lastname     VARCHAR(124),
    email        VARCHAR(124) NOT NULL,
    phone_number varchar(124) NOT NULL,
    role         varchar(32) default 'USER',
    birth_date   DATE         not null
);
--rollback DROP table person

--changeset zhavokhir:1.2
create table if not exists author
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(124) NOT NULL
);
--rollback DROP table author

--changeset zhavokhir:1.3
create table if not exists book
(
    id        BIGSERIAL PRIMARY KEY,
    title     VARCHAR(124) NOT NULL,
    year      INTEGER,
    author_id BIGINT,
    person_id BIGINT,
    foreign key (author_id) references author (id),
    foreign key (person_id) references person (id)
);
--rollback DROP table book