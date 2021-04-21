rem  ****************************************
rem ***********  Установка переменных  *******
rem *****************************************

SET DB_URL=jdbc:postgresql://localhost:5432
SET DB_NAME=demo
SET DB_USERNAME=demo
SET DB_USERPASS=demo
SET LOGFILE=update.log

copy .\target\*.jar lib

--call dm.bat -i --url=%DB_URL% --database=%DB_NAME% --username=%DB_USERNAME% --password=%DB_USERPASS% --logFile=%LOGFILE%
call dm.bat -u --url=%DB_URL% --database=%DB_NAME% --username=%DB_USERNAME% --password=%DB_USERPASS% --logFile=%LOGFILE%
