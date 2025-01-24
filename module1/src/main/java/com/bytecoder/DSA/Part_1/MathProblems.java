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

//https://leetcode.com/problems/sqrtx/description/?envType=study-plan-v2&envId=top-interview-150
//class Solution:
//
//    def sqrt_newton(self, num, precision=0.9):
//        if num < 0:
//            return None  # Square root of negative numbers is not real
//        if num == 0 or num == 1:
//            return num
//
//        guess = num / 2.0  # Initial guess
//        while abs(guess * guess - num) > precision:
//            guess = (guess + num / guess) / 2.0  # Refine the guess
//        return int(guess)
//
//    def mySqrt(self, x: int) -> int:
//        return self.sqrt_newton(x)

