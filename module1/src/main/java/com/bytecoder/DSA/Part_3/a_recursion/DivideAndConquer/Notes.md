## **Divide and Conquer (D&C) Algorithm: Detailed Notes**

### **1. Introduction**
Divide and Conquer (D&C) is an algorithmic paradigm that solves a problem by recursively breaking it down into smaller subproblems, solving each subproblem independently, and then combining the solutions to solve the original problem.

### **2. Steps of Divide and Conquer**
1. **Divide:** Break the problem into smaller subproblems of the same type.
2. **Conquer:** Recursively solve the subproblems.
3. **Combine:** Merge the solutions of subproblems to get the final result.

### **3. Properties of Divide and Conquer**
- **Optimal Substructure:** The problem can be broken into smaller subproblems that can be solved independently.
- **Overlapping Subproblems (Optional):** Unlike Dynamic Programming, D&C does not necessarily store solutions to overlapping subproblems.
- **Recursion:** Uses recursive function calls to divide the problem.

### **4. Time Complexity Analysis**
The complexity of a D&C algorithm is often expressed using the recurrence relation:
\[
T(n) = aT\left(\frac{n}{b}\right) + O(f(n))
\]
where:
- \(a\) = number of subproblems
- \(b\) = factor by which problem size is reduced
- \(O(f(n))\) = cost of dividing and merging

Solving the recurrence using the **Master Theorem**:
1. If \( f(n) = O(n^c) \) where \( c < \log_b a \), then \( T(n) = O(n^{\log_b a}) \).
2. If \( f(n) = O(n^{\log_b a}) \), then \( T(n) = O(n^{\log_b a} \log n) \).
3. If \( f(n) = \Omega(n^c) \) where \( c > \log_b a \), then \( T(n) = O(f(n)) \).

---

## **5. Common Divide and Conquer Algorithms**
| Algorithm | Problem Type | Time Complexity |
|-----------|--------------|----------------|
| Merge Sort | Sorting | \( O(n \log n) \) |
| Quick Sort | Sorting | \( O(n \log n) \) (average case) |
| Binary Search | Searching | \( O(\log n) \) |
| Matrix Multiplication (Strassen’s Algorithm) | Multiplication | \( O(n^{2.81}) \) |
| Closest Pair of Points | Geometry | \( O(n \log n) \) |
| Karatsuba Algorithm | Fast Multiplication | \( O(n^{\log_2 3}) \approx O(n^{1.58}) \) |

---

## **6. LeetCode Problems on Divide and Conquer**
Here are some key problems categorized by difficulty:

### **🔹 Easy**
#### **1. [Binary Search](https://leetcode.com/problems/binary-search/)**
- **Concept:** Recursively search for an element in a sorted array.
- **Approach:**
  ```java
  class Solution {
      public int search(int[] nums, int target) {
          return binarySearch(nums, target, 0, nums.length - 1);
      }

      private int binarySearch(int[] nums, int target, int left, int right) {
          if (left > right) return -1;
          int mid = left + (right - left) / 2;
          if (nums[mid] == target) return mid;
          if (nums[mid] > target) return binarySearch(nums, target, left, mid - 1);
          return binarySearch(nums, target, mid + 1, right);
      }
  }
  ```
- **Complexity:** \( O(\log n) \)

---

### **🔹 Medium**
#### **2. [Sort an Array (Merge Sort)](https://leetcode.com/problems/sort-an-array/)**
- **Concept:** Recursively split the array and merge sorted subarrays.
- **Approach:**
  ```java
  class Solution {
      public int[] sortArray(int[] nums) {
          if (nums.length <= 1) return nums;
          mergeSort(nums, 0, nums.length - 1);
          return nums;
      }

      private void mergeSort(int[] nums, int left, int right) {
          if (left >= right) return;
          int mid = left + (right - left) / 2;
          mergeSort(nums, left, mid);
          mergeSort(nums, mid + 1, right);
          merge(nums, left, mid, right);
      }

      private void merge(int[] nums, int left, int mid, int right) {
          int[] temp = new int[right - left + 1];
          int i = left, j = mid + 1, k = 0;
          while (i <= mid && j <= right) {
              temp[k++] = (nums[i] <= nums[j]) ? nums[i++] : nums[j++];
          }
          while (i <= mid) temp[k++] = nums[i++];
          while (j <= right) temp[k++] = nums[j++];
          System.arraycopy(temp, 0, nums, left, temp.length);
      }
  }
  ```
- **Complexity:** \( O(n \log n) \)

---

#### **3. [Kth Largest Element in an Array (QuickSelect)](https://leetcode.com/problems/kth-largest-element-in-an-array/)**
- **Concept:** Use QuickSelect (based on QuickSort).
- **Approach:**
  ```java
  import java.util.Random;

  class Solution {
      private Random rand = new Random();

      public int findKthLargest(int[] nums, int k) {
          return quickSelect(nums, 0, nums.length - 1, nums.length - k);
      }

      private int quickSelect(int[] nums, int left, int right, int k) {
          if (left == right) return nums[left];
          int pivotIndex = partition(nums, left, right);
          if (pivotIndex == k) return nums[pivotIndex];
          else if (pivotIndex > k) return quickSelect(nums, left, pivotIndex - 1, k);
          else return quickSelect(nums, pivotIndex + 1, right, k);
      }

      private int partition(int[] nums, int left, int right) {
          int pivot = nums[right];
          int i = left;
          for (int j = left; j < right; j++) {
              if (nums[j] <= pivot) {
                  swap(nums, i, j);
                  i++;
              }
          }
          swap(nums, i, right);
          return i;
      }

      private void swap(int[] nums, int i, int j) {
          int temp = nums[i];
          nums[i] = nums[j];
          nums[j] = temp;
      }
  }
  ```
- **Complexity:** \( O(n) \) average case, \( O(n^2) \) worst case.

---

### **🔹 Hard**
#### **4. [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)**
- **Concept:** Use binary search to partition two sorted arrays optimally.
- **Approach:** Binary search on the smaller array.
- **Complexity:** \( O(\log (m + n)) \)

---

#### **5. [The Skyline Problem](https://leetcode.com/problems/the-skyline-problem/)**
- **Concept:** Use Divide and Conquer to merge building outlines.
- **Complexity:** \( O(n \log n) \)

---
