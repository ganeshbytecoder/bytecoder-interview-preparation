###  [**Flood Fill Algorithm**](https://leetcode.com/problems/flood-fill/description/)
   - Similar to DFS/BFS in a 2D grid. Traverse each connected component starting from the given node.
   - Used in scenarios like the "paint fill" feature in image editing.

**DFS**

```java
class Solution {

     private void dfs(int[][] image, int[] current, int color, int target, boolean[][] visited) {

        if (current[0] >= 0 && current[0] < image.length && current[1] >= 0 && current[1] < image[0].length && image[current[0]][current[1]] == color && !visited[current[0]][current[1]] ) {
                
        // Mark this cell as visited
        visited[current[0]][current[1]] = true;

        image[current[0]][current[1]] = target;

        // Directions for movement: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int x = current[0];
            int y = current[1];
            
            x += dir[0];
            y += dir[1];
       

            dfs(image, new int[]{x, y}, color, target, visited);
        }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        boolean[][] visited = new boolean[image.length][image[0].length];
        dfs(image, new int[]{sr,sc}, image[sr][sc],color, visited);
        return image;
        
    }
}
```


```python 

def dfs(self, matrix, start,  i, j, color, visited):
    if(i>=0 and j>=0 and i < len(matrix) and j <len(matrix[0]) and visited[i][j] == -1):

        visited[i][j]=1
        matrix[i][j] = color
        dir = [[0,1],  [0,-1],[1,0], [-1, 0]]
        for row, col in dir:
            row +=i
            col +=j
            if(row>=0 and col>=0 and row < len(matrix) and col <len(matrix[0])  and matrix[row][col]==start and  visited[row][col] == -1):   
                self.dfs(matrix, start, row, col, color, visited)


```




https://leetcode.com/problems/01-matrix/description/ 

https://leetcode.com/problems/rotting-oranges/description/

https://leetcode.com/problems/word-search/?envType=problem-list-v2&envId=array&status=TO_DO 

https://leetcode.com/problems/number-of-islands/description/?envType=problem-list-v2&envId=matrix 

https://leetcode.com/problems/number-of-closed-islands/description/?envType=problem-list-v2&envId=union-find

https://leetcode.com/problems/01-matrix/description/


https://leetcode.com/problems/unique-paths/ 

https://leetcode.com/problems/max-area-of-island/description/?envType=problem-list-v2&envId=union-find 





### 6. **Search in a Maze**
The LeetCode problem "The Maze" (Problem 490) presents a maze represented by a binary 2D array, where `0` indicates open spaces and `1` represents walls. A ball can roll in four directions—up, down, left, or right—continuing in a chosen direction until it hits a wall. Upon hitting a wall, the ball can choose a new direction to roll. The objective is to determine if the ball, starting from a given position, can reach a specified destination within the maze.

**Problem Details:**

- **Input:**
  - `maze`: A 2D binary array representing the maze.
  - `start`: An array `[start_row, start_col]` indicating the ball's starting position.
  - `destination`: An array `[destination_row, destination_col]` indicating the target position.

- **Output:**
  - Return `true` if the ball can stop at the destination; otherwise, return `false`.

**Example:**

```python
Input:
  maze = [
    [0, 0, 1, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 0, 1, 0],
    [1, 1, 0, 1, 1],
    [0, 0, 0, 0, 0]
  ]
  start = [0, 4]
  destination = [4, 4]

Output: true
```

**Solution Approach:**

To solve this problem, we can utilize graph traversal algorithms such as Depth-First Search (DFS) or Breadth-First Search (BFS). The maze can be viewed as a graph where each open space is a node, and rolling in one of the four directions corresponds to traversing edges to other nodes.

**Breadth-First Search (BFS) Approach:**

1. **Initialize Structures:**
   - Use a queue to manage the positions to explore, starting with the initial position.
   - Maintain a set or matrix to record visited positions to prevent redundant processing.

2. **Process the Queue:**
   - Dequeue the current position.
   - For each of the four possible directions, roll the ball until it hits a wall.
   - Check the position where the ball stops:
     - If it matches the destination, return `true`.
     - If it hasn't been visited, mark it as visited and enqueue it for further exploration.

