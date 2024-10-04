### Java Collections Framework Overview:

#### Key Interfaces in the Collection Framework:

1. **`Collection` Interface**:
    - The root interface of the collection hierarchy.
    - Contains methods to add, remove, and query objects in the collection.
    - Subinterfaces such as `List`, `Set`, and `Queue` extend `Collection` and specialize it for specific collection
      types.

   ```java
   Collection<String> collection = new ArrayList<>();
   collection.add("Element");
   ```
   
### Key Methods in the `Collection` Interface:

- **`add(E element)`**: Adds the specified element to the collection.
- **`remove(Object element)`**: Removes the specified element from the collection.
- **`clear()`**: Removes all elements from the collection.
- **`contains(Object element)`**: Returns `true` if the collection contains the specified element.
- **`size()`**: Returns the number of elements in the collection.
- **`isEmpty()`**: Returns `true` if the collection contains no elements.
- **`iterator()`**: Returns an iterator over the elements in the collection.
- **`toArray()`**:
- **`containsAll(Collection c)`**:
- **`removeAll(Collection c)`**:
- **`retainAll(Collection c)`**:

2. **`List` Interface**:
    - Represents an ordered collection (also known as a sequence). It allows positional access and insertion of
      elements.
    - The `List` interface in Java extends the `Collection` interface and includes several additional methods that are
      not present in the `Collection` interface. These methods allow more specific operations related to handling
      elements in an ordered sequence, as lists are indexed collections that allow positional access and manipulation of
      elements.

    - **Implementations**:
        - `ArrayList`: A resizable array implementation.
        - `LinkedList`: A doubly linked list implementation.

 

### Positional Access Methods:

1. **`void add(int index, E element)`**  
   Inserts the specified element at the specified position in the list.

   ```java
   list.add(1, "Element");
   ```

2. **`E get(int index)`**  
   Returns the element at the specified position in the list.

   ```java
   E element = list.get(0);
   ```

3. **`E set(int index, E element)`**  
   Replaces the element at the specified position in the list with the specified element and returns the replaced
   element.

   ```java
   E oldElement = list.set(2, "New Element");
   ```

4. **`E remove(int index)`**  
   Removes the element at the specified position in the list and returns the removed element.

   ```java
   E removedElement = list.remove(1);
   ```

### Search Methods:

5. **`int indexOf(Object o)`**  
   Returns the index of the first occurrence of the specified element in the list, or `-1` if the list does not contain
   the element.

   ```java
   int index = list.indexOf("Element");
   ```

6. **`int lastIndexOf(Object o)`**  
   Returns the index of the last occurrence of the specified element in the list, or `-1` if the list does not contain
   the element.

   ```java
   int lastIndex = list.lastIndexOf("Element");
   ```

### Range View Methods:

7. **`List<E> subList(int fromIndex, int toIndex)`**  
   Returns a view of the portion of this list between the specified `fromIndex`, inclusive, and `toIndex`, exclusive.

   ```java
   List<E> subList = list.subList(1, 3);
   ```

### List Iteration Methods:

8. **`ListIterator<E> listIterator()`**  
   Returns a `ListIterator` over the elements in the list (in proper sequence), allowing bidirectional iteration and
   element modification.

   ```java
   ListIterator<E> listIterator = list.listIterator();
   ```

9. **`ListIterator<E> listIterator(int index)`**  
   Returns a `ListIterator` of the elements in the list, starting at the specified position in the list.

   ```java
   ListIterator<E> listIterator = list.listIterator(2);
   ```

### Bulk Operations:

10. **`boolean addAll(int index, Collection<? extends E> c)`**  
    Inserts all the elements in the specified collection into the list at the specified position.

    ```java
    list.addAll(2, anotherCollection);
    ```


3. **`Set` Interface**:
    - Represents a collection that cannot contain duplicate elements.
    - **Implementations**:
        - `HashSet`: Uses a hash table to store elements; no ordering of elements.
        - `LinkedHashSet`: Maintains insertion order.
        - `TreeSet`: Maintains sorted order based on natural ordering or a comparator.

   ```java
   Set<String> set = new HashSet<>();
   set.add("Element");
   ```

