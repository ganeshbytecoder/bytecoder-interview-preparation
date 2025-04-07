### **Counting the Number of Subarrays Using Two-Pass Inclusion-Exclusion**

This approach is commonly used for efficiently counting **valid subarrays** while optimizing **brute-force O(N²) solutions**. The **Two-Pass Inclusion-Exclusion technique** breaks problems into simpler counting tasks.

---

### **Understanding the Two-Pass Approach**
Instead of iterating over all subarrays (**O(N²)**), we can:
1. **First Pass (Inclusion)** → Count **all subarrays** with at most `K` elements.
2. **Second Pass (Exclusion)** → Remove subarrays with at most `K-1` elements to get exactly `K` elements.

This technique is useful in problems involving:
- Counting valid subarrays with **exactly `K` occurrences**.
- Finding subarrays satisfying **X but not Y**.
- Optimizing **brute-force O(N²) subarray counting**.

---

### **📌 Problems Based on This Pattern**

#### **1. Number of Subarrays with Exactly K Distinct Elements**
**Problem:** Count subarrays with exactly `K` distinct elements.

**Solution:** Use `count(atMost(K)) - count(atMost(K-1))`

```python
def count_subarrays_at_most_k(nums, k):
    n = len(nums)
    count = 0

    for i in range(n):  # Start of subarray
        odd_count = 0
        for j in range(i, n):  # End of subarray
            if nums[j] % 2 == 1:
                odd_count += 1
            if odd_count == k:
                count += 1
            elif odd_count > k:
                break  # No need to continue if we exceed k

    return count



from collections import defaultdict

def count_subarrays_at_most_k(nums, k):
    freq_map = defaultdict(int)
    left = 0
    count = 0
    
    for right in range(len(nums)):
        freq_map[nums[right]] += 1
        
        while len(freq_map) > k:
            freq_map[nums[left]] -= 1
            if freq_map[nums[left]] == 0:
                del freq_map[nums[left]]
            left += 1
        
        count += (right - left + 1)  # Count valid subarrays
    
    return count

def count_subarrays_exactly_k(nums, k):
    return count_subarrays_at_most_k(nums, k) - count_subarrays_at_most_k(nums, k - 1)

# Example Usage
nums = [1, 2, 1, 2, 3]
k = 2
print("Number of subarrays with exactly K distinct elements:", count_subarrays_exactly_k(nums, k))
```

✅ **Time Complexity:** `O(N)` since each element is added and removed at most once.

---

#### **2. Number of Subarrays with at Most K Odd Numbers**
**Problem:** Given an array, count subarrays with **exactly K** odd numbers.

**Solution:** Convert odd numbers to `1` and even numbers to `0`, then apply `count(atMost(K)) - count(atMost(K-1))`.

```python

def count_subarrays_exactly_k_odds(nums, k):
    n = len(nums)
    count = 0

    for i in range(n):  # Start of subarray
        odd_count = 0
        for j in range(i, n):  # End of subarray
            if nums[j] % 2 == 1:
                odd_count += 1
            if odd_count == k:
                count += 1
            elif odd_count > k:
                break  # No need to continue if we exceed k

    return count


def count_subarrays_at_most_k_odds(nums, k):
    left = 0
    count = 0
    odd_count = 0
    
    for right in range(len(nums)):
        odd_count += nums[right] % 2  # Convert odd to 1, even to 0
        
        while odd_count > k:
            odd_count -= nums[left] % 2
            left += 1
        
        count += (right - left + 1)
    
    return count

def count_subarrays_exactly_k_odds(nums, k):
    return count_subarrays_at_most_k_odds(nums, k) - count_subarrays_at_most_k_odds(nums, k - 1)

# Example Usage
nums = [1, 2, 3, 4, 5]
k = 2
print("Number of subarrays with exactly K odd numbers:", count_subarrays_exactly_k_odds(nums, k))
```

✅ **Time Complexity:** `O(N)`.

---

### **Why and How the Two-Pass Inclusion-Exclusion Works**

The **Two-Pass Inclusion-Exclusion** technique efficiently counts subarrays satisfying an **exact condition** (e.g., "exactly `K` elements") by leveraging results from a simpler problem ("at most `K` elements").

Instead of directly counting subarrays with **exactly `K` elements** (which is hard), we:
1. Count subarrays with at most `K` elements (`F(K)`).
2. Count subarrays with at most `K-1` elements (`F(K-1)`).
3. Compute:
   ```
   Subarrays with exactly K elements = F(K) - F(K-1)
   ```

✅ **Why does this work?**
- **F(K) counts too much**: Includes all subarrays with up to `K` elements.
- **F(K-1) removes extra ones**: Removes subarrays that had fewer than `K` elements.
- **Difference isolates exactly `K`**: The remaining subarrays must contain **exactly `K` elements**.

---

### **More Variations of This Pattern**

| **Problem Type** | **Solution Approach** |
|-----------------|----------------------|
| Number of subarrays with exactly `K` distinct elements | Two-Pass `F(K) - F(K-1)` |
| Number of subarrays with exactly `K` odd numbers | Convert odd/even & apply Two-Pass |
| Number of subarrays with at most `K` distinct elements | Sliding Window + Hash Map |
| Number of subarrays with exactly `K` vowels | Convert vowels & apply Two-Pass |
| Number of subarrays with at most `K` negative numbers | Similar Two-Pass Sliding Window approach |

---

### **Key Takeaways**
✅ **Optimized Counting:** Avoids `O(N²)` substring generation, reducing complexity to `O(N)`.
✅ **Works for Exact Constraints:** Since counting **exactly `K`** is hard, we use `count(atMost(K)) - count(atMost(K-1))`.
✅ **Avoids Nested Loops:** Traditional substring approaches use `O(N²)`, but this method optimizes it to `O(N)`.

---

### **Recommended Practice Problems**
- [Leetcode 992: Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/)
- [Leetcode 1248: Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/)

---