3. **Termination:**
   - If the queue is exhausted without reaching the destination, return `false`.

**Depth-First Search (DFS) Approach:**

1. **Recursive Exploration:**
   - From the current position, recursively explore each direction by rolling the ball until it hits a wall.
   - If the ball stops at the destination, return `true`.
   - Mark the current position as visited to avoid cycles.

2. **Backtracking:**
   - If all directions have been explored from the current position without success, backtrack to the previous position.

3. **Termination:**
   - If all possible paths have been explored and the destination hasn't been reached, return `false`.

### **Key Differences Between BFS and DFS**
- **BFS** explores all positions level by level and is generally better suited for finding the shortest path in unweighted graphs.
- **DFS** explores as far as possible along one branch before backtracking, which may result in a solution but not necessarily the shortest one.


---

### **Breadth-First Search (BFS) Solution**
```java
import java.util.LinkedList;
import java.util.Queue;

public class TheMaze {

    public boolean hasPathBFS(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;

        // Directions for movement: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            // If we reached the destination, return true
            if (current[0] == destination[0] && current[1] == destination[1]) {
                return true;
            }

            for (int[] dir : directions) {
                int x = current[0];
                int y = current[1];

                // Roll the ball until it hits a wall
                while (x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }

                // Step back to the last valid position
                x -= dir[0];
                y -= dir[1];

                if (!visited[x][y]) {
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TheMaze mazeSolver = new TheMaze();
        int[][] maze = {
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0}
        };
        int[] start = {0, 4};
        int[] destination = {4, 4};

        System.out.println(mazeSolver.hasPathBFS(maze, start, destination)); // Output: true
    }
}
```

---

### **Depth-First Search (DFS) Solution**
```java
public class TheMazeDFS {

    public boolean hasPathDFS(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];

        return dfs(maze, start, destination, visited);
    }

    private boolean dfs(int[][] maze, int[] current, int[] destination, boolean[][] visited) {
        // If we've visited this cell already, return false
        if (visited[current[0]][current[1]]) {
            return false;
        }

        // Mark this cell as visited
        visited[current[0]][current[1]] = true;

        // If we've reached the destination, return true
        if (current[0] == destination[0] && current[1] == destination[1]) {
            return true;
        }

        // Directions for movement: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int x = current[0];
            int y = current[1];

            // Roll the ball until it hits a wall
            while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
            }

            // Step back to the last valid position
            x -= dir[0];
            y -= dir[1];

            // Explore this position further
            if (dfs(maze, new int[]{x, y}, destination, visited)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TheMazeDFS mazeSolver = new TheMazeDFS();
        int[][] maze = {
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0}
        };
        int[] start = {0, 4};
        int[] destination = {4, 4};

        System.out.println(mazeSolver.hasPathDFS(maze, start, destination)); // Output: true
    }
}
```

---


### 7. **Minimum Step by Knight**
   - Treat the chessboard as a graph with each square as a node.
   - Use BFS starting from the knight’s position to calculate the minimum number of moves to the destination.


The LeetCode problem **"Minimum Knight Moves"** (Problem 1197) challenges you to determine the minimum number of moves a knight requires to travel from the origin \([0, 0]\) to a target position \([x, y]\) on an infinite chessboard.

**Problem Description:**

- **Input:**
  - Two integers, `x` and `y`, representing the target coordinates.

- **Output:**
  - An integer representing the minimum number of knight moves to reach \([x, y]\) from \([0, 0]\).

- **Knight's Movement:**
  - A knight moves in an "L" shape: two squares in one direction and then one square perpendicular, resulting in eight possible moves.

**Solution Approach:**

Given the infinite nature of the chessboard and the uniform movement cost, a **Breadth-First Search (BFS)** is appropriate for finding the shortest path. BFS explores all positions at the current depth before moving deeper, ensuring the shortest path is found.

**Steps:**

1. **Normalize Target Coordinates:**
   - Since the chessboard is symmetric with respect to the origin and knight's moves are symmetric, consider only the first quadrant by taking the absolute values of `x` and `y`. This reduces computational complexity.

