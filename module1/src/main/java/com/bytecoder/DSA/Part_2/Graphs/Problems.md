### 🟢 Easy Problems

1. **Find if Path Exists in Graph** (LC 1971)

   - Basic DFS traversal
   - Time: O(V + E) | Space: O(V)
2. **Find Center of Star Graph** (LC 1791)

   - Graph property check
   - Time: O(1) | Space: O(1)
3. **Find the Town Judge** (LC 997)

   - In-degree/out-degree analysis
   - Time: O(E) | Space: O(V)
4. **Flood Fill** (LC 733)

   - Matrix DFS pattern
   - Time: O(rows * cols) | Space: O(rows * cols)

---

### 🟡 Medium Problems

5. **Number of Islands** (LC 200) ⭐⭐⭐

   - Classic grid DFS
   - Time: O(m * n) | Space: O(m * n)
6. **Clone Graph** (LC 133) ⭐⭐

   - HashMap + DFS
   - Time: O(V + E) | Space: O(V)
7. **Course Schedule** (LC 207) ⭐⭐⭐

   - Cycle detection in directed graph
   - Time: O(V + E) | Space: O(V)
8. **Number of Provinces** (LC 547) ⭐⭐

   - Connected components
   - Time: O(n²) | Space: O(n)
9. **All Paths From Source to Target** (LC 797) ⭐

   - DFS with backtracking
   - Time: O(2^V * V) | Space: O(V)
10. **Max Area of Island** (LC 695) ⭐⭐

    - Grid DFS with area calculation
    - Time: O(m * n) | Space: O(m * n)
11. **Keys and Rooms** (LC 841)

    - DFS reachability
    - Time: O(V + E) | Space: O(V)
12. **Number of Closed Islands** (LC 1254) ⭐

    - Boundary DFS pattern
    - Time: O(m * n) | Space: O(m * n)
13. **Surrounded Regions** (LC 130) ⭐⭐

    - Boundary DFS + marking
    - Time: O(m * n) | Space: O(m * n)
14. **Number of Enclaves** (LC 1020)

    - Boundary DFS variant
    - Time: O(m * n) | Space: O(m * n)
15. **Graph Valid Tree** (LC 261) ⭐⭐

    - Cycle detection + connectivity
    - Time: O(V + E) | Space: O(V)
16. **Number of Connected Components** (LC 323) ⭐

    - Basic component counting
    - Time: O(V + E) | Space: O(V)
17. **Pacific Atlantic Water Flow** (LC 417) ⭐⭐

    - Dual DFS from boundaries
    - Time: O(m * n) | Space: O(m * n)
18. **Redundant Connection** (LC 684) ⭐

    - Cycle detection in undirected
    - Time: O(V + E) | Space: O(V)
19. **Find Eventual Safe States** (LC 802) ⭐

    - Reverse topological + DFS
    - Time: O(V + E) | Space: O(V)
20. **Time Needed to Inform All Employees** (LC 1376)

    - Tree DFS with time tracking
    - Time: O(n) | Space: O(n)
21. **Minimum Time to Collect Apples** (LC 1443)

    - Tree DFS with backtracking
    - Time: O(n) | Space: O(n)
22. **All Nodes Distance K in Binary Tree** (LC 863) ⭐

    - Convert tree to graph + DFS
    - Time: O(n) | Space: O(n)

---

### 🔴 Hard Problems

23. **Longest Increasing Path in Matrix** (LC 329) ⭐⭐⭐

    - DFS + memoization
    - Time: O(m * n) | Space: O(m * n)
24. **Word Ladder** (LC 127) ⭐⭐

    - State space DFS (BFS better)
    - Time: O(M² * N) | Space: O(M * N)
25. **Critical Connections in Network** (LC 1192) ⭐⭐

    - Tarjan's algorithm (bridges)
    - Time: O(V + E) | Space: O(V)




## FAANG BFS Problem List

### 🟢 Easy Problems

1. **Flood Fill** (LC 733)

   - Basic BFS on grid
   - Time: O(m × n) | Space: O(m × n)
2. **Binary Tree Level Order Traversal** (LC 102)

   - Classic level-order BFS
   - Time: O(n) | Space: O(n)
3. **Average of Levels in Binary Tree** (LC 637)

   - Level-wise calculation
   - Time: O(n) | Space: O(n)
4. **N-ary Tree Level Order Traversal** (LC 429)

   - Multi-child tree BFS
   - Time: O(n) | Space: O(n)
5. **Find if Path Exists in Graph** (LC 1971)

   - Basic BFS traversal
   - Time: O(V + E) | Space: O(V)

---

### 🟡 Medium Problems

6. **Number of Islands** (LC 200) ⭐⭐⭐

   - Grid BFS, connected components
   - Time: O(m × n) | Space: O(min(m, n))
   - **Real-world:** Flood-fill algorithms in image processing
7. **Rotting Oranges** (LC 994) ⭐⭐⭐

   - Multi-source BFS
   - Time: O(m × n) | Space: O(m × n)
   - **Real-world:** Disease spread modeling (COVID-19)
8. **01 Matrix** (LC 542) ⭐⭐

   - Multi-source BFS, distance calculation
   - Time: O(m × n) | Space: O(m × n)
   - **Real-world:** Nearest hospital in city grid
