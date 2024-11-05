CREATE TABLE product
(
    maker varchar(10) NOT NULL,
    model varchar(50) NOT NULL UNIQUE,
    type  varchar(50) NOT NULL
);
