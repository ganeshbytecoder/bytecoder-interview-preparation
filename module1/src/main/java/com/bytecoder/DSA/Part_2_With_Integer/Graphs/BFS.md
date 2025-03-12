
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
// visited = set()   use hashset as well for tracking visited 
```





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
- **BFS is ideal for finding the shortest path in unweighted graphs**.

```java
import java.util.*;

class Graph<T> {
    private Map<T, List<T>> adjList;

    // Constructor
    public Graph() {
        this.adjList = new HashMap<>();
    }

    // Method to add an edge (undirected by default)
    public void addEdge(T src, T dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>()); // Ensure destination exists

        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // Remove this line for a directed graph
    }

    // Method to get adjacency list
    public Map<T, List<T>> getAdjList() {
        return adjList;
    }

    // Method to print the graph
    public void printGraph() {
        for (T node : adjList.keySet()) {
            System.out.println(node + " -> " + adjList.get(node));
        }
    }
}



/*                                   BFS Method
-------------------------------------------------------------------------------------*/
private static void bfs(Graph<String> graph, String source) {
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    queue.add(source);
    visited.add(source);

    int level = 0;

    while (!queue.isEmpty()) {
        int sz = queue.size(); // Level size

        for (int i = 0; i < sz; i++) {
            String curr = queue.poll();
            System.out.println(curr);

            for (String neighbor : graph.getAdjList().get(curr)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor); // Mark as visited
                }
            }
        }
        level++;
    }
}

/*-----------------------------------------------------------------------------------*/
```


BFS for shortest path:
```java

import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    public void addEdge(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // Remove for directed graph
    }

    public List<Integer> shortestPath(int source, int destination) {
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> distance = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        queue.add(source);
        distance.put(source, 0);
        parent.put(source, null);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : adjList.get(curr)) {
                if (!distance.containsKey(neighbor)) { // Not visited
                    queue.add(neighbor);
                    distance.put(neighbor, distance.get(curr) + 1);
                    parent.put(neighbor, curr);

                    if (neighbor == destination) { // Stop when we reach the target
                        return constructPath(parent, destination);
                    }
                }
            }
        }
        return new ArrayList<>(); // No path found
    }

    private List<Integer> constructPath(Map<Integer, Integer> parent, int destination) {
        List<Integer> path = new LinkedList<>();
        Integer at = destination;

        while (at != null) {  // Continue until reaching the source (null parent)
            path.add(0, at);  // Insert at the beginning
            at = parent.get(at); // Move to the parent node
        }

        return path;
    }


    public Map<Integer, List<Integer>> getAdjList() {
        return adjList;
    }
}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(5, 6);

        System.out.println("Shortest Path from 1 to 6: " + graph.shortestPath(1, 6));
//        Shortest Path from 1 to 6: [1, 3, 6]

    }
}



```


```java

public List<List<Integer>> findConnectedComponents() {
    Set<Integer> visited = new HashSet<>();
    List<List<Integer>> components = new ArrayList<>();

    for (int node : adjList.keySet()) {
        if (!visited.contains(node)) {
            List<Integer> component = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();

            queue.add(node);
            visited.add(node);

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                component.add(curr);

                for (int neighbor : adjList.get(curr)) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            components.add(component);
        }
    }
    return components;
}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);  // Separate component

        System.out.println("Connected Components: " + graph.findConnectedComponents());
//        Connected Components: [[1, 2, 3], [4, 5]]

    }
}


```

```java
// Practice Question : No of Islands , Rotting Oranges
   
/*                             Rotting Oranges (BFS)
-------------------------------------------------------------------------------------*/
public int orangesRotting(int[][] grid) {
Queue<int[]> queue = new LinkedList<>();
int fresh = 0;
int time = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 2)
                    queue.add(new int[]{r,c});
                if (grid[r][c] == 1)
                    fresh++;
            }
        }

        int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};

        while (!queue.isEmpty() && fresh > 0){
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int[] rotten = queue.poll();
                int row = rotten[0], col = rotten[1];

                for (int[] drc : direction){
                    int r = drc[0] + row, c = drc[1] + col;
                    boolean rowbound = 0 <= r && r < grid.length;
                    boolean colbound = 0 <= c && c < grid[0].length;

                    if (rowbound && colbound && grid[r][c] == 1){
                        grid[r][c] = 2;
                        queue.add(new int[]{r,c});
                        fresh--;
                    }
                }
            }
            time++;
        }
        if(fresh == 0)
            return time;

        return -1;    
    }
