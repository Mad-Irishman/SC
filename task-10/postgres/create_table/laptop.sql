CREATE TABLE laptop
(
    code  INTEGER NOT NULL UNIQUE,
    model VARCHAR(50) NOT NULL REFERENCES product(model),
    speed SMALLINT NOT NULL,
    ram   SMALLINT NOT NULL,
    hd    REAL NOT NULL,
    price MONEY,
    screen SMALLINT NOT NULL
);
