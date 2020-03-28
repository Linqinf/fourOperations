package com.title.Question;

import java.util.ArrayList;
import java.util.Random;

public class QuestionCreator {
    Random r = new Random();
    private ArrayList<String> value = null;
    public String CreateQuestion(int bound){
        value = new ArrayList<>();
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
        if(divideNum >= 1 && operatorNum > 1) { //出现除号，要检查
            int flag = 0;
            for (int i = 0; i < operatorNum; i++) { //遍历所有的运算符，找到第一个除号
                if ((operator[i] == '÷' && flag == 0) || (i == 2 && operator[2] == '÷' && flag == 1)) {
                    int Num1 = Integer.parseInt(Number[i].trim());
                    int Num2 = Integer.parseInt(Number[i + 1].trim());
                    if (Num2 != 0) { //除数不能为0
                        if (Num1 > Num2) { //出现了假分数，要转化
                            if (Num1 % Num2 == 0)
                                Number[i] = " " + Num1 / Num2;
                            else
                                Number[i] = " " + Num1 / Num2 + "'" + Num1 % Num2 + "/" + Num2;
                        } else { //真分数
                            Number[i] = " " + Num1 + "/" + Num2;
                        }
                        operator[i] = '\0';
                        Number[i + 1] = "";
                    }

                    if (i == 1) //如果是第二个运算符为除号，后边不检查第三个运算符的情况了。
                        flag = 2;
                    else
                        flag++; //若第一次变为1，还要检查第三个运算符除号的情况
                }
            }
        }
        //构建运算数组
        for(int i=0;i<Number.length;i++){
            if(Number[i]!=""&&Number[i]!=null){
                value.add(Number[i].trim());
            }
        }
        //拼接运算式
        String result = null;
        String leftBrcket = " (";
        String rightBrcket = ") ";
        if((operator[1]=='+' || operator[1]=='-') && operator[0] != '\0' && r.nextBoolean()== true) {
            if(Number[3] == "" )  rightBrcket =" )";
            if(Number[3] == null) Number[3] = "";
            result = Number[0] + operator[0] + leftBrcket + Number[1] +  //在式子中间（第二个运算符）加括号
                    operator[1] + Number[2] + rightBrcket + operator[2] + Number[3];
        }
        else {//无括号
            if ((operator[0] == '+' || operator[0] == '-') && operator[1] != '\0' && r.nextBoolean() == true)
                result = leftBrcket + Number[0] + operator[0] + Number[1] + rightBrcket; //在最开始加括号
            else result = Number[0] + operator[0] + Number[1];  //无括号

            if ((operator[2] == '+' || operator[2] == '-') && operator[1] != '\0' && r.nextBoolean() == true) {
                result += operator[1] + leftBrcket + Number[2] + operator[2] + Number[3] + rightBrcket; //在最后加括号
            } else {
                if (operatorNum > 1) result += operator[1] + Number[2]; //无括号
                if (operatorNum == 3) result += operator[2] + Number[3]; //无括号
            }
        }
        return  result.replaceAll("\0"," ").trim();
    }

    public ArrayList<String> getValue() {
        return value;
    }
}