Unlike `List` and `Queue`, the `Set` interface does not introduce new methods beyond those inherited from `Collection`.
Its primary focus is enforcing the uniqueness of elements, making it suitable for use cases where duplicates are not
allowed, such as in a collection of unique IDs, or in algorithms that need to track unique values without regard to
order.

4. **`Queue` Interface**:
    - Represents a collection designed for holding elements prior to processing. Typically, queues are ordered in a
      FIFO (first-in, first-out) manner.
    - **Implementations**:
        - `LinkedList`: Can be used as both a `List` and a `Queue`.
        - `PriorityQueue`: Orders elements according to their natural ordering or by a comparator.
        - `Deque`: A double-ended queue, supporting insertion and removal at both ends (`ArrayDeque`, `LinkedList`).

   ```java
   Queue<String> queue = new LinkedList<>();
   queue.add("Element");
   ```

### Summary of Queue-Specific Methods:

- **`offer(E e)`**: Inserts the specified element without throwing an exception if the queue is full (returns `false` instead).
- **`poll()`**: Retrieves and removes the head of the queue, or returns `null` if the queue is empty.
- **`peek()`**: Retrieves, but does not remove, the head of the queue, or returns `null` if the queue is empty.
- **`remove()`**: Retrieves and removes the head of the queue, throwing an exception if the queue is empty.
- **`element()`**: Retrieves, but does not remove, the head of the queue, throwing an exception if the queue is empty.

5. **`Deque` Interface**

The `Deque` (short for "Double-Ended Queue") interface in Java extends the `Queue` interface, which in turn extends the `Collection` interface. The `Deque` interface allows for elements to be added or removed from both ends of the queue, making it more flexible than a regular `Queue`, which is typically restricted to FIFO operations. As a result, the `Deque` interface includes several additional methods that are not part of the `Collection` or `Queue` interfaces.

Here are the extra methods in the `Deque` interface that are not part of the `Collection` interface:

### Deque Interface Methods:
### `Deque` Implementations:
1. `LinkedList`
2. `ArrayDeque`

#### Insert Methods:
1. **`void addFirst(E e)`**  
   Inserts the specified element at the front of the deque.

   ```java
   deque.addFirst("Element");
   ```

2. **`void addLast(E e)`**  
   Inserts the specified element at the end of the deque (equivalent to `add()` in `Queue`).


3. **`boolean offerFirst(E e)`**  
   Attempts to insert the specified element at the front of the deque. Returns `false` if it fails due to capacity restrictions.


4. **`boolean offerLast(E e)`**  
   Attempts to insert the specified element at the end of the deque. Returns `false` if it fails due to capacity restrictions.


#### Remove Methods:
5. **`E removeFirst()`**  
   Retrieves and removes the first element of the deque. Throws a `NoSuchElementException` if the deque is empty.


6. **`E removeLast()`**  
   Retrieves and removes the last element of the deque. Throws a `NoSuchElementException` if the deque is empty.


7. **`E pollFirst()`**  
   Retrieves and removes the first element of the deque, or returns `null` if the deque is empty.

8. **`E pollLast()`**  
   Retrieves and removes the last element of the deque, or returns `null` if the deque is empty.


#### Retrieve Methods (without removal):
9. **`E getFirst()`**  
   Retrieves, but does not remove, the first element of the deque. Throws a `NoSuchElementException` if the deque is empty.


10. **`E getLast()`**  
   Retrieves, but does not remove, the last element of the deque. Throws a `NoSuchElementException` if the deque is empty.


11. **`E peekFirst()`**  
   Retrieves, but does not remove, the first element of the deque, or returns `null` if the deque is empty.


12. **`E peekLast()`**  
   Retrieves, but does not remove, the last element of the deque, or returns `null` if the deque is empty.


#### Stack-like Methods:
The `Deque` interface also provides methods that allow the deque to be used as a LIFO (Last-In-First-Out) stack, in addition to its queue-like behavior:

