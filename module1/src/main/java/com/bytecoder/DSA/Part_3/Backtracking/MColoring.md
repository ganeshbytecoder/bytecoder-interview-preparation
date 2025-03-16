
The **M-Coloring Problem** is a well-known **graph coloring** problem where you need to determine whether you can color a graph using at most **M** colors such that no two adjacent nodes have the same color. It is typically solved using **backtracking**.

### **LeetCode Problems Related to M-Coloring**
There is no direct problem titled "M-Coloring" on LeetCode, but several problems require similar backtracking and graph coloring concepts. Here are the most relevant ones:


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

