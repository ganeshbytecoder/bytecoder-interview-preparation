### 🎨 Essential Patterns for Meta Interviews

### 1. Two Pointers (Fast & Slow)

**Use Cases:** Finding middle, cycle detection, cycle start, palindrome check

**💡 Key Insight:** Slow moves 1 step, fast moves 2 steps. When they meet in a cycle, distance from head to cycle start = distance from meeting point to cycle start.

**Common Problems:**

- Middle of LinkedList (876)
- Linked List Cycle (141)
- Linked List Cycle II (142)
- Palindrome Linked List (234)
- Remove Nth From End (19)

```python


def middle(node):
	slow,fast = node, node
	while(fast and fast.next):
		slow = slow.next
		fast = fast.next.next
	# this will give second element in even length to get first use fast.next and fast.next.next

	return slow 

def isCycle(node):
	slow,fast = node, node
	while(fast and fast.next):
		slow = slow.next
		fast = fast.next.next
		if(fast==slow):
			return True
	return False

def getStart(node):
	slow,fast = node, node
	while(fast and fast.next):
		slow = slow.next
		fast = fast.next.next
		if(fast==slow):
			break
	slow = node
	while(fast==slow):
		slow = slow.next
		fast = fast.next
	return slow

self.head = root

def palindromeLinkedList(node):
	if(node is None): return True

	temp = palindromeLinkedList(node.next)
	if(node.val == self.head.val and temp):
		self.head = self.head.next
		return True
	else:
		return False

def Remove Nth From End(node, n):
	dummy = Node(-1)
	dummy.next = node
	// 1,2,3,4,5. -> n = 2(node 4)
	// d-1-2-3-4-5 
	for i in range(n+1): fast = fast.next

	while(fast):
		slow = slow.next
		fast = fast.next
	slow.next = slow.next.next
	return node


```

### 2. Dummy Node Technique

**Use Cases:** Simplifying head modifications, edge case handling

**💡 Key Insight:** Create a dummy node pointing to head to avoid special cases when head changes. Always return dummy.next.

**Common Problems:**

- Merge Two Sorted Lists (21)
- Remove Duplicates (83, 82)
- Remove Nth Node (19)
- Partition List (86)

```python
l =[1,5,8,10] l2 = [2,3,5,9,10]

```

### 3. In-Place Reversal

**Use Cases:** Reversing entire list or sublists without extra space

**💡 Key Insight:** Keep track of prev, curr, next pointers. Iteratively change curr.next to prev.

**Common Problems:**

- **Reverse Linked List (206)**
- **Reverse Linked List II (92) between(n,m)**
- **Swap Nodes in Pairs (24)**
- **Reverse Nodes in k-Group (25)**
- **Reorder List (143)**

```python

class Solution:
    def reverseBetween(self, head: Optional[ListNode], left: int, right: int) -> Optional[ListNode]:
        if not head or left == right:
            return head

        dummy = ListNode(-1)
        dummy.next = head
        prev = dummy

        # Step 1: Move `prev` to the node just before `left`
        for _ in range(left - 1):
            prev = prev.next

        # Step 2: Reverse the sublist between left and right
        curr = prev.next
        nxt = None
        prev_sub = None
        for _ in range(right - left + 1):
            nxt = curr.next
            curr.next = prev_sub
            prev_sub = curr
            curr = nxt

        # Step 3: Connect the reversed sublist
        # prev is node before left
        # curr is node after right
        prev.next.next = curr      # left node becomes tail
        prev.next = prev_sub       # connect to new head of reversed section

        return dummy.next
```

### 4. Stack for Reverse Processing

**Use Cases:** Processing nodes in reverse order, comparing values

**💡 Key Insight:** Push all nodes to stack, then pop for reverse order. Useful when you can't modify the list.

**Common Problems:**

- Add Two Numbers II (445)
- Remove Nodes (2487)
- Next Greater Node (1019)
- Palindrome Check (234 - alternative)

### 5. HashMap/HashSet for Tracking

**Use Cases:** Tracking visited nodes, cloning with random pointers

**💡 Key Insight:** Map original nodes to copied/processed nodes. Useful for cycle detection and deep copy.

**Common Problems:**

- Copy List with Random Pointer (138)
- Intersection of Two Lists (160)
- **Remove Zero Sum Sublists (1171)**

### 6. Priority Queue/Heap

**Use Cases:** Merging multiple sorted lists

**💡 Key Insight:** Keep k nodes in min-heap, always extract minimum and add its next node.

