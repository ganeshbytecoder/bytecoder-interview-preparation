# Sorting Algorithms 
https://leetcode.com/problems/kth-largest-element-in-an-array/
https://leetcode.com/problems/top-k-frequent-elements/submissions/1598686457/


## bubble sort : 
comparing the each pair of elements and swapping them if need. bubble sort continues its iterations until no more swaps are needed. 
* algorithm takes O(n^2) and space complexity O(1)

```java
public static void main(String[] args) {
    int[] array = {10, 2, 3, 5, 0};
    for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array.length - 1; j++) {
            if (array[j+1] < array[j]) {
                swap(array, j, j+1);
            }
        }
    }
    System.out.println(array);
}
```

## selection sort (pick min from right): 
we creates two index i and j where is used for current element and j is to find least element from right side of list.
swap it with the value in the current position. 
Repeat this process for all the elements until the entire array is sorted. 
* algorithm takes O(n^2) and space complexity O(1)


```java
public static void main(String[] args) {
    int[] array = {10, 2, 3, 5, 0};
    for (int i = 0; i < array.length; i++) {
        int min = i;
        for (int j = i + 1; j < array.length; j++) {
            if (array[j] < array[min]) {
                min = j;
            }
        }
        swap(array, i, min);
    }
}

```


## Insertion sort(taash pick and shift): 
in this algorithm we pick a element and slide it till it satisfy the condition then swap it 

```java

public static void main(String[] args) {
    int[] array = {10, 2, 3, 5, 1};
    for (int i = 1; i < array.length; i++) {
        int k = i;
        int temp = array[k];
        
        while (k > 0 && temp < array[k - 1]) {
            array[k] = array[k - 1];
            k--;
        }
        array[k] = temp;
    }
    for (int i : array) {
        System.out.println(i);
    }
}
```

## Heap Sort


## Merge Sort

**Merge Sort** is a **Divide and Conquer** algorithm used for sorting an array or list. It breaks the input into smaller parts, sorts them, and then merges them back together in a sorted way.

---

### **Steps of Merge Sort:**

1. **Divide**:
    - Recursively split the array into two halves until each sub-array contains only one element.

2. **Conquer** (Sort):
    - Recursively sort each half.

3. **Combine** (Merge):
    - Merge the sorted halves back together into a single sorted array.

---

### **Merge Sort Example** (Array: `[6, 3, 8, 5, 2]`)

```
Step 1: Split => [6, 3, 8, 5, 2]
                   /         \
              [6, 3, 8]     [5, 2]
               /    \         /  \
           [6]    [3, 8]    [5]  [2]
                   /  \
                [3]  [8]

Step 2: Merge back in sorted order
         [6] + [3,8] => [3,6,8]
         [5] + [2] => [2,5]

Step 3: Merge [3,6,8] + [2,5] => [2,3,5,6,8]
```

---

### **Time and Space Complexity**

| Case       | Time Complexity |
|------------|-----------------|
| Best       | O(n log n)      |
| Average    | O(n log n)      |
| Worst      | O(n log n)      |

- **Space Complexity**: O(n) (due to temporary arrays used during merging)

---

### **Why Use Merge Sort?**

- **Stable sort**: Maintains relative order of equal elements.
- Performs well on **large datasets**.
- Good for **linked lists** (no extra space needed if merging is done in-place).


### **Java (Array)**
```java
import java.util.Arrays;

public class MergeSortArray {
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }

        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void main(String[] args) {
        int[] arr = {6, 3, 8, 5, 2};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr)); // Output: [2, 3, 5, 6, 8]
    }
}
```

---

### **Python (Array)**
```python
def merge_sort(arr):
    if len(arr) < 2:
        return arr

    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)

def merge(left, right):
    result = []
    i = j = 0

    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1

    result.extend(left[i:])
    result.extend(right[j:])
    return result

# Usage
arr = [6, 3, 8, 5, 2]
sorted_arr = merge_sort(arr)
print(sorted_arr)  # Output: [2, 3, 5, 6, 8]
```

