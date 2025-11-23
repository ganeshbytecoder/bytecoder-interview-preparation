Covers  **DFS, BFS, Morris** , and  **important problem templates** .

---

# ✅ **1. DFS (Depth-First Search)**

Used for:

✔ Tree traversals

✔ Subtree computations

✔ Path problems

✔ Max path, diameter, sums, ancestors

### **General DFS Template (Python)**

```python
def dfs(node):
    if not node:
        return base_case

    # preorder
    left = dfs(node.left)

    # inorder
    right = dfs(node.right)

    # postorder

    return combine(left, right)
```

---

## 🔥 **1B. DFS Without Recursion (Python)**

```python
def dfs_iterative(root):
    if not root: 
        return
  
    stack = [root]

    while stack:
        node = stack.pop()
        print(node.val, end=" ")

        if node.right:
            stack.append(node.right)
        if node.left:
            stack.append(node.left)
```

---

# 🌿 **Problems Based on DFS**

---

## **📌 Root-to-Leaf Paths**

```python
def root_to_leaf_paths(root):
    result = []
    path = []

    def dfs(node):
        if not node:
            return

        path.append(node.val)

        if not node.left and not node.right:
            result.append(path.copy())
        else:
            dfs(node.left)
            dfs(node.right)

        path.pop()

    dfs(root)
    return result
```

---

## **📌 Sum of Root-to-Leaf Numbers**

LeetCode 129

```python
def sumNumbers(root):
    def dfs(node, curr):
        if not node:
            return 0
  
        curr = curr * 10 + node.val
  
        if not node.left and not node.right:
            return curr
  
        return dfs(node.left, curr) + dfs(node.right, curr)

    return dfs(root, 0)
```

---

## **📌 Kth Smallest in BST (Inorder)**

```python
def kthSmallest(root, k):
    stack = []
    curr = root

    while True:
        while curr:
            stack.append(curr)
            curr = curr.left
  
        curr = stack.pop()
        k -= 1
        if k == 0:
            return curr.val
  
        curr = curr.right
```

---

## **📌 Check If Path Sum Exists (Root → Leaf)**

LeetCode 112

```python
def hasPathSum(root, target):
    if not root:
        return False

    if not root.left and not root.right:
        return target == root.val

    return (
        hasPathSum(root.left, target - root.val) or 
        hasPathSum(root.right, target - root.val)
    )
```

---

## **📌 Vertical Sum of Binary Tree**

```python
from collections import defaultdict

def vertical_sum(root):
    column_sum = defaultdict(int)

    def dfs(node, col):
        if not node:
            return
  
        column_sum[col] += node.val
        dfs(node.left, col - 1)
        dfs(node.right, col + 1)

    dfs(root, 0)
    return [column_sum[x] for x in sorted(column_sum)]
```

---

| Problem Type                 | Pattern                                | Time        | Works For     |
| ---------------------------- | -------------------------------------- | ----------- | ------------- |
| Root→Leaf only              | Simple DFS                             | O(n)        | leaf paths    |
| Root→Any node               | DFS                                    | O(n)        | downward only |
| Any node→downward           | Path Sum III                           | O(n) prefix | downward only |
| **Any Node→Any Node** | **Prefix sum DFS (this method)** |             |               |

## Python Template for “Any Node to Any Node Path Sum for a Given Target

```python
class Solution:
    def pathSumAnyToAny(self, root, target):
        self.count = 0

        def dfs(node):
            if not node:
                return []

            # get all downward sums from children
            left_paths = dfs(node.left)
            right_paths = dfs(node.right)

            # paths starting AT node
            new_paths = [node.val]

            # downward paths from left
            for s in left_paths:
                new_paths.append(node.val + s)

            # downward paths from right
            for s in right_paths:
                new_paths.append(node.val + s)

            # count paths ending at this node
            for s in new_paths:
                if s == target:
                    self.count += 1

            # Now check "through paths": left → node → right
            for L in left_paths:
                for R in right_paths:
                    if L + node.val + R == target:
                        self.count += 1

            return new_paths

        dfs(root)
        return self.count

```

## **Other DFS-Driven Problems**

* Max Depth
* Min Depth
* Node exists
* Diameter of Tree (543)
* Path Sum II (113)
* Path Sum III (437)https://chatgpt.com/share/692202df-6db0-8006-8912-80ec35174f51
* Maximum Path Sum (124)
* Longest root-to-leaf path sum
* Kth ancestor

