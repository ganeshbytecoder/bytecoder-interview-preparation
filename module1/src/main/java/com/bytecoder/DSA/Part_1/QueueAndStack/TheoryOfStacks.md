**Problem** balancing of symbols,
**Problem** reverse order of element or check palindrome

**Problem** undo sequence and page history

**Problem** evaluation of postfix expression

**Problem** matching tags in html and xml

**Problem** design a stack such that getMinimum() should be O(1)

hint:
* take an auxiliary stack that maintains the minimum of all values in the stack.

**Problem** largest rectangle under the histogram

**Problem** given an array of elements , replace every element with the nearest greater element on the right of that element

hint:
* brute-force using two for loop 

**Problem** given a singly linked list l1->l2->l3.....-> ln. reorder it to l1->ln-> l2->ln-1....


**Problem** list of Max element in sliding window on an array

hint :
* using brute-force
* using priority queue



Here are multiple hints for each of the stack and queue problems:

## Stack Problems:

5. **Check Balanced Parentheses**
   - **Hint**: Use a stack to push opening brackets and pop them when a matching closing bracket is found. If the stack is empty after the entire traversal and no unmatched brackets remain, the expression is balanced.

6. **Reverse a String using Stack**
   - **Hint**: Push all characters of the string into a stack and pop them one by one to form the reversed string.

7. **Design Stack to Support getMin() in O(1)**
   - **Hint**: Maintain an auxiliary stack to track the minimum element along with the main stack. Push the new minimum onto the auxiliary stack when necessary.

8. **Find the Next Greater Element**
   - **Hint**: Traverse the array from right to left, pushing elements onto a stack. For each element, pop smaller elements and use the stack's top as the next greater element.

9. **The Celebrity Problem**
   - **Hint**: Use two pointers or a stack. Keep eliminating non-celebrities by comparing them, eventually narrowing down to a single candidate.

10. **Arithmetic Expression Evaluation**
    - **Hint**: Use two stacks: one for operators and one for operands. Follow the operator precedence and associativity rules while pushing and popping elements.

11. **Evaluation of Postfix Expression**
    - **Hint**: Traverse the expression and push operands onto the stack. When encountering an operator, pop the top two elements, apply the operator, and push the result back onto the stack.

12. **Insert Element at the Bottom of Stack Without Extra DS**
    - **Hint**: Use recursion to pop all elements, insert the new element at the bottom, and push all the popped elements back onto the stack.

13. **Reverse a Stack Using Recursion**
    - **Hint**: Recursively pop elements until the stack is empty, then insert each element at the bottom using a helper recursive function.

14. **Sort a Stack Using Recursion**
    - **Hint**: Pop elements recursively and insert them back in sorted order using a helper function that finds the correct position for each element.

15. **Largest Rectangular Area in Histogram**
    - **Hint**: Use a stack to store indices of histogram bars. Traverse the histogram and calculate the area for the bars stored in the stack.

16. **Length of the Longest Valid Substring**
    - **Hint**: Use a stack to store indices of unbalanced opening brackets. Calculate the length of valid substrings using the stack and track the longest.

17. **Expression Contains Redundant Brackets**
    - **Hint**: Use a stack to track operators and parentheses. If you find consecutive parentheses without any operators between them, they are redundant.

18. **Implement Stack Using Queue**
    - **Hint**: Use two queues and simulate stack behavior by ensuring the push or pop operation involves transferring elements between queues.

19. **Implement Stack Using Deque**
    - **Hint**: Use a double-ended queue to simulate stack operations (push and pop) from one end.

20. **Stack Permutations**
    - **Hint**: Use a stack to simulate the process of permutation and verify if it’s possible by simulating the stack operations.

---

## Queue Problems:


7. **Reverse First "K" Elements of a Queue**
   - **Hint**: Dequeue the first K elements into a stack and enqueue them back to the queue, followed by the remaining elements.

8. **Interleave the First Half of Queue with Second Half**
   - **Hint**: Use a stack or an auxiliary queue to reverse the first half and merge it with the second half in an interleaving manner.

9. **First Circular Tour That Visits All Petrol Pumps**
   - **Hint**: Use a queue to simulate the circular tour. Track the current fuel balance as you simulate the tour across petrol pumps.

10. **Minimum Time to Rot All Oranges**
    - **Hint**: Use a BFS approach by enqueuing all rotten oranges initially. Traverse the matrix in four directions from each rotten orange and count the time taken for fresh oranges to rot.

11. **Distance of Nearest Cell Having 1 in a Binary Matrix**
    - **Hint**: Use BFS from all cells containing 1s. Initialize their distances to 0 and propagate the minimum distance to adjacent cells.

12. **First Negative Integer in Every Window of Size "k"**
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

