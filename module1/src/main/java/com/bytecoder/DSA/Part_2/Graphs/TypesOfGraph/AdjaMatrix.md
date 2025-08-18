# 📁 Graph Representations: Edge List, Adjacency List, Adjacency Matrix, Grid (Matrix)

---

## ✅ Edge List Representation

### ▪️ What is an Edge List?
- A graph is represented as a **list of edges**.
- Each edge is either:
    - **Unweighted**: `(u, v)` — simple connection.
    - **Weighted**: `(u, v, w)` — connection with weight/cost.
- This is an **implicit representation** — not suitable for direct traversal without building an adjacency list.

### ▪️ Edge List to Adjacency List (Unweighted)
```python
edges = [(0, 1), (1, 2), (2, 3)]
from collections import defaultdict
adj = defaultdict(list)
for u, v in edges:
    adj[u].append(v)
    adj[v].append(u)  # if undirected
```

### ▪️ Edge List to Adjacency List (Weighted)
```python
edges = [(0, 1, 5), (1, 2, 3), (2, 3, 2)]
adj = defaultdict(list)
for u, v, w in edges:
    adj[u].append((v, w))
    adj[v].append((u, w))  # if undirected
```

### ▪️ When to Use Edge List
| Use Case | Why |
|----------|-----|
| Sorting by weight | Great for Kruskal’s MST |
| Raw input format | Many problems give edges directly |
| Space efficient | Stores only actual edges `O(E)` |

---

## ✅ Adjacency List Representation

### ▪️ What is an Adjacency List?
- Each node has a list of its connected neighbors.
- Efficient for **sparse graphs**.

### ▪️ Structure
```python
adj = {
    0: [1, 2],
    1: [0, 3],
    2: [0],
    3: [1]
}
```

### ▪️ Benefits
| Feature | Benefit |
|--------|---------|
| Space | `O(V + E)` — efficient for sparse graphs |
| Access | Fast access to neighbors |
| Flexibility | Can easily represent weighted or directed edges |

---

## ✅ Adjacency Matrix Representation

### ▪️ What is an Adjacency Matrix?
- A `n x n` matrix `adj[i][j]` where:
    - `1` (or weight) = edge from node `i` to `j`
    - `0` = no edge

### ▪️ Characteristics
- Good for **dense graphs**.
- Constant time edge checks: `adj[i][j] == 1`.

### ▪️ BFS (Adjacency Matrix)
```python
from collections import deque

def bfs(adj, start):
    visited = [False]*len(adj)
    queue = deque([start])
    visited[start] = True
    while queue:
        node = queue.popleft()
        print(node, end=' ')
        for i in range(len(adj)):
            if adj[node][i] == 1 and not visited[i]:
                visited[i] = True
                queue.append(i)
```

### ▪️ DFS (Adjacency Matrix)
```python
def dfs(adj, node, visited):
    visited[node] = True
    print(node, end=' ')
    for i in range(len(adj)):
        if adj[node][i] == 1 and not visited[i]:
            dfs(adj, i, visited)
```

---

## ✅ Grid as Graph (Matrix / Implicit Graph)

### ▪️ What is a Matrix Graph?
- Treats a **2D grid as an implicit graph**.
- Each cell `(i, j)` is a node.
- Neighbors = adjacent cells (up/down/left/right or diagonals).

### ▪️ Matrix BFS Template
```python
from collections import deque

def bfs(matrix, start):
    rows, cols = len(matrix), len(matrix[0])
    visited = [[False]*cols for _ in range(rows)]
    directions = [(-1,0), (1,0), (0,-1), (0,1)]
    queue = deque([start])
    visited[start[0]][start[1]] = True
    while queue:
        r, c = queue.popleft()
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
            if 0 <= nr < rows and 0 <= nc < cols:
                if not visited[nr][nc] and matrix[nr][nc] == 1:
                    queue.append((nr, nc))
                    visited[nr][nc] = True
```

### ▪️ Matrix DFS Template
```python
def dfs(matrix, r, c, visited):
    rows, cols = len(matrix), len(matrix[0])
    if r < 0 or r >= rows or c < 0 or c >= cols:
        return
    if visited[r][c] or matrix[r][c] != 1:
        return
    visited[r][c] = True
    directions = [(-1,0), (1,0), (0,-1), (0,1)]
    for dr, dc in directions:
        dfs(matrix, r+dr, c+dc, visited)
```

---

## 🔍 Matrix vs Adjacency Matrix: How to Distinguish

| Clue | Grid Matrix (2D Array) | Adjacency Matrix |
|------|------------------------|------------------|
| Purpose | Spatial layout | Graph connections |
| Meaning of A[i][j] | Value at cell | Edge from node i → j |
| Traversal | Move up/down/left/right | Traverse based on connectivity |
| Typical use case | Pathfinding, Flood Fill | Connectivity, shortest paths |

---

## 🔗 LeetCode Examples

