package com.bytecoder.DSA.Part_1;

import java.util.HashSet;

public class MathProblems {

    //    Write an algorithm to determine if a number is happy or not

    //  method return true if n is Happy Number
    private static int numSquareSum(int n)
    {
        int num = 0;
        while (n != 0) {
            int digit = n % 10;
            num += digit * digit;
            n /= 10;
        }
        return num;
    }

    static boolean isHappyNumber(int n) {
        HashSet<Integer> st = new HashSet<>();
        while (true) {
            n = numSquareSum(n);
            if (n == 1)
                return true;
            if (st.contains(n))
                return false;
            st.add(n);
        }
    }




}
