

# ✅ **Sorting Algorithms – Ultimate Revision Notes**

---

## 🔁 1. **Bubble Sort**
- **Concept**: Repeatedly swap adjacent elements if out of order.
- **Time**:
    - Best: O(n) (already sorted)
    - Worst: O(n²)
- **Space**: O(1)
- **Stable**: ✅
- **Use When**: Simple or nearly sorted arrays.

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



---

## 📍 2. **Selection Sort**
- **Concept**: Find the min element from the unsorted part and place it at the beginning.
- **Time**: O(n²) in all cases
- **Space**: O(1)
- **Stable**: ❌
- **Use When**: Simplicity > performance.

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


## 🃏 3. **Insertion Sort** ("Taash logic")
- **Concept**: Insert each element into its correct position in the sorted part of the array.
- **Time**:
    - Best: O(n)
    - Worst: O(n²)
- **Space**: O(1)
- **Stable**: ✅
- **Use When**: Nearly sorted, small datasets.

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

---

## 📦 4. **Merge Sort**
- **Concept**: Divide → Sort halves → Merge.
- **Time**: O(n log n) in all cases
- **Space**: O(n)
- **Stable**: ✅
- **Use When**: Linked lists, guaranteed performance.

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



## ⚡ 5. **Quick Sort**
- **Concept**: Pick pivot → Partition → Recur on halves.
- **Time**:
    - Best/Avg: O(n log n)
    - Worst: O(n²) (bad pivot)
- **Space**: O(log n)
- **Stable**: ❌
- **Use When**: Fast in-place sort.

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


---



## 🏔️ 6. **Heap Sort**
- **Concept**: Build a max-heap → Swap max with end → Heapify.
- **Time**: O(n log n)
- **Space**: O(1)
- **Stable**: ❌
- **Use When**: In-place, consistent time, no recursion.

```python
def heap_sort(arr):
    n = len(arr)
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]
        heapify(arr, i, 0)
```

---

### 🔧 `heapq` (Python Min Heap Library)

| Method | Description |
|--------|-------------|
| `heapq.heapify(arr)` | In-place min-heap |
| `heappush(heap, x)` | Add element |
| `heappop(heap)` | Pop smallest |
| `nlargest(k, iterable)` | k largest |
| `nsmallest(k, iterable)` | k smallest |

---
## ✅ **Heap Sort in Python (Manual Implementation using Max-Heap Logic)**

```python
def heap_sort(arr):
    n = len(arr)

    # Step 1: Build max heap
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)

    # Step 2: Extract elements one by one from the heap
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]  # swap max element to end
        heapify(arr, i, 0)  # restore heap property for reduced heap

def heapify(arr, size, root):
    largest = root
    left = 2 * root + 1
    right = 2 * root + 2

    if left < size and arr[left] > arr[largest]:
        largest = left
    if right < size and arr[right] > arr[largest]:
        largest = right

    if largest != root:
        arr[root], arr[largest] = arr[largest], arr[root]
        heapify(arr, size, largest)

# Example usage
arr = [4, 10, 3, 5, 1]
heap_sort(arr)
print(arr)  # Output: [1, 3, 4, 5, 10]
```

---
## 🧰 **Python Built-in Heap: `heapq` Module**

The `heapq` module provides a **Min-Heap** by default. It allows **efficient priority queue operations** in O(log n) time.

---

### 📦 `heapq` – Common Methods

```python
import heapq
```

| Method | Description |
|--------|-------------|
| `heapq.heapify(lst)` | Converts a list into a min-heap in-place (O(n)) |
| `heapq.heappush(heap, item)` | Adds an item to the heap (O(log n)) |
| `heapq.heappop(heap)` | Pops and returns the smallest item (O(log n)) |
| `heapq.heappushpop(heap, item)` | Push then pop (faster than separate ops) |
| `heapq.heapreplace(heap, item)` | Pops then pushes (always replaces root) |
| `heapq.nlargest(k, iterable)` | Returns k largest elements |
| `heapq.nsmallest(k, iterable)` | Returns k smallest elements |

---

### ✅ Example Usage

```python
import heapq

arr = [5, 1, 3, 10, 4]

heapq.heapify(arr)
print(arr)  # Heapified array: [1, 4, 3, 10, 5]

heapq.heappush(arr, 0)
print(heapq.heappop(arr))  # Output: 0

# Get 2 largest elements
print(heapq.nlargest(2, arr))  # Output: [10, 5]

# Get 2 smallest elements
print(heapq.nsmallest(2, arr))  # Output: [1, 3]
```

---

### ✅ Common Queue Methods `PriorityQueue` Supports:
```java
public static void main(String[] args) {

    Queue<Integer> queue = new LinkedList<>();


    List<Integer> list = Arrays.asList(3, 1, 4, 2);
    PriorityQueue<Integer> pq = new PriorityQueue<>(list);


    List<Integer> list = Arrays.asList(3, 1, 4, 2);
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    maxHeap.addAll(list);
}

```



