# Pattern 1: Basic BFS Traversal

**Use Cases:** Level-order traversal, shortest path in unweighted graphs

**💡 Key Insight:** BFS explores level by level using a queue. Guarantees shortest path in unweighted graphs.

**Time:** O(V + E) | **Space:** O(V)

#### Python Template:

```python
from collections import deque

def bfs(start, graph):
    queue = deque([start])
    visited = set([start])
    level = 0
  
    while queue:
        level_size = len(queue)
  
        for _ in range(level_size):
            node = queue.popleft()
            print(f"Level {level}: {node}")
  
            for neighbor in graph[node]:
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append(neighbor)
  
        level += 1
  
    return visited
```

---

## 📚 Quick Reference

### BFS Template (Generic)

```python
from collections import deque

def bfs_template(start, graph):
    queue = deque([start])
    visited = set([start])
    level = 0
  
    while queue:
        size = len(queue)
  
        for _ in range(size):
            node = queue.popleft()
  
            # Process node
  
            for neighbor in graph[node]:
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append(neighbor)
  
        level += 1
  
    return result
```

### Multi-Source BFS Template

```python
def multi_source_bfs(grid):
    queue = deque()
    visited = set()
  
    # Add all sources
    for r in range(rows):
        for c in range(cols):
            if is_source(grid[r][c]):
                queue.append((r, c))
                visited.add((r, c))
  
    level = 0
    while queue:
        size = len(queue)
        for _ in range(size):
            r, c = queue.popleft()
            # Process and add neighbors
        level += 1
  
    return level
```

### Grid BFS Template

```java
public int gridBFS(int[][] grid) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[rows][cols];
    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
  
    queue.add(new int[]{startR, startC});
    visited[startR][startC] = true;
    int steps = 0;
  
    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
  
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (isValid(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        steps++;
    }
    return steps;
}
```

### Shortest Path with Parent Tracking

```python
def shortest_path_with_reconstruction(start, end, graph):
    queue = deque([start])
    parent = {start: None}
  
    while queue:
        node = queue.popleft()
  
        if node == end:
            # Reconstruct path
            path = []
            while node is not None:
                path.append(node)
                node = parent[node]
            return path[::-1]
  
        for neighbor in graph[node]:
            if neighbor not in parent:
                parent[neighbor] = node
                queue.append(neighbor)
  
    return []  # No path found
```

---

### Problem-Solving Approach

1. **Identify if BFS is needed:**

   - Keywords: shortest, minimum, level, nearest
   - Unweighted graph problems
   - State transformation problems
2. **Choose the right pattern:**

   - Single source → Basic BFS
   - Multiple sources → Multi-source BFS
   - Grid problem → Grid BFS with directions
   - State space → BFS with custom state
   - Bipartite check → BFS with coloring
3. **Implementation checklist:**

   - [ ] Initialize queue with starting node(s)
   - [ ] Initialize visited set/array
   - [ ] Mark visited during enqueue
   - [ ] Process level by level if needed
   - [ ] Check bounds for grid problems
   - [ ] Handle disconnected components

## 🎯 BFS vs DFS Comparison

| Feature                    | BFS                    | DFS                    |
| -------------------------- | ---------------------- | ---------------------- |
| **Data Structure**   | Queue                  | Stack (recursion)      |
| **Space Complexity** | O(width)               | O(height)              |
| **Shortest Path**    | ✅ Yes (unweighted)    | ❌ No                  |
| **All Paths**        | ❌ No                  | ✅ Yes                 |
| **Level Processing** | ✅ Yes                 | ❌ No                  |
| **Memory Usage**     | Higher for wide graphs | Higher for deep graphs |
| **Use When**         | Need shortest path     | Need all paths/cycles  |

---

## 💡 Interview Tips

### When to Use BFS

✅ **Use BFS when:**

- Finding shortest path in unweighted graph
- Level-order traversal needed
- Minimum steps/moves required
- Nearest neighbor problems
- State space exploration with minimum transitions

❌ **Don't use BFS when:**

- Need to find all paths (use DFS)
- Graph has weighted edges (use Dijkstra)
- Memory is constrained and graph is wide

