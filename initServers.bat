@echo off
set /p veces="Secondary sv num ?? "

rem Verificar si la entrada es un número entero válido
setlocal EnableDelayedExpansion
set "num=%veces%"
if defined num (
    set "num=!num:~0,1!"
    if "!num!" equ "0" (
        echo El número debe ser mayor que cero.
        pause
        exit /b
    )
)
endlocal

rem Ejecutar el comando Java el número de veces especificado
for /l %%i in (1, 1, %veces%) do (
    start "Java_Instance_%%i" java -jar secondaryServer\build\libs\secondaryServer.jar
)

pause
