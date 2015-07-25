@echo off

:LOOP

time /t
start D:\program\apache-jmeter-2.13\bin\jmeter -n -t D:\myhome\scripts\getData.jmx

TIMEOUT /T 900 /NOBREAK
GOTO LOOP