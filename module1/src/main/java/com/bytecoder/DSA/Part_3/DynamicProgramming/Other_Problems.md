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


## **Hashing + DP**
1. **[Target Sum](https://leetcode.com/problems/target-sum/)** - DP with state `(index, sum)`.
2. **[Longest Arithmetic Sequence](https://leetcode.com/problems/longest-arithmetic-sequence/)** - DP with hash table.
3. **[Longest Arithmetic Subsequence of Given Difference](https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/)** - DP with hashmap.
4. **[Maximum Product of Splitted Binary Tree](https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/)** - DFS + DP.



## **Coin Change Variants**
1. **[Coin Change](https://leetcode.com/problems/coin-change/)** - DP for minimum number of coins.
2. **[Coin Change 2](https://leetcode.com/problems/coin-change-2/)** - Count ways to make change.
3. **[Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/)** - DP with order-sensitive sum combinations.
4. **[Perfect Squares](https://leetcode.com/problems/perfect-squares/)** - Find min squares summing to `n`.
5. **[Minimum Cost for Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/)** - DP tracking cost of valid travel days.





# these problems good to know but to must 

## **Game Theory DP**
- Stone Game
- Predict the Winner

## **BitMasking**
1. **[Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/)** - Use bitmask DP to track subset partitions.



---

##  Digit DP




---

## **Minimax DP**
1. **[Predict the Winner](https://leetcode.com/problems/predict-the-winner/)** - Minimax DP for game strategy.
2. **[Stone Game](https://leetcode.com/problems/stone-game/)** - Optimal strategy using DP.


## Probability DP
LeetCode 688: Knight Probability in Chessboard

LeetCode 808: Soup Servings

LeetCode 837. New 21 Game



## Catalan Numbers
LeetCode 96: Unique Binary Search Trees

LeetCode 22: Generate Parentheses



## Other Probelms
https://leetcode.com/problems/interleaving-string/submissions/1545724299/?envType=study-plan-v2&envId=top-interview-150