### **Pattern-Based Approaches for Substring Problems**
When dealing with substring problems, we can broadly classify them into different categories based on what needs to be computed (length, count, etc.). Each category has an optimal approach. Below are **common patterns** along with **problem types** and their corresponding **approaches**.

---
---

## **📌 5. K Distinct Character Problems → Sliding Window + HashMap**
Use **Sliding Window + HashMap** when:
- You need substrings with **at most K distinct characters**.

### **Example 5: Longest Substring with At Most K Distinct Characters**
✅ **Problem**: Find the longest substring with at most `K` distinct characters.
```python
def longest_substr_k_distinct(s: str, k: int) -> int:
    char_map = {}
    left, max_length = 0, 0

    for right in range(len(s)):
        char_map[s[right]] = char_map.get(s[right], 0) + 1

        while len(char_map) > k:
            char_map[s[left]] -= 1
            if char_map[s[left]] == 0:
                del char_map[s[left]]
            left += 1

        max_length = max(max_length, right - left + 1)
    
    return max_length
```
📌 **Key Idea**: Maintain a frequency map of characters and shrink the window when more than `k` distinct characters are found.

---

## **🎯 Summary Table of Patterns**
| Problem Type | Pattern |
|-------------|----------|
| **Find longest/shortest substring** | Sliding Window |
| **Count number of substrings** | Inclusion-Exclusion with Two-Pointer |
| **Binary substring problems** | Prefix Sum + HashMap |
| **Palindrome problems** | Expand Around Center |
| **K distinct character problems** | Sliding Window + HashMap |


Yes! FANG interviews often ask string problems that fit into **specific patterns**. Here are more patterns beyond the ones I already shared:

---

# **📌 String Problem Patterns for Interviews (FANG)**

## **1️⃣ Sliding Window (Variable Length)**
Use when finding the **longest** or **shortest** substring with certain properties.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| Longest substring with at most K distinct characters | Sliding Window + HashMap (`O(n)`) |
| Longest substring without repeating characters | Sliding Window + HashSet (`O(n)`) |
| Shortest substring containing all characters of a pattern | Sliding Window + Frequency Map (`O(n)`) |

### **Example: Longest Substring Without Repeating Characters**
```python
def length_of_longest_substring(s: str) -> int:
    char_set = set()
    left = 0
    max_length = 0

    for right in range(len(s)):
        while s[right] in char_set:
            char_set.remove(s[left])
            left += 1
        char_set.add(s[right])
        max_length = max(max_length, right - left + 1)
    
    return max_length
```
✅ **Use When**: Finding the longest or shortest substring satisfying a condition.

---

## **2️⃣ Two-Pointer (Fixed Window)**
Use when comparing **two substrings** or **reversing** parts of a string.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| Check if a string is a palindrome | Two Pointers (`O(n)`) |
| Reverse words in a string | Two Pointers (`O(n)`) |
| Valid anagram (reordering of characters) | HashMap + Sorting (`O(n log n)`) |

### **Example: Valid Palindrome (Ignoring Non-Alphanumeric Characters)**
```python
import re
def is_palindrome(s: str) -> bool:
    s = re.sub(r'[^a-zA-Z0-9]', '', s).lower()
    left, right = 0, len(s) - 1
    while left < right:
        if s[left] != s[right]:
            return False
        left += 1
        right -= 1
    return True
```
✅ **Use When**: Comparing parts of a string from both ends.

---

## **3️⃣ Expand Around Center**
Use for problems involving **palindromes**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| Longest palindromic substring | Expand Around Center (`O(n^2)`) |
| Count palindromic substrings | Expand Around Center (`O(n^2)`) |

### **Example: Longest Palindromic Substring**
```python
def longest_palindrome(s: str) -> str:
    def expand(l, r):
        while l >= 0 and r < len(s) and s[l] == s[r]:
            l -= 1
            r += 1
        return s[l + 1:r]
    
    res = ""
    for i in range(len(s)):
        odd = expand(i, i)
        even = expand(i, i + 1)
        res = max(res, odd, even, key=len)
    
    return res
```
✅ **Use When**: Checking for palindromes or finding longest palindromic substrings.

---

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

## **5️⃣ Prefix Sum + HashMap**
Use when dealing with **binary strings** or **cumulative calculations**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| Count substrings with equal 0s and 1s | Prefix Sum + HashMap (`O(n)`) |
| Longest balanced substring (e.g., parentheses, binary) | Prefix Sum + HashMap (`O(n)`) |

### **Example: Count Binary Substrings with Equal 0s and 1s**
```python
def count_binary_substrings(s: str) -> int:
    prev, cur, count = 0, 1, 0

    for i in range(1, len(s)):
        if s[i] == s[i - 1]:
            cur += 1
        else:
            count += min(prev, cur)
            prev, cur = cur, 1

    return count + min(prev, cur)
```
✅ **Use When**: Finding substrings with equal frequency of characters.

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

# **✅ Final Checklist of String & Substring Patterns (with Time Complexity)**
| **Pattern** | **When to Use?** | **Time Complexity** |
|------------|----------------|------------------|
| **Sliding Window (Variable Length)** | Find longest/shortest substring with a condition | `O(n)` |
| **Two Pointers** | Comparing two parts of a string | `O(n)` |
| **Expand Around Center** | Find palindromic substrings | `O(n^2)` |
| **Hashing + Frequency Counting** | Find anagrams, frequency-based conditions | `O(n)` |
| **Prefix Sum + HashMap** | Binary substring problems | `O(n)` |
| **KMP (Knuth-Morris-Pratt)** | Fast substring search | `O(n + m)` |
| **Rabin-Karp (Rolling Hash)** | Fast substring search (multiple matches) | `O(n + m)` |
| **Trie (Prefix Tree)** | Word-based problems, autocomplete | `O(n * m)` |
| **Manacher’s Algorithm** | Find longest palindromic substring | `O(n)` |

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