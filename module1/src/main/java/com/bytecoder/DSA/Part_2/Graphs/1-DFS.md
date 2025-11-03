# 📚 Graph Fundamentals

**Graph Definition:** A graph G = (V, E) where V is vertices and E is edges connecting them.

**Graph Types:**

- **Directed** vs **Undirected**
- **Weighted** vs **Unweighted**
- **Cyclic** vs **Acyclic** (DAG)
- **Connected** vs **Disconnected**

### Graph Representation

#### 1. Adjacency List (Preferred for sparse graphs)

```python
# Unweighted graph
graph = {}

for u, v in edges:
    if u not in graph:
        graph[u] = []
    if v not in graph:
        graph[v] = []
  
    graph[u].append(v)
    graph[v].append(u)  # For undirected
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




**Flood Fill** (LC 733)

- Basic BFS/DFS on grid
- Time: O(m × n) | Space: O(m × n)

---

**Number of Islands** (LC 200) ⭐⭐⭐

- Grid BFS, connected components
- Time: O(m × n) | Space: O(min(m, n))
- **Real-world:** Flood-fill algorithms in image processing



**Number of Connected Components** (LC 323) ⭐

- Basic component counting
- Time: O(V + E) | Space: O(V)

1. **Find Center of Star Graph** (LC 1791)

- Graph property check or In-degree/out-degree analysis
- Time: O(1) | Space: O(1)

3. **Find the Town Judge** (LC 997)

   - In-degree/out-degree analysis
   - Time: O(E) | Space: O(V)
