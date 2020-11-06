#!/bin/bash

cd /home/huang/data

file=/home/huang/data/`date +%Y%m%d`
newfile="bankgold_`date +'%Y%m%d%H%M%S'`.json"
fileName="bankgold_`date +'%Y%m%d'`.json"

if [ ! -d $file ];then
    mkdir `date +%Y%m%d`
fi

cd /home/huang/data/`date +%Y%m%d`

#判断文件是否存在，存在则备份
echo "`date +'%Y%m%d%H%M%S'`  程序开始!" >> temp.log
if [ -f ${fileName} ]
    then
        mv ${fileName} ${newfile}
            echo "`date +'%Y%m%d%H%M%S'`  已经移动" >> temp.log
    else
            echo "`date +'%Y%m%d%H%M%S'`  该文件不存在，今天第一次" >> temp.log
fi

#下载文件
wget -q -O ${fileName} http://web.juhe.cn:8080/finance/gold/bankgold?key=d079671d39ae5259cb9dba6fe1b8828c
echo "`date +'%Y%m%d%H%M%S'`  ${fileName} 下载完毕!" >> temp.log

#转换文件
python /home/huang/data/json2del.py 'bankgold' `date +%Y%m%d`
echo "`date +'%Y%m%d%H%M%S'`  ${fileName} 添加完毕!" >> temp.log

echo "`date +'%Y%m%d%H%M%S'`  程序结束!" >> temp.log