13. **`void push(E e)`**  
   Pushes an element onto the stack, equivalent to `addFirst()`.


14. **`E pop()`**  
   Pops an element off the stack, equivalent to `removeFirst()`. Throws a `NoSuchElementException` if the deque is empty.


#### Check Methods:
15. **`boolean removeFirstOccurrence(Object o)`**  
   Removes the first occurrence of the specified element from the deque.


16. **`boolean removeLastOccurrence(Object o)`**  
   Removes the last occurrence of the specified element from the deque.

### Summary of Deque-Specific Methods:

- **Insertion at both ends**: `addFirst()`, `addLast()`, `offerFirst()`, `offerLast()`
- **Removal at both ends**: `removeFirst()`, `removeLast()`, `pollFirst()`, `pollLast()`
- **Peeking at both ends**: `getFirst()`, `getLast()`, `peekFirst()`, `peekLast()`
- **Stack operations**: `push()` (equivalent to `addFirst()`), `pop()` (equivalent to `removeFirst()`)
- **Occurrence removal**: `removeFirstOccurrence()`, `removeLastOccurrence()`

### Use Cases of Deque:

The `Deque` interface is versatile and can be used both as a **FIFO queue** and a **LIFO stack**. It allows for more complex operations like adding, removing, and inspecting elements at both the head and the tail of the collection. Common implementations of `Deque` include `ArrayDeque` and `LinkedList`, both of which provide efficient implementations of these operations.

This makes `Deque` suitable for use cases where elements need to be processed in a double-ended manner, such as in **caching mechanisms**, **task scheduling**, **expression evaluation**, and **history tracking** (e.g., browser history, undo operations).


### Java Collection Framework Hierarchy:

```
                     Collection
                          |
       ---------------------------------------------------
       |                  |                  |            |
      List               Set               Queue         Deque
       |                  |                  |             |
   ArrayList           HashSet            LinkedList      ArrayDeque
   LinkedList         LinkedHashSet       PriorityQueue   LinkedList
                        TreeSet
   
```

### Important Classes in the Collection Framework:


### Key Methods in the `List` Interface:

1. **`ArrayList`**:
    - Dynamic array implementation of the `List` interface.
    - Provides fast random access but slow insertion/removal in the middle due to shifting elements.

   ```java
   ArrayList<String> list = new ArrayList<>();
   list.add("Apple");
   ```

2. **`LinkedList`**:
    - Doubly linked list implementation of `List` and `Deque`.
    - Provides fast insertion/removal at the ends, slower random access compared to `ArrayList`.

   ```java
   LinkedList<String> linkedList = new LinkedList<>();
   linkedList.add("Banana");
   ```

### Key Methods in the `Set` Interface:

4.**`HashSet`**:

- Hash table-based implementation of `Set`.
- Fast access to elements but does not guarantee any specific order of elements.

   ```java
   HashSet<String> hashSet = new HashSet<>();
   hashSet.add("Mango");
   ```

4. **`TreeSet`**:
    - Navigable set implementation based on a Red-Black Tree.
    - Elements are stored in sorted order (natural or custom comparator).

   ```java
   TreeSet<Integer> treeSet = new TreeSet<>();
   treeSet.add(5);
   ```

### Key Methods in the `Queue` Interface:

5. **`PriorityQueue`**:
    - Implements a priority queue, where elements are ordered based on their priority, defined by their natural order or
      a comparator.

   ```java
   PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
   priorityQueue.add(10);
   ```

### Collections Utility Class:

The `Collections` class in Java provides various static utility methods for operating on collections (like `List`, `Set`, and `Map`). These methods are useful for tasks such as sorting, searching, reversing, and synchronizing collections. Below are some of the most commonly used utility methods provided by the `Collections` class.

### Common Utility Methods in the `Collections` Class:

1. **`sort(List<T> list)`**
   - Sorts the specified list in ascending order based on the natural ordering of its elements.
   - For custom sorting, you can use `sort(List<T> list, Comparator<? super T> c)` to specify a comparator.

   ```java
   List<String> list = Arrays.asList("banana", "apple", "pear");
   Collections.sort(list);
   System.out.println(list);  // Output: [apple, banana, pear]
   ```

