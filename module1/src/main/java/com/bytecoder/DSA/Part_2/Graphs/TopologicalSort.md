
# **Implement Topological Sort** 
```
Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u-v, vertex u comes before v in the ordering.

Note: Topological Sorting for a graph is not possible if the graph is not a DAG.
- it is used in scenarios where certain tasks must be done before others. such as scheduling tasks, resolving coureses prequites structures. 

hamilaton path exists ig a topological sort order is unique.



```
![img_1.png](img_1.png)
#### implementations: 
### **DFS:**
   - perform a dfs traversal of the graph
   - for every vertex, after visiting all its adjacent vertices, push it onto stack
   - once all the vertices are visited, pop vertices from the stack to get the topological sort order
   -  if(stack.size != no. of nodes) :  graph contains a cycle or topological sort is not possible

### **Kahn’s Algorithm(BFS):**
   - compute the in-degree (number of incoming edges) for each vertex
   - initialise the queue with all vertices with an in-degree of 0
   - Dequeue a vertex, add it to topological order & reduce in-degree of its adjacent vertices
   - if the on-degree of an adjacent vertex become 0, enqueue it 
   - continue this process until the queue is empty 
   - Note - if count != vertices : graph contains a cycle or topological sort is not possible

### 14. **Minimum Time Taken by Each Job in a DAG**
   - Perform topological sorting and calculate the time taken for each job by relaxing edges according to the topological order.

### 15. **Find if All Tasks Can be Finished**
   - Use topological sort to detect cycles. If a cycle exists, not all tasks can be completed.

### 16. **Find the Number of Islands**
   - Treat the grid as a graph and use DFS/BFS to count the number of connected components of '1's (land).

### 17. **Alien Dictionary (Order of Characters)**
   - Create a graph with characters as nodes and edges representing the ordering between characters.
   - Perform topological sort on the graph to find the character order.


### **Find all possible topological sort**

### **is given topological sort valid ?**
