
### **Greedy Problems:**

1. **Activity Selection Problem**
   - Sort activities by their finish times.
   - Always pick the next activity with the earliest finish time that starts after the last selected activity.

2. **Job Sequencing Problem**
   - Sort jobs in decreasing order of profit.
   - Use a greedy approach to assign the highest-profit jobs to the latest available slots before their deadlines.

3. **Huffman Coding**
   - Use a priority queue (min-heap) to build a binary tree where the most frequent characters are closer to the root.
   - The Huffman tree minimizes the overall coding length for characters.

4. **Water Connection Problem**
   - Use a union-find (or DFS) approach to identify connected components, and then determine the minimum pipe diameter for each component.

5. **Fractional Knapsack Problem**
   - Sort items by value/weight ratio in descending order.
   - Take as much as possible of the item with the highest ratio until the knapsack is full.

6. **Greedy Algorithm to Find Minimum Number of Coins**
   - Use the largest denomination first, then the next largest, and so on until you reach the total amount.

7. **Maximum Trains for Which Stoppage Can be Provided**
   - Sort the trains by their arrival times and use a greedy approach to provide platforms to trains while avoiding overlaps.

8. **Minimum Platforms Problem**
   - Sort arrival and departure times separately.
   - Use a two-pointer approach to count the number of platforms required at any time by comparing arrivals and departures.

9. **Buy Maximum Stocks if i Stocks Can be Bought on i-th Day**
   - Sort the stocks by price and buy as many stocks as you can starting with the cheapest, up to the limit on each day.

10. **Find the Minimum and Maximum Amount to Buy All N Candies**
   - Sort the prices of candies. To minimize cost, buy the cheapest candies and get the most expensive free; to maximize, buy the most expensive and get the cheapest free.

11. **Minimize Cash Flow Among Friends Who Have Borrowed Money**
   - Use a greedy approach to settle debts by always settling the largest net debt first.

12. **Minimum Cost to Cut a Board into Squares**
   - Sort the cuts by cost in descending order and always make the most expensive cut first to minimize the total cost.

13. **Check if it is Possible to Survive on Island**
   - Calculate the total food needed for survival and compare it with the maximum possible food that can be gathered. Check if the required food can be gathered within the allowed days.

14. **Find Maximum Meetings in One Room**
   - Sort meetings by their end times.
   - Always pick the earliest finishing meeting that does not overlap with the last selected meeting.

15. **Maximum Product Subset of an Array**
   - Count the number of negative and zero elements. If there is an even number of negative elements, multiply all non-zero elements together; if odd, skip the smallest negative.

16. **Maximize Array Sum after K Negations**
   - Sort the array and flip the sign of the smallest (most negative) element `K` times.

17. **Maximize the Sum of `arr[i]*i`**
   - Sort the array and compute the sum by multiplying each element with its index.

18. **Maximum Sum of Absolute Difference of an Array**
   - Sort the array, and arrange the elements in a sequence where large and small numbers alternate.

19. **Maximize Sum of Consecutive Differences in a Circular Array**
   - Use a sorted array and alternate placing the largest and smallest elements for maximum difference.

20. **Minimum Sum of Absolute Differences of Pairs of Two Arrays**
   - Sort both arrays and pair elements from corresponding indices to minimize the sum of differences.

21. **Program for Shortest Job First (SJF) CPU Scheduling**
   - Sort the jobs by their burst times and execute the shortest jobs first to minimize average waiting time.

22. **Program for Least Recently Used (LRU) Page Replacement Algorithm**
   - Use a queue or a hash map to store pages and keep track of their last usage time. Replace the least recently used page when a new page is needed.

23. **Smallest Subset with Sum Greater than All Other Elements**
   - Sort the array in descending order and pick elements from the start until their sum exceeds the sum of the remaining elements.

24. **Chocolate Distribution Problem**
   - Sort the packets of chocolates and find the minimum difference between the largest and smallest chocolates in any contiguous subarray of size `m`.

25. **DEFKIN - Defense of a Kingdom**
   - Sort the horizontal and vertical coordinates of the towers. Calculate the maximum width and height of the undefended areas.

26. **DIEHARD - DIE HARD**
   - Use dynamic programming or greedy strategies based on the problem's conditions (e.g., survival strategies in different conditions).

27. **GERGOVIA - Wine Trading in Gergovia**
   - Use a greedy approach to settle trades by moving wine from surplus to deficit houses, minimizing total work.

28. **Picking Up Chicks**
   - Sort the chicks by position and use a greedy approach to count the minimum number of swaps required to get `K` chicks to their destination on time.

29. **CHOCOLA – Chocolate**
   - Sort the vertical and horizontal cuts by cost in descending order, making the most expensive cuts first.

30. **ARRANGE - Arranging Amplifiers**
   - Sort the amplifiers based on their effect and arrange them for maximum effect.

31. **K Centers Problem**
   - Use a greedy approach to select `K` centers such that the maximum distance between any point and its nearest center is minimized.

32. **Minimum Cost of Ropes**
   - Use a min-heap to connect the ropes. Combine the two smallest ropes, and repeat the process while minimizing total cost.

33. **Find Smallest Number with Given Number of Digits and Sum of Digits**
   - Use a greedy approach to allocate the sum across the digits, starting from the most significant digit.

34. **Rearrange Characters in a String such that No Two Adjacent are the Same**
   - Use a max-heap to store characters by frequency and construct the string by placing the most frequent characters first.

35. **Find Maximum Sum Possible Equal Sum of Three Stacks**
   - Use a greedy approach to reduce the height of the largest stack until all three stacks have equal height.


