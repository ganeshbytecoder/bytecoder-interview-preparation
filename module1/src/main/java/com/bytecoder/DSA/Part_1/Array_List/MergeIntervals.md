* https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/5950330/more-simpler-java-solution/
* https://leetcode.com/problems/insert-interval/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/summary-ranges/description/?envType=study-plan-v2&envId=top-interview-150


### 14. **Merge Intervals**
        - **Hint**: Sort intervals by their start times, then merge overlapping intervals by checking if the end of one interval is greater than or equal to the start of the next.

        >
        > Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

        >Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
