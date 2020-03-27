package code;

import java.io.*;
import java.util.ArrayList;

public class CheckAnswers {
    public static void main(String[] args) {
        Correct(new File("C:\\5069\\Java\\测试\\e.txt"),new File("C:\\5069\\Java\\测试\\a.txt"));

    }
    public static void Correct(File exercisefile, File answerfile){
        if(!exercisefile.isFile()||!answerfile.isFile()){
            System.out.println("文件错误");
        }
        //统计题号链表
        ArrayList<Integer> correct = new ArrayList<>();
        ArrayList<Integer> wrong = new ArrayList<>();
        //io流
        BufferedReader exeReader = null;
        BufferedReader ansReader = null;
        BufferedWriter gradeWriter = null;
        try {
            int count = 1;
            //创建文件
            File outfile = new File(answerfile.getParent()+"\\Correction.txt");
            outfile.createNewFile();
            exeReader = new BufferedReader(new FileReader(exercisefile));
            ansReader = new BufferedReader(new FileReader(answerfile));
            gradeWriter = new BufferedWriter(new FileWriter(outfile));

            //统计答案
            while (true) {
                String question = exeReader.readLine();
                String answer = ansReader.readLine();
                if(question==null||answer==null)//到达空行时
                    break;
                //去除题号标记
                String[] Msg = question.split("\\.");
                question = Msg[Msg.length-1].trim();
                Msg = answer.split("\\.");
                answer = Msg[Msg.length-1].trim();
                //答案正确
                if(answer.equals(Question.Calculator(question))
                        &&question.length()!=0&&answer.length()!=0){
                    correct.add(count);
                    //System.out.println(count+". "+question+" = "+answer+" 正确");
                }
                //答案错误
                else {
                    wrong.add(count);
                    //System.out.println(question+" = "+answer+" 错误");
                }
                count++;
            }

            //输出批改文件
            gradeWriter.write("Correct: "+correct.size()+" (");
            for(int i=0;i<correct.size();i++){
                if(i!=0){
                    gradeWriter.write(",");
                }
                gradeWriter.write(correct.get(i).toString());
            }
            gradeWriter.write(")");
            gradeWriter.newLine();

            gradeWriter.write("Wrong: "+wrong.size()+" (");
            for(int i=0;i<wrong.size();i++){
                if(i!=0){
                    gradeWriter.write(",");
                }
                gradeWriter.write(wrong.get(i).toString());

            }
            gradeWriter.write(")");
            gradeWriter.flush();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                if (exeReader != null) {
                    exeReader.close();
                }
                if (ansReader != null) {
                    ansReader.close();
                }
                if (gradeWriter != null) {
                    gradeWriter.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
