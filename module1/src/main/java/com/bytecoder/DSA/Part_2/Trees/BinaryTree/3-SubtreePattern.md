### 7. Subtree Properties Pattern

**Use Cases:** Finding largest/smallest subtree with property, counting subtrees

**💡 Key Insight:** Use postorder to get info from children first. Return tuple with (property, size, min, max, etc). Track global best.

#### Template:

```python
def largestBSTSubtree(root):
    result = 0
  
    def dfs(node):
        nonlocal result
        if not node:
            return (True, 0, float('inf'), float('-inf'))
  
        l_valid, l_size, l_min, l_max = dfs(node.left)
        r_valid, r_size, r_min, r_max = dfs(node.right)
  
        if l_valid and r_valid and l_max < node.val < r_min:
            size = l_size + r_size + 1
            result = max(result, size)
            return (True, size, 
                    min(l_min, node.val), 
                    max(r_max, node.val))
  
        return (False, 0, 0, 0)
  
    dfs(root)
    return result
```

**Common Problems:**

- Largest BST Subtree (333)
- Maximum Sum BST (1373)
- Count Nodes Equal to Average (2265)
- Balanced Binary Tree (110)

### **2. Maximum Path Sum**

given a binary tree . find the max path sum. the path may start and end at any node in the tree.

```java
int maxPathSum(Node<T> root) {
    AtomicInteger maxSum = new AtomicInteger(Integer.MIN_VALUE);

    int dfs(Node<T> node) {
        if (node == null) return 0;

        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);

        // Update maxSum with the path passing through the current node
        maxSum.set(Math.max(maxSum.get(), left + right + node.data));

        // Return the maximum path sum including the current node
        return Math.max(left, right) + node.data;
    }

    dfs(root);
    return maxSum.get();
}
```

### **9. Find Diameter of Binary Tree**

```java
int diameterOfBinaryTree(Node<T> root) {
    AtomicInteger diameter = new AtomicInteger(0);

    int height(Node<T> node) {
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        diameter.set(Math.max(diameter.get(), leftHeight + rightHeight));
        return Math.max(leftHeight, rightHeight) + 1;
    }

    height(root);
    return diameter.get();
}

```

### **4. Diameter of a Tree**

The diameter is the longest path between any two nodes in the tree. Use a recursive DFS to calculate the height of the tree while keeping track of the diameter.

```java
class Result {
    int diameter = 0;
}

int diameterOfTree(Node root) {
    Result result = new Result();
    height(root, result);
    return result.diameter;
}

int height(Node node, Result result) {
    if (node == null) return 0;

    int leftHeight = height(node.left, result);
    int rightHeight = height(node.right, result);

    // Update the diameter at this node
    result.diameter = Math.max(result.diameter, leftHeight + rightHeight);

    // Return the height of the current node
    return 1 + Math.max(leftHeight, rightHeight);
}
```

**Time Complexity**: \(O(n)\), where \(n\) is the number of nodes.
**Space Complexity**: \(O(h)\), where \(h\) is the height of the tree (due to recursion stack).

---

### FAANG Question 1: Check if tree is balanced

```java
    // Time: O(n), Space: O(h) where h is height
    public boolean isBalanced() {
        return checkBalance(root) != -1;
    }

    private int checkBalance(Node node) {
        if (node == null) return 0;
  
        int leftHeight = checkBalance(node.getLeftChild());
        if (leftHeight == -1) return -1;
  
        int rightHeight = checkBalance(node.getRightChild());
        if (rightHeight == -1) return -1;
  
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
  
        return Math.max(leftHeight, rightHeight) + 1;
    }

```



### 15. Binary Tree Longest Consecutive (LC 298)

**Difficulty:** Medium | **Pattern:** DFS

Find longest consecutive sequence path.

**Time:** O(n) | **Space:** O(h)


```java

    // FAANG Question 4: Maximum Path Sum
    // Time: O(n), Space: O(h)
    private int maxSum;
  
    public int maxPathSum() {
        maxSum = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxSum;
    }

    private int maxPathSumHelper(Node node) {
        if (node == null) return 0;
  
        int leftGain = Math.max(maxPathSumHelper(node.getLeftChild()), 0);
        int rightGain = Math.max(maxPathSumHelper(node.getRightChild()), 0);
  
        maxSum = Math.max(maxSum, node.getData() + leftGain + rightGain);
  
        return node.getData() + Math.max(leftGain, rightGain);
    }




    // FAANG Question 6: Diameter of Binary Tree -> Q2 - max width of BT (level order )
    // Time: O(n), Space: O(h)
    private int maxDiameter;
  
    public int diameterOfBinaryTree() {
        maxDiameter = 0;
        calculateHeight(root);
        return maxDiameter;
    }

    private int calculateHeight(Node node) {
        if (node == null) return 0;
  
        int leftHeight = calculateHeight(node.getLeftChild());
        int rightHeight = calculateHeight(node.getRightChild());
  
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
  
        return Math.max(leftHeight, rightHeight) + 1;
    }


```
