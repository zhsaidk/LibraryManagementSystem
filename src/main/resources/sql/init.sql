create table person
(
    id           BIGSERIAL PRIMARY KEY,
    firstname    VARCHAR(124),
    lastname     VARCHAR(124),
    email        VARCHAR(124) NOT NULL,
    phone_number varchar(124) NOT NULL,
    role         varchar(32) default 'USER',
    birth_date   DATE         not null
);

create table author
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(124) NOT NULL
);

create table book
(
    id        BIGSERIAL PRIMARY KEY,
    title     VARCHAR(124) NOT NULL,
    year      INTEGER,
    author_id INTEGER,
    person_id INTEGER,
    foreign key (author_id) references author (id),
    foreign key (person_id) references person (id)
);

drop table person, author, book;


insert into person(firstname, lastname, email, phone_number, birth_date)
values ('Ivan', 'Ivanov', 'ivan@gmail.com', '+77787077777', '2005-10-10'),
       ('Maria', 'Ivanova', 'maria@mail.com', '+77075460909', '2001-10-10'),
       ('Oleg', 'Olegovich', 'oleg@gmail.com', '+77787070909', '2002-10-10'),
       ('Sveta', 'Svetnikova', 'sveta@gmail.com', '+77763459292', '1999-10-10'),
       ('Dmitriy', 'Falkovskiy', 'dmitriy2@gmail.com', '+77782346576', '1998-10-10');


insert into author(name)
values ('Лев Толстой'),
       ('Стивен Кинг');

insert into book(title, year, person_id, author_id)
VALUES ('Война и мир', 1863, null, 1),
       ('Анна Каренина', 1873, null, 1),
       ('Кавказский пленник', 1872, null, 1),
       ('Оно', 1986, null, 2),
       ('Темная Башня', 1970, null, 2),
       ('Зеленая Миля', 1996, null, 2),
       ('Сияние', 1977, null, 2);