
#### 1 SubArray and subStrings
- continuous order
    - for distinct
    - for duplicate elements
    - with condition
```java
    public void backtrack(int[] nums, int start, List<Integer> tempList, List<List<Integer>> result) {
        if (!tempList.isEmpty()) {
            result.add(new ArrayList<>(tempList)); // Add only non-empty subarrays
        }
    
        // ✅ Base case to prevent out-of-bounds error
        if (start >= nums.length) {
            return;
        }
    
        // Add current element
        tempList.add(nums[start]);
    
        // Continue to expand the subarray
        backtrack(nums, start + 1, tempList, result);
    
        // ✅ Backtrack step: remove the last element added
        tempList.remove(tempList.size() - 1);
    }
    
    public List<List<Integer>> findAllSubarrays(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            backtrack(nums, i, new ArrayList<>(), result);
        }
        return result;
    }

```
m2

```java

   public void backtrack(int[] nums, int start, List<Integer> tempList, List<List<Integer>> result, Set<String> seen) {
        if (!tempList.isEmpty()) {  // Only add non-empty subarrays
            String key = tempList.toString(); // Convert list to string for uniqueness check
            if (!seen.contains(key)) {
                result.add(new ArrayList<>(tempList));
                seen.add(key);
            }
        }
        
        for (int i = start==-1 ?0 : start ; i < nums.length; i++) {
            tempList.add(nums[i]);  // Include current element
            backtrack(nums, i + 1, tempList, result, seen); // Move forward (ensuring contiguity)
            tempList.remove(tempList.size() - 1);  // Backtrack
        }
    }

    public List<List<Integer>> findAllSubarrays(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<String> seen = new HashSet<>(); // Avoid duplicate subarrays
        backtrack(nums, -1, new ArrayList<>(), result, seen);
        
        return result;
    }
```

m3
```java
import java.util.*;

public class SubarraysUsingLoops {
    public static List<List<Integer>> findAllSubarrays(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Generate all possible subarrays
        for (int start = 0; start < nums.length; start++) { // Start index
            List<Integer> tempList = new ArrayList<>();
            for (int end = start; end < nums.length; end++) { // End index
                tempList.add(nums[end]); // Expand subarray
                result.add(new ArrayList<>(tempList)); // Store subarray
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subarrays = findAllSubarrays(nums);
        System.out.println(subarrays);
    }
}

```





```java
import java.util.*;

public class SubstringWithDuplicates {
    public void backtrack(String s, int start, StringBuilder temp, List<String> result) {
        if (temp.length() > 0) {  // Only add non-empty substrings
            result.add(temp.toString());
        }

        // Base case: Prevent out-of-bounds access
        if (start >= s.length()) return;

        // Expand substring by including contiguous characters
        temp.append(s.charAt(start));
        backtrack(s, start + 1, temp, result);
        temp.deleteCharAt(temp.length() - 1);  // Backtrack
    }

    public List<String> findAllSubstrings(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) { // Start from each index
            backtrack(s, i, new StringBuilder(), result);
        }
        return result;
    }

    public static void main(String[] args) {
        SubstringWithDuplicates obj = new SubstringWithDuplicates();
        System.out.println(obj.findAllSubstrings("abc"));
    }
}


```

* Method 2 solution only for sub-strings
```java
    public List<String> substrings(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                result.add(s.substring(i, j));
            }
        }
        
        return result;
    }
```

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

Here are some **LeetCode problems** on **Subarrays** and **Substrings**, categorized by different conditions:

---

## 🔹 **Subarray Problems**
(An array slice that maintains order and contiguity)

### **📌 Sum-Related Conditions**
1. **[560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)**
    - Given an integer array `nums` and an integer `k`, find the number of contiguous subarrays that sum to `k`.
    - **Condition:** Sum of elements must be exactly `k`.
    - **Difficulty:** Medium

2. **[1248. Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/)**
    - Find the number of subarrays containing exactly `k` odd numbers.
    - **Condition:** Count specific elements in subarray (odd numbers).
    - **Difficulty:** Medium

