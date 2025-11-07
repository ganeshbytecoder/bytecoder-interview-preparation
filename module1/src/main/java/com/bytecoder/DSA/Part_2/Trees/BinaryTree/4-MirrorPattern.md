# Tree Mirror Pattern

**Use Cases:** Inverting trees, checking symmetry, tree isomorphism, structural comparison, mirror validation

**💡 Key Insight:**

* **Invert/Mirror:** Swap left and right children recursively
* **Check Mirror:** Compare one tree's left with another's right
* **Symmetric:** A tree is symmetric if left subtree mirrors right subtree
* **Isomorphic:** Trees can match with or without flipping subtrees

**Common LeetCode Problems:**

* Invert Binary Tree (LC 226)
* Symmetric Tree (LC 101)
* Same Tree (LC 100)
* Flip Equivalent Binary Trees (LC 951) - Isomorphism

---

## **1. Invert Binary Tree (LC 226)**

Invert/mirror a binary tree by swapping all left and right children.

### Approach 1: Recursive (Most Elegant)

```python
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def invert_tree(root: Optional[TreeNode]) -> Optional[TreeNode]:
    """
    Invert binary tree recursively.
    Time: O(n), Space: O(h)
    """
    if not root:
        return None
  
    # Swap children while recursively inverting them
    root.left, root.right = invert_tree(root.right), invert_tree(root.left)
  
    return root
```

### Approach 3: Iterative (BFS)

```python
from collections import deque

def invert_tree_iterative(root: Optional[TreeNode]) -> Optional[TreeNode]:
    """
    Invert tree using level-order traversal.
    Time: O(n), Space: O(w) where w is max width
    """
    if not root:
        return None
  
    queue = deque([root])
  
    while queue:
        node = queue.popleft()
    
        # Swap children
        node.left, node.right = node.right, node.left
    
        if node.left:
            queue.append(node.left)
        if node.right:
            queue.append(node.right)
  
    return root
```

### Approach 4: Iterative (DFS with Stack)

```python
def invert_tree_dfs(root: Optional[TreeNode]) -> Optional[TreeNode]:
    """
    Invert tree using DFS with stack.
    Time: O(n), Space: O(h)
    """
    if not root:
        return None
  
    stack = [root]
  
    while stack:
        node = stack.pop()
    
        # Swap children
        node.left, node.right = node.right, node.left
    
        if node.left:
            stack.append(node.left)
        if node.right:
            stack.append(node.right)
  
    return root
```

---

## **2. Symmetric Tree (LC 101)**

Check if a tree is a mirror of itself (symmetric around center).

### Approach: Recursive Mirror Check

```python
def is_symmetric(root: Optional[TreeNode]) -> bool:
    """
    Check if tree is symmetric (mirror of itself).
    Time: O(n), Space: O(h)
    """
    def is_mirror(left: Optional[TreeNode], right: Optional[TreeNode]) -> bool:
        """Check if two subtrees are mirrors of each other."""
        # Both null - symmetric
        if not left and not right:
            return True
    
        # One null - not symmetric
        if not left or not right:
            return False
    
        # Check: same value + left mirrors right + right mirrors left
        return (left.val == right.val and
                is_mirror(left.left, right.right) and
                is_mirror(left.right, right.left))
  
    if not root:
        return True
  
    return is_mirror(root.left, root.right)
```

### Approach 2: Iterative (BFS with Queue)

```python
def is_symmetric_iterative(root: Optional[TreeNode]) -> bool:
    """
    Check symmetry using iterative BFS.
    Time: O(n), Space: O(w)
    """
    if not root:
        return True
  
    queue = deque([(root.left, root.right)])
  
    while queue:
        left, right = queue.popleft()
    
        if not left and not right:
            continue
    
        if not left or not right:
            return False
    
        if left.val != right.val:
            return False
    
        # Add pairs to compare in mirror order
        queue.append((left.left, right.right))
        queue.append((left.right, right.left))
  
    return True
```

---

## **3. Same Tree (LC 100)**

Check if two trees are structurally identical with same values.

### Approach: Recursive Comparison

```python
def is_same_tree(p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
    """
    Check if two trees are identical.
    Time: O(min(n,m)), Space: O(min(h1,h2))
    """
    # Both null - same
    if not p and not q:
        return True
  
    # One null - different
    if not p or not q:
        return False
  
    # Check: same value + same left subtree + same right subtree
    return (p.val == q.val and
            is_same_tree(p.left, q.left) and
            is_same_tree(p.right, q.right))
```

### Approach 2: Iterative (BFS)

