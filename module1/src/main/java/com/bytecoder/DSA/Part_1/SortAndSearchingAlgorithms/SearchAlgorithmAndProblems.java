package com.bytecoder.DSA.Part_1.SortAndSearchingAlgorithms;

public class SearchAlgorithmAndProblems {

//    finding element from ordered array:
//        1. Binary search

    private boolean findElement(int[] arr, int start, int end, int target) {
        if (start >= end) {
            return false;
        }
        int mid = (end + start) / 2;
        if (arr[mid] == target) {
            return true;
        } else if (arr[mid] > target) {
            return findElement(arr, start, mid, target);
        } else {
            return findElement(arr, mid + 1, end, target);
        }

    }

    public boolean findElementUsingBST(int[] arr, int target) {
        System.out.println(38 % 9);
        findElement(arr, 0, arr.length - 1, target);

        int start = 0, end = arr.length - 1;

        while (start < end) {
            int mid = (end + start) / 2;

            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] > target) {
                end = mid;

            } else {
                start = mid + 1;
            }

        }
        return false;


    }

//    finding a element from unorder array

//    1. brute-force
//    make binary search tree and then search -> time complexity = O(log(n)) + O(log(n))

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i]= array[j];
        array[j]=temp;
    }
    public static void main(String[] args) {
        int[] array = {10, 2, 3, 5, 0};
         for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array.length - 1; j++) {
            if (array[j+1] < array[j]) {
                swap(array, j, j+1);
            }
        }
        }
        for (int i : array) {
            System.out.println(i);
        }
    }
}
