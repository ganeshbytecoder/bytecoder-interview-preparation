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

```python
# Common Helpers (for local testing)
# LeetCode already provides these; included here for local testing only.

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Node:  # for next pointers / special problems
    def __init__(self, val=0, left=None, right=None, next=None, parent=None, random=None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
        self.parent = parent
        self.random = random
```

---

## 🎨 Essential Tree Patterns for FAANG

---

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

---

### 8. Tree Construction Pattern

**Use Cases:** Building tree from traversals or other representations

**💡 Key Insight:** Inorder + Preorder/Postorder uniquely determines tree. Preorder gives root, inorder splits left/right. Use hashmap for O(1) lookup.

#### Template:

```python
def buildTree(preorder, inorder):
    if not preorder:
        return None
  
    pos = {v: i for i, v in enumerate(inorder)}
    pre_idx = 0
  
    def build(left, right):
        nonlocal pre_idx
        if left > right:
            return None
  
        root_val = preorder[pre_idx]
        pre_idx += 1
        root = TreeNode(root_val)
  
        mid = pos[root_val]
        root.left = build(left, mid - 1)
        root.right = build(mid + 1, right)
  
        return root
  
    return build(0, len(inorder) - 1)
```

**Common Problems:**

- Construct from Preorder & Inorder (105)
- Construct from Postorder & Inorder (106)
- BST from Preorder (1008)
- Generate Unique BSTs (95)

---

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

