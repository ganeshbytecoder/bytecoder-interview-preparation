
# 📌 Sliding Window Pattern – Fixed & Variable Size

---

## 🔁 **Overview**

Sliding window is a powerful two-pointer technique used for:
- ✅ Finding **longest** or **shortest** substrings/subarrays with specific properties.
- ✅ Optimizing from brute force `O(n²)` to `O(n)`.

---

## **1️⃣ Variable Window Size (Dynamic Window)**
Used when window size is not fixed — it expands and shrinks based on conditions.

### 🔹 Core Use-Cases
| Problem Type | Approach |
|--------------|----------|
| Longest substring without repeating characters | HashSet |
| Longest substring with at most K distinct chars | HashMap |
| Minimum window containing all characters of pattern | Frequency Map + Count |
| Longest substring where char frequency meets condition | HashMap |

### 🔹 Problems & Patterns

#### ✅ Longest substring without repeating characters
[🔗 LeetCode 3](https://leetcode.com/problems/longest-substring-without-repeating-characters)
```python
def length_of_longest_substring(s: str) -> int:
    char_set = set()
    left = 0
    max_len = 0

    for right in range(len(s)):
        while s[right] in char_set:
            char_set.remove(s[left])
            left += 1
        char_set.add(s[right])
        max_len = max(max_len, right - left + 1)
    return max_len
```

#### ✅ Longest substring with at most K distinct characters
```python
def longest_substr_k_distinct(s, k):
    from collections import defaultdict
    window = defaultdict(int)
    left = max_len = 0

    for right in range(len(s)):
        window[s[right]] += 1
        while len(window) > k:
            window[s[left]] -= 1
            if window[s[left]] == 0:
                del window[s[left]]
            left += 1
        max_len = max(max_len, right - left + 1)
    return max_len
```

#### ✅ Minimum window substring
[🔗 LeetCode 76](https://leetcode.com/problems/minimum-window-substring/)
- Smallest window in `s` that contains all characters in `t`.

#### ✅ Longest Repeating Character Replacement
[🔗 LeetCode 424](https://leetcode.com/problems/longest-repeating-character-replacement/)
- Longest substring where you can replace ≤ k characters.

---

## **2️⃣ Fixed Window Size**
Use when you're given a strict window size (e.g., "window of size k").

### 🔹 Patterns
| Problem Type | Approach |
|--------------|----------|
| Max/Min sum of subarray size k | Prefix Sum / Sliding Window |
| Replace/rearrange ≤ k characters in window | Character Count |

### 🔹 Example: Maximize Confusion of Exam
[🔗 LeetCode 2024](https://leetcode.com/problems/maximize-the-confusion-of-an-exam/)
- Flip ≤ k answers to maximize same character window.

---

## **3️⃣ Prefix Sum + HashMap Extensions**
Great for problems involving **equality in counts** (e.g., equal 0s/1s, A/B).

#### ✅ Longest substring with equal 0s and 1s
```python
def longest_equal_0s_1s(s):
    prefix_sum = 0
    index_map = {0: -1}
    max_len = 0

    for i, c in enumerate(s):
        prefix_sum += 1 if c == '1' else -1
        if prefix_sum in index_map:
            max_len = max(max_len, i - index_map[prefix_sum])
        else:
            index_map[prefix_sum] = i
    return max_len
```

#### ✅ Count of subarrays with equal 0s and 1s
```python
def count_equal_0s_1s(arr):
    prefix = 0
    count_map = {0: 1}
    count = 0

    for val in arr:
        prefix += 1 if val == 1 else -1
        count += count_map.get(prefix, 0)
        count_map[prefix] = count_map.get(prefix, 0) + 1
    return count
```

---

## **4️⃣ Pattern-Specific Sliding Window Variants**

### 🧩 Longest substring without adjacent English alphabets
[🔗 GFG](https://www.geeksforgeeks.org/longest-substring-with-no-pair-of-adjacent-characters-are-adjacent-english-alphabets/)
```python
def longest_no_adjacent_alphabets(s):
    left = max_len = 0
    for right in range(len(s)):
        if right > 0 and abs(ord(s[right]) - ord(s[right - 1])) == 1:
            left = right
        max_len = max(max_len, right - left + 1)
    return max_len
```

---

## **5️⃣ Notable Problems (Mixed Sliding Window Variants)**

| Problem | Description | Type |
|--------|-------------|------|
| [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) | Expand Around Center | Variable |
| [Longest Duplicate Substring](https://leetcode.com/problems/longest-duplicate-substring/) | Rolling Hash / Binary Search | Binary + Sliding |
| [Longest String Chain](https://leetcode.com/problems/longest-string-chain/) | DP Based | N/A |
| [Find Longest Awesome Substring](https://leetcode.com/problems/find-longest-awesome-substring/) | Bitmask + Sliding | Bitmask |
| [Longest Happy String](https://leetcode.com/problems/longest-happy-string/) | Greedy + Priority Queue | Greedy |

---

## 📚 **Additional Resources**

- [GFG – Longest substring with k unique characters](https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/)
- [GFG – Length of longest substring without repeating characters](https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/)
- [GFG – Longest substring that can form palindrome](https://www.geeksforgeeks.org/longest-substring-whose-characters-can-be-rearranged-to-form-a-palindrome/)

