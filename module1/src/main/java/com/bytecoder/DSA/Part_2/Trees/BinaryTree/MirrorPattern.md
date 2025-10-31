* https://leetcode.com/problems/invert-binary-tree/description/

```python
class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
  
        if root is None:
            return None
        root.left, root.right = self.invertTree(root.right), self.invertTree(root.left)
        return root
```

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {

        if(root == null){
            return null;
        }
        TreeNode left=invertTree(root.left);
        TreeNode right =invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
```


### **8. Check if Two Trees Are Structurally Same**

```java
boolean areStructurallySameTree(Node<T> root1, Node<T> root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null) return false;
    return areStructurallySameTree(root1.left, root2.left) &&
           areStructurallySameTree(root1.right, root2.right);
}
```

### 35. **Tree Isomorphism Problem**

- Check if two trees can be transformed into each other by flipping subtrees.

````java

public class TreeIsomorphism {

    // Function to check if two trees are isomorphic
    public static boolean isIsomorphic(Node n1, Node n2) {
        // Both nodes are null, trees are isomorphic
        if (n1 == null && n2 == null) {
            return true;
        }

        // Exactly one of the nodes is null, trees are not isomorphic
        if (n1 == null || n2 == null) {
            return false;
        }

        // Data of the nodes doesn't match, trees are not isomorphic
        if (n1.data != n2.data) {
            return false;
        }

        // Two cases:
        // Case 1: Subtrees are not flipped
        // Case 2: Subtrees are flipped
        return (isIsomorphic(n1.left, n2.left) && isIsomorphic(n1.right, n2.right))
                || (isIsomorphic(n1.left, n2.right) && isIsomorphic(n1.right, n2.left));
    }
````


### **4. Convert Tree to Its Mirror**

- **Recursive Approach:** Swap the left and right children of each node recursively.
- **Iterative Approach:** Use level order traversal and swap children at each level.

```java
void mirrorOfBinaryTree(Node<T> root) {
    if (root == null) return;

    // Swap the left and right children
    Node<T> temp = root.left;
    root.left = root.right;
    root.right = temp;

    // Recur for left and right subtrees
    mirrorOfBinaryTree(root.left);
    mirrorOfBinaryTree(root.right);
}
```

---

### **5. Check If Two Trees Are Mirrors**

```java
boolean areMirrors(Node<T> root1, Node<T> root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null) return false;

    // Check if the data matches and the left subtree of one is the mirror of the right subtree of the other
    return root1.data.equals(root2.data) &&
           areMirrors(root1.left, root2.right) &&
           areMirrors(root1.right, root2.left);
}
```
