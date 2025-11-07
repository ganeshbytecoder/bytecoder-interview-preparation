- Path Sum (112)

# Path Sum Pattern

**Use Cases:** Root-to-leaf paths, any path with target sum, path counting, path collections, binary numbers in paths, specific node paths (left leaves)

**💡 Key Insight:**

* **Root-to-Leaf:** DFS with target reduction, check leaf condition
* **Any Path:** Prefix sum + HashMap (like subarray sum)
* **Path Collection:** DFS with backtracking to build paths
* **Binary Numbers:** Left shift and OR operations during traversal
* **K Sum Paths:** Sliding window on path with backtracking

**Common LeetCode Problems:**

* Path Sum (LC 112)
* Path Sum II (LC 113)
* Path Sum III (LC 437)
* Sum Root to Leaf Numbers (LC 129)
* Binary Tree Maximum Path Sum (LC 124)
* Binary Tree Paths (LC 257)
* Sum of Left Leaves (LC 404)
* Sum Root to Leaf Binary Numbers (LC 1022)
* Path Sum IV (LC 666)

---

## **1. Path Sum (LC 112)**

Check if root-to-leaf path exists with given sum.

### Approach: DFS with Target Reduction

```python
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def has_path_sum(root: Optional[TreeNode], target_sum: int) -> bool:
    """
    Check if root-to-leaf path with target sum exists.
    Time: O(n), Space: O(h)
  
    Key: Only check at leaves, reduce target as we go down
    """
    if not root:
        return False
  
    # Leaf node: check if current value equals remaining target
    if not root.left and not root.right:
        return root.val == target_sum
  
    # Recurse with reduced target
    remaining = target_sum - root.val
    return (has_path_sum(root.left, remaining) or
            has_path_sum(root.right, remaining))
```

### Approach 2: Iterative (BFS)

```python
from collections import deque

def has_path_sum_iterative(root: Optional[TreeNode], target_sum: int) -> bool:
    """
    Iterative solution using BFS.
    Time: O(n), Space: O(w)
    """
    if not root:
        return False
  
    queue = deque([(root, root.val)])
  
    while queue:
        node, curr_sum = queue.popleft()
  
        # Check if leaf with target sum
        if not node.left and not node.right and curr_sum == target_sum:
            return True
  
        if node.left:
            queue.append((node.left, curr_sum + node.left.val))
        if node.right:
            queue.append((node.right, curr_sum + node.right.val))
  
    return False
```

**Example:**

```python
#       5
#      / \
#     4   8
#    /   / \
#   11  13  4
#  /  \      \
# 7    2      1

# has_path_sum(root, 22) = True
# Path: 5 → 4 → 11 → 2 (5+4+11+2=22)

# has_path_sum(root, 26) = True
# Path: 5 → 8 → 13 (5+8+13=26)

# has_path_sum(root, 10) = False
```

---

## **2. Path Sum II (LC 113)**

Return all root-to-leaf paths that sum to target.

### Approach: DFS with Backtracking

```python
def path_sum_ii(root: Optional[TreeNode], target_sum: int) -> list[list[int]]:
    """
    Find all root-to-leaf paths with target sum.
    Time: O(n), Space: O(h) for recursion + O(n) for result
  
    Key: Use backtracking to build and collect paths
    """
    result = []
  
    def dfs(node: Optional[TreeNode], remaining: int, path: list[int]) -> None:
        if not node:
            return
  
        # Add current node to path
        path.append(node.val)
  
        # Check if leaf with target sum
        if not node.left and not node.right and remaining == node.val:
            result.append(path[:])  # Copy path
  
        # Recurse on children
        dfs(node.left, remaining - node.val, path)
        dfs(node.right, remaining - node.val, path)
  
        # Backtrack: remove current node
        path.pop()
  
    dfs(root, target_sum, [])
    return result
```

### Approach 2: Without Backtracking (Pass New List)

```python
def path_sum_ii_v2(root: Optional[TreeNode], target_sum: int) -> list[list[int]]:
    """
    Alternative: pass new list to each recursive call.
    Time: O(n), Space: O(n*h) - more space for path copies
    """
    if not root:
        return []
  
    result = []
  
    def dfs(node: Optional[TreeNode], remaining: int, path: list[int]) -> None:
        # Add current node
        new_path = path + [node.val]
  
        # Check if leaf with target sum
        if not node.left and not node.right:
            if remaining == node.val:
                result.append(new_path)
            return
  
        # Recurse with new path
        if node.left:
            dfs(node.left, remaining - node.val, new_path)
        if node.right:
            dfs(node.right, remaining - node.val, new_path)
  
    dfs(root, target_sum, [])
    return result
```

