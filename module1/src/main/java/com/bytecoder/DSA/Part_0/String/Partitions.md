

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


Here’s a complete **revision guide** + **LeetCode problem list** for the **String Partitioning Pattern** — useful for **FAANG-level interviews** and strong **recursion/DP practice**.

---

# 🧠 String Partitioning Pattern — Revision Notes

---

## ✅ What is the Pattern?

> **Breaking a string into multiple segments (substrings) based on some constraint** — like palindromes, dictionary words, unique parts, or balanced characters.

---

## 🎯 Core Techniques Used
| Technique        | Description |
|------------------|-------------|
| **Backtracking** | Try all partition points recursively and track valid paths |
| **Dynamic Programming (DP)** | Memorize optimal substructure like cuts or reachable indices |
| **Greedy** | For certain problems like balancing |
| **Bitmasking** | When uniqueness or character combinations matter |
| **Trie** (Rare) | For fast word lookups in word-break problems |

---

## 🧩 Key Sub-Patterns

| Sub-Pattern | Example Problem | Notes |
|-------------|------------------|-------|
| **Palindrome Partitions** | 131, 132 | Recursion + pre-check for palindrome |
| **Word Breaks** | 139, 140 | DP + DFS + memoization |
| **Balanced Partitions** | 1221 | Count-based greedy |
| **Unique Substrings** | 1593 | Backtracking + set |
| **Numeric Splits** | 842 | Backtracking, string to int |

---

## 🪜 General Backtracking Template

```python
def dfs(index, path):
    if index == len(s):
        result.append(path[:])
        return

    for i in range(index + 1, len(s)+1):
        prefix = s[index:i]
        if isValid(prefix):   # e.g., palindrome or in dictionary
            path.append(prefix)
            dfs(i, path)
            path.pop()
```

---

## 💡 Palindrome Precomputation for Optimization

```python
is_palindrome = [[False]*n for _ in range(n)]
for i in range(n-1, -1, -1):
    for j in range(i, n):
        if s[i] == s[j] and (j-i < 2 or is_palindrome[i+1][j-1]):
            is_palindrome[i][j] = True
```

---

# 🧾 LeetCode Problems — String Partitioning Pattern

| # | Problem | Difficulty | Pattern | Tags |
|---|---------|------------|---------|------|
| [131](https://leetcode.com/problems/palindrome-partitioning/) | Palindrome Partitioning | Medium | Backtracking | Palindrome DFS |
| [132](https://leetcode.com/problems/palindrome-partitioning-ii/) | Palindrome Partitioning II | Hard | DP | Min cuts |
| [139](https://leetcode.com/problems/word-break/) | Word Break I | Medium | DP | Dict match |
| [140](https://leetcode.com/problems/word-break-ii/) | Word Break II | Hard | DFS + Memo | All possible segmentations |
| [1593](https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/) | Split String into Max Unique Substrings | Medium | Backtracking | Set-based uniqueness |
| [842](https://leetcode.com/problems/split-array-into-fibonacci-sequence/) | Split Array into Fibonacci Sequence | Medium | Backtracking | Numeric split |
| [1221](https://leetcode.com/problems/split-a-string-in-balanced-strings/) | Split Balanced Strings | Easy | Greedy | Count L/R |
| [696](https://leetcode.com/problems/count-binary-substrings/) | Count Binary Substrings | Easy | Group Partition | Count pairs |
| [1078](https://leetcode.com/problems/occurrences-after-bigram/) | Occurrences After Bigram | Easy | Phrase Partition | Simple string scan |
| [1707](https://leetcode.com/problems/maximum-xor-with-an-element-from-array/) | Maximum XOR with Element | Hard | Split arrays & mask | Trie + Bitmask |

---

## 📌 Suggested Practice Order

1. ✅ 131. Palindrome Partitioning
2. ✅ 139. Word Break
3. ✅ 1593. Max Unique Substrings
4. ✅ 140. Word Break II
5. ✅ 132. Palindrome Partitioning II
6. ✅ 842. Fibonacci Split
7. ✅ 1221. Balanced String
8. ✅ 696. Binary Substrings

---

## ⚠️ Tips & Edge Cases

- Handle leading zeros for numeric splits (842)
- Use memoization to avoid TLE in Word Break II
- Precompute palindromes for O(n²) DP solutions
- Always track visited substrings when uniqueness is required (e.g., 1593)

