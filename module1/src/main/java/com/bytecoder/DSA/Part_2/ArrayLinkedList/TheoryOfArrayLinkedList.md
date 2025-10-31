
# **Complete revision guide with 45+ essential problems**

---

## 📚 Table of Contents

1. [Quick Reference](#quick-reference)
2. [Essential Patterns](#essential-patterns)
3. [Easy Problems](#easy-problems)
4. [Medium Problems](#medium-problems)
5. [Hard Problems](#hard-problems)

---

## 📚 Quick Reference Guide

### ⚡ Time & Space Complexity Cheat Sheet

| Operation                  | Time       | Space          |
| -------------------------- | ---------- | -------------- |
| Access/Search              | O(n)       | O(1)           |
| Insert/Delete (known pos)  | O(1)       | O(1)           |
| Reverse List               | O(n)       | O(1) iterative |
| Find Middle (Two Pointers) | O(n)       | O(1)           |
| Cycle Detection            | O(n)       | O(1)           |
| Merge Two Lists            | O(n+m)     | O(1)           |
| Merge k Lists (Heap)       | O(N log k) | O(k)           |
| Sort List (Merge Sort)     | O(n log n) | O(log n)       |

### 🎯 Meta Interview Tips

- **Master Design Problems:** All 5 design patterns in Patterns section - especially LRU Cache!
- **Practice Two Pointers:** Most efficient pattern for LinkedList problems
- **Always consider edge cases:** null, single node, two nodes, cycles
- **Draw diagrams:** Visualize pointer movements before coding
- **Start with brute force:** Then optimize to O(1) space
- **Use dummy nodes:** Simplifies head modifications
- **Test with examples:** Walk through 3-4 node example
- **Discuss trade-offs:** Time vs space, iterative vs recursive
- **Know design patterns:** When to use HashMap+DLL vs Array vs pure LinkedList

### 🔥 Must-Know Code Templates

#### 1. Reverse LinkedList (Iterative)

```java
ListNode prev = null, curr = head;
while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}
return prev;
```

#### 2. Find Middle (Two Pointers)

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
return slow;
```

#### 3. Detect Cycle

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) return true;
}
return false;
```

#### 4. Find Cycle Start

```java
// After detecting cycle
slow = head;
while (slow != fast) {
    slow = slow.next;
    fast = fast.next;
}
return slow;
```

#### 5. Merge Two Sorted Lists

```java
ListNode dummy = new ListNode(0), curr = dummy;
while (l1 != null && l2 != null) {
    if (l1.val <= l2.val) {
        curr.next = l1;
        l1 = l1.next;
    } else {
        curr.next = l2;
        l2 = l2.next;
    }
    curr = curr.next;
}
curr.next = (l1 != null) ? l1 : l2;
return dummy.next;
```

#### 6. Remove Nth From End

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode fast = dummy, slow = dummy;

for (int i = 0; i <= n; i++) fast = fast.next;
while (fast != null) {
    slow = slow.next;
    fast = fast.next;
}
slow.next = slow.next.next;
return dummy.next;
```

### 💡 Problem-Solving Framework

1. **Understand the problem:** Draw example with 3-4 nodes
2. **Identify pattern:** Two pointers? Reversal? Merge? Stack?
3. **Consider edge cases:** null, single node, even/odd length
4. **Choose approach:** Iterative vs recursive (discuss trade-offs)
5. **Write clean code:** Use meaningful variable names
6. **Test thoroughly:** Walk through example step-by-step
7. **Analyze complexity:** State time and space clearly
8. **Optimize if possible:** Can you reduce space to O(1)?

### 🚨 Common Mistakes to Avoid

- ❌ Forgetting to handle null head
- ❌ Not using dummy node when head changes
- ❌ Losing reference to next node before modifying pointers
- ❌ Infinite loops in cycle problems
- ❌ Off-by-one errors in two-pointer problems
- ❌ Not breaking circular references (memory leak)
- ❌ Forgetting to return correct head after modifications
- ❌ Not checking fast.next before accessing fast.next.next

### 📊 Priority Order for Meta Prep

#### 🔴 Critical (Must Know)

- **LRU Cache (146)** - #1 Priority
- Reverse Linked List (206)
- Merge Two Sorted Lists (21)
- Linked List Cycle (141, 142)
- Add Two Numbers (2)
- Remove Nth From End (19)

#### 🟡 High Priority

- Copy List with Random Pointer (138)
- Merge k Sorted Lists (23)
- Reverse Nodes in k-Group (25)
- Reorder List (143)
- Palindrome Linked List (234)
- Sort List (148)

#### 🟢 Medium Priority

- Odd Even Linked List (328)
- Swap Nodes in Pairs (24)
- Reverse Linked List II (92)
- Flatten Multilevel List (430)
- Design Browser History (1472)

#### 🔵 Good to Know

- Design HashMap (706)
- Design Skiplist (1206)
- Remove Zero Sum Sublists (1171)
- Split List in Parts (725)

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

### 2. Dummy Node Technique

**Use Cases:** Simplifying head modifications, edge case handling

**💡 Key Insight:** Create a dummy node pointing to head to avoid special cases when head changes. Always return dummy.next.

**Common Problems:**

- Merge Two Sorted Lists (21)
- Remove Duplicates (83, 82)
- Remove Nth Node (19)
- Partition List (86)

### 3. In-Place Reversal

**Use Cases:** Reversing entire list or sublists without extra space

**💡 Key Insight:** Keep track of prev, curr, next pointers. Iteratively change curr.next to prev.

**Common Problems:**

- Reverse Linked List (206)
- Reverse Linked List II (92)
- Swap Nodes in Pairs (24)
- Reverse Nodes in k-Group (25)
- Reorder List (143)

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
- Remove Zero Sum Sublists (1171)

### 6. Priority Queue/Heap

**Use Cases:** Merging multiple sorted lists

**💡 Key Insight:** Keep k nodes in min-heap, always extract minimum and add its next node.

**Common Problems:**

- Merge k Sorted Lists (23)

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

- LRU Cache (146)
- Design Browser History (1472)
- BST to Sorted Doubly List (426)
- Flatten Multilevel List (430)

### 9. Design Problems - LRU Cache Pattern ⭐⭐⭐

**CRITICAL FOR META!** Most frequently asked LinkedList design problem.

#### LRU Cache (LC 146)

**💡 Core Design:**

- HashMap + Doubly LinkedList combination
- HashMap: O(1) access to any node
- DLL: O(1) insertion/deletion, maintains LRU order
- Most recent at head, least recent at tail
- Dummy head & tail nodes simplify edge cases

**Operations:**

- **get(key):** Return value & move to head (mark as recently used)
- **put(key, value):** Insert/update & move to head, evict tail if full

**Implementation Template:**

```java
class LRUCache {
    class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }
  
    private Map<Integer, Node> map;
    private Node head, tail;
    private int capacity;
  
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
  
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        moveToHead(node);
        return node.value;
    }
  
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (map.size() >= capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
  
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
  
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
  
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
}
```

**Complexity:**

- **Time:** O(1) for both get and put
- **Space:** O(capacity)

**⚡ Meta Interview Tips:**

- Be ready to implement from scratch in 20-25 minutes
- Explain the trade-offs: Why not just HashMap? Why not just array?
- Discuss thread safety and concurrency considerations
- Know variations: LFU Cache, Time-based eviction
- Can you make it generic? Support different eviction policies?

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

### 1. Reverse Linked List (LC 206)

- **Difficulty:** Easy
- **Tags:** Reversal, Two Pointers
- **Problem:** Reverse a singly linked list.
- **Time:** O(n) | **Space:** O(1) iterative, O(n) recursive
- **Key Approach:** Maintain prev, curr, next pointers. Iteratively change curr.next to prev.

### 2. Merge Two Sorted Lists (LC 21)

- **Difficulty:** Easy
- **Tags:** Merge, Dummy Node
- **Problem:** Merge two sorted lists into one sorted list.
- **Time:** O(n + m) | **Space:** O(1)

### 3. Linked List Cycle (LC 141)

- **Difficulty:** Easy
- **Tags:** Two Pointers, Floyd's Algorithm
- **Problem:** Detect if linked list has a cycle.
- **Time:** O(n) | **Space:** O(1)
- **Floyd's Tortoise & Hare:** If fast and slow pointers meet, cycle exists.

### 4. Middle of Linked List (LC 876)

- **Difficulty:** Easy
- **Tags:** Two Pointers
- **Problem:** Return the middle node of linked list.
- **Time:** O(n) | **Space:** O(1)

### 5. Remove Duplicates from Sorted List (LC 83)

- **Difficulty:** Easy
- **Tags:** Two Pointers
- **Problem:** Remove duplicate nodes from sorted list.
- **Time:** O(n) | **Space:** O(1)

### 6. Delete Node in Linked List (LC 237)

- **Difficulty:** Easy
- **Tags:** Pointer Manipulation
- **Problem:** Delete a node when only given access to that node.
- **Trick:** Copy next node's value to current, then skip next node.

### 7. Binary to Integer (LC 1290)

- **Difficulty:** Easy
- **Tags:** Bit Manipulation
- **Problem:** Convert binary linked list to integer.
- **Time:** O(n) | **Space:** O(1)
- **Formula:** result = (result << 1) | node.val

### 8. Intersection of Two Lists (LC 160)

- **Difficulty:** Easy
- **Tags:** Two Pointers
- **Problem:** Find intersection point of two lists.
- **Time:** O(n + m) | **Space:** O(1)
- **Trick:** When pointer reaches end, redirect to other list's head. They'll meet at intersection.

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

### 1. Add Two Numbers (LC 2)

- **Difficulty:** Medium
- **Tags:** Dummy Node, Math
- **Problem:** Add two numbers represented as linked lists (reversed order).
- **Time:** O(max(n,m)) | **Space:** O(max(n,m))

### 2. Add Two Numbers II (LC 445)

- **Difficulty:** Medium
- **Tags:** Stack
- **Problem:** Add two numbers (most significant digit first).
- **Time:** O(max(n,m)) | **Space:** O(max(n,m))

### 3. Remove Nth Node From End (LC 19)

- **Difficulty:** Medium
- **Tags:** Two Pointers, Dummy Node
- **Problem:** Remove nth node from end in one pass.
- **Time:** O(n) | **Space:** O(1)
- **Trick:** Move fast n+1 steps ahead, then move both until fast reaches end.

### 4. Swap Nodes in Pairs (LC 24)

- **Difficulty:** Medium
- **Tags:** Reversal
- **Problem:** Swap every two adjacent nodes.
- **Time:** O(n) | **Space:** O(1) iterative, O(n) recursive

### 5. Odd Even Linked List (LC 328)

- **Difficulty:** Medium
- **Tags:** Split & Merge
- **Problem:** Group odd and even positioned nodes together.
- **Time:** O(n) | **Space:** O(1)

### 6. Reorder List (LC 143)

- **Difficulty:** Medium
- **Tags:** Two Pointers, Reversal
- **Problem:** Reorder L0→L1→...→Ln to L0→Ln→L1→Ln-1→...
- **Time:** O(n) | **Space:** O(1)
- **Steps:** 1) Find middle 2) Reverse second half 3) Merge alternately

### 7. Palindrome Linked List (LC 234)

- **Difficulty:** Medium
- **Tags:** Two Pointers, Reversal
- **Problem:** Check if linked list is palindrome.
- **Time:** O(n) | **Space:** O(1)

### 8. Reverse Linked List II (LC 92)

- **Difficulty:** Medium
- **Tags:** Reversal
- **Problem:** Reverse nodes from position left to right.
- **Time:** O(n) | **Space:** O(1)

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

### 25. Segregate Even and Odd Values

- **Difficulty:** Medium
- **Tags:** Split & Merge
- **Problem:** Segregate nodes by value (even values first, then odd values). Maintain relative order.
- **Example:** `[2,1,3,5,6,4,7]` → `[2,6,4,1,3,5,7]`
- **Time:** O(n) | **Space:** O(1)
- **Approach:** Create two separate lists for even and odd values, then concatenate.

```java
public ListNode segregateEvenOdd(ListNode head) {
    if (head == null || head.next == null) return head;
  
    ListNode evenDummy = new ListNode(0);
    ListNode oddDummy = new ListNode(0);
    ListNode even = evenDummy, odd = oddDummy;
  
    while (head != null) {
        if (head.val % 2 == 0) {
            even.next = head;
            even = even.next;
        } else {
            odd.next = head;
            odd = odd.next;
        }
        head = head.next;
    }
  
    odd.next = null;
    even.next = oddDummy.next;
    return evenDummy.next;
}
```

### 26. Intersection of Two Sorted Lists (Common Elements)

- **Difficulty:** Medium
- **Tags:** Two Pointers, Merge
- **Problem:** Find common elements in two sorted lists.
- **Example:** `[1,2,3,4]` and `[2,4,6,8]` → `[2,4]`
- **Time:** O(n + m) | **Space:** O(1)
- **Approach:** Traverse both lists simultaneously, add matching elements.

```java
public ListNode getIntersection(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0), curr = dummy;
  
    while (l1 != null && l2 != null) {
        if (l1.val == l2.val) {
            curr.next = new ListNode(l1.val);
            curr = curr.next;
            l1 = l1.next;
            l2 = l2.next;
        } else if (l1.val < l2.val) {
            l1 = l1.next;
        } else {
            l2 = l2.next;
        }
    }
    return dummy.next;
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

## 🔴 Hard Problems

### 1. Merge k Sorted Lists (LC 23)

- **Difficulty:** Hard
- **Tags:** Priority Queue, Divide & Conquer
- **Problem:** Merge k sorted linked lists into one.
- **Priority Queue:** O(N log k) time, O(k) space
- **Divide & Conquer:** O(N log k) time, O(1) space
- **Approach 1:** Use min-heap to always extract smallest element.
- **Approach 2:** Repeatedly merge pairs of lists.

### 2. Reverse Nodes in k-Group (LC 25)

- **Difficulty:** Hard
- **Tags:** Reversal, Recursion
- **Problem:** Reverse nodes in groups of k.
- **Time:** O(n) | **Space:** O(n/k) recursive, O(1) iterative
- **Steps:** 1) Count k nodes 2) Reverse if k exists 3) Recursively process rest 4) Connect groups

### 3. Design Skiplist (LC 1206)

- **Difficulty:** Hard
- **Tags:** Design, Probabilistic DS
- **Problem:** Design skiplist without built-in libraries.
- **Time:** O(log n) average for search/insert/delete | **Space:** O(n)
- **Key Concept:** Multi-level linked list. Each level is express lane. Use randomization for level promotion (P=0.25).

---

## 📝 Summary

This guide covers **45+ LinkedList problems** organized by difficulty, with:

- ✅ **Quick Reference** for fast revision
- ✅ **13 Essential Patterns** with detailed explanations
- ✅ **Code Templates** for common operations
- ✅ **Priority Order** for Meta preparation
- ✅ **1-Week Study Plan** for systematic prep
- ✅ **Complete problem list** with complexity analysis

**Perfect for FAANG interviews, especially Meta!**
