#!/bin/bash
#set -x

function sysstat(){
        us=`iostat -c 1 1 | sed -n 4p  | awk '{print $1}'`
        sys=`iostat -c 1 1 | sed -n 4p  | awk '{print $3}'`
        cpu=`echo $(echo "$us+$sys"|bc) | awk -F "." '{print $2}'`

        used=`free -m | sed -n '2p' | awk '{print $3}'` #used memory
        fm=`free -m | sed -n '2p' | awk '{print $4}'` #free memory
        bu=`free -m | sed -n '2p' | awk '{print $6}'`       #buffers
        ca=`free -m | sed -n '2p' | awk '{print $7}'`       #cached
        total=`free -m | sed -n '2p' | awk '{print $2}'`    #total
        free=$(echo "$fm+$bu+$ca")
        bfb=$(echo "scale=2;$used/$total*100"|bc)
        kk=`awk 'BEGIN{print "'"$bfb"'"}' | awk -F "." '{print $1}'`

        kysp=`free -m | sed -n '4p' | awk '{print $3}'` #free swap
        zsp=`free -m | sed -n '4p' | awk '{print $2}'` #free swap
        ssyl=$(echo "scale=2;$kysp/$zsp*100"|bc)
        swap=`awk 'BEGIN{print "'"$ssyl"'"}' | awk -F "." '{print $1}'`

        hd0=`df -h |sed -n '3p'|awk '{print $4}'`
        hd=`echo $hd0 |cut -d '%' -f1`
        if [ $1 = harddisk ];then
                echo $hd
        fi
        if [ $1 = swap ];then
                echo $swap
        fi
        if [ $1 = memory ];then
                echo $kk
        fi
        if [ $1 = cpu ];then
                echo $cpu
        fi
}

function traff()
{
        eth=$1
        RXpre=$(cat /proc/net/dev | grep $eth | tr : " " | awk '{print $2}')
        TXpre=$(cat /proc/net/dev | grep $eth | tr : " " | awk '{print $10}')
        sleep 2
        RXnext=$(cat /proc/net/dev | grep $eth | tr : " " | awk '{print $2}')
        TXnext=$(cat /proc/net/dev | grep $eth | tr : " " | awk '{print $10}')
        RX0=$((${RXnext}-${RXpre}))
        TX0=$((${TXnext}-${TXpre}))
        if [ $2 = TX ];then
#                echo "$(echo $TX0 | awk '{print $1/1024}')"
                echo $TX0
        fi
        if [ $2 = RX ];then
#                echo "$(echo $RX0 | awk '{print $1/1024}')"
                echo $RX0
        fi
}
ip=`/sbin/ifconfig | grep "inet addr" | grep -v "127.0.0.1" | cut -d ":" -f2 | cut -d " " -f1`
input=`traff eth0 RX`
output=`traff eth0 TX`
tcp_connect=`netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}' | awk '{m+=$2}END{print m}'`
harddisk=`sysstat harddisk`
swap=`sysstat swap`
memory=`sysstat memory`
cpu=`sysstat cpu`
date=`date +%Y-%m-%d' '%H:%M:%S`
 
#echo $harddisk
#echo $swap
#echo $memory
#echo $cpu
#echo $tcp_connect
#echo $input
#echo $output

echo "$ip,$date,$harddisk%,$swap%,$memory%,$cpu%,$tcp_connect,$input,$output" >> /home/crontab/sysstat.log
runsql="insert into tbl_sysstat(ip,date,disk,swap,memory,cpu,tcp_conn,net_in,net_out) values('$ip','$date','$harddisk%','$swap%','$memory%','$cpu%',$tcp_connect,$input,$output);"
/usr/local/mysql/bin/mysql -uroot -psai -h192.100.11.10 edufound32_tianjin -e "$runsql";
