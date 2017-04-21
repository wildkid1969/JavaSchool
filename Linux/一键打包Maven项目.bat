@echo off
title 自动打包工具【移动互联事业部】

echo 开始打包Maven工程......
echo 项目路径：E:\Workspaces\mobileaccount

cd /d E:\Workspaces\mobileaccount 
set /p env=输入要打包的环境:
call mvn clean package -P %env%

echo Maven工程打包完毕!
start E:\Workspaces\mobileaccount\target

echo. & pause