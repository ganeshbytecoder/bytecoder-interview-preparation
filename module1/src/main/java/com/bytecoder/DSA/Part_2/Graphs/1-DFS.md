//    MST -> PK -> P -> graph starts PriorityQueue 0 edge +  BFS and K -> PriorityQueue + all edges + DSU
//    note -> use only if you know graph is DAG for this (stack and DFS) else use kahn's algorithm (in-degree and queue

in the real world , many problems are representated in terms of objects and connections between them.
for example, airline routes, electric circuits , LAN and internet , facebook friends etc

## 📚 Graph Fundamentals

**Graph Definition:** A graph G = (V, E) where V is vertices and E is edges connecting them.

**Graph Types:**

- **Directed** vs **Undirected**
- **Weighted** vs **Unweighted**
- **Cyclic** vs **Acyclic** (DAG)
- **Connected** vs **Disconnected**

### Graph Representation

#### 1. Adjacency List (Preferred for sparse graphs)

```python
from collections import defaultdict

# Unweighted graph
graph = defaultdict(list)
for u, v in edges:
    graph[u].append(v)
    graph[v].append(u)  # For undirected

# Weighted graph
graph = defaultdict(list)
for u, v, weight in edges:
    graph[u].append((v, weight))
```

#### 2. Adjacency Matrix (Dense graphs)

```python
n = 5  # number of nodes
matrix = [[0] * n for _ in range(n)]
for u, v in edges:
    matrix[u][v] = 1
    matrix[v][u] = 1  # For undirected
```

#### 3. Visited Tracking

```python
visited = set()  # For node tracking
visited = [[False] * cols for _ in range(rows)]  # For grid/matrix
```

---

## 🎨 Core DFS Patterns

### Pattern 1: Basic DFS Traversal

**Use Cases:** Explore all nodes, detect connectivity, count components

**💡 Key Insight:** Go deep before going wide. Mark visited to avoid cycles.

**Time:** O(V + E) | **Space:** O(V) for recursion stack

#### Recursive Template:

```python
def dfs_recursive(node, graph, visited):
    if node in visited:
        return
    visited.add(node)
    # Process node here
    for neighbor in graph[node]:
        dfs_recursive(neighbor, graph, visited)

# Usage
graph = defaultdict(list)
visited = set()
dfs_recursive(start_node, graph, visited)
```

#### Iterative Template (Using Stack):

```python
def dfs_iterative(start, graph):
    stack = [start]
    visited = set([start])
  
    while stack:
        node = stack.pop()
        # Process node here
  
        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                stack.append(neighbor)
  
    return visited
```

---

### Pattern 6: Boundary DFS (Start from Edges)

**Use Cases:** Surrounded regions, enclaves, capture territories

**💡 Key Insight:** Start DFS from boundary cells. Mark reachable cells. Remaining cells are "surrounded".

**Time:** O(rows * cols) | **Space:** O(rows * cols)

```python
def surrounded_regions(board):
    """Capture surrounded regions (LC 130)"""
    if not board:
        return
  
    rows, cols = len(board), len(board[0])
  
    def dfs(r, c):
        if (r < 0 or r >= rows or c < 0 or c >= cols or
            board[r][c] != 'O'):
            return
  
        board[r][c] = 'T'  # Mark as temporary
        dfs(r + 1, c)
        dfs(r - 1, c)
        dfs(r, c + 1)
        dfs(r, c - 1)
  
    # Mark boundary-connected 'O's
    for r in range(rows):
        dfs(r, 0)
        dfs(r, cols - 1)
    for c in range(cols):
        dfs(0, c)
        dfs(rows - 1, c)
  
    # Capture surrounded regions and restore boundary-connected
    for r in range(rows):
        for c in range(cols):
            if board[r][c] == 'O':
                board[r][c] = 'X'  # Surrounded
            elif board[r][c] == 'T':
                board[r][c] = 'O'  # Restore
```

**FAANG Problems:**

- Surrounded Regions (LC 130)
- Number of Enclaves (LC 1020)
- Pacific Atlantic Water Flow (LC 417)

