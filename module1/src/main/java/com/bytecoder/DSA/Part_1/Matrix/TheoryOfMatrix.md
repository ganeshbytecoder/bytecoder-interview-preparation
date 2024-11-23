### 1. **Spiral Traversal on a Matrix**
   - **Hint**: Maintain four boundaries: top, bottom, left, and right. Traverse the matrix in a spiral order by adjusting these boundaries after each traversal in one of the four directions (left to right, top to bottom, right to left, and bottom to top).

### 2. **Search an Element in a Matrix**
   - **Hint**: If the matrix is sorted row-wise and column-wise, start the search from the top-right corner. If the target is smaller, move left; if larger, move down. This approach works in O(n + m) time.

### 3. **Find Median in a Row-Wise Sorted Matrix**
   - **Hint**: Use binary search on the possible range of matrix elements. For each middle value in the binary search, count how many elements are less than or equal to it by using binary search in each row.

### 4. **Find Row with Maximum Number of 1s**
   - **Hint**: Start from the top-right corner of the matrix. If you encounter a 1, move left; otherwise, move down. Keep track of the row index where you move left the most.

### 5. **Print Elements in Sorted Order Using Row-Column Wise Sorted Matrix**
   - **Hint**: Use a min-heap (or priority queue) to extract the minimum element. Insert the next element from the same row into the heap until all elements are processed.

### 6. **Maximum Size Rectangle (In a Binary Matrix)**
   - **Hint**: Treat each row as a histogram. Use a stack-based method to find the largest rectangle in a histogram. Iterate through rows, updating the height of the histogram at each step.

### 7. **Find a Specific Pair in Matrix**
   - **Hint**: Maintain an auxiliary matrix that stores the maximum element from the bottom-right corner to the current position. For each element, compare it with the corresponding maximum in the auxiliary matrix and update accordingly.

### 8. **Rotate Matrix by 90 Degrees**
   - **Hint**: First, transpose the matrix (swap rows with columns). Then reverse each row to achieve a 90-degree clockwise rotation.

### 9. **Kth Smallest Element in a Row-Column Wise Sorted Matrix**
   - **Hint**: Use a min-heap (or priority queue). Start by inserting the smallest element of each row into the heap. Extract the minimum element and insert the next element from the same row until you reach the Kth smallest element.

### 10. **Common Elements in All Rows of a Given Matrix**
   - **Hint**: Use a hash map to count the frequency of each element across rows. Traverse the matrix row by row, and only update the count for elements present in all previous rows.



https://leetcode.com/problems/01-matrix/description/ 

https://leetcode.com/problems/rotting-oranges/description/

https://leetcode.com/problems/word-search/?envType=problem-list-v2&envId=array&status=TO_DO 

https://leetcode.com/problems/number-of-islands/description/?envType=problem-list-v2&envId=matrix 

https://leetcode.com/problems/01-matrix/description/


https://leetcode.com/problems/unique-paths/ 