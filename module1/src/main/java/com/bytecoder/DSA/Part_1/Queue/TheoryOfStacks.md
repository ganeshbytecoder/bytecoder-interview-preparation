## Queue Problems:
- normal queue, double ended queue
- prioty queue

https://leetcode.com/problems/simplify-path/submissions/1521546931/?envType=study-plan-v2&envId=top-interview-150
https://leetcode.com/problems/min-stack/?envType=study-plan-v2&envId=top-interview-150
https://leetcode.com/problems/evaluate-reverse-polish-notation/description/?envType=study-plan-v2&envId=top-interview-150
https://leetcode.com/problems/decode-string/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
https://leetcode.com/problems/exclusive-time-of-functions/?envType=company&envId=linkedin&favoriteSlug=linkedin-thirty-days

https://leetcode.com/problems/exclusive-time-of-functions/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days


---




7. **Reverse First "K" Elements of a Queue**
   - **Hint**: Dequeue the first K elements into a stack and enqueue them back to the queue, followed by the remaining elements.

8. **Interleave the First Half of Queue with Second Half with one extra stack only**
   - **Hint**: Use a stack or an auxiliary queue to reverse the first half and merge it with the second half in an interleaving manner.

9. **First Circular Tour That Visits All Petrol Pumps**
   - **Hint**: Use a queue to simulate the circular tour. Track the current fuel balance as you simulate the tour across petrol pumps.

10. **Minimum Time to Rot All Oranges**  https://leetcode.com/problems/rotting-oranges/description/
    - **Hint**: Use a BFS approach by enqueuing all rotten oranges initially. Traverse the matrix in four directions from each rotten orange and count the time taken for fresh oranges to rot.

11. **Distance of Nearest Cell Having 1 in a Binary Matrix**
    - **Hint**: Use BFS from all cells containing 1s. Initialize their distances to 0 and propagate the minimum distance to adjacent cells.

12. **First Negative Integer in Every Window of Size "k"** \
    - Given an array arr[]  and a positive integer k, find the first negative integer for each and every window(contiguous subarray) of size k.

Note: If a window does not contain a negative integer, then return 0 for that window
    - **Hint**: Use a deque to maintain indices of negative elements within the current window. Slide the window across the array and check the deque for negative elements.

13. **Check if All Levels of Two Trees Are Anagrams**
    - **Hint**: Traverse both trees level-by-level using BFS. Check if the nodes at each level form an anagram by comparing frequency counts of elements.

14. **Sum of Minimum and Maximum Elements in Subarrays of Size "k"**
    - **Hint**: Use two deques to track the minimum and maximum elements in the current window of size "k".

15. **Minimum Operations to Make Array Palindrome**
    - **Hint**: Use two pointers (front and rear) and repeatedly combine the smallest elements (by adding) to make the array symmetric.

16. **Queue-Based Approach for First Non-Repeating Character**
    - **Hint**: Use a queue and a frequency map to track the first non-repeating character. As characters are streamed in, update their frequency and check the queue for non-repeating characters.

17. **Next Smaller Element**
    - **Hint**: Similar to "Next Greater Element," but traverse from right to left and maintain a stack that tracks the next smaller element for each item.