| Method         | Description |
|----------------|-------------|
| `offer(E e)`   | Inserts an element (returns `false` if it fails) |
| `add(E e)`     | Same as `offer()`, but throws exception on failure |
| `peek()`       | Retrieves but does not remove the head (min by default) |
| `poll()`       | Retrieves and removes the head |
| `remove()`     | Removes the head (throws exception if empty) |
| `isEmpty()`    | Checks if the queue is empty |
| `size()`       | Returns number of elements |

---

### MedianFinder

```java
import java.util.PriorityQueue;
import java.util.Collections;

public class MedianFinder {

    // Max-heap for the smaller half
    private PriorityQueue<Integer> left;

    // Min-heap for the larger half
    private PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
        right = new PriorityQueue<>(); // min-heap
    }

    public void addNum(int num) {
        // Add to max-heap first
        if (!left.isEmpty() && num < left.peek()) {
            left.offer(num);
        } else {
            right.offer(num);
        }

        // Balance the heaps
        if (left.size() > right.size() + 1) {
            right.offer(left.poll());
        } else if (right.size() > left.size() + 1) {
            left.offer(right.poll());
        }
    }

    public double findMedian() {
        if (left.size() > right.size()) {
            return left.peek();
        } else if (right.size() > left.size()) {
            return right.peek();
        } else {
            return (left.peek() + right.peek()) / 2.0;
        }
    }
}

```

```python 
import heapq

class MedianFinder:

    def __init__(self):
        self.left = []   # Max heap (as negative numbers)
        self.right = []  # Min heap

    def addNum(self, num: int) -> None:
        if self.left and -self.left[0] > num:
            heapq.heappush(self.left, -num)
        else:
            heapq.heappush(self.right, num)

        # Balance the heaps only if their sizes differ by more than 1
        if len(self.left) > len(self.right) + 1:
            heapq.heappush(self.right, -heapq.heappop(self.left))
        elif len(self.right) > len(self.left) + 1:
            heapq.heappush(self.left, -heapq.heappop(self.right))

    def findMedian(self) -> float:
        if len(self.left) > len(self.right):
            return -self.left[0]
        elif len(self.right) > len(self.left):
            return self.right[0]
        return (-self.left[0] + self.right[0]) / 2



```



---
















## 📥 7. **Bucket Sort**
- **Concept**: Distribute into buckets → Sort → Merge.
- **Best For**: Uniform float values in [0, 1)
- **Time**:
    - Best: O(n)
    - Worst: O(n²) (bad distribution)
- **Space**: O(n + k)
- **Stable**: ❌ (but can be)

```python
def bucket_sort(arr):
    n = len(arr)
    buckets = [[] for _ in range(n)]
    for num in arr:
        index = int(n * num)
        buckets[index].append(num)
    for b in buckets:
        b.sort()
    return [x for bucket in buckets for x in bucket]
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


## 🔥 Key Variants & Notes

- You can adapt Bucket Sort for **integers** by mapping to appropriate ranges.
- **Insertion Sort** is often used for small bucket sorting due to low overhead.
- Not stable by default, but can be made stable if needed.

---
Great question! The reason **Bucket Sort is best suited for floats in the [0, 1) range** is because of how the algorithm distributes values **evenly across buckets** — which directly impacts its performance.

---

## 🔍 Let’s break it down:

### ✅ 1. **Uniform Distribution Makes Buckets Effective**
- In Bucket Sort, we map elements to buckets based on their value.
- For floats in `[0, 1)`, each value `x` can be mapped using `int(x * n)` where `n = number of buckets`.
- This ensures:
    - `x ∈ [0, 1)` → index ∈ `[0, n-1]`
    - Values are **evenly distributed** if they’re uniform ⇒ small buckets ⇒ fast sort.

> 📌 Example:  
> `x = 0.72` in array of size 10  
> `index = int(0.72 * 10) = 7`  
> Goes into bucket 7.

---

### ✅ 2. **Predictable Range = Simple Indexing**
- All values are guaranteed to lie within `[0, 1)`, so you don’t need to normalize or scale.
- That makes it easy to assign to buckets without extra logic.

---

### ✅ 3. **Smaller Subproblems = Faster Sorting**
- If values are evenly distributed, each bucket has **few elements**.
- Sorting them individually (e.g., with insertion sort) becomes nearly **O(1)** per bucket.

---

## 🚫 What if values aren’t in [0, 1)?

If values are:
- Spread unevenly (e.g., `[1, 1000, 1000000]`):  
  ⇒ **Bucket imbalance** (most values go into one bucket)
  ⇒ Performance degrades to **O(n²)** in worst case.

You’d need to:
- **Normalize values**: Bring them into `[0, 1)` range
- Or use another algorithm (like Quick Sort or Heap Sort)

---

## ✅ Summary

| Reason                     | Benefit                            |
|---------------------------|-------------------------------------|
| Uniform float distribution| Even bucket sizes (faster sort)     |
| [0, 1) range               | Simple index mapping (`x * n`)      |
| Small bucket sizes         | Efficient with O(n + k) performance |

---

Absolutely! Here's the updated **Python implementation for Bucket Sort on integers**, **with time and space complexity analysis** included.

---

## ✅ **Python Code: Bucket Sort for Integers**
```python
def bucket_sort_integers(arr, bucket_size=5):
    if len(arr) == 0:
        return []

    # Step 1: Find min and max
    min_val, max_val = min(arr), max(arr)

    # Step 2: Calculate number of buckets
    bucket_count = (max_val - min_val) // bucket_size + 1
    buckets = [[] for _ in range(bucket_count)]

    # Step 3: Distribute elements into buckets
    for num in arr:
        index = (num - min_val) // bucket_size
        buckets[index].append(num)

    # Step 4: Sort each bucket and concatenate
    sorted_array = []
    for bucket in buckets:
        sorted_array.extend(sorted(bucket))  # Replace with insertion sort if needed

    return sorted_array

