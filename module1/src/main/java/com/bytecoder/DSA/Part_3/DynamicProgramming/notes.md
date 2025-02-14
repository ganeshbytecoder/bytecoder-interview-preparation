* top - bottom approach -> how many variables are used create a dp array 
* bottom top approach analysis the base condition


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

### 10. **[Minimum Operations to Make the Array K-Increasing](https://leetcode.com/problems/minimum-operations-to-make-the-array-k-increasing/description/)**
   - Problem: Find the minimum number of operations to make the array k-increasing.
   - **Recursion**:
     ```java
     public int minOperations(int[] arr, int k) {
         return kIncreasingHelper(arr, 0, k);
     }

     private int kIncreasingHelper(int[] arr, int index, int k) {
         if (index >= arr.length) return 0;

         int operations = 0;
         for (int i = index; i < arr.length; i += k) {
             if (i > index && arr[i] < arr[i - k]) {
                 operations++;
             }
         }

         return operations + kIncreasingHelper(arr, index + 1, k);
     }
     ```
   - **Memoization**:
     ```java
     public int minOperations(int[] arr, int k) {
         int[][] memo = new int[arr.length][k + 1];
         for (int[] row : memo) Arrays.fill(row, -1);
         return kIncreasingHelper(arr, 0, k, memo);
     }

     private int kIncreasingHelper(int[] arr, int index, int k, int[][] memo) {
         if (index >= arr.length) return 0;
         if (memo[index][k] != -1) return memo[index][k];

         int operations = 0;
         for (int i = index; i < arr.length; i += k) {
             if (i > index && arr[i] < arr[i - k]) {
                 operations++;
             }
         }

         return memo[index][k] = operations + kIncreasingHelper(arr, index + 1, k, memo);
     }
     ```
   - **Tabulation**:
     ```java
     public int minOperations(int[] arr, int k) {
         int operations = 0;
         for (int i = 0; i < k; i++) {
             List<Integer> subsequence = new ArrayList<>();
             for (int j = i; j < arr.length; j += k) {
                 subsequence.add(arr[j]);
             }
             operations += subsequence.size() - lengthOfLIS(subsequence);
         }
         return operations;
     }

     private int lengthOfLIS(List<Integer> nums) {
         List<Integer> dp = new ArrayList<>();
         for (int num : nums) {
             int pos = Collections.binarySearch(dp, num);
             if (pos < 0) pos = -(pos + 1);
             if (pos < dp.size()) dp.set(pos, num);
             else dp.add(num);
         }
         return dp.size();
     }
     ```

---



# Complete Dynamic Programming Guide

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

## Problem Categories

### 3. Common Patterns


## 1. Basic DP Problems

#### 1.1 Fibonacci Pattern Problems
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


## Distinct Ways
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



## 3. String-based DP
#### ip partition
https://leetcode.com/problems/partition-equal-subset-sum/description/
#### 3.1 Word Break Problems
##### [Word Break](https://leetcode.com/problems/word-break/)
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



## **Matrix Pattern**
- Grid traversal
- Path finding



## **Interval Pattern**
- Substring problems
- Range queries


## Interview Tips

## Must-Know Problems for FAANG

1. **Array/String DP**
     - Longest Increasing Subsequence
     - Edit Distance
     - Word Break
     - Regular Expression Matching

2. **Matrix DP**
     - Unique Paths
     - Minimum Path Sum
     - Maximal Square

3. **Game Theory DP**
     - Stone Game
     - Predict the Winner

4. **Interval DP**
     - Burst Balloons
     - Palindrome Problems

5. **Tree DP**
     - House Robber III
     - Binary Tree Maximum Path Sum

## Practice Strategy

1. **Start with Basics**
     - Fibonacci sequence
     - Climbing Stairs
     - Coin Change

2. **Move to Intermediate**
     - Longest Common Subsequence
     - Word Break
     - Jump Game

3. **Advanced Problems**
     - Regular Expression Matching
     - Burst Balloons
     - Edit Distance

4. **Company-Specific Focus**
     - Google: String DP
     - Amazon: Matrix DP
     - Facebook: Array DP
     - Apple: Tree DP














## other 


### **6. [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/)**

