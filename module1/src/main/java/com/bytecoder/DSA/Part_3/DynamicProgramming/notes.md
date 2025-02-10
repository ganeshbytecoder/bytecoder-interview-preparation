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


### ** [Jump Game](https://leetcode.com/problems/jump-game/) **
You are given an integer array `nums`. You are initially positioned at the first index, and each element in the array represents your maximum jump length at that position. Determine if you can reach the last index.

#### 1. **Recursive Solution**
The recursive approach explores all possible jumps from the current position. It uses the current index and determines if reaching the last index is possible.

```java

private boolean canJumpRecursive(int[] nums, int index) {
if (index >= nums.length - 1) return true; // Reached the last index.

    int maxJump = nums[index];
    for (int step = 1; step <= maxJump; step++) {
        if (canJumpRecursive(nums, index + step)) return true;
    }
    return false;
}

public boolean canJump(int[] nums) {
return canJumpRecursive(nums, 0);
}
```


#### 2. **Memoization Solution**
The memoized approach stores the results of previously computed indices to avoid redundant calculations.

```java
public boolean canJump(int[] nums) {
    int[] memo = new int[nums.length];
    Arrays.fill(memo, -1); // -1: Not computed, 0: False, 1: True
    return canJumpMemo(nums, 0, memo);
}

private boolean canJumpMemo(int[] nums, int index, int[] memo) {
    if (index >= nums.length - 1) return true; // Reached the last index.
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
```


#### 3. **Tabulation Solution**
The tabulation approach uses an array to track whether each index is reachable. It iterates through the array, updating the reachable states.

```java
public boolean canJump(int[] nums) {
    boolean[] dp = new boolean[nums.length];
    dp[0] = true; // Start is always reachable.

    for (int i = 0; i < nums.length; i++) {
        if (!dp[i]) continue; // If this index is not reachable, skip.

        int maxJump = nums[i];
        for (int j = i + 1; j <= Math.min(i + maxJump, nums.length - 1); j++) {
            dp[j] = true;
        }
    }
    return dp[nums.length - 1];
}
```

**Time Complexity**: \(O(n^2)\)  
**Space Complexity**: \(O(n)\)

---

#### 4. **Optimized Greedy Solution**
A greedy approach tracks the farthest position reachable from each index and checks if the last index is within reach.

```java
public boolean canJump(int[] nums) {
    int farthest = 0;

    for (int i = 0; i < nums.length; i++) {
        if (i > farthest) return false; // If current index is not reachable.
        farthest = Math.max(farthest, i + nums[i]);
    }
    return true;
}
```

* https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair/description/

### 1. **[Longest Increasing Subsequence II](https://leetcode.com/problems/longest-increasing-subsequence-ii/description/)**
   - Problem: Find the length of the longest increasing subsequence within a limited difference range.
   - **Recursion**:
     ```java
     public int lengthOfLIS(int[] nums, int k) {
         return lisHelper(nums, -1, 0, k);
     }

     private int lisHelper(int[] nums, int prevIndex, int currIndex, int k) {
         if (currIndex == nums.length) return 0;

         int exclude = lisHelper(nums, prevIndex, currIndex + 1, k);
         int include = 0;
         if (prevIndex == -1 || (nums[currIndex] - nums[prevIndex] <= k && nums[currIndex] > nums[prevIndex])) {
             include = 1 + lisHelper(nums, currIndex, currIndex + 1, k);
         }

         return Math.max(include, exclude);
     }
     ```
   - **Memoization**:
     ```java
     public int lengthOfLIS(int[] nums, int k) {
         int[][] memo = new int[nums.length][nums.length + 1];
         for (int[] row : memo) Arrays.fill(row, -1);
         return lisHelper(nums, -1, 0, k, memo);
     }

     private int lisHelper(int[] nums, int prevIndex, int currIndex, int k, int[][] memo) {
         if (currIndex == nums.length) return 0;
         if (memo[prevIndex + 1][currIndex] != -1) return memo[prevIndex + 1][currIndex];

         int exclude = lisHelper(nums, prevIndex, currIndex + 1, k, memo);
         int include = 0;
         if (prevIndex == -1 || (nums[currIndex] - nums[prevIndex] <= k && nums[currIndex] > nums[prevIndex])) {
             include = 1 + lisHelper(nums, currIndex, currIndex + 1, k, memo);
         }

         return memo[prevIndex + 1][currIndex] = Math.max(include, exclude);
     }
     ```
   - **Tabulation**:
     ```java
     public int lengthOfLIS(int[] nums, int k) {
         int[] dp = new int[nums.length];
         Arrays.fill(dp, 1);

         for (int i = 1; i < nums.length; i++) {
             for (int j = 0; j < i; j++) {
                 if (nums[i] > nums[j] && nums[i] - nums[j] <= k) {
                     dp[i] = Math.max(dp[i], dp[j] + 1);
                 }
             }
         }

         int max = 0;
         for (int val : dp) max = Math.max(max, val);
         return max;
     }
     ```