# ✅ Example
arr = [42, 32, 33, 52, 37, 47, 51]
sorted_arr = bucket_sort_integers(arr)
print(sorted_arr)  # Output: [32, 33, 37, 42, 47, 51, 52]
```

---

## ⏱️ **Time and Space Complexity**

### **Time Complexity:**
Let:
- `n` = number of elements in the input array
- `k` = number of buckets
- `b` = average number of elements per bucket = `n/k`

| Step                          | Complexity     |
|------------------------------|----------------|
| Finding min/max              | O(n)           |
| Bucketing elements           | O(n)           |
| Sorting buckets (individually) | O(k * b log b) → worst case: O(n log n) |
| Concatenating buckets        | O(n)           |
| **Total (best/avg)**         | O(n + n log(n/k)) |
| **Worst case (if all elements fall into one bucket)** | O(n log n) |

### **Space Complexity:**
- Buckets = O(n)
- Final sorted array = O(n)
- **Total = O(n)**

---

## 📌 Summary

| Scenario     | Time Complexity |
|--------------|------------------|
| Best Case    | O(n + k)         |
| Average Case | O(n + n log(n/k))|
| Worst Case   | O(n log n)       |
| Space        | O(n)             |

---

Let me know if you'd like to:
- Replace built-in sort with **insertion sort** for teaching purposes,
- See the **Java version**,
- Or try it with **negative integers**!





## 🔠 8. **Radix Sort**
- **Concept**: Sort digits from least to most significant using Counting Sort.
- **Time**: O(n * k), where k = digit length
- **Space**: O(n + k)
- **Stable**: ✅
- **Use When**: Fixed-length integers, phone numbers, IDs.

---

## 🔢 9. **Counting Sort**
- **Concept**: Count occurrences → Rebuild sorted array.
- **Time**: O(n + k)
- **Space**: O(k)
- **Stable**: ✅
- **Use When**: Small integer range.

---

## Frequency based Sorting (Top K Frequent)


Perfect choice! The LeetCode problem **[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)** is a classic — and it can be solved efficiently using **Bucket Sort**.

---

### 🧑‍💻 Python Bucket Sort Solution
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



-----




## problems 


**Problem** Given an array , where each element of the array represents a vote in the elections. means this array contains list of chosen candidate IDs. find the candidate who wins the elections



**Problem** Given two array of n elements and target value K. check whether a+b =k where a is from A and b is from B;


**Problem** if we have a telephone directory with 10 million entries which sorting is best


**Problem** nuts and bolts : given a set of nuts of different sizes and n bolts such that there is a one to one mapping only


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


## **Other problems**

| Problem | Topic | Company Tags |
|--------|-------|---------------|
| [148. Sort List](https://leetcode.com/problems/sort-list/) | Linked List + Merge Sort | Facebook, Amazon |
| [912. Sort an Array](https://leetcode.com/problems/sort-an-array/) | Array + Merge Sort (or Quick Sort) | Facebook |
| [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Merge + Heap (Min Heap) | Google, Amazon |
| [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) | Linked List Merge | Amazon |
| [315. Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/) | Merge Sort + Inversion Count | Google, Bloomberg |
| [912. Sort an Array](https://leetcode.com/problems/sort-an-array/) | Quick Sort / Merge Sort | Facebook |
| [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Quick Select | Amazon, Google |
| [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) | Quick Select / Heap | Google |
| [280. Wiggle Sort](https://leetcode.com/problems/wiggle-sort/) | Sort + Partition | Amazon |
| [324. Wiggle Sort II](https://leetcode.com/problems/wiggle-sort-ii/) | Three-way QuickSort | Google |
| [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Max Heap / Quick Select | Amazon |
| [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/) | Min Heap | Microsoft |
| [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) | Min & Max Heap | Google |
| [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) | Min Heap | Amazon |
| [1046. Last Stone Weight](https://leetcode.com/problems/last-stone-weight/) | Max Heap | Apple |
| [378. Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) | Min Heap | Facebook |
| [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Min Heap | Amazon |
| [621. Task Scheduler](https://leetcode.com/problems/task-scheduler/) | Max Heap + Greedy | Google |





