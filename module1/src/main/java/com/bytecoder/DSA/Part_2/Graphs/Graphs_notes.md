
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
```java
public class Graph<T> {
    private final HashMap<T, List<T>> adjList;
    private final boolean bidirection;

    public HashMap<T, List<T>> getAdjList() {
        return adjList;
    }

    public Graph(boolean bidirection) {
        adjList = new HashMap<>();
        this.bidirection = bidirection;
    }

    public void addVertex(T v){
        adjList.put(v, new ArrayList<T>());
    }

    public void addEdge(T source, T destination){
        if(!adjList.containsKey(source))
            addVertex(source);
        if (!adjList.containsKey(destination))
            addVertex(destination);
        adjList.get(source).add(destination);
        if (bidirection)
            adjList.get(destination).add(source);
    }
```

### 2. **Implement BFS Algorithm**
   - Use a queue for BFS traversal.
   - Track visited nodes to avoid cycles and repetition.
   - BFS is ideal for finding the shortest path in unweighted graphs.

### 3. **Implement DFS Algorithm**
   - Use a stack (or recursion) for DFS traversal.
   - Track visited nodes to avoid infinite loops.
   - DFS is useful for exploring deeper paths first.

```java
import java.util.HashMap;

/*                             DFS Iterative Method
-------------------------------------------------------------------------------------*/
private static void dfs(Graph<String> graph, String source) {
    Stack<String> stack = new Stack<>();
    stack.push(source);
    Map<String, Boolean> visited = new HashMap<>();

    while (!stack.empty()) {
        String curr = stack.pop();
        System.out.println(curr);

        for (String neighbour : graph.getAdjList().get(curr)) {
            if (visited.get(neighbour) == null){
                stack.push(neighbour);
                visited.put(neighbour, false);
            }
        }
    }
}
/*-----------------------------------------------------------------------------------*/

/*                           DFS Recursive Method
-------------------------------------------------------------------------------------*/
private static void dfs(Graph<String> graph, String source) {
    System.out.println(source);

    for (String neighbour : graph.getAdjList().get(source)) {
        if (visited.get(neighbour) == null) {
            visited.put(neighbour, false);
            dfs(graph, neighbour);
        }
    }
}
/*-----------------------------------------------------------------------------------*/
```

Start DFS from nodes at boundary:
* https://leetcode.com/problems/surrounded-regions/
* https://leetcode.com/problems/number-of-enclaves/
* https://leetcode.com/problems/time-needed-to-inform-all-employees/
* https://leetcode.com/problems/find-eventual-safe-states/description/

DFS from each unvisited node/Island problems
* https://leetcode.com/problems/number-of-closed-islands/
* https://leetcode.com/problems/number-of-islands/
* https://leetcode.com/problems/keys-and-rooms/
* https://leetcode.com/problems/max-area-of-island/
* https://leetcode.com/problems/flood-fill/

BFS:

BFS for shortest path:
* https://leetcode.com/problems/01-matrix/
* https://leetcode.com/problems/as-far-from-land-as-possible/
* https://leetcode.com/problems/rotting-oranges/
* https://leetcode.com/problems/shortest-path-in-binary-matrix/

Graph coloring:
* https://leetcode.com/problems/possible-bipartition/
* https://leetcode.com/problems/is-graph-bipartite/




### 4. **Detect Cycle in Undirected Graph using BFS/DFS Algorithm**
   - **DFS:** Track the parent of each node; if you find an edge that leads to a previously visited node that is not the parent, a cycle is detected.
   - **BFS:** Use a queue and track parent nodes similarly to DFS.


### 5. **Detect Cycle in Directed Graph using BFS/DFS Algorithm**
   - **DFS Approach:** Maintain a recursion stack to check for back edges (edges that point to an ancestor in DFS tree).
   - **BFS Approach (Kahn’s Algorithm):** Use topological sorting and check for leftover nodes (a cycle exists if a topological sort is not possible).

### 5. **All possible paths from scr to dst using BFS/DFS Algorithm**

### 5. **All possible paths from scr to dst using BFS/DFS Algorithm with at most k stops** 
* https://leetcode.com/problems/cheapest-flights-within-k-stops/solutions/3102509/normal-bfs-in-cpp/
 


## Disjoint set: Union Find:

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


* https://leetcode.com/problems/friend-circles/
* https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
* https://leetcode.com/problems/number-of-operations-to-make-network-connected/
* https://leetcode.com/problems/satisfiability-of-equality-equations/
* https://leetcode.com/problems/accounts-merge/

### https://leetcode.com/problems/redundant-connection/

```java
class Solution {

        private int[] parent;

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // Path compression
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];

        // Initialize the parent array
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int[] res = new int[2];
        for (int[] edge : edges) {
            int x = find(edge[0]);
            int y = find(edge[1]);
            if (x != y) {
                parent[y] = x; // Union operation
            } else {
                res[0] = edge[0];
                res[1] = edge[1];
            }
        }

        return res;
    }
   
}
```

