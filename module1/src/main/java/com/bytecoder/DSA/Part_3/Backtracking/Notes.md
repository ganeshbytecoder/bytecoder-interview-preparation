### What is Backtracking?
Backtracking is an algorithmic technique that considers searching every possible combination in order to solve a computational problem. It builds candidates to the solution incrementally and abandons each partial candidate ("backtracks") if it determines that the candidate cannot possibly be completed to a valid solution.


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



### **Backtracking Problems:**

   
9. **The Knight’s Tour Problem**
   - Use backtracking to move the knight on the chessboard, ensuring each move is valid and backtracking if no further moves are possible.

10. **Tug of War**
   - Use backtracking to divide the array into two subsets of nearly equal sum, minimizing the difference between the sums.

13. **Find Maximum Number Possible by Doing At-Most K Swaps**
   - Use backtracking to explore all possible digit swaps, keeping track of the maximum number formed.
   

18. **Partition of a Set into K Subsets with Equal Sum**
   - Use backtracking to partition the array into `K` subsets such that each subset has the same sum.




---


## Common Data Structures to Track Visited Elements

### 1. **Boolean Array / List**
#### Usage:
- Suitable when the elements are **limited in range** (e.g., `0 to N-1`).
- Used for tracking visited indices in **permutations, subsets, and graph traversal**.
- Often used in **DFS, BFS, and N-Queens problems**.

#### Example:
```java
boolean[] visited = new boolean[N];
visited[index] = true; // Mark index as visited
...
visited[index] = false; // Backtrack
```

#### Where to Use:
✅ **Permutations (LeetCode 46)** – Ensures no element is used more than once in a sequence.  
✅ **Graph Traversal (LeetCode 79 - Word Search)** – Prevents revisiting the same cell.  
✅ **N-Queens (LeetCode 51)** – Ensures each column is used only once.

---
### 2. **Set (HashSet in Java, unordered_set in C++)**
#### Usage:
- Suitable when elements are **not continuous or have large values**.
- Fast `O(1)` lookup time for checking visited elements.
- Used for **tracking previously visited nodes or partial results**.

#### Example:
```java
Set<Integer> visited = new HashSet<>();
visited.add(num); // Mark visited
...
visited.remove(num); // Backtrack
```

#### Where to Use:
✅ **Unique Permutations (LeetCode 47)** – Avoid duplicate permutations.  
✅ **Graph Cycles Detection (LeetCode 207 - Course Schedule)** – Avoid loops in DFS.  
✅ **Word Search with Memory (LeetCode 212 - Word Search II)** – Store visited words for pruning.

---
### 3. **Dictionary / HashMap**
#### Usage:
- Best for problems involving **memoization** or **storing visited elements along with computed results**.
- Used for **Dynamic Programming (DP) and backtracking hybrid problems**.

#### Example:
```java
Map<String, Integer> memo = new HashMap<>();
memo.put(state, result); // Store computed result for a state
...
memo.remove(state); // Backtrack if needed
```

#### Where to Use:
✅ **Memoized Backtracking (LeetCode 329 - Longest Increasing Path in Matrix)** – Stores results to avoid recomputation.  
✅ **Expression Add Operators (LeetCode 282)** – Stores partial computations.  
✅ **Word Break (LeetCode 139 - Word Break I)** – Caches already computed substrings.

---
### 4. **Matrix (2D Boolean Array)**
#### Usage:
- Used for **grid-based traversal problems** like **mazes, puzzles, and pathfinding**.
- Helps avoid revisiting the same grid cell.

#### Example:
```java
boolean[][] visited = new boolean[rows][cols];
visited[row][col] = true;
...
visited[row][col] = false; // Backtrack
```

#### Where to Use:
✅ **Maze Problems (LeetCode 79 - Word Search, LeetCode 130 - Surrounded Regions)**  
✅ **Knight’s Tour Problem**  
✅ **Flood Fill Algorithm**

---
### 5. **Bitmasking (Integer Bit Manipulation)**
#### Usage:
- Used when tracking small numbers of visited elements efficiently.
- Typically applied in **state-space problems, DP, and subset problems**.

#### Example:
```java
int mask = 0;
mask |= (1 << index); // Mark visited
...
mask &= ~(1 << index); // Unmark visited (backtrack)
```

#### Where to Use:
✅ **Travelling Salesman Problem (TSP) (LeetCode 847 - Shortest Path Visiting All Nodes)**  
✅ **Subset Problems (LeetCode 78 - Subsets, LeetCode 90 - Subsets II)**  
✅ **Combinatorial Optimization**

---
### 6. **Stack/List for Path Tracking**
#### Usage:
- Used for problems where we need to keep track of the exact path we have taken.
- Useful when backtracking in tree and graph problems.

#### Example:
```java
Stack<Integer> path = new Stack<>();
path.push(node); // Add to path
...
path.pop(); // Remove when backtracking
```

#### Where to Use:
✅ **Pathfinding Problems (LeetCode 797 - All Paths From Source to Target)**  
✅ **Eulerian Path & Hamiltonian Path Problems**  
✅ **Graph Problems with Explicit Path Tracking**

---
## **Comparison Table**

| Data Structure    | Best For | Lookup Time | Space Complexity |
|------------------|---------|-------------|-----------------|
| **Boolean Array**  | Small fixed range indices (0 to N-1) | O(1) | O(N) |
| **HashSet**        | Large or unordered values | O(1) | O(N) |
| **HashMap**        | Memoization & storing states | O(1) | O(N) |
| **2D Boolean Array** | Grid-based problems | O(1) | O(N*M) |
| **Bitmasking**     | Small element sets, TSP | O(1) | O(1) |
| **Stack**         | Explicit path tracking | O(1) | O(N) |

---
## **When to Use Which?**
- ✅ **Use a boolean array** when elements have a known range **(e.g., permutations, N-Queens).**
- ✅ **Use a HashSet** when the range is large or unknown **(e.g., cycle detection, unique elements).**
- ✅ **Use a HashMap** when caching results is necessary **(e.g., memoized DP in backtracking).**
- ✅ **Use a 2D boolean array** when working with grid traversal problems **(e.g., Word Search, Maze Solving).**
- ✅ **Use bitmasking** for optimizing state tracking in small element sets **(e.g., TSP, subset generation).**
- ✅ **Use a stack** when explicit path tracking is required **(e.g., graph traversal, pathfinding).**

---



