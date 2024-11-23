- 279: Perfect Squares
- 746: Min Cost Climbing Stairs
- Coin Change

https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/ 

## **0. Greedy Solution**

```java
   public int maxProfit(int[] prices) {
        int n= prices.length;
        int profit = 0;
        // take into profit only those days for which the next day's price is greater.
        for(int day=1;day<n;day++){
            if(prices[day]>prices[day-1]){
                profit +=(prices[day] - prices[day-1]);
            }
        }
        return profit;
    }
```


## **1. Recursive Solution**

The recursive approach explores all possibilities (buy, sell, skip) for each day.

### Code:
```java
class Solution {
    public int maxProfit(int[] prices) {
        return calcProfit(prices, 0, 1); // Start with index 0 and option to buy (1)
    }

    private int calcProfit(int[] prices, int day, int canBuy) {
        if (day >= prices.length) {
            return 0; // Base case: no more days left
        }

        int maxProfit = 0;
        if (canBuy == 1) {
            // Option to buy or skip
            int skipBuy = calcProfit(prices, day + 1, 1);
            int buy = -prices[day] + calcProfit(prices, day + 1, 0);
            maxProfit = Math.max(skipBuy, buy);
        } else {
            // Option to sell or skip
            int skipSell = calcProfit(prices, day + 1, 0);
            int sell = prices[day] + calcProfit(prices, day + 1, 1);
            maxProfit = Math.max(skipSell, sell);
        }

        return maxProfit;
    }
}
```

---

## **2. Memoization Solution**

Memoization avoids redundant calculations by storing results for already-computed states.

### Code:
```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // Initialize dp array with -1
        int[][] dp = new int[n][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return calcProfit(prices, dp, 0, 1);
    }

    private int calcProfit(int[] prices, int[][] dp, int day, int canBuy) {
        if (day >= prices.length) {
            return 0; // Base case
        }

        // If result is already calculated, return it
        if (dp[day][canBuy] != -1) {
            return dp[day][canBuy];
        }

        int maxProfit = 0;
        if (canBuy == 1) {
            int skipBuy = calcProfit(prices, dp, day + 1, 1);
            int buy = -prices[day] + calcProfit(prices, dp, day + 1, 0);
            maxProfit = Math.max(skipBuy, buy);
        } else {
            int skipSell = calcProfit(prices, dp, day + 1, 0);
            int sell = prices[day] + calcProfit(prices, dp, day + 1, 1);
            maxProfit = Math.max(skipSell, sell);
        }

        // Store result in dp array
        dp[day][canBuy] = maxProfit;
        return maxProfit;
    }
}
```

---

## **3. Tabulation Solution**

Tabulation builds the solution iteratively from the base case.

### Code:
```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2]; // dp[day][canBuy]

        // Base cases: no profit possible after the last day
        dp[n][0] = 0;
        dp[n][1] = 0;

        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    int skipBuy = dp[day + 1][1];
                    int buy = -prices[day] + dp[day + 1][0];
                    dp[day][canBuy] = Math.max(skipBuy, buy);
                } else {
                    int skipSell = dp[day + 1][0];
                    int sell = prices[day] + dp[day + 1][1];
                    dp[day][canBuy] = Math.max(skipSell, sell);
                }
            }
        }

        return dp[0][1]; // Start with day 0 and the option to buy
    }
}
```

---

## **4. Optimized Space Tabulation**

Since each state depends only on the next day's state, you can reduce space complexity to **O(1)**.

### Code:
```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int nextBuy = 0, nextSell = 0;

        for (int day = n - 1; day >= 0; day--) {
            int currBuy = Math.max(nextBuy, -prices[day] + nextSell);
            int currSell = Math.max(nextSell, prices[day] + nextBuy);

            nextBuy = currBuy;
            nextSell = currSell;
        }

        return nextBuy; // Start with the option to buy
    }
}
```

---



https://leetcode.com/problems/jump-game/description/ 

recursion -> tabulation -> memoization 