### **Java (Linked List)**

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class MergeSortLinkedList {

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode mid = getMiddle(head);
        ListNode right = mid.next;
        mid.next = null;

        ListNode leftSorted = mergeSort(head);
        ListNode rightSorted = mergeSort(right);

        return merge(leftSorted, rightSorted);
    }

    private static ListNode getMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // Helper to print list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(6);
        head.next = new ListNode(3);
        head.next.next = new ListNode(8);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(2);

        head = mergeSort(head);
        printList(head);  // Output: 2 3 5 6 8
    }
}
```

---

### **Python (Linked List)**
```python
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

def merge_sort(head):
    if not head or not head.next:
        return head

    mid = get_middle(head)
    right = mid.next
    mid.next = None

    left_sorted = merge_sort(head)
    right_sorted = merge_sort(right)

    return merge(left_sorted, right_sorted)

def get_middle(head):
    slow = head
    fast = head.next
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    return slow

def merge(l1, l2):
    dummy = ListNode()
    tail = dummy

    while l1 and l2:
        if l1.val <= l2.val:
            tail.next = l1
            l1 = l1.next
        else:
            tail.next = l2
            l2 = l2.next
        tail = tail.next

    tail.next = l1 or l2
    return dummy.next

# Helper to print list
def print_list(head):
    while head:
        print(head.val, end=" ")
        head = head.next
    print()

# Usage
head = ListNode(6, ListNode(3, ListNode(8, ListNode(5, ListNode(2)))))
sorted_head = merge_sort(head)
print_list(sorted_head)  # Output: 2 3 5 6 8
```

---


## **Quick Sort Overview**

### **Steps:**

1. **Pick a Pivot**: Choose an element from the array (commonly first, last, middle, or random).
2. **Partition**: Rearrange elements so that:
    - All elements less than the pivot go to its left,
    - All elements greater than the pivot go to its right.
3. **Recursively sort** the left and right subarrays.



### **Quick Sort Example**

Input: `[6, 3, 8, 5, 2]`  
Pivot (e.g., 6)

Partitioning:
- Left of pivot: `[3, 5, 2]`
- Right of pivot: `[8]`
- Result after one round: `[3, 5, 2, 6, 8]`

Continue recursively on `[3, 5, 2]` and `[8]`.

---

### **Time and Space Complexity**

| Case       | Time       |
|------------|------------|
| Best       | O(n log n) |
| Average    | O(n log n) |
| Worst      | O(n²) (when array is already sorted or reverse sorted with bad pivot)

- **Space Complexity**: O(log n) (recursive stack)


### **Java (Array)**
```java
import java.util.Arrays;

public class QuickSortArray {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // choose last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {6, 3, 8, 5, 2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr)); // Output: [2, 3, 5, 6, 8]
    }
}
```

---

### **Python (Array)**
```python
def quick_sort(arr, low, high):
    if low < high:
        pi = partition(arr, low, high)
        quick_sort(arr, low, pi - 1)
        quick_sort(arr, pi + 1, high)

def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1

    for j in range(low, high):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]

    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

