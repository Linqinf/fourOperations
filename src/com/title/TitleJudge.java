package com.title;

import java.util.ArrayList;

public class TitleJudge {

    public boolean judgeTitle(Title title, int bound){
        if(title==null)//title为空时
            return false;
        boolean Lawful = judgeQuestion(title.getQuestion())
                &&judgeQuestionValue(title.getValue())
                &&judgeAnswer(title.getAnswer(),bound);

        return Lawful;
    }

    public boolean judgeQuestion(String question){//判断题目合法性
        if(question==null&&question.length()==0)
            return false;
        return true;
    }
    public boolean judgeQuestionValue(ArrayList<String> value){//判断题目数值数组合法性
        if(value==null&&value.size()==0)
            return false;
        return true;
    }
    public boolean judgeAnswer(String answer, int bound){//判断答案合法性
        boolean Lawful = true;
        if(answer==null||answer.contains("-")||answer.equals("")){//计算结果存在负号

            return false;
        }
        if(answer.contains("/")){//结果存在分数
            String[] split = answer.split("/");//分母
            String Denominator = split[split.length-1].trim();
            if(Integer.parseInt(Denominator)>=bound||Integer.parseInt(Denominator)==0){
                Lawful = false;
            }
        }
        return Lawful;
    }
}
