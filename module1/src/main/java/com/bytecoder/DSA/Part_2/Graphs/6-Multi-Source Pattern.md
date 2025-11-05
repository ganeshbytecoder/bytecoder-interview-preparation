# Multi-Source BFS Pattern - Complete Notes

## 🎯 Core Concept

Instead of starting BFS from a  **single source** , start from  **multiple sources simultaneously** . All sources are added to the queue initially and processed level by level. This finds the shortest distance from any source to all other cells.

---

## 📋 When to Use

* Multiple starting points spreading simultaneously
* Finding distance to nearest source from any position
* Time-based propagation (spreading, rotting, flooding)
* Distance calculations from multiple origins
* "Nearest X" problems with multiple X locations

---

## 💡 Key Insight

**Traditional BFS:** One source → explores outward

**Multi-Source BFS:** Multiple sources → all explore simultaneously at same pace

This naturally gives you the **shortest distance from ANY source** to each cell.

---

## ⚙️ Standard Template

```python
from collections import deque

def multi_source_bfs(grid):
    rows, cols = len(grid), len(grid[0])
    queue = deque()
    visited = set()
  
    # Step 1: Add ALL sources to queue initially
    for r in range(rows):
        for c in range(cols):
            if is_source(grid[r][c]):
                queue.append((r, c, 0))  # (row, col, distance/time)
                visited.add((r, c))
  
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
  
    # Step 2: Process level by level
    while queue:
        r, c, dist = queue.popleft()
  
        # Process current cell
        # ... do something with (r, c, dist)
  
        # Step 3: Explore neighbors
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
  
            if nr < 0 or nr >= rows or nc < 0 or nc >= cols:
                continue
            if (nr, nc) in visited:
                continue
            if is_obstacle(grid[nr][nc]):
                continue
  
            visited.add((nr, nc))
            queue.append((nr, nc, dist + 1))
  
    return result
```

---

## 🍊 Pattern 1: Rotting Oranges (Time-Based Spread)

**Problem:** LC 994 - Find minimum time for all oranges to rot

**Use Case:** Time-based propagation, spreading phenomena

**Complexity:** Time O(m × n) | Space O(m × n)

```python
from collections import deque

def orangesRotting(grid):
    """LC 994: Rotting Oranges"""
    if not grid:
        return -1
  
    rows, cols = len(grid), len(grid[0])
    queue = deque()
    fresh_count = 0
  
    # Step 1: Find all initial rotten oranges and count fresh ones
    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == 2:
                queue.append((r, c, 0))  # (row, col, time)
            elif grid[r][c] == 1:
                fresh_count += 1
  
    # If no fresh oranges, return 0
    if fresh_count == 0:
        return 0
  
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    max_time = 0
  
    # Step 2: BFS from all rotten oranges
    while queue:
        r, c, time = queue.popleft()
        max_time = max(max_time, time)
  
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
  
            # Check bounds and if it's a fresh orange
            if (0 <= nr < rows and 0 <= nc < cols and 
                grid[nr][nc] == 1):
      
                grid[nr][nc] = 2  # Make it rotten
                fresh_count -= 1
                queue.append((nr, nc, time + 1))
  
    # If still fresh oranges remain, return -1
    return max_time if fresh_count == 0 else -1
```

**Real-World Application:** Disease spread modeling, viral propagation, fire spreading

---

## 📏 Pattern 2: 01 Matrix (Distance to Nearest)

**Problem:** LC 542 - Find distance to nearest 0 for each cell

**Use Case:** Distance calculation to nearest source

**Complexity:** Time O(m × n) | Space O(m × n)

```python
from collections import deque

def updateMatrix(mat):
    """LC 542: 01 Matrix - Distance to nearest 0"""
    if not mat:
        return []
  
    rows, cols = len(mat), len(mat[0])
    queue = deque()
  
    # Step 1: Add all 0s to queue (sources)
    for r in range(rows):
        for c in range(cols):
            if mat[r][c] == 0:
                queue.append((r, c))
            else:
                mat[r][c] = -1  # Mark as unvisited
  
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
  
    # Step 2: BFS from all 0s
    while queue:
        r, c = queue.popleft()
  
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
  
            # If unvisited (value is -1)
            if (0 <= nr < rows and 0 <= nc < cols and 
                mat[nr][nc] == -1):
      
                mat[nr][nc] = mat[r][c] + 1
                queue.append((nr, nc))
  
    return mat
```

**Real-World Application:** Nearest hospital finder, closest gas station, emergency services planning

---

## 🌊 Pattern 3: As Far from Land as Possible

**Problem:** LC 1162 - Find water cell farthest from any land

**Use Case:** Maximum distance from all sources

**Complexity:** Time O(m × n) | Space O(m × n)

```python
from collections import deque

def maxDistance(grid):
    """LC 1162: As Far from Land as Possible"""
    if not grid:
        return -1
  
    n = len(grid)
    queue = deque()
  
    # Step 1: Add all land cells (1s) to queue
    for r in range(n):
        for c in range(n):
            if grid[r][c] == 1:
                queue.append((r, c))
  
    # Edge cases: all land or all water
    if len(queue) == 0 or len(queue) == n * n:
        return -1
  
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    max_dist = -1
  
    # Step 2: BFS from all land cells
    while queue:
        r, c = queue.popleft()
  
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
  
            # If water cell (0)
            if (0 <= nr < n and 0 <= nc < n and 
                grid[nr][nc] == 0):
      
                grid[nr][nc] = grid[r][c] + 1
                max_dist = max(max_dist, grid[nr][nc] - 1)
                queue.append((nr, nc))
  
    return max_dist
```