# Usage
arr = [6, 3, 8, 5, 2]
quick_sort(arr, 0, len(arr) - 1)
print(arr)  # Output: [2, 3, 5, 6, 8]
```

---


## bucket sort
Great! Let’s explore **Bucket Sort**, a super interesting sorting algorithm that works really well in **special cases** like **uniformly distributed data**.

---

## 🪣 What is **Bucket Sort**?

**Bucket Sort** is a **distribution sort** that:
1. Divides elements into several **buckets** (like bins),
2. Sorts each bucket **individually** (using any other sorting algorithm like Insertion Sort or Merge Sort),
3. Then **concatenates** the buckets to get the sorted array.

---

## 🧠 When is it used?

- When input is **uniformly distributed** over a range.
- Best for **floats** in `[0, 1)` range.
- Can be better than O(n log n) for specific distributions!

---

## 📊 Time and Space Complexity

| Case        | Time       | Notes                          |
|-------------|------------|--------------------------------|
| Best        | O(n + k)   | k = number of buckets          |
| Average     | O(n + k + n²/k) | Efficient if few elements per bucket |
| Worst       | O(n²)      | If all elements go into one bucket |
| Space       | O(n + k)   | Extra space for buckets        |

---

## ✅ Bucket Sort Algorithm Steps

1. Create `k` empty buckets.
2. Distribute the elements into the buckets based on their range.
3. Sort each bucket.
4. Merge all sorted buckets.

---

## 🧑‍💻 Python Implementation (For Float Values in [0, 1))
```python
def bucket_sort(arr):
    n = len(arr)
    if n <= 0:
        return arr

    # Step 1: Create empty buckets
    buckets = [[] for _ in range(n)]

    # Step 2: Distribute elements into buckets
    for num in arr:
        index = int(n * num)  # Index based on range [0,1)
        buckets[index].append(num)

    # Step 3: Sort individual buckets
    for bucket in buckets:
        bucket.sort()

    # Step 4: Concatenate buckets
    sorted_array = []
    for bucket in buckets:
        sorted_array.extend(bucket)

    return sorted_array

# Usage
arr = [0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68]
print(bucket_sort(arr))  # Output: sorted list
```

---

## 🧑‍💻 Java Implementation (For Float Values in [0, 1))
```java
import java.util.*;

public class BucketSort {
    public static void bucketSort(float[] arr) {
        int n = arr.length;
        if (n <= 0) return;

        // Step 1: Create empty buckets
        List<Float>[] buckets = new List[n];
        for (int i = 0; i < n; i++)
            buckets[i] = new ArrayList<>();

        // Step 2: Distribute into buckets
        for (float num : arr) {
            int index = (int)(num * n);
            buckets[index].add(num);
        }

        // Step 3: Sort each bucket
        for (List<Float> bucket : buckets)
            Collections.sort(bucket);

        // Step 4: Merge buckets into original array
        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket)
                arr[index++] = num;
        }
    }

    public static void main(String[] args) {
        float[] arr = {0.78f, 0.17f, 0.39f, 0.26f, 0.72f, 0.94f, 0.21f, 0.12f, 0.23f, 0.68f};
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));  // Output: sorted array
    }
}
```

---

## 🔥 Key Variants & Notes

- You can adapt Bucket Sort for **integers** by mapping to appropriate ranges.
- **Insertion Sort** is often used for small bucket sorting due to low overhead.
- Not stable by default, but can be made stable if needed.

---

Perfect choice! The LeetCode problem **[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)** is a classic — and it can be solved efficiently using **Bucket Sort**.

---

## 🔍 **Problem Summary**

> Given an integer array `nums` and an integer `k`, return the **k most frequent elements**.

---

## ✅ **Why Bucket Sort Works Here**

- Frequency count ranges from `1` to `n` (array length), so we can **bucket elements by their frequency**.
- Bucket index = frequency, and each bucket holds elements with that frequency.
- Finally, iterate **from high frequency to low** and collect top `k` elements.

---

## 🧑‍💻 Python Bucket Sort Solution
```python
from collections import defaultdict

def topKFrequent(nums, k):
    # Step 1: Count frequency
    freq_map = defaultdict(int)
    for num in nums:
        freq_map[num] += 1

    # Step 2: Create buckets: index = frequency, value = list of numbers
    n = len(nums)
    buckets = [[] for _ in range(n + 1)]
    for num, freq in freq_map.items():
        buckets[freq].append(num)

    # Step 3: Traverse buckets in reverse to get top k frequent
    result = []
    for freq in range(n, 0, -1):
        for num in buckets[freq]:
            result.append(num)
            if len(result) == k:
                return result
