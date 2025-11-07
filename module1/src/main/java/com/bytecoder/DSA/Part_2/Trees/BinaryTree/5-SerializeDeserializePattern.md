# Serialize/Deserialize Pattern

**Use Cases:** Encoding tree structure, persistence, transmission, tree reconstruction, string representation

**💡 Key Insight:**

* **Preorder with null markers:** Most compact, natural recursion
* **Level-order (BFS):** Preserves level structure, intuitive
* **String with brackets:** Human-readable, parentheses show structure
* **Always need null markers** to reconstruct tree unambiguously

**Common LeetCode Problems:**

* Serialize and Deserialize Binary Tree (LC 297)
* Construct Binary Tree from String (LC 536)
* Construct String from Binary Tree (LC 606)
* Serialize and Deserialize BST (LC 449)
* Construct from Preorder & Inorder (LC 105)
* Construct BST from Preorder (LC 1008)

---

## **1. Serialize and Deserialize Binary Tree (LC 297)**

Encode tree to string and decode string back to tree.

### Approach 1: Preorder Traversal (Most Common)

```python
from typing import Optional
from collections import deque

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Codec:
    """
    Serialize/Deserialize using preorder traversal.
    Time: O(n) for both, Space: O(n)
    """
  
    def serialize(self, root: Optional[TreeNode]) -> str:
        """Encodes a tree to a single string."""
        result = []
    
        def preorder(node: Optional[TreeNode]) -> None:
            if not node:
                result.append("null")
                return
        
            result.append(str(node.val))
            preorder(node.left)
            preorder(node.right)
    
        preorder(root)
        return ",".join(result)
  
    def deserialize(self, data: str) -> Optional[TreeNode]:
        """Decodes your encoded data to tree."""
        values = deque(data.split(","))
    
        def build() -> Optional[TreeNode]:
            val = values.popleft()
        
            if val == "null":
                return None
        
            node = TreeNode(int(val))
            node.left = build()
            node.right = build()
        
            return node
    
        return build()
```

### Approach 2: Level-Order Traversal (BFS)

```python
class CodecBFS:
    """
    Serialize/Deserialize using level-order traversal.
    Time: O(n) for both, Space: O(w) where w is max width
    """
  
    def serialize(self, root: Optional[TreeNode]) -> str:
        """Encodes tree using BFS."""
        if not root:
            return "null"
    
        result = []
        queue = deque([root])
    
        while queue:
            node = queue.popleft()
        
            if not node:
                result.append("null")
            else:
                result.append(str(node.val))
                queue.append(node.left)
                queue.append(node.right)
    
        return ",".join(result)
  
    def deserialize(self, data: str) -> Optional[TreeNode]:
        """Decodes BFS encoded data to tree."""
        if data == "null":
            return None
    
        values = data.split(",")
        root = TreeNode(int(values[0]))
        queue = deque([root])
        i = 1
    
        while queue and i < len(values):
            node = queue.popleft()
        
            # Process left child
            if values[i] != "null":
                node.left = TreeNode(int(values[i]))
                queue.append(node.left)
            i += 1
        
            # Process right child
            if i < len(values) and values[i] != "null":
                node.right = TreeNode(int(values[i]))
                queue.append(node.right)
            i += 1
    
        return root
```

### Approach 3: Compact Preorder (Space-Optimized)

```python
class CodecCompact:
    """
    More compact serialization without trailing nulls.
    Time: O(n), Space: O(n)
    """
  
    def serialize(self, root: Optional[TreeNode]) -> str:
        """Serialize with minimal null markers."""
        def preorder(node: Optional[TreeNode]) -> str:
            if not node:
                return "#"
        
            return f"{node.val},{preorder(node.left)},{preorder(node.right)}"
    
        return preorder(root)
  
    def deserialize(self, data: str) -> Optional[TreeNode]:
        """Deserialize compact format."""
        def build(values: list[str]) -> Optional[TreeNode]:
            val = values.pop(0)
        
            if val == "#":
                return None
        
            node = TreeNode(int(val))
            node.left = build(values)
            node.right = build(values)
        
            return node
    
        return build(data.split(","))
```

---

## **2. Construct Binary Tree from String (LC 536)**

Build tree from string with parentheses: `"4(2(3)(1))(6(5))"`.

### Approach: Recursive Parsing with Stack

