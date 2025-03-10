

## **📌 Array & Subarray Problem Patterns for FANG Interviews**
Array and subarray problems commonly appear in FANG interviews, and they follow certain **repeatable patterns**. Below is a **complete** breakdown of these patterns, when to use them, and example implementations.

---

## **1️⃣ Sliding Window (Variable Length)**
Used when:
✅ Finding **longest/shortest** subarray that meets a condition.  
✅ Finding **contiguous** subarrays efficiently.  
✅ Works in **O(n)** time using two pointers.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| **Longest subarray with sum ≤ k** | Sliding Window (`O(n)`) |
| **Smallest subarray with sum ≥ k** | Sliding Window (`O(n)`) |
| **Longest substring with at most K distinct elements** | Sliding Window + HashMap (`O(n)`) |

### **Example: Smallest Subarray With Sum ≥ Target**
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
✅ **Use When**: Finding **contiguous subarrays** that satisfy a condition.

---

## **2️⃣ Two-Pointer Approach**
Used when:
✅ Comparing or modifying elements **from both ends**.  
✅ Sorting-based problems (e.g., **pair sum**, **closest pairs**).  
✅ Works in **O(n log n) for sorting + O(n) for traversal**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| **Pair sum problems (Two Sum in sorted array)** | Two Pointers (`O(n)`) |
| **Merging two sorted arrays** | Two Pointers (`O(n)`) |
| **Trapping rainwater** | Two Pointers (`O(n)`) |

### **Example: Two Sum in Sorted Array**
```python
def two_sum(numbers: list[int], target: int) -> list[int]:
    left, right = 0, len(numbers) - 1

    while left < right:
        curr_sum = numbers[left] + numbers[right]
        if curr_sum == target:
            return [left + 1, right + 1]
        elif curr_sum < target:
            left += 1
        else:
            right -= 1

    return []
```
✅ **Use When**: Finding **pairs** or working with **sorted arrays**.

---

## **3️⃣ Kadane's Algorithm (Max/Min Subarray Sum)**
Used when:
✅ Finding the **maximum/minimum sum** of a contiguous subarray.  
✅ Works in **O(n) time**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| **Maximum sum subarray** | Kadane’s Algorithm (`O(n)`) |
| **Minimum sum subarray** | Modified Kadane’s (`O(n)`) |
| **Maximum product subarray** | Dynamic Kadane’s (`O(n)`) |

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

## **4️⃣ Prefix Sum & HashMap**
Used when:
✅ Finding **sum-based** subarray problems (e.g., subarrays with a target sum).  
✅ Works in **O(n) time** using **prefix sums**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| **Subarray sum equals K** | Prefix Sum + HashMap (`O(n)`) |
| **Count subarrays divisible by K** | Prefix Sum + Modulo (`O(n)`) |

### **Example: Count Subarrays with Sum K**
```python
def subarray_sum(nums: list[int], k: int) -> int:
    prefix_sum = {0: 1}  
    current_sum = 0
    count = 0

    for num in nums:
        current_sum += num
        if current_sum - k in prefix_sum:
            count += prefix_sum[current_sum - k]
        prefix_sum[current_sum] = prefix_sum.get(current_sum, 0) + 1

    return count
```
✅ **Use When**: Finding subarrays based on **sum conditions**.

---

## **5️⃣ Sorting + Two Pointers**
Used when:
✅ Finding **triplets, quadruplets, closest sums**.  
✅ Works in **O(n log n) for sorting + O(n) traversal**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| **Three Sum Problem** | Sorting + Two Pointers (`O(n^2)`) |
| **Four Sum Problem** | Sorting + Two Pointers (`O(n^3)`) |

### **Example: Three Sum Problem**
```python
def three_sum(nums: list[int]) -> list[list[int]]:
    nums.sort()
    result = []

    for i in range(len(nums) - 2):
        if i > 0 and nums[i] == nums[i - 1]:  
            continue  

        left, right = i + 1, len(nums) - 1
        while left < right:
            total = nums[i] + nums[left] + nums[right]

            if total == 0:
                result.append([nums[i], nums[left], nums[right]])
                left += 1
                right -= 1

                while left < right and nums[left] == nums[left - 1]:
                    left += 1  
                while left < right and nums[right] == nums[right + 1]:
                    right -= 1  
            elif total < 0:
                left += 1
            else:
                right -= 1

    return result
```
✅ **Use When**: Finding **triplets, quadruplets** with sum constraints.

---

## **6️⃣ Monotonic Stack (Next Greater Element)**
Used when:
✅ Finding **next greater/smaller element** efficiently.  
✅ Works in **O(n)** time using a **stack**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| **Next Greater Element** | Monotonic Stack (`O(n)`) |
| **Largest Rectangle in Histogram** | Monotonic Stack (`O(n)`) |

### **Example: Next Greater Element**
```python
def next_greater_elements(nums: list[int]) -> list[int]:
    stack, result = [], [-1] * len(nums)

    for i in range(len(nums)):
        while stack and nums[stack[-1]] < nums[i]:
            result[stack.pop()] = nums[i]
        stack.append(i)

    return result
```
✅ **Use When**: Finding **next larger/smaller elements** efficiently.

