

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




https://leetcode.com/problems/count-vowel-substrings-of-a-string/
https://leetcode.com/problems/longest-substring-of-all-vowels-in-order/description/
https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/description/?envType=daily-question&envId=2025-03-10