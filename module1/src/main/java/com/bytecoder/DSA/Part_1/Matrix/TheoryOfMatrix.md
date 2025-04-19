
```java
  public static void main(String[] args) {
    int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
    };
    

    
    int rows = matrix.length;
    int cols = matrix[0].length;
    
    int[][] transpose = new int[cols][rows];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            transpose[j][i] = matrix[i][j];
        }
    }
}
```

```python 
    def transpose(matrix):
        return [list(row) for row in zip(*matrix)]



    def transpose(self, matrix):
    //    note in place swaps
        a,b=1,0
        a,b = b,a
        print(a,b)
        for i in range(len(matrix)):
            for j in range(i+1):
                matrix[i][j], matrix[j][i] =  matrix[j][i],matrix[i][j]
        return matrix
```

- It reverses each row of the matrix in place.

```python 
for row in range(len(matrix)):
    matrix[row] = matrix[row][::-1]

```




**DFS**
###  [**Flood Fill Algorithm**](https://leetcode.com/problems/flood-fill/description/)

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




### 1. **Spiral Traversal on a Matrix**
- **Hint**: Maintain four boundaries: top, bottom, left, and right. Traverse the matrix in a spiral order by adjusting these boundaries after each traversal in one of the four directions (left to right, top to bottom, right to left, and bottom to top).


- https://leetcode.com/problems/rotate-image/description/?envType=study-plan-v2&envId=top-interview-150

- https://leetcode.com/problems/game-of-life/submissions/1520503708/?envType=study-plan-v2&envId=top-interview-150


https://leetcode.com/problems/maximal-square/description/

https://leetcode.com/problems/the-maze/description/

https://leetcode.com/problems/minimum-knight-moves/description/

https://leetcode.com/problems/rotting-oranges/description/

https://leetcode.com/problems/word-search/?envType=problem-list-v2&envId=array&status=TO_DO 

https://leetcode.com/problems/number-of-islands/description/?envType=problem-list-v2&envId=matrix 

https://leetcode.com/problems/number-of-closed-islands/description/?envType=problem-list-v2&envId=union-find

https://leetcode.com/problems/01-matrix/description/

https://leetcode.com/problems/unique-paths/ 

https://leetcode.com/problems/max-area-of-island/description/?envType=problem-list-v2&envId=union-find 

https://leetcode.com/problems/diagonal-traverse/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days

https://www.geeksforgeeks.org/problems/find-shortest-safe-route-in-a-matrix/1

- https://leetcode.com/problems/robot-room-cleaner/
- https://leetcode.com/problems/number-of-closed-islands/description/?envType=problem-list-v2&envId=union-find

Another queen in the left diagonal (row - col should be unique). \
Another queen in the right diagonal (row + col should be unique). /

4. [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)
    - Fill an incomplete Sudoku grid with valid numbers.
```java
public void solveSudoku(char[][] board) {
    backtrack(board);
}

private boolean backtrack(char[][] board) {
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[i][j] == '.') {
                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(board, i, j, c)) {
                        board[i][j] = c;
                        if (backtrack(board)) return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
    }
    return true;
}

private boolean isValid(char[][] board, int row, int col, char c) {
    for (int i = 0; i < 9; i++) {
        if (board[row][i] == c || board[i][col] == c || board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == c) return false;
    }
    return true;
}

```



2. **N-Queens** [LC-51] https://leetcode.com/problems/n-queens/description/
```java
public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    char[][] board = new char[n][n];
    for (char[] row : board) Arrays.fill(row, '.');
    backtrack(result, board, 0);
    return result;
}

private void backtrack(List<List<String>> result, char[][] board, int row) {
    if (row == board.length) {
        result.add(construct(board));
        return;
    }
    for (int col = 0; col < board.length; col++) {
        if (isValid(board, row, col)) {
            board[row][col] = 'Q';
            backtrack(result, board, row + 1);
            board[row][col] = '.';
        }
    }
}

private boolean isValid(char[][] board, int row, int col) {
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 'Q') return false;
        if (col - (row - i) >= 0 && board[i][col - (row - i)] == 'Q') return false;
        if (col + (row - i) < board.length && board[i][col + (row - i)] == 'Q') return false;
    }
    return true;
}

private List<String> construct(char[][] board) {
    List<String> result = new ArrayList<>();
    for (char[] row : board) result.add(new String(row));
    return result;
}
```

https://leetcode.com/problems/n-queens-ii/description/


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


1. **Rat in a Maze Problem**
    - Use backtracking to explore all possible paths from the start to the destination, marking visited paths and backtracking if a path leads to a dead end.


11. **Find Shortest Safe Route in a Path with Landmines**
- Use backtracking to explore all possible routes, ensuring that no landmines are encountered.

16. **Longest Possible Route in a Matrix with Hurdles**
- Use backtracking to explore all routes in the matrix, keeping track of the longest valid route.

17. **Print All Possible Paths from Top Left to Bottom Right of an mXn Matrix**
- Use backtracking to explore all possible paths from the start to the destination in the matrix.

