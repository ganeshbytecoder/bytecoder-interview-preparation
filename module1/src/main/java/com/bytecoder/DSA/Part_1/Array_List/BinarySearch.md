
## **7️⃣ Binary Search on Subarrays**
Used when:
✅ Searching in **sorted arrays or subarrays** efficiently.  
✅ Works in **O(log n) time**.

### **Example Problems**
| Problem Type | Approach |
|-------------|----------|
| **Find First and Last Position in Sorted Array** | Binary Search (`O(log n)`) |
| **Find K-th missing element** | Binary Search (`O(log n)`) |

### **Example: Find First and Last Position**
```python
def search_range(nums: list[int], target: int) -> list[int]:
    def find_boundary(left=True):
        lo, hi = 0, len(nums) - 1
        while lo <= hi:
            mid = (lo + hi) // 2
            if nums[mid] > target or (left and nums[mid] == target):
                hi = mid - 1
            else:
                lo = mid + 1
        return lo

    left_index = find_boundary(True)
    return [left_index, find_boundary(False) - 1] if left_index < len(nums) and nums[left_index] == target else [-1, -1]
```
✅ **Use When**: Searching efficiently in **sorted arrays**.

---


https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/?envType=problem-list-v2&envId=array&

https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150
