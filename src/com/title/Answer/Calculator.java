package com.title.Answer;

import java.util.HashSet;

public class Calculator {
    private boolean Lawful = true;//缓存当前计算合法性
    OperatorSearcher searcher = new OperatorSearcher();
    public String calculate(String title){
/*
        if(title!=null)
            return null;
*/
        return SimplifyFraction(Run(title));
    }
    public String Run(String title){
        Lawful = true;
        while ((title.contains("*")||title.contains("÷")||
                title.contains("+")||title.contains("-"))&&Lawful){//存在运算符且当前运算仍合法
            int index = -1,start = 0,end = 0;//记录运算符位标，括号的开始与结束位标
            String[] Message = title.split(" ");//分割字符串
            //检测当前表达式中各元素的合法性
            checkExpressionLawful(Message);
            String temp = null;//将结果替换子表达式
            //记录括号的开始与结束位标
            start = Search(Message,"(");
            end = Search(Message,")");
            if(start!=-1&&end!=-1){//存在括号
                StringBuilder Subexpression = new StringBuilder();//括号内表达式
                for(int i=start+1;i<end;i++) {//构造子表达式
                    Subexpression.append(Message[i]).append(" ");
                }
                //重新构造题干信息
                StringBuilder titleBuilder = new StringBuilder();
                for(int i = 0; i<start; i++) {
                    titleBuilder.append(Message[i]).append(" ");
                }
                title = titleBuilder.toString();
                title += Run(Subexpression.toString());
                StringBuilder titleBuilder1 = new StringBuilder(title);
                for(int i = end+1; i<Message.length; i++) {
                    titleBuilder1.append(Message[i]).append(" ");
                }
                title = titleBuilder1.toString();
                continue;
            }
            //定位运算符，先*、÷后+、-
            index = Search(Message,"*|÷");
            if(index == -1){//式子内没有乘除时
                index = Search(Message,"+|-");
            }

            if(index==-1){//不存在运算符时，运算结束，跳出循环
                break;
            }
            String[] firstNum = transformFraction(Message[index-1]).split("/");
            String[] secondNum = transformFraction(Message[index+1]).split("/");

            switch (Message[index]) {
                case "+": //加法操作
                    if (!Message[index - 1].contains("/") && !Message[index + 1].contains("/"))//不存在分数,直接相加
                        temp = Integer.parseInt(Message[index - 1]) + Integer.parseInt(Message[index + 1]) + "";
                    else if (Message[index - 1].contains("/") && Message[index + 1].contains("/")) {//两个都为分数，化同分母，再相加

                        int Denominator = Integer.parseInt(firstNum[1]) * Integer.parseInt(secondNum[1]);
                        int numerator = Integer.parseInt(firstNum[1]) * Integer.parseInt(secondNum[0])
                                + Integer.parseInt(secondNum[1]) * Integer.parseInt(firstNum[0]);
                        temp = numerator + "/" + Denominator;
                    } else {//只有一个分数，整数化为分数相加
                        int Denominator, numerator;
                        if (Message[index - 1].contains("/")) {//第一个操作数为分数
                            Denominator = Integer.parseInt(firstNum[1]);
                            numerator = Integer.parseInt(firstNum[1]) * Integer.parseInt(secondNum[0]) + Integer.parseInt(firstNum[0]);
                        } else {//第二个操作数为分数
                            Denominator = Integer.parseInt(secondNum[1]);
                            numerator = Integer.parseInt(secondNum[1]) * Integer.parseInt(firstNum[0]) + Integer.parseInt(secondNum[0]);
                        }
                        temp = numerator + "/" + Denominator;
                    }
                    break;
                case "-": //减法操作

                    if (!Message[index - 1].contains("/") && !Message[index + 1].contains("/"))//不存在分数
                        temp = Integer.parseInt(Message[index - 1]) - Integer.parseInt(Message[index + 1]) + "";
                    else if (Message[index - 1].contains("/") && Message[index + 1].contains("/")) {//两个都为分数，化同分母，再相减

                        int Denominator = Integer.parseInt(firstNum[1]) * Integer.parseInt(secondNum[1]);
                        int numerator = Integer.parseInt(firstNum[0]) * Integer.parseInt(secondNum[1])
                                - Integer.parseInt(secondNum[0]) * Integer.parseInt(firstNum[1]);
                        temp = numerator + "/" + Denominator;
                    } else {//只有一个分数，整数化为分数相减
                        int Denominator, numerator;
                        if (Message[index - 1].contains("/")) {//第一个操作数是分数
                            Denominator = Integer.parseInt(firstNum[1]);
                            numerator = Integer.parseInt(firstNum[0]) - Integer.parseInt(secondNum[0]) * Denominator;

                        } else {//第二个操作数是分数
                            Denominator = Integer.parseInt(secondNum[1]);
                            numerator = Integer.parseInt(firstNum[0]) * Denominator - Integer.parseInt(secondNum[0]);
                        }
                        temp = numerator + "/" + Denominator;
                    }
                    break;
                case "*": //乘法操作
                    if (!Message[index - 1].contains("/") && !Message[index + 1].contains("/")) {//不存在分数，直接相乘
                        temp = Integer.parseInt(Message[index - 1]) * Integer.parseInt(Message[index + 1]) + "";

                    } else if (Message[index - 1].contains("/") && Message[index + 1].contains("/")) {//两个都为分数，分子分母相乘

                        int Denominator = Integer.parseInt(firstNum[1]) * Integer.parseInt(secondNum[1]);
                        int numerator = Integer.parseInt(firstNum[0]) * Integer.parseInt(secondNum[0]);
                        temp = numerator + "/" + Denominator;
                    } else {//只有一个分数，整数乘以分子作为新分子，分母不变
                        int Denominator, numerator;
                        if (Message[index - 1].contains("/")) {//第一个操作数是分数
                            Denominator = Integer.parseInt(firstNum[1]);
                        } else {//第二个操作数是分数
                            Denominator = Integer.parseInt(secondNum[1]);
                        }
                        numerator = Integer.parseInt(firstNum[0]) * Integer.parseInt(secondNum[0]);

                        temp = numerator + "/" + Denominator;
                    }
                    break;
                case "÷": //除法操作
                    if (!Message[index - 1].contains("/") && !Message[index + 1].contains("/"))//不存在分数，直接构造分数
                        if (Message[index - 1].equals("0"))
                            temp = "0";
                        else
                            temp = Message[index - 1] + "/" + Message[index + 1];
                    else if (Message[index - 1].contains("/") && Message[index + 1].contains("/")) {//两个都为分数，分子，分母交叉相乘

                        int Denominator = Integer.parseInt(firstNum[1]) * Integer.parseInt(secondNum[0]);
                        int numerator = Integer.parseInt(firstNum[0]) * Integer.parseInt(secondNum[1]);
                        temp = numerator + "/" + Denominator;
                    } else {//只有一个分数
                        int Denominator, numerator;
                        if (Message[index - 1].contains("/")) {//第一个操作数是分数
                            Denominator = Integer.parseInt(firstNum[1]) * Integer.parseInt(secondNum[0]);
                            numerator = Integer.parseInt(firstNum[0]);
                        } else {//第二个操作数是分数
                            Denominator = Integer.parseInt(secondNum[0]);
                            numerator = Integer.parseInt(firstNum[0]) * Integer.parseInt(secondNum[1]);
                        }
                        temp = numerator + "/" + Denominator;
                    }
                    break;
            }
            //构造新题干
            StringBuilder titleBuilder = new StringBuilder();
            for(int i = 0; i<index-1; i++) {
                titleBuilder.append(Message[i]).append(" ");
            }
            title = titleBuilder.toString();
            title += temp+" ";
            StringBuilder titleBuilder1 = new StringBuilder(title);
            for(int i = index+2; i<Message.length; i++) {
                titleBuilder1.append(Message[i]).append(" ");
            }
            title = titleBuilder1.toString();
        }
        if(Lawful)
            return title;
        else
            return null;
    }

