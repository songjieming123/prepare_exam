package com.jieming;

import java.util.Scanner;

/**
 * 最长连续子串，求满足条件的最长子串的长度
 */
public class 华为机试第5题 {

    /*
    * 分析：根据题解来的---很重要：只包含一个字母，其余均为数字，所以截取出来的字符串比将字符替换为空字符后的字符串长度大1
    *
    * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String lowerStr = input.toLowerCase();
        if (lowerStr.matches("[a-z]") || lowerStr.matches("[0-9]")){
            System.out.println(-1);
            return;
        }
        int max = 0;
        String replaceStr = lowerStr.replaceAll("[a-z]", "a");
        for (int i = 0 ; i < lowerStr.length();i++){
            for (int j = i+1; j< lowerStr.length()+1; j++){
                String substring = replaceStr.substring(i, j);
                String newSub = substring.replace("a", "");
                // 所以截取出来的字符串比将字符替换为空字符后的字符串长度大1
                if (newSub.length() +1 == substring.length() && newSub.length() != 0)  {
                    max = Math.max(max,substring.length());
                }
            }
        }
        System.out.println(max);
    }
}
