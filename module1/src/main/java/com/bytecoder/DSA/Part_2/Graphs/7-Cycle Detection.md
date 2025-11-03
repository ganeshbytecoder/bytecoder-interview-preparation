### 6. Cycle Detection

**Use Cases:** Detect cycles, validate DAG

**💡 Key Insight:**

- **Directed:** DFS with 3 colors (white/gray/black)
- **Undirected:** DFS with parent or Union-Find

#### Directed Graph:

```python
def has_cycle_directed(n, edges):
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
    WHITE, GRAY, BLACK = 0, 1, 2
    color = [WHITE] * n
  
    def dfs(node):
        if color[node] == GRAY:
            return True
        if color[node] == BLACK:
            return False
        color[node] = GRAY
        for neighbor in graph[node]:
            if dfs(neighbor):
                return True
        color[node] = BLACK
        return False
  
    return any(color[i] == WHITE and dfs(i) for i in range(n))
```

**Common Problems:** Course Schedule (207), Redundant Connection (684)

### 4. **Detect Cycle in Undirected Graph using BFS/DFS Algorithm**

- **DFS:** Track the parent of each node; if you find an edge that leads to a previously visited node that is not the parent, a cycle is detected.
- **BFS:** Use a queue and track parent nodes similarly to DFS.

```java
/*                                 Detect cycle in an undirected graph
---------------------------------------------------------------------------------------------------------------*/
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        HashSet<Integer> visited = new HashSet<>();

        for (int i = 0; i < V; i++) {
            if (!visited.contains(i)){
                if (dfs(adj, i, -1, visited))
                        return true;
            }
        }
        return false;
    }

    private boolean dfs(ArrayList<ArrayList<Integer>> adj, int src, int parent, HashSet<Integer> visited)
    {
        visited.add(src);

        for (int neighbour : adj.get(src)){
            if (!visited.contains(neighbour)) {
                if (dfs(adj, neighbour, src, visited))
                    return true;
            } else if (parent != neighbour) {
                return true;
            }
        }
        return false;
    }
/*-------------------------------------------------------------------------------------------------------------*/

```

### 5. **Detect Cycle in Directed Graph using BFS/DFS Algorithm**

- **DFS Approach:** Maintain a recursion stack to check for back edges (edges that point to an ancestor in DFS tree).
- **BFS Approach (Kahn’s Algorithm):** Use topological sorting and check for leftover nodes (a cycle exists if a topological sort is not possible).

```java
/*                                      Detect cycle in Directed graph
----------------------------------------------------------------------------------------------------------------*/
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] recstack = new boolean[V];

        for (int i = 0; i < V; i++){
            if (dfs(adj, i, visited, recstack))
                return true;
        }
        return false;
    }

    private boolean dfs(ArrayList<ArrayList<Integer>> adj, int src, boolean[] visited, boolean[] recstack) {
        if (recstack[src])
            return true;
        if (visited[src])
            return false;

        visited[src] = true;
        recstack[src] = true;

        for (int neighbour : adj.get(src)){
            if (dfs(adj, neighbour, visited, recstack))
                return true;
        }
        recstack[src] = false;
        return false;
    }
/*--------------------------------------------------------------------------------------------------------------*/
```

M2

```java

public boolean isCyclic(int V, Map<Integer, List<Integer>> adj) {
    int[] state = new int[V]; // 0 = unvisited, 1 = visiting, 2 = visited

    for (int i = 0; i < V; i++) {
        if (state[i] == 0 && checkCycle(adj, i, state)) {
            return true;  // Cycle detected
        }
    }
    return false;
}

private boolean checkCycle(Map<Integer, List<Integer>> adj, int src, int[] state) {
    if (state[src] == 1) return true;  // Cycle detected
    if (state[src] == 2) return false; // Already processed

    state[src] = 1; // Mark as visiting

    for (int neighbour : adj.getOrDefault(src, new ArrayList<>())) {
        if (checkCycle(adj, neighbour, state)) {
            return true;  // Cycle found
        }
    }

    state[src] = 2; // Mark as visited (fully processed)
    return false;
}

```

### Pattern 8: Cycle Detection

**Use Cases:** Detect cycles in directed/undirected graphs

**💡 Key Insight:**

- **Directed:** Use 3 colors (white/gray/black) or recursion stack
- **Undirected:** Track parent to avoid false cycle detection

**Time:** O(V + E) | **Space:** O(V)

#### Directed Graph Cycle Detection:

