-- Заполнение таблицы product
INSERT INTO product (maker, model, type)
VALUES 
    ('Dell', 'X123', 'PC'),
    ('HP', 'P456', 'PC'),
    ('Asus', 'L789', 'Laptop'),
    ('Acer', 'L101', 'Laptop'),
    ('Canon', 'P102', 'Printer'),
    ('Brother', 'P103', 'Printer');

-- Заполнение таблицы PC
INSERT INTO pc (code, model, speed, ram, hd, cd, price)
VALUES 
    (1, 'X123', 3400, 16, 500, '24x', '600.00'),
    (2, 'P456', 2500, 8, 1000, '48x', '800.00'),
    (3, 'X123', 2200, 4, 320, '12x', '450.00'),
    (4, 'X123', 3400, 16, 500, '4x', '450.00');

-- Заполнение таблицы Laptop
INSERT INTO laptop (code, model, speed, ram, hd, screen, price)
VALUES 
    (1, 'L789', 3000, 16, 500, 15.6, '1200.00'),
    (2, 'L101', 2200, 8, 256, 14.0, '850.00'),
    (3, 'L789', 1800, 4, 128, 13.3, '900.00');

-- Заполнение таблицы Printer
INSERT INTO printer (code, model, color, type, price)
VALUES 
    (1, 'P102', 'Y', 'Laser', '200.00'),
    (2, 'P103', 'N', 'Jet', '150.00'),
    (3, 'P103', 'Y', 'Matrix', '180.00');