If you want, I can also add templates for all of these.

---

# 🟦 **2. BFS (Level-Order Search)**

Used for:

✔ Level order

✔ Zigzag

✔ Left & right view

✔ Minimum depth

✔ Maximum level sum

✔ Deepest node

---

## **General BFS Template**

```python
from collections import deque

def bfs(root):
    if not root:
        return []
  
    q = deque([root])
    result = []

    while q:
        size = len(q)
        level = []

        for _ in range(size):
            node = q.popleft()
            level.append(node.val)

            if node.left:
                q.append(node.left)
            if node.right:
                q.append(node.right)

        result.append(level)

    return result
```

---

## **📌 Height of Tree (BFS)**

```python
from collections import deque

def height(root):
    if not root:
        return 0
  
    q = deque([root])
    h = 0

    while q:
        for _ in range(len(q)):
            node = q.popleft()
            if node.left: q.append(node.left)
            if node.right: q.append(node.right)
        h += 1
  
    return h
```

---

## **📌 Level with Maximum Sum**

```python
from collections import deque

def level_with_max_sum(root):
    if not root: return -1

    q = deque([root])
    max_sum = float('-inf')
    max_level = 0
    level = 0

    while q:
        curr_sum = 0

        for _ in range(len(q)):
            node = q.popleft()
            curr_sum += node.val
            if node.left: q.append(node.left)
            if node.right: q.append(node.right)
  
        if curr_sum > max_sum:
            max_sum = curr_sum
            max_level = level
  
        level += 1

    return max_level
```

---

## **📌 Deepest Node**

```python
def deepest_node(root):
    from collections import deque
    q = deque([root])
    node = None

    while q:
        node = q.popleft()
        if node.left: q.append(node.left)
        if node.right: q.append(node.right)

    return node
```

---

## **📌 Zigzag Level Order Traversal**

```python
from collections import deque

def zigzag(root):
    if not root: return []
    q = deque([root])
    result = []
    left_to_right = True

    while q:
        level = deque()
        for _ in range(len(q)):
            node = q.popleft()

            if left_to_right:
                level.append(node.val)
            else:
                level.appendleft(node.val)

            if node.left: q.append(node.left)
            if node.right: q.append(node.right)

        result.append(list(level))
        left_to_right = not left_to_right

    return result
```

---

# 🟩 **3. Diagonal Traversal**

```python
from collections import deque

class Solution:
    def diagonal(self, root):
        if not root:
            return []

        q = deque([(root, 0)])
        diag = {}  # dictionary: index → list of values

        while q:
            node, index = q.popleft()

            # travel rightwards on the same diagonal
            while node:
                if index not in diag:
                    diag[index] = []
                diag[index].append(node.data)

                # left child → next diagonal
                if node.left:
                    q.append((node.left, index + 1))

                # move right → same diagonal
                node = node.right

        # collect result in order of diagonal indices
        result = []
        for i in sorted(diag.keys()):
            result.extend(diag[i])

        return result

```

---

# 🟫 **4. Boundary Traversal Template**

```python
def boundary_traversal(root):
    if not root:
        return []
  
    result = []

    def is_leaf(node):
        return not node.left and not node.right

    # left boundary (excluding leaves)
    def add_left(node):
        while node:
            if not is_leaf(node):
                result.append(node.val)
            node = node.left if node.left else node.right

    # leaves
    def add_leaves(node):
        if not node:
            return
        if is_leaf(node):
            result.append(node.val)
        add_leaves(node.left)
        add_leaves(node.right)

    # right boundary (store reverse)
    def add_right(node):
        temp = []
        while node:
            if not is_leaf(node):
                temp.append(node.val)
            node = node.right if node.right else node.left
        result.extend(temp[::-1])

    if not is_leaf(root):
        result.append(root.val)

    add_left(root.left)
    add_leaves(root)
    add_right(root.right)

    return result
```

---

# 🟧 **5. Morris Traversal (O(1) Space)**

**Inorder Traversal without stack/recursion**

```python
def morris_inorder(root):
    result = []
    curr = root

    while curr:
        if not curr.left:
            result.append(curr.val)
            curr = curr.right
        else:
            pred = curr.left
            while pred.right and pred.right != curr:
                pred = pred.right

            if not pred.right:
                pred.right = curr
                curr = curr.left
            else:
                pred.right = None
                result.append(curr.val)
                curr = curr.right
  
    return result
```

---