9. **Word Ladder** (LC 127) ⭐⭐⭐

   - State space BFS, transformation sequence
   - Time: O(M² × N) | Space: O(M × N)
   - **Real-world:** DNA mutation paths
10. **Minimum Genetic Mutation** (LC 433) ⭐⭐

    - Similar to Word Ladder
    - Time: O(M² × N) | Space: O(M × N)
    - **Real-world:** Virus mutation tracking in epidemiology
11. **Shortest Path in Binary Matrix** (LC 1091) ⭐⭐

    - Grid BFS with 8 directions
    - Time: O(m × n) | Space: O(m × n)
    - **Real-world:** Robot navigation, maze-solving
12. **As Far from Land as Possible** (LC 1162) ⭐

    - Multi-source BFS from land cells
    - Time: O(m × n) | Space: O(m × n)
13. **Open the Lock** (LC 752) ⭐⭐

    - State space BFS
    - Time: O(10^4) | Space: O(10^4)
    - **Real-world:** Safe unlocking algorithms in security systems
14. **Surrounded Regions** (LC 130) ⭐⭐

    - Boundary BFS
    - Time: O(m × n) | Space: O(m × n)
    - **Real-world:** Water retention in landscapes
15. **Clone Graph** (LC 133) ⭐⭐

    - BFS with HashMap
    - Time: O(V + E) | Space: O(V)
16. **Course Schedule** (LC 207) ⭐⭐⭐

    - Topological sort (Kahn's algorithm)
    - Time: O(V + E) | Space: O(V)
    - **Real-world:** Task scheduling, dependency resolution
17. **Course Schedule II** (LC 210) ⭐⭐

    - Topological sort with ordering
    - Time: O(V + E) | Space: O(V)
18. **Pacific Atlantic Water Flow** (LC 417) ⭐⭐

    - Dual BFS from boundaries
    - Time: O(m × n) | Space: O(m × n)
19. **Snakes and Ladders** (LC 909) ⭐

    - BFS on game board
    - Time: O(n²) | Space: O(n²)
20. **Shortest Bridge** (LC 934) ⭐⭐

    - DFS + BFS combination
    - Time: O(m × n) | Space: O(m × n)
21. **Walls and Gates** (LC 286) ⭐

    - Multi-source BFS (Premium)
    - Time: O(m × n) | Space: O(m × n)
22. **Nearest Exit from Entrance in Maze** (LC 1926) ⭐

    - BFS with exit detection
    - Time: O(m × n) | Space: O(m × n)
    - **Real-world:** Emergency evacuation planning
23. **Minimum Knight Moves** (LC 1197) ⭐⭐

    - BFS on infinite board
    - Time: O(|x| × |y|) | Space: O(|x| × |y|)
24. **Jump Game III** (LC 1306)

    - BFS on array
    - Time: O(n) | Space: O(n)
25. **Jump Game IV** (LC 1345) ⭐⭐

    - BFS with value grouping
    - Time: O(n) | Space: O(n)
26. **Is Graph Bipartite?** (LC 785) ⭐⭐

    - Graph coloring with BFS
    - Time: O(V + E) | Space: O(V)
27. **Possible Bipartition** (LC 886) ⭐⭐

    - Bipartite check variant
    - Time: O(V + E) | Space: O(V)
28. **All Nodes Distance K in Binary Tree** (LC 863) ⭐⭐

    - Convert tree to graph + BFS
    - Time: O(n) | Space: O(n)
29. **Shortest Path to Get All Keys** (LC 864) ⭐⭐⭐

    - BFS with state = (position, keys)
    - Time: O(m × n × 2^k) | Space: O(m × n × 2^k)
30. **Shortest Path Visiting All Nodes** (LC 847) ⭐⭐⭐

    - BFS with bitmask
    - Time: O(2^n × n²) | Space: O(2^n × n)
31. **Find the Celebrity** (LC 277) ⭐

    - Two pointers approach (Premium)
    - Time: O(n) | Space: O(1)
    - **Key:** Celebrity knows no one, everyone knows celebrity
32. **Find the City With Smallest Number of Neighbors** (LC 1334) ⭐

    - Floyd-Warshall or Dijkstra
    - Time: O(n³) or O(n² log n) | Space: O(n²)

---

### 🔴 Hard Problems

33. **Bus Routes** (LC 815) ⭐⭐⭐

    - Multi-level BFS
    - Time: O(N × R) | Space: O(N × R)
    - **Real-world:** Public transport optimization
34. **Shortest Path in Grid with Obstacles Elimination** (LC 1293) ⭐⭐⭐

    - BFS with state = (pos, obstacles_left)
    - Time: O(m × n × k) | Space: O(m × n × k)
    - **Real-world:** Self-driving car navigation in urban areas
35. **Word Ladder II** (LC 126) ⭐⭐⭐

    - BFS + backtracking for all shortest paths
    - Time: O(M² × N) | Space: O(M × N)
36. **Minimum Cost to Make at Least One Valid Path** (LC 1368) ⭐⭐

    - 0-1 BFS variant
    - Time: O(m × n) | Space: O(m × n)
37. **Trapping Rain Water II** (LC 407) ⭐⭐⭐

    - Priority queue + BFS
    - Time: O(m × n × log(m × n)) | Space: O(m × n)
38. **Sliding Puzzle** (LC 773) ⭐⭐

    - State space BFS
    - Time: O((m × n)!) | Space: O((m × n)!)

---