---

### 2. **[Longest Ideal Subsequence](https://leetcode.com/problems/longest-ideal-subsequence/description/)**
   - Problem: Find the longest ideal subsequence with a character difference limit.
   - **Recursion**:
     ```java
     public int longestIdealString(String s, int k) {
         return lisHelper(s, -1, 0, k);
     }

     private int lisHelper(String s, int prevIndex, int currIndex, int k) {
         if (currIndex == s.length()) return 0;

         int exclude = lisHelper(s, prevIndex, currIndex + 1, k);
         int include = 0;
         if (prevIndex == -1 || Math.abs(s.charAt(currIndex) - s.charAt(prevIndex)) <= k) {
             include = 1 + lisHelper(s, currIndex, currIndex + 1, k);
         }

         return Math.max(include, exclude);
     }
     ```
   - **Memoization**:
     ```java
     public int longestIdealString(String s, int k) {
         int[][] memo = new int[s.length()][s.length + 1];
         for (int[] row : memo) Arrays.fill(row, -1);
         return lisHelper(s, -1, 0, k, memo);
     }

     private int lisHelper(String s, int prevIndex, int currIndex, int k, int[][] memo) {
         if (currIndex == s.length()) return 0;
         if (memo[prevIndex + 1][currIndex] != -1) return memo[prevIndex + 1][currIndex];

         int exclude = lisHelper(s, prevIndex, currIndex + 1, k, memo);
         int include = 0;
         if (prevIndex == -1 || Math.abs(s.charAt(currIndex) - s.charAt(prevIndex)) <= k) {
             include = 1 + lisHelper(s, currIndex, currIndex + 1, k, memo);
         }

         return memo[prevIndex + 1][currIndex] = Math.max(include, exclude);
     }
     ```
   - **Tabulation**:
     ```java
     public int longestIdealString(String s, int k) {
         int[] dp = new int[128]; // ASCII range
         int max = 0;

         for (char c : s.toCharArray()) {
             int currMax = 0;
             for (int i = Math.max(0, c - k); i <= Math.min(127, c + k); i++) {
                 currMax = Math.max(currMax, dp[i]);
             }
             dp[c] = currMax + 1;
             max = Math.max(max, dp[c]);
         }

         return max;
     }
     ```

---

