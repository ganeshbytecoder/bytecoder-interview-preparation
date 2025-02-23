## **Matrix Pattern**




## 5. Common Interview Strategy for Matrix DP

1. **Restate** the problem with your own words (make sure it’s a grid-based DP).
2. **Define `dp[i][j]`** precisely.
3. **Check** how many neighbors or transitions you have (top, left, diagonals, etc.).
4. **Write** the recurrence relation.
5. **Initialize** for boundary cases.
6. **Fill** the matrix in a logical order. (Sometimes row-wise, sometimes diagonal iteration if dependencies come from both left and top-left, etc.)
7. **Verify** your solution with a small example.



## **Matrix Multiplication Variants**
1. **[Minimum Score Triangulation of Polygon](https://leetcode.com/problems/minimum-score-triangulation-of-polygon/)** - DP with range.
2. **[Minimum Cost Tree from Leaf Values](https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/)** - DP/Greedy.
3. **[Burst Balloons](https://leetcode.com/problems/burst-balloons/)** - DP with range partitioning.

---

## **Matrix/2D Array Problems**
1. **[Matrix Block Sum](https://leetcode.com/problems/matrix-block-sum/)** - Prefix sum for efficient block sum queries.
2. **[Range Sum Query 2D Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/)** - Prefix sum table.
3. **[Dungeon Game](https://leetcode.com/problems/dungeon-game/)** - DP bottom-up to compute min health.
4. **[Triangle](https://leetcode.com/problems/triangle/)** - DP from bottom to top.
5. **[Maximal Square](https://leetcode.com/problems/maximal-square/)** - DP to find largest square of `1`s.
6. **[Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/)** - DP on rows.

---

## 6. Additional Practice Problems
- Grid traversal
- Path finding
1. **LeetCode 64. Minimum Path Sum**
2. **LeetCode 62/63. Unique Paths (and Unique Paths II)**
3. **LeetCode 221. Maximal Square**
4. **LeetCode 85. Maximal Rectangle**
5. **LeetCode 329. Longest Increasing Path in a Matrix**
6. **LeetCode 174. Dungeon Game** (min HP path)
7. **LeetCode 120. Triangle** (can be viewed as matrix DP)
8. **[Out of Boundary Paths](https://leetcode.com/problems/out-of-boundary-paths/)** - DFS + DP to track valid paths.





## 1. Introduction to Matrix DP

In many grid- or matrix-based problems, we aim to find a minimum/maximum value (cost, path length, etc.), count distinct ways, or identify specific structures (e.g., largest square of 1’s) within the matrix. DP comes into play when:

1. We see **optimal substructure**: The best solution for a submatrix or subproblem can be extended or combined to build the solution for the entire matrix.
2. We have **overlapping subproblems**: We repeatedly solve the same smaller problem (e.g., computing something for cell `(i, j)`) in multiple different ways if we do not use DP.

### Typical DP Approach

1. **DP State**: Often, we create a 2D array `dp` of the same size as the input matrix. `dp[i][j]` might represent:
    - A computed cost/value for the subproblem ending at `(i, j)`
    - The number of ways to get to `(i, j)`
    - A maximum or minimum path sum involving `(i, j)`
    - The size of a structure (like the largest square ending at `(i, j)`)

2. **Transition / Recurrence**: We figure out how `dp[i][j]` relates to neighboring cells (commonly `dp[i-1][j]`, `dp[i][j-1]`, `dp[i-1][j-1]`, etc.).

3. **Initialization / Base Cases**: We set up the edges or corners (e.g., first row, first column) or handle special conditions (obstacles, boundaries).

4. **Answer**: Usually, the result is `dp[m-1][n-1]`, or sometimes the maximum/minimum among all `dp[i][j]`.

---

## 2. Classic Matrix DP Examples

Below are some well-known matrix DP problems, each with a real-life analogy to aid understanding.

---

### 2.1 Minimum Path Sum

**Problem Statement**  
Given an `m x n` grid, each cell containing a non-negative integer, find the path from top-left to bottom-right which minimizes the sum of the numbers in the cells. You can move only down or right.

1. **DP Definition**  
   \[
   dp[i][j] = \text{minimum path sum to reach cell }(i, j).
   \]

2. **Recurrence**  
   \[
   dp[i][j] = \min(dp[i-1][j], dp[i][j-1]) + \text{grid}[i][j]
   \]  
   We take the minimum of coming from the top `(i-1, j)` or left `(i, j-1)`.

3. **Base Cases**
    - `dp[0][0] = grid[0][0]`
    - First row: `dp[0][j] = dp[0][j-1] + grid[0][j]`
    - First column: `dp[i][0] = dp[i-1][0] + grid[i][0]`

4. **Result**
    - `dp[m-1][n-1]` gives the minimum path sum.

**Real-Life Example**  
Imagine you’re in a grocery store laid out as a grid. Each aisle (cell) has an associated “cost” (time or effort). You want to get from the entrance (top-left) to the cashier (bottom-right) with the **least** total cost/effort. You can only move down or right.

---

### 2.2 Unique Paths (Without or With Obstacles)

**Problem Statement**  
Count the number of ways a robot can move from the top-left corner to the bottom-right corner of an `m x n` grid by only moving right or down. Cells may be blocked (obstacles).

1. **DP Definition**  
   \[
   dp[i][j] = \text{number of ways to reach }(i, j).
   \]

2. **Recurrence**  
   \[
   dp[i][j] =
   \begin{cases}
   0 & \text{if cell }(i,j)\text{ is blocked},\\
   dp[i-1][j] + dp[i][j-1] & \text{otherwise}.
   \end{cases}
   \]

3. **Base Cases**
    - `dp[0][0] = 1` if the starting cell is not blocked.
    - For first row/column, if a cell is blocked, all subsequent cells in that row/column are 0 ways.

4. **Result**
    - `dp[m-1][n-1]` is the total number of distinct paths.

**Real-Life Example**  
Consider navigating a warehouse with rows and aisles. Aisles might be blocked due to shelving or storage. How many ways can you move goods from one corner to the opposite corner if you can only move right or down?

---

### 2.3 Maximal Square (in a 2D Binary Matrix)

**Problem Statement**  
Given a 2D binary matrix of 0s and 1s, find the largest square containing only 1s and return its area.

1. **DP Definition**  
   \[
   dp[i][j] = \text{side length of the largest square whose bottom-right corner is }(i, j).
   \]

2. **Recurrence**  
   \[
   dp[i][j] =
   \begin{cases}
   0 & \text{if } \text{matrix}[i][j] = 0, \\
   1 + \min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) & \text{if } \text{matrix}[i][j] = 1.
   \end{cases}
   \]

   We take the minimum of the three neighboring squares (top, left, top-left) because the current cell can only form a bigger square if all three neighbors can form a square of some side length.

3. **Base Cases**
    - `dp[i][j] = matrix[i][j]` if `i = 0` or `j = 0`, because along the first row or first column, the largest square is just 1 if it’s a 1, or 0 otherwise.

4. **Result**
    - Track the maximum `dp[i][j]` across the grid, say `max_side`. The answer for the **area** is `max_side^2`.

**Real-Life Example**  
Imagine you have a plot of land represented by a grid, where `1` indicates “suitable soil” and `0` indicates “unsuitable soil.” You want to find the largest square region of suitable soil to build a square structure.

---

### 2.4 Largest Rectangle in a Binary Matrix

**Problem Statement**  
Similar to the “largest rectangle in a histogram” problem, but you’re given a matrix of 0s and 1s. Find the largest rectangular area consisting entirely of 1s.

**High-Level Approach**
1. Convert each row to a histogram by counting consecutive 1s vertically:  
   \[
   \text{height}[j] =
   \begin{cases}
   \text{height}[j] + 1 & \text{if } \text{matrix}[i][j] = 1, \\
   0 & \text{if } \text{matrix}[i][j] = 0.
   \end{cases}
   \]
2. For each row `i`, treat `height` as an array and find the largest rectangle in this histogram.
3. Keep track of the global maximum.

**Real-Life Example**  
Think of stacking bricks in columns (each column’s height is the count of continuous 1s). Each row in the matrix represents a “layer.” You want to find the largest contiguous rectangular block of stacked bricks. This approach is used, for example, in image processing to find large rectangular areas of interest in a binary image.

---

### 2.5 Longest Increasing Path in a Matrix

**Problem Statement**  
Find the length of the longest path of strictly increasing values in a matrix. You can move in 4 directions (up, down, left, right).

1. **Key Insight**
    - This problem can still leverage DP, but often implemented with **DFS + Memo**.
    - `dp[i][j]` = the length of the longest increasing path starting from `(i, j)`.

2. **Recursive Relation (DFS)**  
   \[
   dp[i][j] = 1 + \max(\text{neighbors }(x,y)\text{ where matrix[x][y] > matrix[i][j]} ~ dp[x][y])
   \]

3. **Compute**
    - We do a DFS from each cell `(i, j)`, storing results in `dp[i][j]`.
    - If a cell’s `dp[i][j]` is already computed, reuse it.

4. **Result**
    - The answer is the max over all `(i, j)` of `dp[i][j]`.

**Real-Life Example**  
Imagine an elevation map (matrix). You want to find the longest hiking trail where each step leads to higher ground. Each cell is a point with some elevation, and you can move in 4 directions. DP helps track the longest upward path from each point.

---

## 3. Practical Tips for Matrix DP

1. **Identify the Subproblem**
    - Clearly state what `dp[i][j]` represents: a count, a min/max path sum, or the size of a particular structure.

2. **Careful with Boundaries**
    - Often, you need to handle row 0 or column 0 carefully (base cases).

3. **Space Optimization**
    - Sometimes you only need the previous row or previous column. This allows you to reduce space from `O(m*n)` to `O(n)` or `O(m)`.

4. **Dealing with Obstacles or Special Constraints**
    - If a cell is blocked, `dp[i][j]` might be 0 or some impossible marker.
    - Ensure transitions do not try to update from blocked cells.

5. **Watch Out for Directions of Movement**
    - If movement is restricted (only down/right), the recurrence typically depends on top/left neighbors.
    - If you can move in more directions, the state transitions are more complex (like in longest increasing path).

6. **Large Inputs**
    - The complexity for a naive matrix approach is usually `O(m*n)`, but if each cell checks multiple neighbors, it might be `O(m*n * directionFactor)`. This is generally okay for typical constraints, but always verify the limits.

---

## 4. Extended Examples & Real-Life Analogies

1. **Maximum Sum Rectangular Submatrix**
    - You want the submatrix (contiguous block of cells) with the highest sum. A common solution is to fix left and right columns, then sum rows in that range, reducing the problem to “maximum subarray” for each row-sum.
    - **Analogy**: Searching for the region in a 2D heat map that has the maximum concentration of heat (sum of intensities).

2. **Minimum Path Sum with Obstacles or Weighted Paths**
    - Extend the simple minimum path sum to handle negative values or obstacles.
    - **Analogy**: Road trip with certain roads blocked or with toll roads that have negative/positive costs.

3. **Edit Distance (If Visualized as a 2D Table)**
    - Although it’s a string problem, we often visualize the `dp` table in a matrix form (`dp[i][j]` = distance to convert substring1 up to i to substring2 up to j).
    - **Analogy**: Number of small “edits” needed to transform one piece of text into another.

4. **Word Search in a Grid**
    - Not always DP, but can be approached with backtracking + memoization.
    - **Analogy**: Searching for a “path of letters” in a crossword puzzle.

---