```

---

### ✅ Example

```python
nums = [1,1,1,2,2,3]
k = 2
print(topKFrequent(nums, k))  # Output: [1, 2]
```

---

## 🧠 Time & Space Complexity

- **Time**: O(n), since:
   - Counting = O(n)
   - Bucketing = O(n)
   - Collecting top k = O(n)
- **Space**: O(n) for frequency map + buckets

---

## Radix Sort




---------------------------------------------------------

## problems 

**Problem-1:** check the list if any element is duplicated or not

hint:
* brute-force with two loops  
* sorting technique

**Problem** Given an array , where each element of the array represents a vote in the elections. means this array contains list of chosen candidate IDs. find the candidate who wins the elections

hint:
* brute-force to find count for each candidate and update max count with candidate ID
* sorting technique and count for each Id 
* counting sort 
* using hashtable 

**Problem** Given two array of n elements and target value K. check whether a+b =k where a is from A and b is from B;

hint:
* using brute force
* using hashing technique 
* sort both the array and use binary search to find (k-a) in B


**Problem** if we have a telephone directory with 10 million entries which sorting is best


**Problem** nuts and bolts : given a set of nuts of different sizes and n bolts such that there is a one to one mapping only

Hint: 
* Brute force approach 

**Problem** You are given an m x n integer matrix matrix with the following two properties:
* Each row is sorted in non-decreasing order.
* The first integer of each row is greater than the last integer of the previous row.

![img_1.png](img_1.png)

**Problem** Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
* Integers in each row are sorted in ascending from left to right.
* Integers in each column are sorted in ascending from top to bottom.

![img.png](img.png)

**Problem** Sort elements of list by frequency
* list of objects {value, frequency} and sort the list based on frequency
* hashmap and sort by value


Here’s a list of the **most frequently asked LeetCode problems** that are related to **Merge Sort**, **Quick Sort**, and **Heap Sort** — commonly asked in **FAANG** and top tech interviews.

---

## **1. Merge Sort - Top LeetCode Problems**

| Problem | Topic | Company Tags |
|--------|-------|---------------|
| [148. Sort List](https://leetcode.com/problems/sort-list/) | Linked List + Merge Sort | Facebook, Amazon |
| [912. Sort an Array](https://leetcode.com/problems/sort-an-array/) | Array + Merge Sort (or Quick Sort) | Facebook |
| [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Merge + Heap (Min Heap) | Google, Amazon |
| [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Linked List Merge | Amazon |
| [315. Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/) | Merge Sort + Inversion Count | Google, Bloomberg |

---

## **2. Quick Sort - Top LeetCode Problems**

| Problem | Topic | Company Tags |
|--------|-------|---------------|
| [912. Sort an Array](https://leetcode.com/problems/sort-an-array/) | Quick Sort / Merge Sort | Facebook |
| [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Quick Select | Amazon, Google |
| [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) | Quick Select / Heap | Google |
| [280. Wiggle Sort](https://leetcode.com/problems/wiggle-sort/) | Sort + Partition | Amazon |
| [324. Wiggle Sort II](https://leetcode.com/problems/wiggle-sort-ii/) | Three-way QuickSort | Google |

---

## **3. Heap Sort / Heap - Top LeetCode Problems**

| Problem | Topic | Company Tags |
|--------|-------|---------------|
| [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Max Heap / Quick Select | Amazon |
| [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/) | Min Heap | Microsoft |
| [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Min & Max Heap | Google |
| [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Min Heap | Amazon |
| [1046. Last Stone Weight](https://leetcode.com/problems/last-stone-weight/) | Max Heap | Apple |
| [378. Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) | Min Heap | Facebook |
| [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Min Heap | Amazon |
| [621. Task Scheduler](https://leetcode.com/problems/task-scheduler/) | Max Heap + Greedy | Google |

---

Would you like me to categorize these further (like by level or frequency), or generate a practice plan using these?




