
in the real world , many problems are representated in terms of objects and connections between them.
for example, airline routes, electric circuits , LAN and internet , facebook friends etc

**Graph**: a graph is a pair (V,E), where V is a set of nodes, called vertices (vertex) and E is collection of pairs of vertices


![img.png](AdjacencyMatrix/img.png)

![img.png](img.png)

type of graphs:
- Directed, undirected , weighted and unweighted graphs
- two connected nodes are called adjacent nodes.
- no cyclic in the graph is an acyclic graph

### Representation of graph :

1. Adjacency Matrix (matrix n*n)
2. Adjacency List (using hashmap or node with hashmap(weighted))

To Represent graphs, we need the number of vertices, the number edges (u,v) with their connections for example:
Input: n = 3, edges = [[1,3],[2,3],[3,1]]

```java
// adjacencyList
    Map<Integer,Set<Integer>> adjacencyList = new HashMap<>();
//weighted adjacency
    Map<Node,Map<Node,Integer>> adjacencyList = new HashMap<>();

// adjacencyMatrix
    int[][] matrix = new int[n][n];
    
    for(int[] edge : edges){
        matrix[edge[0]][edge[1]]=1 or adjacencyList.get(edge[0]).add(edge[1]);
        
        }
    
    
```


To keep track of no of connections or isVisited you can have array or map for example
```java
    int[] visited = new int[n];
//    visited[i] = true; means i node id visited
//    counter[i]++; means i nodes total connections with other nodes
```


### Representation of graph in Databases:



### 1. **Create a Graph, Print it**
   - Use adjacency matrix or adjacency list to store the graph.
   - Print the graph by iterating over the matrix/list and displaying connections.

### 2. **Implement BFS Algorithm**
   - Use a queue for BFS traversal.
   - Track visited nodes to avoid cycles and repetition.
   - BFS is ideal for finding the shortest path in unweighted graphs.

### 3. **Implement DFS Algorithm**
   - Use a stack (or recursion) for DFS traversal.
   - Track visited nodes to avoid infinite loops.
   - DFS is useful for exploring deeper paths first.

### 4. **Detect Cycle in Undirected Graph using BFS/DFS Algorithm**
   - **DFS:** Track the parent of each node; if you find an edge that leads to a previously visited node that is not the parent, a cycle is detected.
   - **BFS:** Use a queue and track parent nodes similarly to DFS.


### 5. **Detect Cycle in Directed Graph using BFS/DFS Algorithm**
   - **DFS Approach:** Maintain a recursion stack to check for back edges (edges that point to an ancestor in DFS tree).
   - **BFS Approach (Kahn’s Algorithm):** Use topological sorting and check for leftover nodes (a cycle exists if a topological sort is not possible).

### 5. **All possible paths from scr to dst using BFS/DFS Algorithm**

### 5. **All possible paths from scr to dst using BFS/DFS Algorithm with at most k stops** 
    **important** - https://leetcode.com/problems/cheapest-flights-within-k-stops/solutions/3102509/normal-bfs-in-cpp/
 


## Disjoint set:
Two sets are called disjoint sets if they don’t have any element in common, the intersection of sets is a null set.


    - Find - Finding representative (root) of a disjoint set using Find operation.
    - union : Merging disjoint sets to a single disjoint set using Union operation.

subsetMap[rootDest].parent : this will get / update parent of rootDest 

```java
import java.util.HashMap;

class Subset {
    Node parent;
    int rank;
}


Node find(Map<Node, Subset> subsets, Node node){
    if(subsets.get(node) != node){
      subsets[node].parent = find(subsets, subsets[node].parent);  
    }
    return subsets[node].parent;
}

public void union(Map<Node, Subset> subsetMap, Node src, Node dest){
    
    Node rootSrc = find(subsetMap, src);
    
    Node rootDest = find(subsetMap, dest);
    
    if(subsetMap.get(rootSrc).rank < subsetMap.get(rootDest)){
//    this will update parent of rootSrc with rootDest
        subsetMap[rootSrc].parent = rootDest;
    } else if (subsetMap.get(rootSrc).rank > subsetMap.get(rootDest)) {
//        this will update parent of rootDest with rootSrc
        subsetMap[rootDest].parent = rootSrc;
    }else {
//        this will update parent of rootDest with rootSrc and increase rootSrc rank
        subsetMap[rootDest].parent = rootSrc;
        subsetMap[rootSrc].rank++;
    }
}


public static void main(String[] args) {
    Map<Node, Subset> subsets = new HashMap<>();

    for (Node node : vertices) {
        subsets[node] = new Subset(node, 0);
    }
    
    
//    we can use array is all nodes are numbers
    
//    parent[i] -> mean parent of i is the value node
    int[] parents = new int[n];
    
//    rank[i] - rank of i 
    int[] rank = new int[n];

    for (int i : vertices) {
        parents[i] = i;
        rank[i] = 0;
    }
}
```

    **Two verices are in same graph component**

