## **9. Left View of a Tree**

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
