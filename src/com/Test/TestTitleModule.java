package com.Test;

import com.title.Answer.CheckAnswers;
import com.title.TitleFactory;

import java.io.File;

public class TestTitleModule {
    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        TitleFactory titleFactory = new TitleFactory();
        titleFactory.generateAllTitle(10000,100);
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
    }
}