### 3. **[Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/)**
   - Problem: Find the number of longest increasing subsequences.
   - **Recursion**:
     ```java
  public int findNumberOfLIS(int[] nums) {
    return findLISHelper(nums, -1, 0, 1)[0];
}

private int[] findLISHelper(int[] nums, int prevIndex, int currIndex, int length) {
    if (currIndex == nums.length) return new int[]{1, length - 1};

        int[] exclude = findLISHelper(nums, prevIndex, currIndex + 1, length);
        int[] include = {0, 0};
    
        if (prevIndex == -1 || nums[currIndex] > nums[prevIndex]) {
            include = findLISHelper(nums, currIndex, currIndex + 1, length + 1);
        }
    
        if (include[1] > exclude[1]) {
            return include;
        } else if (include[1] == exclude[1]) {
            return new int[]{include[0] + exclude[0], include[1]};
        } else {
            return exclude;
        }
    }

     ```
   - **Memoization**:
     ```java
     public int findNumberOfLIS(int[] nums) {
         int[][] memo = new int[nums.length][nums.length + 1];
         for (int[] row : memo) Arrays.fill(row, -1);
         return findLISHelper(nums, -1, 0, 1, memo);
     }

     private int findLISHelper(int[] nums, int prevIndex, int currIndex, int length, int[][] memo) {
         if (currIndex == nums.length) return new int[]{1, length - 1};
         if (memo[prevIndex + 1][currIndex] != -1) return memo[prevIndex + 1][currIndex];

         int[] exclude = findLISHelper(nums, prevIndex, currIndex + 1, length, memo);
         int[] include = {0, 0};
         if (prevIndex == -1 || nums[currIndex] > nums[prevIndex]) {
             include = findLISHelper(nums, currIndex, currIndex + 1, length + 1, memo);
         }

         if (include[1] > exclude[1]) {
             return memo[prevIndex + 1][currIndex] = include;
         } else if (include[1] == exclude[1]) {
             return memo[prevIndex + 1][currIndex] = new int[]{include[0] + exclude[0], include[1]};
         } else {
             return memo[prevIndex + 1][currIndex] = exclude;
         }
     }
     ```
   - **Tabulation**:
     ```java
     public int findNumberOfLIS(int[] nums) {
         int n = nums.length;
         int[] lengths = new int[n];
         int[] counts = new int[n];
         Arrays.fill(lengths, 1);
         Arrays.fill(counts, 1);

         int maxLength = 0, totalCount = 0;
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < i; j++) {
                 if (nums[i] > nums[j]) {
                     if (lengths[j] + 1 > lengths[i]) {
                         lengths[i] = lengths[j] + 1;
                         counts[i] = counts[j];
                     } else if (lengths[j] + 1 == lengths[i]) {
                         counts[i] += counts[j];
                     }
                 }
             }
             maxLength = Math.max(maxLength, lengths[i]);
         }

         for (int i = 0; i < n; i++) {
             if (lengths[i] == maxLength) totalCount += counts[i];
         }

         return totalCount;
     }
     ```

---

### 4. **[Find the Maximum Length of Valid Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/description/)**
   - Problem: Find the maximum length of a valid subsequence from the input.
   - **Recursion**:
     ```java
     public int maxLengthValidSubsequenceI(int[] nums, int k) {
         return validSubseqHelper(nums, -1, 0, k);
     }

     private int validSubseqHelper(int[] nums, int prevIndex, int currIndex, int k) {
         if (currIndex == nums.length) return 0;

         int exclude = validSubseqHelper(nums, prevIndex, currIndex + 1, k);
         int include = 0;
         if (prevIndex == -1 || nums[currIndex] - nums[prevIndex] <= k) {
             include = 1 + validSubseqHelper(nums, currIndex, currIndex + 1, k);
         }

         return Math.max(include, exclude);
     }
     ```
   - **Memoization**:
     ```java
     public int maxLengthValidSubsequenceI(int[] nums, int k) {
         int[][] memo = new int[nums.length + 1][nums.length];
         for (int[] row : memo) Arrays.fill(row, -1);
         return validSubseqHelper(nums, -1, 0, k, memo);
     }

     private int validSubseqHelper(int[] nums, int prevIndex, int currIndex, int k, int[][] memo) {
         if (currIndex == nums.length) return 0;
         if (memo[prevIndex + 1][currIndex] != -1) return memo[prevIndex + 1][currIndex];

         int exclude = validSubseqHelper(nums, prevIndex, currIndex + 1, k, memo);
         int include = 0;
         if (prevIndex == -1 || nums[currIndex] - nums[prevIndex] <= k) {
             include = 1 + validSubseqHelper(nums, currIndex, currIndex + 1, k, memo);
         }

         return memo[prevIndex + 1][currIndex] = Math.max(include, exclude);
     }
     ```
   - **Tabulation**:
     ```java
     public int maxLengthValidSubsequenceI(int[] nums, int k) {
         int[] dp = new int[nums.length];
         Arrays.fill(dp, 1);

         for (int i = 1; i < nums.length; i++) {
             for (int j = 0; j < i; j++) {
                 if (nums[i] - nums[j] <= k) {
                     dp[i] = Math.max(dp[i], dp[j] + 1);
                 }
             }
         }

         int max = 0;
         for (int val : dp) max = Math.max(max, val);
         return max;
     }
     ```

---

