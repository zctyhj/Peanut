# 职业规划
1. 岗位 项目经理
2. 核心竞争力--行业经验，技术出身，个人实力
3. 保险行业 银行等行业
4. 价值体现技能系列文章编写

# 当前任务
1. git 知识点总结出一个文档，并实施一个git服务器   
2. 忆云笔记  开发   
3. 博客网站首页改造，提高访问速度  
4. 在apple上安装pycharm 并且通过网上教材激活到2089年，期间一直可用
5. UNIX人认为在到达一行的结尾时新增一行<Line feed> (LF)，而Mac人则认同<Return> (CR)的解决办法，MS则坚持古老的<Return><Line feed> (CRLF)的方法

作者：i林筱程
链接：https://www.jianshu.com/p/e2f4618e9abd
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
在线学习的七门课程为《金融SBU-神州数码融信软件有限公司介绍》、《金融SBU-信息安全管理》、《金融SBU-质量管理与质量意识》、《产品介绍-数据解决方案产品族》、《业务知识-银行存款业务》、《业务知识-银行资产业务》、《业务知识-银行会计核算》

| 总数 | 文档分类 | 已完成文档 | 完成日期 | 剩余文件数 |
| ---- | -------- | ---------- | -------- | ---------- |
| 66   | 文档整合 |            | 20200518 |    66      |
| 66   | 并发知识 |            | 20200524 |            |



## 20200524
岗前认证   已完成
| 认证课程 | 是否通过 | 通过分数 | 通过时间 |
| -------  | -------- | -------- | -------- |
|金融SBU-神州数码融信软件有限公司介绍|通过|80|20200512|
|金融SBU-信息安全管理|通过|86|20200517|
|业务知识-银行存款业务|通过|84|20200518|
|业务知识-银行会计核算|通过|90|20200524|
|业务知识-银行资产业务|通过|86|20200524|
|产品介绍-数据解决方案产品族|通过|84|20200524|
|金融SBU-质量管理与质量意识|通过|92|20200524|

> 结构变更 将当前任务笔记，拆分成两个，形成日志系列
> 把最近的日志放在当前任务笔记，后归档为年
> 每次任务完成，将所有的任务集合在一起，形成一份文档
> 考虑将博客全部改成文字形式，提高加载速度，访问速度。加强排版，更好体现文字博客特征

大数据就业岗位   
 1、ETL工程师   
 2、数据仓库工程师   
 3、大数据开发工程师   
 4、Hadoop工程师   
 5、Spark/Flink工程师   
 6、大数据分析师   
 7、大数据算法工程师   
 8、大数据运维工程师   
 9、大数据解决方案   

MySQL
select TABLE_SCHEMA,TABLE_NAME,COLUMN_TYPE,COLUMN_COMMENT frominformation_schema.columns where TABLE_SCHEMA='guifan' or  TABLE_SCHEMA='test'

ORACLE
--导出表名表注释、列名列注释
SELECT
  CLN.TABLE_NAME  AS "表名",
  TC.COMMENTS     AS "表名注释",
  CLN.COLUMN_NAME AS "列名",
  CMT.COMMENTS    AS "列注释"
FROM USER_TAB_COLUMNS cln
  LEFT JOIN USER_TAB_COMMENTS TC ON cmt.TABLE_NAME = TC.TABLE_NAME
  LEFT JOIN USER_COL_COMMENTS cmt ON cln.TABLE_NAME = cmt.TABLE_NAME
                                     AND cln.COLUMN_NAME = cmt.COLUMN_NAME
WHERE cln.TABLE_NAME LIKE '%表名关键字%';


## 20200526
> 将之前的文档统一改成md格式(已排版的除外)   决定不做


Hello, my name is hunter, 28 years old.I have 5 years of working experience.
Now I hopes to join in HSBC  to improve myself.
I am a dedicated person who is willing to face challenges. 
I have a strong sense of responsibility and am a fast learner. 
Due to this I make sure all work is completed to the highest of quality.
I am dying to join in HSBC,winning a platform to show my talents and create values for company.
Thanks for your attention.


#判断文件是否存在，存在则备份
echo "`date +'%Y%m%d%H%M%S'`  程序开始!" >> temp.log
if [ -f ${fileName} ]
    then
        mv ${fileName} ${newfile}
            echo "`date +'%Y%m%d%H%M%S'`  已经移动" >> temp.log
    else
            echo "`date +'%Y%m%d%H%M%S'`  该文件不存在，今天第一次" >> temp.log
fi

head -n 1 /path/file.txt

#!/bin/sh

#获取参数
fileName=$1
Num=$2

#判断文件是否存在
#如果存在输出前n行
if [ -f ${fileName} ]
    then
	    head -n ${Num} ${fileName}
    else
        echo "抱歉，文件 ${fileName} 不存在！"
fi


## 20200603
oracle 
SELECT CLN.TABLE_NAME AS "表名", TC.COMMENTS AS "表名注释", CLN.COLUMN_NAME AS "列名", 
cmt.COMMENTS AS "列注释" FROM USER_TAB_COLUMNS cln 
LEFT JOIN USER_TAB_COMMENTS TC ON TC.TABLE_NAME = cln.TABLE_NAME
LEFT JOIN USER_COL_COMMENTS cmt ON cln.TABLE_NAME = cmt.TABLE_NAME AND cln.COLUMN_NAME = cmt.COLUMN_NAME 
WHERE cln.TABLE_NAME = 'GL_ACCT'


## 20200611
争取权利

1. 工作所需是否齐全
2. 争取下午茶
3. 争取聚餐
4. 沟通计划

会议主持    ----规则，没有时间的可以不来，没有工作汇报的可以不来，争执会后讨论
1. 开会前半个小时，收取工作汇报
2. 会议计划时间为半个小时
3. 每个人只讲3件事情，做了什么，计划要完成什么，遇到有哪些问题

问题记录到会议记录，当场收集解决方案，如果没有，会后讨论

## 20200612
完成了获取数据字典的初步工作，主要逻辑已经编写好了，剩下的就是润色了


## 20200619
围绕目标

计划 执行 检视 改善

- 凡是工作，必有目标
- 凡是目标，必有计划
- 凡是计划，必有执行
- 凡是执行，必有结果
- 凡是结果，必有责任
- 凡是责任，必有检查
- 凡是检查，必有奖罚

## 20200805
1. 将大数据框架所需技术列出   已完成
2. 南方电网数研院数据仓库模型和指标体系设计项目   已换工作
3. 并发知识重翻   
4. 更新文档 worklog.xlsx  工作日志    
5. 20200708换煤气  充公交卡 已完成 离职证明拍照 已完成
6. 20200805   需要买牙刷
7. 周五晚上直播必须去  已完成
8.  深入理解java虚拟机  读完，输出读后感   
-javaagent:D:\Program Files\JetBrains\PyCharm 2019.3.4\lib\JetbrainsCrack-3.1-release-enc.jar   
9.  总结存储过程和常用数据类型   
10. 编写代码，生成excel版的数据字典，针对mysql和oracle 有时间就加上db2数据库
11. 

## 20200822
相比弄清楚哪些数据是有用的，直接保存所有数据更简便一些

今天搞定了git基本操作
