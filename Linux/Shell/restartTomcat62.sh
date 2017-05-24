#!/bin/sh
#kill tomcat pid
pidlist=`ps -ef|grep tomcat62|grep -v "grep"|awk '{print $2}'`
if [ "$pidlist" = "" ]
   then
       echo "no tomcat pid alive!"
else
  echo "tomcat Id list :$pidlist"
  kill -9 $pidlist
  echo "KILL $pidlist:"
  echo "service stop success"
fi
echo "start tomcat62"
cd /usr/local/tomcat62/bin
./startup.sh ;tail -f ../logs/catalina.out