### 5. **[Find the Maximum Length of Valid Subsequence II](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/description/)**
   - Problem: Find the maximum length of a valid subsequence based on specific conditions.
   - **Recursion**:
     ```java
     public int maxLengthValidSubsequenceII(int[] nums, int k) {
         return validSubseqHelper(nums, -1, 0, k);
     }

     private int validSubseqHelper(int[] nums, int prevIndex, int currIndex, int k) {
         if (currIndex == nums.length) return 0;

         int exclude = validSubseqHelper(nums, prevIndex, currIndex + 1, k);
         int include = 0;
         if (prevIndex == -1 || Math.abs(nums[currIndex] - nums[prevIndex]) <= k) {
             include = 1 + validSubseqHelper(nums, currIndex, currIndex + 1, k);
         }

         return Math.max(include, exclude);
     }
     ```
   - **Memoization**:
     ```java
     public int maxLengthValidSubsequenceII(int[] nums, int k) {
         int[][] memo = new int[nums.length + 1][nums.length];
         for (int[] row : memo) Arrays.fill(row, -1);
         return validSubseqHelper(nums, -1, 0, k, memo);
     }

     private int validSubseqHelper(int[] nums, int prevIndex, int currIndex, int k, int[][] memo) {
         if (currIndex == nums.length) return 0;
         if (memo[prevIndex + 1][currIndex] != -1) return memo[prevIndex + 1][currIndex];

         int exclude = validSubseqHelper(nums, prevIndex, currIndex + 1, k, memo);
         int include = 0;
         if (prevIndex == -1 || Math.abs(nums[currIndex] - nums[prevIndex]) <= k) {
             include = 1 + validSubseqHelper(nums, currIndex, currIndex + 1, k, memo);
         }

         return memo[prevIndex + 1][currIndex] = Math.max(include, exclude);
     }
     ```
   - **Tabulation**:
     ```java
     public int maxLengthValidSubsequenceII(int[] nums, int k) {
         int[] dp = new int[nums.length];
         Arrays.fill(dp, 1);

         for (int i = 1; i < nums.length; i++) {
             for (int j = 0; j < i; j++) {
                 if (Math.abs(nums[i] - nums[j]) <= k) {
                     dp[i] = Math.max(dp[i], dp[j] + 1);
                 }
             }
         }

         int max = 0;
         for (int val : dp) max = Math.max(max, val);
         return max;
     }
     ```

---

### 6. **[Find the Maximum Length of a Good Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-i/description/)**
   - Problem: Determine the maximum length of a "good" subsequence based on given conditions.
   - **Recursion**:
     ```java
     public int maxLengthGoodSubsequenceI(int[] nums, int x) {
         return goodSubseqHelper(nums, 0, x);
     }

     private int goodSubseqHelper(int[] nums, int index, int x) {
         if (index == nums.length) return 0;

         int exclude = goodSubseqHelper(nums, index + 1, x);
         int include = 0;
         if (nums[index] >= x) {
             include = 1 + goodSubseqHelper(nums, index + 1, x);
         }

         return Math.max(include, exclude);
     }
     ```
   - **Memoization**:
     ```java
     public int maxLengthGoodSubsequenceI(int[] nums, int x) {
         int[][] memo = new int[nums.length][2];
         for (int[] row : memo) Arrays.fill(row, -1);
         return goodSubseqHelper(nums, 0, x, memo);
     }

     private int goodSubseqHelper(int[] nums, int index, int x, int[][] memo) {
         if (index == nums.length) return 0;
         if (memo[index][x] != -1) return memo[index][x];

         int exclude = goodSubseqHelper(nums, index + 1, x, memo);
         int include = 0;
         if (nums[index] >= x) {
             include = 1 + goodSubseqHelper(nums, index + 1, x, memo);
         }

         return memo[index][x] = Math.max(include, exclude);
     }
     ```
   - **Tabulation**:
     ```java
     public int maxLengthGoodSubsequenceI(int[] nums, int x) {
         int[] dp = new int[nums.length];
         Arrays.fill(dp, 1);

         for (int i = 1; i < nums.length; i++) {
             for (int j = 0; j < i; j++) {
                 if (nums[i] >= x) {
                     dp[i] = Math.max(dp[i], dp[j] + 1);
                 }
             }
         }

         int max = 0;
         for (int val : dp) max = Math.max(max, val);
         return max;
     }
     ```

---

