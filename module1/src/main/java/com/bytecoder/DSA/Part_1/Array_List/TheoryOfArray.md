
### 6. **Find the Union and Intersection of Two Sorted Arrays**
**Hint**: 
   - brute force
   - Use two pointers, one for each array, and traverse through both arrays simultaneously. Use comparison to find union and intersection.
   - use hashmap for each array and cross check elements

### 7. **Cyclically Rotate an Array by One**
   - **Hint**: Store the last element in a temporary variable, then shift all other elements to the right, and place the last element at the first position.

### 8. **Find Largest Sum Contiguous Subarray (Kadane's Algorithm)**
   - **Hint**: Use Kadane's algorithm by maintaining a running sum and a global maximum. Reset the running sum to zero if it becomes negative.

### 9. **Minimize the Maximum Difference Between Heights**
   - **Hint**: Sort the array, then consider adding or subtracting `k` from the minimum and maximum heights to minimize the difference.

### 10. **Minimum No. of Jumps to Reach End of an Array**
   - **Hint**: Use a greedy approach to keep track of the farthest reachable index. Increment the jump count when you move beyond the current range.

### 11. **Find Duplicate in an Array of N+1 Integers**
   - **Hint**: Use Floyd's Tortoise and Hare (Cycle Detection) method to find the duplicate.

### 12. **Merge Two Sorted Arrays Without Using Extra Space**
    Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    Output: [1,2,2,3,5,6]


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
     





### 17. Best Time to Buy and Sell Stock (https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/)
   - **Hint**: Track the minimum price encountered so far and calculate the maximum profit by comparing it with the current price.
   - dp : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/

### 18. **Find All Pairs on Integer Array Whose Sum is Equal to Given Number**
   - **Hint**: Use a hash map to store the complement of each number as you traverse the array, and check if the complement exists in the map.

### 19. **Find Common Elements in 3 Sorted Arrays**
   - **Hint**: Use three pointers, one for each array, and increment the pointers based on comparison to find common elements.

### 20. **Rearrange the Array in Alternating Positive and Negative Items with O(1) Extra Space**
   - **Hint**: Partition the array into positive and negative numbers, then rearrange by swapping elements at even and odd indices.

### 21. **Find if There is Any Subarray with Sum Equal to 0**
   - **Hint**: Use a hash map to store the cumulative sum as you traverse the array. If a cumulative sum repeats, the subarray sum is zero.

### 22. **Find Factorial of a Large Number**
   - **Hint**: Use an array or a list to store digits and simulate the multiplication process digit by digit.

### 23. **Find Maximum Product Subarray**
   - **Hint**: Track the maximum and minimum products at each position due to the possibility of negative numbers flipping signs.

### 24. **Find Longest Consecutive Subsequence**
   - **Hint**: Use a hash set to store the elements, then for each element, check if it's the start of a sequence by checking if `element - 1` exists.

### 25. **Find Elements That Appear More Than "n/k" Times**
   - **Hint**: Use a modified Boyer-Moore majority vote algorithm to count potential candidates.

* ### 26. **Maximum Profit by Buying and Selling a Share At Most Twice**
   - **Hint**: Create two arrays: one for the maximum profit by selling up to that day, and the other for the maximum profit by buying from that day onwards.

### 27. **Find Whether an Array is a Subset of Another Array**
   - **Hint**: Use a hash set or binary search to check the presence of each element of one array in another.

### 28. **Find the Triplet that Sum to a Given Value**
   - **Hint**: Sort the array, then for each element, use the two-pointer technique to find the other two elements.

### 29. **Trapping Rain Water Problem**
   - **Hint**: Calculate the left and right maximum heights for each element and use them to find the trapped water.

### 30. **Chocolate Distribution Problem**
   - **Hint**: Sort the array, then find the minimum difference between the largest and smallest values in each subarray of size `m`.

### 31. **Smallest Subarray with Sum Greater Than a Given Value**
   - **Hint**: Use a sliding window technique to expand and shrink the window while keeping track of the sum.

* ### 32. **Three-Way Partitioning of an Array Around a Given Value**
   - **Hint**: Use the Dutch National Flag algorithm with three pointers to partition the array into three segments based on the given value.

### 33. **Minimum Swaps Required to Bring together Elements Less than or Equal to K Together**
   - **Hint**: Find the number of elements less than or equal to `K`, then use a sliding window approach to count swaps in subarrays of that size.

### 34. **Minimum No. of Operations Required to Make an Array Palindrome. The only allowed operation is”merging” (of two adjacent elements)**
   - **Hint**: Use a two-pointer approach and incrementally match elements at both ends by either merging or replacing them.

### 35. **Median of Two Sorted Arrays of Equal Size**
   - **Hint**: Use binary search on one array to partition both arrays into two halves that balance the number of elements and the median condition.
   - Merge two sorted arrays  (Pq) then get the median
   - Merge sorting and then get the median  (keep m1 and m2)
     - Case 1: m+n is odd, the median is at (m+n)/2th index in the array obtained after merging both the arrays. 
     - Case 2: m+n is even, the median will be the average of elements at index ((m+n)/2 – 1) and (m+n)/2 in the array obtained after merging both the arrays



### 36. **Median of Two Sorted Arrays of Different Sizes**
   - **Hint**: Use a binary search on the smaller array to find the partition where the median lies, ensuring elements to the left are smaller than elements to the right.
   - Merge two sorted arrays  (Pq) then get the median
   - Merge sorting and then get the median  (keep m1 and m2)
     - Case 1: m+n is odd, the median is at (m+n)/2th index in the array obtained after merging both the arrays. 
     - Case 2: m+n is even, the median will be the average of elements at index ((m+n)/2 – 1) and (m+n)/2 in the array obtained after merging both the arrays

### 15. **Next Permutation**
   - **Hint**: Find the first decreasing element from the right, swap it with the next largest element, and reverse the subsequent part of the array.


### 16. **Count Inversions**
   - **Hint**: Use a modified merge sort algorithm that counts inversions while merging the subarrays.


https://leetcode.com/problems/combination-sum-ii/description/?envType=problem-list-v2&envId=array&status=TO_DO


