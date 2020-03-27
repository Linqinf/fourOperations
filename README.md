## 实现一个自动生成小学四则运算题目的命令行程序
+ Question类：  
静态方法：  
public static String Calculator(String title)  
title应符合计算式格式，例如( 4 + 2 ) * 3  
返回计算结果  

+ CheckAnswers类  
静态方法：  
public static void Correct(File exercisefile, File answerfile)  
自动在answerfile文件的目录下生成Correction.txt文件,里面包含了对回答的正确与错误的统计