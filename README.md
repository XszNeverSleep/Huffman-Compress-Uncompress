# Huffman-Compress-Uncompress
一个基于哈夫曼的压缩和解压缩程序
理论上可以压缩大小为1TB的文件。
程序只能解压缩通过本程序压缩的哈弗后缀名为.hu的文件
## 下为压缩文件的内部构造
<img width="1002" alt="image" src="https://github.com/XszNeverSleep/Huffman-Compress-Uncompress/assets/107178476/dc94d797-daeb-40f8-a5a1-630d8bd71798">
首先是一个值为m的byte，后面m个byte的值通过基数为256的运算可以得到一个正整数123456789的magic number
接下来是通过先序遍历线性化的哈夫曼树
然后是一个值为n的byte，后面n个byte的值通过基数为256的运算可以得到压缩后文件的bits数（因为经过哈夫曼压缩后的文件bits数可能不是8的整数倍）
<br/>tips：
基数为256的运算：假设共有第一个byte的值为n
<br/>
k<sub>0</sub> * 256<sup>0</sup> + k<sub>1</sub> * 256<sup>1</sup> + .... + k<sub>n-1</sub> * 256<sup>n-1</sup> 
<br/>

## 解压缩的步骤
<br/>
首先计算Magic number等不等于123456789
接下来还原哈夫曼树，若哈夫曼树非序列化失败则说明压缩文件内部遭到破坏
接下来计算压缩后文件的bits数
再通过bits数和哈夫曼树还原出原本的文件

## 使用方法
运行HuffmanMain
