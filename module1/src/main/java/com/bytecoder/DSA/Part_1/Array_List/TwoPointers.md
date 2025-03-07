* Write a function that takes a string, s, as an input and determines whether or not it is a palindrome.

* Sum of Three Values or two sum

* Remove nth Node from End of LinkedList/array

* Sort Colors : https://leetcode.com/problems/sort-colors/description/

* Given a sentence, reverse the order of its words without affecting the order of letters within the given word.


* Write a function that takes a string as input and checks whether it can be a valid palindrome by removing at most one character from it.
   

// slow and faster pointers

* Check weather or not a linked list contains a cycle.

* Middle of the Linked List in O(n)

* https://leetcode.com/problems/find-the-duplicate-number/description/

* https://leetcode.com/problems/container-with-most-water/description/?envType=problem-list-v2&envId=array&status=TO_DO



Your code is intended to find the duplicate number in an array using Floyd’s Tortoise and Hare (Cycle Detection) algorithm, but it contains logical errors and inefficiencies that lead to **Time Limit Exceeded (TLE)**:


### Corrected Code (Floyd’s Tortoise and Hare Algorithm)
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

