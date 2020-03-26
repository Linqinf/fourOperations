package code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class Generate {

     public static void allTitle(int titleNumber,int numberBound){ //生成全部的题目
          BufferedWriter writeQuewtion = null;
          BufferedWriter writeAnswer = null;
         BufferedWriter writeLog = null;
          HashSet<Question> hashSet = new HashSet<>();//hashset去重
          try {
                writeQuewtion = new BufferedWriter(new FileWriter("src//dbfile//Exercises.txt"));
                writeAnswer = new BufferedWriter(new FileWriter("src//dbfile//Answers.txt"));
                writeLog = new BufferedWriter(new FileWriter("src//dbfile//title+answer.txt"));
               for (int i = 1;i<=titleNumber;i++){
                   Question question = new Question(numberBound);
                   if(!question.isLawful()&&hashSet.add(question)){//检测式子合法性
                       i--;
                       continue;
                   }
                   String title = i+".  "+question.getTitle();
                   String answer = i+".  "+question.getAnswer();

                   System.out.println(i+".  "+question);

                   //写入文件
                   writeLog.write(i+".  "+question.toString()); //写入一条式子
                   writeLog.newLine();  //换行
                   writeLog.flush();    //刷新管道
                   writeQuewtion.write(title); //写入一条式子
                   writeQuewtion.newLine();  //换行
                   writeQuewtion.flush();    //刷新管道
                   writeAnswer.write(answer); //写入一条式子
                   writeAnswer.newLine();  //换行
                   writeAnswer.flush();    //刷新管道
               }
          } catch (IOException e) {
               e.printStackTrace();
          }finally {
               try {
                    if(writeAnswer !=null){
                        writeAnswer.close(); //关闭管道
                    }
                   if(writeQuewtion !=null){
                       writeQuewtion.close(); //关闭管道
                   }
               }catch (IOException e){
                    e.printStackTrace();
               }

          }

     }
}
