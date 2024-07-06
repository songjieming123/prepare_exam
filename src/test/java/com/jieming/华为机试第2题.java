package com.jieming;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 整数对最小和
 */
public class 华为机试第2题 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int num = scanner.nextInt();
            list1.add(num);
        }

        int n= scanner.nextInt();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            list2.add(num);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int x :list1){
            for (int y :list2){
                result.add(x+y);
            }
        }
        result.sort(Comparator.comparing(Integer::intValue));

        int k = scanner.nextInt();
        int sum = 0;
        for (int i = 0;i<k;i++){
            sum += result.get(i);
        }

        System.out.println(sum);
    }
}
