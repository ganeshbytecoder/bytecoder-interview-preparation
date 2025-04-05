### **Pattern-Based Approaches for Substring Problems**
When dealing with substring problems, we can broadly classify them into different categories based on what needs to be computed (length, count, etc.). Each category has an optimal approach. Below are **common patterns** along with **problem types** and their corresponding **approaches**.

---
---

## **📌 5. K Distinct Character Problems → Sliding Window + HashMap**
Use **Sliding Window + HashMap** when:

# **📌 String Problem Patterns for Interviews (FANG)**


## **4️⃣ Hashing + Frequency Counting**
Use for problems requiring **pattern matching** or **frequency-based conditions**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| Check if two strings are anagrams | HashMap (`O(n)`) |
| Group anagrams | HashMap + Sorting (`O(n log n)`) |
| Find first non-repeating character | HashMap (`O(n)`) |

### **Example: Group Anagrams**
```python
from collections import defaultdict
def group_anagrams(strs):
    anagrams = defaultdict(list)
    for s in strs:
        sorted_s = ''.join(sorted(s))
        anagrams[sorted_s].append(s)
    return list(anagrams.values())
```
✅ **Use When**: Counting character frequencies or grouping words with the same character set.

---

## **7️⃣ Trie (Prefix Tree)**
Use for problems related to **prefix matching**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| Find longest common prefix | Trie (`O(n * m)`) |
| Auto-complete system | Trie (`O(n * m)`) |
| Word search in a dictionary | Trie + Backtracking (`O(n * m)`) |

### **Example: Implement Trie**
```python
class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str):
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]
        node.is_end = True

    def search(self, word: str) -> bool:
        node = self.root
        for c in word:
            if c not in node.children:
                return False
            node = node.children[c]
        return node.is_end
```
✅ **Use When**: Searching words in a dictionary or implementing autocomplete.


---


## **6️⃣ KMP Algorithm (Pattern Matching)**
Use when searching for a **substring within another string** efficiently.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| Find the first occurrence of a substring | KMP Algorithm (`O(n)`) |
| Check if a string is a rotation of another | KMP Algorithm (`O(n)`) |

### **Example: Find First Occurrence of a Substring**
```python
def str_str(haystack: str, needle: str) -> int:
    if not needle:
        return 0
    lps = [0] * len(needle)  # Longest Prefix Suffix array
    j = 0  

    # Preprocess LPS array
    for i in range(1, len(needle)):
        while j > 0 and needle[i] != needle[j]:
            j = lps[j - 1]
        if needle[i] == needle[j]:
            j += 1
            lps[i] = j

    j = 0
    for i in range(len(haystack)):
        while j > 0 and haystack[i] != needle[j]:
            j = lps[j - 1]
        if haystack[i] == needle[j]:
            j += 1
        if j == len(needle):
            return i - j + 1

    return -1
```
✅ **Use When**: Searching for a pattern in a string efficiently.

---






https://leetcode.com/problems/is-subsequence/description/


**Problem**: Check if a string is substring of another
```
Input: S1 = “for”, S2 = “geeksforgeeks”
Output: 5
Explanation: String “for” is present as a substring of s2.


Input: S1 = “practice”, S2 = “geeksforgeeks”
Output: -1.
Explanation: There is no occurrence of “practice” in “geeksforgeeks”
```


