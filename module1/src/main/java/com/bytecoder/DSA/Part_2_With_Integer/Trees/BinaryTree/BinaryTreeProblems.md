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
*  **Height of a node :** is the length of the path from that node to the deepest node.
* **Height of tree :** is the max height among all the nodes

## Type of binary tree
1. **full binary tree:** if each node has exactly two children and height does not matter
2. **complete binary tree :** fill tree from left to right and level should be filled like complete tree
4. **perfect binary tree:** same height + full = perfect. total nodes at height h will be 2^(h+1)-1.
2. **balanced binary tree:** 


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

### 🔥 Top LeetCode Problems Where Inorder is Ideal:

| Problem | Why Inorder? | Link |
|--------|--------------|------|
| **94. Binary Tree Inorder Traversal** | Classic traversal task | [LeetCode 94](https://leetcode.com/problems/binary-tree-inorder-traversal/) |
| **98. Validate Binary Search Tree** | Check if inorder is strictly increasing | [LeetCode 98](https://leetcode.com/problems/validate-binary-search-tree/) |
| **230. Kth Smallest Element in a BST** | Inorder gives sorted order of BST | [LeetCode 230](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) |
| **173. Binary Search Tree Iterator** | Use inorder to iterate in sorted order | [LeetCode 173](https://leetcode.com/problems/binary-search-tree-iterator/) |
| **501. Find Mode in Binary Search Tree** | Inorder to find frequency in sorted nodes | [LeetCode 501](https://leetcode.com/problems/find-mode-in-binary-search-tree/) |
| **530. Minimum Absolute Difference in BST** | Inorder for adjacent sorted values | [LeetCode 530](https://leetcode.com/problems/minimum-absolute-difference-in-bst/) |
| **783. Minimum Distance Between BST Nodes** | Similar to 530 | [LeetCode 783](https://leetcode.com/problems/minimum-distance-between-bst-nodes/) |

---

### 🧪 Example: LeetCode 230 – Kth Smallest Element in a BST

```java
class Solution {
    int count = 0;
    int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null) return;

        inorder(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        inorder(node.right, k);
    }
}
```

---



# Problems
https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/

https://leetcode.com/problems/delete-nodes-and-return-forest/description/

https://leetcode.com/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=top-interview-150
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/
https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/

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
* https://leetcode.com/problems/invert-binary-tree/description/


* https://leetcode.com/problems/balanced-binary-tree/submissions/1327991003/

```java
   private int height(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        if(left ==-1 || right ==-1){
            return -1;
        }

        int diff = Math.abs(left-right);

        if( diff > 1 ){
            return -1;
        }

        return 1 + Math.max(left, right);
    }

    public boolean isBalanced(TreeNode root) {

        if(root == null){
            return true;
        }
        // int diff = Math.abs(height(root.left)-height(root.right));
        // if( diff>1 ){
        //     return false;
        // }
        return height(root) != -1;
    }
```

```java
    private int height(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        return 1 + Math.max(height(root.left), height(root.right));
    }

    public boolean isBalanced(TreeNode root) {

        if(root == null){
            return true;
        }
        int diff = Math.abs(height(root.left)-height(root.right));
        if( diff>1 ){
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
```


* https://leetcode.com/problems/populating-next-right-pointers-in-each-node/submissions/1328063250/

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=study-plan-v2&envId=top-interview-150
```python

def lowestCommonAncestor(root, p, q):
    def dfs(node):
        if not node:
            return None
        
        if node == p or node == q:
            return node
        
        left = dfs(node.left)
        right = dfs(node.right)
        
        if left and right:
            return node  # node is LCA if p and q are found on different sides
        
        return left if left else right
    
    return dfs(root)

```

p = 7 (node with value 7)
q = 4 (node with value 4)

* example

---

            3
           / \
          5   1
         / \  / \
        6  2 0  8
          / \
         7   4

```css
dfs(3)
 ├─ dfs(5)
 │   ├─ dfs(6) -> returns None
 │   └─ dfs(2)
 │       ├─ dfs(7) -> returns 7 (p)
 │       └─ dfs(4) -> returns 4 (q)
 │       └─ returns 2 (because both sides had p & q)
 │   └─ returns 2
 └─ dfs(1)
     ├─ dfs(0) -> returns None
     └─ dfs(8) -> returns None
     └─ returns None
 └─ returns 2

```

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/submissions/1528053859/ 
---













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

### **9. Left View of a Tree**
Perform level order traversal and track the first node at each level.

```java
void leftView(Node root) {
    if (root == null) return;

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            Node node = queue.poll();

            // Print the first node at the current level
            if (i == 0) System.out.print(node.data + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
}
```

**Time Complexity**: \(O(n)\).  
**Space Complexity**: \(O(w)\), where \(w\) is the maximum width of the tree.

---

### **10. Right View of a Tree**
Similar to the left view, but print the last node at each level during level order traversal.

```java
void rightView(Node root) {
    if (root == null) return;

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            Node node = queue.poll();

            // Print the last node at the current level
            if (i == size - 1) System.out.print(node.data + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
}
```

**Time Complexity**: \(O(n)\).  
**Space Complexity**: \(O(w)\).

---

### **11. Top View of a Tree**
Perform a level order traversal while keeping track of horizontal distances. Use a map to store the first node at each horizontal distance.

```java
void topView(Node root) {
    if (root == null) return;

    Map<Integer, Integer> topViewMap = new TreeMap<>();
    Queue<Pair<Node, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(root, 0));

    while (!queue.isEmpty()) {
        Pair<Node, Integer> pair = queue.poll();
        Node node = pair.getKey();
        int hd = pair.getValue();

        // Add the node to the map if it's the first node at this horizontal distance
        if (!topViewMap.containsKey(hd)) {
            topViewMap.put(hd, node.data);
        }

        if (node.left != null) queue.add(new Pair<>(node.left, hd - 1));
        if (node.right != null) queue.add(new Pair<>(node.right, hd + 1));
    }

    for (int value : topViewMap.values()) {
        System.out.print(value + " ");
    }
}
```

**Time Complexity**: \(O(n)\).  
**Space Complexity**: \(O(n)\) for the map.

---

### **12. Bottom View of a Tree**
Similar to the top view, but update the map for every node at a horizontal distance to track the last node.

```java
void bottomView(Node root) {
    if (root == null) return;

    Map<Integer, Integer> bottomViewMap = new TreeMap<>();
    Queue<Pair<Node, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(root, 0));

    while (!queue.isEmpty()) {
        Pair<Node, Integer> pair = queue.poll();
        Node node = pair.getKey();
        int hd = pair.getValue();

        // Update the map with the current node for this horizontal distance
        bottomViewMap.put(hd, node.data);

        if (node.left != null) queue.add(new Pair<>(node.left, hd - 1));
        if (node.right != null) queue.add(new Pair<>(node.right, hd + 1));
    }

    for (int value : bottomViewMap.values()) {
        System.out.print(value + " ");
    }
}
```

**Time Complexity**: \(O(n)\).  
**Space Complexity**: \(O(n)\) for the map.

---

### Key Notes:
1. **Top View vs. Bottom View**:
   - Top View: Track the first node at each horizontal distance.
   - Bottom View: Track the last node at each horizontal distance.

2. **Queue Pair**:
   - In both views, use a `Queue<Pair<Node, Integer>>` to store the node and its horizontal distance.

3. **TreeMap**:
   - TreeMap automatically sorts the horizontal distances, making it easier to print the result in order.

---

### 14. **Check if a Tree is Balanced**
   - A tree is balanced if the height difference between the left and right subtree is at most 1 for all nodes.
   - Calculate height and check balance recursively.

### 15. **Diagonal Traversal of a Binary Tree**
![img_1.png](img_1.png) 

**Output**: 8 10 14 3 6 7 13 1 4

   - Use a queue to traverse nodes diagonally. For each node, enqueue its left child and move to the right child.
   - To find the diagonal view of a binary tree, we perform a recursive  traversal that stores nodes in a hashmap based on their diagonal levels. Left children increase the diagonal level, while right children remain on the same level.

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


### **1. Get Sum of Nodes at a Given Level**
```java
int getSumOfNodesAtLevel(Node<T> root, int level) {
    if (root == null) return 0;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);
    int currentLevel = 0;
    int sum = 0;

    while (!q.isEmpty()) {
        int size = q.size();
        sum = 0;  // Reset sum for the current level
        for (int i = 0; i < size; i++) {
            Node<T> node = q.poll();
            if (currentLevel == level) sum += node.data;
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        if (currentLevel == level) break;
        currentLevel++;
    }
    return sum;
}
```

---

### **2. Print All Nodes at a Given Level**
```java
void printAllNodesGivenLevel(Node<T> node, int level) {
    if (node == null) return;
    if (level == 0) {
        System.out.print(node.data + " ");
        return;
    }
    printAllNodesGivenLevel(node.left, level - 1);
    printAllNodesGivenLevel(node.right, level - 1);
}
```

---

### **3. Level Order Traversal from Bottom to Top**
```java
void traverseFromBottom2Top(Node<T> root) {
    if (root == null) return;

    Queue<Node<T>> q = new LinkedList<>();
    Stack<List<Integer>> stack = new Stack<>();
    q.add(root);

    while (!q.isEmpty()) {
        int size = q.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Node<T> node = q.poll();
            level.add(node.data);
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        stack.push(level);
    }

    while (!stack.isEmpty()) {
        for (int val : stack.pop()) System.out.print(val + " ");
        System.out.println();
    }
}
```

---

### **4. Find the Deepest Node**
```java
Node<T> getDeepestNode(Node<T> root) {
    if (root == null) return null;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);
    Node<T> deepestNode = null;

    while (!q.isEmpty()) {
        deepestNode = q.poll();
        if (deepestNode.left != null) q.add(deepestNode.left);
        if (deepestNode.right != null) q.add(deepestNode.right);
    }
    return deepestNode;
}
```

---

### **5. DFS Traversal Without Recursion**
```java
void dfsWithoutRecursion(Node<T> root) {
    if (root == null) return;

    Stack<Node<T>> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
        Node<T> node = stack.pop();
        System.out.print(node.data + " ");
        if (node.right != null) stack.push(node.right);
        if (node.left != null) stack.push(node.left);
    }
}
```

---

### **6. BFS Traversal Without Recursion**
```java
void bfsWithoutRecursion(Node<T> root) {
    if (root == null) return;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
        Node<T> node = q.poll();
        System.out.print(node.data + " ");
        if (node.left != null) q.add(node.left);
        if (node.right != null) q.add(node.right);
    }
}
```

---

### **7. Get Height of Tree Without Recursion**
```java
int getHeight_m2(Node<T> root) {
    if (root == null) return 0;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);
    int height = 0;

    while (!q.isEmpty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            Node<T> node = q.poll();
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        height++;
    }
    return height;
}
```

---

### **8. Check if Two Trees Are Structurally Same**
```java
boolean areStructurallySameTree(Node<T> root1, Node<T> root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null) return false;
    return areStructurallySameTree(root1.left, root2.left) &&
           areStructurallySameTree(root1.right, root2.right);
}
```

---

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

---

### **10.  give a algorithm for finding the level that has the maximum sum in binary tree**  
```java
int findLevelWithMaxSum(Node<T> root) {
    if (root == null) return -1;

    Queue<Node<T>> q = new LinkedList<>();
    q.add(root);
    int maxSum = 0, maxLevel = 0, currentLevel = 0;

    while (!q.isEmpty()) {
        int size = q.size();
        int levelSum = 0;

        for (int i = 0; i < size; i++) {
            Node<T> node = q.poll();
            levelSum += node.data;
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }

        if (levelSum > maxSum) {
            maxSum = levelSum;
            maxLevel = currentLevel;
        }
        currentLevel++;
    }
    return maxLevel;
}
```

---

### **11. given a binary tree print all its root-to-leaf paths** 
```java
void pathsFinder(Node<T> root) {
    List<Integer> path = new ArrayList<>();
    dfs(root, path);

    void dfs(Node<T> node, List<Integer> path) {
        if (node == null) return;

        path.add(node.data);
        if (node.left == null && node.right == null) {
            System.out.println(path);
        } else {
            dfs(node.left, path);
            dfs(node.right, path);
        }
        path.remove(path.size() - 1);
    }
}
```

---


### **1. Sum of Root-to-Leaf Numbers**

given a binary tree containing digits from 0-9 only , each root-to-leaf path could represent a number. an example 
is the root-to-leaf path 1->2->3 which represents the number 123, find the total sum of all root-to-leaf numbers

```java
int sumRoot2LeafNumbers(Node<T> root) {
    return dfs(root, 0);

    int dfs(Node<T> node, int currentSum) {
        if (node == null) return 0;

        currentSum = currentSum * 10 + node.data;

        // If leaf node, return the computed number
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // Recursive calls for left and right subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}
```

---

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

---

### **3. Check Existence of Path with Given Sum**
give a algorithm for checking the existence of path with given sum. that means , given a sum, check whether there exists a path from root to any of the nodes.
```java
boolean hasPathSum(Node<T> root, int sum) {
    if (root == null) return false;

    // Check if we have reached a leaf node with the exact sum
    if (root.left == null && root.right == null) {
        return sum == root.data;
    }

    // Recur for left and right subtrees with the reduced sum
    return hasPathSum(root.left, sum - root.data) || hasPathSum(root.right, sum - root.data);
}
```

---

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

---

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

---

### **7. Find Vertical Sum of a Binary Tree**
Give an algorithm for finding thr vertical sum of a binary tree
```java
List<Integer> getVerticalSum(Node<T> root) {
    TreeMap<Integer, Integer> columnSumMap = new TreeMap<>();

    dfs(root, 0, columnSumMap);

    return new ArrayList<>(columnSumMap.values());
}

void dfs(Node<T> node, int column, TreeMap<Integer, Integer> columnSumMap) {
    if (node == null) return;

    // Update the sum for the current column
    columnSumMap.put(column, columnSumMap.getOrDefault(column, 0) + node.data);

    // Recur for left and right subtrees
    dfs(node.left, column - 1, columnSumMap);
    dfs(node.right, column + 1, columnSumMap);
}
```

---

### **8. Zigzag Traversal**
   - Use two stacks to alternate between left-to-right and right-to-left traversals at each level.

```java
void zigZagTraversal(Node<T> root) {
    if (root == null) return;

    Queue<Node<T>> queue = new LinkedList<>();
    queue.add(root);
    boolean leftToRight = true;

    while (!queue.isEmpty()) {
        int size = queue.size();
        LinkedList<Integer> level = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            Node<T> node = queue.poll();

            if (leftToRight) {
                level.add(node.data);
            } else {
                level.addFirst(node.data);
            }

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        System.out.println(level);
        leftToRight = !leftToRight;  // Toggle direction
    }
}
```

Examples: -

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

https://leetcode.com/problems/sum-root-to-leaf-numbers/description/?envType=study-plan-v2&envId=top-interview-150


### search till require
https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/submissions/1586147821/
https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/?envType=company&envId=amazon&favoriteSlug=amazon-three-months
https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/

### LCA in a Binary Tree
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=study-plan-v2&envId=top-interview-150
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/description/
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/description/
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/description/

#### 31. **Find LCA in a Binary Tree** (good)

- Use recursion to find the lowest common ancestor by exploring left and right subtrees.

```java
    public void path(TreeNode node, TreeNode target, List<TreeNode> list, List<TreeNode> ans){
        if(node == null){
            return;
        }
        if(node == target){
            list.add(node);
            // ans = new ArrayList<TreeNode>(list);
            for(TreeNode n : list){
                ans.add(n);
            }
            return;
        }
        list.add(node);
        path(node.left, target, list, ans);
        path(node.right, target, list, ans);
        list.remove(node);
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> ans1 = new ArrayList<>();
        List<TreeNode> ans2 = new ArrayList<>();

        path(root, p, new ArrayList<>(), ans1);
        
        path(root, q, new ArrayList<>(), ans2);
        
        
        System.out.print(ans1.size());
        for(int i = ans1.size()-1 ; i >= 0; i--){
            System.out.println("check " + ans1.get(i).val);
            if(ans2.contains(ans1.get(i))){
                return ans1.get(i);
            }
        }

        System.out.println("ans root ");
        return root;

    }
```

https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/description/
https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/
### String to Tree
- https://leetcode.com/problems/construct-binary-tree-from-string/description/
- https://leetcode.com/problems/construct-string-from-binary-tree/description/




### 16. **Boundary Traversal of a Binary Tree**![img.png](img.png)
- Traverse the left boundary, then leaf nodes, then the right boundary (in reverse order).

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

### 25. **Check if Two Trees are Mirror**
- Recursively compare the left subtree of one tree with the right subtree of the other.

### 26. **Sum of Nodes on the Longest Path from Root to Leaf Node**
- Use DFS to find the path with the maximum depth and calculate the sum of its nodes.

### 27. **Check if Given Graph is Tree**
- A graph is a tree if it is connected and has no cycles.
- Use BFS/DFS to verify connectivity and absence of cycles.

### 28. **Find Largest Subtree Sum in a Tree**
- Perform a post-order traversal to calculate the sum of each subtree and track the maximum.

### 29. **Maximum Sum of Nodes in a Binary Tree Such that No Two are Adjacent**
- Use dynamic programming with two states: include the current node or exclude it.

### 30. **Print All “K” Sum Paths in a Binary Tree**
- Use DFS and backtracking to find all paths from any node that sum to `K`.


### 32. **Find Distance Between Two Nodes in a Binary Tree** (good) (https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/)
- Find LCA of the two nodes, then calculate the distance from LCA to each node.

### 33. **Kth Ancestor of a Node in a Binary Tree**
- Use recursion to track ancestors and return the `Kth` one when the target node is found.

### 34. **Find All Duplicate Subtrees in a Binary Tree** (good)
- Use serialization (or hashing) of subtrees during post-order traversal to detect duplicates.

