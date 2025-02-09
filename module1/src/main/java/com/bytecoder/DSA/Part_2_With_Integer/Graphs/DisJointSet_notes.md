
in the real world , many problems are representated in terms of objects and connections between them.
for example, airline routes, electric circuits , LAN and internet , facebook friends etc

**Graph**: a graph is a pair (V,E), where V is a set of nodes, called vertices (vertex) and E is collection of pairs of vertices


![img.png](AdjacencyMatrix/img.png)

![img.png](img.png)

type of graphs:
- Directed, undirected , weighted and unweighted graphs
- two connected nodes are called adjacent nodes.
- no cyclic in the graph is an acyclic graph



## Disjoint set: Union Find: (DSU)

Two sets are called disjoint sets if they don’t have any element in common, the intersection of sets is a null set.


    - Find - Finding representative (root) of a disjoint set using Find operation.
    - union : Merging disjoint sets to a single disjoint set using Union operation.

subsetMap[rootDest].parent : this will get / update parent of rootDest


# **🔹 Real-World Applications of Disjoint Set (Union-Find) in DSA**
The **Disjoint Set Union (DSU) / Union-Find** data structure is useful for solving a wide range of real-world problems that involve **connectivity, grouping, and component merging**. Below are several practical applications that can be solved efficiently using **DSU**.

---

## **1️⃣ Network Connectivity & Communication Systems**
### **🔸 Problem: Checking if Two Computers are in the Same Network**
- In a **network of computers (graph with nodes and edges)**, determine if two computers can communicate.
- Every **direct connection** is like a union operation.
- You can **check connectivity** using `find()`.

**💡 Example:**
- Consider `N` computers connected by `M` cables.
- We receive queries asking if `computer_A` and `computer_B` can communicate.
- **Solution:** Use **Union-Find** to merge connected computers and answer queries in **O(α(N)) ≈ O(1)**.

**📌 Similar Problems:**
- **Internet connectivity**: Are two devices on the same network?
- **Wireless network coverage**: Are two users connected via Wi-Fi routers?

---

## **2️⃣ Friend Circles / Social Network Groups**
### **🔸 Problem: Finding Number of Friend Groups**
- A **social network** consists of **people (nodes)** and **friendships (edges)**.
- Two people belong to the **same group** if they are **directly or indirectly friends**.

**💡 Example:**
- If **A is friends with B, and B is friends with C**, then **A, B, and C are in the same friend circle**.
- We can use **Union-Find** to dynamically group friends and find the number of social groups.

**📌 Real-world applications:**
- **Facebook mutual friend suggestions**.
- **LinkedIn connection groups** (e.g., "People You May Know").
- **Twitter retweet communities**.

📝 **DSU is used to efficiently process dynamic friendship relations and find groups in O(α(N)) time!**

---

## **3️⃣ Kruskal’s Algorithm for Minimum Spanning Tree (MST)**
### **🔸 Problem: Find the Minimum Cost to Connect All Cities**
- Given `N` cities and `M` roads with different costs, find the **cheapest way** to connect all cities.
- **Kruskal’s Algorithm** uses **Union-Find** to ensure edges do **not form cycles** while constructing the MST.

**📌 Applications:**
- **Designing road networks** with minimum cost.
- **Laying fiber optic cables** efficiently for the internet.
- **Power grid optimization** (connecting electricity distribution centers).

🔹 **Complexity:** `O(E log E)`, where `E` is the number of edges.

---

## **4️⃣ Detecting Cycles in Graphs**
### **🔸 Problem: Given an undirected graph, check if it contains a cycle.**
- If adding an edge **(u, v)** connects two nodes that are **already in the same component**, a **cycle is detected**.

**📌 Applications:**
- **Deadlock detection** in operating systems (checking if a resource dependency forms a cycle).
- **Package dependency resolution** (ensuring no cyclic dependencies in software installations).

🔹 **Union-Find makes cycle detection efficient in O(N log N) time.**

---

## **5️⃣ Dynamic Friend Suggestions in Social Media**
### **🔸 Problem: Suggest New Friends on Facebook / LinkedIn**
- If **A and B are friends**, and **B is friends with C**, then suggest **C to A** as a friend.

**📌 Applications:**
- **Facebook**: "People You May Know" feature.
- **LinkedIn**: Finding professional connections.
- **Dating Apps**: Matching users based on mutual interests.

🔹 **DSU helps track connected friend groups and suggest new connections dynamically!**

---

## **6️⃣ Image Processing & Connected Components**
### **🔸 Problem: Count Distinct Objects in an Image**
- An **image is a 2D grid** where pixels belong to **connected components** (objects).
- DSU can **merge adjacent pixels** of the same color to **identify objects**.

