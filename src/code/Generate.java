package code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generate {

     Random r = new Random();
     public void allTitle(int titleNumber,int numberBound){ //生成全部的题目
          BufferedWriter bw = null;
          try {
                bw = new BufferedWriter(new FileWriter("src//dbfile//Exercises.txt"));
               for (int i = 0;i<titleNumber;i++){
                    System.out.println(generateOne(numberBound));
                    String one = generateOne(numberBound);
                    bw.write(one); //写入一条式子
                    bw.newLine();  //换行
                    bw.flush();    //刷新管道
               }
          } catch (IOException e) {
               e.printStackTrace();
          }finally {
               try {
                    if(bw !=null){
                         bw.close(); //关闭管道
                    }
               }catch (IOException e){
                    e.printStackTrace();
               }

          }

     }
     public String generateOne(int bound){ //只生成一次题目
              char []operator = new char[3];
              String []Number = new String[4];
              int operatorNum = r.nextInt(3)+1;//运算符数量， 不超过3个
              int divideNum = 0;
              for(int i = 0;i < operatorNum;i++){ //根据运算符数量来循环拼接
                   int temp = r.nextInt(4);
                   if(temp == 0)
                        operator[i] = '+';
                   else if (temp == 1)
                        operator[i] = '-';
                   else if (temp == 2){
                        divideNum++;
                        operator[i] = '÷';
                   }
                   else if (temp == 3)
                        operator[i] = '*';
              }
              for(int i = 0;i < operatorNum+1 ; i++){
                   Number[i] = " " + r.nextInt(bound) + " ";
              }


              //是否变为分数
//         divideNum = 2 ;
//         operatorNum =3;
//         operator[0] = '÷';
//         operator[1] = '+';
//         operator[2] = '÷';
//         Number[0] = " 1 ";
//         Number[1] = " 4 ";
//         Number[2] = " 1 ";
//         Number[3] = " 6 ";
         if(divideNum >= 1 && operatorNum > 1){ //出现除号，要检查
              int flag = 0;
              for(int i =0;i < operatorNum ; i++){ //遍历所有的运算符，找到第一个除号
                   if( (operator[i] == '÷' && flag ==0) || (i==2 && operator[2]=='÷' && flag ==1 )) {
                        int Num1 = Integer.parseInt(Number[i].trim());
                        int Num2 = Integer.parseInt(Number[i+1].trim());
                        if(Num2 != 0){ //除数不能为0
                             if(Num1 > Num2) { //出现了假分数，要转化
                                  if(Num1%Num2 == 0)
                                       Number[i] = " "+ Num1/Num2 ;
                                  else
                                       Number[i] = " "+ Num1/Num2 + "'" + Num1%Num2 + "/" + Num2;
                             }
                             else{ //真分数
                                  Number[i] = " " + Num1 + "/" + Num2;
                             }
                        }
                        operator[i] = '\0';
                        Number[i+1] = "";
                        if(i == 1) //如果是第二个运算符为除号，后边不检查第三个运算符的情况了。
                             flag = 2;
                        else
                             flag++; //若第一次变为1，还要检查第三个运算符除号的情况
                   }
              }
         }



         //拼接运算式
         String result = Number[0] + operator[0] + Number[1];
         if(operatorNum>1)  result += operator[1] + Number[2];
         if(operatorNum==3)  result += operator[2] + Number[3];
         return result;
    }

}
