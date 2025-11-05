# Matrix as Graph Pattern - Complete Notes

## 🎯 Core Concept

Treat each cell in a matrix as a **node** in a graph. Adjacent cells are  **neighbors** . Use DFS/BFS to explore the graph structure.

---

## 📋 When to Use

* Grid/island problems
* Flood fill operations
* Shortest path in matrix
* Connected regions
* Boundary-based problems

---

## ⚙️ Key Components

### 1. Directions Array

```python
# 4-directional (up, right, down, left)
directions = [(0,1), (1,0), (0,-1), (-1,0)]

# 8-directional (includes diagonals)
directions = [(0,1), (1,0), (0,-1), (-1,0), (1,1), (1,-1), (-1,1), (-1,-1)]
```

### 2. Boundary Checks

```python
if r < 0 or r >= rows or c < 0 or c >= cols:
    continue  # Skip invalid cell
```

### 3. Visited Tracking

* **Option A:** Use `visited` set → O(rows × cols) space
* **Option B:** Modify grid in-place → O(1) extra space

---

## 🔥 Pattern 1: Basic Matrix DFS

---

## 🏝️ Pattern 2: Island Counting (Connected Components)

**Problem:** Number of Islands (LC 200), explore connected components

**Complexity:** Time O(m × n) | Space O(m × n)

### Version A: Using Visited Set

```python
def dfs_island(grid, r, c, visited, rows, cols):
    stack = [(r, c)]
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
  
    while stack:
        curr_r, curr_c = stack.pop()
  
        if curr_r < 0 or curr_r >= rows or curr_c < 0 or curr_c >= cols:
            continue
        if (curr_r, curr_c) in visited:
            continue
        if grid[curr_r][curr_c] == '0':
            continue
  
        visited.add((curr_r, curr_c))
  
        for dr, dc in directions:
            stack.append((curr_r + dr, curr_c + dc))

def dfs_island(grid, r, c, visited, rows, cols):
    if r < 0 or r >= rows or c < 0 or c >= cols:
        return
    if (r, c) in visited:
        return
    if grid[r][c] == '0':
        return
  
    visited.add((r, c))
  
    # Explore 4 directions
    dfs_island(grid, r + 1, c, visited, rows, cols)
    dfs_island(grid, r - 1, c, visited, rows, cols)
    dfs_island(grid, r, c + 1, visited, rows, cols)
    dfs_island(grid, r, c - 1, visited, rows, cols)



def num_islands(grid):
    if not grid:
        return 0
  
    rows, cols = len(grid), len(grid[0])
    visited = set()
    count = 0
  
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == '1' and (r, c) not in visited:
                dfs_island(grid, r, c, visited, rows, cols)
                count += 1
  
    return count
```

### Version B: In-Place Modification (Space Optimized)

```python
def dfs_island_inplace(grid, r, c, rows, cols):
    stack = [(r, c)]
  
    while stack:
        curr_r, curr_c = stack.pop()
  
        if curr_r < 0 or curr_r >= rows or curr_c < 0 or curr_c >= cols:
            continue
        if grid[curr_r][curr_c] != '1':
            continue
  
        grid[curr_r][curr_c] = '0'  # Mark as visited
  
        stack.append((curr_r + 1, curr_c))
        stack.append((curr_r - 1, curr_c))
        stack.append((curr_r, curr_c + 1))
        stack.append((curr_r, curr_c - 1))

def dfs_island_inplace(grid, r, c, rows, cols):
    if r < 0 or r >= rows or c < 0 or c >= cols:
        return
    if grid[r][c] != '1':
        return
  
    grid[r][c] = '0'  # Mark as visited
  
    # Explore 4 directions
    dfs_island_inplace(grid, r + 1, c, rows, cols)
    dfs_island_inplace(grid, r - 1, c, rows, cols)
    dfs_island_inplace(grid, r, c + 1, rows, cols)
    dfs_island_inplace(grid, r, c - 1, rows, cols)

def num_islands_optimized(grid):
    if not grid:
        return 0
  
    rows, cols = len(grid), len(grid[0])
    count = 0
  
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == '1':
                dfs_island_inplace(grid, r, c, rows, cols)
                count += 1
  
    return count
```

---

## 🔲 Pattern 3: Boundary DFS (Edge-to-Center)

**Use Case:** Surrounded regions, enclaves, territories

**Key Insight:** Start from edges, mark reachable cells, remaining cells are "surrounded"

**Complexity:** Time O(m × n) | Space O(m × n)