```python
def str2tree(s: str) -> Optional[TreeNode]:
    """
    Construct tree from string with parentheses.
    Format: value(left_subtree)(right_subtree)
    Time: O(n), Space: O(h)
  
    Examples:
    "4(2(3)(1))(6(5))" -> Tree with root 4
    "-4(2(3)(1))(6(5)(7))" -> Handles negative numbers
    """
    if not s:
        return None
  
    def parse(s: str, index: int) -> tuple[Optional[TreeNode], int]:
        """
        Parse string starting at index.
        Returns: (TreeNode, next_index)
        """
        if index >= len(s):
            return None, index
    
        # Parse number (handle negative numbers)
        start = index
        if s[index] == '-':
            index += 1
    
        while index < len(s) and s[index].isdigit():
            index += 1
    
        node = TreeNode(int(s[start:index]))
    
        # Parse left subtree if exists
        if index < len(s) and s[index] == '(':
            index += 1  # Skip '('
            node.left, index = parse(s, index)
            index += 1  # Skip ')'
    
        # Parse right subtree if exists
        if index < len(s) and s[index] == '(':
            index += 1  # Skip '('
            node.right, index = parse(s, index)
            index += 1  # Skip ')'
    
        return node, index
  
    root, _ = parse(s, 0)
    return root
```

### Approach 2: Iterative with Stack

```python
def str2tree_iterative(s: str) -> Optional[TreeNode]:
    """
    Construct tree using stack (more Meta-style).
    Time: O(n), Space: O(h)
    """
    if not s:
        return None
  
    stack = []
    i = 0
  
    while i < len(s):
        # Parse number
        if s[i].isdigit() or s[i] == '-':
            start = i
            if s[i] == '-':
                i += 1
            while i < len(s) and s[i].isdigit():
                i += 1
        
            node = TreeNode(int(s[start:i]))
        
            # Attach to parent if exists
            if stack:
                parent = stack[-1]
                if not parent.left:
                    parent.left = node
                else:
                    parent.right = node
        
            stack.append(node)
    
        elif s[i] == ')':
            # Pop when closing parenthesis
            stack.pop()
            i += 1
        else:
            # Skip '('
            i += 1
  
    return stack[0] if stack else None
```

---

## **3. Construct String from Binary Tree (LC 606)**

Convert tree to string with parentheses (reverse of LC 536).

### Approach: Preorder with Smart Parentheses

```python
def tree2str(root: Optional[TreeNode]) -> str:
    """
    Convert tree to string representation.
    Omit empty parentheses when possible.
    Time: O(n), Space: O(h)
  
    Rules:
    - Always include value
    - Omit () for both children null
    - Include () for left if right exists
    - Include () for right if it exists
    """
    if not root:
        return ""
  
    result = str(root.val)
  
    # Case 1: Both children are null
    if not root.left and not root.right:
        return result
  
    # Case 2: Left exists or right exists (need left parentheses)
    result += f"({tree2str(root.left)})"
  
    # Case 3: Right exists
    if root.right:
        result += f"({tree2str(root.right)})"
  
    return result
```

### Approach 2: Iterative with Stack

```python
def tree2str_iterative(root: Optional[TreeNode]) -> str:
    """
    Convert tree to string iteratively.
    Time: O(n), Space: O(h)
    """
    if not root:
        return ""
  
    result = []
    stack = [root]
    visited = set()
  
    while stack:
        node = stack[-1]
    
        if node in visited:
            stack.pop()
            result.append(")")
        else:
            visited.add(node)
            result.append(f"({node.val}")
        
            # Add right child
            if not node.left and node.right:
                result.append("()")
        
            if node.right:
                stack.append(node.right)
            if node.left:
                stack.append(node.left)
  
    # Remove outer parentheses
    return "".join(result)[1:-1]
```

---

## **4. Serialize and Deserialize BST (LC 449)**

Optimized for BST - no null markers needed!

### Approach: Preorder Only (BST Property)

```python
class CodecBST:
    """
    BST serialization without null markers.
    Uses BST property: left < root < right
    Time: O(n), Space: O(n)
    """
  
    def serialize(self, root: Optional[TreeNode]) -> str:
        """Preorder traversal, no nulls needed."""
        result = []
    
        def preorder(node: Optional[TreeNode]) -> None:
            if not node:
                return
            result.append(str(node.val))
            preorder(node.left)
            preorder(node.right)
    
        preorder(root)
        return ",".join(result)
  
    def deserialize(self, data: str) -> Optional[TreeNode]:
        """Reconstruct BST using value ranges."""
        if not data:
            return None
    
        values = deque(map(int, data.split(",")))
    
        def build(min_val: float, max_val: float) -> Optional[TreeNode]:
            if not values or values[0] < min_val or values[0] > max_val:
                return None
        
            val = values.popleft()
            node = TreeNode(val)
            node.left = build(min_val, val)
            node.right = build(val, max_val)
        
            return node
    
        return build(float('-inf'), float('inf'))
```

---

## **5. Construct Binary Tree from Preorder and Inorder (LC 105)**

Reconstruct tree from two traversals.

### Approach: Divide and Conquer with HashMap

