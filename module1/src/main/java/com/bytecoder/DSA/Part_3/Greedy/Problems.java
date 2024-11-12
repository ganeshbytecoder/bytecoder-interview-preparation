package com.bytecoder.DSA.Part_3.Greedy;

import java.util.Arrays;

public class Problems {

    //  greedy (exceeding time limit)
    int greedy_solution(int[] coins, int amount, int ans) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return 100000;
        }

        for (int i = coins.length - 1; i >= 0; i--) {
            if (amount - coins[i] >= 0) {
                ans = Math.min(ans, 1 + greedy_solution(coins, amount - coins[i], ans));
            }
        }
        return ans;
    }

    // recusion + memoisation (DP)
    int dpSolution1(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[amount] != Integer.MAX_VALUE) {
            return dp[amount];
        }


        for (int coin : coins) {
            if (amount - coin >= 0) {
                int temp = dpSolution1(coins, amount - coin, dp) ;
                System.out.println(temp);
                dp[amount] = Math.min(dp[amount], temp == Integer.MAX_VALUE ? Integer.MAX_VALUE : temp + 1 );

            }else {return Integer.MAX_VALUE;}
        }
        return dp[amount];
    }


    public static void main(String[] args) {

        Problems problems = new Problems();

        int ans = 100000;
        int[] coins = {1, 2147483647};
        int amount =2;
//        Arrays.sort(coins);
//        ans = problems.greedy_solution(coins, 100, ans);
//        System.out.println(ans);

        int[] dp = new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE);

        System.out.println(problems.dpSolution1(coins, amount, dp));

    }
}
