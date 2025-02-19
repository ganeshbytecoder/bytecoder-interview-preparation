# **Subsets & Subsequences**

## **1. Understanding Subsets and Subsequences**

- **Subset**: A subset of a set includes any combination of its elements, including the empty set and the set itself. Order does not matter.
    - Example: `{1, 2, 3}` → `{}`, `{1}`, `{2}`, `{3}`, `{1,2}`, `{1,3}`, `{2,3}`, `{1,2,3}`
    - Total subsets of a set with `n` elements = `2^n` (including empty set)
    - when there are duplicate elements you should skip since duplicate subset is not valid

- **Subsequence**: A subsequence is a sequence derived from another sequence by deleting some or no elements without changing the order.
    - Example: "abc" → "a", "b", "c", "ab", "ac", "bc", "abc"
    - Length of subsequence = `2^n - 1` (excluding empty subsequence)


---

## Subset Problems
#### **Concept**
- Order doesn't matter (continuous order not required).
- Variations:
    - **Distinct elements**
    - **With duplicates (Skip Duplicates)**
    - **With conditions (Fixed size, sum constraints, etc.)**
    - **if we have constraints of size they we call it combinations not sub set**  
---


### **1. Subsets (LC-78)**
- Generate all subsets.
- **Time:** O(2^n), **Space:** O(n)

#### **Method 1: Backtracking**
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
    list.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
    }
}
```

#### **Method 2: Recursion**
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    helper(nums, 0, new ArrayList<>(), ans);
    return ans;
}

private void helper(int[] nums, int index, List<Integer> temp, List<List<Integer>> ans) {
    if (index == nums.length) {
        ans.add(new ArrayList<>(temp));
        return;
    }
    helper(nums, index + 1, temp, ans);
    temp.add(nums[index]);
    helper(nums, index + 1, temp, ans);
    temp.remove(temp.size() - 1);
}
```
---

### **2. Subsets II (LC-90) [With Duplicates]**
- **Key:** Sort & Skip duplicates

#### **Method 1: Backtracking**
```java
public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
    list.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1]) continue; // Skip duplicates
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
    }
}
```

#### **Method 2: Recursion with Sorting**
```java
public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    helper(nums, 0, new ArrayList<>(), ans, false);
    return ans;
}

private void helper(int[] nums, int index, List<Integer> temp, List<List<Integer>> ans, boolean taken) {
    if (index == nums.length) {
        ans.add(new ArrayList<>(temp));
        return;
    }
    helper(nums, index + 1, temp, ans, false);
    if (index > 0 && nums[index] == nums[index - 1] && !taken) return; // Skip duplicates
    temp.add(nums[index]);
    helper(nums, index + 1, temp, ans, true);
    temp.remove(temp.size() - 1);
}
```
## **5. Bitmasking & Subsets**
- 🔹 **[1178. Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/) (Hard)**  
  Use bitmasking to find valid words in a given puzzle.

- 🔹 **[1986. Minimum Number of Work Sessions to Finish the Tasks](https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/) (Medium)**  
  Find the minimum number of sessions required to finish tasks using subsets.

---

## **6. Partitioning using Subsets**
- 🔹 **[416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) (Medium)**  
  Determine if an array can be partitioned into two subsets with equal sums.

- 🔹 **[698. Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/) (Medium)**  
  Check if an array can be divided into k subsets of equal sum.

