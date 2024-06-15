package com.jieming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题读明白：需要享受一次优惠---即从该数据开始有一个前面的数据大于临近的下一个数据。
 */

public class 华为机试第10题 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strArr = str.split(" ");
        int arr[] = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            int a = Integer.parseInt(strArr[i]);
            int sum = a;
            int max = a;
            for (int j = i+1; j < strArr.length; j++) {
                int b = Integer.parseInt(strArr[j]);
                if(max < b ){
                    break;
                }
                sum += b;
                max = b;

            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