**Example:**

```python
#       5
#      / \
#     4   8
#    /   / \
#   11  13  4
#  /  \    / \
# 7    2  5   1

# path_sum_ii(root, 22) = [[5,4,11,2], [5,8,4,5]]
```

---

## **3. Path Sum III (LC 437)**

Count paths with target sum (not necessarily root-to-leaf, any path going downward).

### Approach: Prefix Sum with HashMap

```python
	from collections import defaultdict

def path_sum_iii(root: Optional[TreeNode], target_sum: int) -> int:
    """
    Count all downward paths with target sum.
    Time: O(n), Space: O(h)
  
    Key insight: Similar to subarray sum = k
    If curr_sum - target exists in prefix_sums, we found a valid path
    """
    def dfs(node: Optional[TreeNode], curr_sum: int) -> int:
        if not node:
            return 0
  
        # Update current sum
        curr_sum += node.val
  
        # Count paths ending at current node
        # If (curr_sum - target) exists, those are valid starting points
        count = prefix_sums[curr_sum - target_sum]
  
        # Add current sum to prefix sums
        prefix_sums[curr_sum] += 1
  
        # Recurse on children
        count += dfs(node.left, curr_sum)
        count += dfs(node.right, curr_sum)
  
        # Backtrack: remove current sum
        prefix_sums[curr_sum] -= 1
  
        return count
  
    prefix_sums = defaultdict(int)
    prefix_sums[0] = 1  # Base case: path from root
  
    return dfs(root, 0)
```

### Approach 2: Brute Force (For Understanding)

```python
def path_sum_iii_brute(root: Optional[TreeNode], target_sum: int) -> int:
    """
    Brute force: try starting from each node.
    Time: O(n²), Space: O(h)
    """
    def count_paths_from_node(node: Optional[TreeNode], target: int) -> int:
        """Count paths starting from this node."""
        if not node:
            return 0
  
        count = 1 if node.val == target else 0
        count += count_paths_from_node(node.left, target - node.val)
        count += count_paths_from_node(node.right, target - node.val)
  
        return count
  
    if not root:
        return 0
  
    # Paths starting from current node
    count = count_paths_from_node(root, target_sum)
  
    # Paths starting from left and right subtrees
    count += path_sum_iii_brute(root.left, target_sum)
    count += path_sum_iii_brute(root.right, target_sum)
  
    return count
```

**Example:**

```python
#       10
#      /  \
#     5   -3
#    / \    \
#   3   2   11
#  / \   \
# 3  -2   1

# path_sum_iii(root, 8) = 3
# Paths: [5,3], [5,2,1], [-3,11]
```

**How Prefix Sum Works:**

```python
# Path: 10 → 5 → 3
# curr_sum progression: 10, 15, 18
# target_sum = 8

# At node 3 (curr_sum = 18):
# Check: prefix_sums[18 - 8] = prefix_sums[10] = 1
# This means: path from node after 10 to current = 8
# Path found: 5 → 3 (sum = 8)
```

---

## **4. Sum Root to Leaf Numbers (LC 129)**

Sum all numbers formed by root-to-leaf paths.

### Approach: DFS with Number Building

```python
def sum_numbers(root: Optional[TreeNode]) -> int:
    """
    Sum numbers formed by root-to-leaf paths.
    Time: O(n), Space: O(h)
  
    Example: Path 1→2→3 forms number 123
    """
    def dfs(node: Optional[TreeNode], current_num: int) -> int:
        if not node:
            return 0
  
        # Build number: shift left and add current digit
        current_num = current_num * 10 + node.val
  
        # Leaf: return the formed number
        if not node.left and not node.right:
            return current_num
  
        # Sum from both subtrees
        return dfs(node.left, current_num) + dfs(node.right, current_num)
  
    return dfs(root, 0)
```

**Example:**

```python
#     1
#    / \
#   2   3

# Paths: 1→2 (12), 1→3 (13)
# Result: 12 + 13 = 25

#     4
#    / \
#   9   0
#  / \
# 5   1

# Paths: 4→9→5 (495), 4→9→1 (491), 4→0 (40)
# Result: 495 + 491 + 40 = 1026
```

---

## **5. Sum Root to Leaf Binary Numbers (LC 1022)**

Sum binary numbers formed by root-to-leaf paths.

### Approach: Bit Manipulation