3. **[974. Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/)**
    - Find the number of subarrays whose sum is divisible by `k`.
    - **Condition:** Sum of elements must be divisible by `k`.
    - **Difficulty:** Medium

4. **[209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)**
    - Find the smallest subarray whose sum is at least `target`.
    - **Condition:** Sum must be `>= target`, and subarray should be smallest.
    - **Difficulty:** Medium

---

### **📌 XOR / Bitwise Conditions**
5. **[1371. Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/)**
    - Find the longest contiguous substring where each vowel appears an even number of times.
    - **Condition:** Subarray must satisfy an XOR-based condition.
    - **Difficulty:** Medium

6. **[1442. Count Triplets That Can Form Two Equal XORs](https://leetcode.com/problems/count-triplets-that-can-form-two-equal-xors/)**
    - Find the number of triplets `(i, j, k)` such that `XOR[i:j] == XOR[j:k]`.
    - **Condition:** XOR of subarrays must be equal.
    - **Difficulty:** Medium

---

### **📌 Maximum & Minimum Conditions**
7. **[152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)**
    - Find the contiguous subarray that has the largest product.
    - **Condition:** Subarray product must be maximized.
    - **Difficulty:** Medium

8. **[53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)**
    - Find the contiguous subarray with the largest sum.
    - **Condition:** Subarray sum must be maximized.
    - **Difficulty:** Easy

---

### **📌 K-Distance or Sliding Window Conditions**
9. **[1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/)**
    - Find the longest contiguous subarray of `1s` after flipping at most `k` zeros.
    - **Condition:** Subarray length maximization while maintaining a condition.
    - **Difficulty:** Medium

10. **[424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)**
- Find the longest contiguous substring where you can replace at most `k` characters.
- **Condition:** Subarray length maximization with `≤ k` changes.
- **Difficulty:** Medium

---

## 🔹 **Substring Problems**
(A contiguous sequence of characters in a string)

### **📌 Palindrome & Reverse Conditions**
11. **[5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)**
- Find the longest contiguous palindromic substring.
- **Condition:** Substring must be a palindrome.
- **Difficulty:** Medium

12. **[647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)**
- Count all substrings in a string that are palindromic.
- **Condition:** Count substrings meeting the palindrome condition.
- **Difficulty:** Medium

---

### **📌 K Distinct or Unique Character Conditions**
13. **[340. Longest Substring with At Most K Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/)**
- Find the longest contiguous substring with at most `k` distinct characters.
- **Condition:** Substring must contain ≤ `k` unique characters.
- **Difficulty:** Medium

14. **[159. Longest Substring with At Most Two Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/)**
- Same as above, but with **exactly two** distinct characters.
- **Condition:** Substring must contain exactly `2` unique characters.
- **Difficulty:** Medium

15. **[3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)**
- Find the longest contiguous substring where no character repeats.
- **Condition:** Substring must have all unique characters.
- **Difficulty:** Medium

---

### **📌 Sliding Window & Frequency Conditions**
16. **[76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)**
- Find the smallest substring containing all characters of `t`.
- **Condition:** Substring must contain all of `t`'s characters at least once.
- **Difficulty:** Hard

17. **[567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)**
- Check if some substring is a permutation of `s2`.
- **Condition:** Substring must be a permutation of another string.
- **Difficulty:** Medium

18. **[30. Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)**
- Find all starting indices where a substring is a concatenation of all words in a given list.
- **Condition:** Substring must be a concatenation of a word list.
- **Difficulty:** Hard

---

### **🔥 Bonus: Hard Problems**
19. **[727. Minimum Window Subsequence](https://leetcode.com/problems/minimum-window-subsequence/)**
- Find the minimum contiguous substring in `s` where `t` appears as a subsequence.
- **Condition:** Substring must contain `t` as a subsequence.
- **Difficulty:** Hard

20. **[1062. Longest Repeating Substring](https://leetcode.com/problems/longest-repeating-substring/)**
- Find the longest contiguous substring that appears at least twice.
- **Condition:** Substring must be **repeating** at least twice.
- **Difficulty:** Hard

---


