Here’s the **Time and Space Complexity Chart** for **Sorting, Searching, Trees, Graphs, Backtracking, and Dynamic Programming**:
https://www.tpointtech.com/time-complexity-in-data-structure
---

## **1. Sorting Algorithms**
| Algorithm        | Best Case  | Average Case | Worst Case  | Space Complexity | Logic |
|-----------------|-----------|-------------|------------|-----------------|------------|
| **Bubble Sort**  | O(n)      | O(n²)       | O(n²)      | O(1) | Repeatedly swaps adjacent elements if they are in the wrong order. Inefficient for large datasets. |
| **Selection Sort** | O(n²) | O(n²) | O(n²) | O(1) | Finds the minimum element and swaps it with the first unsorted element. Always performs O(n²) comparisons. |
| **Insertion Sort** | O(n) | O(n²) | O(n²) | O(1) | Builds the sorted array one element at a time by shifting elements. Efficient for nearly sorted data. |
| **Merge Sort** | O(n log n) | O(n log n) | O(n log n) | O(n) | Divides the array into halves, sorts recursively, and merges them. Stable sorting with additional space usage. |
| **Quick Sort** | O(n log n) | O(n log n) | O(n²) | O(log n) (recursive stack) | Picks a pivot, partitions elements, then recursively sorts the partitions. Fast but worst case occurs with poor pivot choice. |
| **Heap Sort** | O(n log n) | O(n log n) | O(n log n) | O(1) | Uses a binary heap structure to extract the largest element repeatedly. Not stable but good for in-place sorting. |
| **Counting Sort** | O(n + k) | O(n + k) | O(n + k) | O(k) | Counts occurrences of elements and reconstructs the sorted array. Efficient for small integer ranges but uses extra space. |
| **Radix Sort** | O(nk) | O(nk) | O(nk) | O(n + k) | Sorts numbers digit by digit using Counting Sort as a subroutine. Works well for fixed-length integers. |

---

## **2. Searching Algorithms**
| Algorithm        | Best Case | Average Case | Worst Case | Space Complexity |
|-----------------|----------|-------------|------------|------------------|
| **Linear Search** | O(1) | O(n) | O(n) | O(1) |
| **Binary Search** | O(1) | O(log n) | O(log n) | O(1) |

---

## **3. Data Structures Operations**
### **Array Operations**
| Operation       | Time Complexity | Space Complexity |
|----------------|----------------|------------------|
| Access        | O(1) | O(1) |
| Search        | O(n) | O(1) |
| Insert (at end) | O(1) | O(1) |
| Insert (at start or middle) | O(n) | O(1) |
| Delete (at end) | O(1) | O(1) |
| Delete (at start or middle) | O(n) | O(1) |

### **Linked List Operations**
| Operation      | Singly Linked List | Doubly Linked List | Space Complexity |
|---------------|--------------------|--------------------|------------------|
| Access       | O(n) | O(n) | O(n) |
| Search       | O(n) | O(n) | O(n) |
| Insert at Head | O(1) | O(1) | O(1) |
| Insert at Tail | O(1) | O(1) | O(1) |
| Insert in Middle | O(n) | O(n) | O(1) |
| Delete at Head | O(1) | O(1) | O(1) |
| Delete at Tail | O(n) | O(1) | O(1) |
| Delete in Middle | O(n) | O(n) | O(1) |

### **Stack & Queue Operations**
| Operation | Stack (LIFO) | Queue (FIFO) | Deque | Space Complexity |
|-----------|-------------|-------------|------|------------------|
| Push (insert) | O(1) | O(1) | O(1) | O(1) |
| Pop (remove) | O(1) | O(1) | O(1) | O(1) |
| Peek (top/front) | O(1) | O(1) | O(1) | O(1) |

---

## **4. Trees & Graphs**
### **Binary Search Tree (BST)**
| Operation | Best Case | Average Case | Worst Case | Space Complexity |
|-----------|----------|-------------|------------|------------------|
| Search   | O(log n) | O(log n) | O(n) | O(n) |
| Insert   | O(log n) | O(log n) | O(n) | O(n) |
| Delete   | O(log n) | O(log n) | O(n) | O(n) |

### **Binary Tree (DFS & BFS)**
| Algorithm | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| BFS (Level Order Traversal) | O(n) | O(n) |
| DFS (Preorder, Inorder, Postorder) | O(n) | O(h) (h = height of tree, O(log n) for balanced, O(n) for skewed) |

### **Graph Algorithms**
| Algorithm | Best Case | Average Case | Worst Case | Space Complexity |
|-----------|----------|-------------|------------|------------------|
| BFS (Adjacency List) | O(V + E) | O(V + E) | O(V + E) | O(V + E) |
| DFS (Adjacency List) | O(V + E) | O(V + E) | O(V + E) | O(V + E) |
| Dijkstra’s Algorithm (Priority Queue) | O(V log V + E log V) | O(V log V + E log V) | O(V²) | O(V + E) |
| Bellman-Ford Algorithm | O(VE) | O(VE) | O(VE) | O(V) |
| Floyd-Warshall Algorithm | O(V³) | O(V³) | O(V³) | O(V²) |
| Kruskal’s Algorithm | O(E log E) | O(E log E) | O(E log E) | O(E) |
| Prim’s Algorithm | O(E log V) | O(E log V) | O(V²) | O(V + E) |

---

