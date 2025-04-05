* https://leetcode.com/problems/remove-nodes-from-linked-list/description/

* https://leetcode.com/problems/add-two-numbers-ii/ 

* https://leetcode.com/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-interview-150

* https://leetcode.com/problems/reverse-linked-list-ii/description/?envType=study-plan-v2&envId=top-interview-150

* https://leetcode.com/problems/swap-nodes-in-pairs/submissions/1423121266/?envType=problem-list-v2&envId=recursion&difficulty=MEDIUM
* 
* https://leetcode.com/problems/lru-cache/?envType=company&envId=amazon&favoriteSlug=amazon-thirty-days
* 
* https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/

M1
```python

class Solution:
    def dfs(self,node):
        if(node == None):
            return node
        if(node.next == None and node.child == None):
            return node
        if(node.next == None and node.child != None):

            temp = self.dfs(node.child)
            node.child = None
            node.next = temp
            temp.prev = node
            return node
        
        if(node.child != None):
            nextt = node.next
            temp = self.dfs(node.child)
            node.next = temp
            temp.prev = node

            node.child = None
            while(temp.next != None):
                temp = temp.next
            temp.next = nextt
            nextt.prev = temp
            
        else:
            node.next = self.dfs(node.next)

        return node

    def flatten(self, head: 'Optional[Node]') -> 'Optional[Node]':
        return self.dfs(head)
        

```

M2 
```java

class Solution {
    public Node flatten(Node head) {
        Node curt = head;
        Stack<Node> stack = new Stack<>(); // store curt.next when curt.child is not null
        
        while(curt != null) {
            if(curt.child != null) {
                if(curt.next != null){
                    stack.push(curt.next); // might be null
                }
                curt.next = curt.child;
                curt.next.prev = curt;
                curt.child = null;
            } else if(curt.next == null && !stack.isEmpty()) { // reach of tail of child, reconnet the next of parent
                curt.next = stack.pop();
                curt.next.prev = curt;
            }
            
            curt = curt.next;
        }
        
        return head;
    }
}
```

### 34. **Segregate Even and Odd Nodes in a Linked List **
Note that the relative order inside both the even and odd groups should remain as it was in the input.

Input: head = [1,2,3,4,5] \
Output: [1,3,5,2,4]

Input: head = [2,1,3,5,6,4,7] \
Output: [2,3,6,7,1,5,4]

- **Hint**: Create two separate lists for even and odd nodes, then concatenate them.
```java
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while(even != null && even.next != null){
            odd.next= even.next;
            odd = odd.next;

            even.next = odd.next;
            even= even.next;
         
        }
        odd.next = evenHead;
        return head;
        
    }
```

### which sorting algorithm is easily adaptable to linkedlist and apply all sorting algorithms
Hint:
* insertion sort is easily adaptable
* other sorting (bubble, selection, insertion, merge and quick sort etc)


### Reorder List
- You are given the head of a singly linked-list. The list can be represented as:

  L0 → L1 → … → Ln - 1 → Ln
  Reorder the list to be on the following form:

  L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
- https://leetcode.com/problems/reorder-list/description/


### 7. **Remove Duplicates in an Unsorted Linked List**
- **Hint**: Use a hash set to store seen values. As you traverse the list, if a node’s value is in the set, remove it; otherwise, add it to the set.

```java
    public Node removeDuplicates(Node head) {
        // Your code here
        
        Map<Integer, Integer> map = new HashMap<>();
        
        Node curr = head;
        Node prev = null;
        
        while(curr != null){
            if(  map.get(curr.data) != null){
                prev.next = curr.next;
                curr = curr.next;
                continue;
            }else{
                map.put(curr.data, curr.data);
            }
            prev = curr;
            curr = curr.next;
            
        }
        return head;
    }
```



###  Remove Nth Node From End of List**
   - brute force (using length )
   - two pointer

###  **Merge K Sorted Linked Lists**
   - **Hint**: Use a min-heap to efficiently merge the `k` lists.

###  **Delete Nodes with Greater Value on Right Side**
   - **Hint**: Reverse the list, track the max value as you traverse, and delete nodes that are smaller than the max. Reverse the list back.

