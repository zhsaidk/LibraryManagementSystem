## **Library Management System** - это система управления библиотекой, разработанная для автоматизации учета книг, пользователей и операций выдачи/возврата. Проект создан с использованием Java и Spring Framework, с подключением к базе данных PostgreSQL.

   ## **Цель проекта** — предоставить удобный инструмент для управления библиотечными ресурсами.
## **Основные возможности**:

* Регистрация и управление пользователями (читателями).
* Добавление, редактирование и удаление книг.
* Отслеживание статуса выдачи и возврата книг.
* REST API для интеграции с другими системами.

## Функциональности

Все возможности системы доступны через REST API и веб-интерфейс. Подробное описание функциональностей, эндпоинтов, параметров и примеров запросов доступно в **Swagger UI**, по адресу `http://localhost:8080/swagger-ui.html`. Используйте его для изучения и тестирования всех доступных операций, включая:

- Управление книгами (добавление, редактирование, удаление, выдача/возврат).  
- Управление пользователями (регистрация, редактирование, удаление, получение информации, аватары).  
- Отслеживание операций выдачи/возврата книг.  

Swagger UI предоставляет автоматическую документацию с примерами запросов и ответов, что делает его удобным инструментом для работы с API.

## **Технологии**
Язык программирования: Java 19
Фреймворк: Spring (Core, Boot, MVC, Security, Data)
База данных: PostgreSQL
Инструменты: Git, IntelliJ IDEA, Swagger, Lombok, JUnit
Управление зависимостями: Gradle
Принципы: SOLID, MVC, REST

   ## **Требования**
Для запуска проекта вам понадобится:

Java 19
PostgreSQL 17.2 можно любая версия, (главное, чтобы была создана база данных `library`)
Gradle
IntelliJ IDEA
Git

   ## **Установка и запуск**
1. Клонируйте репозиторий:
   git clone https://github.com/zhsaidk/LibraryManagementSystem.git
   cd LibraryManagementSystem

2. Настройте базу данных:
   Установите PostgreSQL, если еще не установлена.
   Создайте базу данных:
   CREATE DATABASE library;

3. Настройте конфигурацию в yaml:
   spring.datasource.url=jdbc:postgresql://localhost:5432/library
   spring.datasource.username=postgres
   spring.datasource.password=your_password

4. Запустите приложение:
   Используйте IntelliJ IDEA, откройте проект и нажмите "Run".
