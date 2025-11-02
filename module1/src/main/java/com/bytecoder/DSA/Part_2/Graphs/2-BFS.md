in the real world , many problems are representated in terms of objects and connections between them.
for example, airline routes, electric circuits , LAN and internet , facebook friends etc

**Graph**: a graph is a pair (V,E), where V is a set of nodes, called vertices (vertex) and E is collection of pairs of vertices

![img.png](TypesOfGraph/AdjacencyMatrix/img.png)

![img.png](img.png)

type of graphs:

- Directed, undirected , weighted and unweighted graphs
- two connected nodes are called adjacent nodes.
- no cyclic in the graph is an acyclic graph

### Representation of graph :

1. Adjacency Matrix (matrix n*n)
2. Adjacency List (using hashmap or node with hashmap(weighted))

To Represent graphs, we need the number of vertices, the number edges (u,v) with their connections for example:
Input: n = 3, edges = [[1,3],[2,3],[3,1]]

```java
// adjacencyList
    Map<Integer,Set<Integer>> adjacencyList = new HashMap<>();
//weighted adjacency
    Map<Node,Map<Node,Integer>> adjacencyList = new HashMap<>();

// adjacencyMatrix
    int[][] matrix = new int[n][n];
  
    for(int[] edge : edges){
        matrix[edge[0]][edge[1]]=1 or adjacencyList.get(edge[0]).add(edge[1]);
  
        }
  
  
```

To keep track of no of connections or isVisited you can have array or map for example

```java
    int[] visited = new int[n];
//    visited[i] = true; means i node id visited
//    counter[i]++; means i nodes total connections with other nodes
// visited = set()   use hashset as well for tracking visited 
```

### 1. **Create a Graph, Print it**

- Use adjacency matrix or adjacency list to store the graph.
- Print the graph by iterating over the matrix/list and displaying connections.

```java
public class Graph<T> {
    private final HashMap<T, List<T>> adjList;
    private final boolean bidirection;

    public HashMap<T, List<T>> getAdjList() {
        return adjList;
    }

    public Graph(boolean bidirection) {
        adjList = new HashMap<>();
        this.bidirection = bidirection;
    }

    public void addVertex(T v){
        adjList.put(v, new ArrayList<T>());
    }

    public void addEdge(T source, T destination){
        if(!adjList.containsKey(source))
            addVertex(source);
        if (!adjList.containsKey(destination))
            addVertex(destination);
        adjList.get(source).add(destination);
        if (bidirection)
            adjList.get(destination).add(source);
    }
```

# 🌊 BFS (Breadth-First Search) - Complete Interview Guide

**Master BFS patterns for graphs with comprehensive problem coverage**

## Quick Stats

- **40+** BFS Problems
- **10** Core Patterns
- **100%** FAANG Relevant
- **Multi-Language** Solutions (Java & Python)

---

## 📚 Graph Fundamentals

**Graph Definition:** A graph G = (V, E) where V is vertices and E is edges connecting them.

In the real world, many problems are represented in terms of objects and connections between them:

- **Airline Routes** - Cities as nodes, flights as edges
- **Electric Circuits** - Components as nodes, wires as edges
- **LAN/Internet** - Computers as nodes, connections as edges
- **Social Networks** - People as nodes, friendships as edges

### Graph Types

- **Directed** vs **Undirected**
- **Weighted** vs **Unweighted**
- **Cyclic** vs **Acyclic** (DAG - Directed Acyclic Graph)
- **Connected** vs **Disconnected**

Two connected nodes are called **adjacent nodes**.
A graph with no cycles is an **acyclic graph**.

---

## 🎯 Graph Representation

### 1. Adjacency Matrix (n × n matrix)

```java
// For n vertices
int[][] matrix = new int[n][n];

// Add edges
for (int[] edge : edges) {
    matrix[edge[0]][edge[1]] = 1;  // or weight for weighted graph
    // For undirected: matrix[edge[1]][edge[0]] = 1;
}
```

### 2. Adjacency List (Using HashMap)

```java
// Unweighted graph
Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

// Weighted graph
Map<Node, Map<Node, Integer>> adjacencyList = new HashMap<>();

// Building the graph
for (int[] edge : edges) {
    adjacencyList.putIfAbsent(edge[0], new HashSet<>());
    adjacencyList.get(edge[0]).add(edge[1]);
    // For undirected: adjacencyList.get(edge[1]).add(edge[0]);
}
```

### 3. Tracking Visited Nodes

```java
// Using array
int[] visited = new int[n];
// visited[i] = 1 means node i is visited

// Using set
Set<Integer> visited = new HashSet<>();

// For counting connections
int[] counter = new int[n];
// counter[i] = total connections of node i
```

---

## 🎨 Core BFS Patterns

### Pattern 1: Basic BFS Traversal

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

## 🎓 Practice Strategy

### Beginner Level (Start Here)

1. Binary Tree Level Order Traversal (LC 102)
2. Flood Fill (LC 733)
3. Number of Islands (LC 200)
4. Rotting Oranges (LC 994)

### Intermediate Level

1. Word Ladder (LC 127)
2. 01 Matrix (LC 542)
3. Course Schedule (LC 207)
4. Clone Graph (LC 133)
5. Shortest Path in Binary Matrix (LC 1091)
6. Is Graph Bipartite? (LC 785)
7. Pacific Atlantic Water Flow (LC 417)

### Advanced Level

1. Bus Routes (LC 815)
2. Shortest Path with Obstacles Elimination (LC 1293)
3. Word Ladder II (LC 126)
4. Shortest Path Visiting All Nodes (LC 847)
5. Shortest Path to Get All Keys (LC 864)

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
