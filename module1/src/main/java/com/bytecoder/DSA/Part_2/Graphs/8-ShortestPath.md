### 5. Shortest Path - Dijkstra's Algorithm

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

### 6. Bellman-Ford Algorithm

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

## Shorted Path algorithms

Shortest Path:
https://leetcode.com/problems/network-delay-time/
https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
https://leetcode.com/problems/cheapest-flights-within-k-stops/
https://leetcode.com/problems/all-paths-from-source-to-target/description/

### 22. **Shorted Path algorithms for unweighted or undirected graph**

### 12. **Dijkstra’s Algorithm**

- Use a priority queue (min-heap) to keep track of the shortest path to each node.
- Greedily expand the shortest known path to reach all nodes in a weighted graph.

### 21. **Bellman-Ford Algorithm**

- Use dynamic programming to relax edges repeatedly. Detect negative weight cycles by checking for changes after `V-1` iterations.

### 23. **Travelling Salesman Problem**

- Use dynamic programming or backtracking to find the minimum cost of visiting all cities once and returning to the start.

### 24. **Graph Coloring Problem**

- Use backtracking to try and assign colors such that no two adjacent nodes share the same color.

### 25. **Snake and Ladders Problem**

- Model the board as a graph with cells as nodes, use BFS to find the minimum dice throws to reach the final cell.

### 29. **Detect Negative Cycle in a Graph**

- Use Bellman-Ford algorithm to check for changes after `V-1` relaxations to detect negative cycles.

### 30. **Longest Path in a DAG**

- Topologically sort the graph and then relax the edges to find the longest path.

### 32. **Cheapest Flights Within K Stops**

- Use a variant of Dijkstra’s algorithm (or BFS) with an additional constraint on the number of stops allowed.
