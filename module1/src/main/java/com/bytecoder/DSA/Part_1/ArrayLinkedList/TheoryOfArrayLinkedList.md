**Problem** find nth node from end of a linkedlist

hint: 
* brute-force : count length and count (l-n+1) to be deleted
* two pointers 
* hashtable (index and node)


### which sorting algorithm is easily adaptable to linkedlist and apply all sorting algorithms

Hint:
* insertion sort is easily adaptable
* other sorting (bubble, selection, insertion, merge and quick sort etc)


### check if the linked list is palindrome or not

hint:
* reverse the second half of the linkedlist and compare the first and second half
* use recursion



### you are given two or more sorted linkedlist. merge them into new linked list

hint:
* brute-force (create new ll and insert min value from all and take next step)
* priority-queue solution -> insert pair (element, which list and node ) , get min and take next step


---------------------------------
## Extra Problems


### 1. **Write a Program to Reverse the Linked List (Iterative and Recursive)**
   - **Iterative Hint**: Use three pointers: `prev`, `curr`, and `next`. Move through the list, updating pointers to reverse the direction of each node.
   - **Recursive Hint**: Recursively reverse the rest of the list and then link the current node to the last node of the reversed list.

### 2. **Reverse a Linked List in Groups of Given Size**
   - **Hint**: Reverse the first `k` nodes, and then recursively call the function for the remaining list. Link the end of the reversed list to the next reversed group.

Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
    
k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
    
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
   - hashtable

### 4. **Write a Program to Delete Loop in a Linked List**
   - **Hint**: First detect the loop using Floyd's algorithm. Once detected, find the start of the loop and disconnect it.

### 5. **Find the Starting Point of the Loop**
   - **Hint**: Once a loop is detected, reset one pointer to the head of the list and move both pointers one step at a time until they meet. This is the start of the loop.

### 6. **Remove Duplicates in a Sorted Linked List**
   - **Hint**: Traverse the list and compare the current node with the next node. If they are the same, remove the next node.

### 7. **Remove Duplicates in an Unsorted Linked List**
   - **Hint**: Use a hash set to store seen values. As you traverse the list, if a node’s value is in the set, remove it; otherwise, add it to the set.

### 8. **Move the Last Element to the Front of the List**
    Input: 2->5->6->2->1
    Output: 1->2->5->6->2
    Explanation : Node 1 moved to front.

   - **Hint**: Traverse to the second-to-last node, unlink the last node, and make it the new head.

### 9. **Add “1” to a Number Represented as a Linked List**
   - **Hint**: Reverse the list, add 1 to the head node, handle carry, then reverse it back.
   - use recursion and carry forward 

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

### 14. **Quicksort for Linked Lists**
   - **Hint**: Choose a pivot, partition the list around the pivot, and recursively sort the partitions.

### 15. **Find the Middle Element of a Linked List**
   - brute-force: find length and then get l/2 element 
   - **Hint**: Use the slow-fast pointer method where the slow pointer moves one step and the fast pointer moves two steps.

### 16. **Check if a Linked List is Circular**
   - **Hint**: Traverse the list and check if any node points back to the head or if a loop is detected.

### 17. **Split a Circular Linked List into Two Halves** or Split Linked List in Parts
   - **Hint**: Use the slow-fast pointer approach to find the middle. Then, break the list into two halves.

### 18. **Check if the Singly Linked List is a Palindrome**
   - use recursion to check palindrome 
   - **Hint**: Find the middle of the list, reverse the second half, and compare both halves.

### 19. **Deletion from a Circular Linked List**
Deletion involves removing a node from the linked list. The main difference is that we need to ensure the list remains circular after the deletion.
We can delete a node in a circular linked list in three ways:
- Deletion from the beginning of the circular linked list
- Deletion at specific position in circular linked list
- Deletion at the end of Circular linked list


   - **Hint**: Handle the edge case when the node to delete is the head and the list has only one node.

### 20. **Reverse a Doubly Linked List**
   - **Hint**: Swap the next and prev pointers of each node while traversing the list.

### 21. **Find Pairs with a Given Sum in a DLL(doubly linked list)**
   - **Hint**: Use two pointers (one from the start and one from the end) to find pairs whose sum matches the target.
   - using hashmap 

### 22. **Count Triplets in a Sorted DLL Whose Sum is Equal to a Given Value**
   - **Hint**: Fix one node and use the two-pointer technique to find pairs with the remaining sum.

### 23. **Sort a “k”-Sorted Doubly Linked List**
   - **Hint**: Use a min-heap of size `k` to efficiently sort the list.

### 24. **Rotate a Doubly Linked List by N Nodes**
   - **Hint**: Make the list circular, move the head `n` nodes forward, then break the circular list.

### 25. **Rotate a Doubly Linked List in Groups of Given Size**
   - **Hint**: Reverse the first `k` nodes, then recursively reverse the next group.

### 26. **Can We Reverse a Linked List in Less than O(n)?**
   - **Hint**: No, reversing a linked list requires touching every node at least once.

### 27. **Why is Quicksort Preferred for Arrays and Merge Sort for Linked Lists?**
   - **Hint**: Quicksort works better for arrays because of cache locality, while merge sort’s splitting and merging process fits well with linked lists.

### 28. **Flatten a Linked List**
   - **Hint**: Use a recursive approach to flatten each sublist and merge them into the main list.

### 29. **Sort a Linked List of 0s, 1s, and 2s**
   - **Hint**: Use a counting sort approach or rearrange nodes in a single pass using three pointers.

### 30. **Clone a Linked List with Next and Random Pointer**
   - **Hint**: Use a hash map to store the original nodes and their clones. Create the list by linking cloned nodes accordingly.

### 31. **Merge K Sorted Linked Lists**
   - **Hint**: Use a min-heap to efficiently merge the `k` lists.

### 32. **Multiply Two Numbers Represented by Linked Lists** or **445. Add Two Numbers II**
   - **Hint**: Reverse the lists, multiply the digits like you would on paper, and handle carry-over.

### 33. **Delete Nodes with Greater Value on Right Side**
   - **Hint**: Reverse the list, track the max value as you traverse, and delete nodes that are smaller than the max. Reverse the list back.

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

### 35. **Find the N’th Node from the End of a Linked List**
   - **Hint**: Use two pointers. Move one pointer `n` nodes ahead, then move both until the first pointer reaches the end.

### 36. **Find the First Non-Repeating Character from a Stream of Characters**
   - **Hint**: Use a linked list to maintain the order of characters and a hash map to keep track of the count of each character.


* https://leetcode.com/problems/merge-two-sorted-lists/submissions/1415980375/?envType=problem-list-v2&envId=recursion
* https://leetcode.com/problems/remove-linked-list-elements/submissions/1416013441/?envType=problem-list-v2&envId=recursion&difficulty=EASY
* https://leetcode.com/problems/remove-linked-list-elements/description/?envType=problem-list-v2&envId=linked-list&difficulty=EASY
* https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/ 