#### **Problem Statement**
Determine if you can partition an array into two subsets such that the sum of the elements in both subsets is equal.

---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java

private boolean canPartitionRecursive(int[] nums, int target, int index) {
    if (target == 0) return true; // Base case: subset found.
    if (index < 0 || target < 0) return false;
    return canPartitionRecursive(nums, target - nums[index], index - 1) || 
           canPartitionRecursive(nums, target, index - 1);
}

public boolean canPartition(int[] nums) {
    int totalSum = Arrays.stream(nums).sum();
    if (totalSum % 2 != 0) return false; // If total sum is odd, partition is not possible.
    return canPartitionRecursive(nums, totalSum / 2, nums.length - 1);
}


```

##### 2. **Memoization Solution**
```java
public boolean canPartition(int[] nums) {
    int totalSum = Arrays.stream(nums).sum();
    if (totalSum % 2 != 0) return false;
    int target = totalSum / 2;
    Boolean[][] memo = new Boolean[nums.length][target + 1];
    return canPartitionMemo(nums, target, nums.length - 1, memo);
}

private boolean canPartitionMemo(int[] nums, int target, int index, Boolean[][] memo) {
    if (target == 0) return true;
    if (index < 0 || target < 0) return false;
    if (memo[index][target] != null) return memo[index][target];
    memo[index][target] = canPartitionMemo(nums, target - nums[index], index - 1, memo) || 
                          canPartitionMemo(nums, target, index - 1, memo);
    return memo[index][target];
}
```

##### 3. **Tabulation Solution**
```java
public boolean canPartition(int[] nums) {
    int totalSum = Arrays.stream(nums).sum();
    if (totalSum % 2 != 0) return false;
    int target = totalSum / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int num : nums) {
        for (int j = target; j >= num; j--) {
            dp[j] = dp[j] || dp[j - num];
        }
    }
    return dp[target];
}
```

---

### **7. [Coin Change](https://leetcode.com/problems/coin-change/)**

#### **Problem Statement**
Find the minimum number of coins required to make up a given amount. If it's not possible, return `-1`.


**Note** similar questions like max/min number of elements or size of array to make target value from given array. it can be distinct or duplicate elements  
---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java
public int coinChange(int[] coins, int amount) {
    int result = coinChangeRecursive(coins, amount);
    return result == Integer.MAX_VALUE ? -1 : result;
}

private int coinChangeRecursive(int[] coins, int amount) {
    if (amount == 0) return 0; // Base case: no coins needed.
    if (amount < 0) return Integer.MAX_VALUE;
    int minCoins = Integer.MAX_VALUE;
    for (int coin : coins) {
        int res = coinChangeRecursive(coins, amount - coin);
        if (res != Integer.MAX_VALUE) {
            minCoins = Math.min(minCoins, 1 + res);
        }
    }
    return minCoins;
}
```

##### 2. **Memoization Solution**
```java
public int coinChange(int[] coins, int amount) {
    int[] memo = new int[amount + 1];
    Arrays.fill(memo, -1);
    int result = coinChangeMemo(coins, amount, memo);
    return result == Integer.MAX_VALUE ? -1 : result;
}

private int coinChangeMemo(int[] coins, int amount, int[] memo) {
    if (amount == 0) return 0;
    if (amount < 0) return Integer.MAX_VALUE;
    if (memo[amount] != -1) return memo[amount];

    int minCoins = Integer.MAX_VALUE;
    for (int coin : coins) {
        int res = coinChangeMemo(coins, amount - coin, memo);
        if (res != Integer.MAX_VALUE) {
            minCoins = Math.min(minCoins, 1 + res);
        }
    }
    memo[amount] = minCoins;
    return memo[amount];
}
```

##### 3. **Tabulation Solution**
```java
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (i >= coin) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    return dp[amount] > amount ? -1 : dp[amount];
}
```

---

## Hard

### **11. [Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii/)**

#### **Problem Statement**
Given a string `s`, partition it such that every substring is a palindrome. Return the minimum number of cuts needed to make the partition.

---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java
public int minCut(String s) {
    return minCutRecursive(s, 0, s.length() - 1);
}

