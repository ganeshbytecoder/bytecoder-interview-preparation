### Pattern 2: Connected Components

**Use Cases:** Count islands, find provinces, network connectivity

**💡 Key Insight:** Run DFS from each unvisited node. Each DFS call finds one component.

**Time:** O(V + E) | **Space:** O(V)

```python
def count_components(n, edges):
    """Count number of connected components in undirected graph"""
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
  
    visited = set()
    count = 0
  
    def dfs(node):
        if node in visited:
            return False
        visited.add(node)
        for neighbor in graph[node]:
            dfs(neighbor)
        return True
  
    for node in range(n):
        if dfs(node):
            count += 1
  
    return count
```

**FAANG Problems:**

- Number of Provinces (LC 547)
- Number of Connected Components (LC 323)
- Keys and Rooms (LC 841)

### Pattern 4: Connected Components Using BFS

**Use Cases:** Count islands, find provinces, network connectivity

**💡 Key Insight:** Run BFS from each unvisited node. Each BFS finds one component.

**Time:** O(V + E) | **Space:** O(V)

```java
public List<List<Integer>> findConnectedComponents(Map<Integer, List<Integer>> adjList) {
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

// Usage Example
public class Main {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(1, 3));
        graph.put(3, Arrays.asList(2));
        graph.put(4, Arrays.asList(5));
        graph.put(5, Arrays.asList(4));

        System.out.println("Connected Components: " + findConnectedComponents(graph));
        // Output: Connected Components: [[1, 2, 3], [4, 5]]
    }
}
```

---
