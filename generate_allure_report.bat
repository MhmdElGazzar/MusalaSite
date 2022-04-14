@echo off
set path=C:\Users\Dell\.m2\repository\allure\allure-2.17.3\bin;C:\Program Files\Java\jdk-17.0.1\bin;%path%
allure serve allure-results
pause
exit