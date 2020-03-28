package com.view;

import com.title.Title;
import com.title.TitleFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class loginFrame extends JFrame {
    private int titleNumber = 0;//获取试题数量
    private int numberBound = 0;//获取数字范围
    //需要导入路径模块
    private TitleFactory titleFactory = new TitleFactory();
    private static ArrayList<Title> paper = null;

    public loginFrame(){
            this.setFontAndSoOn();
            this.addElement();
            this.addListener();
            this.setFrameSelf();
    }
    //创建一个面板
    private JPanel mainPanel = new JPanel();
    //创建title显示标题
    private JLabel titleLabel = new JLabel("四则运算考试系统");
    //创建输入题目数量和数字范围的标题
    private JLabel titleNumberLabel = new JLabel("题目数量：");
    private JLabel numberBoundLabel = new JLabel("数字范围：");
    //创建输入题目数量和数字范围的文本框
    private JTextField titleNumberField = new JTextField();
    private JTextField numberBoundField = new JTextField();
    //创建两个按钮
    private JButton loginButton = new JButton("自动生成");
    private JButton selectButton = new JButton("选择文件");
    private JButton exitButton = new JButton("退 出");

    //设置每一个组件的位置 大小 字体 布局等等
    protected void setFontAndSoOn(){
        mainPanel.setLayout(null);
        //设置标题组件的位置和字体大小
        titleLabel.setBounds(120,40,340,35);
        titleLabel.setFont(new Font("黑体",Font.BOLD,34));

        titleNumberLabel.setBounds(120,124,200,30);
        titleNumberLabel.setFont(new Font("黑体",Font.BOLD,24));

        titleNumberField.setBounds(280,124,100,30);
        titleNumberField.setFont(new Font("黑体",Font.BOLD,24));

        numberBoundLabel.setBounds(120,174,200,30);
        numberBoundLabel.setFont(new Font("黑体",Font.BOLD,24));

        numberBoundField.setBounds(280,174,100,30);
        numberBoundField.setFont(new Font("黑体",Font.BOLD,24));

        loginButton.setBounds(50,232,150,30);
        loginButton.setFont(new Font("黑体",Font.BOLD,22));

        selectButton.setBounds(250,232,150,30);
        selectButton.setFont(new Font("黑体",Font.BOLD,22));

        exitButton.setBounds(440,232,100,30);
        exitButton.setFont(new Font("黑体",Font.BOLD,22));



    }

    //将所有的组件添加在窗体上
    protected void addElement(){
        //将所有的组件添加至窗体上
        mainPanel.add(titleLabel);
        mainPanel.add(titleNumberLabel);
        mainPanel.add(titleNumberField);
        mainPanel.add(numberBoundLabel);
        mainPanel.add(numberBoundField);
        mainPanel.add(loginButton);
        mainPanel.add(exitButton);
        mainPanel.add(selectButton);
        this.add(mainPanel);
    }


    //绑定事件监听
    protected void addListener(){

        //绑定事件监听器
            ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              if(!titleNumberField.getText().equals("") && !numberBoundField.getText().equals("")){
                    titleNumber =  Integer.parseInt(titleNumberField.getText());
                    numberBound = Integer.parseInt(numberBoundField.getText());
                    if(titleNumber>0 && numberBound>0) {
                        titleFactory.generateAllTitle(titleNumber, numberBound);
                        paper = titleFactory.getpaper();
                        loginFrame.this.setVisible(false);//将登录窗口隐藏
                        new ExamFrame(paper);//将试卷传进去
                    }else {
                        titleNumberField.setText("");
                        numberBoundField.setText("");
                        JOptionPane.showMessageDialog(loginFrame.this,"输入格式有误，请重新输入");
                    }
            }else{
                  JOptionPane.showMessageDialog(loginFrame.this,"请输入题目数量和数字范围");
              }

            }
        };

        loginButton.addActionListener(listener);//观察者模式

        //给退出按钮绑定事件
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //选择文件按钮
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new SelctFileFrame();

            }
        });
    }

    protected void setFrameSelf(){
        //设置窗体起始位置和大小
        this.setBounds(600,280,560,340);
        //设置点击关闭退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体大小不可拖拽
        this.setResizable(false);
        //设置窗体显示状态
        this.setVisible(true);
    }
}
