### **6. Print All Ancestors of a Node**

 give an algorithm for printing all the ancestors of a node in a binary tree

```java
boolean printAllAncestors(Node<T> root, Node<T> target) {
    if (root == null) return false;

    // If the target node is found in either left or right subtree
    if (root.left == target || root.right == target ||
        printAllAncestors(root.left, target) ||
        printAllAncestors(root.right, target)) {
        System.out.print(root.data + " ");
        return true;
    }

    return false;
}
```


### 9. Binary Tree Paths (LC 257)

**Difficulty:** Easy | **Pattern:** DFS, Backtracking

Return all root-to-leaf paths as strings.

**Time:** O(n) | **Space:** O(h)



## 6. Flatten Tree to Linked List (LC 114)

**Difficulty:** Medium | **Pattern:** DFS, Postorder

Flatten tree to linked list in preorder.

**Time:** O(n) | **Space:** O(h)

**Trick:** Process in reverse preorder, maintain prev pointer.