```python
def sum_root_to_leaf(root: Optional[TreeNode]) -> int:
    """
    Sum binary numbers from root-to-leaf paths.
    Time: O(n), Space: O(h)
  
    Example: Path 1→0→1 forms binary 101 = 5
    Formula: val = (val << 1) | node.val
    """
    def dfs(node: Optional[TreeNode], current_val: int) -> int:
        if not node:
            return 0
  
        # Left shift and add current bit
        current_val = (current_val << 1) | node.val
  
        # Leaf: return the binary number
        if not node.left and not node.right:
            return current_val
  
        # Sum from both subtrees
        return dfs(node.left, current_val) + dfs(node.right, current_val)
  
    return dfs(root, 0)
```

**Example:**

```python
#     1
#    / \
#   0   1
#  / \   \
# 0   1   1

# Paths: 
# 1→0→0 = 100₂ = 4
# 1→0→1 = 101₂ = 5
# 1→1→1 = 111₂ = 7
# Result: 4 + 5 + 7 = 16
```

**Bit Operations Explained:**

```python
# Start: current_val = 0
# Node 1: current_val = (0 << 1) | 1 = 0 | 1 = 1  (binary: 1)
# Node 0: current_val = (1 << 1) | 0 = 2 | 0 = 2  (binary: 10)
# Node 1: current_val = (2 << 1) | 1 = 4 | 1 = 5  (binary: 101)
```

---

## **6. Binary Tree Paths (LC 257)**

Return all root-to-leaf paths as strings.

### Approach: DFS with String Building

```python
def binary_tree_paths(root: Optional[TreeNode]) -> list[str]:
    """
    Return all root-to-leaf paths as strings.
    Time: O(n), Space: O(h)
    """
    if not root:
        return []
  
    result = []
  
    def dfs(node: Optional[TreeNode], path: str) -> None:
        if not node:
            return
  
        # Add current node to path
        path += str(node.val)
  
        # Leaf: add path to result
        if not node.left and not node.right:
            result.append(path)
            return
  
        # Continue with arrow
        path += "->"
        dfs(node.left, path)
        dfs(node.right, path)
  
    dfs(root, "")
    return result
```

### Approach 2: With List (More Efficient)

```python
def binary_tree_paths_v2(root: Optional[TreeNode]) -> list[str]:
    """
    Use list for path building (more efficient than string concatenation).
    Time: O(n), Space: O(h)
    """
    result = []
  
    def dfs(node: Optional[TreeNode], path: list[int]) -> None:
        if not node:
            return
  
        path.append(node.val)
  
        # Leaf: convert path to string
        if not node.left and not node.right:
            result.append("->".join(map(str, path)))
        else:
            dfs(node.left, path)
            dfs(node.right, path)
  
        path.pop()  # Backtrack
  
    dfs(root, [])
    return result
```

**Example:**

```python
#     1
#    / \
#   2   3
#    \
#     5

# Result: ["1->2->5", "1->3"]
```

---

## **7. Sum of Left Leaves (LC 404)**

Sum all left leaves in the tree.

### Approach: DFS with Parent Tracking

```python
def sum_of_left_leaves(root: Optional[TreeNode]) -> int:
    """
    Sum all left leaves (leaves that are left children).
    Time: O(n), Space: O(h)
    """
    def dfs(node: Optional[TreeNode], is_left: bool) -> int:
        if not node:
            return 0
  
        # Leaf node that is a left child
        if not node.left and not node.right and is_left:
            return node.val
  
        # Recurse: left children are marked True
        return dfs(node.left, True) + dfs(node.right, False)
  
    return dfs(root, False)
```

### Approach 2: Iterative

```python
def sum_of_left_leaves_iterative(root: Optional[TreeNode]) -> int:
    """
    Iterative solution using stack.
    Time: O(n), Space: O(h)
    """
    if not root:
        return 0
  
    total = 0
    stack = [root]
  
    while stack:
        node = stack.pop()
  
        # Check if left child is a leaf
        if node.left:
            if not node.left.left and not node.left.right:
                total += node.left.val
            else:
                stack.append(node.left)
  
        if node.right:
            stack.append(node.right)
  
    return total
```

**Example:**

```python
#     3
#    / \
#   9  20
#     /  \
#    15   7

# Left leaves: 9, 15
# Result: 9 + 15 = 24
```

---

## **8. Print All K Sum Paths**

Find all paths (from any ancestor to any descendant) that sum to K.

### Approach: DFS with Path Tracking

