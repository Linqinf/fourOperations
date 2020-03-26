package code;

import javax.swing.*;
import java.awt.*;

public class ExamFrame extends JFrame{

    public ExamFrame(){ //构造方法，顺便初始化
          this.setElement();
          this.addElement();
          this.setFrame();
          this.setExamArea();
    }

    private int nowNum = 0;//记录当前题目序号
    private int totalCount = 10;//记录试题总数
    private int answerCount = 0;//记录已经回答完毕的题目数量
    private int unanswerCount = totalCount;//记录还没有回答的题目数量



    private JPanel mainPanel = new JPanel();//负责答题主页面展示
    //将主画面分成两部分
    private JPanel leftPanel = new JPanel();//左侧信息展示
    private JPanel rightPanel = new JPanel();//右侧考试
    //左侧信息部分的组件
    private JLabel nowNumLabel = new JLabel("当前题号：");//提示当前的题号
    private JLabel totalCountLabel = new JLabel("题目总数：");//提示题目的总数
    private JLabel answerCountLabel = new JLabel("已答题数：");//提示已经答过的题目数量
    private JLabel unanswerCountLabel = new JLabel("未答题数：");//提示未答题数量
    private JTextField nowNumField = new JTextField();//展示题号
    private JTextField totalCountField = new JTextField();//展示总数
    private JTextField answerCountField = new JTextField();//展示已答数
    private JTextField unanswerCountField = new JTextField();//展示未答数
    private JButton prevButton = new JButton("上一页");//previous题
    private JButton nextButton = new JButton("下一页");//next题
    private JButton submitButton = new JButton("提交");//提交
    //右侧考试部分的组件
    private JTextArea examText = new JTextArea();
    private JLabel[] getExamQuestion = new JLabel[10];


    //设置组件的样式
    private  void setElement(){
        //整个页面
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        //设置左侧展示区域
        leftPanel.setLayout(null);
        leftPanel.setBounds(10,10,200,550);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        nowNumLabel.setBounds(20,30,100,30);
        nowNumLabel.setFont(new Font("黑体",Font.PLAIN,20));
        nowNumField.setBounds(120,30,50,30);
        nowNumField.setFont(new Font("黑体",Font.BOLD,20));
        nowNumField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        nowNumField.setEnabled(false);
        nowNumField.setHorizontalAlignment(JTextField.CENTER);

        totalCountLabel.setBounds(20,70,100,30);
        totalCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        totalCountField.setBounds(120,70,50,30);
        totalCountField.setFont(new Font("黑体",Font.BOLD,20));
        totalCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        totalCountField.setEnabled(false);
        totalCountField.setHorizontalAlignment(JTextField.CENTER);

        answerCountLabel.setBounds(20,110,100,30);
        answerCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        answerCountField.setBounds(120,110,50,30);
        answerCountField.setFont(new Font("黑体",Font.BOLD,20));
        answerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        answerCountField.setEnabled(false);
        answerCountField.setHorizontalAlignment(JTextField.CENTER);

        unanswerCountLabel.setBounds(20,150,100,30);
        unanswerCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        unanswerCountField.setBounds(120,150,50,30);
        unanswerCountField.setFont(new Font("黑体",Font.BOLD,20));
        unanswerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        unanswerCountField.setEnabled(false);
        unanswerCountField.setHorizontalAlignment(JTextField.CENTER);

        prevButton.setBounds(20,270,150,60);
        prevButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.setBounds(20,370,150,60);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setBounds(20,470,150,60);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //设置右侧答题区域
        rightPanel.setLayout(null);
        rightPanel.setBounds(230,10,750,550);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //重新设置右侧message中的组件值--用变量控制
        nowNumField.setText(nowNum+1+"");
        totalCountField.setText(totalCount+"");
        answerCountField.setText(answerCount+"");
        unanswerCountField.setText(unanswerCount+"");


    }


    //添加组件
    private void addElement(){
        leftPanel.add(nowNumField);
        leftPanel.add(totalCountField);
        leftPanel.add(answerCountField);
        leftPanel.add(unanswerCountField);
        leftPanel.add(nowNumLabel);
        leftPanel.add(totalCountLabel);
        leftPanel.add(answerCountLabel);
        leftPanel.add(unanswerCountLabel);
        leftPanel.add(prevButton);
        leftPanel.add(nextButton);
        leftPanel.add(submitButton);

        rightPanel.add(examText);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        this.add(mainPanel);
    }

    //设置窗体本身
    private void setFrame(){
        this.setBounds(260,130,1000,608);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);//不想让窗体拖拽大小
        this.setVisible(true);//最终展示整个窗体
    }

    private  void setExamArea(){
        for (int i = 0;i < 10;i++){
          getExamQuestion[i] = new JLabel("我是题目"+i);
          //getExamQuestion[i];
          rightPanel.add(getExamQuestion[i]);
        }
    }
}
