package com.Test;

import com.title.Answer.CheckAnswers;
import com.title.TitleFactory;

import java.io.File;

public class TestTitleModule {
    public static void main(String[] args) {
        TitleFactory titleFactory = new TitleFactory();

        titleFactory.generateAllTitle(10000,100);

    }
}
