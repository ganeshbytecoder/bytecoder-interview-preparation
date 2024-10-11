**Problem** find nth node from end of a linkedlist

hint: 
* brute-force : count length and count (l-n+1) to be deleted
* two pointers 
* hashtable (index and node)

**Problem** detect the loop in the LinkedList

hint :
* brute-force
* hashtable
* two-pointers


**Problem** reverse the linked list using iterative and recursive methods

**Problem** which sorting algorithm is easily adaptable to linkedlist and apply all sorting algorithms

Hint:
* insertion sort is easily adaptable
* other sorting (bubble, selection, insertion, merge and quick sort etc)

**Problem** given a sorted linked list , write a program to remove duplicate elements

hint:
* iterative solution

**problem** find modular node or fractional node from linked list

**Problem** reverse block of k nodes in the linked list

hint:
* traverse till k+1 nodes and reverse first half and merge them

**Problem** check if the linked list is palindrome or not

hint:
* reverse the second half of the linkedlist and compare the first and second half


**Problem** you are given two or more sorted linkedlist. merge them into new linked list

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

### 3. **Write a Program to Detect a Loop in a Linked List**
   - **Hint**: Use Floyd’s Cycle Detection Algorithm (Tortoise and Hare).
   - Use two pointers that move at different speeds and check if they meet.

### 4. **Write a Program to Delete Loop in a Linked List**
   - **Hint**: First detect the loop using Floyd's algorithm. Once detected, find the start of the loop and disconnect it.

### 5. **Find the Starting Point of the Loop**
   - **Hint**: Once a loop is detected, reset one pointer to the head of the list and move both pointers one step at a time until they meet. This is the start of the loop.

### 6. **Remove Duplicates in a Sorted Linked List**
   - **Hint**: Traverse the list and compare the current node with the next node. If they are the same, remove the next node.

### 7. **Remove Duplicates in an Unsorted Linked List**
   - **Hint**: Use a hash set to store seen values. As you traverse the list, if a node’s value is in the set, remove it; otherwise, add it to the set.

### 8. **Move the Last Element to the Front of the List**
   - **Hint**: Traverse to the second-to-last node, unlink the last node, and make it the new head.

### 9. **Add “1” to a Number Represented as a Linked List**
   - **Hint**: Reverse the list, add 1 to the head node, handle carry, then reverse it back.

### 10. **Add Two Numbers Represented by Linked Lists**
   - **Hint**: Use a carry to add corresponding nodes, similar to how you would do it by hand with pen and paper.

### 11. **Intersection of Two Sorted Linked Lists**
   - **Hint**: Traverse both lists simultaneously, and add matching elements to the result list. Move forward only when elements match.

### 12. **Intersection Point of Two Linked Lists**
   - **Hint**: Calculate lengths of both lists. Move the pointer of the longer list forward by the difference in lengths, then compare nodes.

### 13. **Merge Sort for Linked Lists**
   - **Hint**: Use the slow-fast pointer technique to split the list, then recursively merge the two halves using the merge step of merge sort.

### 14. **Quicksort for Linked Lists**
   - **Hint**: Choose a pivot, partition the list around the pivot, and recursively sort the partitions.

### 15. **Find the Middle Element of a Linked List**
   - **Hint**: Use the slow-fast pointer method where the slow pointer moves one step and the fast pointer moves two steps.

### 16. **Check if a Linked List is Circular**
   - **Hint**: Traverse the list and check if any node points back to the head or if a loop is detected.

### 17. **Split a Circular Linked List into Two Halves**
   - **Hint**: Use the slow-fast pointer approach to find the middle. Then, break the list into two halves.

### 18. **Check if the Singly Linked List is a Palindrome**
   - **Hint**: Find the middle of the list, reverse the second half, and compare both halves.

### 19. **Deletion from a Circular Linked List**
   - **Hint**: Handle the edge case when the node to delete is the head and the list has only one node.

### 20. **Reverse a Doubly Linked List**
   - **Hint**: Swap the next and prev pointers of each node while traversing the list.

### 21. **Find Pairs with a Given Sum in a DLL**
   - **Hint**: Use two pointers (one from the start and one from the end) to find pairs whose sum matches the target.

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

### 32. **Multiply Two Numbers Represented by Linked Lists**
   - **Hint**: Reverse the lists, multiply the digits like you would on paper, and handle carry-over.

### 33. **Delete Nodes with Greater Value on Right Side**
   - **Hint**: Reverse the list, track the max value as you traverse, and delete nodes that are smaller than the max. Reverse the list back.

### 34. **Segregate Even and Odd Nodes in a Linked List**
   - **Hint**: Create two separate lists for even and odd nodes, then concatenate them.

### 35. **Find the N’th Node from the End of a Linked List**
   - **Hint**: Use two pointers. Move one pointer `n` nodes ahead, then move both until the first pointer reaches the end.

### 36. **Find the First Non-Repeating Character from a Stream of Characters**
   - **Hint**: Use a linked list to maintain the order of characters and a hash map to keep track of the count of each character.


* https://leetcode.com/problems/merge-two-sorted-lists/submissions/1415980375/?envType=problem-list-v2&envId=recursion
* https://leetcode.com/problems/remove-linked-list-elements/submissions/1416013441/?envType=problem-list-v2&envId=recursion&difficulty=EASY