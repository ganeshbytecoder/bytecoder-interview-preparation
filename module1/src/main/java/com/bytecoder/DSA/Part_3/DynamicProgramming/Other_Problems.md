**Dynamic Programming Notes on Leetcode Variants**
https://blog.algomaster.io/p/20-patterns-to-master-dynamic-programming?utm_campaign=post&utm_medium=web

## **Longest Increasing Subsequence (LIS) Variants**
1. **[Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)** - Use DP with binary search (O(n log n)) or DP (O(n^2)).
2. **[Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/)** - DP with sorting, track the longest subset where `nums[j]` divides `nums[i]`.
3. **[Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/)** - Sort by width, apply LIS on height.
4. **[Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/)** - Sort pairs and apply LIS/Greedy.
5. **[Number of Longest Increasing Subsequences](https://leetcode.com/problems/number-of-longest-increasing-subsequence/)** - Use DP to count valid LIS sequences.
6. **[Delete and Earn](https://leetcode.com/problems/delete-and-earn/)** - Convert to a house-robber problem.
7. **[Longest String Chain](https://leetcode.com/problems/longest-string-chain/)** - Sort and use DP with a hashmap for longest chains.

---
## **Longest Common Subsequence (LCS) Variants**
1. **[Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)** - Classic DP with memoization.
2. **[Edit Distance](https://leetcode.com/problems/edit-distance/)** - DP table to compute min edit operations.
3. **[Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/)** - Count distinct subsequences using DP.
4. **[Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)** - DP to compute minimum delete cost for equal strings.


## **Partition Subset**
1. **[Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/)** - Use knapsack DP to check if a subset sums to `total/2`.
2. **[Last Stone Weight II](https://leetcode.com/problems/last-stone-weight-ii/)** - Similar to subset sum; minimize the difference of two subsets.
   https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
---

## **BitMasking**
1. **[Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/)** - Use bitmask DP to track subset partitions.

---


## **Palindrome Problems**
1. **[Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii/)** - DP with a cut count for partitions.
2. **[Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)** - DP or center expansion.

---

## **Coin Change Variants**
1. **[Coin Change](https://leetcode.com/problems/coin-change/)** - DP for minimum number of coins.
2. **[Coin Change 2](https://leetcode.com/problems/coin-change-2/)** - Count ways to make change.
3. **[Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/)** - DP with order-sensitive sum combinations.
4. **[Perfect Squares](https://leetcode.com/problems/perfect-squares/)** - Find min squares summing to `n`.
5. **[Minimum Cost for Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/)** - DP tracking cost of valid travel days.

---

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

## **Hashing + DP**
1. **[Target Sum](https://leetcode.com/problems/target-sum/)** - DP with state `(index, sum)`.
2. **[Longest Arithmetic Sequence](https://leetcode.com/problems/longest-arithmetic-sequence/)** - DP with hash table.
3. **[Longest Arithmetic Subsequence of Given Difference](https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/)** - DP with hashmap.
4. **[Maximum Product of Splitted Binary Tree](https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/)** - DFS + DP.

---

## **State Machine Problems (Stock Trading)**
1. **[Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)** - One transaction, find max profit.
2. **[Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)** - Multiple transactions.
3. **[Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/)** - At most two transactions.
4. **[Best Time to Buy and Sell Stock IV](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/)** - At most k transactions.
5. **[Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)** - Cannot buy immediately after selling.
6. **[Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)** - Each transaction has a fee.

---

## **DFS + DP Problems**
1. **[Out of Boundary Paths](https://leetcode.com/problems/out-of-boundary-paths/)** - DFS + DP to track valid paths.
2. **[Knight Probability in Chessboard](https://leetcode.com/problems/knight-probability-in-chessboard/)** - DP to track probabilities.

---

## **Minimax DP**
1. **[Predict the Winner](https://leetcode.com/problems/predict-the-winner/)** - Minimax DP for game strategy.
2. **[Stone Game](https://leetcode.com/problems/stone-game/)** - Optimal strategy using DP.

https://leetcode.com/problems/interleaving-string/submissions/1545724299/?envType=study-plan-v2&envId=top-interview-150