### Pattern 4: Path Finding (All Paths)

**Use Cases:** Find all paths, backtracking problems

**💡 Key Insight:** Use DFS with backtracking. Track current path and add to result when destination reached.

**Time:** O(2^V * V) worst case | **Space:** O(V)

```python
def all_paths_source_target(graph):
    """Find all paths from 0 to n-1 in DAG (LC 797)"""
    n = len(graph)
    result = []
  
    def dfs(node, path):
        if node == n - 1:
            result.append(path[:])  # Found a path
            return
  
        for neighbor in graph[node]:
            path.append(neighbor)
            dfs(neighbor, path)
            path.pop()  # Backtrack
  
    dfs(0, [0])
    return result
```

**Path Exists Check:**

```python
def has_path(graph, start, end):
    """Check if path exists from start to end"""
    visited = set()
  
    def dfs(node):
        if node == end:
            return True
        if node in visited:
            return False
  
        visited.add(node)
        for neighbor in graph[node]:
            if dfs(neighbor):
                return True
        return False
  
    return dfs(start)
```

**FAANG Problems:**

- All Paths From Source to Target (LC 797)
- Find if Path Exists in Graph (LC 1971)
- Path Sum II (LC 113)

### Pattern 9: All Paths from Source to Target

**Use Cases:** Find all possible routes, enumerate paths

**💡 Key Insight:** BFS with path tracking. Store entire path in queue state.

**Time:** O(2^V × V) | **Space:** O(2^V × V)

```java
public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> result = new ArrayList<>();
    Queue<List<Integer>> queue = new LinkedList<>();
  
    List<Integer> initialPath = new ArrayList<>();
    initialPath.add(0);
    queue.add(initialPath);
  
    int target = graph.length - 1;
  
    while (!queue.isEmpty()) {
        List<Integer> path = queue.poll();
        int node = path.get(path.size() - 1);
  
        if (node == target) {
            result.add(new ArrayList<>(path));
            continue;
        }
  
        for (int neighbor : graph[node]) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(neighbor);
            queue.add(newPath);
        }
    }
  
    return result;
}
```

---

### Pattern 10: Cheapest Flights Within K Stops

**Use Cases:** Path with constraints, limited hops, budget optimization

**💡 Key Insight:** Modified BFS tracking (node, cost, stops). Prune paths exceeding k stops.

**Time:** O(K × E) | **Space:** O(V)

```python
from collections import deque

def findCheapestPrice(n, flights, src, dst, k):
    """
    LC 787: Cheapest Flights Within K Stops
    """
    graph = {i: [] for i in range(n)}
    for u, v, price in flights:
        graph[u].append((v, price))
  
    # BFS approach
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

### Pattern 5: Weighted Path DFS (Path with Constraints)

**Use Cases:** Path with minimum/maximum weight, path length constraints

**💡 Key Insight:** DFS with backtracking. Track cumulative weight/distance. Backtrack to explore other paths.

**Time:** O(V!) worst case | **Space:** O(V)

```python
def path_more_than_k_length(n, edges, k):
    """Find if path exists with weight >= k"""
    graph = defaultdict(list)
    for u, v, weight in edges:
        graph[u].append((v, weight))
        graph[v].append((u, weight))  # Undirected
  
    visited = [False] * n
  
    def dfs(node, current_sum):
        if current_sum >= k:
            return True
  
        visited[node] = True
  
        for neighbor, weight in graph[node]:
            if not visited[neighbor]:
                if dfs(neighbor, current_sum + weight):
                    return True
  
        visited[node] = False  # Backtrack
        return False
  
    return dfs(0, 0)
```

**Longest Path in DAG:**

```python
def longest_path_dag(graph, n):
    """Find longest path in DAG using DFS + memoization"""
    memo = {}
  
    def dfs(node):
        if node in memo:
            return memo[node]
  
        max_length = 0
        for neighbor in graph[node]:
            max_length = max(max_length, 1 + dfs(neighbor))
  
        memo[node] = max_length
        return max_length
  
    return max(dfs(i) for i in range(n))
```

### Pattern 2: Shortest Path in Unweighted Graph

**Use Cases:** Finding minimum distance, shortest transformation sequence

**💡 Key Insight:** BFS naturally finds shortest path in unweighted graphs. Track parent to reconstruct path.

**Time:** O(V + E) | **Space:** O(V)

#### Python Implementation (Using Node Class):

```python
from collections import deque

class Node:
    def __init__(self, val, neighbours=None):
        self.val = val
        self.neighbours = neighbours or []

def build_graph(edges):
    nodes = {}

    for u, v in edges:
        if u not in nodes:
            nodes[u] = Node(u)
        if v not in nodes:
            nodes[v] = Node(v)

        # Add neighbours (undirected graph)
        nodes[u].neighbours.append(nodes[v])
        nodes[v].neighbours.append(nodes[u])

    return nodes

def get_path(target, mapper):
    ans = []
    node = target
    while node is not None:
        ans.insert(0, node)
        node = mapper.get(node)
    return ans

def shortest_path_bfs(root, target):
    q = deque([root])
    mapper = {root: None}
  
    while q:
        level_size = len(q)
        for _ in range(level_size):
            temp = q.popleft()
            if temp == target:
                return get_path(target, mapper)
  
            for neighbor in temp.neighbours:
                if neighbor not in mapper:
                    q.append(neighbor)
                    mapper[neighbor] = temp
  
    return []

# Usage Example
edges = [
    ("A", "B"),
    ("A", "C"),
    ("C", "D"),
    ("B", "E"),
    ("F", "Z")
]

graph = build_graph(edges)
start = graph["A"]
end = graph["E"]

path = shortest_path_bfs(start, end)
print(" -> ".join(node.val for node in path))
# Output: A -> B -> E
```

#### Alternative: Shortest Path Using DFS (Not Optimal)

```python
def shortest_path_using_dfs(root, target, visited, temp, result):
    """DFS approach - explores all paths, not optimal for shortest path"""
    if root is None:
        return 

    if root == target:
        if len(temp) < len(result) or not result:
            result.clear()
            result.extend(temp)
            return 
  
    visited.add(root)
  
    for node in root.neighbours:
        if node not in visited:
            temp.append(node.val)
            shortest_path_using_dfs(node, target, visited, temp, result)
            temp.remove(node.val)
  
    visited.remove(root)

# Note: BFS is preferred for shortest path in unweighted graphs
```

---

* Longest Increasing Path (329)
*
