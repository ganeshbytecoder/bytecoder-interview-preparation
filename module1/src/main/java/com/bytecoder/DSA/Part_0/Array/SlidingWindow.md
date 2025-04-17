## Fixed size window and may allow  circular or not
**Note** can be sub-array for max/min sum/product elements or with length k
* https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/description/?envType=daily-question&envId=2025-03-08
* https://leetcode.com/problems/alternating-groups-ii/description/?envType=daily-question&envId=2025-03-09
* https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/submissions/1568646837/?envType=weekly-question&envId=2025-03-08
* Given a string S which represents DNA sequence, the task is to find all the 10-letter long substring that are repeated more than once. Returning the sequence can be done in any order.


## **1️⃣ Sliding Window (Variable Length)**
Used when:
✅ Finding **longest/shortest** subarray that meets a condition.  and this will have only one (more with same value) length specific solution/s that will satisfy
✅ Finding **contiguous** subarrays efficiently.  
✅ Works in **O(n)** time using two pointers.

### **Example Problems without conditions**
| Problem Type | Approach |
|-------------|----------|
| **Maximum sum subarray** | Kadane’s Algorithm (`O(n)`) |
| **Minimum sum subarray** | Modified Kadane’s (`O(n)`) |
| **Maximum product subarray** | Dynamic Kadane’s (`O(n)`) |
| **Longest subarray with sum ≤ k** | Sliding Window (`O(n)`) |
| **Smallest subarray with sum ≥ k** | Sliding Window (`O(n)`) |
| **Longest substring with at most K distinct elements** | Sliding Window + HashMap (`O(n)`) |


## **3️⃣ Kadane's Algorithm (Max/Min Subarray Sum)**
Used when:
✅ Finding the **maximum/minimum sum** of a contiguous subarray.  
✅ Works in **O(n) time**.

### **8. [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)**

### https://leetcode.com/problems/maximum-sum-circular-subarray/?envType=study-plan-v2&envId=top-interview-150


### **Example: Maximum Subarray (Kadane's Algorithm)**
```python
def max_subarray(nums: list[int]) -> int:
    max_sum = float('-inf')
    current_sum = 0

    for num in nums:
        current_sum = max(num, current_sum + num)
        max_sum = max(max_sum, current_sum)

    return max_sum
```
✅ **Use When**: Finding the **largest contiguous sum**.

---

4. **[209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)**
   - Find the smallest subarray whose sum is at least `target`.
   - **Condition:** Sum must be `>= target`, and subarray should be smallest.


7. **Longest Substring Without Repeating Characters**
   - *Problem*: Find the length of the longest substring with unique characters.
   - *Concept*:
      - Use a sliding window and HashMap to track indices of characters.
      - use sliding window
   - [LeetCode](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

* Question-2
```python

def longest_subarray_with_unique_elements(nums):
    element_set = set()
    left = 0
    max_length = 0
    start_idx = 0  # To track start index of the longest subarray

    for right in range(len(nums)):
        while nums[right] in element_set:
            element_set.remove(nums[left])
            left += 1  # Shrink window from left

        element_set.add(nums[right])
        
        # Update max_length and store the starting index
        if right - left + 1 > max_length:
            max_length = right - left + 1
            start_idx = left
    
    return nums[start_idx:start_idx + max_length], max_length

# Example Usage
nums = [4, 3, 5, 1, 3, 5, 2, 6, 7]
result, length = longest_subarray_with_unique_elements(nums)
print("Longest Subarray:", result)
print("Length:", length)

```


* Question-3 : https://leetcode.com/problems/minimum-window-substring/description/
```python

def min_length_subarray(nums, required_elements):
    from collections import Counter

    required_set = set(required_elements)
    required_count = Counter()
    unique_found = 0
    left = 0
    min_length = float('inf')
    min_subarray = []

    for right in range(len(nums)):
        # If this element is required, update its count
        if nums[right] in required_set:
            required_count[nums[right]] += 1
            if required_count[nums[right]] == 1:  # First occurrence
                unique_found += 1

        # Try shrinking the window from the left while it still contains all elements
        while unique_found == len(required_set):
            if right - left + 1 < min_length:
                min_length = right - left + 1
                min_subarray = nums[left:right+1]

            # Remove the leftmost element and shrink the window
            if nums[left] in required_set:
                required_count[nums[left]] -= 1
                if required_count[nums[left]] == 0:  # Element fully removed
                    unique_found -= 1

            left += 1

    return min_subarray if min_length != float('inf') else []

# Example Usage
nums = [4, 3, 5, 1, 3, 5, 2, 6, 7]

# Case 1: Required elements = [5, 6]
result1 = min_length_subarray(nums, [5, 6])
print("Min Length Subarray (5,6):", result1)

# Case 2: Required elements = [5, 2, 6]
result2 = min_length_subarray(nums, [5, 2, 6])
print("Min Length Subarray (5,2,6):", result2)


```
---

* Question-3 :  (https://leetcode.com/problems/minimum-window-substring/description/)
```python

from collections import Counter

def min_length_subarray(nums, required_elements):
    required_map = Counter(required_elements)
    window_count = Counter()
    have, need = 0, len(required_map)
    
    res = []
    res_len = float('inf')
    left = 0

    for right, num in enumerate(nums):
        if num in required_map:
            window_count[num] += 1
            if window_count[num] == required_map[num]:
                have += 1

        while have == need:
            if (right - left + 1) < res_len:
                res = nums[left:right+1]
                res_len = right - left + 1

            # Shrink from the left
            if nums[left] in required_map:
                if window_count[nums[left]] == required_map[nums[left]]:
                    have -= 1
                window_count[nums[left]] -= 1

            left += 1

    return res

```



   - 
9. **[1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/)**
   - Find the longest contiguous subarray of `1s` after flipping at most `k` zeros.
   - **Condition:** Subarray length maximization while maintaining a condition.
   - **Difficulty:** Medium

10. **[424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)**
- what id it's array Find the longest contiguous substring where you can replace at most `k` characters.
- **Condition:** Subarray length maximization with `≤ k` changes.
- **Difficulty:** Medium

17. **[567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)**
- Check if some substring is a permutation of `s2`.
- **Condition:** Substring must be a permutation of another string.
- **Difficulty:** Medium



6. **[1371. Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/)**
   - Find the longest contiguous substring where each vowel appears an even number of times.
   - **Condition:** Subarray must satisfy an XOR-based condition.
   - **Difficulty:** Medium