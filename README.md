# PhotosXX
JavaFX Photos Managment Application


# Guidelines
## Java Setup
Ensure you have installed jdk-11 and above in your machine.

## DataBase Setup
Install mysql-server setup your configurations and also update the same configuration in the DatabaseConnection.java
Create a database that you have configured above and run the following in your mysql command line or directly import the database.sql file.
```bash

create table users
(
    id             bigint unsigned auto_increment
        primary key,
    name           varchar(255)                        not null,
    username       varchar(255)                        not null,
    password       varchar(255)                        not null,
    remember_token varchar(100)                        null,
    created_at     timestamp default CURRENT_TIMESTAMP null,
    updated_at     timestamp default CURRENT_TIMESTAMP null,
    constraint users_email_unique
        unique (username)
)
    collate = utf8mb4_unicode_ci;


create table albums
(
    id         bigint unsigned auto_increment
        primary key,
    user_id    bigint                              not null,
    name       varchar(255)                        not null,
    captions   varchar(255)                        null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null
)
    collate = utf8mb4_unicode_ci;


create table photos
(
    id         bigint unsigned auto_increment
        primary key,
    album_id   bigint                              not null,
    user_id    bigint                              not null,
    name       varchar(255)                        not null,
    tag        varchar(255)                        null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null
)
    collate = utf8mb4_unicode_ci;



```

## IDE Setup
Import the project to either IntellJ, NetBeans or Eclipse
On the IDE hit the run button.

