### Pattern 3: Multi-Source BFS

**Use Cases:** Rotting oranges, 01 matrix, multiple starting points

**💡 Key Insight:** Add all source nodes to queue initially. Process them level by level.

**Time:** O(rows × cols) | **Space:** O(rows × cols)

#### Rotting Oranges Problem (LC 994):

```java
public int orangesRotting(int[][] grid) {
    Queue<int[]> queue = new LinkedList<>();
    int fresh = 0;
    int time = 0;

    // Add all rotten oranges to queue and count fresh oranges
    for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[0].length; c++) {
            if (grid[r][c] == 2)
                queue.add(new int[]{r, c});
            if (grid[r][c] == 1)
                fresh++;
        }
    }

    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    while (!queue.isEmpty() && fresh > 0) {
        int sz = queue.size();
        for (int i = 0; i < sz; i++) {
            int[] rotten = queue.poll();
            int row = rotten[0], col = rotten[1];

            for (int[] dir : directions) {
                int r = dir[0] + row, c = dir[1] + col;
                boolean rowBound = 0 <= r && r < grid.length;
                boolean colBound = 0 <= c && c < grid[0].length;

                if (rowBound && colBound && grid[r][c] == 1) {
                    grid[r][c] = 2;
                    queue.add(new int[]{r, c});
                    fresh--;
                }
            }
        }
        time++;
    }

    return fresh == 0 ? time : -1;
}
```

**Real-World Application:** Disease spread modeling (e.g., COVID-19 spread simulation)


* **Rotting Oranges** (LC 994) ⭐⭐⭐
  - Multi-source BFS
  - Time: O(m × n) | Space: O(m × n)
  - **Real-world:** Disease spread modeling (COVID-19)
* **01 Matrix** (LC 542) ⭐⭐
  - Multi-source BFS, distance calculation
  - Time: O(m × n) | Space: O(m × n)
  - **Real-world:** Nearest hospital in city grid
* **As Far from Land as Possible** (LC 1162) ⭐
  * Multi-source BFS from land cells
  * Time: O(m × n) | Space: O(m × n)
* 

**Walls and Gates** (LC 286) ⭐

- Multi-source BFS (Premium)
- Time: O(m × n) | Space: O(m × n)
