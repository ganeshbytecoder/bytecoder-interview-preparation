all possible sub-array and sub-sequence 

all possible sub-string and sub-sequence of string
https://leetcode.com/problems/sum-of-all-subset-xor-totals/submissions/1423540720/?envType=problem-list-v2&envId=backtracking&difficulty=EASY

https://leetcode.com/problems/permutations/?envType=problem-list-v2&envId=backtracking 



* word search in matrix 
* all possible permutations of string or array
* all possible subsets of array 



### **Notes on Backtracking**


#### **General Structure of Backtracking**
1. **Base Case**: Check if the solution is complete or valid. If yes, add it to the result.
2. **Recursive Exploration**:
   - Iterate over the potential candidates.
   - Add a candidate to the current path.
   - Explore further with recursive backtracking.
   - Remove the candidate (backtrack) and move to the next option.


### **Key Insights for Backtracking**
1. **Use Sorting**: Helps in skipping duplicates and optimizing decisions. \

```java
    if (used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])) continue;

```
2. **Base Case**: Clearly define the stopping condition.
3. **Backtracking Step**: Undo the last decision before moving to the next option.
4. **Pruning**: Skip iterations that are invalid early to improve efficiency.


---

#### **Highlighted Problems and Implementations**
1. **Subsets**:
   - Generate all subsets of a given set.
   - Link: [Subsets](https://leetcode.com/problems/subsets/)
```java
   public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
    list.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
    }
}
```

2. **Subsets II**:
   - Generate subsets but avoid duplicates.
   - Key Technique: Sort the array and skip duplicate elements.
   - Link: [Subsets II](https://leetcode.com/problems/subsets-ii/)
```java
public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
    list.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
    }
} 
```

3. **Permutations**:
   - Generate all permutations of a given list.
   - Avoid duplicates using a `used` array.
   - Link: [Permutations](https://leetcode.com/problems/permutations/)
```java

public List<List<Integer>> permute(int[] nums) {
   List<List<Integer>> list = new ArrayList<>();
   // Arrays.sort(nums); // not necessary
   backtrack(list, new ArrayList<>(), nums);
   return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
   if(tempList.size() == nums.length){
      list.add(new ArrayList<>(tempList));
   } else{
      for(int i = 0; i < nums.length; i++){ 
         if(tempList.contains(nums[i])) continue; // element already exists, skip
         tempList.add(nums[i]);
         backtrack(list, tempList, nums);
         tempList.remove(tempList.size() - 1);
      }
   }
} 
```

4. **Permutations II**:
   - Handle duplicates while generating permutations.
   - Key Technique: Skip already used duplicate elements.
   - Link: [Permutations II](https://leetcode.com/problems/permutations-ii/)

```java
public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
    if(tempList.size() == nums.length){
        list.add(new ArrayList<>(tempList));
    } else{
        for(int i = 0; i < nums.length; i++){
            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
            used[i] = true; 
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, used);
            used[i] = false; 
            tempList.remove(tempList.size() - 1);
        }
    }
}
```

5. **Combination Sum**:
   - Find all unique combinations that sum to a target, with unlimited use of elements.
   - Link: [Combination Sum](https://leetcode.com/problems/combination-sum/)
```java

public List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
    if(remain < 0) return;
    else if(remain == 0) list.add(new ArrayList<>(tempList));
    else{ 
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
            tempList.remove(tempList.size() - 1);
        }
    }
}
```

6. **Combination Sum II**:
   - Same as Combination Sum, but elements cannot be reused.
   - Key Technique: Sort the array and skip duplicates.
   - Link: [Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)
```java

public List<List<Integer>> combinationSum2(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
    
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
    if(remain < 0) return;
    else if(remain == 0) list.add(new ArrayList<>(tempList));
    else{
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i + 1);
            tempList.remove(tempList.size() - 1); 
        }
    }
} 


```

7. **Palindrome Partitioning**:
   - Partition a string into substrings such that each substring is a palindrome.
   - Key Technique: Check for palindrome substrings during backtracking.
   - Link: [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)
```java

public List<List<String>> partition(String s) {
   List<List<String>> list = new ArrayList<>();
   backtrack(list, new ArrayList<>(), s, 0);
   return list;
}

public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
   if(start == s.length())
      list.add(new ArrayList<>(tempList));
   else{
      for(int i = start; i < s.length(); i++){
         if(isPalindrome(s, start, i)){
            tempList.add(s.substring(start, i + 1));
            backtrack(list, tempList, s, i + 1);
            tempList.remove(tempList.size() - 1);
         }
      }
   }
}

public boolean isPalindrome(String s, int low, int high){
   while(low < high)
      if(s.charAt(low++) != s.charAt(high--)) return false;
   return true;
} 


```


1. [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)
   - Generate all combinations of valid parentheses for `n` pairs.
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
    if (open < max) {
        backtrack(result, current + "(", open + 1, close, max);
    }
    if (close < open) {
        backtrack(result, current + ")", open, close + 1, max);
    }
}

```
   
2. [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)
   - Map digits to letters and generate all possible combinations.
```java
public List<String> letterCombinations(String digits) {
    if (digits == null || digits.length() == 0) return new ArrayList<>();
    String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> result = new ArrayList<>();
    backtrack(result, digits, mapping, "", 0);
    return result;
}

private void backtrack(List<String> result, String digits, String[] mapping, String current, int index) {
    if (index == digits.length()) {
        result.add(current);
        return;
    }
    String letters = mapping[digits.charAt(index) - '0'];
    for (char c : letters.toCharArray()) {
        backtrack(result, digits, mapping, current + c, index + 1);
    }
}

```

3. [Word Search](https://leetcode.com/problems/word-search/)
   - Check if a word exists in a grid by exploring all possible paths.
```java
 public boolean backtrack(char[][] board, String word, int i, int j, boolean[][] visited, int index) {

    if (word.length() == index) {
        return true;
    }
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(index) || visited[i][j]) {
        return false;
    }

    visited[i][j] = true;


    if (backtrack(board, word, i + 1, j, visited, index + 1) || backtrack(board, word, i - 1, j, visited, index + 1) || backtrack(board, word, i, j + 1, visited, index + 1) || backtrack(board, word, i, j - 1, visited, index + 1)) {
        return true;
    }

    visited[i][j] = false;

    return false;
}

public boolean exist(char[][] board, String word) {
    boolean[][] visited = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (backtrack(board, word, i, j, visited, 0)) {
                return true;
            }
        }
    }
    return false;
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

5. [N-Queens](https://leetcode.com/problems/n-queens/)
   - Place `N` queens on an `N x N` chessboard such that no two queens attack each other.
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

6. [Combination Sum III](https://leetcode.com/problems/combination-sum-iii/)
   - Find all combinations of `k` numbers that add up to `n`.

```java

public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), k, n, 1);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> tempList, int k, int remain, int start) {
    if (tempList.size() == k && remain == 0) {
        result.add(new ArrayList<>(tempList));
        return;
    }
    for (int i = start; i <= 9; i++) {
        tempList.add(i);
        backtrack(result, tempList, k, remain - i, i + 1);
        tempList.remove(tempList.size() - 1);
    }
}


```
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

---



### **Backtracking Problems:**

1. **Rat in a Maze Problem**
   - Use backtracking to explore all possible paths from the start to the destination, marking visited paths and backtracking if a path leads to a dead end.

2. **Printing All Solutions in N-Queen Problem**
   - Use backtracking to place queens on the board. Ensure that no two queens threaten each other before placing the next queen.

3. **Word Break Problem Using Backtracking**
   - Use backtracking to check all possible partitions of the string and see if each partition is a valid word in the dictionary.

4. **Remove Invalid Parentheses**
   - Use backtracking to generate all possible expressions by removing invalid parentheses, then check if they are valid.

5. **Sudoku Solver**
   - Use backtracking to fill empty cells with digits while ensuring that the current placement does not violate Sudoku rules.

6. **m Coloring Problem**
   - Use backtracking to assign colors to each vertex, ensuring no two adjacent vertices share the same color.

7. **Print All Palindromic Partitions of a String**
   - Use backtracking to explore all possible partitions of the string and check if each partition is a palindrome.

8. **Subset Sum Problem**
   - Use backtracking to explore all subsets of the array, and track the subsets that sum to a given value.

9. **The Knight’s Tour Problem**
   - Use backtracking to move the knight on the chessboard, ensuring each move is valid and backtracking if no further moves are possible.

10. **Tug of War**
   - Use backtracking to divide the array into two subsets of nearly equal sum, minimizing the difference between the sums.

11. **Find Shortest Safe Route in a Path with Landmines**
   - Use backtracking to explore all possible routes, ensuring that no landmines are encountered.

12. **Combinational Sum**
   - Use backtracking to generate all combinations of numbers that sum to the target value, allowing repeated use of elements.

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