### 7. **[Find the Maximum Length of a Good Subsequence II](https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-ii/description/)**
   - Problem: Similar to the above but may involve additional constraints or different definitions of "good".
   - **Recursion**:
     ```java
     public int maxLengthGoodSubsequenceII(int[] nums, int x) {
         return goodSubseqHelper(nums, 0, x);
     }

     private int goodSubseqHelper(int[] nums, int index, int x) {
         if (index == nums.length) return 0;

         int exclude = goodSubseqHelper(nums, index + 1, x);
         int include = 0;
         if (nums[index] >= x) {
             include = 1 + goodSubseqHelper(nums, index + 1, x);
         }

         return Math.max(include, exclude);
     }
     ```
   - **Memoization**:
     ```java
     public int maxLengthGoodSubsequenceII(int[] nums, int x) {
         int[][] memo = new int[nums.length][2];
         for (int[] row : memo) Arrays.fill(row, -1);
         return goodSubseqHelper(nums, 0, x, memo);
     }

     private int goodSubseqHelper(int[] nums, int index, int x, int[][] memo) {
         if (index == nums.length) return 0;
         if (memo[index][x] != -1) return memo[index][x];

         int exclude = goodSubseqHelper(nums, index + 1, x, memo);
         int include = 0;
         if (nums[index] >= x) {
             include = 1 + goodSubseqHelper(nums, index + 1, x, memo);
         }

         return memo[index][x] = Math.max(include, exclude);
     }
     ```
   - **Tabulation**:
     ```java
     public int maxLengthGoodSubsequenceII(int[] nums, int x) {
         int[] dp = new int[nums.length];
         Arrays.fill(dp, 1);

         for (int i = 1; i < nums.length; i++) {
             for (int j = 0; j < i; j++) {
                 if (nums[i] >= x) {
                     dp[i] = Math.max(dp[i], dp[j] + 1);
                 }
             }
         }

         int max = 0;
         for (int val : dp) max = Math.max(max, val);
         return max;
     }
     ```

---

### 8. **[Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/description/)**
   - Problem: Find the longest chain of pairs such that each pair's second value is less than the next pair's first value.
   - **Recursion**:
     ```java
     public int findLongestChain(int[][] pairs) {
         Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
         return pairChainHelper(pairs, -1, 0);
     }

     private int pairChainHelper(int[][] pairs, int prevIndex, int currIndex) {
         if (currIndex == pairs.length) return 0;

         int exclude = pairChainHelper(pairs, prevIndex, currIndex + 1);
         int include = 0;
         if (prevIndex == -1 || pairs[prevIndex][1] < pairs[currIndex][0]) {
             include = 1 + pairChainHelper(pairs, currIndex, currIndex + 1);
         }

         return Math.max(include, exclude);
     }
     ```
   - **Memoization**:
     ```java
     public int findLongestChain(int[][] pairs) {
         Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
         int[][] memo = new int[pairs.length][pairs.length];
         for (int[] row : memo) Arrays.fill(row, -1);
         return pairChainHelper(pairs, -1, 0, memo);
     }

     private int pairChainHelper(int[][] pairs, int prevIndex, int currIndex, int[][] memo) {
         if (currIndex == pairs.length) return 0;
         if (memo[prevIndex + 1][currIndex] != -1) return memo[prevIndex + 1][currIndex];

         int exclude = pairChainHelper(pairs, prevIndex, currIndex + 1, memo);
         int include = 0;
         if (prevIndex == -1 || pairs[prevIndex][1] < pairs[currIndex][0]) {
             include = 1 + pairChainHelper(pairs, currIndex, currIndex + 1, memo);
         }

         return memo[prevIndex + 1][currIndex] = Math.max(include, exclude);
     }
     ```
   - **Tabulation**:
     ```java
     public int findLongestChain(int[][] pairs) {
         Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
         int[] dp = new int[pairs.length];
         Arrays.fill(dp, 1);

         for (int i = 1; i < pairs.length; i++) {
             for (int j = 0; j < i; j++) {
                 if (pairs[j][1] < pairs[i][0]) {
                     dp[i] = Math.max(dp[i], dp[j] + 1);
                 }
             }
         }

         int max = 0;
         for (int val : dp) max = Math.max(max, val);
         return max;
     }
     ```

---