- 🔹 **[805. Split Array With Same Average](https://leetcode.com/problems/split-array-with-same-average/) (Hard)**  
  Find if an array can be split into two subsets with the same average.

---








## **5. Subsequences**
A subsequence is derived from a string or an array by deleting some or no elements without changing the order of the remaining elements.
Yes! A subset is closely related to the concept of a combination in combinatorics.

## Subset Problems
#### **Concept**
- Order matter (continuous order required).
- Variations:
  - **Distinct elements**
  - **With duplicates (Skip Duplicates)**
  - **With conditions (Fixed size, sum constraints, etc.)**
---

### **Generate all Subsequences of a String**
- **Key Idea**: Either take the character or skip it (Recursion).
- **Time Complexity**: `O(2^n)`, **Space Complexity**: `O(n)`

```java
public void generateSubsequences(String s, int index, String current, List<String> result) {
    if (index == s.length()) {
        result.add(current);
        return;
    }
    
    // Exclude current character
    generateSubsequences(s, index + 1, current, result);
    
    // Include current character
    generateSubsequences(s, index + 1, current + s.charAt(index), result);
}

public void generateSubsequences(String s, int index, String current, List<String> result) {
  if (index == s.length()) {
    result.add(current);
    return;
  }

  for (int i = index ; i < s.length() ; i++) {
    generateSubsequences(s, i + 1, current + s.charAt(i), result);
  }
}

public List<String> getAllSubsequences(String s) {
    List<String> result = new ArrayList<>();
    generateSubsequences(s, 0, "", result);
    return result;
}
```












---
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


##### 1. **[Longest Increasing Subsequence II](https://leetcode.com/problems/longest-increasing-subsequence-ii/description/)**
- Problem: Find the length of the longest increasing subsequence within a limited difference range.
##### 2. **[Longest Ideal Subsequence](https://leetcode.com/problems/longest-ideal-subsequence/description/)**
- Problem: Find the longest ideal subsequence with a character difference limit.
##### 3. **[Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/)**
- Problem: Find the number of longest increasing subsequences.

##### 4. **[Find the Maximum Length of Valid Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/description/)**
- Problem: Find the maximum length of a valid subsequence from the input.

##### 6. **[Find the Maximum Length of a Good Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-i/description/)**
- Problem: Determine the maximum length of a "good" subsequence based on given conditions.


##### 8. **[Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/description/)**
- Problem: Find the longest chain of pairs such that each pair's second value is less than the next pair's first value.

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


## **2. Subsequences (String/Array Based)**
- 🔹 **[392. Is Subsequence](https://leetcode.com/problems/is-subsequence/) (Easy)**  
  Check if a string is a subsequence of another.

- 🔹 **[1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) (Medium)**  
  Find the length of the longest subsequence common to two strings.

- 🔹 **[1035. Uncrossed Lines](https://leetcode.com/problems/uncrossed-lines/) (Medium)**  
  Similar to LCS but applied to arrays.

---

## **4. Subarrays & Subsequence Problems**
- 🔹 **[300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) (Medium)**  
  Find the length of the longest increasing subsequence in an array.

- 🔹 **[673. Number of Longest Increasing Subsequences](https://leetcode.com/problems/number-of-longest-increasing-subsequences/) (Medium)**  
  Count how many longest increasing subsequences exist.

- 🔹 **[718. Maximum Length of Repeated Subarray](https://leetcode.com/problems/maximum-length-of-repeated-subarray/) (Medium)**  
  Find the longest repeated subarray in two arrays.

---

## **5. Bitmasking & Subsets**
- 🔹 **[1178. Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/) (Hard)**  
  Use bitmasking to find valid words in a given puzzle.

- 🔹 **[1986. Minimum Number of Work Sessions to Finish the Tasks](https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/) (Medium)**  
  Find the minimum number of sessions required to finish tasks using subsets.

---

## **6. Partitioning using Subsets**
- 🔹 **[416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) (Medium)**  
  Determine if an array can be partitioned into two subsets with equal sums.

- 🔹 **[698. Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/) (Medium)**  
  Check if an array can be divided into k subsets of equal sum.

- 🔹 **[805. Split Array With Same Average](https://leetcode.com/problems/split-array-with-same-average/) (Hard)**  
  Find if an array can be split into two subsets with the same average.

---


## **8. Binary String Subsequences**
- 🔹 **[187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/) (Medium)**  
  Find repeated DNA sequences (subsequences of length 10).

- 🔹 **[791. Custom Sort String](https://leetcode.com/problems/custom-sort-string/) (Medium)**  
  Sort characters of a string based on a custom order.

---