```python
def has_cycle_directed(n, edges):
    """Detect cycle in directed graph"""
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
  
    WHITE, GRAY, BLACK = 0, 1, 2
    color = [WHITE] * n
  
    def dfs(node):
        if color[node] == GRAY:
            return True  # Back edge = cycle
        if color[node] == BLACK:
            return False
  
        color[node] = GRAY
        for neighbor in graph[node]:
            if dfs(neighbor):
                return True
        color[node] = BLACK
        return False
  
    for i in range(n):
        if color[i] == WHITE and dfs(i):
            return True
    return False
```

#### Undirected Graph Cycle Detection:

```python
def has_cycle_undirected(n, edges):
    """Detect cycle in undirected graph"""
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
  
    visited = set()
  
    def dfs(node, parent):
        visited.add(node)
        for neighbor in graph[node]:
            if neighbor not in visited:
                if dfs(neighbor, node):
                    return True
            elif neighbor != parent:
                return True  # Cycle found
        return False
  
    for i in range(n):
        if i not in visited and dfs(i, -1):
            return True
    return False
```

**FAANG Problems:**

- Course Schedule (LC 207)
- Redundant Connection (LC 684)
- Graph Valid Tree (LC 261)

## BFS

### Pattern 5: Cycle Detection in Undirected Graph

**Use Cases:** Validate graph structure, detect redundant connections

**💡 Key Insight:** Track parent during BFS. If we visit a node that's already visited and it's not the parent, cycle exists.

**Time:** O(V + E) | **Space:** O(V)

```java
public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
    HashSet<Integer> visited = new HashSet<>();

    for (int i = 0; i < V; i++) {
        if (!visited.contains(i)) {
            if (bfsCycleCheck(adj, i, visited))
                return true;
        }
    }
    return false;
}

private boolean bfsCycleCheck(ArrayList<ArrayList<Integer>> adj, int src, HashSet<Integer> visited) {
    Queue<int[]> queue = new LinkedList<>();  // [node, parent]
    queue.add(new int[]{src, -1});
    visited.add(src);

    while (!queue.isEmpty()) {
        int[] curr = queue.poll();
        int node = curr[0];
        int parent = curr[1];

        for (int neighbor : adj.get(node)) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.add(new int[]{neighbor, node});
            } else if (neighbor != parent) {
                return true;  // Cycle detected
            }
        }
    }
    return false;
}
```

---

### Pattern 6: Cycle Detection in Directed Graph

**Use Cases:** Course schedule, task dependencies, deadlock detection

**💡 Key Insight:** Use 3-state coloring (unvisited, visiting, visited) or Kahn's algorithm with in-degree.

**Time:** O(V + E) | **Space:** O(V)

#### Method 1: Using State Array (DFS-based)

```java
public boolean isCyclic(int V, Map<Integer, List<Integer>> adj) {
    int[] state = new int[V];  // 0 = unvisited, 1 = visiting, 2 = visited

    for (int i = 0; i < V; i++) {
        if (state[i] == 0 && checkCycle(adj, i, state)) {
            return true;  // Cycle detected
        }
    }
    return false;
}

private boolean checkCycle(Map<Integer, List<Integer>> adj, int src, int[] state) {
    if (state[src] == 1) return true;   // Cycle detected
    if (state[src] == 2) return false;  // Already processed

    state[src] = 1;  // Mark as visiting

    for (int neighbor : adj.getOrDefault(src, new ArrayList<>())) {
        if (checkCycle(adj, neighbor, state)) {
            return true;  // Cycle found
        }
    }

    state[src] = 2;  // Mark as visited (fully processed)
    return false;
}
```

#### Method 2: Using Recursion Stack

```java
public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
    boolean[] visited = new boolean[V];
    boolean[] recStack = new boolean[V];

    for (int i = 0; i < V; i++) {
        if (dfs(adj, i, visited, recStack))
            return true;
    }
    return false;
}

private boolean dfs(ArrayList<ArrayList<Integer>> adj, int src, 
                    boolean[] visited, boolean[] recStack) {
    if (recStack[src])
        return true;
    if (visited[src])
        return false;

    visited[src] = true;
    recStack[src] = true;

    for (int neighbor : adj.get(src)) {
        if (dfs(adj, neighbor, visited, recStack))
            return true;
    }
  
    recStack[src] = false;
    return false;
}
```




**Graph Valid Tree** (LC 261) ⭐⭐

- Cycle detection + connectivity
- Time: O(V + E) | Space: O(V)
