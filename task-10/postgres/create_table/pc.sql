create table pc
(
    code  integer     not null
        primary key,
    model varchar(50) not null
        references product,
    speed smallint,
    ram   smallint,
    hd    real,
    cd    varchar(10),
    price money
);

