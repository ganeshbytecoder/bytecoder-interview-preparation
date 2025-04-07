### **3. Combinations (LC-77)**
- Generate all possible **k-sized** combinations.
- Subset is all possible combination with all size from 0 to n. so no need for if constraints

```java
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), 1, n, k);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> current, int start, int n, int k) {
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = start; i <= n; i++) {
        current.add(i);
        backtrack(result, current, i + 1, n, k);
        current.remove(current.size() - 1);
    }
}
```
---




### **4. Combination Sum (LC-39) [Unlimited Use]**
```java
public List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
    if (remain < 0) return;
    if (remain == 0) list.add(new ArrayList<>(tempList));
    else {
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i); // Allow reuse
            tempList.remove(tempList.size() - 1);
        }
    }
}
```
---


### **5. Combination Sum II (LC-40) [No Reuse]**
```java
public List<List<Integer>> combinationSum2(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
    if (remain < 0) return;
    if (remain == 0) list.add(new ArrayList<>(tempList));
    else {
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // Skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
```
---
### **6. Combination Sum III (LC-216)**
- **Find all k-sized numbers summing to n.**

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


- 🔹 **[377. Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/) (Medium)**  
  Find the total number of ways to form a target sum.