----

### 6. **Search in a Maze**
   - Model the maze as a graph where each cell is a node.
   - Use BFS for the shortest path, or DFS to explore all possible paths.
   - Walls and obstacles can be represented as non-existing edges.

### 7. **Minimum Step by Knight**
   - Treat the chessboard as a graph with each square as a node.
   - Use BFS starting from the knight’s position to calculate the minimum number of moves to the destination.

### 8. **Flood Fill Algorithm**
   - Similar to DFS/BFS in a 2D grid. Traverse each connected component starting from the given node.
   - Used in scenarios like the "paint fill" feature in image editing.

### 9. **Clone a Graph**
   - Use BFS or DFS to traverse the graph and create a deep copy.
   - Keep a hashmap of original nodes to their clones to avoid duplicate copies.

### 10. **Making Wired Connections**
   - Use DFS/BFS to determine the minimum number of extra connections required to make the entire graph connected.

### 11. **Word Ladder**
   - Treat each word as a node and edges between words that differ by one character.
   - Use BFS to find the shortest transformation sequence from the start word to the end word.


-----

# **Implement Topological Sort** 
```
Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u-v, vertex u comes before v in the ordering.

Note: Topological Sorting for a graph is not possible if the graph is not a DAG.
- it is used in scenarios where certain tasks must be done before others. such as scheduling tasks, resolving coureses prequites structures. 

```
![img_1.png](img_1.png)
#### implementations: 
### **DFS:**
   - perform a dfs traversal of the graph
   - for every vertex, after visiting all its adjacent vertices, push it onto stack
   - once all the vertices are visited, pop vertices from the stack to get the topological sort order
   -  if(stack.size != no. of nodes) :  graph contains a cycle or topological sort is not possible

### **Kahn’s Algorithm:**
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

-----


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

### 26. **Find Bridge in a Graph**
   - Use DFS and track discovery and low values of each node to detect bridges (edges whose removal increases the number of connected components).


### 28. **Check if a Graph is Bipartite**
   - Use BFS or DFS to try and color the graph using two colors. If adjacent nodes end up with the same color, the graph is not bipartite.

### 29. **Detect Negative Cycle in a Graph**
   - Use Bellman-Ford algorithm to check for changes after `V-1` relaxations to detect negative cycles.

### 30. **Longest Path in a DAG**
   - Topologically sort the graph and then relax the edges to find the longest path.

### 31. **Journey to the Moon**
   - Treat each country as a connected component and count the ways to choose astronaut pairs from different countries using combination counting.

### 32. **Cheapest Flights Within K Stops**
   - Use a variant of Dijkstra’s algorithm (or BFS) with an additional constraint on the number of stops allowed.

### 33. **Oliver and the Game**
   - DFS-based problem that involves traversing a tree and managing game moves based on tree properties.

### 34. **Water Jug Problem using BFS**
   - Treat each state (jug water levels) as a node and use BFS to find the shortest sequence of operations to reach the goal.

### 35. **Find if Path of More than K Length Exists**
   - Use DFS to explore paths and prune those that exceed the required length early.

### 36. **M-Coloring Problem**
   - Use backtracking to assign colors to nodes such that adjacent nodes don’t share the same color.

### 37. **Minimum Edges to Reverse to Make Path from Source to Destination**
   - Use BFS/DFS to explore and count the number of edges that need to be reversed to form the path.

### 38. **Paths to Travel Each Node Using Each Edge (Seven Bridges)**
   - This problem involves Eulerian paths and circuits. Use DFS to determine the number of Eulerian paths or circuits.

### 39. **Vertex Cover Problem**
   - Use backtracking or approximation algorithms (greedy method) to find the minimum vertex cover.

### 40. **Chinese Postman or Route Inspection**
   - Find an Eulerian circuit (or augment the graph to make it Eulerian) to minimize the traversal cost.

### 41. **Number of Triangles in a Graph**
   - Count triangles using adjacency matrix or list, leveraging combinatorial methods.

### 42. **Minimize Cash Flow among Friends**
   - Use a graph where vertices represent people and edges represent the net balance between pairs, then minimize cash flow using a greedy approach.



https://leetcode.com/problems/cheapest-flights-within-k-stops/solutions/3102509/normal-bfs-in-cpp/
https://leetcode.com/problems/find-the-town-judge/description/?envType=problem-list-v2&envId=graph&difficulty=EASY
https://leetcode.com/problems/find-center-of-star-graph/description/?envType=problem-list-v2&envId=graph&difficulty=EASY
https://leetcode.com/problems/find-if-path-exists-in-graph/submissions/1427991963/?envType=problem-list-v2&envId=graph&difficulty=EASY
https://leetcode.com/problems/course-schedule-ii/submissions/1431168360/
https://leetcode.com/problems/redundant-connection/submissions/1433882713/?envType=problem-list-v2&envId=graph
https://leetcode.com/problems/all-paths-from-source-to-target/description/