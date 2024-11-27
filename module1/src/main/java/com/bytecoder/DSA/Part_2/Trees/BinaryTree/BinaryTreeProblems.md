

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

