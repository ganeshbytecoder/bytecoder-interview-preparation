
---

## 🔠 Problem: Check if Two String Arrays Can Form the Same String by Reordering

You are given two arrays of strings, `word1` and `word2`. You can **reorder the elements** (substrings) in each array **independently**, but **you cannot change the order of characters within each string**.

Return `true` if there exists some permutation of `word1` and some permutation of `word2` such that, after concatenating the elements in each array, both arrays produce the **same final string**.

Otherwise, return `false`.

---

### 🔧 Constraints:
- `1 <= word1.length, word2.length <= 6`
- `1 <= word1[i].length, word2[i].length <= 10`
- `word1[i]` and `word2[i]` consist of lowercase English letters.

---

### ✅ Example 1:

```java
Input: word1 = ["ab", "c"], word2 = ["c", "ab"]
Output: true

Explanation:
Reordering word1 as ["c", "ab"] gives "cab".
Reordering word2 as ["c", "ab"] also gives "cab".
Both match, so return true.
```

---

### ❌ Example 2:

```java
Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: false

Explanation:
Possible permutations of word1: "abc", "cab"
word2 always forms "abc".
No common match, so return false.
```

---

### 💡 Approach:

1. Generate all permutations of `word1` and join their elements.
2. Do the same for `word2`.
3. If there exists any common joined string between the two sets, return `true`. Else, return `false`.

---

### 🔁 Java Code (Brute Force for Small Inputs):

```java
import java.util.*;

public class Solution {
    public boolean canFormSameString(String[] word1, String[] word2) {
        Set<String> perms1 = getAllJoinPermutations(word1);
        Set<String> perms2 = getAllJoinPermutations(word2);

        for (String s : perms1) {
            if (perms2.contains(s)) return true;
        }

        return false;
    }

    private Set<String> getAllJoinPermutations(String[] words) {
        Set<String> result = new HashSet<>();
        permute(words, 0, result);
        return result;
    }

    private void permute(String[] arr, int start, Set<String> result) {
        if (start == arr.length) {
            result.add(String.join("", arr));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr, i, start);
            permute(arr, start + 1, result);
            swap(arr, i, start);
        }
    }

    private void swap(String[] arr, int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
```

---

### ⏱️ Complexity:
- **Time Complexity:** `O(n! * k)` where `n` is the length of the array and `k` is the total number of characters in all strings.
- **Space Complexity:** `O(n!)` to store all permutations.

---


Here’s the clean, LeetCode-style **problem statement** for your variation, complete with examples, constraints, and solution approach:

---

## 🔠 Problem: Can Form Target String by Reordering and Combining Substrings

### 🧾 Description

You are given a list of strings `words` and a `target` string.

Your task is to determine whether you can form the `target` string by **reordering and concatenating some or all of the strings from `words`**, using **each string at most once**.

You may use **any subset** of `words`, and arrange them in **any order**, but you **must use each selected word as a whole** (i.e., you cannot split or rearrange characters inside the word).

---

### ✅ Constraints

- `1 <= words.length <= 10`
- `1 <= words[i].length <= 10`
- `1 <= target.length <= 100`
- All strings consist of lowercase English letters.

---

### 🧪 Examples

#### Example 1:
```java
Input: words = ["ab", "c", "abc"], target = "abc"
Output: true

Explanation: 
You can either use "abc" directly, or "ab" + "c" (in any order).
```

#### Example 2:
```java
Input: words = ["ab", "cd", "ef"], target = "abcdef"
Output: true

Explanation: 
Use "ab" + "cd" + "ef" in any order.
```

#### Example 3:
```java
Input: words = ["a", "abc", "bc"], target = "abcbc"
Output: false

Explanation: 
No combination of whole words can form the target "abcbc".
```

#### Example 4:
```java
Input: words = ["a", "bc", "b", "c"], target = "abc"
Output: true

Explanation: 
Use "a" + "bc", or "a" + "b" + "c".
```

---

### 💡 Approach

- Use **backtracking** to try all possible subsets and their permutations.
- At each recursive call, track which words have been used and the current concatenated string.
- Stop early if the current string is longer than the target or cannot match its prefix.

---

### 🔁 Java Code (Backtracking)

```java
import java.util.*;

public class Solution {
    public boolean canFormTarget(String[] words, String target) {
        return backtrack(words, target, new boolean[words.length], "");
    }

    private boolean backtrack(String[] words, String target, boolean[] used, String current) {
        if (current.length() > target.length()) return false;
        if (current.equals(target)) return true;

        for (int i = 0; i < words.length; i++) {
            if (!used[i]) {
                used[i] = true;
                if (backtrack(words, target, used, current + words[i])) return true;
                used[i] = false;
            }
        }

        return false;
    }
}
```

---

### ⏱️ Time Complexity

- **Worst Case:** `O(n! * k)`  
  Where `n = number of strings`, `k = total length of all strings`.
- Works well for small arrays (`n ≤ 8-10`).

---



## https://leetcode.com/problems/generate-parentheses/description/
if we allow multiple bracket type then how many and what will be output list ?