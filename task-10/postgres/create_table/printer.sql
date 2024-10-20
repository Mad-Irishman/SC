create table printer
(
    code  integer     not null
        primary key,
    model varchar(50) not null
        references product,
    color char,
    type  varchar(10),
    price money
);

