#/usr/bin/python
# -*- coding: utf-8 -*-
import json
import sys
'''
获取文件并将文件的内容读取出来，按照csv的格式输出
一次性读取一天的数据，每天12点半开始读取，生成
eg:
{'resultcode': '200', 'reason': 'SUCCESSED!', 'result':
[{'1': {'variety': '美元账户黄金', 'midpri': '1328.44', 'buypri': '1327.54', 'sellpri': '1329.34', 'maxpri': '1328.44', 'minpri': '1326.00', 'todayopen': '1326.00', 'closeyes': '1325.76', 'quantpri': '1.79', 'time': '2019-06-12 00:59:00.0'},
'2': {'variety': '美元账户白银', 'midpri': '14.80', 'buypri': '14.75', 'sellpri': '14.85', 'maxpri': '14.80', 'minpri': '14.76', 'todayopen': '14.76', 'closeyes': '14.76', 'quantpri': '-0.01', 'time': '2019-06-12 00:59:00.0'},
'3': {'variety': '美元账户铂金', 'midpri': '815.50', 'buypri': '809.50', 'sellpri': '821.50', 'maxpri': '816.00', 'minpri': '812.50', 'todayopen': '813.00', 'closeyes': '812.00', 'quantpri': '-2.50', 'time': '2019-06-12 00:59:00.0'},
'4': {'variety': '美元账户钯金', 'midpri': '1395.50', 'buypri': '1386.50', 'sellpri': '1404.50', 'maxpri': '1395.50', 'minpri': '1384.00', 'todayopen': '1387.50', 'closeyes': '1388.00', 'quantpri': '-1.50', 'time': '2019-06-12 00:59:00.0'},
'5': {'variety': '人民币账户黄金', 'midpri': '295.19', 'buypri': '294.99', 'sellpri': '295.39', 'maxpri': '295.23', 'minpri': '294.59', 'todayopen': '294.59', 'closeyes': '294.59', 'quantpri': '0.40', 'time': '2019-06-12 00:59:00.0'},
'6': {'variety': '人民币账户白银', 'midpri': '3.29', 'buypri': '3.28', 'sellpri': '3.30', 'maxpri': '3.29', 'minpri': '3.28', 'todayopen': '3.28', 'closeyes': '3.28', 'quantpri': '0.00', 'time': '2019-06-12 00:59:00.0'},
'7': {'variety': '人民币账户铂金', 'midpri': '181.21', 'buypri': '180.01', 'sellpri': '182.41', 'maxpri': '181.32', 'minpri': '180.43', 'todayopen': '180.43', 'closeyes': '180.43', 'quantpri': '-0.42', 'time': '2019-06-12 00:59:00.0'},
'8': {'variety': '人民币账户钯金', 'midpri': '310.09', 'buypri': '308.29', 'sellpri': '311.89', 'maxpri': '310.20', 'minpri': '307.42', 'todayopen': '308.42', 'closeyes': '308.42', 'quantpri': '-0.13', 'time': '2019-06-12 00:59:00.0'}}
], 'error_code': 0}
参数是日期  路径

分别使用3个功能读取文件
1. 黄金数据
2. 天气数据
3. temp文件
'''

def weather2del(par_date,file_path):
    with open('/home/huang/data/'+par_date+'/'+file_path, 'r') as json_file:
        data = json.loads(json_file)
        print(data)
        print(data['resultcode'])


def bankgold(par_date,file_path):
    with open('/home/huang/data/'+par_date+'/'+file_path, 'r') as json_file:
        with open('/home/huang/ods/'+par_date+'/'+file_path+'.txt', 'a') as txt_file:
            indexs=['1','2','3','4','5','6','7','8']
            data = json.load(json_file)
            for index in indexs:
                txt_file.write(data['result'][0][index]['variety']+'|')
                txt_file.write(data['result'][0][index]['midpri']+'|')
                txt_file.write(data['result'][0][index]['buypri']+'|')
                txt_file.write(data['result'][0][index]['sellpri']+'|')
                txt_file.write(data['result'][0][index]['maxpri']+'|')
                txt_file.write(data['result'][0][index]['minpri']+'|')
                txt_file.write(data['result'][0][index]['todayopen']+'|')
                txt_file.write(data['result'][0][index]['closeyes']+'|')
                txt_file.write(data['result'][0][index]['quantpri']+'|')
                txt_file.write(data['result'][0][index]['time']+'|')
                txt_file.write(par_date+'|'+'\n')

def shgold(par_date,file_path):
    with open('/home/huang/data/'+par_date+'/'+file_path, 'r') as json_file:
        with open('/home/huang/ods/'+par_date+'/'+file_path+'.txt', 'a') as txt_file:
            indexs=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16']
            data = json.load(json_file)
            for index in indexs:
                txt_file.write(data['result'][0][index]['variety']+'|')
                txt_file.write(data['result'][0][index]['latestpri']+'|')
                txt_file.write(data['result'][0][index]['openpri']+'|')
                txt_file.write(data['result'][0][index]['maxpri']+'|')
                txt_file.write(data['result'][0][index]['minpri']+'|')
                txt_file.write(data['result'][0][index]['limit']+'|')
                txt_file.write(data['result'][0][index]['yespri']+'|')
                txt_file.write(data['result'][0][index]['totalvol']+'|')
                txt_file.write(data['result'][0][index]['time']+'|')
                txt_file.write(par_date+'|'+'\n')

def     temp(par_date,file_path):
    with open('/home/huang/data/'+par_date+'/'+file_path, 'r') as json_file:
        data = json.load(json_file)
        print(data)
        print(data['resultcode'])

if __name__ == "__main__":
    biz_falg=sys.argv[1]
    par_date=sys.argv[2]
    if('shgold'==biz_falg):
        shgold(par_date,"shgold_"+par_date+".json")
    else:
        bankgold(par_date,"bankgold_"+par_date+".json")



