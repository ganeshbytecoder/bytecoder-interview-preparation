# **Subsets & Subsequences**

## **1. Understanding Subsets and Subsequences**

- **Subset**: A subset of a set includes any combination of its elements, including the empty set and the set itself. Order does not matter.
    - Example: `{1, 2, 3}` → `{}`, `{1}`, `{2}`, `{3}`, `{1,2}`, `{1,3}`, `{2,3}`, `{1,2,3}`
    - Total subsets of a set with `n` elements = `2^n` (including empty set)

- **Subsequence**: A subsequence is a sequence derived from another sequence by deleting some or no elements without changing the order.
    - Example: "abc" → "a", "b", "c", "ab", "ac", "bc", "abc"
    - Length of subsequence = `2^n - 1` (excluding empty subsequence)


---
#### **Concept**
- Order doesn't matter (continuous order not required).
- Variations:
    - **Distinct elements**
    - **With duplicates (Skip Duplicates)**
    - **With conditions (Fixed size, sum constraints, etc.)**

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


## **5. Subsequences**
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

---
### **3. Combinations (LC-77)**
- Generate all possible **k-sized** combinations.

```java
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), 1, n, k);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> current, int start, int n, int k) {
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = start; i <= n; i++) {
        current.add(i);
        backtrack(result, current, i + 1, n, k);
        current.remove(current.size() - 1);
    }
}
```
---
### **4. Combination Sum (LC-39) [Unlimited Use]**
```java
public List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
    if (remain < 0) return;
    if (remain == 0) list.add(new ArrayList<>(tempList));
    else {
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i); // Allow reuse
            tempList.remove(tempList.size() - 1);
        }
    }
}
```
---
### **5. Combination Sum II (LC-40) [No Reuse]**
```java
public List<List<Integer>> combinationSum2(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
    if (remain < 0) return;
    if (remain == 0) list.add(new ArrayList<>(tempList));
    else {
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // Skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
```
---
### **6. Combination Sum III (LC-216)**
- **Find all k-sized numbers summing to n.**

```java
public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), k, n, 1);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> tempList, int k, int remain, int start) {
    if (tempList.size() == k && remain == 0) {
        result.add(new ArrayList<>(tempList));
        return;
    }
    for (int i = start; i <= 9; i++) {
        tempList.add(i);
        backtrack(result, tempList, k, remain - i, i + 1);
        tempList.remove(tempList.size() - 1);
    }
}
```



#### 1.2 Permutations
1. **Permutations** [LC-46]
2. **Permutations II** [LC-47]
```java
public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
    if(tempList.size() == nums.length){
        list.add(new ArrayList<>(tempList));
    } else{
        for(int i = 0; i < nums.length; i++){
            if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])) continue;
            used[i] = true; 
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, used);
            used[i] = false; 
            tempList.remove(tempList.size() - 1);
        }
    }
}
```

method-2

```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    public void solve(int [] nums, List<Integer> list, int index, boolean[] used){

        if(nums.length == index){
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0 ; i < nums.length ; i++){
            if(used[i] ) continue;
            used[i] = true; 
            list.add(nums[i]);
            solve(nums, list, index+1, used);
            list.remove(list.size()-1);
            used[i] = false; 
        }

    }



    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> list = new ArrayList<>();
        boolean [] used = new boolean[nums.length];
        Arrays.sort(nums);
        solve(nums, list, 0, used);
        List<List<Integer>> result = ans.stream().distinct().collect(Collectors.toList());
        return result;
    }
}
```

## 4. Subsequence Problems

#### 4.1 Longest Increasing Subsequence (LIS)

##### 1. **[Longest Increasing Subsequence II](https://leetcode.com/problems/longest-increasing-subsequence-ii/description/)**
- Problem: Find the length of the longest increasing subsequence within a limited difference range.
##### 2. **[Longest Ideal Subsequence](https://leetcode.com/problems/longest-ideal-subsequence/description/)**
- Problem: Find the longest ideal subsequence with a character difference limit.
##### 3. **[Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/)**
- Problem: Find the number of longest increasing subsequences.

##### 4. **[Find the Maximum Length of Valid Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/description/)**
- Problem: Find the maximum length of a valid subsequence from the input.


##### 5. **[Find the Maximum Length of Valid Subsequence II](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/description/)**
- Problem: Find the maximum length of a valid subsequence based on specific conditions.

##### 6. **[Find the Maximum Length of a Good Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-i/description/)**
- Problem: Determine the maximum length of a "good" subsequence based on given conditions.

##### 7. **[Find the Maximum Length of a Good Subsequence II](https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-ii/description/)**
- Problem: Similar to the above but may involve additional constraints or different definitions of "good".

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



## **1. Basic Subset Generation**
- 🔹 **[78. Subsets](https://leetcode.com/problems/subsets/) (Medium)**  
  Generate all possible subsets of a given array.

- 🔹 **[90. Subsets II](https://leetcode.com/problems/subsets-ii/) (Medium)**  
  Generate all unique subsets when the array contains duplicates.

---

## **2. Subsequences (String/Array Based)**
- 🔹 **[392. Is Subsequence](https://leetcode.com/problems/is-subsequence/) (Easy)**  
  Check if a string is a subsequence of another.

- 🔹 **[1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) (Medium)**  
  Find the length of the longest subsequence common to two strings.

- 🔹 **[1035. Uncrossed Lines](https://leetcode.com/problems/uncrossed-lines/) (Medium)**  
  Similar to LCS but applied to arrays.

---

## **3. Combination/Subset Sum Problems**
- 🔹 **[39. Combination Sum](https://leetcode.com/problems/combination-sum/) (Medium)**  
  Find all unique ways to sum up to a target using elements (repetition allowed).

- 🔹 **[40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/) (Medium)**  
  Similar to Combination Sum, but elements are unique (no repetition).

- 🔹 **[216. Combination Sum III](https://leetcode.com/problems/combination-sum-iii/) (Medium)**  
  Find k numbers that sum to a target from 1-9.

- 🔹 **[377. Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/) (Medium)**  
  Find the total number of ways to form a target sum.

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

## **7. Power Set & Permutations (Backtracking)**
- 🔹 **[46. Permutations](https://leetcode.com/problems/permutations/) (Medium)**  
  Generate all permutations of an array.

- 🔹 **[47. Permutations II](https://leetcode.com/problems/permutations-ii/) (Medium)**  
  Generate all unique permutations of an array with duplicate elements.

- 🔹 **[267. Palindrome Permutation II](https://leetcode.com/problems/palindrome-permutation-ii/) (Medium)**  
  Find all palindromic permutations of a string.

---

## **8. Binary String Subsequences**
- 🔹 **[187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/) (Medium)**  
  Find repeated DNA sequences (subsequences of length 10).

- 🔹 **[791. Custom Sort String](https://leetcode.com/problems/custom-sort-string/) (Medium)**  
  Sort characters of a string based on a custom order.

---


