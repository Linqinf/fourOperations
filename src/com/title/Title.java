package com.title;



import java.util.ArrayList;

public class Title {
    private String question;
    private String answer;
    private ArrayList<String> value;
    public Title(String question,String answer,ArrayList<String> value){
        this.question = question;
        this.answer = answer;
        this.value = value;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public ArrayList<String> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return question+" = "+answer;
    }
    //重写Question类中的两个方法  equals  hashCode
    //想要将Question对象存入HashSet集合内 让set集合帮我们去掉重复元素
    @Override
    public int hashCode(){ //默认hashcode一样
        return 1;//answer.hashCode();
    }
    @Override
    public boolean equals(Object obj){//比较两个对象是否一致
        if(answer==null||obj==null){
            return false;
        }
        if(this==obj){ //地址一样
            return true;
        }
        if(obj instanceof Title){ //类型一样,都是question
            Title other = (Title) obj;
            if(!answer.equals(other.answer))//先检查答案是否一致
                return false;
            if(value.size()!=other.value.size())
                return false;
            for(int i=0;i<other.value.size();i++){
                if(!value.contains(other.value.get(i))){
                    return  false;
                }
            }
            return true;
        }
        return false;
    }
}
