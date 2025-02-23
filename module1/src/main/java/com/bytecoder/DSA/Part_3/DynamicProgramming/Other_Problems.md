**Dynamic Programming Notes on Leetcode Variants**
## Fundamentals
* **Top-down approach (Memoization)**
    - Analyze how many variables are used to create a dp array
    - Uses recursion with memoization
    - Easier to implement
    - More intuitive

* **Bottom-up approach (Tabulation)**
    - Analyze the base conditions
    - Uses iteration
    - More efficient in space
    - Sometimes harder to implement


https://blog.algomaster.io/p/20-patterns-to-master-dynamic-programming?utm_campaign=post&utm_medium=web
https://leetcode.com/studyplan/dynamic-programming/


## Fibonacci Sequence /pattern 
### Fibonacci series

### **[Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)**
- Problem: You can climb 1 or 2 steps. How many distinct ways can you reach the top of a staircase with `n` steps?

- **Recursion**:
  ```java
  public int climbStairs(int n) {
      if (n <= 2) return n;
      return climbStairs(n - 1) + climbStairs(n - 2);
  }
  ```

- **Memoization**:
  ```java
  private int climbHelper(int n, int[] memo) {
      if (n <= 2) return n;
      if (memo[n] != -1) return memo[n];
      return memo[n] = climbHelper(n - 1, memo) + climbHelper(n - 2, memo);
  }
  
  public int climbStairs(int n) {
      int[] memo = new int[n + 1];
      Arrays.fill(memo, -1);
      return climbHelper(n, memo);
  }
  
  ```
- **Tabulation**:
  ```java
  public int climbStairs(int n) {
      if (n <= 2) return n;
      int[] dp = new int[n + 1];
      dp[1] = 1; dp[2] = 2;
      for (int i = 3; i <= n; i++) {
          dp[i] = dp[i - 1] + dp[i - 2];
      }
      return dp[n];
  }
  ```

### ** [Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/) **
You are given an integer array `cost` where `cost[i]` is the cost of the `i-th` step. Once you pay the cost, you can either climb one or two steps. You can start from step 0 or step 1. Return the minimum cost to reach the top.

- **Solution Approaches**

1. **Recursive Solution**
```java
    public int minCostClimbingStairs(int[] cost) {
        return Math.min(minCost(cost, cost.length - 1), minCost(cost, cost.length - 2));
    }

    private int minCost(int[] cost, int i) {
        if (i < 0) return 0; // Base case
        if (i == 0 || i == 1) return cost[i];
        return cost[i] + Math.min(minCost(cost, i - 1), minCost(cost, i - 2));
    }

    int solve1(int[] cost,int index){
        if(index >= cost.length){
            return 0;
        }
        return cost[index] + Math.min(solve1(cost, index + 1 ),  solve1(cost, index + 2));
    }

```

2. **Memoization Solution**
```java

private int minCost(int[] cost, int i, int[] memo) {
    if (i < 0) return 0; // Base case
    if (i == 0 || i == 1) return cost[i];
    if (memo[i] != -1) return memo[i];
    return memo[i] = cost[i] + Math.min(minCost(cost, i - 1, memo), minCost(cost, i - 2, memo));
}

public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;
    int[] memo = new int[n];
    Arrays.fill(memo, -1);
    return Math.min(minCost(cost, n - 1, memo), minCost(cost, n - 2, memo));
}


```

3. **Tabulation Solution**
```java
public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;
    int[] dp = new int[n + 1];
    
    // Initialize base cases
    dp[0] = 0;
    dp[1] = 0;
    
    // Build the DP array
    for (int i = 2; i <= n; i++) {
        dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
    }
    
    return dp[n];
}
```

4. **Space-Optimized Tabulation**
```java
public int minCostClimbingStairs(int[] cost) {
    int prev1 = 0, prev2 = 0;
    
    for (int i = 2; i <= cost.length; i++) {
        int curr = Math.min(prev1 + cost[i - 1], prev2 + cost[i - 2]);
        prev2 = prev1;
        prev1 = curr;
    }
    
    return prev1;
}
```