private int minCutRecursive(String s, int start, int end) {
    if (isPalindrome(s, start, end)) return 0;

    int minCuts = Integer.MAX_VALUE;
    for (int i = start; i < end; i++) {
        if (isPalindrome(s, start, i)) {
            minCuts = Math.min(minCuts, 1 + minCutRecursive(s, i + 1, end));
        }
    }
    return minCuts;
}

private boolean isPalindrome(String s, int start, int end) {
    while (start < end) {
        if (s.charAt(start++) != s.charAt(end--)) return false;
    }
    return true;
}
```

##### 2. **Memoization Solution**
```java
public int minCut(String s) {
    int[] memo = new int[s.length()];
    Arrays.fill(memo, -1);
    return minCutMemo(s, 0, s.length() - 1, memo);
}

private int minCutMemo(String s, int start, int end, int[] memo) {
    if (isPalindrome(s, start, end)) return 0;
    if (memo[start] != -1) return memo[start];

    int minCuts = Integer.MAX_VALUE;
    for (int i = start; i < end; i++) {
        if (isPalindrome(s, start, i)) {
            minCuts = Math.min(minCuts, 1 + minCutMemo(s, i + 1, end, memo));
        }
    }
    return memo[start] = minCuts;
}
```

##### 3. **Tabulation Solution**
```java
public int minCut(String s) {
    int n = s.length();
    boolean[][] isPalindrome = new boolean[n][n];
    int[] dp = new int[n];

    for (int len = 1; len <= n; len++) {
        for (int i = 0; i <= n - len; i++) {
            int j = i + len - 1;
            if (s.charAt(i) == s.charAt(j) && (len <= 2 || isPalindrome[i + 1][j - 1])) {
                isPalindrome[i][j] = true;
            }
        }
    }

    for (int i = 0; i < n; i++) {
        if (isPalindrome[0][i]) {
            dp[i] = 0;
        } else {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
    }
    return dp[n - 1];
}
```

---

### **12. [Wildcard Matching](https://leetcode.com/problems/wildcard-matching/)**

#### **Problem Statement**
Check if a string `s` matches a pattern `p`, where:
- `?` matches any single character.
- `*` matches any sequence of characters (including the empty sequence).

---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java
public boolean isMatch(String s, String p) {
    return isMatchRecursive(s, p, 0, 0);
}

private boolean isMatchRecursive(String s, String p, int i, int j) {
    if (i == s.length() && j == p.length()) return true;
    if (j == p.length()) return false;
    if (i == s.length()) return p.charAt(j) == '*' && isMatchRecursive(s, p, i, j + 1);

    if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
        return isMatchRecursive(s, p, i + 1, j + 1);
    }

    if (p.charAt(j) == '*') {
        return isMatchRecursive(s, p, i + 1, j) || isMatchRecursive(s, p, i, j + 1);
    }

    return false;
}
```

##### 2. **Memoization Solution**
```java
public boolean isMatch(String s, String p) {
    Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
    return isMatchMemo(s, p, 0, 0, memo);
}

private boolean isMatchMemo(String s, String p, int i, int j, Boolean[][] memo) {
    if (i == s.length() && j == p.length()) return true;
    if (j == p.length()) return false;
    if (memo[i][j] != null) return memo[i][j];

    if (i < s.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
        return memo[i][j] = isMatchMemo(s, p, i + 1, j + 1, memo);
    }

    if (p.charAt(j) == '*') {
        return memo[i][j] = isMatchMemo(s, p, i, j + 1, memo) || 
                            (i < s.length() && isMatchMemo(s, p, i + 1, j, memo));
    }

    return memo[i][j] = false;
}
```

##### 3. **Tabulation Solution**
```java
public boolean isMatch(String s, String p) {
    int m = s.length(), n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;

    for (int j = 1; j <= n; j++) {
        if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            }
        }
    }
    return dp[m][n];
}
```

---

### **13. [Edit Distance](https://leetcode.com/problems/edit-distance/)**

#### **Problem Statement**
Given two strings `word1` and `word2`, return the minimum number of operations required to convert `word1` to `word2`.  
Operations include:
1. Insert a character.
2. Delete a character.
3. Replace a character.

---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java
public int minDistance(String word1, String word2) {
    return minDistanceRecursive(word1, word2, word1.length(), word2.length());
}

private int minDistanceRecursive(String word1, String word2, int m, int n) {
    if (m == 0) return n; // Insert all remaining characters of word2.
    if (n == 0) return m; // Delete all remaining characters of word1.

    if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
        return minDistanceRecursive(word1, word2, m - 1, n - 1);
    }

    return 1 + Math.min(
        Math.min(minDistanceRecursive(word1, word2, m - 1, n),     // Delete
                 minDistanceRecursive(word1, word2, m, n - 1)),    // Insert
        minDistanceRecursive(word1, word2, m - 1, n - 1)           // Replace
    );
}
```

---

##### 2. **Memoization Solution**
```java
public int minDistance(String word1, String word2) {
    int[][] memo = new int[word1.length() + 1][word2.length() + 1];
    for (int[] row : memo) Arrays.fill(row, -1);
    return minDistanceMemo(word1, word2, word1.length(), word2.length(), memo);
}

