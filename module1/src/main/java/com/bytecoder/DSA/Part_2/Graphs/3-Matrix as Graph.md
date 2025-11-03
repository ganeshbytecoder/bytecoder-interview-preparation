### 9. Matrix as Graph Pattern

**Use Cases:** Grid problems, islands, shortest path in matrix, flood fill

**💡 Key Insight:** Each cell is a node. Neighbors are adjacent cells (4 or 8 directions). Use DFS/BFS to explore. Mark visited to avoid revisiting.

#### Template:

```python
def solve_matrix(matrix):
    if not matrix:
        return
  
    rows, cols = len(matrix), len(matrix[0])
    visited = set()
    directions = [(0,1), (1,0), (0,-1), (-1,0)]  # 4-directional
  
    def dfs(r, c):
        if (r, c) in visited:
            return
        if r < 0 or r >= rows or c < 0 or c >= cols:
            return
        if matrix[r][c] == 0:  # obstacle or invalid
            return
  
        visited.add((r, c))
  
        for dr, dc in directions:
            dfs(r + dr, c + dc)
  
    # Start DFS from each unvisited cell
    for i in range(rows):
        for j in range(cols):
            if (i, j) not in visited and matrix[i][j] == 1:
                dfs(i, j)
```

**Common Problems:**

* Longest Increasing Path in Matrix (329)
* Number of Islands
* Surrounded Regions


### Pattern 3: Matrix/Grid DFS (Island Problems)

**Use Cases:** Number of islands, flood fill, surrounded regions

**💡 Key Insight:** Treat each cell as a node. 4-directional or 8-directional neighbors.

**Time:** O(rows * cols) | **Space:** O(rows * cols)

```python
def num_islands(grid):
    """Count number of islands in 2D grid (LC 200)"""
    if not grid:
        return 0
  
    rows, cols = len(grid), len(grid[0])
    visited = set()
    count = 0
  
    def dfs(r, c):
        # Boundary checks
        if (r < 0 or r >= rows or c < 0 or c >= cols or
            (r, c) in visited or grid[r][c] == '0'):
            return
    
        visited.add((r, c))
    
        # Explore 4 directions
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        for dr, dc in directions:
            dfs(r + dr, c + dc)
  
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == '1' and (r, c) not in visited:
                dfs(r, c)
                count += 1
  
    return count
```

**Alternative: Modify Grid In-Place (Space Optimized)**

```python
def num_islands_optimized(grid):
    """O(1) extra space by modifying grid"""
    if not grid:
        return 0
  
    rows, cols = len(grid), len(grid[0])
    count = 0
  
    def dfs(r, c):
        if (r < 0 or r >= rows or c < 0 or c >= cols or
            grid[r][c] != '1'):
            return
    
        grid[r][c] = '0'  # Mark as visited
        dfs(r + 1, c)
        dfs(r - 1, c)
        dfs(r, c + 1)
        dfs(r, c - 1)
  
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == '1':
                dfs(r, c)
                count += 1
  
    return count
```

**FAANG Problems:**

- Number of Islands (LC 200)
- Max Area of Island (LC 695)
- Number of Closed Islands (LC 1254)
- Flood Fill (LC 733



**Max Area of Island** (LC 695) ⭐⭐

- Grid DFS with area calculation
- Time: O(m * n) | Space: O(m * n)

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



### Pattern 2: Connected Components

**Use Cases:** Count islands, find provinces, network connectivity

**💡 Key Insight:** Run DFS from each unvisited node. Each DFS call finds one component.

**Time:** O(V + E) | **Space:** O(V)

```python
def count_components(n, edges):
    """Count number of connected components in undirected graph"""
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
  
    visited = set()
    count = 0
  
    def dfs(node):
        if node in visited:
            return False
        visited.add(node)
        for neighbor in graph[node]:
            dfs(neighbor)
        return True
  
    for node in range(n):
        if dfs(node):
            count += 1
  
    return count
```

**FAANG Problems:**

- Number of Provinces (LC 547)
- Number of Connected Components (LC 323)
- Keys and Rooms (LC 841)

### Pattern 4: Connected Components Using BFS

**Use Cases:** Count islands, find provinces, network connectivity

**💡 Key Insight:** Run BFS from each unvisited node. Each BFS finds one component.

**Time:** O(V + E) | **Space:** O(V)

```java
public List<List<Integer>> findConnectedComponents(Map<Integer, List<Integer>> adjList) {
    Set<Integer> visited = new HashSet<>();
    List<List<Integer>> components = new ArrayList<>();

    for (int node : adjList.keySet()) {
        if (!visited.contains(node)) {
            List<Integer> component = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();

            queue.add(node);
            visited.add(node);

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                component.add(curr);

                for (int neighbor : adjList.get(curr)) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            components.add(component);
        }
    }
    return components;
}

// Usage Example
public class Main {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(1, 3));
        graph.put(3, Arrays.asList(2));
        graph.put(4, Arrays.asList(5));
        graph.put(5, Arrays.asList(4));

        System.out.println("Connected Components: " + findConnectedComponents(graph));
        // Output: Connected Components: [[1, 2, 3], [4, 5]]
    }
}
```

**Pacific Atlantic Water Flow** (LC 417) ⭐⭐

- Dual BFS from boundaries
- Time: O(m × n) | Space: O(m × n)


* **Nearest Exit from Entrance in Maze** (LC 1926) ⭐
  - BFS with exit detection
  - Time: O(m × n) | Space: O(m × n)
  - **Real-world:** Emergency evacuation planning
* **Minimum Knight Moves** (LC 1197) ⭐⭐
  - BFS on infinite board
  - Time: O(|x| × |y|) | Space: O(|x| × |y|)
