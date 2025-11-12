## 🔵 Doubly Linked List Problems

### 1. Reverse Doubly Linked List

- **Difficulty:** Medium
- **Tags:** DLL, Pointer Manipulation
- **Problem:** Reverse a doubly linked list.
- **Time:** O(n) | **Space:** O(1)
- **Approach:** Swap prev and next pointers for each node.

```java
class Node {
    int val;
    Node prev, next;
}

public Node reverseDLL(Node head) {
    if (head == null) return null;
  
    Node curr = head, temp = null;
  
    while (curr != null) {
        temp = curr.prev;
        curr.prev = curr.next;
        curr.next = temp;
        curr = curr.prev; // Move to next (which is now prev)
    }
  
    if (temp != null) {
        head = temp.prev;
    }
    return head;
}
```

### 2. Count Triplets in Sorted DLL with Given Sum

- **Difficulty:** Medium
- **Tags:** DLL, Two Pointers
- **Problem:** Count triplets in sorted DLL whose sum equals target.
- **Time:** O(n²) | **Space:** O(1)
- **Approach:** Fix one node, use two pointers for remaining sum.

```java
public int countTriplets(Node head, int target) {
    int count = 0;
    Node curr = head;
  
    while (curr != null) {
        count += countPairs(curr.next, target - curr.val);
        curr = curr.next;
    }
    return count;
}

private int countPairs(Node head, int target) {
    int count = 0;
    Node left = head;
    Node right = getTail(head);
  
    while (left != null && right != null && left != right && left.prev != right) {
        int sum = left.val + right.val;
        if (sum == target) {
            count++;
            left = left.next;
            right = right.prev;
        } else if (sum < target) {
            left = left.next;
        } else {
            right = right.prev;
        }
    }
    return count;
}

private Node getTail(Node head) {
    while (head != null && head.next != null) {
        head = head.next;
    }
    return head;
}
```

### 3. Sort k-Sorted Doubly Linked List

- **Difficulty:** Medium
- **Tags:** DLL, Min-Heap
- **Problem:** Sort a k-sorted DLL where each element is at most k positions away from target.
- **Time:** O(n log k) | **Space:** O(k)
- **Approach:** Use min-heap of size k+1.

```java
public Node sortKSortedDLL(Node head, int k) {
    if (head == null) return null;
  
    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
    Node newHead = null, last = null;
  
    // Add first k+1 nodes to heap
    Node curr = head;
    for (int i = 0; curr != null && i <= k; i++) {
        pq.offer(curr);
        curr = curr.next;
    }
  
    while (!pq.isEmpty()) {
        Node min = pq.poll();
  
        if (newHead == null) {
            newHead = min;
            min.prev = null;
            last = min;
        } else {
            last.next = min;
            min.prev = last;
            last = min;
        }
  
        if (curr != null) {
            pq.offer(curr);
            curr = curr.next;
        }
    }
  
    last.next = null;
    return newHead;
}
```

### 4. Rotate Doubly Linked List by N Nodes

- **Difficulty:** Medium
- **Tags:** DLL, Circular
- **Problem:** Rotate DLL to the right by n positions.
- **Time:** O(n) | **Space:** O(1)
- **Approach:** Make circular, move head n positions, break circle.

```java
public Node rotateDLL(Node head, int n) {
    if (head == null || n == 0) return head;
  
    // Find length and tail
    Node tail = head;
    int len = 1;
    while (tail.next != null) {
        tail = tail.next;
        len++;
    }
  
    n = n % len;
    if (n == 0) return head;
  
    // Make circular
    tail.next = head;
    head.prev = tail;
  
    // Move to new head position
    int stepsToNewHead = len - n;
    Node newTail = head;
    for (int i = 1; i < stepsToNewHead; i++) {
        newTail = newTail.next;
    }
  
    Node newHead = newTail.next;
  
    // Break circle
    newTail.next = null;
    newHead.prev = null;
  
    return newHead;
}
```

### 5. Rotate DLL in Groups of Given Size

- **Difficulty:** Hard
- **Tags:** DLL, Reversal
- **Problem:** Reverse each group of k nodes in DLL.
- **Time:** O(n) | **Space:** O(1)