### Common Patterns Recognition

| Problem Description           | Pattern          | Algorithm      |
| ----------------------------- | ---------------- | -------------- |
| "Shortest path"               | Shortest Path    | BFS            |
| "Minimum steps/moves"         | Level BFS        | BFS            |
| "All cells at distance k"     | Multi-source     | BFS            |
| "Transform A to B"            | State Space      | BFS            |
| "Can partition into 2 groups" | Bipartite        | BFS + Coloring |
| "Detect cycle"                | Cycle Detection  | BFS/DFS        |
| "Task dependencies"           | Topological Sort | Kahn's (BFS)   |

### Edge Cases to Consider

1. **Empty graph** - Handle null/empty inputs
2. **Single node** - Base case
3. **Disconnected components** - Loop through all nodes
4. **Self-loops** - Check in cycle detection
5. **Duplicate edges** - Use Set if needed
6. **No path exists** - Return -1 or empty result

### Optimization Techniques

```java
// 1. Early termination
if (node == target) return distance;

// 2. Bidirectional BFS (for large graphs)
// Run BFS from both source and target

// 3. Use visited during enqueue (not dequeue)
if (!visited.contains(neighbor)) {
    visited.add(neighbor);  // Mark here
    queue.add(neighbor);
}

// 4. For grid problems, use directions array
int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
```

### Common Mistakes to Avoid

1. ❌ Marking visited after dequeue (causes duplicates in queue)
2. ❌ Not handling disconnected graphs
3. ❌ Forgetting to track parent for path reconstruction
4. ❌ Using DFS when BFS is needed for shortest path
5. ❌ Not checking bounds in grid problems
6. ❌ Modifying graph while traversing

**Flood Fill** (LC 733)

- Basic BFS on grid
- Time: O(m × n) | Space: O(m × n)

**Number of Islands** (LC 200) ⭐⭐⭐

- Grid BFS, connected components
- Time: O(m × n) | Space: O(min(m, n))
- **Real-world:** Flood-fill algorithms in image processing

**Clone Graph** (LC 133) ⭐⭐

- BFS with HashMap
- Time: O(V + E) | Space: O(V)

* **Nearest Exit from Entrance in Maze** (LC 1926) ⭐
  - BFS with exit detection
  - Time: O(m × n) | Space: O(m × n)
  - **Real-world:** Emergency evacuation planning
* **Minimum Knight Moves** (LC 1197) ⭐⭐
  - BFS on infinite board
  - Time: O(|x| × |y|) | Space: O(|x| × |y|)

## 📋 🌍 Real-Life Applications of BFS

### 1️⃣ Social Network Analysis

- **Use Case:** Find shortest connection between two people
- **Example:** LinkedIn's "degrees of connection", Facebook friend suggestions
- **Algorithm:** BFS finds minimum friend connections

### 2️⃣ Google Maps / GPS Navigation

- **Use Case:** Finding shortest path in road networks
- **Example:** Google Maps uses BFS for unweighted routes (fewest turns/stops)
- **Algorithm:** BFS on road graph with intersections as nodes

### 3️⃣ AI & Game Development

- **Use Case:** Pathfinding in games
- **Example:** AI characters finding shortest route to target
- **Algorithm:** BFS as base for A* pathfinding

### 4️⃣ Web Crawlers (Search Engines)

- **Use Case:** Traversing web pages efficiently
- **Example:** Googlebot uses BFS to crawl the web
- **Algorithm:** Level-by-level page discovery

### 5️⃣ Network Packet Routing

- **Use Case:** Ensuring packets take shortest path
- **Example:** Data packet transmission in internet backbone
- **Algorithm:** BFS for optimal routing

### 6️⃣ Water Supply and Power Grid Analysis

- **Use Case:** Finding shortest distribution path
- **Example:** Electricity distribution planning in smart grids
- **Algorithm:** BFS for resource distribution

### 7️⃣ Fire Escape Planning

- **Use Case:** Determining quickest escape routes
- **Example:** Fire safety systems in smart buildings
- **Algorithm:** BFS from fire location to exits

---
