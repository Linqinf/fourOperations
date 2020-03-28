package com.title;

import com.title.Answer.Calculator;
import com.title.Question.QuestionCreator;

import java.util.ArrayList;

public class TitleCreator {

    private Title title = null;
    private QuestionCreator questionCreator = new QuestionCreator();
    private Calculator calculator = new Calculator();
    private TitleJudge Judge = new TitleJudge();

    public Title CreateTitle(int numberBound){
        do{
            //构造题目
            String question = createQuestion(numberBound);
            ArrayList<String> value = getQuestionValue();
            String answer = createAnswer(question);

            title = new Title(question,answer,value);


        }while (!Judge(title, numberBound));//直到生成合法题目为止

        return title;
    }

    private String createQuestion(int bound){
        return questionCreator.CreateQuestion(bound);
    }
    private ArrayList<String> getQuestionValue(){
        return questionCreator.getValue();
    }
    private String createAnswer(String question){
        return calculator.calculate(question);
    }
    private boolean Judge(Title title,int bound){

        boolean Lawful = Judge.judgeTitle(title,bound)
                && calculator.isLawful();
        return Lawful;
    }
}
