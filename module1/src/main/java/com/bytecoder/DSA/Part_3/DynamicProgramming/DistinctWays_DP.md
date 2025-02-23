


##### [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
**Problem**: You can climb 1 or 2 steps. How many distinct ways to reach top?

```java
// 1. Recursion - Time: O(2^n), Space: O(n)
public int climbStairs(int n) {
    if (n <= 2) return n;
    return climbStairs(n - 1) + climbStairs(n - 2);
}

// 2. Memoization - Time: O(n), Space: O(n)
public int climbStairs(int n) {
    int[] memo = new int[n + 1];
    Arrays.fill(memo, -1);
    return climbHelper(n, memo);
}

private int climbHelper(int n, int[] memo) {
    if (n <= 2) return n;
    if (memo[n] != -1) return memo[n];
    return memo[n] = climbHelper(n - 1, memo) + climbHelper(n - 2, memo);
}

// 3. Tabulation - Time: O(n), Space: O(n)
public int climbStairs(int n) {
    if (n <= 2) return n;
    int[] dp = new int[n + 1];
    dp[1] = 1; dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}
```



Below is a structured set of notes on **Dynamic Programming (DP) “Distinct Ways” patterns**, commonly encountered in FANG interviews. These notes include:

- Key DP concepts for counting/number-of-ways problems
- Step-by-step explanations of classic examples
- Real-life analogies to help solidify intuition
- Additional example problems you might encounter

Use these notes as a reference for both understanding and implementation practice.

---

3. **LeetCode 518. Coin Change II** (counting ways to make amount)

```python 
class Solution:
    def dfs(self, amount, coins,index, temp, result):
        if(amount<0):
            return 0

        if(amount==0):
            sorted_string = ''.join(sorted(temp))
            if( sorted_string not in result):
                result.add(sorted_string)
                return 1
            return 0
        if(index>=len(coins)):
            return 0


        distinct = 0
        for i in range(index,len(coins)):
            coin = coins[i]
            distinct += self.dfs(amount-coin, coins,i, temp + str(coin), result)
        return distinct



    def change(self, amount: int, coins: List[int]) -> int:
     
        result = set()
        return self.dfs(amount, coins,0,"" ,result)
        
        

class Solution:
    def dfs(self, amount, coins, index, memo):
        # Base cases
        if amount < 0:
            return 0
        if amount == 0:
            return 1
        if (amount, index) in memo:
            return memo[(amount, index)]
        
        ways = 0
        for i in range(index, len(coins)):
            ways += self.dfs(amount - coins[i], coins, i, memo)
        
        memo[(amount, index)] = ways
        return ways

    def change(self, amount: int, coins: List[int]) -> int:
        memo = {}
        return self.dfs(amount, coins, 0, memo)
        
        
        

class Solution:
    def change(self, amount: int, coins: List[int]) -> int:
        memo = {}
        
        def dfs(rem, i):
            # If we've exactly used up the amount, there's one valid combination.
            if rem == 0:
                return 1
            # If the remaining amount is negative or no more coins to use, no valid combination.
            if rem < 0 or i >= len(coins):
                return 0
            
            # Use the state (rem, i) as the memoization key.
            if (rem, i) in memo:
                return memo[(rem, i)]
            
            # Two choices:
            # 1. Use coin at index i (and we can use it again, so i stays the same)
            # 2. Skip coin at index i and move to the next coin
            ways = dfs(rem - coins[i], i) + dfs(rem, i + 1)
            
            memo[(rem, i)] = ways
            return ways
        
        return dfs(amount, 0)



```

2. **LeetCode 91. Decode Ways**
3. **LeetCode 518. Coin Change II** (counting ways to make amount)
4. **LeetCode 62. Unique Paths** (grid movement)
5. **LeetCode 22. Generate Parentheses** (count valid parentheses or just generate them)
6. **LeetCode 139. Word Break** (count or check feasibility)
7. **LeetCode 377. Combination Sum IV** (order matters for ways to reach a target sum)
8.   LeetCode 2266. Count Number of Texts





## 1. Overview: Why “Distinct Ways” Problems?

In many DP problems, the goal is not to find a minimum or maximum but to count how many ways something can be done. Typically, you see phrases like:

- “Count the number of ways to do **X**.”
- “Find how many distinct sequences/paths/arrangements exist.”

These are direct hints that a DP-based combinatorial approach may be suitable.

**Key DP Concepts for Counting Problems**
1. **Subproblems**: You break down the overall counting problem into smaller sub-counting problems.
2. **Recurrence Relation**: You figure out how the solution for a bigger problem relates to solutions of its subproblems.
3. **Base Cases**: Define the counts for smallest inputs or boundary conditions.
4. **Memoization / Tabulation**: Store intermediate results to avoid recomputation.

---







## 2. Common Patterns & Examples

### 2.1 Distinct Ways to Climb Stairs

**Problem Statement**  
You can climb a staircase of \( n \) steps, and you can either take 1 or 2 steps at a time. How many distinct ways can you reach the top?

1. **DP Definition**
    - Let \( dp[i] \) = number of ways to reach step \( i \).
2. **Recurrence**
    - From step \( i \), you could have come from step \( i-1 \) (1 step jump) or step \( i-2 \) (2 steps jump).
    - Hence, \( dp[i] = dp[i-1] + dp[i-2] \).
3. **Base Cases**
    - \( dp[0] = 1 \) (one way to stand still at the bottom)
    - \( dp[1] = 1 \) (one way to get to the first step)
4. **Answer**
    - \( dp[n] \) is the number of ways to reach the top of \( n \) steps.

**Real-Life Example**  
Imagine you are stacking Lego bricks to build a small tower of height \( n \). You can add a 1-brick block or a 2-brick block each time. In how many different ways can you stack them to reach height \( n \)?

