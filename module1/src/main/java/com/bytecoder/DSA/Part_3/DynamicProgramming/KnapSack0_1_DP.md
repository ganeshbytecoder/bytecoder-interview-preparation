## 0/1 Knapsack

## 5. Practical Tips for Interview Success

- **Clarify Requirements:** Ensure you understand whether items are 0/1 or fractional, and confirm constraints such as capacity and number of items.
- **Define Your State Clearly:** Explain what each dimension of your DP (e.g., \( dp[i][w] \)) represents.
- **Discuss Complexity:** Highlight time and space complexities, and mention optimizations (like reducing space to \( O(W) \)).
- **Handle Edge Cases:** Consider cases such as an empty item list, zero capacity, or items that individually exceed the capacity.
- **Explain Reconstruction:** Be ready to discuss how to backtrack through your DP table to determine the actual items selected.

---

## 6. Additional LeetCode Practice Problems

1. **416. Partition Equal Subset Sum**
    - **Challenge:** Determine if a given set can be partitioned into two subsets of equal sum.
    - **Focus:** Subset sum variation using DP.
      https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
    - 
2. **494. Target Sum**
    - **Challenge:** Count the number of ways to assign + or – signs to array elements to reach a target sum.
    - **Focus:** A variation that models a knapsack-like choice with sign flips.

3. **1049. Last Stone Weight II**
    - **Challenge:** Partition stones into two groups such that the difference in their sums is minimized.
    - **Focus:** A variation of the subset sum problem using knapsack principles.

4. **474. Ones and Zeroes**
    - **Challenge:** Given binary strings and limits on the number of 0's and 1's you can use, find the maximum number of strings you can form.
    - **Focus:** A multidimensional knapsack variant where each string represents an item with two “weights.”




## 1. Overview: What Is the 0/1 Knapsack Problem?

The 0/1 Knapsack problem is a classic dynamic programming challenge that involves choosing a subset of items to maximize total value while staying within a fixed capacity. In this problem:

- **Items:** Each item \( i \) has:
    - **Weight:** \( w_i \)
    - **Value:** \( v_i \)
- **Knapsack:** You have a maximum capacity \( W \).
- **0/1 Constraint:** You can either take an item entirely or leave it—you cannot take fractions.

**Key Concepts:**

- **Decision Making:** For each item, decide whether to include it or not.
- **Optimal Substructure:** The best solution for a larger problem can be constructed from optimal solutions to its subproblems.
- **Overlapping Subproblems:** Many subproblems (e.g., “maximum value with a given capacity using a subset of items”) repeat, which makes memoization or tabulation effective.

---

## 2. Problem Statement

**Given:**

- A list of \( n \) items, where each item \( i \) has:
    - Weight \( w_i \)
    - Value \( v_i \)
- A knapsack with capacity \( W \).

**Objective:**  
Maximize the total value of items chosen such that their combined weight does not exceed \( W \).

### Example:
- **Items:**
    - Item 1: Weight = 2, Value = 4
    - Item 2: Weight = 3, Value = 5
    - Item 3: Weight = 4, Value = 6
- **Capacity:** \( W = 5 \)

**Optimal Selection:**  
Choosing item 1 and item 2 gives a total weight of 5 and a total value of 9.

---

## 3. DP Formulation

### 3.1 Bottom-Up (Tabulation) Approach

#### DP State Definition

Define a 2D table \( dp[i][w] \) where:
- \( dp[i][w] \) is the maximum value achievable using the **first \( i \) items** with a capacity \( w \).

#### Recurrence Relation

For each item \( i \) (using 1-indexing for clarity) and capacity \( w \):
\[
dp[i][w] =
\begin{cases}
dp[i-1][w] & \text{if } w_i > w \quad \text{(item too heavy to include)} \\
\max\Big(dp[i-1][w],\; v_i + dp[i-1][w-w_i]\Big) & \text{if } w_i \leq w \quad \text{(choose the best option)}
\end{cases}
\]

#### Base Cases

- \( dp[0][w] = 0 \) for all \( w \) (with 0 items, value is 0).
- \( dp[i][0] = 0 \) for all \( i \) (with 0 capacity, no items can be included).

