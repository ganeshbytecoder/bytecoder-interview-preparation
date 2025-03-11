### **Counting the Number of Substrings Using Two-Pass Inclusion-Exclusion**
This approach is commonly used when solving problems that require counting **valid substrings** efficiently. The **Two-Pass Inclusion-Exclusion technique** helps count substrings satisfying certain constraints by breaking the problem into simpler parts.

---
* https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/?envType=daily-question&envId=2025-03-10

### **Understanding the Two-Pass Approach**
Instead of iterating over all substrings (which is **O(N²)**), we can:
1. **First Pass (Inclusion)** → Count **all substrings**.
2. **Second Pass (Exclusion)** → Remove substrings **that don’t satisfy the condition**.

This method is useful in problems involving:
- Counting valid substrings with **at most** `K` occurrences.
- Finding the number of substrings satisfying **X but not Y**.
- Optimizing **brute force O(N²) substring counting**.

---


## **📌 2. Counting the Number of Substrings → Two-Pass Inclusion-Exclusion**
When the task is to **count** the number of substrings meeting a condition, use:
1. **Two-Pointer / Sliding Window**
2. **At Most K Trick**: `count(exactly K) = count(at most K) - count(at most K-1)`

### **Examples & Approach**
| Problem Type | Approach |
|-------------|----------|
| **Number of substrings with exactly K distinct characters** | Two-Pointer + `atMost(K) - atMost(K-1)` (`O(n)`) |
| **Number of vowel substrings containing all vowels** | Two-Pointer + HashMap (`O(n)`) |
| **Number of palindromic substrings** | Expand Around Center (`O(n^2)`) |
| **Number of anagram substrings matching a pattern** | Sliding Window + Frequency Map (`O(n)`) |

### **Example 2: Number of Substrings with Exactly K Distinct Characters**
✅ **Problem**: Count substrings with exactly `K` distinct characters.
```python
def at_most_k(s, k):
    freq = {}
    count = 0
    left = 0

    for right in range(len(s)):
        freq[s[right]] = freq.get(s[right], 0) + 1

        while len(freq) > k:
            freq[s[left]] -= 1
            if freq[s[left]] == 0:
                del freq[s[left]]
            left += 1
        
        count += right - left + 1  # Counting substrings
        
    return count

def substr_count_k_distinct(s, k):
    return at_most_k(s, k) - at_most_k(s, k - 1)
```
📌 **Key Idea**: Use `atMost(K) - atMost(K-1)` trick to get exactly K distinct character substrings.



### **Why and How the Two-Pass Inclusion-Exclusion Works**
---
The **Two-Pass Inclusion-Exclusion** technique is used to efficiently count substrings (or subarrays) that satisfy an **exact condition** (e.g., "exactly K distinct elements") by leveraging results from a simpler problem ("at most K distinct elements"). Instead of directly counting substrings with **exactly K elements** (which is difficult), we break the problem into two simpler counting problems and take their difference.

---

## **Core Idea: Convert "Exactly K" to "At Most K"**
Instead of counting **subarrays with exactly K distinct elements** directly, we count:

1. **F(K): The number of subarrays with at most K distinct elements.**
2. **F(K-1): The number of subarrays with at most (K-1) distinct elements.**
3. **Result:**  
   \[
   \text{Number of subarrays with exactly K distinct elements} = F(K) - F(K-1)
   \]

**Why does this work?**
- **F(K) counts too much**: It includes all subarrays with up to K distinct elements.
- **F(K-1) removes extra ones**: It removes subarrays that had fewer than K distinct elements.
- **Difference isolates exactly K**: The remaining subarrays must contain **exactly** K distinct elements.

---

## **Step-by-Step Explanation**
Let’s take an example:

```python
nums = [1, 2, 1, 2, 3]
K = 2
```

We want to count **subarrays with exactly 2 distinct elements**.

### **Step 1: Count Subarrays with at Most K Distinct Elements (F(K))**
Use the **sliding window approach**:
- Expand `right` pointer while keeping at most `K` distinct elements in the window.
- Shrink `left` pointer if there are more than `K` distinct elements.
- **Every valid window contributes (right - left + 1) subarrays**.

#### **Iteration Breakdown for F(2)**
| Left | Right | Window | Distinct Elements | Count |
|------|-------|--------|-------------------|-------|
| 0    | 0     | `[1]`  | 1                 | 1     |
| 0    | 1     | `[1,2]`| 2                 | 3     |
| 0    | 2     | `[1,2,1]`| 2               | 6     |
| 0    | 3     | `[1,2,1,2]`| 2            | 10    |
| 1    | 4     | `[2,1,2,3]`| 3 → shrink | 13    |

**Final count: `F(2) = 13`**

---

### **Step 2: Count Subarrays with at Most K-1 Distinct Elements (F(K-1))**
Now, repeat the process but with at most **1 distinct element**.

#### **Iteration Breakdown for F(1)**
| Left | Right | Window | Distinct Elements | Count |
|------|-------|--------|-------------------|-------|
| 0    | 0     | `[1]`  | 1                 | 1     |
| 1    | 1     | `[2]`  | 1                 | 2     |
| 2    | 2     | `[1]`  | 1                 | 3     |
| 3    | 3     | `[2]`  | 1                 | 4     |
| 4    | 4     | `[3]`  | 1                 | 5     |

**Final count: `F(1) = 5`**

---

### **Step 3: Compute the Answer**
\[
\text{Subarrays with exactly 2 distinct elements} = F(2) - F(1) = 13 - 5 = 8
\]

---

