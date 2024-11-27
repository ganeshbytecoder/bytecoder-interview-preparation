
## **Spanning tree**

**Spanning tree** : A spanning tree is a subset of Graph G, such that all the vertices are connected using minimum possible number of edges. Hence, a spanning tree does not have cycles and a graph may have more than one spanning tree.

![img_2.png](img_2.png)

**Properties of a Spanning Tree:**

* A Spanning tree does not exist for a disconnected graph.
* For a connected graph having N vertices then the number of edges in the spanning tree for that graph will be N-1.
* A Spanning tree does not have any cycle.
* We can construct a spanning tree for a complete graph by removing E-N+1 edges, where E is the number of Edges and N is the number of vertices.
* Cayley’s Formula: It states that the number of spanning trees in a complete graph with N vertices isN^{N-2}       


**Minimum spanning tree** the sum of the edge weights in spanning tree is min

**Maximum spanning tree** the sum of the edge weights in spanning tree is max

### 18. **Prim’s Algorithm** 
    A greedy algorithm that starts with single vertex and grows the MST by adding the smallest edge that connects a vertex in the MST which is outside of MST.
   - Use a priority queue to greedily pick the minimum weight edge expanding the MST, ensuring no cycles are formed

### 19. **Kruskal’s Algorithm**
   - Sort edges by weight and use the union-find data structure to form the minimum spanning tree by avoiding cycles.

### 20. **Total Number of Spanning Trees**
   - Cayley’s formula for complete graphs, or Kirchhoff's Matrix-Tree Theorem for general graphs.


----
