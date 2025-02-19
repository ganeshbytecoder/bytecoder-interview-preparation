
The **M-Coloring Problem** is a well-known **graph coloring** problem where you need to determine whether you can color a graph using at most **M** colors such that no two adjacent nodes have the same color. It is typically solved using **backtracking**.

### **LeetCode Problems Related to M-Coloring**
There is no direct problem titled "M-Coloring" on LeetCode, but several problems require similar backtracking and graph coloring concepts. Here are the most relevant ones:

---

### **1. 207. Course Schedule** (Graph Coloring - Cycle Detection)
🔹 **Type:** Graph, Topological Sort  
🔹 **Concept:** The problem is about checking if a course schedule can be completed given prerequisites. You can solve it using **Graph Coloring (Cycle Detection using DFS)**, where:
- **White (0):** Not visited
- **Gray (1):** In the current recursion stack
- **Black (2):** Processed

🔹 **LeetCode Link:** [Course Schedule](https://leetcode.com/problems/course-schedule/)  
🔹 **Approach:**
- Model the problem as a **directed graph**.
- Use **DFS with coloring** to detect cycles.
- If a cycle exists, return false (not possible to finish courses).

---

### **2. 210. Course Schedule II** (Graph Coloring + Topological Sorting)
🔹 **Type:** Graph, Topological Sort  
🔹 **Concept:** Similar to **Course Schedule (207)**, but you need to return a valid order of courses.  
🔹 **Approach:**
- **DFS (Graph Coloring)** or **Kahn's Algorithm (BFS Topological Sort)**
- If there’s a cycle, return an empty list.

🔹 **LeetCode Link:** [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)

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