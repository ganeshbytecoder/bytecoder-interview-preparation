## LinkedList:

1. Reverse LinkedList (Iterative)

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

### 24. Remove Duplicates from Unsorted List

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

### 1. Two Pointers (Fast & Slow)

**Use Cases:** Finding middle, cycle detection, cycle start, palindrome check

**💡 Key Insight:** Slow moves 1 step, fast moves 2 steps. When they meet in a cycle, distance from head to cycle start = distance from meeting point to cycle start.

**Common Problems:**

- Middle of LinkedList (876)
- Linked List Cycle (141)
- Linked List Cycle II (142)
- **Palindrome Linked List (234)**
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

def getStartOfCycle(node):
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
- **Reverse Even Length Groups (LC 2074)**

```python


def reverseLL(node):
	curr = node
	prev = None

	while(curr):
		temp = curr.next 
		curr.next = prev
		prev = curr
		curr = curr.next
	return prev

def reverseLL(node, left, right ):

	dummy = Node(0)
	dummy.next  = node
	curr = dummy.next 
	prev = dummy
	for _ in range(left-1):prev = prev.next 

#	1->,2,3,4,5,6
	curr = prev.next
	for _ in range(right-left):
		temp = curr.next
		curr.next = temp.next
		temp.next = prev.next
		prev.next = temp
	return dummy.next

def swapInPair(node):
	dummy = ListNode(0, node)
	curr = dummy.next
	prev = dummy

	while(curr and curr.next):
		temp = curr.next.next
		prev.next,curr.next.next, curr.next = curr.next, curr, temp

		prev = curr
		curr = temp
	return dummy.next



 def reverseKGroup( head: Optional[ListNode], k: int) -> Optional[ListNode]:
        if(k<=1):
            return head
        def get_kth(curr, k):
            while curr and k > 0:
                curr = curr.next
                k -= 1
            return curr

        dummy = ListNode(0, head)
  
        prevGroupH = dummy
        curr = dummy.next
        while(curr):
            kth = get_kth(curr, k-1) # last element of current k group

            if(kth is None ):
                break

            nextH = temp.next # first element of next group 

            prev = temp.next # prev will point to next group head so if next group is not complete then it will point in same order c

            while(curr and curr != nextH ):
                t = curr.next 
                curr.next = prev
                prev = curr
                curr = t
   
            nexttt = prevGroupH.next
            prevGroupH.next = temp
            prevGroupH = nexttt
        return dummy.next

def reorderList( head: Optional[ListNode]) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        slow = head 
        fast = head

        while(fast and fast.next):
            slow = slow.next
            fast = fast.next.next
  
        curr = slow.next
        slow.next = None
        prev = None

        while(curr):
            temp = curr.next 
            curr.next = prev
            prev = curr 
            curr = temp
        l1 = head 
        l2 = prev

        while(l1 and l2):
            temp1 = l1.next
  
            temp2 = l2.next
            l1.next = l2
            l2.next = temp1

            l1 = temp1
            l2 = temp2
        return head






```

### 4. Stack for Reverse Processing

**Use Cases:** Processing nodes in reverse order, comparing values

**💡 Key Insight:** Push all nodes to stack, then pop for reverse order. Useful when you can't modify the list.

**Common Problems:**

- Add Two Numbers II (445)
- Remove Nodes (2487)
- **Next Greater Node (1019)**
- Palindrome Check (234 - alternative)

```python

def addTwoNumbers( l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        s1 = []
        s2 = []
        curr1 = l1
        curr2 = l2
        while(curr1 ):
            s1.append(curr1.val)
            curr1 = curr1.next 
        while( curr2):
            s2.append(curr2.val)
            curr2 =  curr2.next

        dummy = ListNode(0)
        curr = dummy 
        carry = 0
        while(s1 or s2):
            temp = (int(s1.pop()) if s1 else 0) + (int(s2.pop()) if s2 else 0) + carry 
            carry = temp//10
            curr.next = ListNode(temp%10)
            curr = curr.next 
        if(carry>0):
            curr.next = ListNode(carry)
            curr = curr.next 

        curr = dummy.next
        prev = None

        while(curr):
            t = curr.next 
            curr.next = prev
            prev = curr
            curr = t
        return prev

def removeNodes(self, head: Optional[ListNode]) -> Optional[ListNode]:
        st = []
        curr = head
        while(curr):
            while(st and st[-1]< curr.val):
                st.pop()
            st.append(curr.val)
            curr = curr.next
        dummy = ListNode(0)
        curr = dummy
        while(st):
            curr.next = ListNode(st.pop(0))
            curr = curr.next
        return dummy.next
  

def removeNodes_v2(head: Optional[ListNode]) -> Optional[ListNode]:
        # Step 1: Reverse the list
        def reverse(node):
            prev = None
            while node:
                nxt = node.next
                node.next = prev
                prev = node
                node = nxt
            return prev
  
        head = reverse(head)
  
        # Step 2: Traverse reversed list, removing smaller nodes
        max_val = head.val
        curr = head
        while curr and curr.next:
            if curr.next.val < max_val:
                # remove node
                curr.next = curr.next.next
            else:
                curr = curr.next
                max_val = curr.val
  
        # Step 3: Reverse again to restore original order
        return reverse(head)

def nextLargerNodes(head: Optional[ListNode]) -> List[int]:
        # Convert linked list to array
        arr = []
        curr = head
        while curr:
            arr.append(curr.val)
            curr = curr.next

  
        res = [0] * len(arr)
        st = []  # stack holds indices
        print(arr)
        for i, val in enumerate(arr):
            # pop smaller values and set their next greater
            while st and arr[st[-1]] < val:
                idx = st.pop()
                res[idx] = val
                print(idx, val)
            st.append(i)
  
        print(res)
        return res

```

