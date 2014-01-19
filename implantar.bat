@echo off
echo.
echo *******************************************************************************
echo Livraria
echo *******************************************************************************
echo.
call mvn clean compile package install
call mvn glassfish:undeploy
call mvn glassfish:deploy
call mvn glassfish:redeploy
echo.
echo A aplicacao ja pode ser acessada pelo endereco http://localhost:8080/livraria/
pause