### 9. **[Increasing Triplet Subsequence](https://leetcode.com/problems/increasing-triplet-subsequence/description/)**
   - Problem: Check if there exists a triplet (i, j, k) such that `nums[i] < nums[j] < nums[k]`.
   - **Recursion**:
     ```java
     public boolean increasingTriplet(int[] nums) {
         return tripletHelper(nums, -1, 0, 0);
     }

     private boolean tripletHelper(int[] nums, int prevIndex, int currIndex, int count) {
         if (count == 3) return true;
         if (currIndex == nums.length) return false;

         boolean exclude = tripletHelper(nums, prevIndex, currIndex + 1, count);
         boolean include = false;
         if (prevIndex == -1 || nums[currIndex] > nums[prevIndex]) {
             include = tripletHelper(nums, currIndex, currIndex + 1, count + 1);
         }

         return include || exclude;
     }
     ```
   - **Memoization**:
     ```java
     public boolean increasingTriplet(int[] nums) {
         int[][][] memo = new int[nums.length][nums.length][4];
         for (int[][] row : memo)
             for (int[] col : row)
                 Arrays.fill(col, -1);
         return tripletHelper(nums, -1, 0, 0, memo);
     }

     private boolean tripletHelper(int[] nums, int prevIndex, int currIndex, int count, int[][][] memo) {
         if (count == 3) return true;
         if (currIndex == nums.length) return false;
         if (memo[prevIndex + 1][currIndex][count] != -1) return memo[prevIndex + 1][currIndex][count] == 1;

         boolean exclude = tripletHelper(nums, prevIndex, currIndex + 1, count, memo);
         boolean include = false;
         if (prevIndex == -1 || nums[currIndex] > nums[prevIndex]) {
             include = tripletHelper(nums, currIndex, currIndex + 1, count + 1, memo);
         }

         memo[prevIndex + 1][currIndex][count] = (include || exclude) ? 1 : 0;
         return include || exclude;
     }
     ```
   - **Tabulation**:
     ```java
     public boolean increasingTriplet(int[] nums) {
         int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;

         for (int num : nums) {
             if (num <= first) {
                 first = num;
             } else if (num <= second) {
                 second = num;
             } else {
                 return true;
             }
         }

         return false;
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




---
















## other 

1. **[Fibonacci Numbers](https://leetcode.com/problems/fibonacci-number/)**
   - **Recursion**:
     ```java
     public int fib(int n) {
         if (n <= 1) return n;
         return fib(n - 1) + fib(n - 2);
     }
     ```
   - **Memoization**:
     ```java
     public int fib(int n) {
         int[] memo = new int[n + 1];
         Arrays.fill(memo, -1);
         return fibHelper(n, memo);
     }

     private int fibHelper(int n, int[] memo) {
         if (n <= 1) return n;
         if (memo[n] != -1) return memo[n];
         return memo[n] = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
     }
     ```
   - **Tabulation**:
     ```java
     public int fib(int n) {
         if (n <= 1) return n;
         int[] dp = new int[n + 1];
         dp[0] = 0; dp[1] = 1;
         for (int i = 2; i <= n; i++) {
             dp[i] = dp[i - 1] + dp[i - 2];
         }
         return dp[n];
     }
     ```


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

### **8. [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)**

#### **Problem Statement**
Find the contiguous subarray within an array (containing at least one number) which has the largest product.


**Note** can be subsequence or sub-array for max/min sum/product elements or with length k  
---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java

private int maxProductRecursive(int[] nums, int index, int currentProduct) {
    if (index == nums.length) return currentProduct;
    return Math.max(maxProductRecursive(nums, index + 1, currentProduct * nums[index]),
                    maxProductRecursive(nums, index + 1, nums[index]));
}

public int maxProduct(int[] nums) {
    return maxProductRecursive(nums, 0, 1);
}


```
**Note**: Recursive solutions for this problem are not ideal due to complexity. Use DP-based solutions below.

---

##### 2. **Tabulation Solution**
```java
public int maxProduct(int[] nums) {
    int maxProduct = nums[0];
    int currMax = nums[0], currMin = nums[0];

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] < 0) {
            int temp = currMax;
            currMax = currMin;
            currMin = temp;
        }
        currMax = Math.max(nums[i], currMax * nums[i]);
        currMin = Math.min(nums[i], currMin * nums[i]);

        maxProduct = Math.max(maxProduct, currMax);
    }
    return maxProduct;
}
```