#### Pseudocode (Bottom-Up)
```python
# Create dp table with dimensions (n+1) x (W+1), initialized to 0.
dp = [[0 for _ in range(W + 1)] for _ in range(n + 1)]

for i in range(1, n + 1):
    for w in range(1, W + 1):
        if weight[i-1] > w:
            dp[i][w] = dp[i-1][w]
        else:
            dp[i][w] = max(dp[i-1][w],
                           value[i-1] + dp[i-1][w - weight[i-1]])

# The final answer is in dp[n][W]
```

**Time Complexity:** \( O(n \times W) \)  
**Space Complexity:** \( O(n \times W) \) (can be optimized to \( O(W) \))

---

### 3.2 Top-Down (Memoization) Approach

#### Recursive Definition

Let \( knapsack(i, w) \) be the maximum value obtainable using items indexed from 0 through \( i \) with capacity \( w \):
\[
knapsack(i, w) =
\begin{cases}
0 & \text{if } i < 0 \text{ or } w \leq 0, \\
knapsack(i-1, w) & \text{if } weight[i] > w, \\
\max\Big(knapsack(i-1, w),\; v_i + knapsack(i-1, w - weight[i])\Big) & \text{otherwise.}
\end{cases}
\]

#### Memoization Strategy

Store computed values in a 2D array (or dictionary) for states \((i, w)\) so that each state is computed only once.

#### Pseudocode (Top-Down)
```python
# Assume dp is a 2D array or dictionary initialized with a sentinel value (e.g., -1)
def knapsack(i, w):
    if i < 0 or w <= 0:
        return 0
    if dp[i][w] != -1:
        return dp[i][w]
    
    if weight[i] > w:
        dp[i][w] = knapsack(i-1, w)
    else:
        dp[i][w] = max(knapsack(i-1, w),
                       value[i] + knapsack(i-1, w - weight[i]))
    return dp[i][w]
```

---

## 4. Extended Examples & Real-Life Analogies

Beyond the basic formulation, the 0/1 Knapsack pattern extends to several interesting scenarios:

### Extended Examples

- **Subset Sum Problem:**
    - **Description:** Here, each item’s weight equals its value, and the goal is to determine if there is a subset that exactly sums to a target \( S \).
    - **Real-Life Analogy:** Imagine you need to pay an exact bill using coins of specific denominations.

- **Equal Subset Partition:**
    - **Description:** Decide if a set can be split into two subsets with equal total sums.
    - **Real-Life Analogy:** Splitting household expenses into two balanced groups.

- **Counting Subsets:**
    - **Description:** Instead of maximizing value, count the number of ways to form a subset that reaches a target sum.
    - **Real-Life Analogy:** Determining the number of ways to select gifts for a party while staying within a fixed budget.

- **Knapsack with Additional Constraints:**
    - **Description:** Problems may require selecting exactly \( K \) items or mandating that certain items must be included. This typically involves extending the DP state (e.g., \( dp[i][w][k] \)).
    - **Real-Life Analogy:** Choosing a fixed number of projects or investments when resources are limited.

### Real-Life Analogies

- **Budgeting and Expense Allocation:**  
  Imagine having a fixed monthly budget. Each potential expense has a cost and a benefit. The 0/1 Knapsack pattern helps you decide which expenses to prioritize to maximize overall benefit without overspending.

- **Trip Packing:**  
  When packing for a trip, every item (clothes, gadgets, etc.) has weight and an associated utility. Your goal is to pack the items that maximize your overall utility while staying under your luggage weight limit.

- **Investment Portfolio Selection:**  
  Consider selecting stocks or investment opportunities with limited capital. Each investment requires a certain amount of money (weight) and has an expected return (value). The knapsack model assists in choosing the optimal combination of investments to maximize returns under your capital constraints.

---




# unbounded knapsack
- Include/exclude choices
- Knapsack problems

The **Coin Change** problem falls under the **Unbounded Knapsack DP Pattern**, which is useful for solving **real-world problems involving combinations, minimum cost, and resource allocation**.
Here are some real-life problems and the pattern to recognize them.