**Common Problems:**

- **Merge k Sorted Lists (23)**

**Complexity:**

- **Time:** O(N log k) where N is total nodes
- **Space:** O(k) for heap

### 7. Merge/Split Patterns

**Use Cases:** Combining or dividing lists based on criteria

**Common Problems:**

- Odd Even Linked List (328)
- Partition List (86)
- Split List in Parts (725)

### 8. Doubly Linked List

**Use Cases:** LRU cache, browser history, bidirectional traversal

**💡 Key Insight:** Use dummy head and tail to simplify boundary conditions. Always update both prev and next pointers.

**Common Problems:**

- **LRU Cache (146)**
- **Design Browser History (1472)**
- **BST to Sorted Doubly List (426)**
- **Flatten Multilevel List (430)**

### 10. Design Problems - HashMap/HashSet Pattern

#### Design HashMap (LC 706)

**Use Cases:** Understanding hash collisions, chaining with LinkedList

**💡 Core Design:**

- Array of LinkedLists (buckets)
- Hash function: key % SIZE determines bucket
- Chaining: Store collisions in LinkedList per bucket
- Use dummy node in each bucket for simplicity

**Key Operations:**

```java
class MyHashMap {
    class ListNode {
        int key, val;
        ListNode next;
        ListNode(int k, int v) { key = k; val = v; }
    }
  
    private ListNode[] buckets;
    private static final int SIZE = 10000;
  
    public MyHashMap() {
        buckets = new ListNode[SIZE];
    }
  
    private int getIndex(int key) {
        return key % SIZE;
    }
  
    public void put(int key, int value) {
        int idx = getIndex(key);
        if (buckets[idx] == null) {
            buckets[idx] = new ListNode(-1, -1); // dummy
        }
        ListNode prev = find(key, idx);
        if (prev.next == null) {
            prev.next = new ListNode(key, value);
        } else {
            prev.next.val = value;
        }
    }
  
    public int get(int key) {
        int idx = getIndex(key);
        if (buckets[idx] == null) return -1;
        ListNode prev = find(key, idx);
        return prev.next == null ? -1 : prev.next.val;
    }
  
    private ListNode find(int key, int idx) {
        ListNode prev = buckets[idx];
        ListNode curr = prev.next;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }
}
```

**Complexity:**

- **Time:** O(1) average, O(n) worst case
- **Space:** O(n + m) where m is SIZE

### 11. Design Problems - Circular Queue Pattern

#### Design Circular Queue (LC 622)

**Use Cases:** Fixed-size queue with wraparound

**💡 Core Design:**

- Array-based implementation with modular arithmetic
- Track head, tail, size, capacity
- Use (index + 1) % capacity for wraparound
- Can also implement with circular LinkedList

**Complexity:**

- **Time:** O(1) all operations
- **Space:** O(n)

### 12. Design Problems - Browser History Pattern

#### Design Browser History (LC 1472)

**Use Cases:** Bidirectional navigation with history clearing

**💡 Core Design:**

- Doubly LinkedList for back/forward navigation
- Current pointer tracks current page
- Visit: clear forward history, add new page
- Back/Forward: move pointer, respect boundaries

**Complexity:**

- **Time:** O(1) visit, O(min(steps, n)) back/forward
- **Space:** O(n)

### 13. Design Problems - Skiplist Pattern (Advanced)

#### Design Skiplist (LC 1206)

**Use Cases:** Fast search/insert/delete without balancing (alternative to BST)

**💡 Core Design:**

- Multi-level LinkedList structure
- Bottom level: complete sorted list
- Higher levels: "express lanes" with fewer nodes
- Probabilistic promotion: P = 0.25 (each level has ~25% of nodes)
- Search from top level, drop down when needed

**Key Characteristics:**

- O(log n) average time for search/insert/delete
- No rebalancing needed (unlike Red-Black trees)
- Simpler implementation than balanced trees
- Randomization ensures good average performance

**Complexity:**

- **Time:** O(log n) average for all operations
- **Space:** O(n)

**⚡ When to Use:**

- Need fast search in sorted data
- Frequent insertions/deletions
- Simpler than self-balancing trees
- Concurrent access scenarios (lock fewer nodes)

---

## 🟢 Easy Problems

### 5. Remove Duplicates from Sorted List (LC 83)

- **Difficulty:** Easy
- **Tags:** Two Pointers
- **Problem:** Remove duplicate nodes from sorted list.
- **Time:** O(n) | **Space:** O(1)