```

matrix:
* https://leetcode.com/problems/01-matrix/
* https://leetcode.com/problems/rotting-oranges/
* https://leetcode.com/problems/shortest-path-in-binary-matrix/

* https://leetcode.com/problems/as-far-from-land-as-possible/
* https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/

Graph coloring:
* https://leetcode.com/problems/possible-bipartition/
* https://leetcode.com/problems/is-graph-bipartite/


### **Important BFS-Based LeetCode Problems & Real-Life Applications** 🚀

---

## **📌 LeetCode Problems Based on BFS**
### **1️⃣ Shortest Path Problems**
1. **[1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)**
    - **Problem:** Find the shortest path from the top-left to the bottom-right in a binary grid (1 = obstacle, 0 = open).
    - **Real-Life Example:** Pathfinding in **robot navigation** or **maze-solving**.

2. **[127. Word Ladder](https://leetcode.com/problems/word-ladder/)**
    - **Problem:** Convert one word to another by changing one letter at a time, ensuring each intermediate word is valid.
    - **Real-Life Example:** DNA mutation paths in **biotechnology**.

3. **[542. 01 Matrix](https://leetcode.com/problems/01-matrix/)**
    - **Problem:** Find the shortest distance from each 0 to its nearest 1 in a matrix.
    - **Real-Life Example:** Finding the **nearest hospital** in a city grid.

4. **[752. Open the Lock](https://leetcode.com/problems/open-the-lock/)**
    - **Problem:** Find the minimum number of moves to unlock a lock with a rotating dial.
    - **Real-Life Example:** **Safe unlocking algorithms** used in security systems.

---

### **2️⃣ Graph Traversal & Connected Components**
5. **[200. Number of Islands](https://leetcode.com/problems/number-of-islands/)**
    - **Problem:** Count the number of islands (clusters of 1s) in a binary matrix.
    - **Real-Life Example:** **Flood-fill algorithms** in image processing.

6. **[433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation/)**
    - **Problem:** Find the minimum steps to mutate one DNA sequence into another.
    - **Real-Life Example:** **Virus mutation tracking** in epidemiology.

7. **[130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions/)**
    - **Problem:** Convert all 'O' regions that are not connected to the border into 'X'.
    - **Real-Life Example:** **Water retention in landscapes**.

8. **[994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)**
    - **Problem:** Determine how long it takes for all fresh oranges to rot.
    - **Real-Life Example:** **Disease spread modeling** (e.g., COVID-19 spread).

---

### **3️⃣ Multi-Source BFS Problems**
9. **[815. Bus Routes](https://leetcode.com/problems/bus-routes/)**
    - **Problem:** Find the minimum number of buses needed to reach a destination.
    - **Real-Life Example:** **Public transport optimization**.

10. **[1926. Nearest Exit from Entrance in Maze](https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/)**
- **Problem:** Find the nearest exit in a maze from a given starting position.
- **Real-Life Example:** **Emergency evacuation planning**.

11. **[1293. Shortest Path in a Grid with Obstacles Elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/)**
- **Problem:** Navigate a grid with obstacles, removing at most `k` obstacles.
- **Real-Life Example:** **Self-driving car navigation** in urban areas.

---


### 4. **Detect Cycle in Undirected Graph using BFS/DFS Algorithm**
- **DFS:** Track the parent of each node; if you find an edge that leads to a previously visited node that is not the parent, a cycle is detected.
- **BFS:** Use a queue and track parent nodes similarly to DFS.

```java
/*                                 Detect cycle in an undirected graph
---------------------------------------------------------------------------------------------------------------*/
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        HashSet<Integer> visited = new HashSet<>();

        for (int i = 0; i < V; i++) {
            if (!visited.contains(i)){
                if (dfs(adj, i, -1, visited))
                        return true;
            }
        }
        return false;
    }

    private boolean dfs(ArrayList<ArrayList<Integer>> adj, int src, int parent, HashSet<Integer> visited)
    {
        visited.add(src);

        for (int neighbour : adj.get(src)){
            if (!visited.contains(neighbour)) {
                if (dfs(adj, neighbour, src, visited))
                    return true;
            } else if (parent != neighbour) {
                return true;
            }
        }
        return false;
    }