---

### Pattern 7: Clone Graph

**Use Cases:** Deep copy graph with pointers

**💡 Key Insight:** Use HashMap to map old nodes to new nodes. DFS to traverse and clone.

**Time:** O(V + E) | **Space:** O(V)

```python
class Node:
    def __init__(self, val=0, neighbors=None):
        self.val = val
        self.neighbors = neighbors if neighbors else []

def clone_graph(node):
    """Clone undirected graph (LC 133)"""
    if not node:
        return None
  
    old_to_new = {}
  
    def dfs(node):
        if node in old_to_new:
            return old_to_new[node]
  
        clone = Node(node.val)
        old_to_new[node] = clone
  
        for neighbor in node.neighbors:
            clone.neighbors.append(dfs(neighbor))
  
        return clone
  
    return dfs(node)
```

---

## 📋 FAANG DFS Problem List

## 🎯 Quick Reference Guide

### When to Use DFS

✅ **Use DFS when:**

- Finding all paths
- Detecting cycles
- Topological sorting
- Backtracking problems
- Exploring all possibilities
- Tree/graph traversal where order doesn't matter

❌ **Don't use DFS when:**

- Need shortest path (use BFS)
- Level-order processing needed
- Minimum steps required

---

### DFS vs BFS Comparison

| Feature                   | DFS                    | BFS                    |
| ------------------------- | ---------------------- | ---------------------- |
| **Data Structure**  | Stack (recursion)      | Queue                  |
| **Space**           | O(height)              | O(width)               |
| **Shortest Path**   | ❌ No                  | ✅ Yes (unweighted)    |
| **All Paths**       | ✅ Yes                 | ❌ No                  |
| **Cycle Detection** | ✅ Yes                 | ✅ Yes                 |
| **Memory**          | Better for deep graphs | Better for wide graphs |

---

### Common DFS Patterns Summary

| Pattern                        | Use Case           | Key Technique              |
| ------------------------------ | ------------------ | -------------------------- |
| **Basic Traversal**      | Visit all nodes    | Visited set                |
| **Connected Components** | Count groups       | DFS from each unvisited    |
| **Grid/Matrix**          | Island problems    | 4/8 directional DFS        |
| **Path Finding**         | All paths          | Backtracking               |
| **Weighted Path**        | Path constraints   | DFS + cumulative sum       |
| **Boundary DFS**         | Surrounded regions | Start from edges           |
| **Clone Graph**          | Deep copy          | HashMap mapping            |
| **Cycle Detection**      | Validate graph     | 3-color or parent tracking |

---

### Time Complexity Cheat Sheet

```python
# Graph with V vertices and E edges
DFS Traversal:           O(V + E)
Connected Components:    O(V + E)
Cycle Detection:         O(V + E)
All Paths (DAG):         O(2^V * V)
Grid DFS (m×n):          O(m * n)
Clone Graph:             O(V + E)
```

---

### Space Complexity Optimization

```python
# Standard: Use visited set
visited = set()  # O(V) space

# Optimized: Modify input (if allowed)
grid[r][c] = '0'  # Mark as visited

# For recursion depth issues:
import sys
sys.setrecursionlimit(10**6)  # Increase if needed

# Or use iterative DFS with explicit stack
```

---

## 💡 Interview Tips

1. **Always clarify:**

   - Directed or undirected?
   - Weighted or unweighted?
   - Can modify input?
   - Connected or disconnected?
2. **Handle edge cases:**

   - Empty graph
   - Single node
   - Disconnected components
   - Self-loops
3. **Optimize space:**

   - Can you modify input instead of visited set?
   - Iterative vs recursive for deep graphs
4. **Common mistakes:**

   - Forgetting to mark visited
   - Not handling disconnected graphs
   - Wrong parent tracking in undirected
   - Stack overflow in deep graphs
5. **Testing:**

   - Test with disconnected graph
   - Test with cycles
   - Test with single node
   - Test with empty graph

---

**Good luck with your FAANG interviews! 🚀**