---

## **7️⃣ Binary Search on Subarrays**
Used when:
✅ Searching in **sorted arrays or subarrays** efficiently.  
✅ Works in **O(log n) time**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| **Find First and Last Position in Sorted Array** | Binary Search (`O(log n)`) |
| **Find K-th missing element** | Binary Search (`O(log n)`) |

### **Example: Find First and Last Position**
```python
def search_range(nums: list[int], target: int) -> list[int]:
    def find_boundary(left=True):
        lo, hi = 0, len(nums) - 1
        while lo <= hi:
            mid = (lo + hi) // 2
            if nums[mid] > target or (left and nums[mid] == target):
                hi = mid - 1
            else:
                lo = mid + 1
        return lo

    left_index = find_boundary(True)
    return [left_index, find_boundary(False) - 1] if left_index < len(nums) and nums[left_index] == target else [-1, -1]
```
✅ **Use When**: Searching efficiently in **sorted arrays**.

---



1️⃣ **Merge Intervals (Sorting + Greedy)**  
✅ Used for interval problems like **meeting rooms, merge overlapping intervals**.

**Example:** **Merge Intervals**
```python
def merge_intervals(intervals: list[list[int]]) -> list[list[int]]:
    intervals.sort()  
    merged = []

    for interval in intervals:
        if not merged or merged[-1][1] < interval[0]:
            merged.append(interval)
        else:
            merged[-1][1] = max(merged[-1][1], interval[1])

    return merged
```

---

2️⃣ **Heap / Priority Queue**  
✅ Used when you need **Kth largest/smallest**, **sliding window max**.

**Example:** **Find Kth Largest Element**
```python
import heapq
def find_kth_largest(nums: list[int], k: int) -> int:
    return heapq.nlargest(k, nums)[-1]  
```

---

3️⃣ **Counting + HashMap**  
✅ Used for **finding duplicates, checking frequency conditions**.

**Example:** **Find All Duplicates in an Array**
```python
def find_duplicates(nums: list[int]) -> list[int]:
    freq = {}
    duplicates = []

    for num in nums:
        freq[num] = freq.get(num, 0) + 1
        if freq[num] == 2:
            duplicates.append(num)

    return duplicates
```

---

4️⃣ **Bit Manipulation**  
✅ Used for **finding missing or unique elements**.

**Example:** **Find the Single Non-Repeating Element**
```python
def single_number(nums: list[int]) -> int:
    result = 0
    for num in nums:
        result ^= num  
    return result
```

---
# **✅ Final Checklist of Array & Subarray Patterns**
| **Pattern** | **When to Use?** | **Time Complexity** |
|------------|----------------|------------------|
| **Sliding Window (Fixed/Variable)** | Find longest/shortest subarray with condition | `O(n)` |
| **Two Pointers** | Pair/triplet problems, sorted arrays | `O(n) or O(n log n)` |
| **Kadane’s Algorithm** | Maximum/Minimum sum subarray | `O(n)` |
| **Prefix Sum + HashMap** | Find subarrays based on sum conditions | `O(n)` |
| **Sorting + Two Pointers** | 2-sum, 3-sum, 4-sum, closest pair | `O(n log n)` |
| **Monotonic Stack** | Next greater/smaller element | `O(n)` |
| **Binary Search on Subarrays** | Search in sorted arrays | `O(log n)` |
| **Merge Intervals** | Interval merging, meeting rooms | `O(n log n)` |
| **Heap (Priority Queue)** | Kth largest/smallest, sliding window max | `O(n log k)` |
| **Counting + HashMap** | Finding duplicates, frequency counts | `O(n)` |
| **Bit Manipulation** | Finding missing/unique elements | `O(n)` |

---







```java
//    Find Largest Sum Contiguous Subarray (Kadane's Algorithm)
    public int maxSubArray(int[] nums) {
        int max = nums[0], sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
```



https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/?envType=problem-list-v2&envId=array&


https://leetcode.com/problems/plus-one/?envType=problem-list-v2&envId=array

https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150

### 9. [**Add “1” to a Number Represented as  List**](https://leetcode.com/problems/plus-one/)
- **Hint**: Reverse the list, add 1 to the head node, handle carry, then reverse it back.
- use recursion and carry forward
- using stack
```java
class Solution {

    int increase(int[] nums, int index){
        if(index==nums.length-1){
            int carry = (nums[index]+1) /10;
            nums[index] =  (nums[index]+1) %10;

            return carry;
        }
        int carry = increase(nums, index+1);
        nums[index] =  (nums[index]+carry);
        carry = (nums[index]) /10;
        nums[index] =(nums[index]) %10;
        return carry;
    }

    public int[] plusOne(int[] digits) {
        int carry = increase(digits, 0);
        if(carry==0){
            return digits;
        }else{
            int[] nums = new int[digits.length+1];
            nums[0]=carry;
            for(int i=1; i<nums.length; i++){
                nums[i] = digits[i-1];
            }
            return nums;
        }
    }
}
```



