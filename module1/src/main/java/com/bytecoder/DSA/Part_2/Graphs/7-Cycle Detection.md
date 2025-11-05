# Cycle Detection Pattern - Complete Notes

## 🎯 Core Concept

Detect if a graph contains a cycle (a path that starts and ends at the same vertex). Approach differs for **directed** vs **undirected** graphs.

---

## 📋 Key Differences

| Aspect                     | Undirected Graph                         | Directed Graph               |
| -------------------------- | ---------------------------------------- | ---------------------------- |
| **Cycle Definition** | Back to any visited node (except parent) | Back to node in current path |
| **Detection Method** | Track parent                             | Track recursion stack/state  |
| **Colors/States**    | 2 (visited/unvisited)                    | 3 (white/gray/black)         |
| **Complexity**       | O(V + E)                                 | O(V + E)                     |

---

## 🔴 Pattern 1: Undirected Graph - DFS with Parent Tracking

**Use Case:** Detect cycles in undirected graphs, validate tree structure

**Key Insight:** If we visit a node that's already visited AND it's not our parent, we found a cycle.

**Complexity:** Time O(V + E) | Space O(V)

```python
def dfs_cycle_check(graph, node, parent, visited):
    visited.add(node)
  
    for neighbor in graph[node]:
        if neighbor not in visited:
            if dfs_cycle_undirected(graph, neighbor, node, visited):
                return True
        elif neighbor != parent:
            return True  # Cycle found
  
    return False


def bfs_cycle_check(graph, start, visited):
    queue = deque([(start, -1)])  # (node, parent)
    visited.add(start)
  
    while queue:
        node, parent = queue.popleft()
  
        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append((neighbor, node))
            elif neighbor != parent:
                return True  # Cycle detected
  
    return False


def has_cycle_undirected(n, edges):
    """Detect cycle in undirected graph using DFS"""
    from collections import defaultdict
  
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
  
    visited = set()
  
    # Check each component
    for i in range(n):
        if i not in visited:
            if dfs_cycle_undirected(graph, i, -1, visited):
                return True
  
    return False
```

---

## 🟢 Pattern 3: Directed Graph - DFS with 3 Colors

**Use Case:** Detect cycles in directed graphs, course scheduling, dependency resolution

**Key Insight:** Use 3 states:

* **WHITE (0):** Unvisited
* **GRAY (1):** Visiting (in current DFS path)
* **BLACK (2):** Visited (fully processed)

**Cycle detected when:** We encounter a GRAY node (back edge in current path)

**Complexity:** Time O(V + E) | Space O(V)

```python
def dfs_cycle_directed(graph, node, color):
    if color[node] == 1:  # GRAY
        return True  # Back edge = cycle
    if color[node] == 2:  # BLACK
        return False  # Already processed
  
    color[node] = 1  # Mark as GRAY (visiting)
  
    for neighbor in graph[node]:
        if dfs_cycle_directed(graph, neighbor, color):
            return True
  
    color[node] = 2  # Mark as BLACK (visited)
    return False

def has_cycle_directed(n, edges):
    """Detect cycle in directed graph using 3-color DFS"""
    from collections import defaultdict
  
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
  
    WHITE, GRAY, BLACK = 0, 1, 2
    color = [WHITE] * n
  
    for i in range(n):
        if color[i] == WHITE:
            if dfs_cycle_directed(graph, i, color):
                return True
  
    return False
```

---

## 🟡 Pattern 4: Directed Graph - Recursion Stack Method

**Use Case:** Alternative to 3-color method, easier to understand

**Key Insight:** Maintain separate `visited` and `recursion_stack` arrays

**Complexity:** Time O(V + E) | Space O(V)

```python
def dfs_rec_stack(graph, node, visited, rec_stack):
    if rec_stack[node]:
        return True  # Cycle detected
    if visited[node]:
        return False  # Already processed
  
    visited[node] = True
    rec_stack[node] = True
  
    for neighbor in graph[node]:
        if dfs_rec_stack(graph, neighbor, visited, rec_stack):
            return True
  
    rec_stack[node] = False  # Backtrack
    return False

def has_cycle_directed_recstack(n, edges):
    """Detect cycle using visited + recursion stack"""
    from collections import defaultdict
  
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
  
    visited = [False] * n
    rec_stack = [False] * n
  
    for i in range(n):
        if not visited[i]:
            if dfs_rec_stack(graph, i, visited, rec_stack):
                return True
  
    return False
```

---

