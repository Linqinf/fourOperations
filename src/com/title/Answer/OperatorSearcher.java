package com.title.Answer;

//查询与s字符串相同的元素在字符串数组的位置，存在则返回所在下标，不存在则返回-1

public class OperatorSearcher {
    public int searchOperator(String[] strs, String s){
        String[] tags = s.split("\\|");
        int index = -1;//记录下标
        for(int i=0;i<strs.length&&index==-1;i++){
            for(String tag : tags){
                if(strs[i].equals(tag)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
