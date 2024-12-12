SELECT * FROM person;

delete FROM person
where id in (10, 11, 12, 13, 14, 15, 16);

alter table person
add column password varchar(256) default '{noop}123';

select *from person;

delete from person
where id > 6;


alter table person
add column image VARCHAR(124) default null