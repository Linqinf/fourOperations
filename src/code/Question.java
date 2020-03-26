package code;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Question {
    public static void main(String[] args) {
//        Question q = new Question(100);
//        System.out.println(q);
//        System.out.println(q.isLawful());
        //System.out.println(Question.Test().calculate("97 * 1'14/69 ÷ 0"));
//        HashSet<Question> hashSet = new HashSet<>();
//        Question q1 = new Question();
//        q1.title = "( 3 + 1 - 2 ) * 2";
//        q1.answer = "4";
//        q1.value.add("3");q1.value.add("1");q1.value.add("2");q1.value.add("2");
//        Question q2 = new Question();
//        q2.title = "2 * ( 3 -2 + 1 )";
//        q2.answer = "4";
//        q2.value.add("2");q2.value.add("3");q2.value.add("2");q2.value.add("1");
//        hashSet.add(q1);
//        System.out.println(hashSet.add(q2));
        System.out.println(Question.Calculator("4 ÷ 2"));
    }
    private boolean Lawful = true;//是否合法
    private int bound;//数值最大值
    private ArrayList<String> value = new ArrayList<>();
    private String title;//存储题干(题目+选项)
    private String answer;//存储答案
    private Random r = new Random();
    private Question(){

    }
    public static String Calculator(String title){//外部调用计算器
        Question q = new Question();
        return q.SimplifyFraction(q.calculate(title));
    }
    public Question(int bound){
        char []operator = new char[3];
        String []Number = new String[4];
        int operatorNum = r.nextInt(3)+1;//运算符数量， 不超过3个
        int divideNum = 0;
        this.bound = bound;
        for(int i = 0;i < operatorNum;i++){ //根据运算符数量来循环拼接
            int temp = r.nextInt(4);
            if(temp == 0)
                operator[i] = '+';
            else if (temp == 1)
                operator[i] = '-';
            else if (temp == 2){
                divideNum++;
                operator[i] = '÷';
            }
            else if (temp == 3)
                operator[i] = '*';
        }
        for(int i = 0;i < operatorNum+1 ; i++){

            Number[i] = " " + r.nextInt(bound) + " ";
        }
        if(divideNum >= 1 && operatorNum > 1) { //出现除号，要检查
            int flag = 0;
            for (int i = 0; i < operatorNum; i++) { //遍历所有的运算符，找到第一个除号
                if ((operator[i] == '÷' && flag == 0) || (i == 2 && operator[2] == '÷' && flag == 1)) {
                    int Num1 = Integer.parseInt(Number[i].trim());
                    int Num2 = Integer.parseInt(Number[i + 1].trim());
                    if (Num2 != 0) { //除数不能为0
                        if (Num1 > Num2) { //出现了假分数，要转化
                            if (Num1 % Num2 == 0)
                                Number[i] = " " + Num1 / Num2;
                            else
                                Number[i] = " " + Num1 / Num2 + "'" + Num1 % Num2 + "/" + Num2;
                        } else { //真分数
                            Number[i] = " " + Num1 + "/" + Num2;
                        }
                        operator[i] = '\0';
                        Number[i + 1] = "";
                    }

                    if (i == 1) //如果是第二个运算符为除号，后边不检查第三个运算符的情况了。
                        flag = 2;
                    else
                        flag++; //若第一次变为1，还要检查第三个运算符除号的情况
                }
            }
        }
        //构建运算数组
        for(int i=0;i<Number.length;i++){
            if(Number[i]!=""&&Number[i]!=null){
                value.add(Number[i].trim());
            }
        }
        //拼接运算式
        String result = null;
        String leftBrcket = " (";
        String rightBrcket = ") ";
        if((operator[1]=='+' || operator[1]=='-') && operator[0] != '\0' && r.nextBoolean()== true) {
            if(Number[3] == "" )  rightBrcket =" )";
            if(Number[3] == null) Number[3] = "";
            result = Number[0] + operator[0] + leftBrcket + Number[1] +  //在式子中间（第二个运算符）加括号
                    operator[1] + Number[2] + rightBrcket + operator[2] + Number[3];
        }
        else {//无括号
            if ((operator[0] == '+' || operator[0] == '-') && operator[1] != '\0' && r.nextBoolean() == true)
                result = leftBrcket + Number[0] + operator[0] + Number[1] + rightBrcket; //在最开始加括号
            else result = Number[0] + operator[0] + Number[1];  //无括号

            if ((operator[2] == '+' || operator[2] == '-') && operator[1] != '\0' && r.nextBoolean() == true) {
                result += operator[1] + leftBrcket + Number[2] + operator[2] + Number[3] + rightBrcket; //在最后加括号
            } else {
                if (operatorNum > 1) result += operator[1] + Number[2]; //无括号
                if (operatorNum == 3) result += operator[2] + Number[3]; //无括号
            }
        }
        //重构字符串
//        title = "";
//        String[] Message = result.split("\0| ");
//        for(int i=0;i<Message.length;i++){
//            if (Message[i].length() != 0) {
//                title += Message[i];
//                if (i != Message.length - 1) {
//                    title += " ";
//                }
//            }
//        }
        //替换空格处理
        title = result.replaceAll("\0"," ").trim();
        //System.out.println(result);
        //System.out.println(title);
        //生成答案
        answer = SimplifyFraction(calculate(title));
        judgeLawful();
    }
    private int Search(String[] strs,String s){//查询与s字符串相同的元素在字符串数组的位置，存在则返回所在下标，不存在则返回-1
        int index = -1;//记录下标
        for(int i=0;i<strs.length;i++){
            if(strs[i].equals(s))
                index = i;
        }
        return index;
    }
    private String transformFraction(String Fraction){//将真分数转化成假分数
        Fraction = Fraction.trim();
        if(!Fraction.contains("'"))//不是真分数
            return Fraction;
        String[] split = Fraction.split("'|/");
        //分子，分母操作
        int Denominator  = Integer.parseInt(split[2]);
        int numerator = Denominator*Integer.parseInt(split[0])+Integer.parseInt(split[1]);
        return numerator+"/"+Denominator;
    }
    private String SimplifyFraction(String Fraction) {//将分数化简：假分数转化成真分数，分数的约分
        Fraction = Fraction.trim();
        int count = 0;//记录进位
        String[] digit = null;//储存分数中每一个数字
        int Denominator = 0 ;//分母
        int numerator = 0 ;//分子
        if(!Fraction.contains("/"))
            return Fraction;
        if(Fraction.contains("'")){
            count = Integer.parseInt(Fraction.split("'")[0]);
            Fraction = Fraction.split("'")[1];
        }
        digit = Fraction.trim().split("/");
        //生成分子，分母
        numerator = Integer.parseInt(digit[0]);
        Denominator = Integer.parseInt(digit[1]);
        //分数进位操作
        if(numerator>=Denominator){//分子大于或等于分母
            if (Denominator == 0) {
                Lawful = false;
                return null;
            }
            count += numerator/Denominator;
            numerator = numerator%Denominator;
        }
        //分数约简操作，将分子分母的因子加入hashset中，若插入不成功即该数为公约数
        while (true) {
            boolean exitCommon = false;//记录是否存在公约是
            HashSet<Integer> set = new HashSet<>();//记录所有公约数
            //加入其本身
            for(int i=2;i<=Math.sqrt(numerator);i++){//构造分子所有公约数
                if(numerator%i==0){
                    set.add(i);
                }
            }
            set.add(numerator);
            for(int i=2;i<=Math.sqrt(Denominator);i++){
                if(Denominator%i==0){
                    if(set.add(i)==false){//出现相同公约数
                        numerator /= i;
                        Denominator /= i;
                        exitCommon = true;
                        break;
                    }
                }
            }
            if (!exitCommon) {//若不出现公约数，即判定为最简分数
                break;
            }
        }
        //构造分数字符串
        Fraction = "";
        if(count!=0){//进位不为0
            Fraction += count+"'";
        }
        if(numerator!=0)//分子不为0
            Fraction += +numerator+"/"+Denominator;
        else {//分子为0，去除符号‘'’
            Fraction = Fraction.replaceAll("'","");
        }
        return Fraction;
    }
    private String calculate(String title) {//计算式子

        while ((title.contains("*")||title.contains("÷")||
                title.contains("+")||title.contains("-"))){//存在运算符
            int index = -1,start = 0,end = 0;//记录运算符位标，括号的开始与结束位标
            String[] Message = title.split(" ");//分割字符串
//            for(int i=0;i<Message.length;i++){
//                Message[i] = Message[i].replaceAll("\0| ","");
//            }
            String temp = null;//将结果替换子表达式
            //记录括号的开始与结束位标
            start = Search(Message,"(");
            end = Search(Message,")");
            if(start!=-1&&end!=-1){//存在括号
                String Subexpression = "";//括号内表达式
                for(int i=start+1;i<end;i++) {//构造子表达式
                    Subexpression += Message[i] + " ";
                }
                //重新构造题干信息
                title = "";
                for(int i=0;i<start;i++) {
                    title += Message[i] + " ";
                }
                title += calculate(Subexpression);
                for(int i=end+1;i<Message.length;i++) {
                    title += Message[i] + " ";
                }
                continue;
            }
            //定位运算符，先*、÷后+、-
            index = Search(Message,"*")>=Search(Message,"÷")?Search(Message,"*"):Search(Message,"÷");
            if(index == -1){//式子内没有乘除时
                index = Search(Message,"+")>=Search(Message,"-")?Search(Message,"+"):Search(Message,"-");
            }

            if(index==-1){//不存在运算符时，运算结束，跳出循环
                break;
            }
            String[] firstNum = transformFraction(Message[index-1]).split("/");
            String[] secondNum = transformFraction(Message[index+1]).split("/");

            if(Message[index].equals("+")){//加法操作
                if(!Message[index-1].contains("/")&&!Message[index+1].contains("/"))//不存在分数,直接相加
                    temp = Integer.parseInt(Message[index-1]) + Integer.parseInt(Message[index+1])+"";
                else if(Message[index-1].contains("/")&&Message[index+1].contains("/")){//两个都为分数，化同分母，再相加

                    int Denominator  = Integer.parseInt(firstNum[1])*Integer.parseInt(secondNum[1]);
                    int numerator = Integer.parseInt(firstNum[1])*Integer.parseInt(secondNum[0])
                            +Integer.parseInt(secondNum[1])*Integer.parseInt(firstNum[0]);
                    temp = numerator + "/" +Denominator ;
                }
                else {//只有一个分数，整数化为分数相加
                    int Denominator,numerator;
                    if(Message[index-1].contains("/")){//第一个操作数为分数
                        Denominator  = Integer.parseInt(firstNum[1]);
                        numerator = Integer.parseInt(firstNum[1])*Integer.parseInt(secondNum[0])+Integer.parseInt(firstNum[0]);
                    }
                    else {//第二个操作数为分数
                        Denominator  = Integer.parseInt(secondNum[1]);
                        numerator = Integer.parseInt(secondNum[1])*Integer.parseInt(firstNum[0])+Integer.parseInt(secondNum[0]);
                    }
                    temp = numerator + "/" +Denominator ;
                }
            }
            else if(Message[index].equals("-")){//减法操作

                if(!Message[index-1].contains("/")&&!Message[index+1].contains("/"))//不存在分数
                    temp = Integer.parseInt(Message[index-1]) - Integer.parseInt(Message[index+1])+"";
                else if(Message[index-1].contains("/")&&Message[index+1].contains("/")){//两个都为分数，化同分母，再相减

                    int Denominator  = Integer.parseInt(firstNum[1])*Integer.parseInt(secondNum[1]);
                    int numerator = Integer.parseInt(firstNum[0])*Integer.parseInt(secondNum[1])
                            -Integer.parseInt(secondNum[0])*Integer.parseInt(firstNum[1]);
                    temp = numerator + "/" +Denominator ;
                }
                else {//只有一个分数，整数化为分数相减
                    int Denominator,numerator;
                    if(Message[index-1].contains("/")){//第一个操作数是分数
                         Denominator  = Integer.parseInt(firstNum[1]);
                         numerator = Integer.parseInt(firstNum[0])-Integer.parseInt(secondNum[0])*Denominator;

                    }
                    else {//第二个操作数是分数
                         Denominator  = Integer.parseInt(secondNum[1]);
                         numerator = Integer.parseInt(firstNum[0])*Denominator - Integer.parseInt(secondNum[0]);
                    }
                    temp = numerator + "/" +Denominator ;
                }
            }
            else if(Message[index].equals("*")){//乘法操作

                if(!Message[index-1].contains("/")&&!Message[index+1].contains("/")){//不存在分数，直接相乘
                    temp = Integer.parseInt(Message[index-1]) * Integer.parseInt(Message[index+1])+"";

                }
                else if(Message[index-1].contains("/")&&Message[index+1].contains("/")){//两个都为分数，分子分母相乘

                    int Denominator  = Integer.parseInt(firstNum[1])*Integer.parseInt(secondNum[1]);
                    int numerator = Integer.parseInt(firstNum[0])*Integer.parseInt(secondNum[0]);
                    temp = numerator + "/" +Denominator ;
                }
                else {//只有一个分数，整数乘以分子作为新分子，分母不变
                    int Denominator,numerator;
                    if(Message[index-1].contains("/")){//第一个操作数是分数
                         Denominator  = Integer.parseInt(firstNum[1]);
                         numerator = Integer.parseInt(firstNum[0])*Integer.parseInt(secondNum[0]);
                    }
                    else {//第二个操作数是分数
                        Denominator  = Integer.parseInt(secondNum[1]);
                        numerator = Integer.parseInt(firstNum[0])*Integer.parseInt(secondNum[0]);
                    }

                    temp = numerator + "/" +Denominator ;
                }
            }
            else if(Message[index].equals("÷")){//除法操作
                if(!Message[index-1].contains("/")&&!Message[index+1].contains("/"))//不存在分数，直接构造分数
                    if(Message[index-1].equals("0"))
                        temp = "0";
                    else
                        temp = Message[index-1]+"/"+Message[index+1];
                else if(Message[index-1].contains("/")&&Message[index+1].contains("/")){//两个都为分数，分子，分母交叉相乘

                    int Denominator  = Integer.parseInt(firstNum[1])*Integer.parseInt(secondNum[0]);
                    int numerator = Integer.parseInt(firstNum[0])*Integer.parseInt(secondNum[1]);
                    temp = numerator + "/" +Denominator ;
                }
                else {//只有一个分数
                    int Denominator,numerator;
                    if(Message[index-1].contains("/")){//第一个操作数是分数
                        Denominator  = Integer.parseInt(firstNum[1])*Integer.parseInt(secondNum[0]);
                        numerator = Integer.parseInt(firstNum[0]);
                    }
                    else {//第二个操作数是分数
                        Denominator  = Integer.parseInt(secondNum[0]);
                        numerator = Integer.parseInt(firstNum[0])*Integer.parseInt(secondNum[1]);
                    }
                    temp = numerator + "/" +Denominator ;
                }
            }
            //构造新题干
            title = "";
            for(int i=0;i<index-1;i++) {
                title += Message[i] + " ";
            }
            title += temp+" ";
            for(int i=index+2;i<Message.length;i++) {
                title += Message[i] + " ";
            }
        }

        return title;
    }

    private void judgeLawful(){//判断表达式是否合法

        if(answer==null||title==null||answer.contains("-")){//计算结果存在负号
            Lawful = false;
            return;
        }
        if(answer.contains("/")){//结果存在分数
            String[] split = answer.split("/");//分母
            String Denominator = split[split.length-1];
            if(Integer.parseInt(Denominator)>=bound||Integer.parseInt(Denominator)==0){
                Lawful = false;
            }
        }

    }
    @Override
    public String toString() {
        return title+ " = "+answer;
    }

    //重写Question类中的两个方法  equals  hashCode
    //想要将Question对象存入HashSet集合内 让set集合帮我们去掉重复元素
    @Override
    public int hashCode(){ //默认hashcode一样
//        String thisTitle = this.title;
//        return thisTitle.hashCode();//31*h
          return 1;
    }
    @Override
    public boolean equals(Object obj){//比较两个对象是否一致
        if(answer==null||obj==null){
            return false;
        }
        if(this==obj){ //地址一样
            return true;
        }
        if(obj instanceof Question){ //类型一样,都是question
            Question other = (Question) obj;
            if(!answer.equals(other.answer))//先检查答案是否一致
                return false;
            if(value.size()!=other.value.size())
                return false;
            for(int i=0;i<other.value.size();i++){
                if(!value.contains(other.value.get(i))){
                    return  false;
                }
            }
            return true;
        }
        return false;
    }
    public boolean isLawful(){
        return Lawful;
    }
    public String getTitle() {
        return title;
    }
    private void setTitle(String title) {
        this.title = title;
        answer = SimplifyFraction(calculate(title));
    }
    public String getAnswer() {
        return answer;
    }
    
}