###  **Find the N’th Node from the End of a Linked List**
* Use two pointers. Move one pointer `n` nodes ahead, then move both until the first pointer reaches the end.
* brute-force : count length and count (l-n+1) to be deleted 
* hashtable (index and node)
* reverse and find nth from start



### check if the linked list is palindrome or not

hint:
* reverse the second half of the linkedlist and compare the first and second half
* use recursion
* use stack/array and compare 
* Find the middle of the list, reverse the second half, and compare both halves.



###  **Write a Program to Reverse the Linked List (Iterative and Recursive)**
   - **Iterative Hint**: Use three pointers: `prev`, `curr`, and `next`. Move through the list, updating pointers to reverse the direction of each node.
   - **Recursive Hint**: Recursively reverse the rest of the list and then link the current node to the last node of the reversed list.

### **Reverse a Linked List in Groups of Given Size**
   - **Hint**: Reverse the first `k` nodes, and then recursively call the function for the remaining list. Link the end of the reversed list to the next reversed group.
 
    ```text
    Input: head = [1,2,3,4,5], k = 2
    Output: [2,1,4,3,5]
    
    Input: head = [1,2,3,4,5], k = 3
    Output: [3,2,1,4,5]
    ```

### 3. **Write a Program to Detect a Loop in a Linked List**
   - **Hint**: Use Floyd’s Cycle Detection Algorithm (Tortoise and Hare).
   - Use two pointers that move at different speeds and check if they meet.
   - brute-force 
   - hashtable or hashset 


### 5. **Find the Starting Point of the Loop**
   - **Hint**: Once a loop is detected, reset one pointer to the head of the list and move both pointers one step at a time until they meet. This is the start of the loop.

### 6. **Remove Duplicates in a Sorted Linked List**
   - **Hint**: Traverse the list and compare the current node with the next node. If they are the same, remove the next node.


### 8. **Move the Last Element to the Front of the List**
    Input: 2->5->6->2->1
    Output: 1->2->5->6->2
    Explanation : Node 1 moved to front.

   - **Hint**: Traverse to the second-to-last node, unlink the last node, and make it the new head.



### 9. [**Add “1” to a Number Represented as a Linked List**](https://leetcode.com/problems/add-two-numbers/description/)
   - **Hint**: Reverse the list, add 1 to the head node, handle carry, then reverse it back.
   - use recursion and carry forward 
   - using stack 


### 10. **Add Two Numbers Represented by Linked Lists**
   - **Hint**: Use a carry to add corresponding nodes, similar to how you would do it by hand with pen and paper.
   - use recursion and carry forward  (if LL is right format (123 + 123 = 246))

### 11. **Intersection of Two Sorted Linked Lists**
   - **Hint**: Traverse both lists simultaneously, and add matching elements to the result list. Move forward only when elements match.

### 12. **Intersection Point of Two Linked Lists**
   - **Hint**: Calculate lengths of both lists. Move the pointer of the longer list forward by the difference in lengths, then compare nodes.
    - hashmap 

### 13. **Merge Sort for Linked Lists**
   - **Hint**: Use the slow-fast pointer technique to split the list, then recursively merge the two halves using the merge step of merge sort.



### 17. **Split a Circular Linked List into Two Halves** or Split Linked List in Parts
   - **Hint**: Use the slow-fast pointer approach to find the middle. Then, break the list into two halves.


### 20. **Reverse a Doubly Linked List**
   - **Hint**: Swap the next and prev pointers of each node while traversing the list.


### 22. **Count Triplets in a Sorted DLL Whose Sum is Equal to a Given Value**
   - **Hint**: Fix one node and use the two-pointer technique to find pairs with the remaining sum.

### 23. **Sort a “k”-Sorted Doubly Linked List**
   - **Hint**: Use a min-heap of size `k` to efficiently sort the list.

### 24. **Rotate a Doubly Linked List by N Nodes**
   - **Hint**: Make the list circular, move the head `n` nodes forward, then break the circular list.

### 25. **Rotate a Doubly Linked List in Groups of Given Size**
   - **Hint**: Reverse the first `k` nodes, then recursively reverse the next group.