2. **Initialize BFS Structures:**
   - Use a queue to manage positions to explore, starting with \([0, 0]\).
   - Maintain a set to record visited positions to prevent reprocessing.

3. **Define Possible Moves:**
   - List the eight potential moves a knight can make.

4. **Execute BFS:**
   - Iteratively process each position:
     - If it matches the target, return the current move count.
     - Otherwise, compute all valid moves, enqueue unvisited positions, and mark them as visited.

5. **Terminate Upon Reaching Target:**
   - The BFS ensures that when the target is reached, it's via the shortest path due to its level-order exploration.

**Java Implementation:**

```java
import java.util.*;

public class MinimumKnightMoves {
    public int minKnightMoves(int x, int y) {
        // Normalize coordinates to the first quadrant
        x = Math.abs(x);
        y = Math.abs(y);

        // Possible knight moves
        int[][] directions = {
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
        };

        // BFS initialization
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new int[]{0, 0});
        visited.add("0,0");
        int moves = 0;

        // Perform BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int currX = current[0];
                int currY = current[1];

                // Check if target is reached
                if (currX == x && currY == y) {
                    return moves;
                }

                // Explore all possible knight moves
                for (int[] dir : directions) {
                    int newX = currX + dir[0];
                    int newY = currY + dir[1];
                    String key = newX + "," + newY;

                    // Only consider positions within bounds and not yet visited
                    if (!visited.contains(key) && newX >= -2 && newY >= -2) {
                        queue.offer(new int[]{newX, newY});
                        visited.add(key);
                    }
                }
            }
            moves++;
        }

        // If the target is unreachable (the problem guarantees it's reachable)
        return -1;
    }

    public static void main(String[] args) {
        MinimumKnightMoves solver = new MinimumKnightMoves();
        System.out.println(solver.minKnightMoves(2, 1)); // Output: 1
        System.out.println(solver.minKnightMoves(5, 5)); // Output: 4
    }
}
```

**Explanation:**

- **Normalization:**
  - By converting `x` and `y` to their absolute values, the problem is confined to the first quadrant, leveraging symmetry to reduce the search space.

- **BFS Execution:**
  - Starting from \([0, 0]\), the algorithm explores all possible moves level by level.
  - Each position is encoded as a string "x,y" for efficient lookup in the visited set.
  - The condition `newX >= -2 && newY >= -2` ensures that the search does not go unnecessarily far into negative coordinates, optimizing performance.

- **Termination:**
  - The BFS guarantees that the first time the target \([x, y]\) is reached, it's via the shortest path, so the current move count is returned immediately.

---

### ** DFS Approach**
- **Recursive Exploration**: Explore one path fully before moving to another.
- **Memoization**: Use a map to store results for already computed positions to avoid redundant calculations, making DFS more efficient.
- **Symmetry Optimization**: Leverage the symmetry of the chessboard to reduce the search space by working only in the first quadrant.

---

### **DFS Algorithm**
1. **Normalize Coordinates**:
   - Use the absolute values of `x` and `y` to confine the problem to the first quadrant.

2. **Base Cases**:
   - If the current position \([x, y]\) is the origin \([0, 0]\), return `0` moves.
   - For positions near the origin, like \([1, 1]\), \([2, 0]\), or \([0, 2]\), return `1` as only one move is required.

3. **Recursive Exploration**:
   - From the current position, calculate the minimum moves needed to reach the destination by trying all 8 possible knight moves.
   - Memoize the result for \([x, y]\) to avoid redundant calculations.

4. **Return the Result**:
   - Use the memoized values to compute and return the minimum moves required for the knight to reach the target.

---

