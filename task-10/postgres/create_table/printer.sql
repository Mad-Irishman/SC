CREATE TABLE printer
(
    code  INTEGER     NOT NULL UNIQUE,
    model VARCHAR(50) NOT NULL REFERENCES product (model),
    color CHAR(1),
    type  VARCHAR(10),
    price MONEY
);
