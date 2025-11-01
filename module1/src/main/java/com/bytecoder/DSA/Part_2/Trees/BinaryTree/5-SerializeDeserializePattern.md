### 6. Serialize/Deserialize Pattern

**Use Cases:** Encoding tree structure, persistence, transmission

**💡 Key Insight:** Use BFS or preorder with null markers. BFS preserves level structure. Preorder is more compact.

**Time:** O(n) | **Space:** O(n)

**Common Problems:**

- Serialize and Deserialize Binary Tree (297)
- Construct from String (536)
- Construct from Preorder & Inorder (105)
- BST from Preorder (1008)

### String to Tree

- https://leetcode.com/problems/construct-binary-tree-from-string/description/
- https://leetcode.com/problems/construct-string-from-binary-tree/description/

### 17. **Construct Binary Tree from String with Bracket Representation**

- Use recursion and string parsing to interpret brackets as indicators of subtree boundaries.


```java
    // FAANG Question 2: Serialize and Deserialize Binary Tree
    // Time: O(n), Space: O(n)
    public String serialize() {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString().trim();
    }

    private void serializeHelper(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append("null ");
            return;
        }
        sb.append(node.getData()).append(" ");
        serializeHelper(node.getLeftChild(), sb);
        serializeHelper(node.getRightChild(), sb);
    }

    public void deserialize(String data) {
        if (data == null || data.equals("null")) {
            root = null;
            return;
        }
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(" ")));
        root = deserializeHelper(nodes);
    }

    private Node deserializeHelper(Queue<String> nodes) {
        String val = nodes.poll();
        if (val == null || val.equals("null")) return null;
        Node node = new Node(Integer.parseInt(val));
        node.setLeftChild(deserializeHelper(nodes));
        node.setRightChild(deserializeHelper(nodes));
        return node;
    }

```
