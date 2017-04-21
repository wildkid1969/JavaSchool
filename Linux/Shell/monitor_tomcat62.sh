#!/bin/bash
http_url=$(curl -I -m 10 -o /dev/null -s -w %{http_code}  http://testwx.mdata.hc360.com/mobileaccount/saleUser/userIndex?phone=37863e32ce5d67c0be9ddf)
echo $http_url >> /usr/local/test.txt
if [[ $http_url = 502 ]] || [[ $http_url = 404 ]];then
    source /etc/profile.d/java.sh
    echo bad `date --date='0 days ago' "+%Y-%m-%d %H:%M:%S"` >> /usr/local/test.txt
    /usr/local/tomcat62/bin/startup.sh
else
    echo "good"
fi
