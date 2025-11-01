

### 11. Parent Pointer / Distance Pattern

**Use Cases:** Find nodes at distance K, operations with parent access

**💡 Key Insight:** Build parent map with DFS. Then use BFS treating tree as undirected graph to find nodes at distance K.

#### Template:

```python
def distanceK(root, target, k):
    # Build parent map
    parent = {}
    def build_parent(node, par=None):
        if not node: return
        parent[node] = par
        build_parent(node.left, node)
        build_parent(node.right, node)
  
    build_parent(root)
  
    # BFS from target
    from collections import deque
    q = deque([(target, 0)])
    seen = {target}
    result = []
  
    while q:
        node, dist = q.popleft()
        if dist == k:
            result.append(node.val)
        elif dist < k:
            for neighbor in [node.left, node.right, parent[node]]:
                if neighbor and neighbor not in seen:
                    seen.add(neighbor)
                    q.append((neighbor, dist + 1))
  
    return result
```

**Common Problems:**

- All Nodes Distance K (863)
- LCA with Parent Pointers (1650)



https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/
