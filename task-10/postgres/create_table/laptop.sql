create table laptop
(
    code   integer     not null
        primary key,
    model  varchar(50) not null
        references product,
    speed  smallint,
    ram    smallint,
    hd     real,
    screen real,
    price  money
);