## **More Variations of This Pattern**
| **Problem** | **Solution Approach** |
|-------------|----------------------|
| **Number of substrings with exactly K distinct characters** | Use `F(K) - F(K-1)` trick with sliding window |
| **Longest substring with exactly K distinct characters** | Use `F(K) - F(K-1)` and track the max window size |
| **Count subarrays with exactly K odd numbers** | Convert odd to `1`, even to `0`, then apply `F(K) - F(K-1)` |
| **Find number of subarrays with at most K negative numbers** | Similar sliding window approach |
| **Count the number of substrings with at most K vowels** | Use sliding window, treating vowels as special |



```python 

class Solution:
    def countVowelSubstrings(self, word: str) -> int:
        def at_most_k_vowels(k):
            vowels = {'a', 'e', 'i', 'o', 'u'}
            freq = {}
            l = count = 0

            for r in range(len(word)):
                if word[r] in vowels:
                    freq[word[r]] = freq.get(word[r], 0) + 1
                else:
                    freq.clear()
                    l = r + 1
                    continue

                while len(freq) > k:
                    freq[word[l]] -= 1
                    if freq[word[l]] == 0:
                        del freq[word[l]]
                    l += 1

                count += r - l + 1  # All substrings ending at r

            return count

        # Count substrings with at most 5 vowels - substrings with at most 4 vowels
        return at_most_k_vowels(5) - at_most_k_vowels(4)


```

### **Example 1: Count Subarrays with at Most `K` Distinct Elements**
#### **Problem Statement**
Given an array, count the number of subarrays that contain **at most `K` distinct elements**.

#### **Optimal Approach Using Two-Pass Inclusion-Exclusion**
1. **Count subarrays with at most `K` distinct elements (`F(K)`).**
2. **Count subarrays with at most `K-1` distinct elements (`F(K-1)`).**
3. **The difference `F(K) - F(K-1)` gives the number of subarrays with exactly `K` distinct elements.**

#### **Implementation in Python**
```python
from collections import defaultdict

def count_subarrays_at_most_k(nums, k):
    freq_map = defaultdict(int)
    left = 0
    count = 0

    for right in range(len(nums)):
        freq_map[nums[right]] += 1

        while len(freq_map) > k:  # More than k distinct elements
            freq_map[nums[left]] -= 1
            if freq_map[nums[left]] == 0:
                del freq_map[nums[left]]
            left += 1

        count += (right - left + 1)  # Add all valid subarrays ending at 'right'

    return count

def count_subarrays_exactly_k(nums, k):
    return count_subarrays_at_most_k(nums, k) - count_subarrays_at_most_k(nums, k - 1)

# Example Usage
nums = [1, 2, 1, 2, 3]
k = 2
print("Number of subarrays with exactly K distinct elements:", count_subarrays_exactly_k(nums, k))
```

#### **Time Complexity**
- **O(N) per function** (since each element is added and removed from `freq_map` at most once).
- **Overall Complexity: O(N) + O(N) = O(N)**.

---

### **Example 2: Count Substrings with Exactly `K` Distinct Characters**
#### **Problem Statement**
Given a string `s`, count the number of substrings that contain **exactly `K` distinct characters**.

#### **Solution Using Two-Pass Inclusion-Exclusion**
We use the same logic as the previous problem:
- **F(K)**: Count substrings with at most `K` distinct characters.
- **F(K-1)**: Count substrings with at most `K-1` distinct characters.
- **F(K) - F(K-1)** gives the number of substrings with exactly `K` distinct characters.

#### **Python Implementation**
```python
def count_substrings_at_most_k(s, k):
    if k == 0:
        return 0

    freq_map = {}
    left = 0
    count = 0

    for right in range(len(s)):
        freq_map[s[right]] = freq_map.get(s[right], 0) + 1

        while len(freq_map) > k:  # More than K distinct characters
            freq_map[s[left]] -= 1
            if freq_map[s[left]] == 0:
                del freq_map[s[left]]
            left += 1

        count += (right - left + 1)  # Count valid substrings

    return count

def count_substrings_exactly_k(s, k):
    return count_substrings_at_most_k(s, k) - count_substrings_at_most_k(s, k - 1)

# Example Usage
s = "pqpqs"
k = 2
print("Number of substrings with exactly K distinct characters:", count_substrings_exactly_k(s, k))
```

#### **Expected Output**
```
Number of substrings with exactly K distinct characters: 7
```
#### **Explanation**
The substrings with exactly `2` distinct characters are:
- `"pq"`
- `"qp"`
- `"pqp"`
- `"qpq"`
- `"pqpq"`
- `"qs"`
- `"s"`

---

### **Why Use Two-Pass Inclusion-Exclusion?**
1. **Optimized Counting:**
    - Instead of generating substrings (O(N²)), we count them efficiently in **O(N)**.
2. **Works for Exact Constraints:**
    - Since counting **exactly K** is harder, we count **at most K** and subtract **at most K-1**.
3. **Avoids Nested Loops:**
    - Traditional substring approaches use O(N²) loops, but this method optimizes it to **O(N)**.

---

## **Similar Pattern-Based Problems**
These problems follow a similar **sliding window + inclusion-exclusion** pattern:

| Problem Type | Solution Approach |
|-------------|------------------|
| **Count Subarrays with at Most `K` Distinct Elements** | Sliding Window + Hash Map |
| **Count Substrings with at Most `K` Distinct Characters** | Sliding Window + Hash Map |
| **Find Number of Subarrays with Exactly `K` Distinct Elements** | `F(K) - F(K-1)` Trick |
| **Find Number of Substrings with Exactly `K` Distinct Characters** | `F(K) - F(K-1)` Trick |


---