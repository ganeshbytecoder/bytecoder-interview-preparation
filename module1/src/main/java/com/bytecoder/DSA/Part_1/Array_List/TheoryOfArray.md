### 1. **Reverse the Array**
   - **Hint**:
   - In-Place Reversal with Loop: This is the most efficient way to reverse an array in terms of both time and space complexity. It only requires O(n) time and O(1) space. 
   - New Array Method: This approach is simpler but requires additional space (O(n)). 
   - Using Collections API: Suitable for object arrays, not for primitive arrays.
   - Use two pointers, one at the beginning and one at the end, and swap the elements until the pointers meet in the middle.


### 2. **Find the Maximum and Minimum Element in an Array**
   - **Hint**: Traverse the array while maintaining two variables for the maximum and minimum values.

### 3. **Find the "Kth" Max and Min Element of an Array**
   - **Hint**: Sort the array, and the Kth max will be at `arr[n-k]`, and the Kth min will be at `arr[k-1]`. Alternatively, use a min-heap or max-heap for better performance.

### 4. **Sort the Array Consisting of Only 0s, 1s, and 2s**
   - **Hint**: Use the Dutch National Flag algorithm with three pointers (low, mid, high) to partition the array into three segments.

### 5. **Move All Negative Elements to One Side of the Array**
   - **Hint**: Use the partition process from the quicksort algorithm to move negative elements to one side.

### 6. **Find the Union and Intersection of Two Sorted Arrays**
   - **Hint**: Use two pointers, one for each array, and traverse through both arrays simultaneously. Use comparison to find union and intersection.

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
   - **Hint**: Use the gap method, where you gradually reduce the gap between elements and compare corresponding elements across the two arrays.

### 13. **Kadane's Algorithm**
   - **Hint**: Track the maximum subarray sum by iterating through the array and maintaining a current sum that resets if it becomes negative.

### 14. **Merge Intervals**
   - **Hint**: Sort intervals by their start times, then merge overlapping intervals by checking if the end of one interval is greater than or equal to the start of the next.

### 15. **Next Permutation**
   - **Hint**: Find the first decreasing element from the right, swap it with the next largest element, and reverse the subsequent part of the array.

### 16. **Count Inversions**
   - **Hint**: Use a modified merge sort algorithm that counts inversions while merging the subarrays.

### 17. **Best Time to Buy and Sell Stock**
   - **Hint**: Track the minimum price encountered so far and calculate the maximum profit by comparing it with the current price.

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

### 26. **Maximum Profit by Buying and Selling a Share At Most Twice**
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

### 32. **Three-Way Partitioning of an Array Around a Given Value**
   - **Hint**: Use the Dutch National Flag algorithm with three pointers to partition the array into three segments based on the given value.

### 33. **Minimum Swaps Required to Bring Elements Less than or Equal to K Together**
   - **Hint**: Find the number of elements less than or equal to `K`, then use a sliding window approach to count swaps in subarrays of that size.

### 34. **Minimum No. of Operations Required to Make an Array Palindrome**
   - **Hint**: Use a two-pointer approach and incrementally match elements at both ends by either merging or replacing them.

### 35. **Median of Two Sorted Arrays of Equal Size**
   - **Hint**: Use binary search on one array to partition both arrays into two halves that balance the number of elements and the median condition.

### 36. **Median of Two Sorted Arrays of Different Sizes**
   - **Hint**: Use a binary search on the smaller array to find the partition where the median lies, ensuring elements to the left are smaller than elements to the right.
