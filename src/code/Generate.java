package code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class Generate {

     Random r = new Random();

     public void allTitle(int titleNumber,int numberBound){ //生成全部的题目
          BufferedWriter writeQuewtion = null;
          BufferedWriter writeAnswer = null;
          HashSet<Question> hashSet = new HashSet<>();//hashset去重
          try {
                writeQuewtion = new BufferedWriter(new FileWriter("src//dbfile//Exercises.txt"));
                writeAnswer = new BufferedWriter(new FileWriter("src//dbfile//Answers.txt"));
               for (int i = 0;i<titleNumber;i++){
                   Question question = new Question(numberBound);
                   if(!question.isLawful()&&hashSet.add(question)){//检测式子合法性
                       i--;
                       continue;
                   }
                   String title = question.getTitle();
                   String answer = question.getAnswer();

                   System.out.println(question);

                   //写入文件
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
