--liquibase formatted sql

--changeset zhavokhir:1
alter table person
add column created_at TIMESTAMP,
add column modified_at TIMESTAMP,
add column created_by VARCHAR(64),
add column modified_by VARCHAR(64);
--rollback alter table person drop column created_at, drop column modified_at, drop column created_by, drop column modified_by