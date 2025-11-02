### 4. Path Sum Pattern

**Use Cases:** Finding paths with specific sum, root-to-leaf problems

**💡 Key Insight:** Use DFS with running sum. For "any path" use prefix sum with hashmap. For "root-to-leaf" check leaf condition.

#### Root-to-Leaf Template:

```python
def hasPathSum(root, target):
    if not root:
        return False
  
    if not root.left and not root.right:
        return root.val == target
  
    return (hasPathSum(root.left, target - root.val) or
            hasPathSum(root.right, target - root.val))
```

#### Any Path (Prefix Sum) Template:

```python
def pathSum(root, target):
    def dfs(node, curr_sum):
        if not node:
            return 0
  
        curr_sum += node.val
        count = prefix_sums[curr_sum - target]
  
        prefix_sums[curr_sum] += 1
        count += dfs(node.left, curr_sum)
        count += dfs(node.right, curr_sum)
        prefix_sums[curr_sum] -= 1
  
        return count
  
    from collections import defaultdict
    prefix_sums = defaultdict(int)
    prefix_sums[0] = 1
    return dfs(root, 0)
```

**Common Problems:**

- Path Sum (112)
- Path Sum II (113)
- Path Sum III (437)
- Sum Root to Leaf Numbers (129)
- Binary Tree Maximum Path Sum (124)

### 9. Binary Tree Paths (LC 257)

**Difficulty:** Easy | **Pattern:** DFS, Backtracking

Return all root-to-leaf paths as strings.

**Time:** O(n) | **Space:** O(h)

### 10. Sum of Left Leaves (LC 404)

**Difficulty:** Easy | **Pattern:** DFS

Sum all left leaves (leaves that are left children).

**Time:** O(n) | **Space:** O(h)


### 14. Sum Root to Leaf Binary Numbers (LC 1022)

**Difficulty:** Easy | **Pattern:** DFS

Treat root-to-leaf paths as binary numbers, sum them.

**Time:** O(n) | **Space:** O(h)

**Formula:** val = (val << 1) | node.val
