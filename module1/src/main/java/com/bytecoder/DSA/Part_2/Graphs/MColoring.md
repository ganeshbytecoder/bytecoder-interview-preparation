
The **M-Coloring Problem** is a well-known **graph coloring** problem where you need to determine whether you can color a graph using at most **M** colors such that no two adjacent nodes have the same color. It is typically solved using **backtracking**.

### **LeetCode Problems Related to M-Coloring**
There is no direct problem titled "M-Coloring" on LeetCode, but several problems require similar backtracking and graph coloring concepts. Here are the most relevant ones:

---

### **3. 785. Is Graph Bipartite?** (2-Coloring)
🔹 **Type:** Graph, DFS/BFS  
🔹 **Concept:** A bipartite graph is a special case of **M-Coloring** where M = 2.  
🔹 **Approach:**
- Try to color the graph using two colors (0 and 1).
- If any adjacent nodes have the same color, return **false**.
- Use **BFS or DFS** for coloring.

🔹 **LeetCode Link:** [Is Graph Bipartite?](https://leetcode.com/problems/is-graph-bipartite/)

---

### **4. 886. Possible Bipartition** (2-Coloring with Constraints)
🔹 **Type:** Graph, DFS/BFS  
🔹 **Concept:** This is a **2-coloring problem** where you check if it's possible to divide people into two groups based on dislikes.  
🔹 **Approach:**
- Treat people as nodes and dislikes as edges.
- Apply **BFS or DFS** to check if the graph is **bipartite** (2-colorable).

🔹 **LeetCode Link:** [Possible Bipartition](https://leetcode.com/problems/possible-bipartition/)

```python 

from typing import List

class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        n = len(graph)
        colors = [-1] * n  # -1 means uncolored
        visited = [-1] * n  # -1 means unvisited

        for node in range(n):  # Handle disconnected components
            if visited[node] == -1:  # Start BFS if node is unvisited
                queue = [node]  # Use deque for efficient pop
                colors[node] = 0  # Start coloring
                visited[node] = 1

                while queue:
                    current = queue.pop(0)  # O(1) operation

                    for neighbor in graph[current]:
                        if visited[neighbor] == -1:  # If unvisited, color and mark
                            if colors[current] == 0:
                                colors[neighbor] = 1
                            else:
                                colors[neighbor] = 0
                            queue.append(neighbor)
                            visited[neighbor] = 1
                        elif colors[neighbor] == colors[current]:  # Conflict found
                            return False
                        
        return True  # After processing all components, return True


```

---

### **5. 847. Shortest Path Visiting All Nodes** (Graph Coloring + Bitmasking)
🔹 **Type:** Graph, Bitmask DP  
🔹 **Concept:** This is related to graph coloring in the sense that each node needs to be visited in a specific order.  
🔹 **Approach:**
- Use **BFS + Bitmasking** to find the shortest path that visits all nodes.
- A **bitmask** represents visited nodes (like coloring states).

🔹 **LeetCode Link:** [Shortest Path Visiting All Nodes](https://leetcode.com/problems/shortest-path-visiting-all-nodes/)

---

### **6. 1349. Maximum Students Taking Exam** (Graph Coloring + Backtracking)
🔹 **Type:** DP, Bitmasking, Backtracking  
🔹 **Concept:** Similar to **M-Coloring**, where valid students need to be placed in a classroom based on given constraints.  
🔹 **Approach:**
- Use **backtracking** to try different seat assignments.
- Use **bitmasking** to optimize state representation.

🔹 **LeetCode Link:** [Maximum Students Taking Exam](https://leetcode.com/problems/maximum-students-taking-exam/)

---

### **7. 1593. Split a String Into the Max Number of Unique Substrings** (Backtracking Similar to M-Coloring)
🔹 **Type:** Backtracking  
🔹 **Concept:** Similar to **M-Coloring**, where you make decisions at each step and backtrack when necessary.  
🔹 **Approach:**
- Use **backtracking** to try different partitions of the string.

🔹 **LeetCode Link:** [Split a String Into the Max Number of Unique Substrings](https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/)

---

### **How to Approach M-Coloring Problems on LeetCode?**
1. **Graph Representation:** Use adjacency lists.
2. **Backtracking:** Try to color each node and backtrack when constraints are violated.
3. **DFS/BFS:** To check for cycles or bipartiteness.
4. **Bitmasking:** For optimization in state-based problems.

Would you like a detailed solution for one of these problems? 🚀