### 5. HashMap/HashSet for Tracking

**Use Cases:** Tracking visited nodes, cloning with random pointers

**💡 Key Insight:** Map original nodes to copied/processed nodes. Useful for cycle detection and deep copy.

**Common Problems:**

- Copy List with Random Pointer (138)
- Intersection of Two Lists (160)
- **Remove Zero Sum Sublists (1171)**

```python

def copyRandomList_dfs( node, hashmap):
        if(node == None):
            return None
        if(node in hashmap):
            return hashmap.get(node)

        temp = Node(node.val)
        hashmap[node] = temp
        temp.next = copyRandomList_dfs(node.next, hashmap)
        temp.random = hashmap.get(node.random)
        return temp

def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        hashmap = {}
        curr = head
   
        while(curr):
            hashmap[curr] = Node(curr.val)
            curr = curr.next

        curr = head  
        while(curr):
            newNode  = hashmap.get(curr)
            newNode.next = hashmap.get(curr.next)
            newNode.random = hashmap.get(curr.random)
            curr = curr.next
        return hashmap.get(head)



```

### 6. Priority Queue/Heap

**Use Cases:** Merging multiple sorted lists

**💡 Key Insight:** Keep k nodes in min-heap, always extract minimum and add its next node.

**Common Problems:**

- **Merge k Sorted Lists (23)**

**Complexity:**

- **Time:** O(N log k) where N is total nodes
- **Space:** O(k) for heap

```python
import heapq

def mergeKLists_2(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        heap = []
        head = ListNode()
        curr = head
# O(k)
        for i, node in enumerate(lists):
            if node:
                heapq.heappush(heap, (node.val, i , node))
# O(n*logk)
        while(heap):
            temp = heapq.heappop(heap)
            curr.next = temp[2]
            if(temp[2].next):
                heapq.heappush(heap, (temp[2].next.val, temp[1], temp[2].next))
            curr = curr.next
        return head.next

```

### 7. Merge/Split Patterns

**Use Cases:** Combining or dividing lists based on criteria

**Common Problems:**

- Odd Even Linked List (328)
- Partition List (86)
- Split List in Parts (725)

#### Design HashMap (LC 706)

**Use Cases:** Understanding hash collisions, chaining with LinkedList

**💡 Core Design:**

- Array of LinkedLists (buckets)
- Hash function: key % SIZE determines bucket
- Chaining: Store collisions in LinkedList per bucket
- Use dummy node in each bucket for simplicity

**Key Operations:**

```python
class ListNode:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None


class MyHashMap:

    def __init__(self):
        self.size = 1000
        self.buckets = [None] * self.size

    def _hash(self, key):
        return key % self.size

    def put(self, key: int, value: int) -> None:
        idx = self._hash(key)
        if self.buckets[idx] is None:
            self.buckets[idx] = ListNode(key, value)
            return

        curr = self.buckets[idx]
        while True:
            if curr.key == key:
                curr.val = value  # update existing
                return
            if curr.next is None:
                break
            curr = curr.next
        curr.next = ListNode(key, value)

    def get(self, key: int) -> int:
        idx = self._hash(key)
        curr = self.buckets[idx]
        while curr:
            if curr.key == key:
                return curr.val
            curr = curr.next
        return -1

    def remove(self, key: int) -> None:
        idx = self._hash(key)
        curr = self.buckets[idx]
        prev = None

        while curr:
            if curr.key == key:
                if prev:
                    prev.next = curr.next
                else:
                    self.buckets[idx] = curr.next
                return
            prev, curr = curr, curr.next

```

**Complexity:**

- **Time:** O(1) average, O(n) worst case
- **Space:** O(n + m) where m is SIZE

### 7. Binary to Integer (LC 1290)

```python
 def calculate(self, node):
        if(node.next == None):
            return node.val*(2**self.n)
        temp = self.calculate(node.next)
        self.n += 1
        return node.val*(2**self.n) + temp
```

## 9. Rotate List (LC 61)

- **Problem:** Rotate list to the right by k places.
- **Time:** O(n) | **Space:** O(1)

```python
    def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        if not head or not head.next or k == 0:
            return head
        slow = head 
        fast = head
        curr = head
        l = 0
        while(curr):
            l += 1
            curr = curr.next

        k = k%l
        if k == 0:
            return head
        for _ in range(k): fast = fast.next

        while(fast and fast.next):
            slow = slow.next 
            fast = fast.next
  
        prev = head
        curr = slow.next
        slow.next = None
        fast.next = head

        return curr 
```

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

### 28. Split Circular Linked List into Two Halves

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

### 19. Sorted List to BST (LC 109)

- **Difficulty:** Medium
- **Tags:** Two Pointers, Recursion
- **Problem:** Convert sorted list to height-balanced BST.
- **Time:** O(n log n) | **Space:** O(log n)

### 29. Bubble Sort for Linked List

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
