-- Шаг 1: Создаем типы ENUM для статусов
CREATE TYPE order_status AS ENUM ('CREATED', 'COMPLETED', 'CANCELLED', 'IN_PROGRESS');
CREATE TYPE master_status AS ENUM ('AVAILABLE', 'OCCUPIED');

-- Шаг 2: Создаем таблицы без внешних ключей

-- Таблица orders
CREATE TABLE orders
(
    id_order              CHARACTER(16) UNIQUE NOT NULL,
    description           TEXT,
    assigned_master       CHARACTER(16), -- Внешний ключ добавим позже
    assigned_garage_place INT,           -- Внешний ключ добавим позже
    status_order          order_status DEFAULT 'CREATED',
    submission_date       TIMESTAMP,
    completion_date       TIMESTAMP,
    planned_start_date    TIMESTAMP,
    price                 NUMERIC(10, 2)
);

-- Таблица masters
CREATE TABLE masters
(
    id            CHARACTER(16) UNIQUE NOT NULL,
    name          VARCHAR(100)         NOT NULL,
    is_available  master_status DEFAULT 'AVAILABLE',
    orders_master CHARACTER(16) -- Внешний ключ добавим позже
);

-- Таблица garage_places
CREATE TABLE garage_places
(
    place_number INT PRIMARY KEY,
    is_occupied  BOOLEAN DEFAULT FALSE,
    id_order     CHARACTER(16) -- Внешний ключ добавим позже
);

-- Шаг 3: Добавляем внешние ключи после создания таблиц

-- Добавляем внешние ключи в таблицу orders
ALTER TABLE orders
    ADD CONSTRAINT fk_assigned_master FOREIGN KEY (assigned_master) REFERENCES masters (id),
    ADD CONSTRAINT fk_assigned_garage_place FOREIGN KEY (assigned_garage_place) REFERENCES garage_places (place_number);

-- Добавляем внешний ключ в таблицу masters
ALTER TABLE masters
    ADD CONSTRAINT fk_orders_master FOREIGN KEY (orders_master) REFERENCES orders (id_order);

-- Добавляем внешний ключ в таблицу garage_places
ALTER TABLE garage_places
    ADD CONSTRAINT fk_id_order FOREIGN KEY (id_order) REFERENCES orders (id_order);