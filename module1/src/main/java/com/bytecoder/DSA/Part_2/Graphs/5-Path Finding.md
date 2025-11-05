# Path Finding Patterns — De-duplicated

1. **Path Existence (DFS)**

   Use to check connectivity (O(V+E)/O(V)).
2. **All Paths (DFS + Backtracking)**

   Enumerate all routes; beware exponential growth.
3. **Shortest Path (Unweighted, BFS)**

   * Return length
   * Return full path
   * Parent tracking (memory-friendly)
4. **Weighted Shortest Path**

   * **Dijkstra** (non-negative weights)
   * **Bellman–Ford** (handles negative weights, detects negative cycles)
5. **Weighted Path with Constraints (DFS/Backtracking)**

   e.g., “Is there a path with total weight ≥ k?” with pruning.
6. **Longest Path in DAG (DFS + Memoization)**

   Works only for DAGs.
7. **K-Stops / Limited-Hops Path (Cheapest Flights Within K Stops)** ✅ *Merged*

   Use modified BFS / level-by-level relaxation up to K edges (or K stops).
8. **Shortest Path in Grid (Binary Matrix)**

   **Use Case:** Grid navigation, maze solving, robot pathfinding

---

## 🔥 Pattern 1: Path Existence Check

**Use Case:** Check if any path exists between two nodes

**Complexity:** Time O(V + E) | Space O(V)

```python
def dfs_path_exists(graph, node, end, visited):
    if node == end:
        return True
  
    visited.add(node)
  
    for neighbor in graph[node]:
	if neighbor in visited:
            continue
        if dfs_path_exists(graph, neighbor, end, visited):
            return True
  
    return False

def has_path(graph, start, end):
    """Check if path exists from start to end"""
    visited = set()
    return dfs_path_exists(graph, start, end, visited)
```

## 🛤️ Pattern 2: Find All Paths (Backtracking)

**Use Case:** Enumerate all possible paths from source to target

**Complexity:** Time O(2^V × V) | Space O(V)

```python
def dfs_all_paths(graph, node, target, path, result):
    if node == target:
        result.append(path[:])  # Add copy of current path
        return
  
    for neighbor in graph[node]:
        path.append(neighbor)
        dfs_all_paths(graph, neighbor, target, path, result)
        path.pop()  # Backtrack

def all_paths_source_target(graph):
    """Find all paths from 0 to n-1 in DAG (LC 797)"""
    n = len(graph)
    result = []
    dfs_all_paths(graph, 0, n - 1, [0], result)
    return result
```

## 🎯 Pattern 3: Shortest Path (Unweighted Graph - BFS)

**Use Case:** Find shortest path in unweighted graphs

**Complexity:** Time O(V + E) | Space O(V)

### Version A: Return Path Length

```python
from collections import deque

def bfs_shortest_path_length(graph, start, end):
    queue = deque([(start, 0)])  # (node, distance)
    visited = {start}
  
    while queue:
        node, dist = queue.popleft()
  
        if node == end:
            return dist
  
        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append((neighbor, dist + 1))
  
    return -1  # No path exists
```

### Version B: Return Full Path

```python
from collections import deque

def bfs_shortest_path(graph, start, end):
    queue = deque([[start]])  # Store entire path
    visited = {start}
  
    while queue:
        path = queue.popleft()
        node = path[-1]
  
        if node == end:
            return path
  
        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                new_path = path + [neighbor]
                queue.append(new_path)
  
    return []  # No path exists
```

### Version C: Using Parent Tracking (Memory Efficient)

```python
from collections import deque

def reconstruct_path(parent, start, end):
    path = []
    current = end
  
    while current is not None:
        path.append(current)
        current = parent.get(current)
  
    path.reverse()
    return path if path[0] == start else []

def bfs_shortest_path_parent(graph, start, end):
    queue = deque([start])
    parent = {start: None}
  
    while queue:
        node = queue.popleft()
  
        if node == end:
            return reconstruct_path(parent, start, end)
  
        for neighbor in graph[node]:
            if neighbor not in parent:
                parent[neighbor] = node
                queue.append(neighbor)
  
    return []
```

---

### ⚖️ Pattern 4. Wighted Shortest Path (both directed and undirected)

