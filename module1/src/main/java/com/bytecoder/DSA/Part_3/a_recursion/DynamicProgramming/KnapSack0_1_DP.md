


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

* Maximize the total value of selected items without exceeding the weight capacity

```python
Maximize the total value of selected items without exceeding the weight capacity 

# Dictionary for memoization
dp = {}

def knapsack(i, w):
    if i < 0 or w <= 0:
        return 0
    if (i, w) in dp:  # Return memoized result
        return dp[(i, w)]
    
    if weight[i] > w:  # Exclude item if too heavy
        dp[(i, w)] = knapsack(i - 1, w)
    else:
        # Max of excluding or including the item
        dp[(i, w)] = max(knapsack(i - 1, w), value[i] + knapsack(i - 1, w - weight[i]))
    
    return dp[(i, w)]

```


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
