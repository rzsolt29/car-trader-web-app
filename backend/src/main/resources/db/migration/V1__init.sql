CREATE TABLE roles(
       id serial PRIMARY KEY,
       name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE users (
       id serial PRIMARY KEY,
       email varchar(45) UNIQUE NOT NULL,
       password varchar(60),
       created_on timestamp(6) with time zone,
       role int NOT NULL,
       FOREIGN KEY (role)
            REFERENCES roles (id)
    );

CREATE TABLE cars (
       id serial PRIMARY KEY,
       published_on timestamp(6) with time zone,
       make varchar(255),
       model varchar(255),
       price integer,
       owner integer NOT NULL,
       FOREIGN KEY (owner)
            REFERENCES users (id)
    );