This pattern applies to problems where:
1. You have **unlimited supply** of each item (like coins, food packets, etc.).
2. You need to **maximize/minimize a value** (like cost, count, weight).
3. You are trying to **fill a target amount/weight/capacity** optimally.

### **🔑 How to Identify This Pattern?**
**Ask yourself:**
✅ Do I have an **unlimited supply** of items?  
✅ Am I trying to **reach an exact target** (amount, weight, length, distance)?  
✅ Am I looking for the **minimum or maximum** way to do it?

If **yes**, then it’s a **Coin Change (Unbounded Knapsack) DP problem**! 🎯
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

```java
class Solution {

    int solve(int[] coins, int amount, HashMap<Integer, Integer> dp){
        if(amount==0){
            return 0;
        }
     
        if(amount < 0){
            return Integer.MAX_VALUE;
        }

        if(dp.get(amount) != null){
            return dp.get(amount);
        }

        int min = Integer.MAX_VALUE;
        for(int coin : coins){
            if(amount<coin) continue;
            int temp = solve(coins, amount-coin, dp);
            min = Math.min(min, temp != Integer.MAX_VALUE ? 1 + temp : temp);
        }
        dp.put(amount, min);

        return dp.get(amount);
    }


    
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);

        HashMap<Integer, Integer> dp= new HashMap<>();
        int count = solve(coins, amount, dp) ;
        if(count!= Integer.MAX_VALUE){
            return count;
        }
        return -1;
    }
}
```

M2  important

```java

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Fill with an unreachable max value
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}

```

---

### **🌍 Real-World Problems Using This Pattern**

### **1. Minimum Banknotes Needed (ATM Withdrawal)**
**Problem:**  
An ATM has banknotes of `{1, 5, 10, 20, 50, 100}`. A customer requests ₹287. Find the **minimum number of banknotes** required to dispense this amount.

🔹 **Solution:**  
Same as **coin change**, where:
- `coins = {1, 5, 10, 20, 50, 100}`
- `amount = 287`
- Goal: Minimize the number of notes.

---

### **2. Minimum Food Packets for Refugees**
**Problem:**  
A charity organization wants to distribute **food packets** to a refugee camp. Packets are available in `{5, 10, 20}` sizes. What is the **minimum number of packets** required to supply `N` kg of food?

🔹 **Solution:**
- `coins = {5, 10, 20}` (packet sizes)
- `amount = N` (total food requirement)
- **Minimize** the number of packets used.

---

### **3. Minimum Bottles to Fill a Tank**
**Problem:**  
You have water bottles of different capacities `{250ml, 500ml, 1L, 2L, 5L}`. Given a tank capacity of `T` liters, find the **minimum number of bottles** required to fill it exactly.

🔹 **Solution:**
- `coins = {250, 500, 1000, 2000, 5000}` (bottle sizes in ml)
- `amount = T * 1000` (convert liters to ml)
- **Minimize** the number of bottles used.

---

### **4. Making Exact Change in Vending Machines**
**Problem:**  
A vending machine has coins of `{1, 2, 5, 10}`. A customer inserts a bill, and the machine needs to return `X` amount in **minimum coins**.

🔹 **Solution:**
- Same as **coin change**, where we minimize the number of coins returned.

---

### **5. Cutting Metal Rods for Construction**
**Problem:**  
A construction company needs metal rods of length `L`. They have an unlimited supply of rods of `{1m, 2m, 5m, 10m}`. Find the **minimum number of rods** required to get exactly `L` meters.

🔹 **Solution:**
- `coins = {1, 2, 5, 10}` (rod lengths)
- `amount = L` (required length)
- **Minimize** the number of rods.

---

### **6. Minimum Jumps to Reach the End**
**Problem:**  
A frog can jump `{1, 2, 3}` stones at a time to cross a river of `N` stones. Find the **minimum jumps** required.

🔹 **Solution:**
- `coins = {1, 2, 3}` (jump sizes)
- `amount = N` (stones to cross)
- **Minimize** the number of jumps.

---