**Real-World Application:** Finding most remote ocean location, wildlife refuge planning

---

## 🚪 Pattern 4: Walls and Gates

**Problem:** LC 286 - Fill distances to nearest gate (Premium)

**Use Case:** Distance propagation from specific points

**Complexity:** Time O(m × n) | Space O(m × n)

```python
from collections import deque

def wallsAndGates(rooms):
    """LC 286: Walls and Gates
    -1: wall, 0: gate, INF: empty room
    Fill each empty room with distance to nearest gate
    """
    if not rooms:
        return
  
    rows, cols = len(rooms), len(rooms[0])
    queue = deque()
    INF = 2147483647
  
    # Step 1: Add all gates (0s) to queue
    for r in range(rows):
        for c in range(cols):
            if rooms[r][c] == 0:
                queue.append((r, c))
  
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
  
    # Step 2: BFS from all gates
    while queue:
        r, c = queue.popleft()
  
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
  
            # If empty room (INF)
            if (0 <= nr < rows and 0 <= nc < cols and 
                rooms[nr][nc] == INF):
      
                rooms[nr][nc] = rooms[r][c] + 1
                queue.append((nr, nc))
```

**Real-World Application:** Building evacuation planning, facility placement optimization

---

## 🔥 Pattern 5: Forest Fire Spread

**Problem:** Custom - Simulate fire spreading in forest

**Use Case:** Multiple simultaneous spreading sources

**Complexity:** Time O(m × n × T) | Space O(m × n)

```python
from collections import deque

def forestFireSpread(forest, minutes):
    """
    forest: 0 = empty, 1 = tree, 2 = burning
    Returns grid state after given minutes
    """
    if not forest:
        return forest
  
    rows, cols = len(forest), len(forest[0])
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
  
    for minute in range(minutes):
        queue = deque()
  
        # Find all currently burning trees
        for r in range(rows):
            for c in range(cols):
                if forest[r][c] == 2:
                    queue.append((r, c))
  
        # Spread fire from all burning trees
        while queue:
            r, c = queue.popleft()
  
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
      
                if (0 <= nr < rows and 0 <= nc < cols and 
                    forest[nr][nc] == 1):
          
                    forest[nr][nc] = 2  # Catch fire
  
    return forest
```

**Real-World Application:** Forest fire simulation, wildfire prediction models

---

## 🏥 Pattern 6: Shortest Distance (Multiple Destinations)

**Problem:** Custom - Find shortest distance to any of multiple targets

**Complexity:** Time O(m × n) | Space O(m × n)

```python
from collections import deque

def shortestDistanceToTargets(grid, start, targets):
    """
    Find shortest distance from start to ANY target
    targets: list of (row, col) coordinates
    """
    rows, cols = len(grid), len(grid[0])
    queue = deque([(*start, 0)])  # (row, col, distance)
    visited = {start}
    target_set = set(targets)
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
  
    while queue:
        r, c, dist = queue.popleft()
  
        # Check if we reached any target
        if (r, c) in target_set:
            return dist
  
        for dr, dc in directions:
            nr, nc = r + dr, c + dc
  
            if (0 <= nr < rows and 0 <= nc < cols and 
                (nr, nc) not in visited and grid[nr][nc] != -1):
      
                visited.add((nr, nc))
                queue.append((nr, nc, dist + 1))
  
    return -1  # No target reachable
```

---

## 📝 FAANG Problem List

### Core Multi-Source BFS

* **Rotting Oranges** (LC 994) ⭐⭐⭐ - Time-based spreading
* **01 Matrix** (LC 542) ⭐⭐ - Distance to nearest 0
* **As Far from Land as Possible** (LC 1162) ⭐⭐ - Maximum distance
* **Walls and Gates** (LC 286) ⭐⭐ - Distance to gates (Premium)


## 🎯 Decision Guide

### Use Multi-Source BFS when:

* ✅ Multiple starting points spreading simultaneously
* ✅ Finding distance to **nearest** source (any source)
* ✅ Time-based propagation (rounds/minutes)
* ✅ "Shortest distance to any X" problems

### Don't use Multi-Source BFS when:

* ❌ Need distance to **specific** source
* ❌ Need all pairs shortest paths
* ❌ Weighted graph (use Dijkstra)
* ❌ Need longest path

---

## 🔍 Template Variations

### Variation 1: Track Time/Rounds

```python
time = 0
while queue:
    level_size = len(queue)
    for _ in range(level_size):
        process_node()
    time += 1
```

### Variation 2: Count Remaining Targets

```python
remaining = count_targets()
while queue and remaining > 0:
    if reached_target:
        remaining -= 1
```

### Variation 3: Return Distance Matrix

```python
distances = [[float('inf')] * cols for _ in range(rows)]
# Fill distances for all cells
return distances
```

---