**📌 Applications:**
- **Object detection in computer vision** (grouping connected pixels).
- **Face recognition systems**.
- **Medical imaging** (detecting tumors in scans).

🔹 **DSU allows fast merging of pixel groups to detect objects in O(N²) time.**

---

## **7️⃣ Battleship Game or Island Counting in a Grid**
### **🔸 Problem: Count Number of Islands in a 2D Grid**
- Given a `N x M` grid where **1 = land** and **0 = water**, count how many **islands** exist.
- An **island** is a connected component of `1`s.

**📌 Applications:**
- **Google Earth island detection**.
- **Satellite image analysis** for landmass detection.
- **Flood fill algorithm** in game development.

🔹 **Union-Find is used to efficiently merge adjacent land cells into islands.**

---

## **8️⃣ Airline and Transportation Systems**
### **🔸 Problem: Are Two Cities Connected by Flights?**
- Given a list of **direct flights** between cities, determine if **city A can reach city B**.
- If a new **direct flight is added**, update the connections.

**📌 Applications:**
- **Flight route optimization** (ensuring all cities are reachable).
- **Railway network analysis** (are all train stations connected?).
- **Shipping routes** (optimizing sea transportation paths).

🔹 **DSU enables efficient queries and updates in O(α(N)) time!**

---

## **9️⃣ DNA Sequencing & Genetic Clustering**
### **🔸 Problem: Group Similar DNA Sequences**
- DNA sequences can be **similar if they differ by at most `k` mutations**.
- Use **Union-Find** to group similar DNA sequences into clusters.

**📌 Applications:**
- **Genetic similarity analysis**.
- **Medical research on hereditary diseases**.
- **Identifying species similarity in evolutionary biology**.

🔹 **DSU helps quickly group related DNA sequences in bioinformatics.**

---

## **🔟 Firewall & Network Security (Connected Components)**
### **🔸 Problem: Block Traffic Between Disjoint Networks**
- Firewalls enforce **rules** to **restrict network traffic** between disconnected regions.
- **Use Union-Find** to keep track of which networks are **connected** and **prevent traffic flow**.

**📌 Applications:**
- **Preventing cyber-attacks** by isolating infected machines.
- **Ensuring security in cloud computing** (AWS, Azure).
- **Enterprise network segmentation**.

🔹 **DSU helps efficiently track connected networks in real-time!**

---

