### **📌 Binary Substring Problems → Prefix Sum + HashMap**
Binary substring problems often involve counting or finding substrings that satisfy a **balance condition** (e.g., equal number of `0s` and `1s`). The most effective way to solve these problems in **O(N) time complexity** is by using **Prefix Sum + HashMap**.

---

## **🔹 Why Use Prefix Sum + HashMap?**
- **Prefix Sum** helps track cumulative balance (e.g., `+1` for `1`, `-1` for `0`).
- **HashMap** stores first occurrences of prefix sums, allowing efficient lookup of subarrays where the balance condition holds.
- **Works in O(N) time complexity** instead of **O(N²) brute force**.



## **📌 3. Binary Substring Problems → Prefix Sum + HashMap**
Use **prefix sum & HashMap** when you need to:
- Count the number of substrings satisfying a binary condition (e.g., equal number of `0s` and `1s`).

### **Examples & Approach**
| Problem Type | Approach |
|-------------|----------|
| **Longest substring with equal 0s and 1s** | Prefix Sum + HashMap (`O(n)`) |
| **Count substrings with equal number of 0s and 1s** | Prefix Sum + HashMap (`O(n)`) |
| **Longest balanced substring (brackets, binary strings, etc.)** | Stack-based approach (`O(n)`) |

### **Example 3: Count Binary Substrings with Equal 0s and 1s**
✅ **Problem**: Count the number of substrings where the number of `0s` and `1s` are the same.
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
📌 **Key Idea**: Track consecutive groups of `0s` and `1s`, then sum `min(prev, cur)`.

---

---

## **Example 1: Longest Substring with Equal `0s` and `1s`**
✅ **Problem:** Find the **longest contiguous substring** with an equal number of `0s` and `1s`.

### **Approach**
1. Convert `0s` to `-1`, keeping `1s` as `+1`.
2. Compute the **prefix sum** while iterating.
3. If the **same prefix sum appears twice**, it means the subarray in between has a balanced number of `0s` and `1s`.
4. Use a **HashMap** to store first occurrences of prefix sums.

### **Python Implementation**
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
            sum_index_map[prefix_sum] = i  # Store first occurrence

    return s[start_idx:start_idx + max_length], max_length

# Example Usage
s = "110101110"
result, length = longest_equal_0s_1s_substring(s)
print("Longest substring with equal 0s and 1s:", result)
print("Length:", length)
```

### **🔹 Explanation**
| Index | Char | Transformed | Prefix Sum | First Occurrence in HashMap |
|--------|------|------------|------------|------------------------------|
| 0      | 1    | +1         | 1          | Stored at index `0`          |
| 1      | 1    | +1         | 2          | Stored at index `1`          |
| 2      | 0    | -1         | 1          | Found in HashMap! (0 to 2 is a valid substring) |
| 3      | 1    | +1         | 2          | Found in HashMap! (1 to 3 is valid) |
| 4      | 0    | -1         | 1          | Found in HashMap! (2 to 4 is valid) |

📌 **Key Idea**: The first time a prefix sum appears, store its index. If it appears again, it means there’s a balanced substring in between.

### **⏳ Time Complexity**
- **O(N)**: Single pass with HashMap lookups.
- **O(N) Space**: Stores at most `N` entries in HashMap.

---

## **Example 2: Count Subarrays with Equal `0s` and `1s`**
✅ **Problem:** Count the number of **subarrays** (not just substrings) where `0s` and `1s` are equal.

### **Approach**
1. Use **Prefix Sum** (`+1` for `1`, `-1` for `0`).
2. Store occurrences of prefix sums in a **HashMap**.
3. If the same prefix sum appears **multiple times**, count all possible subarrays between occurrences.

### **Python Implementation**
```python
def count_equal_0s_1s_subarrays(s):
    prefix_sum = 0
    sum_freq = {0: 1}  # Initialize with sum 0 at frequency 1
    count = 0

    for char in s:
        prefix_sum += 1 if char == '1' else -1
        count += sum_freq.get(prefix_sum, 0)
        sum_freq[prefix_sum] = sum_freq.get(prefix_sum, 0) + 1

    return count

# Example Usage
s = "110101110"
print("Count of subarrays with equal 0s and 1s:", count_equal_0s_1s_subarrays(s))
```

### **🔹 Explanation**
| Index | Char | Transformed | Prefix Sum | HashMap (Count) |
|--------|------|------------|------------|-----------------|
| 0      | 1    | +1         | 1          | `{0:1, 1:1}`    |
| 1      | 1    | +1         | 2          | `{0:1, 1:1, 2:1}` |
| 2      | 0    | -1         | 1          | `{0:1, 1:2, 2:1}` → **1 valid subarray** |
| 3      | 1    | +1         | 2          | `{0:1, 1:2, 2:2}` → **1 valid subarray** |
| 4      | 0    | -1         | 1          | `{0:1, 1:3, 2:2}` → **2 valid subarrays** |

📌 **Key Idea**: Every time a prefix sum repeats, it means a subarray between two indices has equal `0s` and `1s`.

### **⏳ Time Complexity**
- **O(N)**: Single pass with HashMap updates.
- **O(N) Space**: Stores at most `N` entries in HashMap.

---

## **Example 3: Count Binary Substrings with Equal 0s and 1s**
✅ **Problem**: Count substrings where **all 0s appear first, then all 1s** (or vice versa).

### **Approach**
- **Group consecutive 0s and 1s**.
- The number of valid substrings is **min(previous group size, current group size)**.

### **Python Implementation**
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

# Example Usage
s = "00110011"
print("Count of binary substrings:", count_binary_substrings(s))
```

### **🔹 Explanation**
1. Group sizes:
   ```
   "00110011" → [2, 2, 2, 2] (0s, 1s, 0s, 1s)
   ```
2. Valid substrings:
   ```
   min(2,2) + min(2,2) + min(2,2) = 6
   ```

📌 **Key Idea**: Consecutive counts determine valid substrings.

### **⏳ Time Complexity**
- **O(N)**: Single pass grouping.
- **O(1) Space**.

---

## **Summary of Binary Substring Patterns**
| **Problem** | **Approach** | **Time Complexity** |
|------------|------------|------------------|
| **Longest substring with equal `0s` and `1s`** | Prefix Sum + HashMap | **O(N)** |
| **Count subarrays with equal `0s` and `1s`** | Prefix Sum + HashMap | **O(N)** |
| **Count binary substrings where all `0s` come before `1s`** | Consecutive Grouping | **O(N)** |

---

## **Final Thoughts**
- **Prefix Sum + HashMap** is the go-to technique for finding longest or counting **binary substrings**.
- **Consecutive grouping** is useful when substrings must be in **a specific order**.
- **These patterns are crucial in FAANG interviews**, especially for **strings and arrays**.

Would you like **practice problems or variations** on this technique? 🚀