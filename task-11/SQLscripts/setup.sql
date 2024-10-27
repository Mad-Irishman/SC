CREATE TYPE order_status AS ENUM ('CREATED', 'COMPLETED', 'CANCELLED', 'IN_PROGRESS');
CREATE TYPE master_status AS ENUM ('AVAILABLE', 'OCCUPIED');

-- Таблица orders
CREATE TABLE orders
(
    id_order              CHARACTER(16) UNIQUE NOT NULL,
    description           TEXT                 NOT NULL,
    assigned_master       CHARACTER(16)        NOT NULL, -- Внешний ключ на таблицу masters
    assigned_garage_place INT                  NOT NULL, -- Внешний ключ на таблицу garage_places
    status_order          order_status DEFAULT 'CREATED',
    submission_date       TIMESTAMP            NOT NULL,
    completion_date       TIMESTAMP            NOT NULL,
    planned_start_date    TIMESTAMP            NOT NULL,
    price                 NUMERIC(10, 2)       NOT NULL
);

-- Таблица masters
CREATE TABLE masters
(
    id           CHARACTER(16) UNIQUE NOT NULL,
    name         VARCHAR(100)         NOT NULL,
    is_available master_status DEFAULT 'AVAILABLE'
);

-- Таблица garage_places
CREATE TABLE garage_places
(
    place_number INT UNIQUE,
    is_occupied  BOOLEAN DEFAULT FALSE
);


ALTER TABLE orders
    ADD CONSTRAINT fk_assigned_master FOREIGN KEY (assigned_master) REFERENCES masters (id),
    ADD CONSTRAINT fk_assigned_garage_place FOREIGN KEY (assigned_garage_place) REFERENCES garage_places (place_number);