# **📌 Summary Table: Real-World Applications of Disjoint Set**
| **Application**                | **Real-World Example**                                    | **DSU Operation**       |
|--------------------------------|---------------------------------------------------------|-------------------------|
| **Network Connectivity**       | Checking if two computers are in the same network       | `union(A, B)`, `find(A)` |
| **Social Network Groups**      | Finding friend groups on Facebook                      | `union(A, B)`, `find(A)` |
| **Minimum Spanning Tree (MST)**| Finding cheapest way to connect cities (Kruskal's Algo)| `union(A, B)`, sorting  |
| **Cycle Detection**            | Deadlock detection, package dependencies              | `find(A) == find(B)`   |
| **Friend Recommendations**     | Facebook, LinkedIn "People You May Know"               | `union(A, B)`         |
| **Image Processing**           | Counting objects in images                             | `union(A, B)`         |
| **Island Counting**            | Finding the number of islands in a map                 | `union(A, B)`         |
| **Flight & Transportation**    | Checking if two cities are connected                   | `find(A) == find(B)`   |
| **DNA Clustering**             | Grouping similar DNA sequences                         | `union(A, B)`         |
| **Network Security**           | Blocking access between disconnected networks         | `find(A) == find(B)`   |

---

# **🚀 Final Thoughts**
**Disjoint Set (Union-Find) is a powerful data structure** used in **graph problems, networks, social media, image processing, bioinformatics, and security systems**.  
It enables **fast merging, cycle detection, and connectivity checks** with nearly **O(1) complexity**.






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
# https://leetcode.com/problems/redundant-connection/

```java
class Solution {

        private int[] parent;

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // Path compression

        return find(parent[x]); // without Path compression

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


### Optimized Code with Path Compression & Union by Rank
```python
from typing import List

class Solution:
    def find(self, a, parent):
        if parent[a] != a:
            parent[a] = self.find(parent[a], parent)  # Path compression
        return parent[a]

    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        parent = {}
        rank = {}

        for i in range(len(edges) + 1):  # Include extra node for 1-based index
            parent[i] = i
            rank[i] = 1

        for a, b in edges:
            parent_a = self.find(a, parent)
            parent_b = self.find(b, parent)

            if parent_a == parent_b:
                return [a, b]
            else:
                # Union by Rank
                if rank[parent_a] > rank[parent_b]:
                    parent[parent_b] = parent_a
                elif rank[parent_a] < rank[parent_b]:
                    parent[parent_a] = parent_b
                else:
                    parent[parent_b] = parent_a
                    rank[parent_a] += 1
        
        return []

```



### **Understanding Path Compression in Union-Find**
Path compression is an optimization technique used in the **find** operation of the Union-Find (Disjoint Set Union, DSU) data structure. It helps to flatten the tree structure, reducing the time complexity of subsequent `find` operations to nearly constant time **O(α(N))**, where **α(N)** is the inverse Ackermann function (which is almost constant for practical values).

---

## **1️⃣ How Does the `find` Function Work?**
The function **recursively finds the root** of the set a node belongs to. However, the **key optimization** is that it **compresses the path** by making every node along the search path point directly to the root.

### **Annotated Code Explanation**
```python
def find(self, a, parent):
    if parent[a] != a:  # If 'a' is not its own parent (not a root)
        parent[a] = self.find(parent[a], parent)  # Path compression
    return parent[a]  # Return root
```

🔹 **Without Path Compression:** Every node just points to its immediate parent.  
🔹 **With Path Compression:** Every node points directly to the root, making future lookups much faster.

---

## **2️⃣ Example Walkthrough**
### **Initial State (No Compression)**
Consider a graph where elements are connected in a chain:
```
1 → 2 → 3 → 4 → 5
```
Initially, the parent structure (`parent` dictionary) looks like this:
```python
parent = {1: 2, 2: 3, 3: 4, 4: 5, 5: 5}  # 5 is the root
```
Now, let's call `find(1, parent)`:

#### **Step-by-Step Execution**
1. `find(1)` → Calls `find(2)`
2. `find(2)` → Calls `find(3)`
3. `find(3)` → Calls `find(4)`
4. `find(4)` → Calls `find(5)`, which returns **5** (root)
5. **Path Compression:** Updates all nodes to point directly to **5**.

### **After Path Compression**
The updated `parent` dictionary now looks like:
```python
parent = {1: 5, 2: 5, 3: 5, 4: 5, 5: 5}
```
Now, future `find(1)`, `find(2)`, `find(3)`, etc., **all take O(1) time** because they directly return **5** instead of traversing the entire chain.

---

## **3️⃣ Time Complexity Analysis**
Without path compression, `find` could take **O(N)** in the worst case (if the tree is a long chain).  
With path compression:
- The first `find` operation takes **O(log N)** time.
- Subsequent `find` operations take **almost O(1)** time.

Using **amortized analysis**, the total time complexity becomes **O(α(N))**, where **α(N) ≈ O(1) for practical inputs**.

---

## **4️⃣ Visualization**
### **Before Path Compression**
```
1 → 2 → 3 → 4 → 5
```
`find(1)` calls `find(2)`, `find(2)` calls `find(3)`, and so on... **O(N) complexity**.

---

### **After Path Compression**
```
1 → 5
2 → 5
3 → 5
4 → 5
5 → 5  (Root)
```
Now, `find(1)` directly returns **5** in **O(1) time**!

---

## **5️⃣ Summary**
| **Operation** | **Without Path Compression** | **With Path Compression** |
|--------------|---------------------------|---------------------------|
| `find(x)`    | **O(N)** in worst case (linked list) | **O(α(N)) ≈ O(1)** |
| `union(x, y)`| **O(1)**                    | **O(α(N)) ≈ O(1)** |
| **Overall Complexity** | **O(N²) for N queries** | **O(N α(N)) ≈ O(N)** |

### **Key Takeaways**
✅ Path compression makes future `find` operations much faster.  
✅ The amortized complexity of `find` reduces from **O(N) → O(1)**.  
✅ Union-Find with path compression is extremely efficient for large graphs.

---

### **6️⃣ When to Use Path Compression?**
Path compression is highly useful in:
- **Cycle detection in graphs** (like your problem)
- **Connected components in a network**
- **Kruskal's Minimum Spanning Tree Algorithm**
- **Dynamic connectivity problems**



### **Union-Find Applications Explained**
Union-Find (also known as Disjoint Set Union, DSU) with **path compression** and **union by rank** is widely used in graph-related problems. Let's go through its key applications:

---

## **1️⃣ Cycle Detection in Graphs (Like Your Problem)**
### **Problem Statement**
- Given an **undirected graph**, detect if there is a **cycle**.
- If an edge connects two nodes that **already belong to the same component**, a cycle is detected.

### **How Union-Find Helps**
- For each edge **(u, v)**:
    1. Find the **root** of `u` and `v` using `find(u)` and `find(v)`.
    2. If `root_u == root_v`, they are already connected → **Cycle detected**! 🚨
    3. Otherwise, merge them using `union(u, v)`.
- Using **path compression**, each `find` operation runs in **O(α(N)) ≈ O(1)**, making the approach efficient.

### **Example**
#### **Graph**:
```
1 - 2
|   |
4 - 3
```
#### **Edges Processed:**
1. **(1, 2)** → No cycle → Merge sets {1,2}.
2. **(2, 3)** → No cycle → Merge sets {1,2,3}.
3. **(3, 4)** → No cycle → Merge sets {1,2,3,4}.
4. **(4, 1)** → Cycle detected! ❌ (1 and 4 are already connected)

This method is used in problems like **Leetcode 684. Redundant Connection**.

---

## **2️⃣ Connected Components in a Network**
### **Problem Statement**
- Given a network of nodes and connections, find how many **independent groups (connected components)** exist.

### **How Union-Find Helps**
- Initially, each node is its own component.
- For each connection `(u, v)`, perform `union(u, v)`.
- After processing all edges, the number of **unique roots** in the parent array gives the **number of components**.

### **Example**
#### **Graph**:
```
1 - 2   3 - 4   5
```
#### **Edges Processed:**
1. **(1, 2)** → Merge sets {1,2}.
2. **(3, 4)** → Merge sets {3,4}.

After processing, **we have 3 components**: `{1,2}`, `{3,4}`, `{5}`.

### **Time Complexity**:
Each `union` and `find` takes **O(α(N))**, so the total complexity is **O(N)**.

📌 **Real-world application:** Finding **clusters in social networks** or **isolated subgraphs** in a computer network.

---

## **3️⃣ Kruskal’s Algorithm for Minimum Spanning Tree (MST)**
### **Problem Statement**
- Given a weighted **graph**, find a **Minimum Spanning Tree (MST)** that connects all nodes with the **minimum total edge weight**.

### **How Kruskal’s Algorithm Works**
1. **Sort all edges by weight**.
2. **Use Union-Find to check cycles**:
    - Add the **smallest edge** if it does **not** form a cycle (using `find` and `union`).
    - If an edge connects two nodes already in the same set, skip it.

### **Example**
#### **Graph (Weighted)**
```
  1
A----B
|  2  |
C----D
   3
```
#### **Edges Sorted by Weight**
1️⃣ (A, B) **(1 weight)** ✅ Add  
2️⃣ (A, C) **(2 weight)** ✅ Add  
3️⃣ (C, D) **(3 weight)** ✅ Add  
4️⃣ (B, D) **(2 weight)** ❌ Skipped (would form a cycle)

### **Resulting MST**
Edges selected: **(A, B), (A, C), (C, D)**
Total weight: **1 + 2 + 3 = 6**

### **Why Use Union-Find?**
- `find()` ensures **edges don’t form cycles**.
- `union()` merges components efficiently.
- **Time Complexity:** Sorting takes **O(E log E)**, and **Union-Find operations take O(E α(N)) ≈ O(E)**, making it optimal.

📌 **Real-world application:** **Network design** (e.g., laying out fiber-optic cables with the lowest cost).

---

## **4️⃣ Dynamic Connectivity Problems**
### **Problem Statement**
- You have a set of nodes and **dynamic queries** that either:
    1. **Connect two nodes** (union operation)
    2. **Check if two nodes are connected** (find operation)

### **Example**
Consider an **online social network** where users form friendships.
- Initially, everyone is separate.
- As users connect, we use **union**.
- If we want to check if two users are **in the same friend group**, we use **find**.

### **Real-world Use Cases**
✔ **Social networks (Facebook friends suggestion)**  
✔ **Computer networks (Checking if two computers are in the same network)**  
✔ **Airline systems (Are two cities reachable from each other?)**

---

### **Final Thoughts**
| **Problem**                       | **Union-Find Role**                              | **Time Complexity** |
|-----------------------------------|-----------------------------------------------|----------------|
| **Cycle Detection** (your problem) | Merges components, detects redundant edges | **O(N α(N))** |
| **Connected Components**          | Finds the number of independent groups     | **O(N α(N))** |
| **Kruskal’s MST**                 | Ensures no cycles while selecting edges    | **O(E log E)** |
| **Dynamic Connectivity**          | Efficient union and find queries          | **O(N α(N))** |

🚀 **Union-Find is powerful for graph problems** where merging and checking connections are required! 🚀



# **4️⃣ Dynamic Connectivity Problems (With Example)**

## **🔹 What is the Dynamic Connectivity Problem?**
- You are given **N nodes**.
- You receive **queries** of two types:
    1. **Union(A, B)** → Connect nodes **A** and **B**.
    2. **Find(A, B)** → Check if **A and B are connected**.

💡 **Key Challenge**: The connections are **dynamic** (new edges can be added anytime), so we need an efficient way to process queries quickly.

---

## **🔹 Example Scenario: Social Network Friendships**
### **Problem Statement**
Imagine you are designing a **social network** like Facebook. Users can:
- **Send friend requests** (connect two users).
- **Check if two users are in the same friend circle**.

💡 **Operations Needed:**
- `union(A, B)`: Make **A and B** friends.
- `find(A, B)`: Check if **A and B are indirectly connected**.

---

## **🔹 Example Execution**
### **Initial Users (5 Users, No Friendships)**
```
Users: {1, 2, 3, 4, 5}
Initial Parent Mapping:
1 → 1  
2 → 2  
3 → 3  
4 → 4  
5 → 5  
```

### **Processing Friend Requests (Union Operations)**

#### ✅ **Step 1: Friend Request (1, 2)**
```python
union(1, 2)
```
**Parent Mapping Update:**
```
1 → 2  
2 → 2  (Root)
```
📌 **1 and 2 are now in the same friend group.**

---

#### ✅ **Step 2: Friend Request (2, 3)**
```python
union(2, 3)
```
**Parent Mapping Update:**
```
1 → 2  
2 → 3  
3 → 3  (Root)
```
📌 **1, 2, and 3 are now all in the same friend group.**

---

#### ✅ **Step 3: Check if (1, 3) are friends (Find Operation)**
```python
find(1, 3)
```
**Path Traversal:**
```
1 → 2 → 3
```
📌 **Yes, 1 and 3 are in the same group.** ✅

---

#### ✅ **Step 4: Friend Request (4, 5)**
```python
union(4, 5)
```
**Parent Mapping Update:**
```
4 → 5  
5 → 5  (Root)
```
📌 **4 and 5 are now in the same group.**

---

#### ✅ **Step 5: Check if (3, 5) are friends**
```python
find(3, 5)
```
- **Path Traversal:**
    - **3’s Root** = `3`
    - **5’s Root** = `5`
      📌 **No, 3 and 5 are not connected.** ❌

---

## **🔹 Optimized Union-Find Implementation**
Here’s how you can implement **Dynamic Connectivity** using Union-Find with Path Compression:

```python
class UnionFind:
    def __init__(self, n):
        self.parent = {i: i for i in range(1, n+1)}

    def find(self, a):
        if self.parent[a] != a:
            self.parent[a] = self.find(self.parent[a])  # Path Compression
        return self.parent[a]

    def union(self, a, b):
        rootA = self.find(a)
        rootB = self.find(b)
        if rootA != rootB:
            self.parent[rootA] = rootB  # Merge groups

    def connected(self, a, b):
        return self.find(a) == self.find(b)  # Check if in same group
```

---

## **🔹 Time Complexity**
| **Operation**  | **Without Path Compression** | **With Path Compression** |
|---------------|---------------------------|---------------------------|
| `find(x)`     | **O(N) (worst case)**      | **O(α(N)) ≈ O(1)**        |
| `union(x, y)` | **O(N) (worst case)**      | **O(α(N)) ≈ O(1)**        |
| **Overall Complexity** | **O(N²) for N queries** | **O(N α(N)) ≈ O(N)** |

🔹 **α(N) is the inverse Ackermann function**, which is nearly constant (~5 for practical values of N).

---

## **🔹 Real-World Applications**
✅ **Social Networks** (e.g., Checking if two users are indirectly connected on Facebook).  
✅ **Computer Networks** (e.g., Checking if two computers are on the same LAN).  
✅ **Airline Systems** (e.g., Can you reach city B from city A using flights?).  
✅ **Game Servers** (e.g., Are two players in the same server cluster?).

---

## **🔹 Summary**
| **Action**               | **Function**            | **Time Complexity** |
|--------------------------|------------------------|---------------------|
| Add a connection (A, B)  | `union(A, B)`         | **O(α(N)) ≈ O(1)** |
| Check if A and B are connected | `find(A) == find(B)` | **O(α(N)) ≈ O(1)** |

🚀 **Union-Find makes dynamic connectivity problems very efficient and is used in many real-world systems!** 🚀