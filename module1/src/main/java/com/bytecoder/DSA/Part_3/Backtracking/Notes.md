# Backtracking - FAANG Interview Guide

## Core Concepts

### What is Backtracking?
Backtracking is an algorithmic technique that considers searching every possible combination in order to solve a computational problem. It builds candidates to the solution incrementally and abandons each partial candidate ("backtracks") if it determines that the candidate cannot possibly be completed to a valid solution.

### General Structure of Backtracking
1. **Base Case**: Check if the solution is complete or valid
2. **Recursive Exploration**:
   - Choose: Make a choice from available options
   - Explore: Recursively solve the subproblem
   - Unchoose: Undo the choice (backtrack)

```java
void backtrack(parameters) {
    if (base case condition met) {
        // Process result
        return;
    }

    for (each option in choices) {
        // Make a choice
        backtrack(next parameters);
        // Undo the choice
    }
}
```

### Key Optimization Techniques
1. **Sorting**: Helps in skipping duplicates and optimizing decisions
```java
if (used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])) continue;
```
2. **Early Pruning**: Skip invalid paths early
3. **State Management**: Efficiently track and restore state
4. **Visited Sets/Arrays**: Avoid revisiting same states

## Top FAANG Backtracking Questions


### 2. String Backtracking

1. **Generate Parentheses** [LC-22] 
```java
public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    backtrack(result, "", 0, 0, n);
    return result;
}

private void backtrack(List<String> result, String current, int open, int close, int max) {
    if (current.length() == max * 2) {
        result.add(current);
        return;
    }
    
    if (open < max)
        backtrack(result, current + "(", open + 1, close, max);
    if (close < open)
        backtrack(result, current + ")", open, close + 1, max);
}
```

2. **Letter Combinations of Phone Number** [LC-17] 
```java
import java.util.*;

class Solution {
   private void dfs(String digits, int index, List<String> result, String currentCombination, Map<Character, String> digitToChars) {
       
      if (index == digits.length()) { // Base case: all digits are processed
         result.add(currentCombination);
         return;
      }

      String possibleChars = digitToChars.get(digits.charAt(index));

      for (char ch : possibleChars.toCharArray()) {
         dfs(digits, index + 1, result, currentCombination + ch, digitToChars);
      }
   }

   public List<String> letterCombinations(String digits) {
     
      if (digits == null || digits.isEmpty()) {
         return new ArrayList<>();
      }

      Map<Character, String> digitToChars = new HashMap<>();
      digitToChars.put('2', "abc");
      digitToChars.put('3', "def");
      digitToChars.put('4', "ghi");
      digitToChars.put('5', "jkl");
      digitToChars.put('6', "mno");
      digitToChars.put('7', "pqrs");
      digitToChars.put('8', "tuv");
      digitToChars.put('9', "wxyz");

      List<String> result = new ArrayList<>();
      dfs(digits, 0, result, "", digitToChars);
      return result;
   }
}

```

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

2. **N-Queens** [LC-51] 
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

### 4. Advanced Backtracking

1. **Palindrome Partitioning** [LC-131] 
```java
public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), s, 0);
    return result;
}

private void backtrack(List<List<String>> result, List<String> current, String s, int start) {
    if (start == s.length()) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    for (int i = start; i < s.length(); i++) {
        if (isPalindrome(s, start, i)) {
            current.add(s.substring(start, i + 1));
            backtrack(result, current, s, i + 1);
            current.remove(current.size() - 1);
        }
    }
}

private boolean isPalindrome(String s, int low, int high) {
    while (low < high) {
        if (s.charAt(low++) != s.charAt(high--)) return false;
    }
    return true;
}
```

2. **Remove Invalid Parentheses** [LC-301] 
3. **Word Break II** [LC-140] 




7. [Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)
   - Restore all valid IP addresses from a string of digits.
```java
public List<String> restoreIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    backtrack(result, s, "", 0, 0);
    return result;
}

private void backtrack(List<String> result, String s, String current, int start, int segments) {
    if (segments == 4 && start == s.length()) {
        result.add(current.substring(0, current.length() - 1));
        return;
    }
    if (segments == 4 || start == s.length()) return;

    for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
        String part = s.substring(start, start + len);
        if (Integer.parseInt(part) > 255 || (part.length() > 1 && part.startsWith("0"))) continue;
        backtrack(result, s, current + part + ".", start + len, segments + 1);
    }
}

```

## Interview Tips

1. **Pattern Recognition**:
   - If the problem asks for "all possible combinations/arrangements", consider backtracking
   - Look for problems where you need to explore multiple paths to find valid solutions

2. **Optimization Techniques**:
   - Always look for opportunities to prune invalid paths early
   - Use sorting when dealing with duplicates
   - Consider using a visited set/array to avoid cycles

3. **Common Pitfalls**:
   - Forgetting to remove elements when backtracking
   - Not handling base cases properly
   - Missing edge cases in the input

4. **Time Complexity Analysis**:
   - Most backtracking solutions are exponential
   - Be prepared to discuss the exact complexity (e.g., O(2^n) for subsets, O(n!) for permutations)