    private int Search(String[] Msg,String tag){
        return searcher.searchOperator(Msg, tag);
    }
    private String transformFraction(String Fraction){//将真分数转化成假分数
        Fraction = Fraction.trim();
        if(!Fraction.contains("'"))//不是真分数
            return Fraction;
        String[] split = Fraction.split("['/]");
        //分子，分母操作
        int Denominator  = Integer.parseInt(split[2]);
        int numerator = Denominator*Integer.parseInt(split[0])+Integer.parseInt(split[1]);
        return numerator+"/"+Denominator;
    }
    private String SimplifyFraction(String Fraction) {//将分数化简：假分数转化成真分数，分数的约分
        if(!Lawful){
            return null;
        }
        if (Fraction.contains("("))
            Fraction = Fraction.replaceAll("\\(|\\)","");
        Fraction = Fraction.trim();
        int count = 0;//记录进位
        String[] digit = null;//储存分数中每一个数字
        int Denominator = 0 ;//分母
        int numerator = 0 ;//分子
        if(!Fraction.contains("/"))//不存在分数,无需化简，直接返回
            return Fraction;
        if(Fraction.contains("'")){
            count = Integer.parseInt(Fraction.split("'")[0]);
            Fraction = Fraction.split("'")[1];
        }
        digit = Fraction.trim().split("/");
        //生成分子，分母
        numerator = Integer.parseInt(digit[0]);
        Denominator = Integer.parseInt(digit[1]);
        //分子为0
        if(numerator==0){
            return "0";
        }
        //分数进位操作
        if(numerator>=Denominator){//分子大于或等于分母
            if (Denominator == 0) {
                Lawful = false;
                return null;
            }
            count += numerator/Denominator;
            numerator = numerator%Denominator;
        }
        //分数约简操作，将分子分母的因子加入hashset中，若插入不成功即该数为公约数
        while (true) {
            boolean exitCommon = false;//记录是否存在公约是
            HashSet<Integer> set = new HashSet<>();//记录所有公约数
            //加入其本身
            for(int i=2;i<=Math.sqrt(numerator);i++){//构造分子所有公约数
                if(numerator%i==0){
                    set.add(i);
                }
            }
            set.add(numerator);
            for(int i=2;i<=Math.sqrt(Denominator);i++){
                if(Denominator%i==0){
                    if(!set.add(i)){//出现相同公约数
                        numerator /= i;
                        Denominator /= i;
                        exitCommon = true;
                        break;
                    }
                }
            }
            if (!exitCommon) {//若不出现公约数，即判定为最简分数
                break;
            }
        }
        //构造分数字符串
        Fraction = "";
        if(count!=0){//进位不为0
            Fraction += count+"'";
        }
        if(numerator!=0)//分子不为0
            Fraction += +numerator+"/"+Denominator;
        else {//分子为0，去除符号‘'’
            Fraction = Fraction.replaceAll("'","");
        }
        return Fraction;
    }
    private void checkExpressionLawful(String[] Message){//添加合法判断条件
        //判断是否是负数
        for (String s : Message) {
            if (s.length() > 1 && s.contains("-")) {
                Lawful = false;
                break;
            }
        }

    }
    public boolean isLawful(){
        return Lawful;
    }
}
