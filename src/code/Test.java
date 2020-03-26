package code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Test {
   public static void main(String[] args){
//      QuestionFileReader qf = new QuestionFileReader();
//      HashSet<Question> a = qf.getQuestionBox();
//      HashMap<String,String> a = qf.getQuestionBox();
//      System.out.println(a.get(" 4 + 5 + 8 "));
//      System.out.println(a.size());

//      Generate g = new Generate();
//      g.allTitle(10000,100);
      System.out.println(Question.Tools().calculate("( 4 + 5 ) ÷ 2"));
   }
}
