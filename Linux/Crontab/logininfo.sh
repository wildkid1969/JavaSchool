#!/bin/sh
last=/usr/bin/last
head=/usr/bin/head
static_file=/tmp/login.txt
runsql1="delete from tbl_logininfo"

/usr/local/mysql/bin/mysql -uroot -psai -h192.100.11.10 edufound32_tianjin -e "$runsql1"

$last | $head -20 > $static_file
cat $static_file | while read oneline
do

login_user=`echo $oneline | awk -F " " '{print $1}'`
login_tty=`echo "$oneline" | awk -F " " '{print $2}'`
login_ip=`/sbin/ifconfig | grep "inet addr" | grep -v "127.0.0.1" | cut -d ":" -f2 | cut -d " " -f1`
login_date=`echo "$oneline" | awk -F " " '{print $4,$5,$6}'`
login_starttime=`echo "$oneline" | awk -F " " '{print $7}'`
login_endtime=`echo "$oneline" | awk -F " " '{print $9}'`
login_timelong=`echo "$oneline" | awk -F " " '{print $10}'`

runsql1="delete from tbl_logininfo"
runsql2="insert into tbl_logininfo(login_user,login_tty,login_ip,login_date,login_starttime,login_endtime,login_timelong) values('$login_user','$login_tty','$login_ip','$login_date','$login_starttime','$login_endtime','$login_timelong')"

/usr/local/mysql/bin/mysql -uroot -psai -h192.100.11.10 edufound32_tianjin -e "$runsql2"
sleep 1
done
