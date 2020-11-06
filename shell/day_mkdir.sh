#!/bin/bash
#-----------------------------------------------------------------------------------------------------------------------
#
#          功能： 提前创建好文件夹
#
#          开发： 黄进
#          日期： 20190713
#
#-----------------------------------------------------------------------------------------------------------------------
cd /home/huang/data
datafile=/home/huang/data/`date +%Y%m%d`
odsfile=/home/huang/ods/`date +%Y%m%d`


nowt=`date +'%Y%m%d%H%M%S'`

# 创建data 的日期目录
if [ ! -d $datafile ];then
    mkdir $datafile
    echo $datafile" created. "$nowt >> /home/huang/data/temp.log
    else
    echo $datafile"  文件夹已经存在 "$nowt >> /home/huang/data/temp.log
fi

# 创建ods 的日期目录
if [ ! -d $odsfile ];then
    mkdir $odsfile
    echo $odsfile" created. "$nowt >> /home/huang/data/temp.log
    else
    echo $odsfile"  文件夹已经存在 "$nowt >> /home/huang/data/temp.log
fi


