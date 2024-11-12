### Notes on Greedy Algorithm in DSA (Data Structures and Algorithms)

**Definition:**
A greedy algorithm is a problem-solving technique that makes the optimal choice at each step with the hope of finding the global optimum. It builds up a solution piece by piece, always choosing the next piece that offers the most immediate benefit without considering future consequences.

**Key Characteristics:**
1. **Greedy Choice Property**: The algorithm makes a choice that seems best at the moment and moves forward without reconsidering previous choices.

**General Approach:**
1. **Initialize**: Start from an initial state. question is asking for min or max solution
2. **Choose**: Sort and select the locally optimal choice.
3. **Feasibility Check**: Ensure the current choice leads to a feasible solution.
4. **Repeat**: Continue making choices until a complete solution is found.
5. **Solution Construction**: Collect the selected choices to form the final result.



## examples
### **Min/Max number of Coin Change Problem**

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

```text
Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3

Example 2:
Input: coins = [2], amount = 3
Output: -1

```


````java
    //  greedy
// descending of coins , ans = max  
    int greedy_solution(int[] coins, int amount, int ans) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return 100000;
        }

        for (int  i=coins.length-1 ; i>=0 ; i-- ) {
            if (amount - coins[i] >= 0) {
                ans = Math.min(ans, 1 + greedy_solution(coins, amount - coins[i], ans));
            }
        }
        return ans;
    }
````

```java

```


**N Meetings in One room**

**Maximum Length of Pair Chain**

problem : You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
Example 1:
Input: pairs = [[1,2],[2,3],[3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4].

similarly n meetings in one room will be done 

```java
    public int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, (p1, p2)-> p1[1]-p2[1]);
        int min=Integer.MIN_VALUE;
        int count=0;
         for (int i = 0; i <pairs.length; i++) {
            if(pairs[i][0] > min){
                count++;
                min=pairs[i][1];
                System.out.println(pairs[i][1]);
            }
        }
        return count;
    }
```


### Minimum Number of Arrows to Burst Balloons:

```java
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a,b) -> Integer.compare(a[0],b[0]));
        int end= points[0][1];
        int count=1;

         for (int i = 1; i < points.length; i++) {
            if(points[i][0] <= end){
                end = Math.min(end,points[i][1]);
            }else{
                end = points[i][1];
                count++;
            }
        }

        return count;
    }
```



**Max Meetings in One Room**



Activity Selection Problem:

You are given n activities with their start and finish times. Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time. 

Input: start[]  =  {10, 12, 20}, finish[] =  {20, 25, 30}
Output: 0
Explanation: A person can perform at most one activities.


Input: start[]  =  {1, 3, 0, 5, 8, 5}, finish[] =  {2, 4, 6, 7, 9, 9};
Output: 0 1 3 4
Explanation: A person can perform at most four activities. The 
maximum set of activities that can be executed 
is {0, 1, 3, 4} [ These are indexes in start[] and finish[]

The greedy choice is to always pick the next activity whose finish time is the least among the remaining activities and the start time is more than or equal to the finish time of the previously selected activity. We can sort the activities according to their finishing time so that we always consider the next activity as the minimum finishing time activity




Shop Candy Problem:



Chocolate Distribution Problem:

Min Cost of Ropes:

Huffman Encoding / merge files into one problem 1111111111111111:

0/1 Knapsack problem 

Fractional Knapsack:

Job Sequencing Problem:

rope merge fir min cost 

huffman codding 

number of railway platforms 


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




https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/

https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/5950330/more-simpler-java-solution/


https://leetcode.com/problems/non-overlapping-intervals/description/


https://leetcode.com/problems/min-cost-to-connect-all-points/description/