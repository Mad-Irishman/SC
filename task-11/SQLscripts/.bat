@echo off
SET PGUSER=postgres          
SET PGPASSWORD=root  
SET DBNAME=autoserviceDB

psql -U %PGUSER% -c "DROP DATABASE IF EXISTS %DBNAME%; CREATE DATABASE %DBNAME%;"


psql -U %PGUSER% -d %DBNAME% -f setup.sql

echo Database setup complete.
pause
