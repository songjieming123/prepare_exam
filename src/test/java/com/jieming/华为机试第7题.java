package com.jieming;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 众数和中位数
 */
public class 华为机试第7题 {

    /*
    * 基本考察的是map的相关方法
    *
    * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] arr = str.split(" ");
        Map<String, Long> map = Arrays.stream(arr)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        Long max = map.values().stream().max(Comparator.comparing(Long::intValue)).get();
        List<String> nums = map.entrySet().stream()
                .filter(t -> t.getValue().intValue() == max.intValue())
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
        int size = nums.size();
        if (size %2 ==0){
            int result = Integer.valueOf(nums.get(size / 2)) + Integer.valueOf(nums.get(size / 2 - 1))/2;
            System.out.println(result);
        }
        else {
            int result = Integer.valueOf(nums.get(size / 2));
            System.out.println(result);
        }
    }

}
