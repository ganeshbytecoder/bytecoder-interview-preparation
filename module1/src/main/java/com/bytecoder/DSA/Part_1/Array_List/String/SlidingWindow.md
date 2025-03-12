
## **📌 1. Longest or Shortest Substring Problems → Sliding Window**
Use **sliding window** (two-pointer technique) when you need to find:
- The **longest** substring with certain properties.
- The **shortest** substring with certain properties.
  https://leetcode.com/problems/longest-substring-of-all-vowels-in-order/description/

### **Examples & Approach**
| Problem Type | Approach |
|-------------|----------|
| **Longest substring with at most K distinct characters** | Sliding Window with HashMap (`O(n)`) |
| **Longest substring without repeating characters** | Sliding Window with HashSet (`O(n)`) |
| **Shortest substring containing all characters of a given pattern** | Sliding Window with Frequency Map (`O(n)`) |
| **Longest substring with equal 0s and 1s** | Prefix Sum + HashMap (`O(n)`) |

### **Example 1: Longest Substring Without Repeating Characters**
✅ **Problem**: Find the longest substring where no character repeats.
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
📌 **Key Idea**: Expand the window until a duplicate appears, then shrink from the left.

---

```python

def shortest_substring_with_pattern(s, p):
    from collections import Counter

    # Frequency of characters in pattern
    pattern_count = Counter(p)
    window_count = Counter()
    
    left = 0
    min_length = float('inf')
    min_substring = ""
    matched = 0

    for right in range(len(s)):
        char = s[right]
        window_count[char] += 1
        
        # If char is part of the pattern and its count matches required
        if char in pattern_count and window_count[char] == pattern_count[char]:
            matched += 1
        
        # Try to shrink the window while it still contains all characters
        while matched == len(pattern_count):
            # Update the minimum substring
            if right - left + 1 < min_length:
                min_length = right - left + 1
                min_substring = s[left:right+1]

            # Remove leftmost character
            left_char = s[left]
            window_count[left_char] -= 1
            if left_char in pattern_count and window_count[left_char] < pattern_count[left_char]:
                matched -= 1  # Reduce matched count

            left += 1  # Shrink window

    return min_substring if min_length != float('inf') else ""

# Example Usage
s = "ADOBECODEBANC"
p = "ABC"
result = shortest_substring_with_pattern(s, p)
print("Shortest substring containing all characters of pattern:", result)

```


```python 

def longest_equal_0s_1s_substring(s):
    prefix_sum = 0
    sum_index_map = {0: -1}  # Initialize with sum 0 at index -1
    max_length = 0
    start_idx = 0  # To track the start index of the longest substring

    for i, char in enumerate(s):
        # Convert '0' to -1, '1' remains 1
        prefix_sum += 1 if char == '1' else -1

        if prefix_sum in sum_index_map:
            length = i - sum_index_map[prefix_sum]
            if length > max_length:
                max_length = length
                start_idx = sum_index_map[prefix_sum] + 1
        else:
            sum_index_map[prefix_sum] = i

    return s[start_idx:start_idx + max_length], max_length

# Example Usage
s = "110101110"
result, length = longest_equal_0s_1s_substring(s)
print("Longest substring with equal 0s and 1s:", result)
print("Length:", length)


```

### **Explanation of the Approach**
The problem requires finding the **longest contiguous substring** where the number of `0`s and `1`s are equal.

#### **Key Idea: Convert `0` to `-1`**
- If we replace each `0` with `-1`, then the problem reduces to finding the **longest subarray with a sum of `0`**.

#### **Example Breakdown**
Let's take the example:
```plaintext
s = "110101110"
```
1. Convert `0`s to `-1`:
   ```
   110101110 → [1, 1, -1, 1, -1, 1, 1, 1, 0]
   ```
2. Compute the **prefix sum** at each index:
   ```
   Index:      0   1   2   3   4   5   6   7   8
   Binary:     1   1  -1   1  -1   1   1   1   0
   Prefix Sum: 1   2   1   2   1   2   3   4   4
   ```
3. **Tracking First Occurrences:**
    - Store the first occurrence of each prefix sum.
    - If the same sum appears again, it means the subarray in between has **sum = 0**, meaning equal `0`s and `1`s.

4. **Finding the Longest Substring**
    - The longest subarray where sum repeats is **between indices (1, 5) → "1010"**.
    - This means `"1010"` is the **longest contiguous substring with equal `0`s and `1`s**.

---

### **3. Count Subarrays with Equal `0`s and `1`s**
**Problem:** Count the number of **subarrays** where the number of `0`s and `1`s is equal.  
**Approach:**
- Convert `0` to `-1` and use **prefix sum**.
- If the same prefix sum appears again, the subarray between them is valid.

   ```python
   def count_equal_0s_1s_subarrays(nums):
       prefix_sum = 0
       sum_freq = {0: 1}  # Initialize with sum 0
       count = 0

       for num in nums:
           prefix_sum += 1 if num == 1 else -1
           count += sum_freq.get(prefix_sum, 0)
           sum_freq[prefix_sum] = sum_freq.get(prefix_sum, 0) + 1

       return count
   ```

---

### **4. Longest Subarray with Equal Number of `X` and `Y`**
**Problem:** Given an array with multiple values, find the longest subarray with an equal number of two specified values (`X` and `Y`).  
**Approach:**
- Convert `X` to `+1`, `Y` to `-1`, ignore others.
- Use **prefix sum and hash map** to track first occurrences.

---

### **5. Longest Substring with Equal Number of `A` and `B`**
**Problem:** Given a string of letters (like `AABBBABA`), find the longest substring with equal `A`s and `B`s.  
**Approach:**
- Convert `A` to `+1`, `B` to `-1`.
- Use **prefix sum + hash map** (same logic as `0`s and `1`s problem).

