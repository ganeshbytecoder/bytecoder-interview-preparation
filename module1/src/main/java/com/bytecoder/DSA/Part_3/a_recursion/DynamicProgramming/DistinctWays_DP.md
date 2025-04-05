
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

Approaches:

Recursive: Time O(L^n), Space O(n) -> partitions of recursive tree

Recursive: Time O(2^n), Space O(n)

Memoization: Time O(n), Space O(n)

Tabulation: Time O(n), Space O(n)

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
4. **LeetCode 62. Unique Paths** (grid movement)
5. **LeetCode 22. Generate Parentheses** (count valid parentheses or just generate them)
6. **LeetCode 139. Word Break** (count or check feasibility)
7. **LeetCode 377. Combination Sum IV** (order matters for ways to reach a target sum)
8.   LeetCode 2266. Count Number of Texts

#### **Further Variations**
- If you can climb 1, 2, or 3 steps at a time, then \( dp[i] = dp[i-1] + dp[i-2] + dp[i-3] \).
- If some steps are “broken” (you can’t step on them), incorporate conditions in your recurrence relation.

---

## 4. Additional Examples & Real-Life Analogies

### Problem 1: Tiling Problem
- **Statement:** You have tiles with specific dimensions (e.g., 2x1 or 2x2) and must completely cover a 2xn board. How many ways can you accomplish this?
- **Real-Life Analogy:** Imagine tiling a rectangular kitchen or bathroom floor using rectangular ceramic tiles.

### Problem 2: Partition a String (Word Break)
- **Statement:** Given a dictionary of valid words, determine how many ways a given string can be segmented into valid dictionary words.
- **Real-Life Analogy:** Splitting a long text message or hashtags (#) into known dictionary words or phrases.

### Problem 3: Number of Distinct Subsequences
- **Statement:** Given a string, find how many distinct subsequences exist (including the empty subsequence).
- **Real-Life Analogy:** In a corporate environment, generating distinct signature patterns or security codes from a given combination of letters.

### Problem 4: Counting Palindromic Subsequences
- **Statement:** Count the total number of subsequences in a given string that form a palindrome.
- **Real-Life Analogy:** Identifying symmetrical patterns, such as symmetrical waveforms in audio data or symmetrical designs in textiles.

### Real-Life Illustrative Examples

1. **Stacking Lego Bricks**
   - Imagine you are building a tower of height \( n \) using Lego bricks. You can stack either a single-brick or double-brick piece at each step. How many distinct ways can you build the tower?

2. **Phone Keypad Interpretation**
   - Recall typing text messages on old mobile phones using numeric keypads, where each digit represented multiple letters. Determining how many distinct interpretations a numeric sequence could represent parallels this decoding problem.

3. **Colored Tiles for a Walkway**
   - Suppose you have an infinite supply of tiles, each tile with different lengths similar to coin denominations. Your goal is to tile a walkway of exact length 5. How many combinations of tiles can you use to achieve this exact length?

4. **Navigating a Grid City**
   - Imagine a city arranged in a grid format with streets running only south and east. Count how many unique paths exist from the top-left intersection to the bottom-right intersection.

5. **Matching Puzzle Brackets**
   - Consider \( n \) pairs of puzzle pieces shaped like brackets, with each open-bracket piece requiring a matching close-bracket piece. How many ways can these puzzle pieces be arranged so that each open piece correctly matches a corresponding close piece, forming valid bracket sequences?



---
