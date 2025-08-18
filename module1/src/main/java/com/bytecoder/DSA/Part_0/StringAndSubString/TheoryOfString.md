

# 📘 **String-Based Problems – Revision Notes**

---

## **1️⃣ String Comparison and Matching**
Useful when comparing, transforming, or validating strings.

### 🔹 Easy
- [Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings)
- [Valid Anagram](https://leetcode.com/problems/valid-anagram)
- [Buddy Strings](https://leetcode.com/problems/buddy-strings)
- [Increasing Decreasing String](https://leetcode.com/problems/increasing-decreasing-string)
- [Check if Word Equals Summation of Two Words](https://leetcode.com/problems/check-if-word-equals-summation-of-two-words)
- https://leetcode.com/problems/greatest-common-divisor-of-strings/?envType=company&envId=facebook&favoriteSlug=facebook-three-months
- [Check if One String Swap Can Make Strings Equal](https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal)

### 🔹 Medium
- [Additive Number](https://leetcode.com/problems/additive-number)
- [Check If a String Can Break Another String](https://leetcode.com/problems/check-if-a-string-can-break-another-string)
- [Determine If Two Strings Are Close](https://leetcode.com/problems/determine-if-two-strings-are-close)

### 🔹 Hard
- [Longest Happy Prefix](https://leetcode.com/problems/longest-happy-prefix)

---

## **2️⃣ String Sorting**
Focus on sorting characters by custom rules or frequency.

- [Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency) (Medium)
- [Custom Sort String](https://leetcode.com/problems/custom-sort-string) (Medium)

---

## **3️⃣ String Decoding / Encoding**
Manipulating encoded strings, like brackets, symbols, or custom patterns.

- [Decode String](https://leetcode.com/problems/decode-string/)
- [Roman Number to Integer](https://www.geeksforgeeks.org/roman-number-to-integer/)
- [Largest Number After Mutating Substring](https://leetcode.com/problems/largest-number-after-mutating-substring/description/)

---


## **7️⃣ Hashing + Frequency Counting**

| Problem Type                     | Approach                    |
|----------------------------------|-----------------------------|
| Anagram Check                    | HashMap (O(n))             |
| Group Anagrams                   | HashMap + Sorting (O(n log n)) |
| First Non-Repeating Character    | HashMap (O(n))             |

### 🔹 Example: Group Anagrams
```python
from collections import defaultdict
def group_anagrams(strs):
    anagrams = defaultdict(list)
    for s in strs:
        sorted_s = ''.join(sorted(s))
        anagrams[sorted_s].append(s)
    return list(anagrams.values())
```

---


## **6️⃣ Pattern Matching – KMP Algorithm**
Efficient substring search (O(n + m))

### 🔹 Example: First Occurrence
```python
def str_str(haystack: str, needle: str) -> int:
    if not needle: return 0
    lps = [0] * len(needle)
    j = 0
    for i in range(1, len(needle)):
        while j > 0 and needle[i] != needle[j]:
            j = lps[j - 1]
        if needle[i] == needle[j]: j += 1; lps[i] = j
    j = 0
    for i in range(len(haystack)):
        while j > 0 and haystack[i] != needle[j]:
            j = lps[j - 1]
        if haystack[i] == needle[j]: j += 1
        if j == len(needle): return i - j + 1
    return -1
```

✅ **Use When**: Fast substring pattern search.

---
