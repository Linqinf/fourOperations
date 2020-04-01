package com.Test;

import com.title.Answer.CheckAnswers;
import com.title.TitleFactory;

import java.io.File;

public class TestTitleModule {
    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
//        TitleFactory titleFactory = new TitleFactory();
//        titleFactory.generateAllTitle(10000,100);
        CheckAnswers checkAnswers = new CheckAnswers();
        checkAnswers.Correct(new File("C:\\5069\\Java\\测试\\递归测试\\Exercises.txt"),
                new File("C:\\5069\\Java\\测试\\递归测试\\Answers.txt"));
        long time2 = System.currentTimeMillis();
        System.out.println("运行时间:"+(time2-time1)+"ms");
    }
}
