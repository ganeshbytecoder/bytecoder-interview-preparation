## **2️⃣ Two-Pointer Approach**
Used when:
✅ Comparing or modifying elements **from both ends**.  
✅ Sorting-based problems (e.g., **pair sum**, **closest pairs**, **Max water **).  
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
* https://leetcode.com/problems/container-with-most-water/description/?envType=problem-list-v2&envId=array&status=TO_DO

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



## Slow and Fast Pointers (Floyd’s Tortoise and Hare Algorithm)
* https://leetcode.com/problems/find-the-duplicate-number/description/

This approach ensures O(n) time complexity and O(1) space complexity.

```python
from typing import List

class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        slow, fast = nums[0], nums[0]
        
        # Phase 1: Detect the cycle
        while True:
            slow = nums[slow]
            fast = nums[nums[fast]]
            if slow == fast:
                break
        
        # Phase 2: Find the start of the cycle
        slow = nums[0]
        while slow != fast:
            slow = nums[slow]
            fast = nums[fast]
        
        return slow
```

### Explanation:
1. **Cycle Detection (Phase 1)**:
   - `slow` moves one step at a time: `slow = nums[slow]`
   - `fast` moves two steps at a time: `fast = nums[nums[fast]]`
   - The loop continues until `slow == fast`, indicating a cycle.

2. **Finding the Start of the Cycle (Phase 2)**:
   - Reset `slow` to `nums[0]` and move both `slow` and `fast` one step at a time.
   - The point where they meet again is the duplicate number.


### **Other Approaches to Use**
| Method | Time Complexity | Space Complexity | Best When? |
|--------|----------------|------------------|------------|
| **HashSet** | O(n) | O(n) | Extra space is allowed |
| **Sorting** | O(n log n) | O(1) | Modifying array is allowed |


--
## Other Problems

* Write a function that takes a string, s, as an input and determines whether or not it is a palindrome.

* Sum of Three Values or two sum

* Given a sentence, reverse the order of its words without affecting the order of letters within the given word.


* Write a function that takes a string as input and checks whether it can be a valid palindrome by removing at most one character from it.
   

// slow and faster pointers

* Check weather or not a linked list contains a cycle.

* Middle of the Linked List in O(n)


### 12. **Merge Two Sorted Arrays Without Using Extra Space**
    Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    Output: [1,2,2,3,5,6]

### 29. **Trapping Rain Water Problem**
- **Hint**: Calculate the left and right maximum heights for each element and use them to find the trapped water.
