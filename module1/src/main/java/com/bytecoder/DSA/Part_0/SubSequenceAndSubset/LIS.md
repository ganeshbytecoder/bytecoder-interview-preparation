
##### 9. **[Increasing Triplet Subsequence](https://leetcode.com/problems/increasing-triplet-subsequence/description/)**
- Problem: Check if there exists a triplet (i, j, k) such that `nums[i] < nums[j] < nums[k]`.
```java
// 1. Recursion - Time: O(2^n), Space: O(n)
public int lengthOfLIS(int[] nums) {
    return lengthOfLISRecursive(nums, Integer.MIN_VALUE, 0);
}

private int lengthOfLISRecursive(int[] nums, int prev, int curr) {
    if (curr == nums.length) return 0;
    
    int include = 0;
    if (nums[curr] > prev) {
        include = 1 + lengthOfLISRecursive(nums, nums[curr], curr + 1);
    }
    int exclude = lengthOfLISRecursive(nums, prev, curr + 1);
    
    return Math.max(include, exclude);
}

// 2. Memoization - Time: O(n²), Space: O(n²)
public int lengthOfLIS(int[] nums) {
    int[][] memo = new int[nums.length + 1][nums.length];
    for (int[] row : memo) Arrays.fill(row, -1);
    return lengthOfLISMemo(nums, -1, 0, memo);
}

private int lengthOfLISMemo(int[] nums, int prevIndex, int curr, int[][] memo) {
    if (curr == nums.length) return 0;
    if (prevIndex != -1 && memo[prevIndex][curr] != -1) 
        return memo[prevIndex][curr];
    
    int include = 0;
    if (prevIndex == -1 || nums[curr] > nums[prevIndex]) {
        include = 1 + lengthOfLISMemo(nums, curr, curr + 1, memo);
    }
    int exclude = lengthOfLISMemo(nums, prevIndex, curr + 1, memo);
    
    if (prevIndex != -1) memo[prevIndex][curr] = Math.max(include, exclude);
    return Math.max(include, exclude);
}

// 3. Tabulation - Time: O(n²), Space: O(n)
public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int maxLen = 1;
    
    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxLen = Math.max(maxLen, dp[i]);
    }
    return maxLen;
}
```


## **6. Longest Increasing Subsequence [LC-300]**
### **Find longest increasing subsequence (LIS)**
- **Approach 1**: DP with `O(n^2)` time.

```java
public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int maxLen = 1;
    
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxLen = Math.max(maxLen, dp[i]);
    }
    return maxLen;
}
```


##### 9. **[Increasing Triplet Subsequence](https://leetcode.com/problems/increasing-triplet-subsequence/description/)**
- Problem: Check if there exists a triplet (i, j, k) such that `nums[i] < nums[j] < nums[k]`.
```java
// 1. Recursion - Time: O(2^n), Space: O(n)
public int lengthOfLIS(int[] nums) {
    return lengthOfLISRecursive(nums, Integer.MIN_VALUE, 0);
}

private int lengthOfLISRecursive(int[] nums, int prev, int curr) {
    if (curr == nums.length) return 0;
    
    int include = 0;
    if (nums[curr] > prev) {
        include = 1 + lengthOfLISRecursive(nums, nums[curr], curr + 1);
    }
    int exclude = lengthOfLISRecursive(nums, prev, curr + 1);
    
    return Math.max(include, exclude);
}

// 2. Memoization - Time: O(n²), Space: O(n²)
public int lengthOfLIS(int[] nums) {
    int[][] memo = new int[nums.length + 1][nums.length];
    for (int[] row : memo) Arrays.fill(row, -1);
    return lengthOfLISMemo(nums, -1, 0, memo);
}

private int lengthOfLISMemo(int[] nums, int prevIndex, int curr, int[][] memo) {
    if (curr == nums.length) return 0;
    if (prevIndex != -1 && memo[prevIndex][curr] != -1) 
        return memo[prevIndex][curr];
    
    int include = 0;
    if (prevIndex == -1 || nums[curr] > nums[prevIndex]) {
        include = 1 + lengthOfLISMemo(nums, curr, curr + 1, memo);
    }
    int exclude = lengthOfLISMemo(nums, prevIndex, curr + 1, memo);
    
    if (prevIndex != -1) memo[prevIndex][curr] = Math.max(include, exclude);
    return Math.max(include, exclude);
}

// 3. Tabulation - Time: O(n²), Space: O(n)
public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int maxLen = 1;
    
    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxLen = Math.max(maxLen, dp[i]);
    }
    return maxLen;
}
```



##### 1. **[Longest Increasing Subsequence II](https://leetcode.com/problems/longest-increasing-subsequence-ii/description/)**
- Problem: Find the length of the longest increasing subsequence within a limited difference range.

## **Longest Increasing Subsequence (LIS) Variants**
1. **[Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)** - Use DP with binary search (O(n log n)) or DP (O(n^2)).
2. **[Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/)** - DP with sorting, track the longest subset where `nums[j]` divides `nums[i]`.
3. **[Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/)** - Sort by width, apply LIS on height.
4. **[Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/)** - Sort pairs and apply LIS/Greedy.
5. **[Number of Longest Increasing Subsequences](https://leetcode.com/problems/number-of-longest-increasing-subsequence/)** - Use DP to count valid LIS sequences.
6. **[Delete and Earn](https://leetcode.com/problems/delete-and-earn/)** - Convert to a house-robber problem.
7. **[Longest String Chain](https://leetcode.com/problems/longest-string-chain/)** - Sort and use DP with a hashmap for longest chains.


- 🔹 **[718. Maximum Length of Repeated Subarray](https://leetcode.com/problems/maximum-length-of-repeated-subarray/) (Medium)**  
  Find the longest repeated subarray in two arrays.

---