
## Shorted Path algorithms

### 22. **Shorted Path algorithms for unweighted or undirected graph**

### 12. **Dijkstra’s Algorithm**
   - Use a priority queue (min-heap) to keep track of the shortest path to each node.
   - Greedily expand the shortest known path to reach all nodes in a weighted graph.


### 21. **Bellman-Ford Algorithm**
   - Use dynamic programming to relax edges repeatedly. Detect negative weight cycles by checking for changes after `V-1` iterations.


### 23. **Travelling Salesman Problem**
   - Use dynamic programming or backtracking to find the minimum cost of visiting all cities once and returning to the start.

### 24. **Graph Coloring Problem**
   - Use backtracking to try and assign colors such that no two adjacent nodes share the same color.

### 25. **Snake and Ladders Problem**
   - Model the board as a graph with cells as nodes, use BFS to find the minimum dice throws to reach the final cell.
