--liquibase formatted sql

--changeset zhavokhir:3.1
insert into person(firstname, lastname, email, phone_number, role, birth_date)
values ('Ivan', 'Ivanov', 'ivan@gmail.com', '+77787077777', 'USER', '2005-10-10'),
       ('Maria', 'Ivanova', 'maria@mail.com', '+77075460909', 'USER', '2001-10-10'),
       ('Oleg', 'Olegovich', 'oleg@gmail.com', '+77787070909', 'USER', '2002-10-10'),
       ('Sveta', 'Svetnikova', 'sveta@gmail.com', '+77763459292', 'ADMIN', '1999-10-10'),
       ('Dmitriy', 'Falkovskiy', 'dmitriy2@gmail.com', '+77782346576', 'USER', '1998-10-10');
--rollback delete from person where email in ('ivan@gmail.com', 'maria@mail.com', 'oleg@gmail.com', 'sveta@gmail.com', 'dmitriy2@gmail.com')


--changeset zhavokhir:3.2
insert into author(name)
values ('Лев Толстой'),
       ('Стивен Кинг');
--rollback delete from author where name in ('Лев Толстой','Стивен Кинг')

--changeset zhavokhir:3.3
insert into book(title, year, person_id, author_id)
VALUES ('Война и мир', 1863, null, 1),
       ('Анна Каренина', 1873, 2, 1),
       ('Кавказский пленник', 1872, null, 1),
       ('Оно', 1986, null, 2),
       ('Темная Башня', 1970, 4, 2),
       ('Зеленая Миля', 1996, null, 2),
       ('Сияние', 1977, null, 2);
--rollback delete from book where title in ('Война и мир', 'Анна Каренина', 'Кавказский пленник', 'Оно', 'Темная Башня', 'Зеленая Миля', 'Сияние')