/*-------------------------------------------------------------------------------------------------------------*/

```


### 5. **Detect Cycle in Directed Graph using BFS/DFS Algorithm**
- **DFS Approach:** Maintain a recursion stack to check for back edges (edges that point to an ancestor in DFS tree).
- **BFS Approach (Kahn’s Algorithm):** Use topological sorting and check for leftover nodes (a cycle exists if a topological sort is not possible).

```java
/*                                      Detect cycle in Directed graph
----------------------------------------------------------------------------------------------------------------*/
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] recstack = new boolean[V];

        for (int i = 0; i < V; i++){
            if (dfs(adj, i, visited, recstack))
                return true;
        }
        return false;
    }

    private boolean dfs(ArrayList<ArrayList<Integer>> adj, int src, boolean[] visited, boolean[] recstack) {
        if (recstack[src])
            return true;
        if (visited[src])
            return false;

        visited[src] = true;
        recstack[src] = true;

        for (int neighbour : adj.get(src)){
            if (dfs(adj, neighbour, visited, recstack))
                return true;
        }
        recstack[src] = false;
        return false;
    }
/*--------------------------------------------------------------------------------------------------------------*/
```

M2
```java

public boolean isCyclic(int V, Map<Integer, List<Integer>> adj) {
    int[] state = new int[V]; // 0 = unvisited, 1 = visiting, 2 = visited

    for (int i = 0; i < V; i++) {
        if (state[i] == 0 && checkCycle(adj, i, state)) {
            return true;  // Cycle detected
        }
    }
    return false;
}

private boolean checkCycle(Map<Integer, List<Integer>> adj, int src, int[] state) {
    if (state[src] == 1) return true;  // Cycle detected
    if (state[src] == 2) return false; // Already processed

    state[src] = 1; // Mark as visiting

    for (int neighbour : adj.getOrDefault(src, new ArrayList<>())) {
        if (checkCycle(adj, neighbour, state)) {
            return true;  // Cycle found
        }
    }

    state[src] = 2; // Mark as visited (fully processed)
    return false;
}

```


### all possible nodes at given level 

### 5. **All possible paths from scr to dst using BFS/DFS Algorithm**
https://leetcode.com/problems/all-paths-from-source-to-target/description/

### 5. **All possible paths from scr to dst using BFS/DFS Algorithm with at most k stops**
* https://leetcode.com/problems/cheapest-flights-within-k-stops/solutions/3102509/normal-bfs-in-cpp/

https://leetcode.com/problems/snakes-and-ladders/solutions/?envType=study-plan-v2&envId=top-interview-150




---

## **🌍 Real-Life Applications of BFS**
### **1️⃣ Social Network Analysis**
- BFS is used to **find the shortest connection** between two people in a social network (e.g., LinkedIn’s “degrees of connection”).
- **Example:** Finding the **minimum friend connections** between two users on Facebook.

### **2️⃣ Google Maps / GPS Navigation**
- BFS helps in **finding the shortest path** in road networks.
- **Example:** Google Maps **uses BFS for unweighted routes** (e.g., fewest turns or stops).

### **3️⃣ AI & Game Development**
- **Pathfinding algorithms (like A*) use BFS as a base approach**.
- **Example:** AI characters in games use BFS to find **the shortest route to a target**.

### **4️⃣ Web Crawlers (Search Engines)**
- **Web crawlers like Googlebot use BFS** to traverse web pages efficiently.
- **Example:** **Crawling the web** to find new and updated pages.

### **5️⃣ Network Packet Routing**
- BFS ensures that packets **take the shortest path** in computer networks.
- **Example:** **Data packet transmission** in the internet backbone.

### **6️⃣ Water Supply and Power Grid Analysis**
- BFS is used to **find the shortest path of power or water distribution**.
- **Example:** **Electricity distribution planning** in smart grids.

### **7️⃣ Fire Escape Planning**
- BFS helps determine **the quickest escape routes** in large buildings.
- **Example:** **Fire safety systems** in smart buildings.

---




