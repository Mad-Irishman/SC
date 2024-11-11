CREATE TABLE pc
(
    code  INTEGER NOT NULL UNIQUE,
    model VARCHAR(50) NOT NULL REFERENCES product(model),
    speed SMALLINT NOT NULL,
    ram   SMALLINT NOT NULL,
    hd    REAL NOT NULL,
    cd    VARCHAR(10) NOT NULL,
    price MONEY
);
