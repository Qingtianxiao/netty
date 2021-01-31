package leetcode.P6;

import scala.Int;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligc on 2021/1/31 13:54
 */
public class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 0){
            return null;
        }
        if(numRows == 1){
            return s;
        }

        boolean isDown = true;
        List<List<Character>> list = new ArrayList<>(numRows);
        //做一个list的标记为
        int flag = 0;
        //list的初始化
        for(int i = 0; i < numRows; i ++){
            list.add(new ArrayList<Character>());
        }

        for(int i = 0; i < s.length(); i ++){
            list.get(flag).add(s.charAt(i));
            if(isDown){
                if(flag == numRows - 1){
                    flag --;
                    isDown = false;
                }else{
                    flag ++;
                }
            }else{
                if(flag == 0){
                    flag ++;
                    isDown = true;
                }else{
                    flag --;
                }
            }
        }

        StringBuffer sb = new StringBuffer(s.length());
        for(int i = 0; i < numRows; i ++){
            for(int j = 0; j < list.get(i).size(); j ++){
                sb.append(list.get(i).get(j));
            }
        }
        return sb.toString();


    }
}