### **Java Implementation of DFS with Memoization**
```java
import java.util.HashMap;
import java.util.Map;

public class MinimumKnightMovesDFS {

    private final int[][] directions = {
        {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
        {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };

    private final Map<String, Integer> memo = new HashMap<>();

    public int minKnightMoves(int x, int y) {
        // Normalize coordinates to the first quadrant
        x = Math.abs(x);
        y = Math.abs(y);

        return dfs(x, y);
    }

    private int dfs(int x, int y) {
        // Base cases
        if (x == 0 && y == 0) return 0;
        if (x + y == 2) return 2; // Special case for positions like [2, 0] or [1, 1]

        // Create a unique key for memoization
        String key = x + "," + y;

        // Check if result is already memoized
        if (memo.containsKey(key)) return memo.get(key);

        // Recursively calculate the minimum moves
        int minMoves = Integer.MAX_VALUE;
        for (int[] dir : directions) {
            int newX = Math.abs(x - dir[0]);
            int newY = Math.abs(y - dir[1]);
            minMoves = Math.min(minMoves, dfs(newX, newY));
        }

        // Store the result in the memoization map
        memo.put(key, minMoves + 1);
        return minMoves + 1;
    }

    public static void main(String[] args) {
        MinimumKnightMovesDFS solver = new MinimumKnightMovesDFS();
        System.out.println(solver.minKnightMoves(2, 1)); // Output: 1
        System.out.println(solver.minKnightMoves(5, 5)); // Output: 4
    }
}
```

---

### **DFS Steps Explained**
1. **Symmetry Optimization**:
   - By taking the absolute values of `x` and `y`, we restrict the computation to one quadrant, significantly reducing the number of recursive calls.

2. **Base Cases**:
   - Directly return results for trivial cases like \([0, 0]\) or \([1, 1]\), reducing unnecessary recursion.

3. **Recursive Calls**:
   - For each of the 8 possible knight moves, calculate the new position \([newX, newY]\) and recursively determine the minimum moves needed to reach the destination.

4. **Memoization**:
   - Results are cached in a `Map<String, Integer>` using a unique key based on the coordinates. This prevents redundant calculations for positions already visited in the recursion tree.

5. **Termination**:
   - The recursion terminates when the base cases are reached. The memoized results are used to build the solution for higher levels of the recursion tree.




### 1. **Spiral Traversal on a Matrix**
- **Hint**: Maintain four boundaries: top, bottom, left, and right. Traverse the matrix in a spiral order by adjusting these boundaries after each traversal in one of the four directions (left to right, top to bottom, right to left, and bottom to top).

### 2. **Search an Element in a Matrix**
- **Hint**: If the matrix is sorted row-wise and column-wise, start the search from the top-right corner. If the target is smaller, move left; if larger, move down. This approach works in O(n + m) time.

### 3. **Find Median in a Row-Wise Sorted Matrix**
- **Hint**: Use binary search on the possible range of matrix elements. For each middle value in the binary search, count how many elements are less than or equal to it by using binary search in each row.

### 4. **Find Row with Maximum Number of 1s**
- **Hint**: Start from the top-right corner of the matrix. If you encounter a 1, move left; otherwise, move down. Keep track of the row index where you move left the most.

### 5. **Print Elements in Sorted Order Using Row-Column Wise Sorted Matrix**
- **Hint**: Use a min-heap (or priority queue) to extract the minimum element. Insert the next element from the same row into the heap until all elements are processed.

### 6. **Maximum Size Rectangle (In a Binary Matrix)**
- **Hint**: Treat each row as a histogram. Use a stack-based method to find the largest rectangle in a histogram. Iterate through rows, updating the height of the histogram at each step.

### 7. **Find a Specific Pair in Matrix**
- **Hint**: Maintain an auxiliary matrix that stores the maximum element from the bottom-right corner to the current position. For each element, compare it with the corresponding maximum in the auxiliary matrix and update accordingly.

### 8. **Rotate Matrix by 90 Degrees**
- **Hint**: First, transpose the matrix (swap rows with columns). Then reverse each row to achieve a 90-degree clockwise rotation.

### 9. **Kth Smallest Element in a Row-Column Wise Sorted Matrix**
- **Hint**: Use a min-heap (or priority queue). Start by inserting the smallest element of each row into the heap. Extract the minimum element and insert the next element from the same row until you reach the Kth smallest element.

### 10. **Common Elements in All Rows of a Given Matrix**
- **Hint**: Use a hash map to count the frequency of each element across rows. Traverse the matrix row by row, and only update the count for elements present in all previous rows.

