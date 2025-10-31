# Tree -

a tree is a data structure similar to a linked list but instead of each node pointing simply to the next node in a linear fashion, each node
points to a number of nodes. it's non-linear and non-cyclic data structure. we will study binary tree, BST( Binary Search Tree) and Nary Tree

```java
public static class Node {
    int data;
    Node first_child;
    Node second_child;
    Node third_child;
    .
    .
    .
    Node nth_child;
}

```

* In tree ADT, the order of  the elements  is not important. tree is a graph with special conditions
* **Level / depth of a node:** The count of edges on the path from the root node to that node. The root node has level 0.
* **Height of a node :** is the length of the path from that node to the deepest node.
* **Height of tree :** is the max height among all the nodes

## Type of binary tree

1. **full binary tree:** if each node has exactly two children and height does not matter
2. **complete binary tree :** fill tree from left to right and level should be filled like complete tree
3. **perfect binary tree:** same height + full = perfect. total nodes at height h will be 2^(h+1)-1.
4. **balanced binary tree:**

## Operations on binary tree

* Search − Searches specific data in a tree to check whether it is present or not.
* 
* Traversal:

  * Depth-First-Search Traversal
    * In-order
    * pre-order
    * post-order
  * Breadth-First-Search Traversal
    * level order
* finding the size of the tree
* finding the height of the tree
* level of node
* Create – create a tree in the data structure.
* Insert − Inserts data in a tree.
* delete

```java

class Node {
    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

private Node findLeft(Node node) {
    if (node.right != null) {
        return findLeft(node.right);
    }
    return node;
}

private Node deleteDfs(Node node, int data) {
    if (node == null) {
        return null;
    }

    if (node.val == data) {
        // Case 1: Node with one or no child
        if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        }

        // Case 2: Node with two children - Find inorder predecessor
        Node temp = findLeft(node.left);
        node.val = temp.val;
        node.left = deleteDfs(node.left, temp.val); // Remove the predecessor node
    } else {
        // Continue searching for the node
        node.left = deleteDfs(node.left, data);
        node.right = deleteDfs(node.right, data);
    }

    return node;
}

```

patterns questions:

find max height of tree or node
give a subtree has all the elements below x value
recreate binary tree since after remove certain nodes

insert a node at level
delete node at level x and value y
is both binary trees are same ?
add elements at same level and give resultant

Here's a **concise and clean revision note** for:

✅ **Height**
✅ **Depth**
✅ **Level**

with clear **definitions**, **examples**, and **Java implementations**.

---

### 🌳 Binary Tree: Height, Depth & Level (with Java code)

---

### 🔹 1. **Height**

- **Definition**: Number of edges on the longest path from a node **to a leaf**.
- **Height of Tree** = Height of root.
- **Height of leaf** = 0.
- **Height of null node** = -1 (for correct leaf calculation).

#### ✅ Example:

```
        A         → Height = 2
       / \
      B   C       → Height = 1
     /
    D             → Height = 0
```

#### 🧩 Java Code:

```java
public static int height(TreeNode node) {
    if (node == null) return -1;
    return 1 + Math.max(height(node.left), height(node.right));
}
```

---

### 🔹 2. **Depth**

- **Definition**: Number of edges from **root to a node**.
- **Depth of root** = 0.
- Used in recursion and node search.

#### ✅ Example:

```
        A         → Depth = 0
       / \
      B   C       → Depth = 1
     /
    D             → Depth = 2
```

#### 🧩 Java Code:

```java
public static int depth(TreeNode root, TreeNode target) {
    return findDepth(root, target, 0);
}

private static int findDepth(TreeNode node, TreeNode target, int level) {
    if (node == null) return -1;
    if (node == target) return level;
    int left = findDepth(node.left, target, level + 1);
    return (left != -1) ? left : findDepth(node.right, target, level + 1);
}
```

---

### 🔹 3. **Level**

- **Definition**: Number of **nodes** on the path from root to the node (including root and node).
- **Level = Depth + 1**
- **Level of root** = 1

#### ✅ Example:

```
        A         → Level = 1
       / \
      B   C       → Level = 2
     /
    D             → Level = 3
```

#### 🧩 Java Code:

```java
public static int level(TreeNode root, TreeNode target) {
    int depth = depth(root, target); // reuse depth method
    return (depth != -1) ? depth + 1 : -1;
}
```

---

### 🧪 Sample Usage in `main`:

```java
public static void main(String[] args) {
    TreeNode A = new TreeNode('A');
    TreeNode B = new TreeNode('B');
    TreeNode C = new TreeNode('C');
    TreeNode D = new TreeNode('D');
    A.left = B; A.right = C; B.left = D;

    System.out.println("Height of A: " + height(A));       // 2
    System.out.println("Depth of D: " + depth(A, D));       // 2
    System.out.println("Level of D: " + level(A, D));       // 3
}
```

## 1. **Head Recursion**

### ✅ Definition

- **Head recursion** occurs when the **recursive call** is the **first** statement in the function.
- Any processing is done **after** the recursive call returns.

### 🔎 Structure

```java
void headRec(int n) {
    if (n <= 0) return;       // Base case
    headRec(n - 1);          // Recursive call (head)
    System.out.println(n);   // Processing after
}
```

