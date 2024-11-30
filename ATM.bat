@echo off

REM Компиляция Java файлов
javac -d out/production/classes src/Bank/*.java

REM Запуск программы
java -cp out/production/classes Bank.ATM

pause