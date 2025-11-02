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
- Flood Fill (LC 733)
