-- CREATE TYPE order_status AS ENUM ('CREATED', 'COMPLETED', 'CANCELLED', 'IN_PROGRESS');
-- CREATE TYPE master_status AS ENUM ('AVAILABLE', 'OCCUPIED');

-- Таблица orders
CREATE TABLE orders
(
    id_order              CHARACTER(36) UNIQUE NOT NULL,
    description           TEXT                  NOT NULL,
    assigned_master       CHARACTER(36)        NOT NULL, -- Внешний ключ на таблицу masters
    assigned_garage_place INT                   NOT NULL, -- Внешний ключ на таблицу garage_places
    status_order          VARCHAR(10) DEFAULT 'CREATED',
    submission_date       TIMESTAMP             NOT NULL,
    completion_date       TIMESTAMP             NOT NULL,
    planned_start_date    TIMESTAMP             NOT NULL,
    price                 NUMERIC(10, 2)        NOT NULL
);

-- Таблица masters
CREATE TABLE masters
(
    id           CHARACTER(36) UNIQUE NOT NULL,
    name         VARCHAR(100)          NOT NULL,
    is_available VARCHAR(10) DEFAULT 'AVAILABLE'
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


-- Заполнение таблицы

INSERT INTO masters (id, name, is_available)
VALUES
    ('16bed4ef-f0ec-4711-bb1a-888b209694c0', 'Ivan Petrov', 'OCCUPIED'),
    ('9e5207b7-3997-4de4-b9f6-0a00a74abc60', 'Sergey Ivanov', 'OCCUPIED') ,
    ('5c1f4b60-6397-4f49-b80f-3d2a03536cb4', 'Alexey Sidorov', 'AVAILABLE'),
    ('cd5c5dc1-cb55-49e0-84eb-1cfe1745e031', 'Roman Sidor', 'AVAILABLE');

INSERT INTO garage_places (place_number, is_occupied)
VALUES
    (1, true),
    (2, true),
    (3, true),
    (4, false),
    (5, false);


INSERT INTO orders (id_order, description, assigned_master, assigned_garage_place, status_order, submission_date, completion_date, planned_start_date, price)
VALUES
    ('6d939ca9-563d-4a21-9d70-38a91c82f22f', 'Change Oil', '16bed4ef-f0ec-4711-bb1a-888b209694c0', 1, 'CREATED', '2024-10-31 09:00', '2024-10-31 12:00', '2024-10-31 10:00', 1500.00),
    ('6d939ca9-563d-4a21-9d70-38a91c82f22a', 'Change Motor', '9e5207b7-3997-4de4-b9f6-0a00a74abc60', 2, 'CREATED', '2024-10-30 08:30', '2024-10-30 16:00', '2024-10-30 09:00', 5000.00);