## **5. Backtracking Algorithms**
| Algorithm | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| N-Queens | O(n!) | O(n²) (for board) |
| Sudoku Solver | O(9^(n²)) | O(n²) |
| Subset Sum | O(2^n) | O(n) |
| Permutations | O(n!) | O(n) |
| Combination Sum | O(2^n) | O(n) |

| Permutations | O(K^n) | O(n) | if you allow repetition (reuse of elements) in  the
 
Distinct ways/Fibonacci : O(2^n)
word search for start point given O( 4^L)
Suppose you have 𝑛 positions to fill and each position has K choices. then O(K^n)


word search for start point not given O(m * n * 4^L)

coin exchange  - nlog(n)


> **Backtracking has exponential time complexity** since it explores multiple possibilities.

---

## **6. Dynamic Programming (DP)**
| Problem Type | Time Complexity | Space Complexity |
|-------------|-----------------|------------------|
| Fibonacci (Memoization) | O(n)            | O(n) | 
| Fibonacci (Tabulation) | O(n)            | O(1) |
| 0/1 Knapsack | O(nW)           | O(nW) (O(W) with space optimization) |
| Longest Common Subsequence (LCS) | O(nm)           | O(nm) (O(min(n, m)) with space optimization) |
| Matrix Chain Multiplication | O(n³)           | O(n²) |
| Coin Change | O(n * log(n)    | O(amount) |
| Edit Distance | O(nm)           | O(nm) |
| Partition Equal Subset Sum | O(n * sum)      | O(sum) |

> **Dynamic Programming reduces exponential time complexity** to polynomial but may use extra space unless optimized.

---
Here's a structured explanation of these common recursion patterns clearly explaining the time complexity analysis and typical scenarios:

---

## ✅ **Pattern 1: Take / Not-take Pattern**

**Explanation:**
- In this pattern, you make a binary decision at each recursive step:
    - **Take** the current element.
    - **Not take** the current element.
- Commonly used in subset or subsequence problems.

**Example Code:**
```python
def dfs(nums, index):
    if index == len(nums):
        return
    
    # Take current element
    dfs(nums, index + 1)

    # Not take current element
    dfs(nums, index + 1)
```

**Time Complexity:**
- **O(2^n)**, as each element has 2 possibilities (take/not-take).

**Space Complexity:**
- **O(n)**, recursion stack depth.

**Example Problems:**
- **Subsets**, **Subsequence**, **Knapsack (0/1)**

---

## ✅ **Pattern 2: Recursive Loop (Sequential Choices)**

**Explanation:**
- At each recursive call, iterate through the rest of the elements.
- Commonly used when generating combinations, permutations, or subsequences where ordering or position matters.

```python
def dfs(nums, index):
    for i in range(index, len(nums)):
        dfs(nums, i + 1)
```

**Time Complexity:**
- Generally **O(2^n)**, because it explores all subsets recursively, but explicitly visits each combination by recursive calls.

**Space Complexity:**
- **O(n)** (recursion stack)

**Example Problems:**
- **Combination Sum**, **Generate Subsets**

---

## ✅ **Pattern 2: Permutation Pattern**

**Explanation:**
- This recursive pattern involves choosing each element as the "start" of a permutation and recursively permuting remaining elements.
- Usually involves marking visited/unvisited status clearly.

```python
def dfs(nums, path):
    if len(path) == len(nums):
        # found permutation
        return
    for num in nums:
        if num not in path:
            dfs(nums, path + [num])
```

**Time Complexity:**
- **O(n × n!)**, generating all permutations.

**Space Complexity:**
- **O(n)** recursion depth.

**Example Problems:**
- **Permutations**, **N-Queens problem**

---

## ✅ **Pattern 3: DFS for combinations with Backtracking and an Index**

**Explanation:**
- Similar to Pattern 1, but slightly modified: choosing an element explicitly from a specific start index, avoiding repeated combinations.
- Crucially used when **order does NOT matter** (to avoid duplicate sets).

```python
def dfs(nums, index, path):
    # base case: return if condition met
    for i in range(index, len(nums)):
        dfs(nums, i + 1, path + [nums[i]])
```

**Time Complexity:**
- **O(2^n)** subsets generation complexity.
- Note: It is efficient compared to permutations because it does not explore repeated subsets.

**Space Complexity:**
- **O(n)**

**Example Problems:**
- Combination Sum, Combination Sum II

---

## ✅ **Pattern 4: Multiple Choices (coin/change)**

In certain recursive patterns like **coin-change problems**, each recursive call explores multiple branches corresponding to different coin values:

```python
def dfs(amount, coins, index):
    if amount == 0: return 1
    if amount < 0: return 0
    ways = 0
    for i in range(index, len(coins)):
        ways += dfs(amount - coins[i], coins, i) # reuse same coin
```

**Time Complexity:**
- **Exponential (O(m^n))** without memoization (where m = coins, n = amount).

**With memoization/dynamic programming:**
- Complexity reduces to **O(amount × coins)**.

---

## 🔑 **Summary of Common Recursive Patterns:**

| Pattern              | Complexity            | Example Applications                |
|----------------------|-----------------------|-------------------------------------|
| Take/Not-take        | **O(2^n)**            | Subsets, subsequences, 0/1 Knapsack |
| For-loop recursion   | O(2^n) or O(n!)       | Permutations, combinations          |
| Permutations         | O(n × n!)             | Generating permutations             |
| Coin-change (naive)  | Exponential (O(m^n))  | Coin Change                         |
| Coin-change (DP)     | O(amount × coins)     | Optimized Coin Change (DP)          |

---
