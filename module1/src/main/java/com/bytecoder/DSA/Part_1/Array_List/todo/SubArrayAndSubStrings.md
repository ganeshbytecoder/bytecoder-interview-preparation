





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



---

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


### **📌 Sum-Related Conditions**
or mux sum subarray, does subarray have sum is equal k or more constraints
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
- what id it's array Find the longest contiguous substring where you can replace at most `k` characters.
- **Condition:** Subarray length maximization with `≤ k` changes.
- **Difficulty:** Medium

### **📌 XOR / Bitwise Conditions**
5. **[1371. Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/)**
   - Find the longest contiguous substring where each vowel appears an even number of times.
   - **Condition:** Subarray must satisfy an XOR-based condition.
   - **Difficulty:** Medium

---