```python
def is_same_tree_iterative(p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
    """
    Check if trees are identical using BFS.
    Time: O(n), Space: O(w)
    """
    queue = deque([(p, q)])
  
    while queue:
        node1, node2 = queue.popleft()
    
        if not node1 and not node2:
            continue
    
        if not node1 or not node2:
            return False
    
        if node1.val != node2.val:
            return False
    
        queue.append((node1.left, node2.left))
        queue.append((node1.right, node2.right))
  
    return True
```

---

## **4. Check if Two Trees Are Structurally Same**

Check only structure, ignoring values.

```python
def are_structurally_same(root1: Optional[TreeNode], 
                         root2: Optional[TreeNode]) -> bool:
    """
    Check if two trees have same structure (ignore values).
    Time: O(min(n,m)), Space: O(min(h1,h2))
    """
    # Both null - same structure
    if not root1 and not root2:
        return True
  
    # One null - different structure
    if not root1 or not root2:
        return False
  
    # Check structure of subtrees (ignore values)
    return (are_structurally_same(root1.left, root2.left) and
            are_structurally_same(root1.right, root2.right))
```

---

## **5. Check if Two Trees Are Mirrors**

Check if two separate trees are mirrors of each other.

```python
def are_mirrors(root1: Optional[TreeNode], 
                root2: Optional[TreeNode]) -> bool:
    """
    Check if two trees are mirrors of each other.
    Time: O(min(n,m)), Space: O(min(h1,h2))
    """
    # Both null - mirrors
    if not root1 and not root2:
        return True
  
    # One null - not mirrors
    if not root1 or not root2:
        return False
  
    # Check: same value + left mirrors right + right mirrors left
    return (root1.val == root2.val and
            are_mirrors(root1.left, root2.right) and
            are_mirrors(root1.right, root2.left))
```

---

## **6. Flip Equivalent Binary Trees / Tree Isomorphism (LC 951)**

Check if two trees can be made identical by flipping children at any node.

### Approach: Check Both Flip and No-Flip Cases

```python
def flip_equiv(root1: Optional[TreeNode], 
               root2: Optional[TreeNode]) -> bool:
    """
    Check if trees are flip equivalent (isomorphic).
    Time: O(min(n,m)), Space: O(min(h1,h2))
    """
    # Both null - equivalent
    if not root1 and not root2:
        return True
  
    # One null or different values - not equivalent
    if not root1 or not root2 or root1.val != root2.val:
        return False
  
    # Case 1: Subtrees not flipped (normal comparison)
    no_flip = (flip_equiv(root1.left, root2.left) and
               flip_equiv(root1.right, root2.right))
  
    # Case 2: Subtrees flipped (cross comparison)
    flipped = (flip_equiv(root1.left, root2.right) and
               flip_equiv(root1.right, root2.left))
  
    return no_flip or flipped
```

### Alternative: More Readable Version

```python
def is_isomorphic(n1: Optional[TreeNode], 
                  n2: Optional[TreeNode]) -> bool:
    """
    Tree isomorphism with clear case separation.
    Time: O(min(n,m)), Space: O(min(h1,h2))
    """
    # Base cases
    if not n1 and not n2:
        return True
  
    if not n1 or not n2:
        return False
  
    if n1.val != n2.val:
        return False
  
    # Try both configurations
    # Configuration 1: Children match directly
    direct_match = (is_isomorphic(n1.left, n2.left) and 
                   is_isomorphic(n1.right, n2.right))
  
    # Configuration 2: Children are flipped
    flipped_match = (is_isomorphic(n1.left, n2.right) and 
                    is_isomorphic(n1.right, n2.left))
  
    return direct_match or flipped_match
```

---

## **7. Create Mirror Tree**

Create a new tree that is the mirror of the original.

```python
def create_mirror(root: Optional[TreeNode]) -> Optional[TreeNode]:
    """
    Create a new tree that is mirror of original.
    Time: O(n), Space: O(n) for new tree + O(h) for recursion
    """
    if not root:
        return None
  
    # Create new node
    mirror = TreeNode(root.val)
  
    # Recursively create mirrored subtrees
    mirror.left = create_mirror(root.right)
    mirror.right = create_mirror(root.left)
  
    return mirror
```


---

## **Comparison Table**

| Problem                   | Comparison Type            | Returns                |
| ------------------------- | -------------------------- | ---------------------- |
| **Same Tree**       | `left-left, right-right` | `case1`              |
| **Mirror Trees**    | `left-right, right-left` | `case2`              |
| **Symmetric Tree**  | `left-right, right-left` | `case2`(single tree) |
| **Isomorphic**      | Both                       | `case1 OR case2`     |
| **Invert Tree**     | Swap                       | Modified tree          |
| **Structural Same** | Ignore values              | Structure only         |

---