### 6. **Find the Union and Intersection of Two Sorted Arrays**
**Hint**: 
   - brute force
   - Use two pointers, one for each array, and traverse through both arrays simultaneously. Use comparison to find union and intersection.
   - use hashmap for each array and cross check elements

### 7. **Cyclically Rotate an Array by One**
   - **Hint**: Store the last element in a temporary variable, then shift all other elements to the right, and place the last element at the first position.

### 12. **Merge Two Sorted Arrays Without Using Extra Space**
    Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    Output: [1,2,2,3,5,6]














### 9. **Minimize the Maximum Difference Between Heights** https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/description/
   - **Hint**: Sort the array, then consider adding or subtracting `k` from the minimum and maximum heights to minimize the difference.


### 11. **Find Duplicate in an Array of N+1 Integers**
   - **Hint**: Use Floyd's Tortoise and Hare (Cycle Detection) method to find the duplicate.


### 18. **Find All Pairs on Integer Array Whose Sum is Equal to Given Number**
   - **Hint**: Use a hash map to store the complement of each number as you traverse the array, and check if the complement exists in the map.

### 19. **Find Common Elements in 3 Sorted Arrays**
   - **Hint**: Use three pointers, one for each array, and increment the pointers based on comparison to find common elements.

### 20. **Rearrange the Array in Alternating Positive and Negative Items with O(1) Extra Space**
   - **Hint**: Partition the array into positive and negative numbers, then rearrange by swapping elements at even and odd indices.

### 21. **Find if There is Any Subarray with Sum Equal to 0**
   - **Hint**: Use a hash map to store the cumulative sum as you traverse the array. If a cumulative sum repeats, the subarray sum is zero.

### 22. **Find Factorial of a Large Number**
   - **Hint**: Use an array or a list to store digits and simulate the multiplication process digit by digit.

### 23. **Find Maximum Product Subarray**
   - **Hint**: Track the maximum and minimum products at each position due to the possibility of negative numbers flipping signs.

### 24. **Find Longest Consecutive Subsequence**
   - **Hint**: Use a hash set to store the elements, then for each element, check if it's the start of a sequence by checking if `element - 1` exists.

### 25. **Find Elements That Appear More Than "n/k" Times**
   - **Hint**: Use a modified Boyer-Moore majority vote algorithm to count potential candidates.

* ### 26. **Maximum Profit by Buying and Selling a Share At Most Twice**
   - **Hint**: Create two arrays: one for the maximum profit by selling up to that day, and the other for the maximum profit by buying from that day onwards.

### 27. **Find Whether an Array is a Subset of Another Array**
   - **Hint**: Use a hash set or binary search to check the presence of each element of one array in another.

### 28. **Find the Triplet that Sum to a Given Value**
   - **Hint**: Sort the array, then for each element, use the two-pointer technique to find the other two elements.

### 29. **Trapping Rain Water Problem**
   - **Hint**: Calculate the left and right maximum heights for each element and use them to find the trapped water.

### 30. **Chocolate Distribution Problem**
   - **Hint**: Sort the array, then find the minimum difference between the largest and smallest values in each subarray of size `m`.

### 31. **Smallest Subarray with Sum Greater Than a Given Value**
   - **Hint**: Use a sliding window technique to expand and shrink the window while keeping track of the sum.

* ### 32. **Three-Way Partitioning of an Array Around a Given Value**
   - **Hint**: Use the Dutch National Flag algorithm with three pointers to partition the array into three segments based on the given value.

### 33. **Minimum Swaps Required to Bring together Elements Less than or Equal to K Together**
   - **Hint**: Find the number of elements less than or equal to `K`, then use a sliding window approach to count swaps in subarrays of that size.

### 34. **Minimum No. of Operations Required to Make an Array Palindrome. The only allowed operation is”merging” (of two adjacent elements)**
   - **Hint**: Use a two-pointer approach and incrementally match elements at both ends by either merging or replacing them.

### 35. **Median of Two Sorted Arrays of Equal Size**
   - **Hint**: Use binary search on one array to partition both arrays into two halves that balance the number of elements and the median condition.
   - Merge two sorted arrays  (Pq) then get the median
   - Merge sorting and then get the median  (keep m1 and m2)
     - Case 1: m+n is odd, the median is at (m+n)/2th index in the array obtained after merging both the arrays. 
     - Case 2: m+n is even, the median will be the average of elements at index ((m+n)/2 – 1) and (m+n)/2 in the array obtained after merging both the arrays



### 36. **Median of Two Sorted Arrays of Different Sizes**
   - **Hint**: Use a binary search on the smaller array to find the partition where the median lies, ensuring elements to the left are smaller than elements to the right.
   - Merge two sorted arrays  (Pq) then get the median
   - Merge sorting and then get the median  (keep m1 and m2)
     - Case 1: m+n is odd, the median is at (m+n)/2th index in the array obtained after merging both the arrays. 
     - Case 2: m+n is even, the median will be the average of elements at index ((m+n)/2 – 1) and (m+n)/2 in the array obtained after merging both the arrays


### 16. **Count Inversions**
   - **Hint**: Use a modified merge sort algorithm that counts inversions while merging the subarrays.


