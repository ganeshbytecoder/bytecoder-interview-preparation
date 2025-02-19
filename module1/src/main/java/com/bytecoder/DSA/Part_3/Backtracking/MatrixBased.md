### 3. Matrix Backtracking

1. **Word Search** [LC-79]
```java
class Solution {
   private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

   private boolean findWord(char[][] board, int i, int j, String word, String currentPath, int[][] visited) {
      if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] == 1) {
         return false;
      }

      // If the current path matches the word, return true
      if (word.equals(currentPath)) {
         return true;
      }

      visited[i][j] = 1; // Mark cell as visited

      // Explore all 4 directions
      for (int[] dir : DIRECTIONS) {
         int newRow = i + dir[0];
         int newCol = j + dir[1];

         if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length
                 && visited[newRow][newCol] != 1 && board[newRow][newCol] == word.charAt(currentPath.length())) {

            if (findWord(board, newRow, newCol, word, currentPath + board[newRow][newCol], visited)) {
               return true;
            }
         }
      }

      visited[i][j] = 0; // Backtrack: Unmark cell
      return false;
   }

   public boolean exist(char[][] board, String word) {
      int rows = board.length, cols = board[0].length;
      int[][] visited = new int[rows][cols];

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            // Start search from any matching first character
            if (board[i][j] == word.charAt(0)) {
               if (findWord(board, i, j, word, "" + board[i][j], visited)) {
                  return true;
               }
            }
         }
      }
      return false;
   }
}

```



1. **Rat in a Maze Problem**
   - Use backtracking to explore all possible paths from the start to the destination, marking visited paths and backtracking if a path leads to a dead end.


11. **Find Shortest Safe Route in a Path with Landmines**
- Use backtracking to explore all possible routes, ensuring that no landmines are encountered.

16. **Longest Possible Route in a Matrix with Hurdles**
- Use backtracking to explore all routes in the matrix, keeping track of the longest valid route.

17. **Print All Possible Paths from Top Left to Bottom Right of an mXn Matrix**
- Use backtracking to explore all possible paths from the start to the destination in the matrix.


https://www.geeksforgeeks.org/problems/find-shortest-safe-route-in-a-matrix/1


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
