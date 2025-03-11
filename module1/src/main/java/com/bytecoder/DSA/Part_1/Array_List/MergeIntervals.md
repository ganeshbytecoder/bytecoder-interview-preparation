
1️⃣ **Merge Intervals (Sorting + Greedy)**  
✅ Used for interval problems like **meeting rooms, merge overlapping intervals**.

**Example:** **Merge Intervals**
```python
def merge_intervals(intervals: list[list[int]]) -> list[list[int]]:
    intervals.sort()  
    merged = []

    for interval in intervals:
        if not merged or merged[-1][1] < interval[0]:
            merged.append(interval)
        else:
            merged[-1][1] = max(merged[-1][1], interval[1])

    return merged
```

---
* https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/5950330/more-simpler-java-solution/
* https://leetcode.com/problems/insert-interval/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/summary-ranges/description/?envType=study-plan-v2&envId=top-interview-150
