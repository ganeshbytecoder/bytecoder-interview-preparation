
# Tree Views Pattern

**Use Cases:** Right view, left view, top view, bottom view, vertical order traversal, boundary traversal

**💡 Key Insight:**

* **Right/Left View:** Track first/last node per level using BFS or DFS
* **Top/Bottom View:** Use horizontal distance tracking with BFS
* **Vertical Order:** Group nodes by column index using BFS + HashMap
* **Boundary:** Combine left edge + leaves + reversed right edge

**Common LeetCode Problems:**

* Binary Tree Right Side View (LC 199)
* Vertical Order Traversal (LC 314, 987)
* Boundary of Binary Tree (LC 545)

---

## **1. Left View of Binary Tree**

Return the leftmost node at each level visible from the left side.

### Approach: Level Order Traversal (BFS)

Track the first node at each level during BFS.

```python
from collections import deque
from typing import List, Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def left_view(root: Optional[TreeNode]) -> List[int]:
    """
    Returns left view of binary tree using BFS.
    Time: O(n), Space: O(w) where w is max width
    """
    if not root:
        return []
  
    result = []
    queue = deque([root])
  
    while queue:
        level_size = len(queue)
      
        for i in range(level_size):
            node = queue.popleft()
          
            # First node at current level
            if i == 0:
                result.append(node.val)
          
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
  
    return result
```

---

## **2. Right View of Binary Tree (LC 199)**

Return the rightmost node at each level visible from the right side.

### Approach 1: BFS (Optimal for Meta)

```python
def right_side_view_bfs(root: Optional[TreeNode]) -> List[int]:
    """
    Right side view using BFS - cleaner for Meta interviews.
    Time: O(n), Space: O(w)
    """
    if not root:
        return []
  
    result = []
    queue = deque([root])
  
    while queue:
        level_size = len(queue)
      
        for i in range(level_size):
            node = queue.popleft()
          
            # Last node at current level
            if i == level_size - 1:
                result.append(node.val)
          
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
  
    return result
```

### Approach 2: DFS (Space-Efficient)

```python
def right_side_view_dfs(root: Optional[TreeNode]) -> List[int]:
    """
    Right side view using DFS - better space for skewed trees.
    Time: O(n), Space: O(h)
    """
    result = []
  
    def dfs(node: Optional[TreeNode], level: int) -> None:
        if not node:
            return
      
        # First time visiting this level
        if level == len(result):
            result.append(node.val)
      
        # Process right subtree first for right view
        dfs(node.right, level + 1)
        dfs(node.left, level + 1)
  
    dfs(root, 0)
    return result
```

---

## **3. Top View of Binary Tree**

Return nodes visible from the top, ordered by horizontal distance.

### Approach: BFS + Horizontal Distance Tracking

```python
from collections import deque, defaultdict

def top_view(root: Optional[TreeNode]) -> List[int]:
    """
    Top view using horizontal distance (HD).
    Time: O(n), Space: O(n)
    """
    if not root:
        return []
  
    # Map: horizontal_distance -> first node value at that HD
    hd_map = {}
  
    # Queue: (node, horizontal_distance)
    queue = deque([(root, 0)])
  
    while queue:
        node, hd = queue.popleft()
      
        # Only store first node at each horizontal distance
        if hd not in hd_map:
            hd_map[hd] = node.val
      
        if node.left:
            queue.append((node.left, hd - 1))
        if node.right:
            queue.append((node.right, hd + 1))
  
    # Sort by horizontal distance and return values
    return [hd_map[hd] for hd in sorted(hd_map.keys())]
```

---

## **4. Bottom View of Binary Tree**

Return nodes visible from the bottom, ordered by horizontal distance.

### Approach: BFS + Horizontal Distance (Update Every Time)

```python
def bottom_view(root: Optional[TreeNode]) -> List[int]:
    """
    Bottom view - overwrite map for last node at each HD.
    Time: O(n), Space: O(n)
    """
    if not root:
        return []
  
    # Map: horizontal_distance -> last node value at that HD
    hd_map = {}
  
    # Queue: (node, horizontal_distance)
    queue = deque([(root, 0)])
  
    while queue:
        node, hd = queue.popleft()
      
        # Update with current node (overwrites previous)
        hd_map[hd] = node.val
      
        if node.left:
            queue.append((node.left, hd - 1))
        if node.right:
            queue.append((node.right, hd + 1))
  
    # Sort by horizontal distance and return values
    return [hd_map[hd] for hd in sorted(hd_map.keys())]
```

---

## **5. Vertical Order Traversal (LC 314)**

Return nodes grouped by vertical column, ordered left to right, top to bottom.

### Approach: BFS + Column Tracking

```python
def vertical_order(root: Optional[TreeNode]) -> List[List[int]]:
    """
    Vertical order traversal using BFS.
    Time: O(n log n), Space: O(n)
    """
    if not root:
        return []
  
    # Map: column -> list of node values
    column_map = defaultdict(list)
  
    # Queue: (node, column)
    queue = deque([(root, 0)])
  
    while queue:
        node, col = queue.popleft()
        column_map[col].append(node.val)
      
        if node.left:
            queue.append((node.left, col - 1))
        if node.right:
            queue.append((node.right, col + 1))
  
    # Sort by column and return values
    return [column_map[col] for col in sorted(column_map.keys())]
```

---

## **Key Patterns & Tips for Meta Interviews**

### **1. Top vs. Bottom View**

| View                  | Strategy                                                        |
| --------------------- | --------------------------------------------------------------- |
| **Top View**    | Store**first**node at each horizontal distance            |
| **Bottom View** | Store**last**node at each horizontal distance (overwrite) |

### **2. Horizontal Distance (HD) Rules**

* Root has HD = 0
* Left child: `parent_hd - 1`
* Right child: `parent_hd + 1`

### **3. BFS vs. DFS Trade-offs**

* **BFS:** Better for level-based views, uses `O(w)` space
* **DFS:** Better for skewed trees, uses `O(h)` space
* **For Meta:** Start with BFS, mention DFS as optimization

### **4. Common Mistakes to Avoid**

* ❌ Forgetting to handle empty tree
* ❌ Not sorting horizontal distances in top/bottom view
* ❌ Using wrong traversal order in right view DFS (should be right→left)
* ❌ Not using `defaultdict` for column grouping in vertical order

### **5. Follow-up Questions to Expect**

* "How would you handle ties in vertical order?" (Add row tracking)
* "What if we need diagonal view?" (Use HD + level as key)
* "Can you optimize space complexity?" (Switch BFS to DFS)

---

## **Complete Example Usage**

```python
# Build sample tree:
#       1
#      / \
#     2   3
#    / \   \
#   4   5   6

root = TreeNode(1)
root.left = TreeNode(2)
root.right = TreeNode(3)
root.left.left = TreeNode(4)
root.left.right = TreeNode(5)
root.right.right = TreeNode(6)

print(left_view(root))              # [1, 2, 4]
print(right_side_view_bfs(root))    # [1, 3, 6]
print(top_view(root))               # [4, 2, 1, 3, 6]
print(bottom_view(root))            # [4, 2, 5, 3, 6]
print(vertical_order(root))         # [[4], [2], [1, 5], [3], [6]]
```

**Pro Tip for Meta:** Always clarify edge cases (empty tree, single node, skewed tree) and discuss time/space complexity before coding!
