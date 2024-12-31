Here are hints and strategies for solving various priority queue (heap) related problems in Data Structures and Algorithms (DSA):

### 1. **Implement a MaxHeap/MinHeap using arrays and recursion**
   - Heaps are typically implemented using arrays. For any node at index `i`, the left child is at `2*i + 1` and the right child is at `2*i + 2`.
   - **Insertion:** Add the element at the end and perform "heapify-up" to maintain heap properties.
   - **Deletion (max/min element):** Remove the root, replace it with the last element, and "heapify-down" to restore heap properties.
   - Recursive heapify methods can be used for both heapify-up and heapify-down operations.

### 2. **Sort an Array using Heap (HeapSort)**
   - First, build a max heap from the array.
   - Then, repeatedly extract the maximum element from the heap, swapping it with the last element in the array, and heapify the reduced heap.
   - Time complexity is `O(n log n)`.

### 3. **Maximum of all subarrays of size k** https://leetcode.com/problems/sliding-window-maximum/ 
   - Use a **sliding window with a max heap**. Insert elements into the heap, and slide the window to remove elements no longer in the window.
   - Alternatively, use a deque to maintain the max element at each window in `O(n)` time.

### 4. **“k” largest elements in an array**
   - Use a **min-heap** of size `k` to keep track of the largest `k` elements.
   - Insert elements into the heap, and if the heap size exceeds `k`, remove the smallest element.

### 5. **Kth smallest and largest element in an unsorted array**
   - Use a **min-heap** to find the `kth` largest element.
   - Alternatively, use a **max-heap** to find the `kth` smallest element.
   - Another approach: use the **quickselect** algorithm with average `O(n)` time complexity.

### 6. **Merge “K” sorted arrays**
#### **Approach-1**:
* Divide and Conquer:


* **use brute-force approach** :
  * create new list with all elements and sort it
  
* **Priority Queue**:
  * create a pair ([data, listIndex, dataIndex]) or node
  * add all list first element in priority queue
  * poll data for PQ and add next element from respective list
  


### merge k sorted LinkedList (https://leetcode.com/problems/merge-k-sorted-lists/description/)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

 class Node {
    int data;
    int li;
    ListNode di;
    public Node(int data,int li, ListNode di){
        this.data = data;
        this.li= li;
        this.di=di;
    }
 }
 class Solution {
      public ListNode mergeKLists_m1(ListNode[] lists) {
          PriorityQueue<Node> queue = new PriorityQueue<>((n1,n2)-> n1.data - n2.data);

          for(int i =0; i< lists.length; i++){
              if(lists[i] != null)
                  queue.add(new Node(lists[i].val, i , lists[i]));
          }
          List<Integer> ans = new ArrayList<>();

          while(!queue.isEmpty()){
              Node temp = queue.poll();
              ans.add(temp.data);
              if( temp.di != null && temp.di.next != null){
                  queue.add(new Node(temp.di.next.val, temp.li , temp.di.next));   
              }
            
          }

          ListNode head = null;
          ListNode curr = null;

          for(int i =0; i<ans.size(); i++){
              if(head==null){
                  head=new ListNode(ans.get(i));
                  curr=head;
                  continue;
              }
              curr.next= new ListNode(ans.get(i));
              curr=curr.next;
          }
          return head;


      }

      public ListNode mergeKLists(ListNode[] lists) {
          PriorityQueue<Node> queue = new PriorityQueue<>((n1,n2)-> n1.data - n2.data);

          for(int i =0; i< lists.length; i++){
              if(lists[i] != null)
                  queue.add(new Node(lists[i].val, i , lists[i]));
          }
        
          ListNode head = null;
          ListNode curr = null;

          while(!queue.isEmpty()){
              Node temp = queue.poll();
              if( temp.di != null && temp.di.next != null){
                  queue.add(new Node(temp.di.next.val, temp.li , temp.di.next));   
              }
              if(head==null){
                  head=new ListNode(temp.data);
                  curr=head;
                  continue;
              }
              curr.next= new ListNode(temp.data);
              curr=curr.next;
          }

    
          return head;


      }
 }

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return divideAndConquer(lists, 0, lists.length - 1);
    }

    private ListNode divideAndConquer(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];

        int mid = left + (right - left) / 2;
        ListNode l1 = divideAndConquer(lists, left, mid);
        ListNode l2 = divideAndConquer(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }
}
```

### 8. **Kth largest sum of continuous subarrays**
- Use a **min-heap** to store the sums of subarrays.
- Iterate over all possible subarrays, and if the heap size exceeds `k`, remove the smallest element.


### 7. **Merge 2 Binary Max Heaps**
   - Concatenate both heaps into a single array and perform a build-heap operation.
   - Alternatively, insert each element of the second heap into the first heap while maintaining heap properties.


### 9. **Leetcode: Reorganize Strings**
   - Use a **max-heap** to arrange characters by frequency.
   - Start by placing the most frequent character, then alternate with the next most frequent character to avoid placing the same characters next to each other.

### 10. **Merge “K” Sorted Linked Lists**
   - Use a **min-heap** to merge the sorted linked lists.
   - Insert the head of each list into the heap, extract the smallest node, and insert the next node from the same list into the heap.

### 11. **Smallest range in “K” lists**
   - Use a **min-heap** to track the minimum element in the current range across `k` lists.
   - Keep track of the current maximum and try to minimize the range by adjusting the window over the lists.

### 12. **Median in a stream of integers**
   - Use two heaps: a **max-heap** for the lower half of the numbers and a **min-heap** for the upper half.
   - Ensure the heaps are balanced in size, and the median is the root of either heap or the average of the roots.

### 13. **Check if a Binary Tree is Heap**
   - Perform a level-order traversal and check two conditions:
     1. **Complete Binary Tree**: The tree should be complete.
     2. **Heap Property**: For max-heap, every node should be greater than or equal to its children; for min-heap, every node should be smaller than or equal to its children.

### 14. **Connect “n” ropes with minimum cost**
   - Use a **min-heap** to repeatedly connect the two smallest ropes.
   - Add the cost of each connection and continue until all ropes are connected.

### 15. **Convert BST to Min Heap**
   - Perform an in-order traversal to collect elements in sorted order.
   - Then, perform a level-order traversal to assign the elements to form a min-heap.

### 16. **Convert Min Heap to Max Heap**
   - Simply traverse the min heap and negate all elements to convert it to a max heap (since max heap logic is essentially min heap for negative numbers).
   - Perform a heapify operation on the new array.

### 17. **Rearrange Characters in a String so that No Two Adjacent Characters Are Same**
   - Use a **max-heap** to arrange characters by frequency.
   - Pick the most frequent character and then the next most frequent character. Place them alternatively and use a temporary buffer for already used characters.

### 18. **Minimum Sum of Two Numbers Formed from Digits of an Array**
   - Sort the digits, then alternate the digits between the two numbers to minimize their sum.
   - Use a **greedy approach** to form the numbers.