---

##### [Jump Game](https://leetcode.com/problems/jump-game/)
**Problem**: Determine if you can reach the last index.

```java
// 1. Recursion - Time: O(2^n), Space: O(n)
public boolean canJump(int[] nums) {
    return canJumpRecursive(nums, 0);
}

private boolean canJumpRecursive(int[] nums, int index) {
    if (index >= nums.length - 1) return true;
    
    int maxJump = nums[index];
    for (int step = 1; step <= maxJump; step++) {
        if (canJumpRecursive(nums, index + step)) return true;
    }
    return false;
}

// 2. Memoization - Time: O(n²), Space: O(n)
public boolean canJump(int[] nums) {
    int[] memo = new int[nums.length];
    Arrays.fill(memo, -1); // -1: Not computed, 0: False, 1: True
    return canJumpMemo(nums, 0, memo);
}

private boolean canJumpMemo(int[] nums, int index, int[] memo) {
    if (index >= nums.length - 1) return true;
    if (memo[index] != -1) return memo[index] == 1;

    int maxJump = nums[index];
    for (int step = 1; step <= maxJump; step++) {
        if (canJumpMemo(nums, index + step, memo)) {
            memo[index] = 1;
            return true;
        }
    }
    memo[index] = 0;
    return false;
}

// 3. Greedy (Most Optimal) - Time: O(n), Space: O(1)
public boolean canJump(int[] nums) {
    int maxReach = 0;
    for (int i = 0; i <= maxReach && i < nums.length; i++) {
        maxReach = Math.max(maxReach, i + nums[i]);
        if (maxReach >= nums.length - 1) return true;
    }
    return false;
}
```

##  Kadane's Algorithm/ prefix sum for subarray 

LeetCode 53: Maximum Subarray

LeetCode 918: Maximum Sum Circular Subarray

LeetCode 152: Maximum Product Subarray



## Distinct ways
LeetCode 91: Decode Ways

LeetCode 2266. Count Number of Texts
##### [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
**Problem**: You can climb 1 or 2 steps. How many distinct ways to reach top?

```java
// 1. Recursion - Time: O(2^n), Space: O(n)
public int climbStairs(int n) {
    if (n <= 2) return n;
    return climbStairs(n - 1) + climbStairs(n - 2);
}

// 2. Memoization - Time: O(n), Space: O(n)
public int climbStairs(int n) {
    int[] memo = new int[n + 1];
    Arrays.fill(memo, -1);
    return climbHelper(n, memo);
}

private int climbHelper(int n, int[] memo) {
    if (n <= 2) return n;
    if (memo[n] != -1) return memo[n];
    return memo[n] = climbHelper(n - 1, memo) + climbHelper(n - 2, memo);
}

// 3. Tabulation - Time: O(n), Space: O(n)
public int climbStairs(int n) {
    if (n <= 2) return n;
    int[] dp = new int[n + 1];
    dp[1] = 1; dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}
```


## Catalan Numbers

