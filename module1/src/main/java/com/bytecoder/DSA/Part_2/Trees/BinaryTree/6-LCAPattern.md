# Lowest Common Ancestor (LCA) Pattern

**Use Cases:** Finding common ancestor, distance between nodes, path queries, subtree relationships, deepest nodes problems

**💡 Key Insight:**

* **BST:** Use value ordering to navigate (O(h) time, O(1) space)
* **Binary Tree:** Use postorder DFS - return node if found, LCA is where both subtrees return non-null
* **With Parent Pointers:** Convert to linked list intersection problem
* **Deepest Nodes:** Combine LCA with height tracking

**Common LeetCode Problems:**

* **[Count Nodes Equal to Average of Subtree](https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/)**
* **[Smallest Subtree with All the Deepest Nodes](https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/)**
* **[Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)**
* **[Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)**
* **[Lowest Common Ancestor of a Binary Tree II](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/)**
* **[Lowest Common Ancestor of a Binary Tree III](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/)**
* **[Lowest Common Ancestor of a Binary Tree IV](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/)**
* **[Lowest Common Ancestor of Deepest Leaves](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/)**
* **[Find Distance Between Two Nodes in a Binary Tree](https://leetcode.com/problems/minimum-distance-between-bst-nodes/)**

---

## **1. LCA of Binary Search Tree (LC 235)**

Find LCA in BST using value ordering property.

### Approach 1: Iterative (Optimal)

```python
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def lowest_common_ancestor_bst(root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
    """
    LCA in BST using value ordering.
    Time: O(h), Space: O(1)
  
    Key insight: LCA is the split point where p and q diverge
    """
    while root:
        # Both nodes in left subtree
        if root.val > p.val and root.val > q.val:
            root = root.left
        # Both nodes in right subtree
        elif root.val < p.val and root.val < q.val:
            root = root.right
        else:
            # Split point found (or one is ancestor of other)
            return root
  
    return None
```

### Approach 2: Recursive

```python
def lowest_common_ancestor_bst_recursive(root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
    """
    Recursive BST LCA.
    Time: O(h), Space: O(h)
    """
    if root.val > p.val and root.val > q.val:
        return lowest_common_ancestor_bst_recursive(root.left, p, q)
  
    if root.val < p.val and root.val < q.val:
        return lowest_common_ancestor_bst_recursive(root.right, p, q)
  
    return root
```

**Example:**

```python
#       6
#      / \
#     2   8
#    / \ / \
#   0  4 7  9
#     / \
#    3   5

# LCA(2, 8) = 6  (split at root)
# LCA(2, 4) = 2  (2 is ancestor of 4)
# LCA(3, 5) = 4  (split at 4)
```

---

## **2. LCA of Binary Tree (LC 236)**

Find LCA in general binary tree using postorder traversal.

### Approach: Postorder DFS (Classic)

```python
def lowest_common_ancestor(root: Optional[TreeNode], p: TreeNode, q: TreeNode) -> Optional[TreeNode]:
    """
    LCA in binary tree using postorder DFS.
    Time: O(n), Space: O(h)
  
    Key insight:
    - If current node is p or q, return it
    - If both subtrees return non-null, current node is LCA
    - Otherwise, return whichever subtree found something
    """
    # Base case: empty tree or found target
    if not root or root == p or root == q:
        return root
  
    # Search in left and right subtrees
    left = lowest_common_ancestor(root.left, p, q)
    right = lowest_common_ancestor(root.right, p, q)
  
    # Both subtrees found something → current node is LCA
    if left and right:
        return root
  
    # Return whichever subtree found something (or None)
    return left or right
```

**How it works:**

```python
#       3
#      / \
#     5   1
#    / \ / \
#   6  2 0  8
#     / \
#    7   4

# LCA(5, 1) = 3
# - left subtree finds 5
# - right subtree finds 1
# - both non-null → return 3

# LCA(5, 4) = 5
# - At node 5: root == p, return 5
# - Never explores beyond 5

# LCA(6, 4) = 5
# - At node 5: left finds 6, right finds 4
# - Both non-null → return 5
```

---

## **3. LCA of Binary Tree II (LC 1644)**

Find LCA when nodes might not exist in tree.

### Approach: Track Found Count

```python
def lowest_common_ancestor_ii(root: Optional[TreeNode], p: TreeNode, q: TreeNode) -> Optional[TreeNode]:
    """
    LCA with validation that both nodes exist.
    Time: O(n), Space: O(h)
    """
    found_count = 0
  
    def dfs(node: Optional[TreeNode]) -> Optional[TreeNode]:
        nonlocal found_count
    
        if not node:
            return None
    
        # Search in subtrees
        left = dfs(node.left)
        right = dfs(node.right)
    
        # Check if current node is p or q
        is_target = (node == p or node == q)
        if is_target:
            found_count += 1
    
        # Return LCA logic
        if is_target and (left or right):
            return node
    
        if left and right:
            return node
    
        return node if is_target else (left or right)
  
    result = dfs(root)
  
    # Both nodes must be found
    return result if found_count == 2 else None
```

**Example:**

```python
#       3
#      / \
#     5   1
#    / \
#   6   2

p = TreeNode(5)  # Exists
q = TreeNode(10) # Does NOT exist

# Result: None (q not found, found_count = 1)
```

---

## **4. LCA of Binary Tree III (LC 1650)**

Find LCA when nodes have parent pointers.

### Approach 1: Two Pointers (Linked List Intersection)

```python
class Node:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
        self.parent = None

def lowest_common_ancestor_iii(p: Node, q: Node) -> Node:
    """
    LCA with parent pointers using two-pointer technique.
    Time: O(h), Space: O(1)
  
    Similar to finding intersection of two linked lists.
    """
    a, b = p, q
  
    # Move pointers until they meet
    while a != b:
        # When a reaches root, jump to q
        a = a.parent if a else q
        # When b reaches root, jump to p
        b = b.parent if b else p
  
    return a
```

### Approach 2: Hash Set

```python
def lowest_common_ancestor_iii_set(p: Node, q: Node) -> Node:
    """
    Use set to track ancestors of p.
    Time: O(h), Space: O(h)
    """
    # Store all ancestors of p
    ancestors = set()
    current = p
  
    while current:
        ancestors.add(current)
        current = current.parent
  
    # Find first common ancestor from q
    current = q
    while current:
        if current in ancestors:
            return current
        current = current.parent
  
    return None
```

**Example:**

```python
#       3
#      / \
#     5   1
#    / \
#   6   2
#      / \
#     7   4

# With parent pointers:
# p = 7, q = 4
# Path p to root: 7 → 2 → 5 → 3
# Path q to root: 4 → 2 → 5 → 3
# LCA = 2 (first common node)
```

---

## **5. LCA of Binary Tree IV (LC 1676)**

Find LCA of multiple nodes.

### Approach: Extend Classic LCA

```python
def lowest_common_ancestor_iv(root: Optional[TreeNode], nodes: list[TreeNode]) -> Optional[TreeNode]:
    """
    LCA of multiple nodes.
    Time: O(n), Space: O(h)
    """
    # Convert list to set for O(1) lookup
    nodes_set = set(nodes)
  
    def dfs(node: Optional[TreeNode]) -> Optional[TreeNode]:
        if not node or node in nodes_set:
            return node
    
        left = dfs(node.left)
        right = dfs(node.right)
    
        # Both subtrees found nodes → current is LCA
        if left and right:
            return node
    
        return left or right
  
    return dfs(root)
```

**Example:**

```python
#       3
#      / \
#     5   1
#    / \ / \
#   6  2 0  8
#     / \
#    7   4

# LCA([6, 7, 4]) = 5
# - All three nodes in left subtree rooted at 5
# LCA([6, 0, 8]) = 3
# - Nodes split between left and right subtrees
```

---

## **6. Smallest Subtree with Deepest Nodes (LC 865)**

Find LCA of all deepest leaves.

### Approach: Track Depth and LCA Together

```python
def subtree_with_all_deepest(root: Optional[TreeNode]) -> Optional[TreeNode]:
    """
    Find LCA of deepest leaves.
    Time: O(n), Space: O(h)
    """
    def dfs(node: Optional[TreeNode]) -> tuple[Optional[TreeNode], int]:
        """
        Returns: (lca_of_deepest_in_subtree, depth)
        """
        if not node:
            return (None, 0)
    
        left_lca, left_depth = dfs(node.left)
        right_lca, right_depth = dfs(node.right)
    
        # Same depth on both sides → current node is LCA
        if left_depth == right_depth:
            return (node, left_depth + 1)
    
        # Return deeper side
        if left_depth > right_depth:
            return (left_lca, left_depth + 1)
        else:
            return (right_lca, right_depth + 1)
  
    return dfs(root)[0]
```

**Example:**

```python
#       3
#      / \
#     5   1
#    / \   \
#   6   2   0
#      / \   \
#     7   4   8

# Deepest nodes: 7, 4, 8 (depth = 3)
# LCA(7, 4) = 2
# LCA(2, 8) = 3
# Result: 3
```

---

## **7. Distance Between Two Nodes**

Calculate distance between two nodes in binary tree.

### Approach: LCA + Distance from LCA

```python
def find_distance(root: Optional[TreeNode], p: TreeNode, q: TreeNode) -> int:
    """
    Find distance between two nodes.
    Time: O(n), Space: O(h)
  
    Strategy: dist(p, q) = dist(lca, p) + dist(lca, q)
    """
    # Find LCA
    lca = lowest_common_ancestor(root, p, q)
  
    def distance_from_node(node: Optional[TreeNode], target: TreeNode) -> int:
        """Find distance from node to target."""
        if not node:
            return -1
    
        if node == target:
            return 0
    
        # Search in left subtree
        left_dist = distance_from_node(node.left, target)
        if left_dist != -1:
            return left_dist + 1
    
        # Search in right subtree
        right_dist = distance_from_node(node.right, target)
        if right_dist != -1:
            return right_dist + 1
    
        return -1
  
    # Calculate distance from LCA to both nodes
    dist_p = distance_from_node(lca, p)
    dist_q = distance_from_node(lca, q)
  
    return dist_p + dist_q
```

### Optimized: Single Pass

```python
def find_distance_optimized(root: Optional[TreeNode], p: TreeNode, q: TreeNode) -> int:
    """
    Find distance in single DFS pass.
    Time: O(n), Space: O(h)
    """
    result = 0
  
    def dfs(node: Optional[TreeNode]) -> tuple[bool, int]:
        """
        Returns: (found_target, distance_from_lca)
        """
        nonlocal result
    
        if not node:
            return (False, 0)
    
        left_found, left_dist = dfs(node.left)
        right_found, right_dist = dfs(node.right)
    
        is_target = (node == p or node == q)
    
        # Both subtrees found targets
        if left_found and right_found:
            result = left_dist + right_dist
            return (True, 0)
    
        # One subtree found, current is other target
        if (left_found or right_found) and is_target:
            result = max(left_dist, right_dist)
            return (True, 0)
    
        # Pass up the distance
        if left_found:
            return (True, left_dist + 1)
        if right_found:
            return (True, right_dist + 1)
        if is_target:
            return (True, 0)
    
        return (False, 0)
  
    dfs(root)
    return result
```

**Example:**

```python
#       3
#      / \
#     5   1
#    / \ / \
#   6  2 0  8
#     / \
#    7   4

# Distance(6, 4):
# LCA = 5
# dist(5, 6) = 1
# dist(5, 4) = 2
# Total = 1 + 2 = 3
```

---

## **8. Path Between Two Nodes**

Find the actual path between two nodes.

### Approach: Find Paths to LCA

```python
def find_path(root: Optional[TreeNode], p: TreeNode, q: TreeNode) -> list[TreeNode]:
    """
    Find path from p to q through LCA.
    Time: O(n), Space: O(h)
    """
    def find_path_to_target(node: Optional[TreeNode], 
                           target: TreeNode, 
                           path: list[TreeNode]) -> bool:
        """Build path from node to target."""
        if not node:
            return False
    
        path.append(node)
    
        if node == target:
            return True
    
        if (find_path_to_target(node.left, target, path) or
            find_path_to_target(node.right, target, path)):
            return True
    
        path.pop()
        return False
  
    # Find paths from root to both nodes
    path_to_p = []
    path_to_q = []
  
    find_path_to_target(root, p, path_to_p)
    find_path_to_target(root, q, path_to_q)
  
    # Find LCA (last common node)
    lca_idx = 0
    for i in range(min(len(path_to_p), len(path_to_q))):
        if path_to_p[i] == path_to_q[i]:
            lca_idx = i
        else:
            break
  
    # Build path: p → LCA → q
    # Reverse path from p to LCA, then add path from LCA to q
    result = path_to_p[:lca_idx+1][::-1]  # Reverse up to LCA
    result.extend(path_to_q[lca_idx+1:])  # Add down to q
  
    return result
```

**Example:**

```python
#       3
#      / \
#     5   1
#    / \   \
#   6   2   0
#      / \
#     7   4

# Path(7, 0):
# path_to_7: [3, 5, 2, 7]
# path_to_0: [3, 1, 0]
# LCA = 3 (index 0)
# Result: [7, 2, 5, 3, 1, 0]
```

---

## **Universal LCA Pattern Template**

```python
def lca_template(root: Optional[TreeNode], 
                 targets: set[TreeNode]) -> Optional[TreeNode]:
    """
    Generic LCA template for multiple variations.
  
    Variations:
    1. Two nodes (LC 236)
    2. Multiple nodes (LC 1676)
    3. Nodes may not exist (LC 1644)
    4. With depth tracking (LC 865)
    """
    found_count = 0
  
    def dfs(node: Optional[TreeNode]) -> Optional[TreeNode]:
        nonlocal found_count
    
        # Base case
        if not node:
            return None
    
        # Check if current node is target
        is_target = node in targets
        if is_target:
            found_count += 1
    
        # Recurse on subtrees
        left = dfs(node.left)
        right = dfs(node.right)
    
        # LCA logic: multiple return patterns
    
        # Pattern 1: Current node is target AND found in subtree
        if is_target and (left or right):
            return node
    
        # Pattern 2: Both subtrees found targets
        if left and right:
            return node
    
        # Pattern 3: Return current if target, else propagate subtree result
        return node if is_target else (left or right)
  
    result = dfs(root)
  
    # Validation: ensure all targets found (for LC 1644)
    # return result if found_count == len(targets) else None
  
    return result
```

---

## **Comparison Table**

| Problem                   | Input                   | Key Technique     | Time | Space |
| ------------------------- | ----------------------- | ----------------- | ---- | ----- |
| **BST LCA**         | 2 nodes                 | Value ordering    | O(h) | O(1)  |
| **Binary Tree LCA** | 2 nodes                 | Postorder DFS     | O(n) | O(h)  |
| **LCA II**          | 2 nodes (may not exist) | Track found count | O(n) | O(h)  |
| **LCA III**         | Parent pointers         | Two pointers      | O(h) | O(1)  |
| **LCA IV**          | Multiple nodes          | Set lookup        | O(n) | O(h)  |
| **Deepest Nodes**   | All deepest             | Track depth       | O(n) | O(h)  |
| **Distance**        | 2 nodes                 | LCA + path length | O(n) | O(h)  |

---

## **Key Patterns for Meta Interviews**

### **1. LCA Return Value Meaning**

```python
# Returns:
# - None: Target(s) not found in subtree
# - Node: Target found OR LCA found
# - Key: Check both left AND right to determine if LCA
```

### **2. Three Cases in LCA**

```python
if left and right:
    return root  # Split point (LCA)

if root == p or root == q:
    return root  # Current node is target

return left or right  # Propagate found node up
```

### **3. BST vs Binary Tree**

```python
# BST: Use ordering (no need to explore both sides)
if root.val > max(p.val, q.val):
    go_left()

# Binary Tree: Must explore both sides
left = dfs(node.left)
right = dfs(node.right)
```

### **4. Depth Tracking Pattern**

```python
def dfs(node) -> tuple[TreeNode, int]:
    # Return (lca, depth) tuple
    # Compare depths to decide LCA
    if left_depth == right_depth:
        return (node, depth + 1)
```

### **5. Common Mistakes**

❌ Forgetting to check if node == target before recursing

❌ Not handling case where one node is ancestor of another

❌ Missing validation that both nodes exist (LC 1644)

❌ Wrong base case (should return root if root == p or root == q)

❌ For BST: comparing with wrong values (use min/max)

### **6. Optimization Tips**

* **BST:** Iterative > Recursive (O(1) space)
* **With parent pointers:** Two pointers > Hash set
* **Multiple nodes:** Convert list to set for O(1) lookup
* **Distance:** Combine LCA + distance in single pass

### **7. Follow-up Questions**

* "What if tree has duplicate values?" → Use node references, not values
* "Find kth ancestor?" → Extend parent pointer approach
* "LCA in n-ary tree?" → Same logic, iterate all children
* "Path between nodes?" → Build paths to LCA and combine

---

## **Complete Example Walkthrough**

```python
# Build tree:
#       3
#      / \
#     5   1
#    / \ / \
#   6  2 0  8
#     / \
#    7   4

root = TreeNode(3)
root.left = TreeNode(5)
root.right = TreeNode(1)
root.left.left = TreeNode(6)
root.left.right = TreeNode(2)
root.right.left = TreeNode(0)
root.right.right = TreeNode(8)
root.left.right.left = TreeNode(7)
root.left.right.right = TreeNode(4)

# Store node references
node5 = root.left
node1 = root.right
node6 = root.left.left
node4 = root.left.right.right

# Test different LCA problems
print(lowest_common_ancestor(root, node5, node1).val)  # 3
print(lowest_common_ancestor(root, node5, node4).val)  # 5
print(lowest_common_ancestor(root, node6, node4).val)  # 5
print(find_distance(root, node6, node4))               # 3
print(subtree_with_all_deepest(root).val)              # 2

# Trace execution for LCA(6, 4):
# dfs(3): left=dfs(5), right=dfs(1)
#   dfs(5): left=dfs(6), right=dfs(2)
#     dfs(6): returns 6 (found)
#     dfs(2): left=dfs(7), right=dfs(4)
#       dfs(7): returns None
#       dfs(4): returns 4 (found)
#     Returns: 2 (left and right both non-null)
#   dfs(1): returns None
# Returns: 5 (only left is non-null)
# Final LCA: 5
```

**Pro Tip for Meta:** Always explain the three-case logic clearly:

1. Both subtrees return something → current node is LCA
2. One subtree returns something → propagate it up
3. Current node is target → return it

Then walk through a concrete example to show how values bubble up!
