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