## 🟠 Pattern 5: Directed Graph - Kahn's Algorithm (BFS)

**Use Case:** Topological sort based cycle detection

**Key Insight:** If we can't complete topological sort (some nodes remain), there's a cycle

**Complexity:** Time O(V + E) | Space O(V)

```python
from collections import deque, defaultdict

def has_cycle_kahns(n, edges):
    """Detect cycle using Kahn's algorithm (topological sort)"""
    graph = defaultdict(list)
    in_degree = [0] * n
  
    # Build graph and in-degree array
    for u, v in edges:
        graph[u].append(v)
        in_degree[v] += 1
  
    # Start with nodes having 0 in-degree
    queue = deque([i for i in range(n) if in_degree[i] == 0])
    processed = 0
  
    while queue:
        node = queue.popleft()
        processed += 1
  
        for neighbor in graph[node]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)
  
    # If we couldn't process all nodes, there's a cycle
    return processed != n
```

---

## 🎯 Pattern 6: Graph Valid Tree (Combined Check)

**Problem:** LC 261 - Check if graph is a valid tree

**Key Insight:** A valid tree must:

1. Have no cycles
2. Be fully connected
3. Have exactly n-1 edges for n nodes

**Complexity:** Time O(V + E) | Space O(V)

```python
def validTree(n, edges):
    """LC 261: Check if undirected graph is a valid tree"""
    # Tree must have exactly n-1 edges
    if len(edges) != n - 1:
        return False
  
    from collections import defaultdict
  
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
  
    visited = set()
  
    # Check for cycle and connectivity
    if has_cycle_or_not_connected(graph, 0, -1, visited):
        return False
  
    # Check if all nodes are connected
    return len(visited) == n

def has_cycle_or_not_connected(graph, node, parent, visited):
    visited.add(node)
  
    for neighbor in graph[node]:
        if neighbor not in visited:
            if has_cycle_or_not_connected(graph, neighbor, node, visited):
                return True
        elif neighbor != parent:
            return True  # Cycle found
  
    return False
```

---

## 🔴 Pattern 7: Redundant Connection (Find the Edge)

**Problem:** LC 684 - Find edge that creates cycle in undirected graph or

**Use Case:** Find which edge to remove to make graph acyclic

**Complexity:** Time O(V + E) | Space O(V)

```python
def findRedundantConnection(edges):
    """LC 684: Find the edge that creates a cycle"""
    from collections import defaultdict
  
    graph = defaultdict(list)
  
    def has_cycle(node, target, parent, visited):
        visited.add(node)
  
        if node == target:
            return True
  
        for neighbor in graph[node]:
            if neighbor not in visited and neighbor != parent:
                if has_cycle(neighbor, target, node, visited):
                    return True
  
        return False
  
    # Try adding edges one by one
    for u, v in edges:
        # Check if adding this edge creates a cycle
        if u in graph and v in graph:
            if has_cycle(u, v, -1, set()):
                return [u, v]
  
        graph[u].append(v)
        graph[v].append(u)
  
    return []
```

**Union Find**

```python
class Solution:

    def find(self, a , parent):
        if(parent.get(a) != a):
            return self.find(parent.get(a), parent)

        return parent.get(a)
  

    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:

        parent =  {}

        # O(N)
        for i in range(len(edges)):
            parent[i]=i
        # O(N)
        for edge in edges:
            a, b = edge
            # O(N)
            parent_a = self.find(a, parent)
            parent_b = self.find(b, parent)

            if(parent_a == parent_b):
                return edge
            else:
                parent[parent_a] = parent_b
  
        return []

```

---

## 📝 FAANG Problem List

### Undirected Graph Cycles

* **Redundant Connection** (LC 684) ⭐⭐ - Find edge creating cycle
* **Graph Valid Tree** (LC 261) ⭐⭐ - Validate tree structure (Premium)
* **Detect Cycles in 2D Grid** (LC 1559) ⭐⭐ - Same value cycle

## 💡 Method Selection Guide

| Scenario                      | Best Method      | Why                            |
| ----------------------------- | ---------------- | ------------------------------ |
| Undirected graph              | DFS + Parent     | Simple, intuitive              |
| Directed graph (small)        | 3-Color DFS      | Most common interview approach |
| Directed graph (large)        | Kahn's Algorithm | Also gives topological sort    |
| Need to find cycle edge       | Union-Find       | Efficient edge-by-edge         |
| Already have topological sort | Kahn's Algorithm | Reuse existing logic           |

---
