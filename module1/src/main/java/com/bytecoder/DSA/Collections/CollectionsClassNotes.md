
### Java Collections Class

The **`Collections`** class in Java belongs to the `java.util` package and provides utility methods for working with collections such as lists, sets, and maps.

#### Key Methods in `Collections` Class

1. **Sorting**
    - `Collections.sort(List<T> list)`: Sorts the list in ascending order.
    - `Collections.sort(List<T> list, Comparator<? super T> comparator)`: Sorts the list using a custom comparator.

2. **Searching**
    - `Collections.binarySearch(List<? extends T> list, T key)`: Searches for the key in a sorted list using binary search.
    - `Collections.binarySearch(List<? extends T> list, T key, Comparator<? super T> comparator)`: Binary search with a comparator.

3. **Reversing and Rotating**
    - `Collections.reverse(List<?> list)`: Reverses the order of elements.
    - `Collections.rotate(List<?> list, int distance)`: Rotates the list by the specified distance.

4. **Shuffling**
    - `Collections.shuffle(List<?> list)`: Randomly shuffles the elements.
    - `Collections.shuffle(List<?> list, Random rnd)`: Shuffles with a specific random source.

5. **Filling**
    - `Collections.fill(List<? super T> list, T obj)`: Replaces all elements with the specified value.

6. **Frequency and Disjoint**
    - `Collections.frequency(Collection<?> c, Object o)`: Returns the number of occurrences of the specified element.
    - `Collections.disjoint(Collection<?> c1, Collection<?> c2)`: Checks if the two collections have no elements in common.

7. **Unmodifiable and Synchronized Collections**
    - `Collections.unmodifiableList(List<? extends T> list)`: Returns an unmodifiable view of the list.
    - `Collections.synchronizedList(List<T> list)`: Returns a thread-safe list.

8. **Min and Max**
    - `Collections.min(Collection<? extends T> coll)`: Returns the smallest element.
    - `Collections.max(Collection<? extends T> coll)`: Returns the largest element.

9. **Copy and Replace**
    - `Collections.copy(List<? super T> dest, List<? extends T> src)`: Copies elements from one list to another.
    - `Collections.replaceAll(List<T> list, T oldVal, T newVal)`: Replaces all occurrences of a value with another.

10. **Empty and Singleton Collections**
    - `Collections.emptyList()`: Returns an immutable empty list.
    - `Collections.singleton(T obj)`: Returns an immutable set with a single element.

---

### Notes

- **`Arrays`** is for array-specific utilities, while **`Collections`** is for generic data structures like `List`, `Set`, and `Map`.
- `Arrays` is more static-oriented, while `Collections` focuses on dynamic, mutable operations.
- Always ensure collections are properly initialized to avoid `NullPointerException`.
- Use synchronized methods from `Collections` for thread-safe operations but prefer modern `Concurrent` utilities for better performance.