## **Coin Change Variants**
1. **[Coin Change](https://leetcode.com/problems/coin-change/)** - DP for minimum number of coins.
2. **[Coin Change 2](https://leetcode.com/problems/coin-change-2/)** - Count ways to make change.
3. **[Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/)** - DP with order-sensitive sum combinations.
4. **[Perfect Squares](https://leetcode.com/problems/perfect-squares/)** - Find min squares summing to `n`.
5. **[Minimum Cost for Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/)** - DP tracking cost of valid travel days.


## **Matrix Pattern**
- Grid traversal
- Path finding
- LeetCode 62: Unique Paths
- LeetCode 64: Minimum Path Sum
- LeetCode 329. Longest Increasing Path in a Matrix


## **Matrix Multiplication Variants**
1. **[Minimum Score Triangulation of Polygon](https://leetcode.com/problems/minimum-score-triangulation-of-polygon/)** - DP with range.
2. **[Minimum Cost Tree from Leaf Values](https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/)** - DP/Greedy.
3. **[Burst Balloons](https://leetcode.com/problems/burst-balloons/)** - DP with range partitioning.

---

## **Matrix/2D Array Problems**
1. **[Matrix Block Sum](https://leetcode.com/problems/matrix-block-sum/)** - Prefix sum for efficient block sum queries.
2. **[Range Sum Query 2D Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/)** - Prefix sum table.
3. **[Dungeon Game](https://leetcode.com/problems/dungeon-game/)** - DP bottom-up to compute min health.
4. **[Triangle](https://leetcode.com/problems/triangle/)** - DP from bottom to top.
5. **[Maximal Square](https://leetcode.com/problems/maximal-square/)** - DP to find largest square of `1`s.
6. **[Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/)** - DP on rows.

---


## 0/1 Knapsack


## unbounded knapsack 
## **🎯 Minimization Problems Pattern: Unbounded Knapsack (Dynamic Programming)**
### 2. Array-based DP

##### [Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/)
**Problem**: Find minimum cost to reach the top. Can start from index 0 or 1.

```java
// 1. Recursion - Time: O(2^n), Space: O(n)
public int minCostClimbingStairs(int[] cost) {
    return Math.min(minCost(cost, cost.length - 1), 
                   minCost(cost, cost.length - 2));
}

private int minCost(int[] cost, int i) {
    if (i < 0) return 0;
    if (i == 0 || i == 1) return cost[i];
    return cost[i] + Math.min(minCost(cost, i - 1), 
                             minCost(cost, i - 2));
}

// 2. Memoization - Time: O(n), Space: O(n)
public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;
    int[] memo = new int[n];
    Arrays.fill(memo, -1);
    return Math.min(minCost(cost, n - 1, memo), 
                   minCost(cost, n - 2, memo));
}

private int minCost(int[] cost, int i, int[] memo) {
    if (i < 0) return 0;
    if (i == 0 || i == 1) return cost[i];
    if (memo[i] != -1) return memo[i];
    return memo[i] = cost[i] + Math.min(minCost(cost, i - 1, memo), 
                                       minCost(cost, i - 2, memo));
}

// 3. Tabulation - Time: O(n), Space: O(1)
public int minCostClimbingStairs(int[] cost) {
    int prev1 = 0, prev2 = 0;
    
    for (int i = 2; i <= cost.length; i++) {
        int curr = Math.min(prev1 + cost[i - 1], 
                          prev2 + cost[i - 2]);
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}
```

### 5.  Unbounded Knapsack **0/1 Pattern or  Unbounded Knapsack**
- Include/exclude choices
- Knapsack problems

The **Coin Change** problem falls under the **Unbounded Knapsack DP Pattern**, which is useful for solving **real-world problems involving combinations, minimum cost, and resource allocation**.
Here are some real-life problems and the pattern to recognize them.

This pattern applies to problems where:
1. You have **unlimited supply** of each item (like coins, food packets, etc.).
2. You need to **maximize/minimize a value** (like cost, count, weight).
3. You are trying to **fill a target amount/weight/capacity** optimally.

### **🔑 How to Identify This Pattern?**
**Ask yourself:**
✅ Do I have an **unlimited supply** of items?  
✅ Am I trying to **reach an exact target** (amount, weight, length, distance)?  
✅ Am I looking for the **minimum or maximum** way to do it?

If **yes**, then it’s a **Coin Change (Unbounded Knapsack) DP problem**! 🎯
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

```java
class Solution {

    int solve(int[] coins, int amount, HashMap<Integer, Integer> dp){
        if(amount==0){
            return 0;
        }
     
        if(amount < 0){
            return Integer.MAX_VALUE;
        }

        if(dp.get(amount) != null){
            return dp.get(amount);
        }

        int min = Integer.MAX_VALUE;
        for(int coin : coins){
            if(amount<coin) continue;
            int temp = solve(coins, amount-coin, dp);
            min = Math.min(min, temp != Integer.MAX_VALUE ? 1 + temp : temp);
        }
        dp.put(amount, min);

        return dp.get(amount);
    }


    
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);

        HashMap<Integer, Integer> dp= new HashMap<>();
        int count = solve(coins, amount, dp) ;
        if(count!= Integer.MAX_VALUE){
            return count;
        }
        return -1;
    }
}
```

M2  important

```java

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Fill with an unreachable max value
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}

```

---

### **🌍 Real-World Problems Using This Pattern**

### **1. Minimum Banknotes Needed (ATM Withdrawal)**
**Problem:**  
An ATM has banknotes of `{1, 5, 10, 20, 50, 100}`. A customer requests ₹287. Find the **minimum number of banknotes** required to dispense this amount.

🔹 **Solution:**  
Same as **coin change**, where:
- `coins = {1, 5, 10, 20, 50, 100}`
- `amount = 287`
- Goal: Minimize the number of notes.

---

### **2. Minimum Food Packets for Refugees**
**Problem:**  
A charity organization wants to distribute **food packets** to a refugee camp. Packets are available in `{5, 10, 20}` sizes. What is the **minimum number of packets** required to supply `N` kg of food?

🔹 **Solution:**
- `coins = {5, 10, 20}` (packet sizes)
- `amount = N` (total food requirement)
- **Minimize** the number of packets used.

---

### **3. Minimum Bottles to Fill a Tank**
**Problem:**  
You have water bottles of different capacities `{250ml, 500ml, 1L, 2L, 5L}`. Given a tank capacity of `T` liters, find the **minimum number of bottles** required to fill it exactly.

🔹 **Solution:**
- `coins = {250, 500, 1000, 2000, 5000}` (bottle sizes in ml)
- `amount = T * 1000` (convert liters to ml)
- **Minimize** the number of bottles used.

---

### **4. Making Exact Change in Vending Machines**
**Problem:**  
A vending machine has coins of `{1, 2, 5, 10}`. A customer inserts a bill, and the machine needs to return `X` amount in **minimum coins**.

🔹 **Solution:**
- Same as **coin change**, where we minimize the number of coins returned.

---

### **5. Cutting Metal Rods for Construction**
**Problem:**  
A construction company needs metal rods of length `L`. They have an unlimited supply of rods of `{1m, 2m, 5m, 10m}`. Find the **minimum number of rods** required to get exactly `L` meters.

🔹 **Solution:**
- `coins = {1, 2, 5, 10}` (rod lengths)
- `amount = L` (required length)
- **Minimize** the number of rods.

---

### **6. Minimum Jumps to Reach the End**
**Problem:**  
A frog can jump `{1, 2, 3}` stones at a time to cross a river of `N` stones. Find the **minimum jumps** required.

🔹 **Solution:**
- `coins = {1, 2, 3}` (jump sizes)
- `amount = N` (stones to cross)
- **Minimize** the number of jumps.

---

## **State Machine Problems (Stock Trading)**
1. **[Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)** - One transaction, find max profit.
2. **[Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)** - Multiple transactions.
3. **[Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/)** - At most two transactions.
4. **[Best Time to Buy and Sell Stock IV](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/)** - At most k transactions.
5. **[Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)** - Cannot buy immediately after selling.
6. **[Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)** - Each transaction has a fee.


## **Longest Common Subsequence (LCS) Variants**
1. **[Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)** - Classic DP with memoization.
2. **[Edit Distance](https://leetcode.com/problems/edit-distance/)** - DP table to compute min edit operations.
3. **[Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/)** - Count distinct subsequences using DP.
4. **[Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)** - DP to compute minimum delete cost for equal strings.



## **Longest Increasing Subsequence (LIS) Variants**
1. **[Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)** - Use DP with binary search (O(n log n)) or DP (O(n^2)).
2. **[Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/)** - DP with sorting, track the longest subset where `nums[j]` divides `nums[i]`.
3. **[Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/)** - Sort by width, apply LIS on height.
4. **[Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/)** - Sort pairs and apply LIS/Greedy.
5. **[Number of Longest Increasing Subsequences](https://leetcode.com/problems/number-of-longest-increasing-subsequence/)** - Use DP to count valid LIS sequences.
6. **[Delete and Earn](https://leetcode.com/problems/delete-and-earn/)** - Convert to a house-robber problem.
7. **[Longest String Chain](https://leetcode.com/problems/longest-string-chain/)** - Sort and use DP with a hashmap for longest chains.

---

## Palindromic Subsequence
1. **[Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii/)** - DP with a cut count for partitions.
2. **[Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)** - DP or center expansion.


## **Partition Subset / String Partition**
1. **[Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/)** - Use knapsack DP to check if a subset sums to `total/2`.
2. **[Last Stone Weight II](https://leetcode.com/problems/last-stone-weight-ii/)** - Similar to subset sum; minimize the difference of two subsets.
   https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/

#### [ip partition](https://leetcode.com/problems/partition-equal-subset-sum/description/)

#### [Word Break](https://leetcode.com/problems/word-break/)
**Problem**: Determine if string can be segmented into dictionary words.

```java
// 1. Recursion - Time: O(2^n), Space: O(n)
public boolean wordBreak(String s, List<String> wordDict) {
    return wordBreakRecursive(s, new HashSet<>(wordDict), 0);
}

private boolean wordBreakRecursive(String s, Set<String> dict, int start) {
    if (start == s.length()) return true;
    
    for (int end = start + 1; end <= s.length(); end++) {
        if (dict.contains(s.substring(start, end)) && 
            wordBreakRecursive(s, dict, end)) {
            return true;
        }
    }
    return false;
}

// 2. Memoization - Time: O(n²), Space: O(n)
public boolean wordBreak(String s, List<String> wordDict) {
    return wordBreakMemo(s, new HashSet<>(wordDict), 0, 
                        new Boolean[s.length()]);
}

private boolean wordBreakMemo(String s, Set<String> dict, 
                            int start, Boolean[] memo) {
    if (start == s.length()) return true;
    if (memo[start] != null) return memo[start];
    
    for (int end = start + 1; end <= s.length(); end++) {
        if (dict.contains(s.substring(start, end)) && 
            wordBreakMemo(s, dict, end, memo)) {
            return memo[start] = true;
        }
    }
    return memo[start] = false;
}

// 3. Tabulation - Time: O(n²), Space: O(n)
public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> dictionary = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    
    for (int i = 1; i <= s.length(); i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && dictionary.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[s.length()];
}
```

---


## **Interval Pattern**
- Substring problems
- Range queries
- Burst Balloons
- Palindrome Problems

## **Game Theory DP**
- Stone Game
- Predict the Winner

## **BitMasking**
1. **[Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/)** - Use bitmask DP to track subset partitions.


## DP on Trees
LeetCode 337: House Robber III

LeetCode 124: Binary Tree Maximum Path Sum

LeetCode 968: Binary Tree Cameras

## DP on Graphs

LeetCode 787: Cheapest Flights Within K Stops

LeetCode 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance

---

##  Digit DP


---

## **Hashing + DP**
1. **[Target Sum](https://leetcode.com/problems/target-sum/)** - DP with state `(index, sum)`.
2. **[Longest Arithmetic Sequence](https://leetcode.com/problems/longest-arithmetic-sequence/)** - DP with hash table.
3. **[Longest Arithmetic Subsequence of Given Difference](https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/)** - DP with hashmap.
4. **[Maximum Product of Splitted Binary Tree](https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/)** - DFS + DP.



---

## **DFS + DP Problems**
1. **[Out of Boundary Paths](https://leetcode.com/problems/out-of-boundary-paths/)** - DFS + DP to track valid paths.
2. **[Knight Probability in Chessboard](https://leetcode.com/problems/knight-probability-in-chessboard/)** - DP to track probabilities.

---

## **Minimax DP**
1. **[Predict the Winner](https://leetcode.com/problems/predict-the-winner/)** - Minimax DP for game strategy.
2. **[Stone Game](https://leetcode.com/problems/stone-game/)** - Optimal strategy using DP.


## Probability DP
LeetCode 688: Knight Probability in Chessboard

LeetCode 808: Soup Servings

LeetCode 837. New 21 Game


## Other Probelms

https://leetcode.com/problems/interleaving-string/submissions/1545724299/?envType=study-plan-v2&envId=top-interview-150