### 7. Binary to Integer (LC 1290)

- **Difficulty:** Easy
- **Tags:** Bit Manipulation
- **Problem:** Convert binary linked list to integer.
- **Time:** O(n) | **Space:** O(1)
- **Formula:** result = (result << 1) | node.val

### 9. Move Last Element to Front

- **Difficulty:** Easy
- **Tags:** Pointer Manipulation
- **Problem:** Move the last node to the front of the list.
- **Example:** `2→5→6→2→1` becomes `1→2→5→6→2`
- **Time:** O(n) | **Space:** O(1)
- **Approach:** Traverse to second-to-last node, unlink last node, make it new head.

```java
public ListNode moveLastToFront(ListNode head) {
    if (head == null || head.next == null) return head;
  
    ListNode prev = null, curr = head;
    while (curr.next != null) {
        prev = curr;
        curr = curr.next;
    }
  
    prev.next = null;
    curr.next = head;
    return curr;
}
```

---

## 🟡 Medium Problems

### 9. Rotate List (LC 61)

- **Difficulty:** Medium
- **Tags:** Two Pointers
- **Problem:** Rotate list to the right by k places.
- **Time:** O(n) | **Space:** O(1)

### 10. Remove Duplicates II (LC 82)

- **Difficulty:** Medium
- **Tags:** Dummy Node
- **Problem:** Remove all nodes that have duplicates.
- **Time:** O(n) | **Space:** O(1)

### 11. Copy List with Random Pointer (LC 138)

- **Difficulty:** Medium
- **Tags:** HashMap, Interweaving
- **Problem:** Deep copy linked list with random pointers.
- **Time:** O(n) | **Space:** O(n) with map, O(1) with interweaving

### 12. Linked List Cycle II (LC 142)

- **Difficulty:** Medium
- **Tags:** Two Pointers, Floyd's Algorithm
- **Problem:** Find the starting point of cycle.
- **Time:** O(n) | **Space:** O(1)
- **Key:** After meeting, reset one to head. Move both 1 step until they meet again.

### 13. Sort List (LC 148)

- **Difficulty:** Medium
- **Tags:** Merge Sort, Two Pointers
- **Problem:** Sort linked list using merge sort.
- **Time:** O(n log n) | **Space:** O(log n)

### 14. Insertion Sort List (LC 147)

- **Difficulty:** Medium
- **Tags:** Sorting
- **Problem:** Sort list using insertion sort.
- **Time:** O(n²) | **Space:** O(1)

### 15. Split List in Parts (LC 725)

- **Difficulty:** Medium
- **Tags:** Split
- **Problem:** Split list into k consecutive parts.
- **Time:** O(n + k) | **Space:** O(k)

### 16. Remove Zero Sum Sublists (LC 1171)

- **Difficulty:** Medium
- **Tags:** HashMap, Prefix Sum
- **Problem:** Remove consecutive nodes that sum to zero.
- **Time:** O(n) | **Space:** O(n)

### 17. Next Greater Node (LC 1019)

- **Difficulty:** Medium
- **Tags:** Stack
- **Problem:** Find next greater element for each node.
- **Time:** O(n) | **Space:** O(n)

### 18. Remove Nodes (LC 2487)

- **Difficulty:** Medium
- **Tags:** Stack, Reversal
- **Problem:** Remove nodes with greater value to their right.
- **Time:** O(n) | **Space:** O(n) with stack, O(1) with reversal

### 19. Sorted List to BST (LC 109)

- **Difficulty:** Medium
- **Tags:** Two Pointers, Recursion
- **Problem:** Convert sorted list to height-balanced BST.
- **Time:** O(n log n) | **Space:** O(log n)

### 20. Reverse Even Length Groups (LC 2074)

- **Difficulty:** Medium
- **Tags:** Reversal
- **Problem:** Reverse each group of even length.
- **Time:** O(n) | **Space:** O(1)

### 21. Flatten Multilevel List (LC 430)

- **Difficulty:** Medium
- **Tags:** DFS, Stack
- **Problem:** Flatten multilevel doubly linked list.
- **Time:** O(n) | **Space:** O(n)

### 22. BST to Sorted Doubly List (LC 426)

- **Difficulty:** Medium
- **Tags:** In-order Traversal
- **Problem:** Convert BST to circular sorted doubly list.
- **Time:** O(n) | **Space:** O(log n)

### 23. Design Circular Queue (LC 622)

