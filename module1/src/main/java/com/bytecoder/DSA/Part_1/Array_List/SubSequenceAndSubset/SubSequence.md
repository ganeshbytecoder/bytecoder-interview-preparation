
# **Subsets & Subsequences**

## **1. Understanding Subsets and Subsequences**

- **Subset**: A subset of a set includes any combination of its elements, including the empty set and the set itself. Order does not matter.
  - Example: `{1, 2, 3}` → `{}`, `{1}`, `{2}`, `{3}`, `{1,2}`, `{1,3}`, `{2,3}`, `{1,2,3}`
  - Total subsets of a set with `n` elements = `2^n` (including empty set)
  - when there are duplicate elements you should skip since duplicate subset is not valid

- **Subsequence**: A subsequence is a sequence derived from another sequence by deleting some or no elements without changing the order.
  - Example: "abc" → "a", "b", "c", "ab", "ac", "bc", "abc"
  - Length of subsequence = `2^n - 1` (excluding empty subsequence)

  
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


## **2. Subsequences (String/Array Based)**
- 🔹 **[392. Is Subsequence](https://leetcode.com/problems/is-subsequence/) (Easy)**  
  Check if a string is a subsequence of another.


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






