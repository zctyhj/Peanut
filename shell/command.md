# 列出当前路径下的全路径
```
ls | sed "s:^:`pwd`/:"
```
# 切割文件名的方法
```
echo "huangjin.txt" | cut -d'.' -f1
```
