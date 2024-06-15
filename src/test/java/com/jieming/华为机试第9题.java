package com.jieming;

import java.util.Arrays;
import java.util.Scanner;

public class 华为机试第9题 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer num = scanner.nextInt();

        // 这个输入需要看看 到底是怎么回事----需要scanner.nextLine(); 或者scanner.next() 清楚缓存
        scanner.nextLine();
        String[] stusStr = scanner.nextLine().split(" ");


        int arr[] = new int[num];
        for (int i = 0; i < stusStr.length - 1; i++) {
            for (int j = i + 1; j < stusStr.length; j++) {
                if (Integer.parseInt(stusStr[j]) > Integer.parseInt(stusStr[i]) ) {
                    arr[i] = j;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