[//]: # (get 0.012012012012012012)
```python

def find_smallest_repeating_substring(s):
    n = len(s)
    for k in range(1, n + 1):
        if n % k == 0:  # Check if k is a divisor of n
            substring = s[:k]
            if substring * (n // k) == s:  # Check if it forms the original string
                return substring
    return s  # If no repeating pattern found, return the original string

# Test cases
print(find_smallest_repeating_substring("ABCABCABCABC"))  # Output: "ABC"
print(find_smallest_repeating_substring("ABCDABCDABCD"))  # Output: "ABCD"
print(find_smallest_repeating_substring("AAAAAA"))        # Output: "A"
print(find_smallest_repeating_substring("ABABABAB"))      # Output: "AB"
print(find_smallest_repeating_substring("ABCDE"))         # Output: "ABCDE"

```



https://leetcode.com/problems/largest-number-after-mutating-substring/description/ 

## **8. Binary String Subsequences**
- 🔹 **[187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/) (Medium)**  
  Find repeated DNA sequences (subsequences of length 10).

- 🔹 **[791. Custom Sort String](https://leetcode.com/problems/custom-sort-string/) (Medium)**  
  Sort characters of a string based on a custom order.

---




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
//    https://leetcode.com/problems/repeated-dna-sequences/description/
//    https://www.geeksforgeeks.org/given-two-strings-find-first-string-subsequence-second/
//    https://www.geeksforgeeks.org/check-string-substring-another/?ref=ml_lbp




Parenthesis problem:-

1.https://leetcode.com/problems/generate-parentheses Medium
2.https://leetcode.com/problems/score-of-parentheses Medium
5.https://leetcode.com/problems/remove-outermost-parentheses Easy
6.https://leetcode.com/problems/different-ways-to-add-parentheses/ Medium
8.https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses Medium
9.https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses Easy
10.https://leetcode.com/problems/longest-valid-parentheses/ Hard
7.https://leetcode.com/problems/remove-invalid-parentheses Hard


Counting of substring based on some condition:-


1.https://leetcode.com/problems/number-of-wonderful-substrings Medium
2.https://leetcode.com/problems/sum-of-beauty-of-all-substrings/ Medium
3.https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring Medium
4.https://leetcode.com/problems/number-of-wonderful-substrings Medium


Check types of string:-

1.https://leetcode.com/problems/isomorphic-strings Easy
2.https://leetcode.com/problems/valid-anagram Easy
3. https://leetcode.com/problems/additive-number Medium
4.https://leetcode.com/problems/buddy-strings Easy
5.https://leetcode.com/problems/longest-happy-prefix Hard
6.https://leetcode.com/problems/increasing-decreasing-string Easy
7.https://leetcode.com/problems/check-if-a-string-can-break-another-string Medium
8.https://leetcode.com/problems/determine-if-two-strings-are-close Medium 
9.https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent Easy
10.https://leetcode.com/problems/check-if-word-equals-summation-of-two-words Easy
11.https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal Easy


Palindromic string:-

1.https://leetcode.com/problems/palindrome-partitioning Medium
2.https://leetcode.com/problems/palindrome-partitioning-ii Hard
3.https://leetcode.com/problems/valid-palindrome Easy
4.https://leetcode.com/problems/shortest-palindrome Hard
5.https://leetcode.com/problems/palindrome-pairs Hard
6.https://leetcode.com/problems/longest-palindrome Easy
7.https://leetcode.com/problems/longest-palindromic-subsequence Medium
8.https://leetcode.com/problems/find-the-closest-palindrome Hard
9.https://leetcode.com/problems/palindromic-substrings Medium
10.https://leetcode.com/problems/valid-palindrome-ii Easy
11.https://leetcode.com/problems/longest-chunked-palindrome-decomposition Hard 
12.https://leetcode.com/problems/break-a-palindrome Medium
13. https://leetcode.com/problems/can-make-palindrome-from-substring Medium
14.https://leetcode.com/problems/palindrome-partitioning-iii Hard
15.https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome Hard
16.https://leetcode.com/problems/remove-palindromic-subsequences Easy
16.https://leetcode.com/problems/construct-k-palindrome-strings Medium
17.https://leetcode.com/problems/split-two-strings-to-make-palindrome Medium


Sorting on String:-
1.https://leetcode.com/problems/sort-characters-by-frequency Medium
2.https://leetcode.com/problems/custom-sort-string


Longest and shortest kind of String Problem :-


https://leetcode.com/problems/longest-duplicate-substring Hard
2.https://leetcode.com/problems/longest-string-chain Medium
3.https://leetcode.com/problems/longest-common-subsequence Medium
4.https://leetcode.com/problems/longest-happy-string Medium
5.https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters Medium
6.https://leetcode.com/problems/find-longest-awesome-substring Hard
7.https://leetcode.com/problems/largest-substring-between-two-equal-characters Easy
8.https://leetcode.com/problems/largest-odd-number-in-string Easy

get all possible substrings 

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

https://leetcode.com/problems/longest-palindromic-substring/description/



Basics:
* https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/?envType=problem-list-v2&envId=recursion&difficulty=EASY


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



https://leetcode.com/problems/decode-string/description/

https://www.geeksforgeeks.org/roman-number-to-integer/


https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
https://leetcode.com/problems/longest-palindromic-subsequence/



