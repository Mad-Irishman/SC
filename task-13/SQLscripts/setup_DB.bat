@echo off
set DB_NAME=autoserviceDB
set DB_USER=postgres
set DB_PASSWORD=root

:: Устанавливаем переменную среды для пароля PostgreSQL
set PGPASSWORD=%DB_PASSWORD%

:: Выполняем SQL-скрипт для создания базы и заполнения данных
psql -U %DB_USER% -d %DB_NAME% -f "setup.sql"

:: Очистка переменной окружения пароля
set PGPASSWORD=

echo Database setup complete.
pause
