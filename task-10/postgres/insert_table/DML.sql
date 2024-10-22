INSERT INTO product (maker, model, type)
VALUES ('Dell', 'XPS 13', 'Laptop'),
       ('Apple', 'MacBook Air', 'Laptop'),
       ('HP', 'Pavilion 15', 'Laptop'),
       ('Lenovo', 'ThinkPad X1', 'Laptop'),
       ('Asus', 'ZenBook 14', 'Laptop'),
       ('Acer', 'Aspire 5', 'Laptop'),
       ('Samsung', 'Galaxy Book', 'Laptop'),
       ('Microsoft', 'Surface Laptop', 'Laptop'),
       ('Canon', 'PIXMA TR4520', 'Printer'),
       ('Epson', 'EcoTank ET-2720', 'Printer'),
       ('HP', 'LaserJet Pro M15w', 'Printer'),
       ('Brother', 'HL-L2350DW', 'Printer'),
       ('Dell', 'B2360dn', 'Printer'),
       ('Xerox', 'Phaser 6510', 'Printer'),
       ('Razer', 'Razer Blade 15', 'PC'),
       ('MSI', 'MSI GF63', 'PC'),
       ('Gigabyte', 'Gigabyte AORUS 15G', 'PC'),
       ('Lenovo', 'Lenovo Legion 5', 'PC'),
       ('ASRock', 'ASRock DeskMini A300', 'PC'),
       ('HP', 'HP Pavilion Gaming', 'PC'),
       ('Razer', 'Razer Blade Stealth 13', 'PC'),
       ('Razer', 'Razer Blade 17', 'PC'),
       ('Dell', 'Inspiron 15', 'PC'),
       ('HP', 'HP Omen', 'PC'),
       ('Lenovo', 'ThinkPad E14', 'PC'),
       ('Canon', 'PIXMA TR150', 'Printer'),
       ('Epson', 'EcoTank ET-4760', 'Printer');



INSERT INTO pc (code, model, speed, ram, hd, cd, price)
VALUES (1, 'Razer Blade 15', 3000, 16, 512, '4x', 1999.99),
       (2, 'MSI GF63', 2500, 8, 256, '24x', 899.99),
       (3, 'Gigabyte AORUS 15G', 3200, 32, 1024, 'No', 2499.99),
       (4, 'Lenovo Legion 5', 2700, 16, 512, '12x', 1299.99),
       (5, 'HP Pavilion Gaming', 2500, 8, 512, 'No', 999.99),
       (6, 'ASRock DeskMini A300', 2800, 24, 512, '12x', 499.99),
       (7, 'Razer Blade Stealth 13', 2800, 16, 512, '4x', 1499.99),
       (8, 'Razer Blade 17', 3200, 32, 2048, 'No', 2999.99),
       (9, 'Inspiron 15', 2000, 4, 256, 'No', 599.99),
       (10, 'HP Omen', 2200, 4, 512, 'No', 799.99),
       (11, 'ThinkPad E14', 2100, 4, 512, 'No', 749.99);



INSERT INTO laptop (code, model, speed, ram, hd, price, screen)
VALUES (1, 'XPS 13', 3200, 16, 512, 1299.99, 13),
       (2, 'MacBook Air', 2800, 8, 256, 999.99, 13),
       (3, 'Pavilion 15', 2500, 8, 512, 799.99, 15),
       (4, 'ThinkPad X1', 3500, 16, 1000, 1999.99, 14),
       (5, 'ZenBook 14', 3000, 16, 512, 1199.99, 14),
       (6, 'Aspire 5', 2400, 8, 256, 599.99, 15),
       (7, 'Galaxy Book', 2900, 16, 512, 1099.99, 15),
       (8, 'Surface Laptop', 3100, 8, 256, 999.99, 13);


INSERT INTO printer (code, model, color, type, price)
VALUES (1, 'PIXMA TR4520', 'Y', 'Inkjet', 99.99),
       (2, 'EcoTank ET-2720', 'N', 'Inkjet', 299.99),
       (3, 'LaserJet Pro M15w', 'N', 'Laser', 199.99),
       (4, 'HL-L2350DW', 'N', 'Laser', 129.99),
       (5, 'B2360dn', 'N', 'Laser', 249.99),
       (6, 'Phaser 6510', 'Y', 'Laser', 349.99),
       (7, 'PIXMA TR150', 'N', 'Inkjet', 149.99),
       (8, 'EcoTank ET-4760', 'Y', 'Inkjet', 299.99);