### 🏷️ When to Use

- When you need to process data in **reverse order** of the function calls.
- Situations where you're building up a result **after** exploring deeper levels.

### 🌐 Real-Life Example

- **Reading a book from last page to first page**:
  - You might want to understand how many pages are remaining, so you keep “recursing” (turning pages backward) until you reach the front. Then, as you come back from each page, you record the page number or content.

---

## 2. **Tail Recursion**

### ✅ Definition

- **Tail recursion** occurs when the **recursive call** is the **last** operation in the function.
- There is no extra computation after the recursive call returns.

### 🔎 Structure

```java
void tailRec(int n) {
    if (n <= 0) return;      // Base case
    System.out.println(n);   // Processing before
    tailRec(n - 1);          // Recursive call (tail)
}
```

### 🏷️ When to Use

- When processing the data in **forward order** of the function calls.
- Certain languages/compilers optimize tail calls (called **tail call optimization**).

### 🌐 Real-Life Example

- **Counting down a rocket launch**:
  - You say “10” (process now), then call the function to say “9,” and so forth, until you reach zero. There’s no work after the recursive call because you do all the work (print the number) before you recurse.

---

Great question! Understanding **when and why to use inorder traversal** is key for solving many tree-related LeetCode problems efficiently.

---

### ✅ **When to Use Inorder Traversal**

You should use **inorder traversal** when:

1. ✅ **You need nodes in sorted order** (only for **BSTs**).
2. ✅ **You want to process the left subtree, then the node, then the right subtree**.
3. ✅ You are working with **range queries** in BSTs.
4. ✅ You need to **validate or reconstruct** a BST.
5. ✅ You are solving **Morris traversal / iterative traversal** questions.

---

### 🧠 Inorder Traversal Pattern (Left → Node → Right):

```java
void inorder(TreeNode root) {
    if (root == null) return;
    inorder(root.left);
    // process root
    inorder(root.right);
}
```

---

# Problems

https://leetcode.com/problems/delete-nodes-and-return-forest/description/

https://leetcode.com/problems/count-nodes-with-the-highest-score/description/

* https://leetcode.com/problems/merge-two-binary-trees/
* merge two binary trees

```java
class Solution {
    TreeNode merge(TreeNode root1, TreeNode root2){


        if(root1 == null && root2 == null){
            return null;
        }

        if(root1 == null ){
            return root2;
        }

        if(root2 == null ){
            return root1;
        }

        root1.val +=root2.val;

        root1.left = merge(root1.left, root2.left);
        root1.right = merge(root1.right, root2.right);

        return root1;

    }
  
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        return merge(root1, root2);

  
    }
}
```

### search till require

https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/submissions/1586147821/
https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/?envType=company&envId=amazon&favoriteSlug=amazon-three-months
https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/

### String to Tree

- https://leetcode.com/problems/construct-binary-tree-from-string/description/
- https://leetcode.com/problems/construct-string-from-binary-tree/description/

### 17. **Construct Binary Tree from String with Bracket Representation**

- Use recursion and string parsing to interpret brackets as indicators of subtree boundaries.

### 18. **Convert Binary Tree into Doubly Linked List**

- Perform in-order traversal, convert each node into a doubly linked list node, and link them together.

### 19. **Convert Binary Tree into Sum Tree**

- Recursively replace each node with the sum of its left and right subtrees while returning  Finally, return the sum of new value and value (which is sum of values in the subtree rooted with this node).

### 20. **Construct Binary Tree from Inorder and Preorder Traversal**

- Use recursion and index tracking to build the tree. The first element in preorder is the root; use inorder to find left and right subtrees.

### 21. **Find Minimum Swaps to Convert Binary Tree to BST**

- In-order traversal of the tree yields a list. Count the minimum swaps required to sort the list.

### 22. **Check if Binary Tree is Sum Tree**

- A sum tree is one where the value of each node is equal to the sum of its left and right subtree values.
- Use a post-order traversal to verify the sum property.

### 23. **Check if All Leaf Nodes are at the Same Level**

- Use level order traversal and check the levels of leaf nodes.

### 24. **Check if a Binary Tree Contains Duplicate Subtrees of Size 2 or More**

- Use serialization (or hashing) of subtrees to detect duplicates during post-order traversal.

### 26. **Sum of Nodes on the Longest Path from Root to Leaf Node**

- Use DFS to find the path with the maximum depth and calculate the sum of its nodes.

### 27. **Check if Given Graph is Tree**

- A graph is a tree if it is connected and has no cycles.
- Use BFS/DFS to verify connectivity and absence of cycles.

### 29. **Maximum Sum of Nodes in a Binary Tree Such that No Two are Adjacent**

- Use dynamic programming with two states: include the current node or exclude it.

### 30. **Print All “K” Sum Paths in a Binary Tree**

- Use DFS and backtracking to find all paths from any node that sum to `K`.

### 33. **Kth Ancestor of a Node in a Binary Tree**

- Use recursion to track ancestors and return the `Kth` one when the target node is found.

### 34. **Find All Duplicate Subtrees in a Binary Tree** (good)

- Use serialization (or hashing) of subtrees during post-order traversal to detect duplicates.
