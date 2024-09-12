package com.bytecoder.DSA.LinearDS.Array_List;

import java.util.Arrays;

public class ArraySorting {
    public static void sortUsingInbuilt(int[] array) {
        Arrays.sort(array);
        for (int a : array) {
            System.out.print(a+ " ,");
        }

    }

    public static void main(String[] args) {
        sortUsingInbuilt(new int[]{1, 2, 3, 4, 133, 4, 2});
    }
}
