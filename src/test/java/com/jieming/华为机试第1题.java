package com.jieming;

import java.util.Scanner;


/**
 * 字符串序列判定
 */
public class 华为机试第1题 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String longStr = scanner.nextLine();
        String smallStr = scanner.nextLine();
        int flag = 0;
        int index = 0;
        for (int i = 0; i < smallStr.length(); i++) {
            char ch = smallStr.charAt(i);
            int theChar = findTheChar(index, longStr, ch);
            if (theChar == -1){
                flag =-1;
                break;
            }
            index = theChar;

        }
        if (flag == -1){
            System.out.println(flag);
            return;
        }
        System.out.println(index);


    }

    public static int findTheChar(int index, String str,char ch){
        int num = str.indexOf(ch, index);
        return num;
    }
}
