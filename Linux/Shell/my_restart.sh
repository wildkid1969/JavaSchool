#!/bin/sh
/space/tomcat/theWolverine_tomcat8026/bin/shutdown.sh
sleep 5s
ps -ef|grep tomcat8026
/space/tomcat/theWolverine_tomcat8026/bin/startup.sh
ps -ef|grep tomcat8026
