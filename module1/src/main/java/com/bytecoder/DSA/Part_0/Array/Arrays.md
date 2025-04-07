
#### 1 all possible SubArray

```java
import java.util.*;

public class SubarraysUsingLoops {
    public static List<List<Integer>> findAllSubarrays(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Generate all possible subarrays
        for (int start = 0; start < nums.length; start++) { // Start index
            List<Integer> tempList = new ArrayList<>();
            for (int end = start; end < nums.length; end++) { // End index
                tempList.add(nums[end]); // Expand subarray
                result.add(new ArrayList<>(tempList)); // Store subarray
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> subarrays = findAllSubarrays(nums);
        System.out.println(subarrays);
    }
}

```


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
### 24. **Find Longest Consecutive Subsequence**
- **Hint**: Use a hash set to store the elements, then for each element, check if it's the start of a sequence by checking if `element - 1` exists.
```python
class Solution(object):
    def longestConsecutive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        num_set = set(nums)
        longest_count = 0
        
        for num in num_set:
            # Check if it's the start of a sequence
            if num - 1 not in num_set:
                current_num = num
                current_streak = 1
                
                while current_num + 1 in num_set:
                    current_num += 1
                    current_streak += 1
                
                longest_count = max(longest_count, current_streak)
        
        return longest_count
   
   ```

### 19. **Find Common Elements in 3 Sorted Arrays**
- **Hint**: Use three pointers, one for each array, and increment the pointers based on comparison to find common elements.







### 9. **Minimize the Maximum Difference Between Heights** https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/description/
- **Hint**: Sort the array, then consider adding or subtracting `k` from the minimum and maximum heights to minimize the difference.


### 18. **Find All Pairs on Integer Array Whose Sum is Equal to Given Number**
- **Hint**: Use a hash map to store the complement of each number as you traverse the array, and check if the complement exists in the map.


### 20. **Rearrange the Array in Alternating Positive and Negative Items with O(1) Extra Space**
- **Hint**: Partition the array into positive and negative numbers, then rearrange by swapping elements at even and odd indices.


### 22. **Find Factorial of a Large Number**
- **Hint**: Use an array or a list to store digits and simulate the multiplication process digit by digit.



### 25. **Find Elements That Appear More Than "n/k" Times**
- **Hint**: Use a modified Boyer-Moore majority vote algorithm to count potential candidates.

### 27. **Find Whether an Array is a Subset of Another Array**
- **Hint**: Use a hash set or binary search to check the presence of each element of one array in another.

### 28. **Find the Triplet that Sum to a Given Value**
- **Hint**: Sort the array, then for each element, use the two-pointer technique to find the other two elements.


### 30. **Chocolate Distribution Problem**
- **Hint**: Sort the array, then find the minimum difference between the largest and smallest values in each subarray of size `m`.


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




### **[Decode Ways](https://leetcode.com/problems/decode-ways/)**

#### **Problem Statement**
Given a string `s` containing only digits, determine the total number of ways to decode it, where:
- 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26".

---

#### **Solution Approaches**

##### 1. **Recursive Solution**
```java
public int numDecodings(String s) {
    return decodeRecursive(s, 0);
}

private int decodeRecursive(String s, int index) {
    if (index == s.length()) return 1;
    if (s.charAt(index) == '0') return 0;

    int ways = decodeRecursive(s, index + 1);
    if (index + 1 < s.length() && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
        ways += decodeRecursive(s, index + 2);
    }
    return ways;
}
```

##### 2. **Memoization Solution**
```java
public int numDecodings(String s) {
    int[] memo = new int[s.length()];
    Arrays.fill(memo, -1);
    return decodeMemo(s, 0, memo);
}

private int decodeMemo(String s, int index, int[] memo) {
    if (index == s.length()) return 1;
    if (s.charAt(index) == '0') return 0;
    if (memo[index] != -1) return memo[index];

    int ways = decodeMemo(s, index + 1, memo);
    if (index + 1 < s.length() && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
        ways += decodeMemo(s, index + 2, memo);
    }
    memo[index] = ways;
    return ways;
}
```

##### 3. **Tabulation Solution**
```java
public int numDecodings(String s) {
    int n = s.length();
    int[] dp = new int[n + 1];
    dp[0] = 1;

    for (int i = 1; i <= n; i++) {
        if (s.charAt(i - 1) != '0') {
            dp[i] += dp[i - 1];
        }
        if (i >= 2 && Integer.parseInt(s.substring(i - 2, i)) <= 26) {
            dp[i] += dp[i - 2];
        }
    }
    return dp[n];
}
```

---




6. **[1442. Count Triplets That Can Form Two Equal XORs](https://leetcode.com/problems/count-triplets-that-can-form-two-equal-xors/)**
    - Find the number of triplets `(i, j, k)` such that `XOR[i:j] == XOR[j:k]`.
    - **Condition:** XOR of subarrays must be equal.
    - **Difficulty:** Medium

---