### ▪️ Edge List
- [684. Redundant Connection](https://leetcode.com/problems/redundant-connection/)
- [261. Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/)
- [1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/)
- [1135. Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/)
- [785. Is Graph Bipartite?](https://leetcode.com/problems/is-graph-bipartite/)

### ▪️ Adjacency Matrix
- [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/)
- [261. Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/)
- [323. Number of Connected Components](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/)

### ▪️ Grid as Graph
- [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)
- [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)
- [542. 01 Matrix](https://leetcode.com/problems/01-matrix/)
- [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)
- [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island/)
- [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions/)
- [733. Flood Fill](https://leetcode.com/problems/flood-fill/)

---




# 📁 Graph Representations: Edge List, Adjacency Matrix, Grid (Matrix)

---

## ✅ Edge List Graph (Concise Notes)

### ▪️ What is an Edge List?
- Graph represented as a list of edges.
- Edge forms:
    - Unweighted: `(u, v)`
    - Weighted: `(u, v, w)`
- Implicit representation. Build adjacency list to use BFS/DFS.

### ▪️ Unweighted Example
```python
edges = [(0, 1), (1, 2), (2, 3)]
from collections import defaultdict
adj = defaultdict(list)
for u, v in edges:
    adj[u].append(v)
    adj[v].append(u)  # if undirected
```

### ▪️ Weighted Example
```python
edges = [(0, 1, 5), (1, 2, 3)]
adj = defaultdict(list)
for u, v, w in edges:
    adj[u].append((v, w))
    adj[v].append((u, w))  # if undirected
```

### ▪️ BFS (Unweighted)
```python
from collections import deque
def bfs(start, adj):
    visited = set()
    queue = deque([start])
    visited.add(start)
    while queue:
        node = queue.popleft()
        print(node, end=' ')
        for neighbor in adj[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)
```

### ▪️ DFS Recursive (Unweighted)
```python
def dfs(node, adj, visited):
    visited.add(node)
    print(node, end=' ')
    for neighbor in adj[node]:
        if neighbor not in visited:
            dfs(neighbor, adj, visited)
```

### ▪️ DFS Iterative (Unweighted)
```python
def dfs_iterative(start, adj):
    visited = set()
    stack = [start]
    while stack:
        node = stack.pop()
        if node not in visited:
            visited.add(node)
            print(node, end=' ')
            for neighbor in reversed(adj[node]):
                if neighbor not in visited:
                    stack.append(neighbor)
```

### 🔗 LeetCode Examples (Edge List)
| Problem | Type | Concept |
|--------|------|---------|
| [684. Redundant Connection](https://leetcode.com/problems/redundant-connection/) | Unweighted | Union-Find |
| [261. Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/) | Unweighted | BFS/DFS |
| [1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) | Weighted | Kruskal’s MST |
| [1135. Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/) | Weighted | Kruskal MST |
| [785. Is Graph Bipartite?](https://leetcode.com/problems/is-graph-bipartite/) | Unweighted | BFS/DFS |

---

## ✅ Adjacency Matrix Graph

### ▪️ Definition
- 2D array `adj[i][j]` indicates edge from node `i` to `j`.
- Size: `n x n` where `n` = number of nodes.

### ▪️ BFS
```python
from collections import deque
def bfs(adj, start):
    visited = [False]*len(adj)
    queue = deque([start])
    visited[start] = True
    while queue:
        node = queue.popleft()
        print(node, end=' ')
        for i in range(len(adj)):
            if adj[node][i] == 1 and not visited[i]:
                visited[i] = True
                queue.append(i)
```

### ▪️ DFS
```python
def dfs(adj, node, visited):
    visited[node] = True
    print(node, end=' ')
    for i in range(len(adj)):
        if adj[node][i] == 1 and not visited[i]:
            dfs(adj, i, visited)
```

### 🔗 LeetCode Examples (Adj Matrix)
| Problem | Concept |
|--------|---------|
| [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/) | Connected Components |
| [261. Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/) | Graph Validity |
| [323. Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) | BFS/DFS |

---

## ✅ Grid as Graph (Matrix BFS/DFS)

### ▪️ Matrix as Implicit Graph
- Each cell `(i, j)` = node.
- Neighbors = adjacent cells (up, down, left, right).

### ▪️ BFS Template
```python
from collections import deque
def bfs(matrix, start):
    rows, cols = len(matrix), len(matrix[0])
    visited = [[False]*cols for _ in range(rows)]
    directions = [(-1,0), (1,0), (0,-1), (0,1)]
    queue = deque([start])
    visited[start[0]][start[1]] = True
    while queue:
        r, c = queue.popleft()
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
            if 0 <= nr < rows and 0 <= nc < cols:
                if not visited[nr][nc] and matrix[nr][nc] == 1:
                    queue.append((nr, nc))
                    visited[nr][nc] = True
```

### ▪️ DFS Template
```python
def dfs(matrix, r, c, visited):
    rows, cols = len(matrix), len(matrix[0])
    if r < 0 or r >= rows or c < 0 or c >= cols:
        return
    if visited[r][c] or matrix[r][c] != 1:
        return
    visited[r][c] = True
    directions = [(-1,0), (1,0), (0,-1), (0,1)]
    for dr, dc in directions:
        dfs(matrix, r+dr, c+dc, visited)
```

### 🔗 LeetCode Examples (Matrix Graph)
| Problem | Type |
|--------|------|
| [200. Number of Islands](https://leetcode.com/problems/number-of-islands/) | DFS/BFS |
| [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) | BFS |
| [542. 01 Matrix](https://leetcode.com/problems/01-matrix/) | Multi-source BFS |
| [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/) | BFS |
| [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island/) | DFS |
| [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions/) | DFS |
| [733. Flood Fill](https://leetcode.com/problems/flood-fill/) | DFS/BFS |

---

## 🔎 Matrix vs Adjacency Matrix: How to Distinguish

| Clue | Grid Matrix | Adjacency Matrix |
|------|-------------|------------------|
| Purpose | Spatial problems | Node connections |
| Movement | Direction vectors (up/down/etc) | Index-to-index relationships |
| Values | Grid values | 0s and 1s (or weights) |
| Examples | Islands, Maze, Fill | Connected Components, Provinces |

---

