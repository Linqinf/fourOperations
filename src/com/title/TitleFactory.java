package com.title;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class TitleFactory {
    private TitleCreator creator = new TitleCreator();
    BufferedWriter writeQuestion = null;
    BufferedWriter writeAnswer = null;
    BufferedWriter writeLog = null;
    Title title = null;
    private static ArrayList<Title> TitlesBank = new ArrayList<>();//存储题目

    //文件
    File QuestionFile = null;
    File AnswerFile = null;
    File LogFile = null;

    public TitleFactory(File questionFile, File answerFile, File logFile) {
        QuestionFile = questionFile;
        AnswerFile = answerFile;
        LogFile = logFile;
    }

    public void generateAllTitle(int titleNumber, int numberBound){
        HashSet<Title> hashSet = new HashSet<>();//hashset去重
        try {
            writeQuestion = new BufferedWriter(new FileWriter(QuestionFile));
            writeAnswer = new BufferedWriter(new FileWriter(AnswerFile));
            writeLog = new BufferedWriter(new FileWriter(LogFile));
            for (int i = 1;i<=titleNumber;i++){
                title = generateTitle(numberBound);

                if(!hashSet.add(title)){
                    i--;
                    continue;
                }
                //输入数组
                TitlesBank.add(title);
                String question = title.getQuestion();
                String answer = title.getAnswer();

                System.out.println(i+".  "+question+" = "+answer);

                //写入文件
                writeLog.write(i+".  "+title.toString()); //写入一条式子
                writeLog.newLine();  //换行
                writeLog.flush();    //刷新管道
                writeQuestion.write(i+".  "+question); //写入一条式子
                writeQuestion.newLine();  //换行
                writeQuestion.flush();    //刷新管道
                writeAnswer.write(i+".  "+answer); //写入一条式子
                writeAnswer.newLine();  //换行
                writeAnswer.flush();    //刷新管道
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writeAnswer !=null){
                    writeAnswer.close(); //关闭管道
                }
                if(writeQuestion !=null){
                    writeQuestion.close(); //关闭管道
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    private Title generateTitle(int bound){
        return creator.CreateTitle(bound);
    }
    public ArrayList<Title> getpaper(){
        return TitlesBank;
    }
}