```java
/**
 * Comprehensive Binary Tree implementation with common FAANG interview questions.
 * Time and Space complexity provided for each operation.
 */
public class BinaryTree implements Tree {
    private Node root;

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Tree insert(int data) {
        if (isEmpty()) {
            root = new Node(data);
        } else {
            insertNode_BFS(data);
        }
        return this;
    }


    // Time: O(n), Space: O(w) where w is max width
    private void insertNode_BFS(int data) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.getLeftChild() == null) {
                current.setLeftChild(new Node(data));
                return;
            }
            if (current.getRightChild() == null) {
                current.setRightChild(new Node(data));
                return;
            }

            queue.add(current.getLeftChild());
            queue.add(current.getRightChild());
        }
    }

    private void insertNode_DFS(Node current, int data) {
        if (current.getLeftChild() == null) {
            current.setLeftChild(new Node(data));
        } else if (current.getRightChild() == null) {
            current.setRightChild(new Node(data));
        } else {
            // Insert in the shortest subtree
            if (getHeight(current.getLeftChild()) <= getHeight(current.getRightChild())) {
                insertNode_DFS(current.getLeftChild(), data);
            } else {
                insertNode_DFS(current.getRightChild(), data);
            }
        }
    }

    // Original Tree Interface Methods
    @Override
    public int size() {
        return calculateSize(root);
    }

    private int calculateSize(Node node) {
        if (node == null) return 0;
        return 1 + calculateSize(node.getLeftChild()) + calculateSize(node.getRightChild());
    }

    @Override
    public int getMax() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return findMax(root);
    }

    private int findMax(Node node) {
        if (node == null) return Integer.MIN_VALUE;
        int max = node.getData();
        int leftMax = findMax(node.getLeftChild());
        int rightMax = findMax(node.getRightChild());
        return Math.max(max, Math.max(leftMax, rightMax));
    }

    @Override
    public int getMin() {
        if (isEmpty()) return Integer.MAX_VALUE;
        return findMin(root);
    }

    private int findMin(Node node) {
        if (node == null) return Integer.MAX_VALUE;
        int min = node.getData();
        int leftMin = findMin(node.getLeftChild());
        int rightMin = findMin(node.getRightChild());
        return Math.min(min, Math.min(leftMin, rightMin));
    }

    @Override
    public int getLevel(int data) {
        return findLevel(root, data, 1);
    }

    private int findLevel(Node node, int data, int level) {
        if (node == null) return -1;
        if (node.getData() == data) return level;
  
        int leftLevel = findLevel(node.getLeftChild(), data, level + 1);
        if (leftLevel != -1) return leftLevel;
  
        return findLevel(node.getRightChild(), data, level + 1);
    }

    @Override
    public List<Node> getNodesAtLevel(int level) {
        List<Node> nodes = new ArrayList<>();
        collectNodesAtLevel(root, level, 1, nodes);
        return nodes;
    }

    private void collectNodesAtLevel(Node node, int targetLevel, int currentLevel, List<Node> nodes) {
        if (node == null) return;
        if (currentLevel == targetLevel) {
            nodes.add(node);
            return;
        }
        collectNodesAtLevel(node.getLeftChild(), targetLevel, currentLevel + 1, nodes);
        collectNodesAtLevel(node.getRightChild(), targetLevel, currentLevel + 1, nodes);
    }

    @Override
    public boolean searchData(int data) {
        return search(root, data);
    }

    private boolean search(Node node, int data) {
        if (node == null) return false;
        if (node.getData() == data) return true;
        return search(node.getLeftChild(), data) || search(node.getRightChild(), data);
    }

    @Override
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node node, int data) {
        if (node == null) return null;
  
        if (node.getData() == data) {
            // Case 1: Leaf node
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                return null;
            }
            // Case 2: Node with one child
            if (node.getLeftChild() == null) return node.getRightChild();
            if (node.getRightChild() == null) return node.getLeftChild();
      
            // Case 3: Node with two children
            // Find the minimum value in right subtree (successor)
            Node successor = findMinNode(node.getRightChild());
            node.setData(successor.getData());
            node.setRightChild(deleteNode(node.getRightChild(), successor.getData()));
            return node;
        }
  
        node.setLeftChild(deleteNode(node.getLeftChild(), data));
        node.setRightChild(deleteNode(node.getRightChild(), data));
        return node;
    }

    private Node findMinNode(Node node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

  
    // Helper methods
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())) + 1;
    }
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

### 9. Morris Traversal Pattern (Advanced)

**Use Cases:** O(1) space traversal using threading

**💡 Key Insight:** Temporarily modify tree to create threads back to inorder successor. Restore structure while traversing. O(1) extra space!

**Time:** O(n) | **Space:** O(1)

#### Template:

```python
def inorderTraversal_Morris(root):
    result = []
    curr = root
  
    while curr:
        if not curr.left:
            result.append(curr.val)
            curr = curr.right
        else:
            # Find predecessor
            pred = curr.left
            while pred.right and pred.right != curr:
                pred = pred.right
  
            if not pred.right:
                # Create thread
                pred.right = curr
                curr = curr.left
            else:
                # Remove thread
                pred.right = None
                result.append(curr.val)
                curr = curr.right
  
    return result
```

**Common Problems:**

- Inorder Traversal (94)
- Kth Smallest in BST (230)
- Recover BST (99)

---

### 9. Binary Tree Paths (LC 257)

**Difficulty:** Easy | **Pattern:** DFS, Backtracking

Return all root-to-leaf paths as strings.

**Time:** O(n) | **Space:** O(h)


### 10. Sum of Left Leaves (LC 404)

**Difficulty:** Easy | **Pattern:** DFS

Sum all left leaves (leaves that are left children).

**Time:** O(n) | **Space:** O(h)


### 13. Closest BST Value (LC 270)

**Difficulty:** Easy | **Pattern:** BST

Find value closest to given target in BST.

**Time:** O(h) | **Space:** O(1)

---

### 14. Sum Root to Leaf Binary Numbers (LC 1022)

**Difficulty:** Easy | **Pattern:** DFS

Treat root-to-leaf paths as binary numbers, sum them.

**Time:** O(n) | **Space:** O(h)

**Formula:** val = (val << 1) | node.val

---

### 15. Cousins in Binary Tree (LC 993)

**Difficulty:** Easy | **Pattern:** BFS

Check if two nodes are cousins (same depth, different parents).

**Time:** O(n) | **Space:** O(w)
