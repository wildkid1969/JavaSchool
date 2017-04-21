#!/bin/bash
export JAVA_HOME=/usr/bin
export LANG=zh_CN.UTF-8
export PATH=/usr/kerberos/sbin:/usr/kerberos/bin:/usr/java/jdk1.6.0_05/bin:/usr/java/jdk1.6.0_05/jre/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin

DATE_TIME=$(date '+%Y-%m-%d')

PID_VALUE=`ps -ef|grep FlightstatsCrawler| grep -v grep| tr -s " " | cut -d " " -f2`
PID_VALUE=`echo ${PID_VALUE} | sed 's/ *$//g'|sed 's/^ *//g'`

#if [ -n "${PID_VALUE}" ];
#then
#        echo "already running, pid= $PID_VALUE " >> /home/crontab/spider-flight-flightstats-${DATE_TIME}.log
#	echo "already running, pid=${PID_VALUE}, then kill it"
#	kill -9 ${PID_VALUE}
#fi

#echo "" >> /home/crontab/spider-flight-flightstats-${DATE_TIME}.log
#echo $(date '+%Y-%m-%d_%H:%M:%S') >> /home/crontab/spider-flight-flightstats-${DATE_TIME}.log

/usr/local/jdk1.6/bin/java -classpath /home/crontab/lib/inspur/insertStat.jar:/home/crontab/lib/*  com.edufound.main.InsertStat >> /home/crontab/logs/insert-${DATE_TIME}.log
