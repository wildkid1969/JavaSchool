#!/bin/sh
cat ./updir.conf | while read one
do
ftp -v -n 10.255.12.2 << END
user yfjy yfjy
binary
hash
cd YFJY201404
cd $one
mdelete *
cd ..
bye
END
done
