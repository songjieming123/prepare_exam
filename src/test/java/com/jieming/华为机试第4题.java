package com.jieming;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 密码输入检测
 */
public class 华为机试第4题 {
    /*
    *
    * 注意ArrayQueue 这个数据结构的特点，相当于一个堆
    * */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[] charArray = input.toCharArray();
//        int i = 0;
//        int index = 0;
//        while (i < input.length()) {
//            int num = input.indexOf(0, '<');
//            if (num == -1) {
//                break;
//            }
//            index = num + 1;
//            for (int j = i - 1; j >= 0; j--) {
//                if (charArray[j] != ' ') {
//                    charArray[j] = ' ';
//                    break;
//                }
//            }
//            i++;
//
//        }
//        String password = "";
//        for (int k = 0; k < charArray.length;k++) {
//            if (charArray[i] == ' ') {
//                continue;
//            }
//            password = password+ charArray[i];
//        }
//        if (password.length() < 8 ){
//            System.out.println(false);
//        }

        ArrayDeque<Character> stack = new ArrayDeque<>();
        boolean lower = false;
        boolean upper = false;
        boolean digit = false;
        boolean special = false;
        for (int i = 0 ;i< input.length();i++){
            char ch = input.charAt(i);
            if (ch != '<'){
                stack.push(ch);
            }else {
                stack.pop();
            }
        }
        String password = "";
        while (!stack.isEmpty()){
            Character ch = stack.removeLast();
            if (Character.isLowerCase(ch)){
                lower = true;
            }else if (Character.isUpperCase(ch)){
                upper = true;
            }else if(Character.isDigit(ch)){
                digit = true;
            }else if(!Character.isSpaceChar(ch)){
                special = true;
            }
            password = password + String.valueOf(ch);
        }
        if (password.length() >= 8 && lower&&upper&&digit&&special){
            System.out.println(password + ","+true);
        }else {
            System.out.println(password + ","+false);
        }


    }
}
