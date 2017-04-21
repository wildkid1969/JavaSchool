#!/bin/sh
/home/tomcat6/bin/shutdown.sh
sleep 5s
ps -ef|grep java
/home/tomcat6/bin/startup.sh
ps -ef|grep java
