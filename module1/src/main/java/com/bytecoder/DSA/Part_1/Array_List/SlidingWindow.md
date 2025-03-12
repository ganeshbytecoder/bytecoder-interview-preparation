## Fixed size window and may allow  circular or not
* https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/description/?envType=daily-question&envId=2025-03-08
* https://leetcode.com/problems/alternating-groups-ii/description/?envType=daily-question&envId=2025-03-09
* https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/submissions/1568646837/?envType=weekly-question&envId=2025-03-08
* public class SlidingWindow {

//    Given a string S which represents DNA sequence, the task is to find all the 10-letter long substring that are repeated more than once. Returning the sequence can be done in any order.
//    Count of substrings of length K with exactly K distinct characters
//    Longest substring without repeating characters
}


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

### **Example Problems with conditions**
| Problem Type | Approach |
|-------------|----------|
| **Longest subarray with sum ≤ k** | Sliding Window (`O(n)`) |
| **Smallest subarray with sum ≥ k** | Sliding Window (`O(n)`) |
| **Longest substring with at most K distinct elements** | Sliding Window + HashMap (`O(n)`) |


## **3️⃣ Kadane's Algorithm (Max/Min Subarray Sum)**
Used when:
✅ Finding the **maximum/minimum sum** of a contiguous subarray.  
✅ Works in **O(n) time**.


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

* Question-1
```python
def min_subarray_len(target: int, nums: list[int]) -> int:
    left = 0
    min_length = float('inf')
    current_sum = 0

    for right in range(len(nums)):
        current_sum += nums[right]

        while current_sum >= target:
            min_length = min(min_length, right - left + 1)
            current_sum -= nums[left]
            left += 1

    return min_length if min_length != float('inf') else 0
```

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


* Question-3
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


* https://leetcode.com/problems/minimum-size-subarray-sum/description/?envType=study-plan-v2&envId=top-interview-150


7. **Longest Substring Without Repeating Characters**
    - *Problem*: Find the length of the longest substring with unique characters.
    - *Concept*:
        - Use a sliding window and HashMap to track indices of characters.
        - use sliding window
    - [LeetCode](https://leetcode.com/problems/longest-substring-without-repeating-characters/)


### 31. **Smallest Subarray with Sum Greater Than a Given Value**
- **Hint**: Use a sliding window technique to expand and shrink the window while keeping track of the sum.