### a. Dijkstra's Algorithm

**Use Cases:** Shortest path in weighted graph with non-negative weights, network routing

**💡 Key Insight:** Greedy algorithm. Always pick closest unvisited node. Use min-heap (priority queue). Does NOT work with negative weights (use Bellman-Ford instead).

**Time:** O((V + E) log V) with min-heap | **Space:** O(V)

#### Template:

```python
import heapq
from collections import defaultdict

def dijkstra(n, edges, start):
    graph = defaultdict(list)
    for u, v, weight in edges:
        graph[u].append((v, weight))
  
    # Min heap: (distance, node)
    heap = [(0, start)]
    dist = {i: float('inf') for i in range(n)}
    dist[start] = 0
    visited = set()
  
    while heap:
        d, node = heapq.heappop(heap)
  
        if node in visited:
            continue
        visited.add(node)
  
        for neighbor, weight in graph[node]:
            new_dist = d + weight
            if new_dist < dist[neighbor]:
                dist[neighbor] = new_dist
                heapq.heappush(heap, (new_dist, neighbor))
  
    return dist
```

**Common Problems:**

* Network Delay Time (743)
* Cheapest Flights Within K Stops (787)

### b. Bellman-Ford Algorithm

**Use Cases:** Shortest path with negative weights, detect negative cycles

**💡 Key Insight:** Relax all edges V-1 times. Can detect negative cycles by checking if distances still decrease in V-th iteration.

**Time:** O(V * E) | **Space:** O(V)

#### Template:

```python
def bellman_ford(n, edges, start):
    dist = [float('inf')] * n
    dist[start] = 0
  
    # Relax all edges V-1 times
    for _ in range(n - 1):
        for u, v, weight in edges:
            if dist[u] != float('inf') and dist[u] + weight < dist[v]:
                dist[v] = dist[u] + weight
  
    # Check for negative cycles
    for u, v, weight in edges:
        if dist[u] != float('inf') and dist[u] + weight < dist[v]:
            return None  # Negative cycle detected
  
    return dist
```

**Common Problems:**

* Cheapest Flights Within K Stops (787) - Modified Bellman-Ford
* Network Delay Time (743) - Alternative to Dijkstra

## ⚖️ Pattern 5: Weighted Path Finding (With Constraints)(both directed and undirected)

****Use when:** **“Is there a path with total weight ≥/≤ K?”, resource limits, etc.

**Complexity:** Time O(V!) worst case | Space O(V)

```python
def dfs_weighted_path(graph, node, target, current_sum, k, visited):
    if current_sum >= k:
        return True
    if node == target:
        return current_sum >= k
  
    visited[node] = True
  
    for neighbor, weight in graph[node]:
        if not visited[neighbor]:
            if dfs_weighted_path(graph, neighbor, target, current_sum + weight, k, visited):
                visited[node] = False  # Backtrack
                return True
  
    visited[node] = False  # Backtrack
    return False

def path_more_than_k_length(n, edges, k):
    """Find if path exists with weight >= k"""
    from collections import defaultdict
  
    graph = defaultdict(list)
    for u, v, weight in edges:
        graph[u].append((v, weight))
        graph[v].append((u, weight))  # Undirected
  
    visited = [False] * n
    return dfs_weighted_path(graph, 0, n - 1, 0, k, visited)
```

---

## 📏 Pattern 6: Longest Path in DAG

**Use Case:** Find maximum length path (works only for DAGs)

**Complexity:** Time O(V + E) | Space O(V)

```python
def dfs_longest_path(graph, node, memo):
    if node in memo:
        return memo[node]
  
    max_length = 0
    for neighbor in graph[node]:
        max_length = max(max_length, 1 + dfs_longest_path(graph, neighbor, memo))
  
    memo[node] = max_length
    return max_length

def longest_path_dag(graph, n):
    """Find longest path in DAG using DFS + memoization"""
    memo = {}
    max_path = 0
  
    for i in range(n):
        max_path = max(max_path, dfs_longest_path(graph, i, memo))
  
    return max_path
```

---

## ✈️ Pattern 7: Cheapest Flights (K Stops Constraint)

**Use Case:** “Cheapest path with ≤ K stops/edges” (merged pattern).

