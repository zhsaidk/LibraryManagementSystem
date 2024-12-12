--liquibase formatted sql

--changeset zhavokhir:1
alter table person
    add column image VARCHAR(124) default null;


alter table person
    add column password varchar(256) default '{noop}123';