- **Difficulty:** Medium
- **Tags:** Array/LinkedList
- **Problem:** Implement circular queue.
- **Time:** O(1) all operations | **Space:** O(n)

### 24. Remove Duplicates from Unsorted List

- **Difficulty:** Medium
- **Tags:** HashMap, Two Pointers
- **Problem:** Remove duplicate values from unsorted linked list.
- **Time:** O(n) | **Space:** O(n)
- **Approach:** Use HashSet to track seen values. Remove nodes with duplicate values.

```java
public ListNode removeDuplicates(ListNode head) {
    if (head == null) return null;
  
    Set<Integer> seen = new HashSet<>();
    ListNode curr = head, prev = null;
  
    while (curr != null) {
        if (seen.contains(curr.val)) {
            prev.next = curr.next;
        } else {
            seen.add(curr.val);
            prev = curr;
        }
        curr = curr.next;
    }
    return head;
}
```

### 27. Add One to Number as Linked List

- **Difficulty:** Medium
- **Tags:** Recursion, Stack, Reversal
- **Problem:** Add 1 to a number represented as linked list.
- **Example:** `1→2→3` → `1→2→4`, `9→9→9` → `1→0→0→0`
- **Time:** O(n) | **Space:** O(n) recursive/stack, O(1) with reversal

**Approach 1: Reverse List**

```java
public ListNode addOne(ListNode head) {
    head = reverse(head);
    ListNode curr = head;
    int carry = 1;
  
    while (curr != null && carry > 0) {
        int sum = curr.val + carry;
        curr.val = sum % 10;
        carry = sum / 10;
        if (curr.next == null && carry > 0) {
            curr.next = new ListNode(carry);
            carry = 0;
        }
        curr = curr.next;
    }
    return reverse(head);
}
```

**Approach 2: Recursion**

```java
public ListNode addOne(ListNode head) {
    int carry = addOneHelper(head);
    if (carry > 0) {
        ListNode newHead = new ListNode(carry);
        newHead.next = head;
        return newHead;
    }
    return head;
}

private int addOneHelper(ListNode node) {
    if (node == null) return 1;
    int carry = addOneHelper(node.next);
    int sum = node.val + carry;
    node.val = sum % 10;
    return sum / 10;
}
```

### 28. Split Circular Linked List into Two Halves

- **Difficulty:** Medium
- **Tags:** Two Pointers, Circular List
- **Problem:** Split a circular linked list into two equal halves.
- **Time:** O(n) | **Space:** O(1)
- **Approach:** Use slow-fast pointers to find middle, then split.

```java
public ListNode[] splitCircular(ListNode head) {
    if (head == null) return new ListNode[]{null, null};
  
    ListNode slow = head, fast = head;
    while (fast.next != head && fast.next.next != head) {
        slow = slow.next;
        fast = fast.next.next;
    }
  
    ListNode head2 = slow.next;
    slow.next = head;
  
    // Find end of second half
    ListNode curr = head2;
    while (curr.next != head) {
        curr = curr.next;
    }
    curr.next = head2;
  
    return new ListNode[]{head, head2};
}
```

### 29. Bubble Sort for Linked List

- **Difficulty:** Medium
- **Tags:** Sorting
- **Problem:** Sort linked list using bubble sort.
- **Time:** O(n²) | **Space:** O(1)

```java
public ListNode bubbleSort(ListNode head) {
    if (head == null) return null;
  
    boolean swapped;
    do {
        swapped = false;
        ListNode curr = head;
        while (curr.next != null) {
            if (curr.val > curr.next.val) {
                // Swap values
                int temp = curr.val;
                curr.val = curr.next.val;
                curr.next.val = temp;
                swapped = true;
            }
            curr = curr.next;
        }
    } while (swapped);
  
    return head;
}
```

### 30. Selection Sort for Linked List

- **Difficulty:** Medium
- **Tags:** Sorting
- **Problem:** Sort linked list using selection sort.
- **Time:** O(n²) | **Space:** O(1)

```java
public ListNode selectionSort(ListNode head) {
    ListNode curr = head;
  
    while (curr != null) {
        ListNode min = curr;
        ListNode temp = curr.next;
  
        while (temp != null) {
            if (temp.val < min.val) {
                min = temp;
            }
            temp = temp.next;
        }
  
        // Swap values
        int tempVal = curr.val;
        curr.val = min.val;
        min.val = tempVal;
  
        curr = curr.next;
    }
    return head;
}
```

---

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

---
