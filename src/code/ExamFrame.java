package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ExamFrame extends JFrame  {

    public ExamFrame(){ //构造方法，顺便初始化
        this.setExamArea();
        this.setElement();
        this.addElement();
        this.setFrame();
        this.addListener();
    }

    private static ArrayList<Question> paper =new Generate().getpaper();//拿到试题
    private int nowNum = 0;//记录当前题目序号
    private int nowPage = 0;//记录当前页数
    private int totalCount = paper.size()/10;//记录试题页数
    private int answerCount = 0;//记录已经回答完毕的题目数量
    private int unanswerCount = paper.size();//记录还没有回答的题目数量
    private String[] answers = new String[paper.size()];//存储用户答案的数组
    private int[] answersState = new int[paper.size()];//存储用户答案正确与否的数组,-1为错误，1为正确


    private JPanel mainPanel = new JPanel();//负责答题主页面展示
    //将主画面分成两部分
    private JPanel leftPanel = new JPanel();//左侧信息展示
    private JPanel rightPanel = new JPanel();//右侧考试
    //左侧信息部分的组件
    private JLabel nowPageLabel = new JLabel("当前页数：");//提示当前的页数
    private JLabel totalCountLabel = new JLabel("题目页数：");//提示题目的总数
    private JLabel answerCountLabel = new JLabel("已答题数：");//提示提示当前的题号
    private JLabel unanswerCountLabel = new JLabel("未答题数：");//提示未答题数量
    private JTextField nowPageField = new JTextField();//展示当前的页数
    private JTextField totalCountField = new JTextField();//展示页面总数
    private JTextField answerCountField = new JTextField();//展示已答数
    private JTextField unanswerCountField = new JTextField();//展示未答数
    private JButton prevButton = new JButton("上一页");//previous题
    private JButton nextButton = new JButton("下一页");//next题
    private JButton submitButton = new JButton("提交");//提交
    private JButton againButton = new JButton("重考");//重考
    private JButton selectFileButton = new JButton("选择文件");//手动选择文件比较答案
    //右侧考试部分的组件
    private JTextArea examText = new JTextArea();
    private JLabel[] ExamQuestion = new JLabel[10];
    private JTextField[] answerField = new JTextField[10];


    //设置组件的样式
    private  void setElement(){
        //整个页面
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        //设置左侧展示区域
        leftPanel.setLayout(null);
        leftPanel.setBounds(10,10,200,550);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        nowPageLabel.setBounds(20,30,100,30);
        nowPageLabel.setFont(new Font("黑体",Font.PLAIN,20));
        nowPageField.setBounds(120,30,50,30);
        nowPageField.setFont(new Font("黑体",Font.BOLD,20));
        nowPageField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        nowPageField.setEnabled(false);
        nowPageField.setHorizontalAlignment(JTextField.CENTER);

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

        prevButton.setBounds(50,200,90,40);
        prevButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.setBounds(50,270,90,40);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setBounds(50,340,90,40);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        againButton.setBounds(50,410,90,40);
        againButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        selectFileButton.setBounds(50,480,90,40);
        selectFileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //设置右侧答题区域
        rightPanel.setLayout(null);
        rightPanel.setBounds(230,10,870,550);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //重新设置右侧message中的组件值--用变量控制
        if(paper.size()%10!=0) totalCount++;
        nowPageField.setText(nowPage+1+"");
        totalCountField.setText(totalCount+"");
        answerCountField.setText(answerCount+"");
        unanswerCountField.setText(unanswerCount+"");


    }


    //添加组件
    private void addElement(){
        leftPanel.add(nowPageField);
        leftPanel.add(totalCountField);
        leftPanel.add(answerCountField);
        leftPanel.add(unanswerCountField);
        leftPanel.add(nowPageLabel);
        leftPanel.add(totalCountLabel);
        leftPanel.add(answerCountLabel);
        leftPanel.add(unanswerCountLabel);
        leftPanel.add(prevButton);
        leftPanel.add(nextButton);
        leftPanel.add(submitButton);
        leftPanel.add(againButton);
        leftPanel.add(selectFileButton);

        rightPanel.add(examText);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        this.add(mainPanel);
    }

    //设置窗体本身
    private void setFrame(){
        this.setBounds(260,130,1120,608);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);//不想让窗体拖拽大小
        this.setVisible(true);//最终展示整个窗体
    }

    //设置考试区域(题目框和答案框)
    private  void setExamArea(){
        for (int i = 0;i < 10;i++){
            if(i>= paper.size()){
                ExamQuestion[i] = new JLabel();
                answerField[i] = new JTextField();
            }else{
                ExamQuestion[i] = new JLabel(paper.get(i).getTitle()+" =");
                answerField[i] = new JTextField();
            }
            ExamQuestion[i].setHorizontalAlignment(JLabel.CENTER);
            answerField[i].setHorizontalAlignment(JTextField.CENTER);
            if(i<5) {
                ExamQuestion[i].setBounds(20, 60 + 95 * i, 305, 60);
                answerField[i].setBounds(330, 60 + 95 * i, 80, 60);
            }
            else{
                ExamQuestion[i].setBounds(430,60 + 95*(i-5),305,60);
                answerField[i].setBounds(740, 60 + 95*(i-5), 80, 60);

            }
            ExamQuestion[i].setFont(new Font("黑体",Font.BOLD,20));
            ExamQuestion[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));

            answerField[i].setFont(new Font("黑体",Font.BOLD,20));
            answerField[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            rightPanel.add(ExamQuestion[i]);
            rightPanel.add(answerField[i]);

            answerField[i].getText();

        }
    }

    //给按钮绑定事件
    private void addListener(){

        //下一题按钮
        nextButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            if(nowNum+10 >= paper.size()){  //检测下一页还有没有题目
                  nextButton.setEnabled(false);//调按钮状态
            }
            else {
                    prevButton.setEnabled(true);//调按钮状态
                    updateAnswersArray();//更新存储答案的数组和已答题目
                    nowPage++;
                    nowNum += 10; //改变已答题目数
                    for (int i = 0; i < 10; i++) {
                        if(i + nowNum  >= paper.size()) {  //题目数量不能填满整页
                            answerField[i].setText("");
                            ExamQuestion[i].setText("");
                        }
                        else {
                            answerField[i].setText(answers[i + nowNum]);
                            ExamQuestion[i].setText(paper.get(i + nowNum).getTitle() + " =");
                        }
                    }
                changeBackground();
                nowPageField.setText(nowPage+1+"");

            } }});

        //上一题按钮
        prevButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(nowNum<=0) prevButton.setEnabled(false);//检测上一题有没有答案
                else {
                    nextButton.setEnabled(true);
                    updateAnswersArray();//更新存储答案的数组和已答题目
                    nowPage--;
                    nowNum -= 10;
                    for (int i = 0; i < 10; i++) {
                        answerField[i].setText(answers[i + nowNum]);
                        ExamQuestion[i].setText(paper.get(i + nowNum).getTitle() + " =");
                    }
                    changeBackground();
                    nowPageField.setText(nowPage+1+"");
                } }});

        //提交按钮
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAnswersArray();//更新存储答案的数组和已答题目
                String messages = "";
                if(answerCount < paper.size()) messages = "您还有"+ unanswerCount +"道题目没做,";
                else messages ="您已做完全部题目,";
                int value = JOptionPane.showConfirmDialog(ExamFrame.this,messages+"是否确认提交试卷?");
                if(value == 0){
                    submitButton.setEnabled(false);
                    calculateResults();//计算分数并弹窗显示
                }
            }});

        againButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = JOptionPane.showConfirmDialog(ExamFrame.this,"请问是否确认重考?");
                if(value == 0){
                    ExamFrame.this.setVisible(false);//隐藏自己
                    new SelctBoundFrame();
                }
            }});
    }


    //更新存储答案的数组和已答题目(answerCount)的方法
    private void updateAnswersArray(){
        for (int i = 0; i < 10; i++) {
            if(i + nowNum  >= paper.size()) break;;

            if( answers[i + nowNum] == null) //最开始数组为空的话，先给个默认值，方便后面使用equals方法
                answers[i + nowNum] = "";

            //以下是统计已做题目数量answerCount
            if(!answerField[i].getText().equals("")) {//画面里的答案不为空
                if(answers[i + nowNum].equals("")) {
                    answerCount++;   //新增了答案（从无到有）
                }
            }else{ //为空
                if(!answers[i + nowNum].equals(""))
                    answerCount--;    //本来做了，但后来删除答案的情况
            }

            answers[i + nowNum] = answerField[i].getText(); //刷新答案数组
        }
        unanswerCount = paper.size()-answerCount;
        answerCountField.setText(answerCount+"");
        unanswerCountField.setText(unanswerCount+"");
    }

    //提交答案后，更新题目的背景颜色
    private void changeBackground(){
        int j=0;
        for(int k=0;k < 10;k++){
            if(k + nowNum  >= paper.size()) {
                ExamQuestion[k].setOpaque(false);
                answerField[k].setBackground(null);
            }else {
                    if(answersState[k + nowNum] == 1) {
                        ExamQuestion[k].setOpaque(true);
                        ExamQuestion[k].setBackground(Color.green);
                        answerField[k].setBackground(Color.green);
                        answerField[k].setEditable(false);
                    }
                    if(answersState[k + nowNum] == -1) {
                        ExamQuestion[k].setOpaque(true);
                        ExamQuestion[k].setBackground(Color.red);
                        answerField[k].setBackground(Color.red);
                        answerField[k].setEditable(false);
                    }
            }
        }
    }

    //计算最终成绩的方法
    private void calculateResults(){
        final float oneTitleScore = 100f/paper.size();//一道题目的分数
        float finalScore = 0; //最终分数
        int rightNum=0;
        for(int i=0;i < paper.size();i++){
            if(paper.get(i).getAnswer().equals(answers[i])){ //比较用户的答案和正确的答案
                rightNum++;
                answersState[i] = 1;
                finalScore += oneTitleScore; //统计分数
            }else{//答案不对
                answersState[i] = -1;
            }
        }
        changeBackground();
        JOptionPane.showMessageDialog(ExamFrame.this,"您答对了"+rightNum+"道题，最终成绩为" + (int)Math.floor(finalScore));

    }



}