```python
def build_tree(preorder: list[int], inorder: list[int]) -> Optional[TreeNode]:
    """
    Construct tree from preorder and inorder traversals.
    Time: O(n), Space: O(n)
    """
    # Map value to index in inorder for O(1) lookup
    inorder_map = {val: i for i, val in enumerate(inorder)}
    pre_idx = 0
  
    def build(in_left: int, in_right: int) -> Optional[TreeNode]:
        nonlocal pre_idx
    
        if in_left > in_right:
            return None
    
        # Root is first element in preorder
        root_val = preorder[pre_idx]
        root = TreeNode(root_val)
        pre_idx += 1
    
        # Find root in inorder to split left/right subtrees
        in_idx = inorder_map[root_val]
    
        # Build left subtree (all elements left of root in inorder)
        root.left = build(in_left, in_idx - 1)
    
        # Build right subtree (all elements right of root in inorder)
        root.right = build(in_idx + 1, in_right)
    
        return root
  
    return build(0, len(inorder) - 1)
```

---

## **6. Construct BST from Preorder Traversal (LC 1008)**

Build BST from preorder only (no inorder needed).

### Approach: Use BST Property with Bounds

```python
def bst_from_preorder(preorder: list[int]) -> Optional[TreeNode]:
    """
    Construct BST from preorder traversal only.
    Time: O(n), Space: O(h)
    """
    idx = 0
  
    def build(min_val: float, max_val: float) -> Optional[TreeNode]:
        nonlocal idx
    
        if idx >= len(preorder):
            return None
    
        val = preorder[idx]
    
        # Value must be within valid range for this subtree
        if val < min_val or val > max_val:
            return None
    
        idx += 1
        node = TreeNode(val)
    
        # Left subtree: values < current value
        node.left = build(min_val, val)
    
        # Right subtree: values > current value
        node.right = build(val, max_val)
    
        return node
  
    return build(float('-inf'), float('inf'))
```

---

## **Universal Serialize/Deserialize Template**

```python
class CodecTemplate:
    """
    Generic template for tree serialization.
    """
  
    def serialize(self, root: Optional[TreeNode]) -> str:
        """
        Choose traversal method:
        1. Preorder: Natural recursion, compact
        2. Level-order: Preserves levels, intuitive
        3. Custom format: Problem-specific
        """
        result = []
    
        def traverse(node: Optional[TreeNode]) -> None:
            if not node:
                result.append("null")
                return
        
            # Preorder: root, left, right
            result.append(str(node.val))
            traverse(node.left)
            traverse(node.right)
    
        traverse(root)
        return ",".join(result)
  
    def deserialize(self, data: str) -> Optional[TreeNode]:
        """
        Reconstruct tree matching serialization order.
        Key: Use queue/list and pop in same order as serialization.
        """
        if not data or data == "null":
            return None
    
        values = deque(data.split(","))
    
        def build() -> Optional[TreeNode]:
            val = values.popleft()
        
            if val == "null":
                return None
        
            node = TreeNode(int(val))
            node.left = build()   # Must match serialize order
            node.right = build()
        
            return node
    
        return build()
```

---

## **Comparison Table**

| Method                     | Format                | Use Case              | Pros             | Cons             |
| -------------------------- | --------------------- | --------------------- | ---------------- | ---------------- |
| **Preorder + Nulls** | `1,2,null,null,3`   | General tree          | Compact, natural | Many nulls       |
| **Level-Order**      | `1,2,3,null,null,4` | Level structure       | Intuitive        | More nulls       |
| **Parentheses**      | `4(2)(3)`           | Human-readable        | Clear structure  | Parsing overhead |
| **BST Preorder**     | `2,1,3`             | BST only              | No nulls needed  | BST only         |
| **Pre + Inorder**    | Two arrays            | Unique reconstruction | No nulls         | Need both arrays |

---

## **Complete Example Usage**

```python
# Build sample tree:
#       1
#      / \
#     2   3
#        / \
#       4   5

root = TreeNode(1)
root.left = TreeNode(2)
root.right = TreeNode(3)
root.right.left = TreeNode(4)
root.right.right = TreeNode(5)

# Test different serialization methods
codec = Codec()
serialized = codec.serialize(root)
print(f"Preorder: {serialized}")
# Output: "1,2,null,null,3,4,null,null,5,null,null"

codec_bfs = CodecBFS()
serialized_bfs = codec_bfs.serialize(root)
print(f"Level-order: {serialized_bfs}")
# Output: "1,2,3,null,null,4,5"

tree_str = tree2str(root)
print(f"String format: {tree_str}")
# Output: "1(2)(3(4)(5))"

# Test deserialization
restored = codec.deserialize(serialized)
print(f"Restored: {codec.serialize(restored) == serialized}")
# Output: True
```

**Pro Tip for Meta:** Start with preorder serialization (most common), clearly explain the format, handle edge cases, then offer optimizations like BST-specific or level-order approaches based on the follow-up!
