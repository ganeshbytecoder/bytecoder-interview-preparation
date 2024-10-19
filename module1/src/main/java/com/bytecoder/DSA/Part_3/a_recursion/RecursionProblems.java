package com.bytecoder.DSA.Part_3.a_recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//[01:44, 17/10/2024] Ganesh Choudhary: 1.
//2. Sum of array using recursion
//[01:44, 17/10/2024] Ganesh Choudhary: Linear search in unsorted array find a value
//[01:44, 17/10/2024] Ganesh Choudhary: Binary search
//[01:44, 17/10/2024] Ganesh Choudhary: Reverse string or array
//[01:44, 17/10/2024] Ganesh Choudhary: Check palindrome
//[01:44, 17/10/2024] Ganesh Choudhary: A to the power b . A n b is inputs
//[01:44, 17/10/2024] Ganesh Choudhary: Bubble sort
// Merge and quick sort
public class RecursionProblems {


    public static boolean isArraySorted(int[] nums, int k) {
        if (k == nums.length - 1) {
            return true;
        }

        if (nums[k] <= nums[k + 1]) {
            return isArraySorted(nums, k + 1);
        } else {
            return false;
        }
    }

    //     Sum of array using recursion
    public static int sumOfArray(int[] nums, int k) {
        if (k == nums.length) {
            return 0;
        }
        return nums[k] + sumOfArray(nums, k + 1);
    }


    //     Linear search in unsorted array find a value
    public static boolean searchTarget(int[] nums, int k, int target) {
        if (k == nums.length) {
            return true;
        }
        if (nums[k] == target) {
            return true;
        }
        return searchTarget(nums, k + 1, target);
    }


    //    Reverse string or array
    public static int[] reverseArray(int[] nums, int i, int j) {
        if (i > j) {
            return nums;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return reverseArray(nums, i + 1, j - 1);
    }

    //    Check palindrome
    public static boolean checkPalindrome(int[] nums, int i, int j) {
        if (i > j) {
            return true;
        }
        if (nums[i] == nums[j]) {
            return checkPalindrome(nums, i + 1, j - 1);
        } else {
            return false;
        }
    }


    //    power of a on b
    public static long powerOfa(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b % 2 == 0) {
            return powerOfa(a * a, b / 2);
        } else {
            return a * powerOfa(a * a, b / 2);
        }
    }

    //    power of a on b
    public static long powerOfaV2(int a, int b) {
        if (b == 0) {
            return 1;
        }
        long ans = powerOfaV2(a, b / 2);
        if (b % 2 == 0) {
            return ans * ans;
        } else {
            return a * ans * ans;
        }
    }

    public static boolean binarySearch(int[] nums, int i, int j, int target) {
        if (i > j) {
            return false;
        }
        int mid = (i + j) / 2;
        if (nums[mid] == target) {
            return true;
        }
        if (nums[mid] > target) {
            return binarySearch(nums, i, mid, target);
        } else {
            return binarySearch(nums, mid + 1, j, target);

        }

    }

    public static  void powerSet(int[] nums, int index, List<Integer> output, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(output));
            System.out.println(output);
            return;
        }
        // exlclude
        powerSet(nums, index + 1, output, ans);

        output.add(nums[index]);
        powerSet(nums, index + 1, output, ans);
        output.remove(Integer.valueOf(nums[index]));
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 2, 1};
        System.out.println(isArraySorted(nums, 0));
        System.out.println(sumOfArray(nums, 0));
        System.out.println(searchTarget(nums, 0, 6));
        Arrays.stream(reverseArray(nums, 0, nums.length - 1)).forEach(System.out::print);
        System.out.println();
        System.out.println(checkPalindrome(nums, 0, nums.length - 1));

        System.out.println(powerOfaV2(2, 10));
        System.out.println(powerOfa(2, 10));

        int[] nums2 = {1, 2, 10, 20, 40};
        System.out.println(binarySearch(nums2, 0, nums2.length - 1, 100));
        System.out.println(Character.isDigit('9'));

        System.out.println("printing power set ");
//        power set contains all the subset of a set => 2^n
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        int[] input = {1,2,3,4};
        powerSet(input, 0,output, ans);
        ans.stream().forEach(System.out::println);
        System.out.println("all sub-set " + ans.size());

    }
}
