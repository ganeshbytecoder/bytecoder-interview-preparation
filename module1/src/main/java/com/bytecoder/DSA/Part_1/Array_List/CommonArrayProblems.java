package com.bytecoder.DSA.Part_1.Array_List;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//How to find all pairs on integer array whose sum is equal to given number
public class CommonArrayProblems {
    public static void printPairsUsingTwoPointers(int[] numbers, int k) {
        if (numbers.length < 2) {
            return;
        }
        Arrays.sort(numbers);

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == k) {
                System.out.printf("(%d, %d) %n", numbers[left],
                        numbers[right]);
                left = left + 1;
                right = right - 1;

            } else if (sum < k) {
                left = left + 1;

            } else if (sum > k) {
                right = right - 1;
            }
        }

    }

    public static void printPairsUsingHashTable(int[] numbers, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int a : numbers) {
            if (map.get(a) != null) {
                int b = map.get(a);
                System.out.printf("(%d,%d) \n ", a, b);
            } else {
                map.put((Integer) k - a, a);
            }


        }

    }

//    Find Largest Sum Contiguous Subarray (Kadane's Algorithm)
    public int maxSubArray(int[] nums) {
        int max = nums[0], sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        printPairsUsingTwoPointers(new int[]{1, 9, 10, 8, 2}, 10);
        printPairsUsingHashTable(new int[]{1, 9, 10, 8, 2}, 10);


    }
}