private int minDistanceMemo(String word1, String word2, int m, int n, int[][] memo) {
    if (m == 0) return n;
    if (n == 0) return m;

    if (memo[m][n] != -1) return memo[m][n];

    if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
        return memo[m][n] = minDistanceMemo(word1, word2, m - 1, n - 1, memo);
    }

    return memo[m][n] = 1 + Math.min(
        Math.min(minDistanceMemo(word1, word2, m - 1, n, memo),     // Delete
                 minDistanceMemo(word1, word2, m, n - 1, memo)),    // Insert
        minDistanceMemo(word1, word2, m - 1, n - 1, memo)           // Replace
    );
}
```

---

##### 3. **Tabulation Solution**
```java
public int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) dp[i][0] = i;
    for (int j = 0; j <= n; j++) dp[0][j] = j;

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 
                                        Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
    }
    return dp[m][n];
}
```

---

### **14. [Burst Balloons](https://leetcode.com/problems/burst-balloons/)**

#### **Problem Statement**
You are given `n` balloons, numbered from `0` to `n - 1`. Each balloon has a positive integer value associated with it. When you burst a balloon `i`, you gain `nums[left] * nums[i] * nums[right]` coins. Return the maximum coins you can collect by bursting the balloons wisely.

---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java

private int maxCoinsRecursive(int[] nums, int left, int right) {
    if (left > right) return 0;
    int maxCoins = 0;
    for (int i = left; i <= right; i++) {
        int coins = nums[left - 1] * nums[i] * nums[right + 1]
                  + maxCoinsRecursive(nums, left, i - 1)
                  + maxCoinsRecursive(nums, i + 1, right);
        maxCoins = Math.max(maxCoins, coins);
    }
    return maxCoins;
}

public int maxCoins(int[] nums) {
    int[] newNums = new int[nums.length + 2];
    newNums[0] = 1;
    newNums[newNums.length - 1] = 1;
    System.arraycopy(nums, 0, newNums, 1, nums.length);
    return maxCoinsRecursive(newNums, 1, nums.length);
}


```

---

##### 2. **Memoization Solution**
```java
public int maxCoins(int[] nums) {
    int[] newNums = new int[nums.length + 2];
    newNums[0] = 1;
    newNums[newNums.length - 1] = 1;
    System.arraycopy(nums, 0, newNums, 1, nums.length);
    int[][] memo = new int[newNums.length][newNums.length];
    return maxCoinsMemo(newNums, 1, nums.length, memo);
}

private int maxCoinsMemo(int[] nums, int left, int right, int[][] memo) {
    if (left > right) return 0;
    if (memo[left][right] != 0) return memo[left][right];

    int maxCoins = 0;
    for (int i = left; i <= right; i++) {
        int coins = nums[left - 1] * nums[i] * nums[right + 1]
                  + maxCoinsMemo(nums, left, i - 1, memo)
                  + maxCoinsMemo(nums, i + 1, right, memo);
        maxCoins = Math.max(maxCoins, coins);
    }
    return memo[left][right] = maxCoins;
}
```

---

