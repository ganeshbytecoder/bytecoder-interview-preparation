

## 📘 Recursion: Concept & Fundamentals

### 🔁 What is Recursion?
Recursion is a programming technique where a function **calls itself** to solve smaller instances of the same problem until it reaches a **base case**.

### 🧠 Key Concepts
1. **Base Case** – The condition under which the recursion stops.
2. **Recursive Case** – The part where the function calls itself with a smaller input.
3. **Stack Overflow** – Recursive calls consume stack memory. Too many calls without a base case can lead to overflow.
4. **Backtracking** – A form of recursion where you explore all possible options and backtrack when a path fails.

---

## 📌 When to Use Recursion
- Normal exhaust recursions (all possible) 
- Tree and graph traversals
- Backtracking Problems (Combinations and permutations , Subsets and subsequences)
- Divide-and-conquer problems
- Dynamic programming (top-down with memoization)


## 1. **Head Recursion**

### ✅ Definition
- **Head recursion** occurs when the **recursive call** is the **first** statement in the function.
- Any processing is done **after** the recursive call returns.

### 🔎 Structure
```java
void headRec(int n) {
    if (n <= 0) return;       // Base case
    headRec(n - 1);          // Recursive call (head)
    System.out.println(n);   // Processing after
}
```

### 🏷️ When to Use
- When you need to process data in **reverse order** of the function calls.
- Situations where you're building up a result **after** exploring deeper levels.

### 🌐 Real-Life Example
- **Reading a book from last page to first page**:
    - You might want to understand how many pages are remaining, so you keep “recursing” (turning pages backward) until you reach the front. Then, as you come back from each page, you record the page number or content.

---

## 2. **Tail Recursion**

### ✅ Definition
- **Tail recursion** occurs when the **recursive call** is the **last** operation in the function.
- There is no extra computation after the recursive call returns.

### 🔎 Structure
```java
void tailRec(int n) {
    if (n <= 0) return;      // Base case
    System.out.println(n);   // Processing before
    tailRec(n - 1);          // Recursive call (tail)
}
```

### 🏷️ When to Use
- When processing the data in **forward order** of the function calls.
- Certain languages/compilers optimize tail calls (called **tail call optimization**).

### 🌐 Real-Life Example
- **Counting down a rocket launch**:
    - You say “10” (process now), then call the function to say “9,” and so forth, until you reach zero. There’s no work after the recursive call because you do all the work (print the number) before you recurse.

---

## 3. **Branching Recursion (Multiple Recursion or conditional multiple)**
- conditional take or not_take
- graph/tree dfs with condition
- all Back tracking problems 
- all dp problems 
- all DnC problems

### ✅ Definition
- **Tree recursion** is when a function **calls itself more than once** in one function body.
- Example: The classic **Fibonacci** function.

### 🔎 Structure
```java
int fib(int n) {
    if (n <= 1) return n;          // Base case
    return fib(n - 1) + fib(n - 2); // Multiple recursive calls
}
```

### 🏷️ When to Use
- **Divide-and-conquer** algorithms where you branch out into multiple subproblems.
- Traversing **tree-like** or hierarchical data structures.

### 🌐 Real-Life Example
- **Family Tree Exploration**:
    - If you want to list all descendants of a person, you’d explore each child, then each child’s children, and so on. That’s a branching (tree) recursion.

---

## Basic Recursions - check the examples class


# Optimisation of recursion 

## Divide-and-conquer Recursion

## 6. **Backtracking (DFS with “Undo” Steps)**

### ✅ Definition
- A technique using recursion to **explore all potential solutions** and **backtrack** (undo changes) when a path is invalid.
- Example: Generating **all permutations**, **combinations**, or solving the **N-Queens** puzzle.
- these problems can be solve using two approaches mainly -> 
  - binary decision tree 
  - Iterative recursion (DFS)

### 🔎 Structure
```java
void backtrack(List<Integer> current, int[] nums, boolean[] used) {
    if (current.size() == nums.length) {
        // Found a valid permutation
        results.add(new ArrayList<>(current));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if (!used[i]) {
            current.add(nums[i]);
            used[i] = true;
            backtrack(current, nums, used); // Explore
            // Backtrack (undo)
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
}
```

### 🏷️ When to Use
- **Combinatorial** problems: permutations, combinations, subsets, generating parentheses, sudoku solver, etc.
- Searching for **all** solutions in a discrete search space.

### 🌐 Real-Life Example
- **Trying on outfits**:
    - You pick a shirt, then pick pants, then pick shoes. If you decide shoes don’t match, you remove them (backtrack) and try a different pair. You keep backtracking and exploring until you find all (or the best) matching combinations.

---

## Recursion with DP (DFS with Memo Steps)



## Advance recursions:
https://leetcode.com/problems/predict-the-winner/submissions/1423638042/?envType=problem-list-v2&envId=recursion&difficulty=MEDIUM


### 🔹 Subsets / Subsequences
1. **[78. Subsets](https://leetcode.com/problems/subsets/)**
2. **[90. Subsets II](https://leetcode.com/problems/subsets-ii/)**
3. **[46. Permutations](https://leetcode.com/problems/permutations/)**
4. **[47. Permutations II](https://leetcode.com/problems/permutations-ii/)**
5. **[77. Combinations](https://leetcode.com/problems/combinations/)**
6. **[39. Combination Sum](https://leetcode.com/problems/combination-sum/)**
7. **[40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)**

### 🔹 Strings
1. **[22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)**
2. **[17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)**
3. **[131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/)**
4. **[93. Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)**

### 🔹 Backtracking (Advanced Recursion)
1. **[51. N-Queens](https://leetcode.com/problems/n-queens/)**
2. **[37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)**
3. **[52. N-Queens II](https://leetcode.com/problems/n-queens-ii/)**

### 🔹 Classic Recursion
1. **[70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)**
2. **[509. Fibonacci Number](https://leetcode.com/problems/fibonacci-number/)**
3. **[104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)**

### 🔹 Grid Problems (DFS style)
1. **[200. Number of Islands](https://leetcode.com/problems/number-of-islands/)**
2. **[695. Max Area of Island](https://leetcode.com/problems/max-area-of-island/)**

---

## 🔄 Recursion to DP Transition
- Add memoization to recursive solutions to avoid recomputation.
- Good practice problems:
    - **[70. Climbing Stairs]**
    - **[198. House Robber]**
    - **[322. Coin Change]**

