# Tree -

a tree is a data structure similar to a linked list but instead of each node pointing simply to the next node in a linear fashion, each node
points to a number of nodes. it's non-linear and non-cyclic data structure. we will study binary tree, BST( Binary Search Tree) and Nary Tree

```python
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class TreeNode:
    def __init__(self, val=0,first=None,second=None, third = None ):
        self.val = val
        self.first = first
        self.second = second
	self.third = third





class Node:  # for next pointers / special problems
    def __init__(self, val=0, left=None, right=None, next=None, parent=None, random=None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
        self.parent = parent
        self.random = random

```

* In tree ADT, the order of  the elements  is not important. tree is a graph with special conditions
* **Level / depth of a node:** The count of edges on the path from the root node to that node. The root node has level 0.
* **Height of a node :** is the length of the path from that node to the deepest node.
* **Height of tree :** is the max height among all the nodes

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
    public int getMax(Node node) {
        if (node == null) return Integer.MIN_VALUE;
        int max = node.getData();
        int leftMax = findMax(node.getLeftChild());
        int rightMax = findMax(node.getRightChild());
        return Math.max(max, Math.max(leftMax, rightMax));
    }


    @Override
    public int getMin(Node node) {
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

### Tree Construction Pattern

**Use Cases:** Building tree from traversals or other representations

**💡 Key Insight:** Inorder + Preorder/Postorder uniquely determines tree. Preorder gives root, inorder splits left/right. Use hashmap for O(1) lookup.

#### Template:

```python
class Solution:

    def createTree(self, preorder, mapper, l, r):
        if(l>r):
            return None
        node = TreeNode(preorder.pop(0))
        mid = mapper.get(node.val)
        node.left = self.createTree(preorder, mapper, l, mid-1)
        node.right = self.createTree(preorder, mapper, mid+1 , r)
        return node



    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:

        mapper = {v: i for i, v in enumerate(inorder)}  
        return self.createTree(preorder, mapper,0, len(preorder)-1)
```

**Common Problems:**

- Construct from Preorder & Inorder (105)
- Construct from Postorder & Inorder (106)
- BST from Preorder (1008)
- Generate Unique BSTs (95)

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

### 13. Closest BST Value (LC 270)

**Difficulty:** Easy | **Pattern:** BST

Find value closest to given target in BST.

**Time:** O(h) | **Space:** O(1)

---

### 15. Cousins in Binary Tree (LC 993)

**Difficulty:** Easy | **Pattern:** BFS

Check if two nodes are cousins (same depth, different parents).

**Time:** O(n) | **Space:** O(w)

## 6. Flatten Tree to Linked List (LC 114)

**Difficulty:** Medium | **Pattern:** DFS, Postorder

Flatten tree to linked list in preorder.

**Time:** O(n) | **Space:** O(h)

**Trick:** Process in reverse preorder, maintain prev pointer.

```python
class Solution:

    def dfs(self, node):
        if node is None:
            return
  
        print(node.val)

        # Save original children BEFORE modifying the node
        left = node.left
        right = node.right

        # Remove left pointer (linked-list rule)
        node.left = None

        # Attach this node to the result list
        self.last.right = node
        self.last = node

        # Preorder: node → left → right
        self.dfs(left)
        self.dfs(right)

    def flatten(self, root):
        self.dummy = TreeNode(-1)
        self.last = self.dummy

        self.dfs(root)

        return self.dummy.right

class Solution:

    def dfs(self, node):
        if not node:
            return

        # inorder left
        self.dfs(node.left)

        # process root
        node.left = None
        if self.prev:
            self.prev.right = node
        else:
            self.head = node
        self.prev = node

        # inorder right
        self.dfs(node.right)

    def flatten_inorder(self, root):
        self.prev = None
        self.head = None
        self.dfs(root)
        return self.head

```

### 10. BST Iterator (LC 173)

**Difficulty:** Medium | **Pattern:** BST, Stack

Implement iterator for BST with O(h) space.

**Time:** O(1) average per next() | **Space:** O(h)

**Approach:** Controlled inorder using stack. Push all left nodes.

### 15. Binary Tree Longest Consecutive (LC 298)

**Difficulty:** Medium | **Pattern:** DFS

Find longest consecutive sequence path.

**Time:** O(n) | **Space:** O(h)


### 32. Clone Tree with Random Pointer (LC 1485)

**Difficulty:** Medium | **Pattern:** DFS, HashMap

Deep copy tree with random pointers.

**Time:** O(n) | **Space:** O(n)



### 21. Boundary of Binary Tree (LC 545)

**Difficulty:** Medium | **Pattern:** Tree Views, DFS

Return boundary in counter-clockwise order.

**Time:** O(n) | **Space:** O(h)

**Three parts:** Left boundary + Leaves + Reversed right boundary

---

### 22. Maximum Width (LC 662)

**Difficulty:** Medium | **Pattern:** BFS

Find maximum width using heap indexing (2*i, 2*i+1).

**Time:** O(n) | **Space:** O(w)

### 25. Check Completeness (LC 958)

**Difficulty:** Medium | **Pattern:** BFS

Check if tree is complete (all levels filled except possibly last).

**Time:** O(n) | **Space:** O(w)

**Trick:** Once null seen in BFS, no more non-null nodes allowed.

### 29. All Elements in Two BSTs (LC 1305)

**Difficulty:** Medium | **Pattern:** BST, Merge

Merge two BSTs into sorted array.

**Time:** O(n1 + n2) | **Space:** O(h1 + h2)

---

### 30. Validate Binary Tree Nodes (LC 1361)

**Difficulty:** Medium | **Pattern:** Graph, BFS

Check if given edges form valid binary tree.

**Time:** O(n) | **Space:** O(n)


## **Correct Conditions for a Valid Binary Tree**

1. **Exactly one root** — i.e., exactly one node with no parent.
2. **No node has more than one parent** — you already check this.
3. **The structure forms a single connected tree**

```python
class Solution:
    def validateBinaryTreeNodes(self, n: int, leftChild: List[int], rightChild: List[int]) -> bool:
        parent = [-1] * n
        left = leftChild
        right = rightChild
        # Step 1: assign parents; check no node has two parents
        for i in range(n):
            if left[i] != -1:
                if parent[left[i]] != -1:
                    return False
                parent[left[i]] = i

            if right[i] != -1:
                if parent[right[i]] != -1:
                    return False
                parent[right[i]] = i
        # Step 2: find root (exactly one node must have no parent)
        roots = [i for i in range(n) if parent[i] == -1]
        if len(roots) != 1: 
            return False

        root = roots[0]

        # Step 3: BFS/DFS from root to check connectivity
        visited = set()
        stack = [root]

        while stack:
            node = stack.pop()
            if node in visited:
                return False  # cycle detected
            visited.add(node)

            if left[node] != -1:
                stack.append(left[node])
            if right[node] != -1:
                stack.append(right[node])

        return len(visited) == n   

```

### 33. Diameter of N-Ary Tree (LC 1522)

**Difficulty:** Medium | **Pattern:** DFS

Find diameter of N-ary tree.

**Time:** O(n) | **Space:** O(h)

### 18. **Convert Binary Tree into Doubly Linked List**

- Perform in-order traversal, convert each node into a doubly linked list node, and link them together.

### 21. **Find Minimum Swaps to Convert Binary Tree to BST**

- In-order traversal of the tree yields a list. Count the minimum swaps required to sort the list.

### 27. **Check if Given Graph is Tree**

- A graph is a tree if it is connected and has no cycles.
- Use BFS/DFS to verify connectivity and absence of cycles.
