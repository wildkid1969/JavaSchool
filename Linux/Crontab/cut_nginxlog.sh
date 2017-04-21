#!/bin/bash
log_path="/home/nginx/logs"
day=`date +%Y-%m-%d`
mv $log_path/access.log /nginx/access${day}.log
kill -USR1 `cat /home/nginx/logs/nginx.pid`

##mv tomcatlog

cat /home/tomcat6/logs/catalina.out > /nginx/catalina.out-${day}
echo '' > /home/tomcat6/logs/catalina.out 
