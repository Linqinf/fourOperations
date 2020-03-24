package code;

public class Question {
    //domain实体 存储文件中的题目 增强可读性

    private String title;//存储题干(题目+选项)
    private String answer;//存储答案


    public Question(){}
    public Question(String title, String answer) {
        this.title = title;
        this.answer = answer;
    }


    //重写Question类中的两个方法  equals  hashCode
    //想要将Question对象存入HashSet集合内 让set集合帮我们去掉重复元素
    @Override
    public int hashCode(){ //根据题干来计算hashCode
//        String thisTitle = this.title;
//        return thisTitle.hashCode();//31*h
          return 1;
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj){ //地址一样
            return true;
        }
        if(obj instanceof Question){ //类型一样,都是question
            Question anotherQuestion = (Question)obj;
            String thisTitle = this.title;
            String anotherTitle = anotherQuestion.title;
            String[] thisNum = thisTitle.split( "\\+|\\-|\\*|\\/");
            String[] anotherNum = anotherTitle.split( "\\+|\\-|\\*|\\/");
            if(thisTitle.equals(anotherTitle)){ //整条式子都相等,一模一样
                return true;
            }
            if(thisTitle.length() == anotherTitle.length()){  // 6*8和8*6的情况
                if(thisTitle.charAt(3) == anotherTitle.charAt(3)){
                    if(thisNum[0].equals(anotherNum[1]) && thisNum[1].equals(anotherNum[0]))
                        return true;
                }
            }
        }
        return false;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