```java
public Node reverseInGroups(Node head, int k) {
    if (head == null || k == 1) return head;
  
    Node curr = head, prevGroupEnd = null, newHead = null;
  
    while (curr != null) {
        Node groupStart = curr;
        Node prev = null;
        int count = 0;
  
        // Reverse k nodes
        while (curr != null && count < k) {
            Node next = curr.next;
            curr.next = prev;
            if (prev != null) prev.prev = curr;
            prev = curr;
            curr = next;
            count++;
        }
  
        if (newHead == null) {
            newHead = prev;
        }
  
        if (prevGroupEnd != null) {
            prevGroupEnd.next = prev;
            prev.prev = prevGroupEnd;
        }
  
        groupStart.next = curr;
        if (curr != null) curr.prev = groupStart;
  
        prevGroupEnd = groupStart;
    }
  
    return newHead;
}
```



### **Important Problems:**

- **LRU Cache (146)**
- **Design Browser History (1472)**
- **Design Circular Queue (LC 622)**
- **BST to Sorted Doubly List (426)**
- **Flatten Multilevel List (430)**

```python
class Node:
    def __init__(self, val, nxt = None, prev =None):
        self.val = val
        self.next = nxt 
        self.prev = prev

# LRU Cache (146)
class LRUCache:
    def __init__(self, capacity: int):
        self.cap = capacity
        self.map = {}
        self.head, self.tail = Node(0, 0), Node(0, 0)
        self.head.next = self.tail
        self.tail.prev = self.head

    def _remove(self, node):
        p, n = node.prev, node.next
        p.next = n
        n.prev = p

    def _add(self, node):
        p, n = self.tail.prev, self.tail
        p.next = n.prev = node
        node.prev, node.next = p, n

    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        self._remove(node)
        self._add(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            self._remove(self.map[key])
        node = Node(key, value)
        self._add(node)
        self.map[key] = node
        if len(self.map) > self.cap:
            lru = self.head.next
            self._remove(lru)
            del self.map[lru.key]


class BrowserHistory:
    def __init__(self, homepage: str):
        self.back_stack = [homepage]
        self.forward_stack = []

    def visit(self, url: str) -> None:
        self.back_stack.append(url)
        self.forward_stack.clear()

    def back(self, steps: int) -> str:
        while steps > 0 and len(self.back_stack) > 1:
            self.forward_stack.append(self.back_stack.pop())
            steps -= 1
        return self.back_stack[-1]

    def forward(self, steps: int) -> str:
        while steps > 0 and self.forward_stack:
            self.back_stack.append(self.forward_stack.pop())
            steps -= 1
        return self.back_stack[-1]



class MyCircularQueue:

    def __init__(self, k: int):
        self.tail = None
        self.head = None
        self.maxSize = k
        self.size = 0
  

    def enQueue(self, value: int) -> bool:
        if(self.size >= self.maxSize):
            return False
        temp = Node(value)
        if(self.tail):

            self.tail.next = temp
            temp.prev = self.tail
            self.tail = temp

            self.head.prev = self.tail 
            self.tail.next = self.head
        else:
            self.head = temp
            self.tail = temp 
            temp.next = temp
            temp.prev = temp
        self.size += 1
        return True

    def deQueue(self) -> bool:
        if(self.size ==0 ):
            return False
        if( self.size > 1):
            self.head = self.head.next
            self.head.prev = self.tail
            self.tail.next = self.head
        else:
            self.head = None
            self.tail = None
        self.size -= 1
        return True
  
  

    def Front(self) -> int:
        if(self.head):
            return self.head.val
        return -1
  

    def Rear(self) -> int:
        if(self.tail):
            return self.tail.val
        return -1
  

    def isEmpty(self) -> bool:
        if(self.head is None):
            return True
        return False
  

    def isFull(self) -> bool:
        return self.size == self.maxSize



# BST to Sorted Doubly List (426)
def treeToDoublyList( root: 'Node') -> 'Node':
        if not root:
            return None

        def inorder(node):
            nonlocal last, first
            if not node:
                return
            inorder(node.left)
            if last:
                last.right = node
                node.left = last
            else:
                first = node
            last = node
            inorder(node.right)

        first = last = None
        inorder(root)
        first.left = last
        last.right = first
        return first

# Flatten Multilevel List (430)
 def flatten(self, head: 'Node') -> 'Node':
        if not head:
            return None

        def dfs(node):
            curr = node
            last = node
            while curr:
                nxt = curr.next
                if curr.child:
                    child_last = dfs(curr.child)
                    curr.next = curr.child
                    curr.child.prev = curr
                    curr.child = None
                    if nxt:
                        child_last.next = nxt
                        nxt.prev = child_last
                    last = child_last
                else:
                    last = curr
                curr = nxt
            return last

        dfs(head)
        return head


```
