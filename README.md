## 实现一个自动生成小学四则运算题目的命令行程序
+ TitleFactory类：   
1.public TitleFactory(File questionFile, File answerFile, File logFile)  
questionFile：输出问题文件  
answerFile：输出答案文件  
logFile：输出日志文件  
2.public void generateAllTitle(int titleNumber, int numberBound)     
titleNumber：题目数量  
numberBound：数值范围  
生成题目打印在文件中

+ CheckAnswers类   
public void Correct(File exercisefile, File answerfile)  
exercisefile：习题文件  
answerfile：答案文件  
自动在answerfile文件的目录下生成Correction.txt文件,里面包含了对回答的正确与错误的统计