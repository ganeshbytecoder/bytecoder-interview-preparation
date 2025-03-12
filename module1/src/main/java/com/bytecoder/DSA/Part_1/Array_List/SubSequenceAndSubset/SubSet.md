
# **Subsets & Subsequences**

## **1. Understanding Subsets and Subsequences**

- **Subset**: A subset of a set includes any combination of its elements, including the empty set and the set itself. Order does not matter.
  - Example: `{1, 2, 3}` → `{}`, `{1}`, `{2}`, `{3}`, `{1,2}`, `{1,3}`, `{2,3}`, `{1,2,3}`
  - Total subsets of a set with `n` elements = `2^n` (including empty set)
  - when there are duplicate elements you should skip since duplicate subset is not valid

- **Subsequence**: A subsequence is a sequence derived from another sequence by deleting some or no elements without changing the order.
  - Example: "abc" → "a", "b", "c", "ab", "ac", "bc", "abc"
  - Length of subsequence = `2^n - 1` (excluding empty subsequence)


---

## Subset Problems
#### **Concept**
- Order doesn't matter (continuous order not required).
- Variations:
  - **Distinct elements**
  - **With duplicates (Skip Duplicates)**
  - **With conditions (Fixed size, sum constraints, etc.)**
  - **if we have constraints of size they we call it combinations not sub set**
---


### **1. Subsets (LC-78)**
- Generate all subsets.
- **Time:** O(2^n), **Space:** O(n)

#### **Method 1: Backtracking**
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
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

#### **Method 2: Recursion**
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    helper(nums, 0, new ArrayList<>(), ans);
    return ans;
}

private void helper(int[] nums, int index, List<Integer> temp, List<List<Integer>> ans) {
    if (index == nums.length) {
        ans.add(new ArrayList<>(temp));
        return;
    }
    helper(nums, index + 1, temp, ans);
    temp.add(nums[index]);
    helper(nums, index + 1, temp, ans);
    temp.remove(temp.size() - 1);
}
```
---

### **2. Subsets II (LC-90) [With Duplicates]**
- **Key:** Sort & Skip duplicates

#### **Method 1: Backtracking**
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
        if (i > start && nums[i] == nums[i - 1]) continue; // Skip duplicates
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
    }
}
```

#### **Method 2: Recursion with Sorting**
```java
public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    helper(nums, 0, new ArrayList<>(), ans, false);
    return ans;
}

private void helper(int[] nums, int index, List<Integer> temp, List<List<Integer>> ans, boolean taken) {
    if (index == nums.length) {
        ans.add(new ArrayList<>(temp));
        return;
    }
    helper(nums, index + 1, temp, ans, false);
    if (index > 0 && nums[index] == nums[index - 1] && !taken) return; // Skip duplicates
    temp.add(nums[index]);
    helper(nums, index + 1, temp, ans, true);
    temp.remove(temp.size() - 1);
}
```


### **5. Bitmasking & Subsets**
- 🔹 **[1178. Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/) (Hard)**  
  Use bitmasking to find valid words in a given puzzle.

- 🔹 **[1986. Minimum Number of Work Sessions to Finish the Tasks](https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/) (Medium)**  
  Find the minimum number of sessions required to finish tasks using subsets.

---

### **6. Partitioning using Subsets**
- 🔹 **[416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) (Medium)**  
  Determine if an array can be partitioned into two subsets with equal sums.

- 🔹 **[698. Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/) (Medium)**  
  Check if an array can be divided into k subsets of equal sum.

- 🔹 **[805. Split Array With Same Average](https://leetcode.com/problems/split-array-with-same-average/) (Hard)**  
  Find if an array can be split into two subsets with the same average.

---