**Complexity:** Time O(K × E) | Space O(V)

```python
from collections import deque

def findCheapestPrice(n, flights, src, dst, k):
    """LC 787: Cheapest Flights Within K Stops"""
    from collections import defaultdict
  
    graph = defaultdict(list)
    for u, v, price in flights:
        graph[u].append((v, price))
  
    queue = deque([(src, 0, 0)])  # (node, cost, stops)
    min_cost = {src: 0}
  
    while queue:
        node, cost, stops = queue.popleft()
  
        if node == dst:
            continue
  
        if stops > k:
            continue
  
        for neighbor, price in graph[node]:
            new_cost = cost + price
  
            # Only add if we found a cheaper way
            if neighbor not in min_cost or new_cost < min_cost[neighbor]:
                min_cost[neighbor] = new_cost
                queue.append((neighbor, new_cost, stops + 1))
  
    return min_cost.get(dst, -1)
```

---

## 🗺️ Pattern 8: Shortest Path in Grid (Binary Matrix)

**Use Case:** Grid navigation, maze solving, robot pathfinding

**Complexity:** Time O(m × n) | Space O(m × n)

```python
from collections import deque

def shortestPathBinaryMatrix(grid):
    """LC 1091: Shortest Path in Binary Matrix (8-directional)"""
    if not grid or grid[0][0] == 1:
        return -1
  
    n = len(grid)
    if grid[n-1][n-1] == 1:
        return -1
  
    # 8 directions
    directions = [(-1,-1), (-1,0), (-1,1), (0,-1), (0,1), (1,-1), (1,0), (1,1)]
  
    queue = deque([(0, 0, 1)])  # (row, col, distance)
    grid[0][0] = 1  # Mark as visited
  
    while queue:
        r, c, dist = queue.popleft()
  
        if r == n - 1 and c == n - 1:
            return dist
  
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
  
            if 0 <= nr < n and 0 <= nc < n and grid[nr][nc] == 0:
                grid[nr][nc] = 1  # Mark as visited
                queue.append((nr, nc, dist + 1))
  
    return -1
```

---

## 📝 FAANG Problem List

### Path Existence

* **Find if Path Exists in Graph** (LC 1971) ⭐
* **Keys and Rooms** (LC 841) ⭐⭐

### All Paths

* **All Paths From Source to Target** (LC 797) ⭐⭐
* **Path Sum II** (LC 113) ⭐⭐
* **Binary Tree Paths** (LC 257) ⭐

### Shortest Path

* **Shortest Path in Binary Matrix** (LC 1091) ⭐⭐
* **Word Ladder** (LC 127) ⭐⭐⭐
* **Minimum Knight Moves** (LC 1197) ⭐⭐
* **Nearest Exit from Entrance in Maze** (LC 1926) ⭐

### Weighted/Constrained Paths

* **Cheapest Flights Within K Stops** (LC 787) ⭐⭐⭐
* **Path with Maximum Probability** (LC 1514) ⭐⭐
* **Network Delay Time** (LC 743) ⭐⭐

### Longest Path

* **Longest Increasing Path in Matrix** (LC 329) ⭐⭐⭐
* **Longest Path in a DAG** (varies)

---

## 💡 Key Decision Points

| Problem Type               | Algorithm             | Why                 |
| -------------------------- | --------------------- | ------------------- |
| Path exists?               | DFS                   | Simple, O(V) space  |
| Shortest path (unweighted) | BFS                   | Guarantees shortest |
| All paths                  | DFS + Backtracking    | Need to explore all |
| Shortest path (weighted)   | Dijkstra/Bellman-Ford | Handles weights     |
| Path with constraints      | Modified BFS/DFS      | Custom pruning      |
| Longest path (DAG)         | DFS + Memo            | Works only for DAGs |
| Grid shortest path         | BFS                   | Layer-by-layer      |

---

## 🎓 Common Pitfalls

1. **Using DFS for shortest path** → May not find optimal solution
2. **Not handling cycles** → Infinite loops in all-paths problems
3. **Forgetting to backtrack** → Incorrect results in path enumeration
4. **Not copying paths** → Reference issues with `path[:]`
5. **Wrong visited tracking** → Missing valid paths or infinite loops

---
