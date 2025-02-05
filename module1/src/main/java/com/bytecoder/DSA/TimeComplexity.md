Here’s the **Time and Space Complexity Chart** for **Sorting, Searching, Trees, Graphs, Backtracking, and Dynamic Programming**:

---

## **1. Sorting Algorithms**
| Algorithm        | Best Case  | Average Case | Worst Case  | Space Complexity |
|-----------------|-----------|-------------|------------|-----------------|
| **Bubble Sort**  | O(n)      | O(n²)       | O(n²)      | O(1) |
| **Selection Sort** | O(n²) | O(n²) | O(n²) | O(1) |
| **Insertion Sort** | O(n) | O(n²) | O(n²) | O(1) |
| **Merge Sort** | O(n log n) | O(n log n) | O(n log n) | O(n) |
| **Quick Sort** | O(n log n) | O(n log n) | O(n²) | O(log n) (recursive stack) |
| **Heap Sort** | O(n log n) | O(n log n) | O(n log n) | O(1) |
| **Counting Sort** | O(n + k) | O(n + k) | O(n + k) | O(k) |
| **Radix Sort** | O(nk) | O(nk) | O(nk) | O(n + k) |

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

> **Backtracking has exponential time complexity** since it explores multiple possibilities.

---

## **6. Dynamic Programming (DP)**
| Problem Type | Time Complexity | Space Complexity |
|-------------|----------------|------------------|
| Fibonacci (Memoization) | O(n) | O(n) |
| Fibonacci (Tabulation) | O(n) | O(1) |
| 0/1 Knapsack | O(nW) | O(nW) (O(W) with space optimization) |
| Longest Common Subsequence (LCS) | O(nm) | O(nm) (O(min(n, m)) with space optimization) |
| Matrix Chain Multiplication | O(n³) | O(n²) |
| Coin Change | O(n * amount) | O(amount) |
| Edit Distance | O(nm) | O(nm) |
| Partition Equal Subset Sum | O(n * sum) | O(sum) |

> **Dynamic Programming reduces exponential time complexity** to polynomial but may use extra space unless optimized.

---

This is **a complete Time & Space Complexity Chart** for **all major DSA topics**! 🚀