---

### **9. [Word Break](https://leetcode.com/problems/word-break/)**

#### **Problem Statement**
Given a string `s` and a dictionary of strings `wordDict`, determine if `s` can be segmented into a space-separated sequence of one or more dictionary words.

---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java
public boolean wordBreak(String s, List<String> wordDict) {
    return wordBreakRecursive(s, new HashSet<>(wordDict), 0);
}

private boolean wordBreakRecursive(String s, Set<String> wordDict, int start) {
    if (start == s.length()) return true;
    for (int end = start + 1; end <= s.length(); end++) {
        if (wordDict.contains(s.substring(start, end)) && wordBreakRecursive(s, wordDict, end)) {
            return true;
        }
    }
    return false;
}
```

##### 2. **Memoization Solution**
```java
public boolean wordBreak(String s, List<String> wordDict) {
    return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
}

private boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
    if (start == s.length()) return true;
    if (memo[start] != null) return memo[start];

    for (int end = start + 1; end <= s.length(); end++) {
        if (wordDict.contains(s.substring(start, end)) && wordBreakMemo(s, wordDict, end, memo)) {
            return memo[start] = true;
        }
    }
    return memo[start] = false;
}
```

##### 3. **Tabulation Solution**
```java
public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> wordSet = new HashSet<>(wordDict);
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;

    for (int i = 1; i <= s.length(); i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && wordSet.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[s.length()];
}
```

### **[Decode Ways](https://leetcode.com/problems/decode-ways/)**

#### **Problem Statement**
Given a string `s` containing only digits, determine the total number of ways to decode it, where:
- 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26".

---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java
public int numDecodings(String s) {
    return decodeRecursive(s, 0);
}

private int decodeRecursive(String s, int index) {
    if (index == s.length()) return 1;
    if (s.charAt(index) == '0') return 0;

    int ways = decodeRecursive(s, index + 1);
    if (index + 1 < s.length() && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
        ways += decodeRecursive(s, index + 2);
    }
    return ways;
}
```

##### 2. **Memoization Solution**
```java
public int numDecodings(String s) {
    int[] memo = new int[s.length()];
    Arrays.fill(memo, -1);
    return decodeMemo(s, 0, memo);
}

private int decodeMemo(String s, int index, int[] memo) {
    if (index == s.length()) return 1;
    if (s.charAt(index) == '0') return 0;
    if (memo[index] != -1) return memo[index];

    int ways = decodeMemo(s, index + 1, memo);
    if (index + 1 < s.length() && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
        ways += decodeMemo(s, index + 2, memo);
    }
    memo[index] = ways;
    return ways;
}
```

##### 3. **Tabulation Solution**
```java
public int numDecodings(String s) {
    int n = s.length();
    int[] dp = new int[n + 1];
    dp[0] = 1;

    for (int i = 1; i <= n; i++) {
        if (s.charAt(i - 1) != '0') {
            dp[i] += dp[i - 1];
        }
        if (i >= 2 && Integer.parseInt(s.substring(i - 2, i)) <= 26) {
            dp[i] += dp[i - 2];
        }
    }
    return dp[n];
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

### 1. Problem Solving Steps
1. **Identify Pattern**
   - What changes in each subproblem?
   - What information needs to be tracked?

2. **Define States**
   - What variables represent the state?
   - How many dimensions needed?

3. **Write Recurrence**
   - Base cases first
   - Transition function
   - Return value

4. **Optimize**
   - Can space be reduced?
   - Any unnecessary calculations?

### 2. Common Mistakes to Avoid
1. **Base Cases**
   - Missing edge cases
   - Incorrect initialization

2. **State Definition**
   - Too many state variables
   - Missing crucial information

3. **Transitions**
   - Incorrect recurrence relation
   - Missing some valid transitions

4. **Space Optimization**
   - Not considering when possible
   - Incorrect variable updates

## Must-Know Problems by Difficulty

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

## Space Optimization Techniques

### 1. 1D to Constant Space
- Keep only last 2-3 values
- Example: Fibonacci, House Robber

### 2. 2D to 1D Space
- Use rolling array
- Only need previous row/column

### 3. Memory vs Speed
- Consider problem constraints
- Sometimes trading space for time is worth it




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