```python
def dfs_boundary(board, r, c, rows, cols):
    stack = [(r, c)]
  
    while stack:
        curr_r, curr_c = stack.pop()
  
        if curr_r < 0 or curr_r >= rows or curr_c < 0 or curr_c >= cols:
            continue
        if board[curr_r][curr_c] != 'O':
            continue
  
        board[curr_r][curr_c] = 'T'  # Temporary mark
  
        stack.append((curr_r + 1, curr_c))
        stack.append((curr_r - 1, curr_c))
        stack.append((curr_r, curr_c + 1))
        stack.append((curr_r, curr_c - 1))

def dfs_boundary(board, r, c, rows, cols):
    if r < 0 or r >= rows or c < 0 or c >= cols:
        return
    if board[r][c] != 'O':
        return
  
    board[r][c] = 'T'  # Temporary mark
  
    # Explore 4 directions
    dfs_boundary(board, r + 1, c, rows, cols)
    dfs_boundary(board, r - 1, c, rows, cols)
    dfs_boundary(board, r, c + 1, rows, cols)
    dfs_boundary(board, r, c - 1, rows, cols)

def surrounded_regions(board):
    if not board:
        return
  
    rows, cols = len(board), len(board[0])
  
    # Step 1: Mark boundary-connected 'O's
    for r in range(rows):
        dfs_boundary(board, r, 0, rows, cols)         # Left edge
        dfs_boundary(board, r, cols - 1, rows, cols)  # Right edge
  
    for c in range(cols):
        dfs_boundary(board, 0, c, rows, cols)         # Top edge
        dfs_boundary(board, rows - 1, c, rows, cols)  # Bottom edge
  
    # Step 2: Capture surrounded & restore boundary-connected
    for r in range(rows):
        for c in range(cols):
            if board[r][c] == 'O':
                board[r][c] = 'X'  # Surrounded → capture
            elif board[r][c] == 'T':
                board[r][c] = 'O'  # Boundary-connected → restore
```

---

## 🎯 Pattern 5: Max Area of Island

**Problem:** Max Area of Island (LC 695)

**Complexity:** Time O(m × n) | Space O(m × n)

```python
    def dfs(self, grid: List[List[int]], r: int, c: int, visited: List[List[bool]]) -> int:
        # Boundary and base checks
        if (r < 0 or r >= len(grid) or
            c < 0 or c >= len(grid[0]) or
            grid[r][c] == 0 or visited[r][c]):
            return 0

        # Mark current cell as visited
        visited[r][c] = True

        # Explore 4 directions
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        area = 1  # current cell

        for dr, dc in directions:
            area += self.dfs(grid, r + dr, c + dc, visited)

        return area


def dfs_area(grid, r, c, visited, rows, cols):
    stack = [(r, c)]
    area = 0
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
  
    while stack:
        curr_r, curr_c = stack.pop()
  
        if curr_r < 0 or curr_r >= rows or curr_c < 0 or curr_c >= cols:
            continue
        if (curr_r, curr_c) in visited:
            continue
        if grid[curr_r][curr_c] == 0:
            continue
  
        visited.add((curr_r, curr_c))
        area += 1
  
        for dr, dc in directions:
            stack.append((curr_r + dr, curr_c + dc))
  
    return area

def max_area_of_island(grid):
    if not grid:
        return 0
  
    rows, cols = len(grid), len(grid[0])
    visited = set()
    max_area = 0
  
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == 1 and (r, c) not in visited:
                area = dfs_area(grid, r, c, visited, rows, cols)
                max_area = max(max_area, area)
  
    return max_area
```

---

## 📝 FAANG Problem List

### Island Problems

* **Number of Islands** (LC 200) ⭐⭐
* **Max Area of Island** (LC 695) ⭐⭐
* **Number of Closed Islands** (LC 1254) ⭐⭐
* **Flood Fill** (LC 733) ⭐

### Boundary Problems

* **Surrounded Regions** (LC 130) ⭐⭐
* **Number of Enclaves** (LC 1020) ⭐⭐
* **Pacific Atlantic Water Flow** (LC 417) ⭐⭐⭐

### Connected Components

* **Number of Provinces** (LC 547) ⭐⭐
* **Number of Connected Components** (LC 323) ⭐⭐
* **Keys and Rooms** (LC 841) ⭐

### Path Problems

* **Longest Increasing Path in Matrix** (LC 329) ⭐⭐⭐
* **Nearest Exit from Entrance in Maze** (LC 1926) ⭐⭐
* **Minimum Knight Moves** (LC 1197) ⭐⭐

---

## 💡 Key Decision Points

| Scenario                  | Use DFS         | Use BFS          |
| ------------------------- | --------------- | ---------------- |
| Count components          | ✅              | ✅               |
| Find shortest path        | ❌              | ✅               |
| Explore all paths         | ✅              | ❌               |
| Memory constraints        | ✅ (uses stack) | ❌ (needs queue) |
| Layer-by-layer processing | ❌              | ✅               |
