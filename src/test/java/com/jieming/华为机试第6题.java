package com.jieming;

import java.util.Scanner;

/**
 * 最长的指定瑕疵度的元音子串
 */
public class 华为机试第6题 {
    //  这提-题还有问题---还有组用例不能通过
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.nextLine();
        String str = scanner.nextLine();
        String replaceStr = str.toLowerCase().replaceAll("[a,e,i,o,u]", "a");
//        System.out.println(replaceStr);
        int max= 0 ;
        for (int i = 0; i < replaceStr.length(); i++) {
            char chi = replaceStr.charAt(i);
            if (chi != 'a'){
                continue;
            }
            int aeiou = 0;
            int real = 0;
            for (int j = i + 1; j < replaceStr.length(); j++) {
                char chj = replaceStr.charAt(j);
                if (chj != 'a'){
                    continue;
                }

                int len = j -i-1;
                aeiou += 1;
                real = len - aeiou;
                if (real > num){
                    continue;
                }
                if (real == num){
                    max = Math.max(max,len);
                }
            }
            if (real > num){
                continue;
            }
        }
        System.out.println(max);

    }

}
