# **Implement Topological Sort**

```
Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u-v, vertex u comes before v in the ordering.

Note: Topological Sorting for a graph is not possible if the graph is not a DAG.
- it is used in scenarios where certain tasks must be done before others. such as scheduling tasks, resolving coureses prequites structures. 

hamilaton path exists ig a topological sort order is unique.



```

Topological Sort:
https://leetcode.com/problems/course-schedule-ii/

![img_1.png](img_1.png)

### 3. Topological Sort (DAG Pattern)

**Use Cases:** Task scheduling, dependency resolution, course prerequisites, build systems

**💡 Key Insight:** Only works on DAG (Directed Acyclic Graph). Two approaches: 1) Kahn's Algorithm (BFS with in-degree), 2) DFS with post-order. Detect cycle if can't visit all nodes.

**Time:** O(V + E) | **Space:** O(V)

#### Kahn's Algorithm (BFS approach):

```
from collections import deque, defaultdict

def topological_sort(n, edges):
    graph = defaultdict(list)
    in_degree = [0] * n
  
    # Build graph and count in-degrees
    for u, v in edges:
        graph[u].append(v)
        in_degree[v] += 1
  
    # Start with nodes having no prerequisites
    queue = deque([i for i in range(n) if in_degree[i] == 0])
    result = []
  
    while queue:
        node = queue.popleft()
        result.append(node)
      
        for neighbor in graph[node]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)
  
    # Cycle detection
    return result if len(result) == n else []
```

#### DFS Approach (Post-order):

```
def topological_sort_dfs(n, edges):
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
  
    visited = set()
    rec_stack = set()  # For cycle detection
    result = []
  
    def dfs(node):
        if node in rec_stack:
            return False  # Cycle detected
        if node in visited:
            return True
      
        rec_stack.add(node)
        for neighbor in graph[node]:
            if not dfs(neighbor):
                return False
        rec_stack.remove(node)
      
        visited.add(node)
        result.append(node)
        return True
  
    for i in range(n):
        if i not in visited:
            if not dfs(i):
                return []  # Cycle exists
  
    return result[::-1]  # Reverse for correct order
```

**Common Problems:**

* Course Schedule (207) - Cycle detection
* Course Schedule II (210) - Return order
* Alien Dictionary (269)
* Course Schedule IV (1462)
* Largest Color Value in Directed Graph (1857)

#### implementations:

### **DFS:**

- perform a dfs traversal of the graph
- for every vertex, after visiting all its adjacent vertices, push it onto stack
- once all the vertices are visited, pop vertices from the stack to get the topological sort order
- if(stack.size != no. of nodes) :  graph contains a cycle or topological sort is not possible

```java


/*                                Topological Sort (DFS)
--------------------------------------------------------------------------------------*/
import java.util.*;

class Solution {
    private void dfs(Map<Integer, List<Integer>> adj, int src, Stack<Integer> stack, HashSet<Integer> visited) {
        if (visited.contains(src))
            return;

        visited.add(src);

        for (int neighbor : adj.get(src)) {
            if (!visited.contains(neighbor)) {
                dfs(adj, neighbor, stack, visited);
            }
        }

        stack.push(src);
    }

    private boolean checkCycle(Map<Integer, List<Integer>> adj, int src, int[] state) {
        if (state[src] == 1) return true;  // Cycle detected
        if (state[src] == 2) return false; // Already processed

        state[src] = 1; // Mark as visiting
        for (int neighbor : adj.get(src)) {
            if (checkCycle(adj, neighbor, state)) {
                return true;
            }
        }
        state[src] = 2; // Mark as visited
        return false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            adjList.get(edge[1]).add(edge[0]); // Correct edge direction
        }

        int[] state = new int[numCourses];
        // 0 -> not visited 
        // 1->  Mark as visiting
        // 2 -> Mark as visited (fully processed)
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && checkCycle(adjList, i, state)) {
                return new int[0]; // Cycle found
            }
        }

        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited.contains(i)) {
                dfs(adjList, i, stack, visited);
            }
        }

        int[] order = new int[numCourses];
        int i = 0;
        while (!stack.isEmpty()) {
            order[i++] = stack.pop();
        }
        return order;
    }
}

/*------------------------------------------------------------------------------------*/
```

### **Kahn’s Algorithm(BFS):** (better approach less code)

- compute the in-degree (number of incoming edges) for each vertex
- initialise the queue with all vertices with an in-degree of 0
- Dequeue a vertex, add it to topological order & reduce in-degree of its adjacent vertices
- if the on-degree of an adjacent vertex become 0, enqueue it
- continue this process until the queue is empty
- Note - if count != vertices : graph contains a cycle or topological sort is not possible

```java


class Solution {

    private boolean checkCycle(Map<Integer, List<Integer>> adj, int src, int[] state) {
            if (state[src] == 1) return true;  // Cycle detected
            if (state[src] == 2) return false; // Already processed

            state[src] = 1; // Mark as visiting
            for (int neighbor : adj.get(src)) {
                if (checkCycle(adj, neighbor, state)) {
                    return true;
                }
            }
            state[src] = 2; // Mark as visited
            return false;
        }
      
        //     this is used when order is possible or not
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            adjList.get(edge[1]).add(edge[0]); // Correct edge direction
        }

        int[] state = new int[numCourses];
        // 0 -> not visited 
        // 1->  Mark as visiting
        // 2 -> Mark as visited (fully processed)
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && checkCycle(adjList, i, state)) {
                return false;
            }
        }
        return true;
      
    }



//     this is used when order is asked
     public boolean canFinish(int numCourses, int[][] prerequisites) {

         Map<Integer, List<Integer>> graph = new HashMap<>();
         int[] indegree = new int[numCourses];
         for(int i= 0; i<numCourses; i++){
             graph.put(i, new ArrayList<>());
         }

         for(int[] edge : prerequisites){
             graph.get(edge[1]).add(edge[0]);
             indegree[edge[0]]++;
         }

         Queue<Integer> queue =new LinkedList<>();

         for(int i = 0; i < numCourses; i++){
             if(indegree[i]==0){
                 queue.add(i);
             } 
         }

         List<Integer> order  = new ArrayList<>();

         while(!queue.isEmpty()){
             int node = queue.poll();
             order.add(node);
             for(int neighbour : graph.get(node)){
                 if(--indegree[neighbour]==0){
                     queue.add(neighbour);
                 }
             }
         }

         return order.size()!= numCourses ? false : true;
      
     }
}
```

### **4️⃣ Cycle Detection & Topological Sorting (BFS in DAGs)**

12. **[207. Course Schedule](https://leetcode.com/problems/course-schedule/)**

- **Problem:** Determine if all courses can be taken given prerequisites.
- **Real-Life Example:** **Dependency resolution in package management**.

13. **[210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)**

- **Problem:** Find the order in which courses should be taken.
- **Real-Life Example:** **University curriculum planning**.

14. **[1462. Course Schedule IV](https://leetcode.com/problems/course-schedule-iv/)**

- **Problem:** Determine if a prerequisite relationship exists between two courses.
- **Real-Life Example:** **Building software module dependencies**.

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
