

## **4️⃣ Prefix Sum & HashMap**
Used when:
✅ Finding **sum-based** subarray problems (e.g., subarrays with a target sum).  
✅ Works in **O(n) time** using **prefix sums**.

### **Example Problems**
| Problem Type                       | Approach |
|------------------------------------|----------|
| **Count Subarrays sum equals K**   | Prefix Sum + HashMap (`O(n)`) |
| **Count subarrays divisible by K** | Prefix Sum + Modulo (`O(n)`) |

### **Example: Count Subarrays with Sum K**
```python
def subarray_sum(nums: list[int], k: int) -> int:
    prefix_sum = {0: 1}  
    current_sum = 0
    count = 0

    for num in nums:
        current_sum += num
        if current_sum - k in prefix_sum:
            count += prefix_sum[current_sum - k]
        prefix_sum[current_sum] = prefix_sum.get(current_sum, 0) + 1

    return count
```
✅ **Use When**: Finding subarrays based on **sum conditions**.

---


### 21. **Find if There is Any Subarray with Sum Equal to 0**
- **Hint**: Use a hash map to store the cumulative sum as you traverse the array. If a cumulative sum repeats, the subarray sum is zero.


3. **[974. Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/)**
    - Find the number of subarrays whose sum is divisible by `k`.
    - **Condition:** Sum of elements must be divisible by `k`.
    - **Difficulty:** Medium



---