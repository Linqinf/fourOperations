package code;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

public class QuestionFileReader {

    //程序执行时 将文件中的所有题目 一次性都读取出来
    //private HashSet<Question> questionBox = new HashSet<>();
      private HashMap<String,String> questionBox = new HashMap<>();
    {  //放在代码块，自动运行以下代码
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src//dbfile//title+answer.txt"));
            String message = reader.readLine();//每一次读取一行  title#answer
            while(message!=null){
                String[] values = message.split("#");
                 questionBox.put(values[0],values[1]);
                message = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String,String> getQuestionBox(){
        return questionBox;
    }

}
