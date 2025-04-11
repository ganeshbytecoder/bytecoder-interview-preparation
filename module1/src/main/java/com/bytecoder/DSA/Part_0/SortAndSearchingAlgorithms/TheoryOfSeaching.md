
### 🔍 1. **Linear Search**

#### ✅ Concept:
- Sequentially checks each element of the array.
- Time Complexity: **O(n)**
- Space Complexity: **O(1)**
- Works for **unsorted** arrays.

#### ✅ When to Use:
- Array is **unsorted**.
- Small datasets.

#### ✅ Java Code:
```java
public class LinearSearch {
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target)
                return i; // Found
        }
        return -1; // Not found
    }
}
```

#### ✅ Python Code:
```python
def linear_search(arr, target):
    for i in range(len(arr)):
        if arr[i] == target:
            return i  # Found
    return -1  # Not found
```

---

### 🔍 2. **Binary Search (Sorted Array)**

#### ✅ Concept:
- Divide & Conquer.
- Requires **sorted** array.
- Time Complexity: **O(log n)**
- Space: **O(1)** (Iterative), **O(log n)** (Recursive)

#### ✅ When to Use:
- Array is **sorted**.
- Fast lookups required.

#### ✅ Java Code:
```java
public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target)
                return mid;
            else if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1; // Not found
    }
}
```

#### ✅ Python Code:
```python
def binary_search(arr, target):
    left, right = 0, len(arr) - 1

    while left <= right:
        mid = (left + right) // 2

        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1
```

---

### 🔄 3. **Cycle Detection in Linked List**

#### ✅ Concept:
Detect if a **linked list has a cycle** using:
- Hashing: O(n) space
- **Floyd’s Cycle Detection Algorithm (Tortoise & Hare)**: O(1) space

#### ✅ Java Code:
```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

public class CycleDetection {
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }
}
```

#### ✅ Python Code:
```python
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

def has_cycle(head):
    slow = head
    fast = head

    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next

        if slow == fast:
            return True
    return False
```

---

### 🌀 4. **Floyd's Cycle Detection Algorithm (Tortoise and Hare)**

#### ✅ Concept:
- Used for **cycle detection in linked lists**.
- Uses two pointers:
    - `slow` moves 1 step
    - `fast` moves 2 steps
- If there’s a cycle, they will **meet** at some point.

#### ✅ To Find Starting Node of Cycle:
- After detecting the cycle, reset one pointer to head.
- Move both one step at a time until they meet → that’s the cycle start.

#### ✅ Java Code to Get Starting Node of Cycle:
```java
public class CycleStart {
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                ListNode start = head;
                while (start != slow) {
                    start = start.next;
                    slow = slow.next;
                }
                return start; // start of the cycle
            }
        }
        return null; // No cycle
    }
}
```

#### ✅ Python Code to Get Starting Node of Cycle:
```python
def detect_cycle_start(head):
    slow = head
    fast = head

    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next

        if slow == fast:
            start = head
            while start != slow:
                start = start.next
                slow = slow.next
            return start  # Start of cycle
    return None
```

---


### ⚡ **Quick Select Algorithm** (a.k.a Quicksearch)

### ✅ What is Quick Select?
Quick Select is a **selection algorithm** to find the **k-th smallest (or largest)** element in an unsorted list.  
It’s based on the **partitioning logic** of Quick Sort.

---

### ✅ Time & Space Complexity
| Case | Time | Space |
|------|------|-------|
| Best / Avg | **O(n)** | O(1) (in-place) |
| Worst | O(n²) (rare) | O(1) |

---

### ✅ When to Use
- Find:
    - k-th smallest/largest element
    - Median
- Faster than full sort (**O(n)** vs O(n log n))

---

### ✅ Core Idea
1. Choose a pivot.
2. Partition array:
    - All elements `< pivot` to left
    - All elements `> pivot` to right
3. Check the pivot's index:
    - If `pivot == k`, return it.
    - If `pivot > k`, recurse left.
    - If `pivot < k`, recurse right.

---

### 🔧 Java Implementation
```java
public class QuickSelect {
    public static int quickSelect(int[] nums, int k) {
        return quickSelectHelper(nums, 0, nums.length - 1, k - 1);
    }

    private static int quickSelectHelper(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];

        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == k) return nums[k];
        else if (pivotIndex > k) return quickSelectHelper(nums, left, pivotIndex - 1, k);
        else return quickSelectHelper(nums, pivotIndex + 1, right, k);
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }

        swap(nums, i, right);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```

---

