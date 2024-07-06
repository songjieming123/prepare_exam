package com.jieming;

import java.util.Arrays;


/**
 * 选择排序
 * 思想：选择排序，每轮找出最小的数，同该轮最大的第一个数进行交换
 */
public class 选择排序算法 {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 9, 23, 12, 13};
        int[] sort = sort(arr);
        System.out.println(Arrays.toString(sort));
    }

    public static int[] sort(int arr[]) {
        int min_index = 0;
        for (int i = 0; i < arr.length; i++) {
            min_index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    min_index = j;
                }
            }
            if (i != min_index) {
                int temp = arr[i];
                arr[i] = arr[min_index];
                arr[min_index] = temp;
            }

        }
        return arr;
    }
}
