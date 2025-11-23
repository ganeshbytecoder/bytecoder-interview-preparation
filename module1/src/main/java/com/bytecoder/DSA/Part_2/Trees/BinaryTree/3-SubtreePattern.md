# Subtree Properties Pattern

**Use Cases:** Finding largest/smallest subtree with specific property, counting subtrees, validating subtree conditions, sum trees, balanced trees

**💡 Key Insight:**

* Use **postorder traversal** (left → right → root) to gather information from children first
* Return a tuple/object with multiple properties: `(is_valid, size, min, max, sum, height, etc.)`
* Track global result with `nonlocal` variable
* Make decisions at current node based on children's information

**Common LeetCode Problems:**

* Largest BST Subtree (LC 333)
* Maximum Sum BST in Binary Tree (LC 1373)
* Balanced Binary Tree (LC 110)
* Binary Tree Maximum Path Sum (LC 124)
* Diameter of Binary Tree (LC 543)
* Count Nodes Equal to Average of Subtree (LC 2265)

---

## **1. Balanced Binary Tree (LC 110)**

Check if tree is height-balanced (left and right subtree heights differ by at most 1).

### Approach: Bottom-Up Height Calculation

```python
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def is_balanced(root: Optional[TreeNode]) -> bool:
    """
    Check if binary tree is balanced.
    Time: O(n), Space: O(h)
    """
    def check_balance(node: Optional[TreeNode]) -> int:
        """Returns height if balanced, -1 if unbalanced."""
        if not node:
            return 0
  
        left_height = check_balance(node.left)
        if left_height == -1:
            return -1  # Left subtree unbalanced
  
        right_height = check_balance(node.right)
        if right_height == -1:
            return -1  # Right subtree unbalanced
  
        # Check if current node is balanced
        if abs(left_height - right_height) > 1:
            return -1
  
        return max(left_height, right_height) + 1
  
    return check_balance(root) != -1
```

---

## **2. Binary Tree Maximum Path Sum (LC 124)**

Find maximum path sum where path can start and end at any node.

### Approach: Postorder DFS with Global Max

```python
def max_path_sum(root: Optional[TreeNode]) -> int:
    """
    Find maximum path sum in binary tree.
    Time: O(n), Space: O(h)
    """
    max_sum = float('-inf')
  
    def dfs(node: Optional[TreeNode]) -> int:
        """Returns max path sum from node going down."""
        nonlocal max_sum
  
        if not node:
            return 0
  
        # Get max path sum from left and right (ignore negative paths)
        left_gain = max(dfs(node.left), 0)
        right_gain = max(dfs(node.right), 0)
  
        # Update global max with path through current node
        current_path_sum = node.val + left_gain + right_gain
        max_sum = max(max_sum, current_path_sum)
  
        # Return max path including current node (choose better branch)
        return node.val + max(left_gain, right_gain)
  
    dfs(root)
    return max_sum
```

**Key Insight:** At each node, we consider:

* Path through node: `node.val + left_gain + right_gain`
* Path continuing upward: `node.val + max(left_gain, right_gain)`

---

## **3. Diameter of Binary Tree (LC 543)**

Find longest path between any two nodes (number of edges).

### Approach: Track Max During Height Calculation

```python
def diameter_of_binary_tree(root: Optional[TreeNode]) -> int:
    """
    Find diameter (longest path between any two nodes).
    Time: O(n), Space: O(h)
    """
    diameter = 0
  
    def height(node: Optional[TreeNode]) -> int:
        """Returns height and updates diameter."""
        nonlocal diameter
  
        if not node:
            return 0
  
        left_height = height(node.left)
        right_height = height(node.right)
  
        # Diameter through current node
        diameter = max(diameter, left_height + right_height)
  
        return max(left_height, right_height) + 1
  
    height(root)
    return diameter
```

**Alternative: Return Tuple**

```python
def diameter_of_binary_tree_v2(root: Optional[TreeNode]) -> int:
    """Returns (height, diameter) tuple."""
  
    def dfs(node: Optional[TreeNode]) -> tuple[int, int]:
        if not node:
            return (0, 0)
  
        left_height, left_diameter = dfs(node.left)
        right_height, right_diameter = dfs(node.right)
  
        current_height = max(left_height, right_height) + 1
        current_diameter = max(
            left_height + right_height,  # Through current node
            left_diameter,                # In left subtree
            right_diameter                # In right subtree
        )
  
        return (current_height, current_diameter)
  
    return dfs(root)[1]
```

---

## **4. Largest BST Subtree (LC 333)**

Find size of largest BST subtree.

### Approach: Return (is_valid, size, min, max) Tuple

```python
def largest_bst_subtree(root: Optional[TreeNode]) -> int:
    """
    Find size of largest BST subtree.
    Time: O(n), Space: O(h)
    """
    result = 0
  
    def dfs(node: Optional[TreeNode]) -> tuple[bool, int, int, int]:
        """
        Returns: (is_bst, size, min_val, max_val)
        """
        nonlocal result
  
        if not node:
            return (True, 0, float('inf'), float('-inf'))
  
        left_is_bst, left_size, left_min, left_max = dfs(node.left)
        right_is_bst, right_size, right_min, right_max = dfs(node.right)
  
        # Check if current subtree is BST
        if (left_is_bst and right_is_bst and 
            left_max < node.val < right_min):
  
            size = left_size + right_size + 1
            result = max(result, size)
  
            return (
                True,
                size,
                min(left_min, node.val),  # Min in subtree
                max(right_max, node.val)  # Max in subtree
            )
  
        # Not a BST
        return (False, 0, 0, 0)
  
    dfs(root)
    return result
```

