@echo off
title �Զ�������ߡ��ƶ�������ҵ����

echo ��ʼ���Maven����......
echo ��Ŀ·����E:\Workspaces\mobileaccount

cd /d E:\Workspaces\mobileaccount 
set /p env=����Ҫ����Ļ���:
call mvn clean package -P %env%

echo Maven���̴�����!
start E:\Workspaces\mobileaccount\target

echo. & pause