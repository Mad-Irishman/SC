-- 1 query
SELECT model, speed, hd
FROM pc
WHERE price < '500';

-- 2 query
SELECT maker
FROM product as p
WHERE p.type = 'Printer';

-- 3 query
SELECT model, ram, screen
FROM laptop
WHERE price < '1000';

-- 4 query
SELECT *
FROM printer
WHERE color = 'Y';

-- 5 query
SELECT model, speed, hd
FROM pc
WHERE cd IN ('12x', '24x')
  AND price < '600';

-- 6 query
SELECT p.maker, l.speed
FROM laptop AS l
         JOIN product AS p ON l.model = p.model
WHERE l.hd >= 100;

-- 7 query
SELECT p.model, pc.price
FROM product AS p
         LEFT JOIN pc ON p.model = pc.model
         LEFT JOIN laptop ON p.model = laptop.model
         LEFT JOIN printer ON p.model = printer.model
WHERE p.maker LIKE 'B%';

-- 8 query
SELECT DISTINCT maker
FROM product as p
WHERE p.type = 'PC'
  AND maker NOT IN (SELECT maker FROM product AS p WHERE p.type = 'Laptop');

-- 9 query
SELECT DISTINCT p.maker
FROM pc
         JOIN product AS p ON p.model = pc.model
WHERE pc.speed >= 450;

-- 10 query
SELECT model, price
FROM printer
WHERE price = (SELECT MAX(price) FROM printer);

-- 11 query
SELECT AVG(pc.speed) AS avg_speed
FROM pc;

-- 12 query
SELECT AVG(laptop.speed) AS avg_speed
FROM laptop
WHERE laptop.price > '1000';

-- 13 query
SELECT AVG(pc.speed) AS avg_speed
FROM pc
         JOIN product p on p.model = pc.model
WHERE p.maker LIKE 'A%';

-- 14 query
SELECT speed, AVG(CAST(price AS DECIMAL(10, 2))) AS avg_price
FROM pc
GROUP BY speed;

-- 15 query
SELECT pc.hd
FROM pc
GROUP BY pc.hd
HAVING COUNT(pc.hd) >= 2;

-- 16 query
SELECT pc1.model AS model1, pc2.model AS model2, pc1.speed, pc1.ram
FROM pc pc1,
     pc pc2
WHERE pc1.speed = pc2.speed
  AND pc1.ram = pc2.ram
  AND pc1.model > pc2.model;


-- 17 query
SELECT 'Laptop' AS type, model, speed
FROM laptop
WHERE speed < (SELECT MIN(speed) FROM pc);

-- 18 query
SELECT product.maker, p.price
FROM product
         JOIN printer AS p ON product.model = p.model
WHERE p.color = 'Y'
ORDER BY p.price
LIMIT 1;

-- 19 query
SELECT p.maker, AVG(l.screen)
FROM laptop AS l
         JOIN product AS p ON l.model = p.model
GROUP BY p.maker;

-- 20 query
SELECT p.maker, COUNT(DISTINCT model)
FROM product AS p
WHERE p.type = 'PC'
GROUP BY p.maker
HAVING COUNT(DISTINCT model) >= 3;

-- 21 query
SELECT p.maker, MAX(pc.price)
FROM pc
         JOIN product AS p ON p.model = pc.model
GROUP BY p.maker;

-- 22 query
SELECT speed, AVG(price :: DECIMAL(10, 2))
FROM pc
WHERE speed > 600
GROUP BY speed;

-- 23 query
SELECT maker
FROM product
WHERE model IN (SELECT model FROM pc WHERE speed >= 750)
  AND model IN (SELECT model FROM laptop WHERE speed >= 750);


-- 24 query
SELECT model, price
FROM (SELECT model, price
      FROM pc
      UNION
      SELECT model, price
      FROM laptop
      UNION
      SELECT model, price
      FROM printer) AS all_products
WHERE price = (SELECT MAX(price)
               FROM (SELECT price
                     FROM pc
                     UNION
                     SELECT price
                     FROM laptop
                     UNION
                     SELECT price
                     FROM printer) AS all_prices);

-- 25 query
WITH min_ram_pc AS (SELECT model, MIN(ram) AS min_ram
                    FROM pc
                    GROUP BY model),
     fastest_min_ram_pc AS (SELECT model, MAX(speed) AS max_speed
                            FROM pc
                            WHERE ram = (SELECT MIN(ram) FROM pc)
                            GROUP BY model)
SELECT DISTINCT p.maker
FROM product p
         JOIN printer pr ON p.model = pr.model
WHERE p.model IN (SELECT model
                  FROM fastest_min_ram_pc);
