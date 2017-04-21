#!/bin/bash 
cat /home/bak/updir.conf | while read dir
do
ftp -v -n 10.255.12.2 << END
user yfjy yfjy
binary
hash
cd /YFJY201404
mkdir $dir
cd /YFJY201404/$dir
lcd /home/video_uploading/$dir
prompt
mput *
cd /YFJY201404
bye
END
sleep 1
echo "upload $dir successful" >> /home/bak/upload.log
done
