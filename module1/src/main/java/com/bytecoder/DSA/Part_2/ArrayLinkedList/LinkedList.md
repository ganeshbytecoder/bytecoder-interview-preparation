## LinkedList:

#### **Reverse LinkedList (Iterative)**

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

#### Find Middle (Two Pointers)

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
return slow;
```

#### Detect Cycle

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) return true;
}
return false;
```

#### Find Cycle Start

```java
// After detecting cycle
slow = head;
while (slow != fast) {
    slow = slow.next;
    fast = fast.next;
}
return slow;
```

#### Merge Two Sorted Lists

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

#### Remove Nth From End

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

### Remove Duplicates from Unsorted List

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

### Add One to Number as Linked List

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
