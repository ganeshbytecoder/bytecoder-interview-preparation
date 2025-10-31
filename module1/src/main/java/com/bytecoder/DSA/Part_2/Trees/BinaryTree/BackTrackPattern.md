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