**Further Variations**
- If you can climb 1, 2, or 3 steps at a time, then \( dp[i] = dp[i-1] + dp[i-2] + dp[i-3] \).
- If some steps are “broken” (you can’t step on them), incorporate conditions in your recurrence relation.

---

### 2.2 Distinct Ways to Decode a Message

**Problem Statement**  
Given a string of digits (e.g., “123”), where ‘1’=’A’, ‘2’=’B’, …, ‘26’=’Z’, count how many possible decodings exist.

1. **DP Definition**
    - Let \( dp[i] \) = number of ways to decode the substring up to index \( i \).
2. **Recurrence**
    - Look at one digit (the last digit) and check if it’s valid (1–9).
        - If valid, add \( dp[i-1] \) ways.
    - Look at two digits (the last two digits) and check if it’s valid (10–26).
        - If valid, add \( dp[i-2] \) ways.
    - So \( dp[i] = \)( (if valid single) ? \( dp[i-1] \) : 0 ) + ( (if valid double) ? \( dp[i-2] \) : 0 ).
3. **Base Cases**
    - \( dp[0] = 1 \) (empty string can be decoded in 1 way—by doing nothing).
    - For the first character, initialize based on whether it’s ‘0’ or not.
4. **Answer**
    - \( dp[n] \), where \( n \) is the length of the string.

**Real-Life Example**  
Think about how phone keypad text messages were interpreted in old feature phones (before smartphones). Each digit could represent letters in multiple ways. Counting distinct interpretations is analogous to this decoding problem.

---

### 2.3 Coin Change (Counting Ways to Make Change)

**Problem Statement**  
You have an unlimited supply of coins of given denominations and a target amount. How many different ways can you make that amount?

For example, if coins = [1,2,5] and amount = 5, the ways are:
1. 1+1+1+1+1
2. 1+1+1+2
3. 1+2+2
4. 5

So, there are 4 ways.

1. **DP Definition**
    - Let \( dp[x] \) = number of ways to make amount \( x \).
2. **Recurrence** (Typical approach: **Unbounded Knapsack** style)
    - For each coin of value \( c \), we do:  
      \[
      dp[x + c] += dp[x]
      \]
    - Meaning, if you already know how many ways to make \( x \), you can extend that by coin \( c \) to make \( x + c \).
3. **Base Case**
    - \( dp[0] = 1 \) (one way to make 0 amount: by choosing no coins).
4. **Answer**
    - \( dp[\text{amount}] \) is your final answer.

**Real-Life Example**  
Imagine you have an infinite supply of different colored tiles (each tile color is like a coin denomination). You want to fill a 1D walkway of length 5. In how many ways can you place these tiles so the total length is exactly 5?

---

### 2.4 Distinct Ways to Move in a Grid

**Problem Statement**  
A robot starts at the top-left corner of an \( m \times n \) grid and can move only right or down. How many distinct ways are there to reach the bottom-right corner?

1. **DP Definition**
    - \( dp[i][j] \) = number of ways to reach cell \((i, j)\).
2. **Recurrence**
    - \( dp[i][j] = dp[i-1][j] + dp[i][j-1] \) (from top or from left).
3. **Base Cases**
    - First row and first column each have only 1 way if there are no obstacles (straight line).
4. **Answer**
    - \( dp[m-1][n-1] \).

**Real-Life Example**  
Imagine you have a city with a grid layout of streets. You can only drive south or east. In how many ways can you get from the top-left corner of the city to the bottom-right corner?

---

### 2.5 Generating (and Counting) Valid Parentheses

**Problem Statement**  
Given \( n \) pairs of parentheses, count the number of valid arrangements. Also known as “Generate Parentheses.”

1. **Recursive Definition + Counting**
    - At each step, you can either place ‘(’ or ‘)’ if it leads to a valid sequence.
2. **DP / Catalan Relation**
    - The number of valid parentheses with \( n \) pairs is the \( n \)-th Catalan number:  
      \[
      C_n = \frac{1}{n+1}\binom{2n}{n}
      \]
    - Often solved either via direct DP or direct formula.
3. **Core Idea**
    - A valid parentheses string from \( n \) pairs can be formed by choosing a split: the left part has \( i \) pairs, the right part has \( n-1-i \) pairs, and so on.

**Real-Life Example**  
Imagine you have \( n \) matching pairs of bracket-shaped puzzle pieces. Each arrangement must be formed by matching the correct “open bracket piece” with a “close bracket piece.” You want to know in how many ways you can line up these puzzle pieces so that no piece is mismatched.

---

## 4. Additional Examples & Real-Life Analogies

Here are some additional scenarios (along with real-life parallels) you might come across:

1. **Tiling Problem**
    - **Statement**: You have tiles of certain dimensions (e.g., 2x1 or 2x2) to cover a 2xn board. In how many ways can you do it?
    - **Real-Life**: Tiling a rectangular floor using small rectangular tiles.

2. **Partition a String (Word Break)**
    - **Statement**: Given a dictionary of words, how many ways can a given string be segmented into valid words?
    - **Real-Life**: Splitting a text message using known dictionary words or hashtags (#).

3. **Number of Distinct Subsequences**
    - **Statement**: Given a string, count the number of distinct subsequences (including the empty subsequence).
    - **Real-Life**: In a corporate setting, each employee ID might produce a combination of letters. Counting subsequences is like counting all distinct signature patterns that can be formed from those letters.

4. **Counting Palindromic Subsequences**
    - **Statement**: Count the number of subsequences that form a palindrome in a string.
    - **Real-Life**: Searching for symmetrical patterns in a data sequence, e.g., a wave pattern that is symmetrical.

---