### 🐍 Python Implementation
```python
def quick_select(nums, k):
    def partition(left, right):
        pivot = nums[right]
        i = left
        for j in range(left, right):
            if nums[j] < pivot:
                nums[i], nums[j] = nums[j], nums[i]
                i += 1
        nums[i], nums[right] = nums[right], nums[i]
        return i

    def helper(left, right, k):
        if left == right:
            return nums[left]
        pivot_index = partition(left, right)
        if pivot_index == k:
            return nums[k]
        elif pivot_index > k:
            return helper(left, pivot_index - 1, k)
        else:
            return helper(pivot_index + 1, right, k)

    return helper(0, len(nums) - 1, k - 1)
```

---




### 🔍 3. **Ternary Search**
- ✅ Use when: **Unimodal functions (single peak/valley)**
- 🕒 Time: O(log n)
- 📊 Divide into 3 parts

---

### 🔍 4. **Jump Search**
- ✅ Use when: **Sorted arrays**
- 🕒 Time: O(√n)
- 🚶 Skip ahead in blocks, then do linear search

---

### 🔍 5. **Exponential Search**
- ✅ Use when: **Infinite/unknown-sized sorted arrays**
- 🕒 Time: O(log n)
- 🔎 Double the search range, then binary search

---

### 🔍 6. **Interpolation Search**
- ✅ Use when: **Sorted, uniformly distributed**
- 🕒 Best: O(log log n), Worst: O(n)
- 🔢 Predict position using value

---


---




## 🔝 Top LeetCode Problems on Search Algorithms

### 1️⃣ **Binary Search Patterns**
- 🔹 **[704. Binary Search](https://leetcode.com/problems/binary-search/)**
- 🔹 **[35. Search Insert Position](https://leetcode.com/problems/search-insert-position/)**
- 🔹 **[34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)**
- 🔹 **[33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)**
- 🔹 **[74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/)**
- 🔹 **[81. Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/)**
- https://leetcode.com/problems/cutting-ribbons/submissions/1602164437/
- https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
- https://leetcode.com/problems/koko-eating-bananas/description/
- https://leetcode.com/problems/split-array-largest-sum/description/
- solve create min number of subarrays from array where max sum of any sumarray is less than k



---
https://leetcode.com/problems/kth-largest-element-in-an-array/

---

### 4️⃣ **Matrix Search Problems**
- 🔹 **[240. Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/)** – Start from top-right
- 🔹 **[378. Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/)** – Min Heap/Binary Search

---
### 🧠 Top LeetCode Problems using Quick Select

| Problem | Link |
|--------|------|
| 🥇 **[215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)** |
| 🥈 **[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)** (can use QuickSelect or Heap) |
| 🥉 **[973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)** |
| 📈 **[658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/)** |

---







**Problem** Given an array of n numbers. find the element which appears the maximum number of times

hint:
* Brute-force solution
* sorting technique with binary search count
* sorting technique with normal count 
* using hash table and count array solution 

**Problem** Given an array of n numbers , give an algorithm for finding the first element in the array which is repeated. 

hint : 
* brute-force solution 
* using hashtable of element and index 
* hashset using traversing from last
* sorting technique of copied array with binary search count
* sorting technique of copied array with normal count


**Problem** find the element occurring odd number of times in the array

hint :
* brute-force solution 
* sorting solution 
* hashing table or count array solution 
* maths xor solution 

**Problem** find the two elements which got repeated in the given array

hint :
* brute-force solution 
* sorting solution 
* hashing table or count array solution 


**Problem** Given an array of n elements. find the two elements in the array such that their sum is equal to given target k

hint: 
* Brute-force solution 
* sorting technique and two pointers 
* hashing table

**Problem** Given an array of n elements. find the three elements in the array such that their sum is equal to given target k

hint: 
* Brute-force solution 
* sorting technique and two pointers 
* hashing table


**Problem** Given an array of n elements. find the two elements in the array such that their sum is closest to zero

hint: 
* Brute-force solution 
* sorting technique and two pointers 



**Problem** Given a sorted array of n elements with duplicates. find the first and last occurrence index with count of given target value.

example - [5,6,12,12,21,21,21]  ans = [4,7, 3] (first, last, count)

hint: 
* Brute-force solution 
* sorting technique 
* binary search when element is matched and increase until it matches the condition 


 Q 37 n 38 is left 



**Problem** given n*n matrix and in each row all 1's are followed by 0's . find the row with the max 0s

Hint: 
* start with the first row , last column. if the element is 0 then move to the previous column in the same row and at the same time increase the counter to indicate the max number of 0s. if the element is 1 then move to the next row in the same column. repeat this process until you reach last row and first column.

**Problem** separate the even and odd numbers in the array or similarly separate 0's and 1's in an array. 

Hint:
* using two pointers
* brute-force 

