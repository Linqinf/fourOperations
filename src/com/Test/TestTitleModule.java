package com.Test;

import com.title.Answer.CheckAnswers;
import com.title.TitleFactory;

import java.io.File;

public class TestTitleModule {
    public static void main(String[] args) {
        TitleFactory titleFactory = new TitleFactory(
                new File("src/dbfile/Exercises.txt"),
                new File("src/dbfile/Answers.txt"),
                new File("src/dbfile/title+answer.txt")
        );
        //CheckAnswers checkAnswers = new CheckAnswers();
        titleFactory.generateAllTitle(10000,100);
        //checkAnswers.Correct(new File("src/dbfile/Exercises.txt"),new File("src/dbfile/Answers.txt"));
//        File file = new File("E:\\Java-workspace\\fourOperations-master\\src\\dbfile\\Grade.txt");
//        System.out.println(file.exists());
    }
}