---

## **5. Maximum Sum BST in Binary Tree (LC 1373)**

Find maximum sum of values in any BST subtree.

### Approach: Combine BST Validation + Sum Tracking

```python
def max_sum_bst(root: Optional[TreeNode]) -> int:
    """
    Find maximum sum of any BST subtree.
    Time: O(n), Space: O(h)
    """
    max_sum = 0
  
    def dfs(node: Optional[TreeNode]) -> tuple[bool, int, int, int]:
        """
        Returns: (is_bst, subtree_sum, min_val, max_val)
        """
        nonlocal max_sum
  
        if not node:
            return (True, 0, float('inf'), float('-inf'))
  
        left_is_bst, left_sum, left_min, left_max = dfs(node.left)
        right_is_bst, right_sum, right_min, right_max = dfs(node.right)
  
        # Check if current subtree is BST
        if (left_is_bst and right_is_bst and 
            left_max < node.val < right_min):
  
            current_sum = left_sum + right_sum + node.val
            max_sum = max(max_sum, current_sum)
  
            return (
                True,
                current_sum,
                min(left_min, node.val),
                max(right_max, node.val)
            )
  
        # Not a BST
        return (False, 0, 0, 0)
  
    dfs(root)
    return max_sum
```

---

## **6. Count Nodes Equal to Average of Subtree (LC 2265)**

Count nodes where value equals average of its subtree.

### Approach: Return (sum, count) Tuple

```python
def average_of_subtree(root: Optional[TreeNode]) -> int:
    """
    Count nodes equal to average of their subtree.
    Time: O(n), Space: O(h)
    """
    result = 0
  
    def dfs(node: Optional[TreeNode]) -> tuple[int, int]:
        """
        Returns: (sum_of_subtree, count_of_nodes)
        """
        nonlocal result
  
        if not node:
            return (0, 0)
  
        left_sum, left_count = dfs(node.left)
        right_sum, right_count = dfs(node.right)
  
        current_sum = left_sum + right_sum + node.val
        current_count = left_count + right_count + 1
  
        # Check if current node equals average
        if node.val == current_sum // current_count:
            result += 1
  
        return (current_sum, current_count)
  
    dfs(root)
    return result
```

---

## **7. Check if Binary Tree is Sum Tree**

Verify if each node's value equals sum of its left and right subtrees.

### Approach: Postorder with Sum Validation

```python
def is_sum_tree(root: Optional[TreeNode]) -> bool:
    """
    Check if tree is a sum tree.
    Time: O(n), Space: O(h)
    """
    def dfs(node: Optional[TreeNode]) -> tuple[bool, int]:
        """
        Returns: (is_sum_tree, sum_of_subtree)
        """
        if not node:
            return (True, 0)
  
        # Leaf nodes are valid sum trees
        if not node.left and not node.right:
            return (True, node.val)
  
        left_valid, left_sum = dfs(node.left)
        right_valid, right_sum = dfs(node.right)
  
        # Check if current node satisfies sum property
        is_valid = (left_valid and right_valid and 
                   node.val == left_sum + right_sum)
  
        # Return sum including current node
        current_sum = left_sum + right_sum + node.val
  
        return (is_valid, current_sum)
  
    return dfs(root)[0]
```

---

## **8. Convert Binary Tree to Sum Tree**

Transform tree so each node contains sum of its original left and right subtrees.

### Approach: Postorder Modification

```python
def convert_to_sum_tree(root: Optional[TreeNode]) -> int:
    """
    Convert tree to sum tree and return sum of original tree.
    Time: O(n), Space: O(h)
    """
    def dfs(node: Optional[TreeNode]) -> int:
        """Returns sum of original values in subtree."""
        if not node:
            return 0
  
        # Store original value
        original_val = node.val
  
        # Get sums from subtrees
        left_sum = dfs(node.left)
        right_sum = dfs(node.right)
  
        # Update current node to sum of subtrees
        node.val = left_sum + right_sum
  
        # Return total sum including original value
        return original_val + left_sum + right_sum
  
    dfs(root)
    return root.val if root else 0
```

---

## **Universal Subtree Pattern Template**

```python
def subtree_property(root: Optional[TreeNode]) -> Any:
    """
    Generic template for subtree property problems.
    """
    # Global result tracker
    result = initial_value
  
    def dfs(node: Optional[TreeNode]) -> tuple:
        """
        Returns: (property1, property2, ..., propertyN)
        Common properties:
        - is_valid: bool
        - size/count: int
        - sum: int
        - min_val: int/float
        - max_val: int/float
        - height: int
        """
        nonlocal result
  
        # Base case
        if not node:
            return base_case_tuple
  
        # Postorder: Get info from children first
        left_info = dfs(node.left)
        right_info = dfs(node.right)
  
        # Process current node
        # 1. Extract info from children
        # 2. Validate property at current node
        # 3. Update global result if needed
        # 4. Compute info to return to parent
  
        current_info = compute_current_info(
            node, left_info, right_info
        )
  
        # Update result
        result = update_result(result, current_info)
  
        return current_info
  
    dfs(root)
    return result
```

---
