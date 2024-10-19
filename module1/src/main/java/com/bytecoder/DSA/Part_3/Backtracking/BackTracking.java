package com.bytecoder.DSA.Part_3.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class BackTracking {
    public List<List<Integer>> subArrays = new ArrayList<>();

    public int allSubArrayCount(int[] arr, int index){
        if(index== arr.length){
            return 0;
        }
        int l1 = 1+  allSubArrayCount(arr, index+1);
        int l2 = allSubArrayCount(arr, index+1);
        return l1+l2;
    }

    public int allMaxSubSequence(int[] arr, int index){
        if(index== arr.length){
            return 0;
        }
        int l1 = arr[index] +  allMaxSubSequence(arr, index+1);
        int l2 = allMaxSubSequence(arr, index+1);
        return Math.max(l1,l2);
    }


    public int allMaxSubArray(int[] arr, int index, int ans){
        if(index== arr.length){
            return 0;
        }
        for (int i = index; i <arr.length; i++) {
            int l1 = arr[i] +  allMaxSubArray(arr, i+1, ans);
            ans =Math.max(ans,l1);
        }
        return ans;
    }




     public Integer[] allSubArray(int[] arr, int index){
        if(index== arr.length){
            return new Integer[0];
        }
        Integer[] l1 = new Integer[]{arr[index],  allSubArrayCount(arr, index+1)};
        Integer[] l2 = new Integer[]{allSubArrayCount(arr, index+1)};
         System.out.println();
         for (int i = 0; i < l1.length; i++) {
             System.out.print(l1[i]);
         }
         System.out.println();
         for (int i = 0; i < l2.length ; i++) {
             System.out.print(l2[i]);
         }
        return new Integer[0];
    }





    public static void main(String[] args) {
        BackTracking backTracking = new BackTracking();
                int ans =Integer.MIN_VALUE;

        System.out.println(backTracking.allSubArrayCount(new int[]{1,2,3}, 0));

        System.out.println(backTracking.allMaxSubSequence(new int[]{-1,-2,3}, 0));

        System.out.println(backTracking.allMaxSubArray(new int[]{-1,2,-3}, 0, ans));


//        backTracking.allSubArray(new int[]{1,2,3}, 0);
//        backTracking.subArrays.forEach(System.out::println);
    }

}
