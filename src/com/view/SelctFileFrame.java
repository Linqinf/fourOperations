package com.view;

import com.title.Answer.CheckAnswers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelctFileFrame extends JFrame {
    private String exercisesFile ="" ;//试题文件路径
    private String answersFile ="" ;//答案文件路径
    private String gradeFile = new File(System.getProperty("user.dir")).getAbsolutePath()+"\\src\\dbfile";
    private CheckAnswers checkAnswers = new CheckAnswers();

    public SelctFileFrame(){
        this.setFontAndSoOn();
        this.addElement();
        this.initListener();
        this.setFrameSelf();
    }
    //创建一个面板
    private JPanel mainPanel = new JPanel();

    //创建两个按钮
    private JButton exercisesButton = new JButton("点击选择试题文件路径");
    private JButton answersButton = new JButton("点击选择答案文件路径");
    private JButton confirmButton = new JButton("校对答案");


    //设置每一个组件的位置 大小 字体 布局等等
    protected void setFontAndSoOn(){
        mainPanel.setLayout(null);
        //设置标题组件的位置和字体大小

        exercisesButton.setBounds(70,20,400,50);
        exercisesButton.setFont(new Font("黑体",Font.BOLD,22));

        answersButton.setBounds(70,110,400,50);
        answersButton.setFont(new Font("黑体",Font.BOLD,22));

        confirmButton.setBounds(150,200,200,50);
        confirmButton.setFont(new Font("黑体",Font.BOLD,22));

    }

    //将所有的组件添加在窗体上
    protected void addElement(){
        //将所有的组件添加至窗体上
        mainPanel.add(exercisesButton);
        mainPanel.add(answersButton);
        mainPanel.add(confirmButton);
        this.add(mainPanel);
    }


    //绑定事件监听
    private void initListener(){

        //选择文件按钮
        exercisesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(new File(gradeFile));//设置默认打开路径
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int option = fileChooser.showOpenDialog(null);
                if(option == JFileChooser.APPROVE_OPTION){
                    exercisesFile = fileChooser.getSelectedFile().getAbsolutePath();
                    exercisesButton.setText(exercisesFile);
                    exercisesButton.setFont(new Font("黑体",Font.BOLD,16));
                }
            }
        });

        answersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(new File(gradeFile));
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int option = fileChooser.showOpenDialog(null);
                if(option == JFileChooser.APPROVE_OPTION){
                    answersFile =fileChooser.getSelectedFile().getAbsolutePath();
                    answersButton.setText(exercisesFile);
                    answersButton.setFont(new Font("黑体",Font.BOLD,16));
                }
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!answersFile.equals("") && !exercisesFile.equals("")
                        && checkAnswers.Correct(new File(exercisesFile),new File(answersFile)))
                {
                    JOptionPane.showMessageDialog(SelctFileFrame.this,"校对成功，结果的文件路径为:"+gradeFile);
                }
                else {
                    JOptionPane.showMessageDialog(SelctFileFrame.this,"打开失败，请重新选择文件路径");
                }
            }
        });


    }


    protected void setFrameSelf(){
        //设置窗体起始位置和大小
        this.setBounds(700,380,560,340);
        //设置点击关闭退出程序
        this.setDefaultCloseOperation(SelctFileFrame.DISPOSE_ON_CLOSE);
        //设置窗体大小不可拖拽
        this.setResizable(false);
        //设置窗体显示状态
        this.setVisible(true);
    }
}