```python
def k_sum_paths(root: Optional[TreeNode], k: int) -> list[list[int]]:
    """
    Find all paths from any ancestor to descendant with sum K.
    Time: O(n²), Space: O(h)
    """
    result = []
  
    def dfs(node: Optional[TreeNode], path: list[int]) -> None:
        if not node:
            return
  
        # Add current node to path
        path.append(node.val)
  
        # Check all suffixes of current path
        current_sum = 0
        for i in range(len(path) - 1, -1, -1):
            current_sum += path[i]
            if current_sum == k:
                result.append(path[i:].copy())
  
        # Recurse on children
        dfs(node.left, path)
        dfs(node.right, path)
  
        # Backtrack
        path.pop()
  
    dfs(root, [])
    return result
```

### Approach 2: Optimized with Prefix Sum

```python
def k_sum_paths_optimized(root: Optional[TreeNode], k: int) -> int:
    """
    Count K sum paths using prefix sum (faster).
    Time: O(n), Space: O(h)
    """
    def dfs(node: Optional[TreeNode], curr_sum: int, path: list[int]) -> int:
        if not node:
            return 0
  
        curr_sum += node.val
        path.append(node.val)
  
        # Count valid paths ending at current node
        count = 0
        temp_sum = 0
        for i in range(len(path) - 1, -1, -1):
            temp_sum += path[i]
            if temp_sum == k:
                count += 1
  
        # Recurse
        count += dfs(node.left, curr_sum, path)
        count += dfs(node.right, curr_sum, path)
  
        path.pop()
        return count
  
    return dfs(root, 0, [])
```

**Example:**

```python
#       10
#      /  \
#     5   -3
#    / \    \
#   3   2   11
#  / \   \
# 3  -2   1

# k = 8
# Paths: [5,3], [5,2,1], [-3,11]
# Result: [[5,3], [5,2,1], [-3,11]]
```

---

## **9. Binary Tree Maximum Path Sum (LC 124)**

Find maximum path sum (covered in Subtree Pattern, but relevant here).

```python
def max_path_sum(root: Optional[TreeNode]) -> int:
    """
    Maximum path sum between any two nodes.
    Time: O(n), Space: O(h)
    """
    max_sum = float('-inf')
  
    def dfs(node: Optional[TreeNode]) -> int:
        nonlocal max_sum
  
        if not node:
            return 0
  
        # Get max gains from left and right (ignore negative)
        left_gain = max(dfs(node.left), 0)
        right_gain = max(dfs(node.right), 0)
  
        # Update global max with path through current node
        max_sum = max(max_sum, node.val + left_gain + right_gain)
  
        # Return max path sum including current node
        return node.val + max(left_gain, right_gain)
  
    dfs(root)
    return max_sum
```

---

## **Universal Path Sum Template**

```python
def path_sum_template(root: Optional[TreeNode], target: int) -> Any:
    """
    Generic template for path sum problems.
  
    Variations:
    1. Root-to-leaf only
    2. Any downward path
    3. Path collection (all paths)
    4. Count paths
    5. Build numbers/strings
    """
    result = []  # or counter, or sum
  
    def dfs(node: Optional[TreeNode], 
            current_state: Any,  # sum, path, number, etc.
            additional_info: Any = None) -> Any:
  
        # Base case
        if not node:
            return base_case_value
  
        # Update current state
        current_state = update_state(current_state, node.val)
  
        # Check condition (for root-to-leaf)
        if is_leaf(node):
            if satisfies_condition(current_state, target):
                result.append(current_state)  # or increment counter
  
        # Recurse on children
        left_result = dfs(node.left, current_state)
        right_result = dfs(node.right, current_state)
  
        # Backtrack if needed (for path collection)
        backtrack_if_needed(current_state)
  
        # Combine results
        return combine(left_result, right_result)
  
    dfs(root, initial_state)
    return result
```

---

## **Comparison Table**

| Problem                  | Path Type      | Technique        | Time   | Space |
| ------------------------ | -------------- | ---------------- | ------ | ----- |
| **Path Sum**       | Root-to-leaf   | Target reduction | O(n)   | O(h)  |
| **Path Sum II**    | Root-to-leaf   | Backtracking     | O(n)   | O(h)  |
| **Path Sum III**   | Any downward   | Prefix sum       | O(n)   | O(h)  |
| **Sum Numbers**    | Root-to-leaf   | Number building  | O(n)   | O(h)  |
| **Binary Numbers** | Root-to-leaf   | Bit manipulation | O(n)   | O(h)  |
| **Tree Paths**     | Root-to-leaf   | String building  | O(n)   | O(h)  |
| **Left Leaves**    | Specific nodes | Parent tracking  | O(n)   | O(h)  |
| **K Sum Paths**    | Any path       | Path + sliding   | O(n²) | O(h)  |

---