##### 3. **Tabulation Solution**
```java
public int maxCoins(int[] nums) {
    int n = nums.length;
    int[] newNums = new int[n + 2];
    newNums[0] = newNums[n + 1] = 1;
    System.arraycopy(nums, 0, newNums, 1, n);
    int[][] dp = new int[n + 2][n + 2];

    for (int len = 1; len <= n; len++) {
        for (int left = 1; left <= n - len + 1; left++) {
            int right = left + len - 1;
            for (int i = left; i <= right; i++) {
                dp[left][right] = Math.max(dp[left][right], 
                                           newNums[left - 1] * newNums[i] * newNums[right + 1] 
                                           + dp[left][i - 1] 
                                           + dp[i + 1][right]);
            }
        }
    }
    return dp[1][n];
}
```

---

### **15. [Egg Drop Problem](https://leetcode.com/problems/super-egg-drop/)**

#### **Problem Statement**
You are given `k` eggs and a building with `n` floors. Your goal is to find the minimum number of attempts needed to find the critical floor.

---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java
public int superEggDrop(int k, int n) {
    if (n == 0 || n == 1) return n;
    if (k == 1) return n;

    int minAttempts = Integer.MAX_VALUE;
    for (int x = 1; x <= n; x++) {
        int attempts = 1 + Math.max(superEggDrop(k - 1, x - 1), 
                                    superEggDrop(k, n - x));
        minAttempts = Math.min(minAttempts, attempts);
    }
    return minAttempts;
}
```

---

##### 2. **Memoization Solution**
```java
public int superEggDrop(int k, int n) {
    int[][] memo = new int[k + 1][n + 1];
    for (int[] row : memo) Arrays.fill(row, -1);
    return eggDropMemo(k, n, memo);
}

private int eggDropMemo(int k, int n, int[][] memo) {
    if (n == 0 || n == 1) return n;
    if (k == 1) return n;
    if (memo[k][n] != -1) return memo[k][n];

    int minAttempts = Integer.MAX_VALUE;
    for (int x = 1; x <= n; x++) {
        int attempts = 1 + Math.max(eggDropMemo(k - 1, x - 1, memo), 
                                    eggDropMemo(k, n - x, memo));
        minAttempts = Math.min(minAttempts, attempts);
    }
    return memo[k][n] = minAttempts;
}
```

---

##### 3. **Tabulation Solution**
```java
public int superEggDrop(int k, int n) {
    int[][] dp = new int[k + 1][n + 1];
    for (int i = 1; i <= k; i++) {
        for (int j = 1; j <= n; j++) {
            dp[i][j] = 1 + dp[i - 1][j - 1] + dp[i][j - 1];
            if (dp[i][j] >= n) return j;
        }
    }
    return n;
}
```

---

Interview Tips and Problem Organization

### Must-Know Problems by Difficulty

### Easy (⭐)
1. Climbing Stairs
2. Min Cost Climbing Stairs
3. Best Time to Buy and Sell Stock
4. Maximum Subarray
5. Range Sum Query - Immutable

### Medium (⭐⭐)
1. House Robber
2. Jump Game
3. Unique Paths
4. Coin Change
5. Longest Increasing Subsequence

### Hard (⭐⭐⭐)
1. Edit Distance
2. Regular Expression Matching
3. Burst Balloons
4. Maximum Profit in Job Scheduling
5. Longest Common Subsequence


~~## string problems 

https://leetcode.com/problems/decode-string/description/

https://www.geeksforgeeks.org/roman-number-to-integer/

https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/

https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/

https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/?ref=asr30

https://www.geeksforgeeks.org/longest-substring-whose-characters-can-be-rearranged-to-form-a-palindrome/?ref=asr29

https://www.geeksforgeeks.org/print-longest-substring-without-repeating-characters/?ref=asr28

https://www.geeksforgeeks.org/longest-substring-that-can-be-made-a-palindrome-by-swapping-of-characters/?ref=asr27

https://www.geeksforgeeks.org/longest-substring-with-no-pair-of-adjacent-characters-are-adjacent-english-alphabets/?ref=asr26

https://www.geeksforgeeks.org/longest-substring-where-all-the-characters-appear-at-least-k-times-set-3/?ref=asr25

https://www.geeksforgeeks.org/longest-prefix-also-suffix/

https://www.geeksforgeeks.org/problems/length-of-the-longest-substring3036/1

https://leetcode.com/problems/longest-palindromic-substring/description/~~

https://leetcode.com/problems/longest-palindromic-subsequence/