## Common Templates

### Choice-Based Template
```java
void backtrack(List<List<Integer>> result, List<Integer> current, int[] choices, int start) {
    result.add(new ArrayList<>(current));  // add current combination
    
    for (int i = start; i < choices.length; i++) {
        current.add(choices[i]);           // make choice
        backtrack(result, current, choices, i + 1);  // explore
        current.remove(current.size() - 1); // undo choice
    }
}
```

### Constraint-Based Template
```java
void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
    if (current.length() == max * 2) {     // base case
        result.add(current.toString());
        return;
    }
    
    if (open < max) {                      // constraint 1
        current.append("(");
        backtrack(result, current, open + 1, close, max);
        current.setLength(current.length() - 1);
    }
    if (close < open) {                    // constraint 2
        current.append(")");
        backtrack(result, current, open, close + 1, max);
        current.setLength(current.length() - 1);
    }
}

```






---



### **Backtracking Problems:**

1. **Rat in a Maze Problem**
   - Use backtracking to explore all possible paths from the start to the destination, marking visited paths and backtracking if a path leads to a dead end.

3. **Word Break Problem Using Backtracking**
   - Use backtracking to check all possible partitions of the string and see if each partition is a valid word in the dictionary.

4. **Remove Invalid Parentheses**
   - Use backtracking to generate all possible expressions by removing invalid parentheses, then check if they are valid.

6. **m Coloring Problem**
   - Use backtracking to assign colors to each vertex, ensuring no two adjacent vertices share the same color.

   
9. **The Knight’s Tour Problem**
   - Use backtracking to move the knight on the chessboard, ensuring each move is valid and backtracking if no further moves are possible.

10. **Tug of War**
   - Use backtracking to divide the array into two subsets of nearly equal sum, minimizing the difference between the sums.

11. **Find Shortest Safe Route in a Path with Landmines**
   - Use backtracking to explore all possible routes, ensuring that no landmines are encountered.


13. **Find Maximum Number Possible by Doing At-Most K Swaps**
   - Use backtracking to explore all possible digit swaps, keeping track of the maximum number formed.

14. **Print All Permutations of a String**
   - Use backtracking to generate all permutations of the string by swapping characters and reverting swaps.

15. **Find if There is a Path of More Than K Length from a Source**
   - Use backtracking to explore all paths and track the length of each path.

16. **Longest Possible Route in a Matrix with Hurdles**
   - Use backtracking to explore all routes in the matrix, keeping track of the longest valid route.

17. **Print All Possible Paths from Top Left to Bottom Right of an mXn Matrix**
   - Use backtracking to explore all possible paths from the start to the destination in the matrix.

18. **Partition of a Set into K Subsets with Equal Sum**
   - Use backtracking to partition the array into `K` subsets such that each subset has the same sum.

19. **Find the K-th Permutation Sequence of First N Natural Numbers**
   - Use backtracking to generate permutations, and keep track of the `K-th` permutation.
```python
   class Solution:
    def solve(self, nums, used, k, num, result):
        if len(num) == k:
            result.append(num)
            return
        
        for i in range(len(nums)):
            if not used[i]:  # Ensure each number is used only once
                used[i] = True
                self.solve(nums, used, k, num + str(nums[i]), result)
                used[i] = False  # Backtrack
                
    def kthPermutation(self, n: int, k: int) -> str:
        nums = list(range(1, n+1))
        result = []
        used = [False] * n  # Track used numbers
        
        self.solve(nums, used, n, "", result)
        
        return result[k-1] if k <= len(result) else ""
 
   
   ```

## string problems

https://leetcode.com/problems/decode-string/description/

https://www.geeksforgeeks.org/roman-number-to-integer/

https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/

https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/

https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/?ref=asr30

https://www.geeksforgeeks.org/longest-substring-whose-characters-can-be-rearranged-to-form-a-palindrome/?ref=asr29

https://www.geeksforgeeks.org/print-longest-substring-without-repeating-characters/?ref=asr28

https://www.geeksforgeeks.org/longest-substring-that-can-be-made-a-palindrome-by-swapping-of-characters/?ref=asr27

https://www.geeksforgeeks.org/longest-substring-with-no-pair-of-adjacent-characters-are-adjacent-english-alphabets/?ref=asr26

https://www.geeksforgeeks.org/longest-substring-where-all-the-characters-appear-at-least-k-times-set-3/?ref=asr25

https://www.geeksforgeeks.org/longest-prefix-also-suffix/

https://www.geeksforgeeks.org/problems/length-of-the-longest-substring3036/1

```python
    
    def longestUniqueSubstring(self, s):
        charSet = set()  # HashSet to store unique characters
        i = 0
        ans = 0
        
        for j in range(len(s)):
            while s[j] in charSet:  # If duplicate found, remove leftmost character
                charSet.remove(s[i])
                i += 1
            charSet.add(s[j])  # Add current character
            ans = max(ans, j - i + 1)  # Update max length
        
        return ans
```


https://leetcode.com/problems/longest-palindromic-substring/description/

https://leetcode.com/problems/longest-palindromic-subsequence/
