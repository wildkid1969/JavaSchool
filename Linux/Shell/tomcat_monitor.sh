#!/bin/bash
while true
do
http_url=$(curl -I -m 10 -o /dev/null -s -w %{http_code}  http://testwx.mdata.hc360.com/mobileaccount/saleUser/userIndex?phone=37863e32ce5d67c0be9ddf)
if [ $http_url != 404 ];then
    continue
    sleep 5
    continue
else
    TIME=$(date)
    sh /usr/local/restartTomcat62.sh
    echo [$TIME] STATUS:404 Tomcat has been restart! >> tomcat_dead.log
    sleep 5
    continue
fi
done