2. **`reverse(List<?> list)`**
   - Reverses the order of the elements in the specified list.

   ```java
   List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
   Collections.reverse(list);
   System.out.println(list);  // Output: [5, 4, 3, 2, 1]
   ```

3. **`shuffle(List<?> list)`**
   - Randomly shuffles the elements in the specified list.

   ```java
   List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
   Collections.shuffle(list);
   System.out.println(list);  // Output: A randomly shuffled list
   ```

4. **`min(Collection<? extends T> coll)`**
   - Returns the minimum element of the specified collection, according to the natural ordering of its elements.
   - You can use `min(Collection<? extends T> coll, Comparator<? super T> comp)` for custom comparison.

   ```java
   List<Integer> list = Arrays.asList(3, 1, 4, 1, 5);
   int min = Collections.min(list);
   System.out.println(min);  // Output: 1
   ```

5. **`max(Collection<? extends T> coll)`**
   - Returns the maximum element of the specified collection, according to the natural ordering of its elements.
   - You can use `max(Collection<? extends T> coll, Comparator<? super T> comp)` for custom comparison.

   ```java
   List<Integer> list = Arrays.asList(3, 1, 4, 1, 5);
   int max = Collections.max(list);
   System.out.println(max);  // Output: 5
   ```

6. **`binarySearch(List<? extends T> list, T key)`**
   - Performs a binary search for the specified element in the specified sorted list and returns the index of the element.
   - The list must be sorted prior to making this call.

   ```java
   List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
   int index = Collections.binarySearch(list, 3);
   System.out.println(index);  // Output: 2
   ```

7. **`copy(List<? super T> dest, List<? extends T> src)`**
   - Copies all elements from the source list into the destination list.
   - The destination list must be at least as long as the source list.

   ```java
   List<Integer> src = Arrays.asList(1, 2, 3);
   List<Integer> dest = Arrays.asList(0, 0, 0);
   Collections.copy(dest, src);
   System.out.println(dest);  // Output: [1, 2, 3]
   ```

8. **`fill(List<? super T> list, T obj)`**
   - Replaces all elements of the specified list with the specified element.

   ```java
   List<Integer> list = Arrays.asList(1, 2, 3);
   Collections.fill(list, 0);
   System.out.println(list);  // Output: [0, 0, 0]
   ```

9. **`replaceAll(List<T> list, T oldVal, T newVal)`**
   - Replaces all occurrences of one specified value in the list with another.

   ```java
   List<Integer> list = Arrays.asList(1, 2, 2, 3);
   Collections.replaceAll(list, 2, 99);
   System.out.println(list);  // Output: [1, 99, 99, 3]
   ```

10. **`swap(List<?> list, int i, int j)`**
    - Swaps the elements at the specified positions in the specified list.

    ```java
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    Collections.swap(list, 1, 3);
    System.out.println(list);  // Output: [1, 4, 3, 2]
    ```

11. **`rotate(List<?> list, int distance)`**
    - Rotates the elements in the specified list by the specified distance. The list is shifted by the distance, and elements shifted out on one end are reintroduced at the other end.

    ```java
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    Collections.rotate(list, 2);
    System.out.println(list);  // Output: [4, 5, 1, 2, 3]
    ```


14. **`disjoint(Collection<?> c1, Collection<?> c2)`**
    - Returns `true` if the two specified collections have no elements in common.

    ```java
    List<Integer> list1 = Arrays.asList(1, 2, 3);
    List<Integer> list2 = Arrays.asList(4, 5, 6);
    boolean disjoint = Collections.disjoint(list1, list2);
    System.out.println(disjoint);  // Output: true
    ```

15. **`frequency(Collection<?> c, Object o)`**
    - Returns the number of occurrences of the specified element in the specified collection.

    ```java
    List<Integer> list = Arrays.asList(1, 2, 2, 3);
    int freq = Collections.frequency(list, 2);
    System.out.println(freq);  // Output: 2
    ```




## **concurrent collections**
