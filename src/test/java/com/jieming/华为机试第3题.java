package com.jieming;

import java.util.Scanner;

/**
 * 找座位
 */
public class 华为机试第3题 {
    /*
     *  分析：1 首先判断第i个座位是否有人：
     *          a 若有，（因为空格位）说明需要间隔一个座位才能坐人，直接到i+2座位的情况
     *          b 若没有，则情况复杂，需要通过下个座位的情况来判断：
     *               b.1 若下个座位的情况有人（即i+1有人），因为需要间隔空位，需要i+3才能进行坐人判断
     *               b.2 若下个座位没人，说面当前位置是可以坐人的，
     *
     *
     * 注意：这个逻辑每次每次向前的进度要么是2 要么是3，所以判断当前座位前肯定是空格，不需要判断i-1的情况
     * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int i = 0;
        int ans = 0;
        while (i < input.length()) {
            if (input.charAt(i) == '1') {
                // 判断当前座位有人，则下次只能考虑i+2的情况
                i += 2;
            } else {
                // 判断当前座位是没人的，只需要i+1没人，则测试就可以插入，若i+1有人，则下次需要俺短i+3额 位置，
                // 此时逻辑回到了input.charAt(i) == '1'的位置
                if (input.charAt(i + 1) == '0' || i == input.length() - 1) {
                    // 这里需要注意i == input.length() - 1，是可以直接插入的
                    i += 2;
                    ans++;
                } else {
                    i += 3;
                }
            }
        }
        System.out.println(ans);

    }
}
