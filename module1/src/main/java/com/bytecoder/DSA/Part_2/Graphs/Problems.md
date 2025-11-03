
🟡 Medium Problems

4. **Redundant Connection** (LC 684) ⭐

   - Cycle detection in undirected
   - Time: O(V + E) | Space: O(V)
5. **Word Ladder** (LC 127) ⭐⭐⭐

   - State space BFS, transformation sequence
   - Time: O(M² × N) | Space: O(M × N)
   - **Real-world:** DNA mutation paths
6. **Minimum Genetic Mutation** (LC 433) ⭐⭐

   - Similar to Word Ladder
   - Time: O(M² × N) | Space: O(M × N)
   - **Real-world:** Virus mutation tracking in epidemiology
7. **Open the Lock** (LC 752) ⭐⭐

   - State space BFS
   - Time: O(10^4) | Space: O(10^4)
   - **Real-world:** Safe unlocking algorithms in security systems
8. **Snakes and Ladders** (LC 909) ⭐

   - BFS on game board
   - Time: O(n²) | Space: O(n²)
9. **Shortest Bridge** (LC 934) ⭐⭐

   - DFS + BFS combination
   - Time: O(m × n) | Space: O(m × n)
10. **Shortest Path to Get All Keys** (LC 864) ⭐⭐⭐

    - BFS with state = (position, keys)
    - Time: O(m × n × 2^k) | Space: O(m × n × 2^k)
11. **Shortest Path Visiting All Nodes** (LC 847) ⭐⭐⭐

    - BFS with bitmask
    - Time: O(2^n × n²) | Space: O(2^n × n)
12. **Find the Celebrity** (LC 277) ⭐

    - Two pointers approach (Premium)
    - Time: O(n) | Space: O(1)
    - **Key:** Celebrity knows no one, everyone knows celebrity
13. **Find the City With Smallest Number of Neighbors** (LC 1334) ⭐

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
39. **Longest Increasing Path in Matrix** (LC 329) ⭐⭐⭐

    - DFS + memoization
    - Time: O(m * n) | Space: O(m * n)
40. **Word Ladder** (LC 127) ⭐⭐

    - State space DFS (BFS better)
    - Time: O(M² * N) | Space: O(M * N)
41. **Critical Connections in Network** (LC 1192) ⭐⭐

    - Tarjan's algorithm (bridges)
    - Time: O(V + E) | Space: O(V)

---
