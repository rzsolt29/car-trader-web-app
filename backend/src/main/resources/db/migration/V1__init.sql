CREATE TABLE users (
       id serial PRIMARY KEY,
       email varchar(45) UNIQUE NOT NULL,
       password varchar(60),
       created_on timestamp(6) with time zone,
       role varchar(10) NOT NULL
    );

CREATE TABLE cars (
       id serial PRIMARY KEY,
       published_on timestamp(6) with time zone,
       make varchar(45),
       model varchar(60),
       price integer,
       body_type varchar(15),
       gearbox varchar(15),
       fuel_type varchar(15),
       production_year integer,
       colour varchar(30),
       doors integer,
       engine_size integer,
       engine_power integer,
       drivetrain varchar(15),
       owner integer NOT NULL,
       FOREIGN KEY (owner)
            REFERENCES users (id)
    );
