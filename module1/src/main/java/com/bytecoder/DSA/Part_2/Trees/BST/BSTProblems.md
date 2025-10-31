* https://leetcode.com/problems/n-ary-tree-postorder-traversal/description/
* https://leetcode.com/problems/increasing-order-search-tree/description/
* https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/?envType=study-plan-v2&envId=top-interview-150 
* https://leetcode.com/problems/maximum-depth-of-n-ary-tree/description/
* https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/recover-binary-search-tree/ 


### **41. Populate Inorder Successor of All Nodes**
Traverse the tree in reverse in-order and link each node to its successor.

```java
void populateInorderSuccessor(Node root) {
    AtomicReference<Node> next = new AtomicReference<>(null);
    populateHelper(root, next);
}

void populateHelper(Node node, AtomicReference<Node> next) {
    if (node == null) return;

    populateHelper(node.right, next);
    node.successor = next.get();
    next.set(node);
    populateHelper(node.left, next);
}
```

---

### **42. Find LCA of Two Nodes in a BST**
Find the Lowest Common Ancestor using the BST property.

```java
Node findLCA(Node root, int n1, int n2) {
    if (root == null) return null;

    if (root.data > n1 && root.data > n2) return findLCA(root.left, n1, n2);
    if (root.data < n1 && root.data < n2) return findLCA(root.right, n1, n2);

    return root;
}
```

---

### **43. Construct BST from Preorder Traversal**
Use a stack to efficiently construct the BST.

```java
Node constructBSTFromPreorder(int[] preorder) {
    Stack<Node> stack = new Stack<>();
    Node root = new Node(preorder[0]);
    stack.push(root);

    for (int i = 1; i < preorder.length; i++) {
        Node temp = null;

        while (!stack.isEmpty() && preorder[i] > stack.peek().data) {
            temp = stack.pop();
        }

        Node newNode = new Node(preorder[i]);
        if (temp != null) temp.right = newNode;
        else stack.peek().left = newNode;

        stack.push(newNode);
    }
    return root;
}
```

---

### **44. Convert Binary Tree into BST**
1. Extract the in-order traversal.
2. Sort the values.
3. Reconstruct the tree.

```java
void convertToBST(Node root) {
    List<Integer> values = new ArrayList<>();
    inorderTraversal(root, values);

    Collections.sort(values);
    Iterator<Integer> iterator = values.iterator();
    fillTreeWithValues(root, iterator);
}

void inorderTraversal(Node node, List<Integer> values) {
    if (node == null) return;
    inorderTraversal(node.left, values);
    values.add(node.data);
    inorderTraversal(node.right, values);
}

void fillTreeWithValues(Node node, Iterator<Integer> iterator) {
    if (node == null) return;
    fillTreeWithValues(node.left, iterator);
    node.data = iterator.next();
    fillTreeWithValues(node.right, iterator);
}
```

---

### **45. Convert Normal BST into Balanced BST**
1. Perform an in-order traversal to extract sorted values.
2. Recursively build the balanced BST.

```java
Node balanceBST(Node root) {
    List<Integer> values = new ArrayList<>();
    inorderTraversal(root, values);
    return buildBalancedTree(values, 0, values.size() - 1);
}

Node buildBalancedTree(List<Integer> values, int start, int end) {
    if (start > end) return null;

    int mid = (start + end) / 2;
    Node node = new Node(values.get(mid));
    node.left = buildBalancedTree(values, start, mid - 1);
    node.right = buildBalancedTree(values, mid + 1, end);
    return node;
}
```

---

### **46. Merge Two BSTs**
Merge two BSTs by combining their sorted arrays and building a new BST.

```java
Node mergeBSTs(Node root1, Node root2) {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    inorderTraversal(root1, list1);
    inorderTraversal(root2, list2);

    List<Integer> mergedList = mergeSortedLists(list1, list2);
    return buildBalancedTree(mergedList, 0, mergedList.size() - 1);
}
```

---

### **47. Find Kth Largest Element in BST**
Perform reverse in-order traversal.

```java
int kthLargest(Node root, int k) {
    AtomicInteger count = new AtomicInteger(0);
    return kthLargestHelper(root, k, count);
}

int kthLargestHelper(Node node, int k, AtomicInteger count) {
    if (node == null) return -1;

    int right = kthLargestHelper(node.right, k, count);
    if (right != -1) return right;

    count.incrementAndGet();
    if (count.get() == k) return node.data;

    return kthLargestHelper(node.left, k, count);
}
```

---

### **48. Find Kth Smallest Element in BST**
Perform in-order traversal.

```java
int kthSmallest(Node root, int k) {
    AtomicInteger count = new AtomicInteger(0);
    return kthSmallestHelper(root, k, count);
}

int kthSmallestHelper(Node node, int k, AtomicInteger count) {
    if (node == null) return -1;

    int left = kthSmallestHelper(node.left, k, count);
    if (left != -1) return left;

    count.incrementAndGet();
    if (count.get() == k) return node.data;

    return kthSmallestHelper(node.right, k, count);
}
```

---

### **49. Count Pairs from Two BSTs Whose Sum is Equal to X**
Use in-order and reverse in-order traversal.

```java
int countPairs(Node root1, Node root2, int X) {
    Stack<Node> stack1 = new Stack<>();
    Stack<Node> stack2 = new Stack<>();

    Node curr1 = root1, curr2 = root2;
    int count = 0;

    while ((curr1 != null || !stack1.isEmpty()) &&
           (curr2 != null || !stack2.isEmpty())) {

        while (curr1 != null) {
            stack1.push(curr1);
            curr1 = curr1.left;
        }

        while (curr2 != null) {
            stack2.push(curr2);
            curr2 = curr2.right;
        }

        Node top1 = stack1.peek(), top2 = stack2.peek();

        if (top1.data + top2.data == X) {
            count++;
            stack1.pop();
            stack2.pop();
            curr1 = top1.right;
            curr2 = top2.left;
        } else if (top1.data + top2.data < X) {
            stack1.pop();
            curr1 = top1.right;
        } else {
            stack2.pop();
            curr2 = top2.left;
        }
    }
    return count;
}
```

---

### **50. Find Median of a BST**
Use Morris Traversal to count nodes and find the median.

```java
double findMedian(Node root) {
    int count = countNodes(root);
    int mid1 = (count + 1) / 2, mid2 = (count + 2) / 2;

    AtomicInteger currCount = new AtomicInteger(0);
    double sum = findKthNode(root, mid1, currCount) + findKthNode(root, mid2, currCount);

    return sum / 2;
}

int countNodes(Node root) {
    // Count nodes using Morris Traversal
}

int findKthNode(Node root, int k, AtomicInteger count) {
    // Morris traversal to find kth node
}
```
