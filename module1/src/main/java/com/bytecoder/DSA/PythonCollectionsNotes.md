> **Note**: One relevant change that arrived in **Python 3.9** is the **dictionary merge (`|`) and update (`|=`) operators**, which you can use in Python 3.10 as well.
> by-default all the iterable functions will work on dictionary's key only it does not take values 

[//]: # (priorty queue, arrays , Collections)


* sum(): Retuns sum the elements of an iterable.
* max(): Finds the maximum value in an iterable.
* min(): Finds the minimum value in an iterable.
* len(): Returns the number of elements in an iterable.
* abs()
* pow()
* 
* filter(function, iterable)-> filteredObject: Filters an iterable based on a given function.
* map(function, iterable) : Applies a function to each element of an iterable.
* sorted(iterable, key, reverse) -> list: Returns a sorted list of the elements of an iterable.
* 
* enumerate(): Adds a counter to each element in an iterable, returning tuples of (index, element).


---

## 1. List

A **list** in Python is an **ordered**, **mutable** collection of items.

### Constructor
- **`list([iterable])`**  
  Creates a list from an iterable, tuple, set, . If no argument is provided, it creates an empty list.
  ```python
  my_list = list()          # Empty list
  my_list = list(range(5))  # [0, 1, 2, 3, 4]
  my_list = []  # Empty list
  my_list = [1]*5  # [1, 1, 1, 1, 1]

  ```

### Common Methods
1. **`append(x)`**  
   Adds an item `x` to the end of the list.
   ```python
   my_list.append(10) #[10]
   my_list.append(20) #[10,20]
   my_list.append(30) #[10,20,30]

   ```
2. **`extend(iterable)`**  
   Extends a list by appending elements from another iterable.
   ```python
   my_list.extend([20, 30])
   my_list = my_list + [20, 30]
   ```
3. **`insert(i, x)`**  
   Inserts an item `x` at position `i`.
   ```python
   my_list.insert(0, 99)
   ```
4. **`remove(x)`**  
   Removes the first item in the list whose value is `x`. Raises `ValueError` if not found.
   ```python
   my_list.remove(20)
   ```
5. **`pop([i])`**  
   Removes and returns the item at index `i`. If `i` is not specified, removes and returns the last item.
   ```python
   last_item = my_list.pop()
   first_item = my_list.pop(0)
   ```
   
5. **access elements**

```python
my_list[0]  # First element
my_list[-1]  # Last element
my_list[1:3]  # Returns elements from index 1 to 2
my_list[:3]  # First three elements
my_list[2:]  # Elements from index 2 to the end
my_list[::2]  # Every second element
my_list[::-1]  # Every element from back or reversed
my_list[::-2]  # Every second element from back

print(my_list[::])  # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print(my_list[::3])  # [0, 3, 6, 9]
print(my_list[::-2])  # [9, 7, 5, 3, 1]

```
6. **`clear()`**  
   Removes all items from the list (makes it empty).
   ```python
   my_list.clear()
   ```
7. **`index(x)`**  
   Returns the zero-based index of the first item whose value is `x`. Raises `ValueError` if not found.
   ```python
   idx = my_list.index(99)
   ```
8. **`count(x)`**  
   Returns the number of times `x` appears in the list.
   ```python
   count_of_99 = my_list.count(99)
   ```
9. **`sort(key=None, reverse=False)`**  
   Sorts the list in-place, optionally using a `key` function and a `reverse` flag.
   ```python
   my_list.sort()
   my_list.sort(reverse=True)
   my_list.sort(key=lambda x: -x)  # Example: sort descending by value
   ```
10. **`reverse()`**  
    Reverses the list in-place.
    ```python
    my_list.reverse()
    ```
11. **`copy()`**  
    Returns a shallow copy of the list.
    ```python
    new_list = my_list.copy()
    ```

---




## 2. Tuple

A **tuple** is an **ordered**, **immutable** collection of items. Because tuples are immutable and allow Duplicates, they have fewer built-in methods.

### Constructor
- **`tuple([iterable])`**  
  Creates a tuple from an iterable. If no argument is provided, it creates an empty tuple.
  ```python
  my_tuple = tuple()              # Empty tuple
  my_tuple = tuple([1, 2, 3])     # (1, 2, 3)
  thistuple = ("apple", "banana", "cherry")

  ```

### Common Methods
1. **`count(x)`**  
   Returns the number of times `x` appears in the tuple.
   ```python
   my_tuple.count(2)
   ```
2. **`index(x)`**  
   Returns the zero-based index of the first item whose value is `x`. Raises `ValueError` if not found.
   ```python
   my_tuple.index(3)
   ```

*(Tuples do not support item assignment or any modifying operations since they are immutable.)*

---

## 3. Set

A **set** is an **unordered**, **mutable** collection of **unique** items. No duplicates are allowed in a set.

### Constructor
- **`set([iterable])`**  
  Creates a set from an iterable. If no argument is provided, it creates an empty set.
  ```python
  my_set = set()               # Empty set
  my_set = set([1, 2, 3, 2])   # {1, 2, 3} (duplicates removed)
  my_set = {1, 2, 3, 3}  # {1, 2, 3} (duplicates removed)
  ```

### Common Methods
1. **`add(x)`**  
   Adds an item `x` to the set.
   ```python
   my_set.add(4)
   ```
2. **`update(iterable)`**  
   Updates the set, adding items from an iterable (like union, but in-place).
   ```python
   my_set.update([5, 6])
   ```
3. **`remove(x)`**  
   Removes `x` from the set. Raises a `KeyError` if `x` is not in the set.
   ```python
   my_set.remove(2)
   ```
4. **`discard(x)`**  
   Removes `x` from the set if it is present. Does nothing if `x` is not in the set.
   ```python
   my_set.discard(100)
   ```
5. **`pop()`**  
   Removes and returns an arbitrary element from the set. Raises a `KeyError` if the set is empty.
   ```python
   item = my_set.pop()
   ```
6. **`clear()`**  
   Removes all items from the set.
   ```python
   my_set.clear()
   ```
7. **`union(*others)`** (or the operator `|`)  
   Returns a **new set** with elements from the set and all others.
   ```python
   new_set = my_set.union({7, 8})
   # or
   new_set = my_set | {7, 8}
   ```
8. **`intersection(*others)`** (or the operator `&`)  
   Returns a **new set** with elements common to the set and all others.
   ```python
   new_set = my_set.intersection({1, 3, 5})
   # or
   new_set = my_set & {1, 3, 5}
   ```
9. **`difference(*others)`** (or the operator `-`)  
   Returns a **new set** with elements in the set that are not in the others.
   ```python
   new_set = my_set.difference({1, 2})
   # or
   new_set = my_set - {1, 2, 4}  # {3}
   ```
10. **`symmetric_difference(other)`** (or the operator `^`)  
    Returns a **new set** with elements in either the set or `other`, but not in both.
    ```python
    new_set = my_set.symmetric_difference({2, 3})
    # or
    new_set = my_set ^ {2, 3}
    ```
11. **`isdisjoint(other)`**  
    Returns `True` if the set has no elements in common with `other`.
    ```python
    my_set.isdisjoint({10, 11})
    ```
12. **`issubset(other)`** (or the operator `<=`)  
    Returns `True` if the set is a subset of `other`.
    ```python
    my_set.issubset({1, 2, 3, 4})
    # or
    my_set <= {1, 2, 3, 4}
    ```
13. **`issuperset(other)`** (or the operator `>=`)  
    Returns `True` if the set is a superset of `other`.
    ```python
    my_set.issuperset({1, 2})
    # or
    my_set >= {1, 2}
    ```
14. **`copy()`**  
    Returns a shallow copy of the set.
    ```python
    new_set = my_set.copy()
    ```

---

## 4. Dictionary

A **dictionary** (`dict`) is an **unordered**, **mutable** collection of **key-value** pairs.

### Constructor
- **`dict()`**  
  Creates an empty dictionary or can be used with keyword arguments or a mapping/iterable of key-value pairs.
  ```python
  my_dict = dict()  # Empty dict
  my_dict = dict(a=1, b=2)  # {'a': 1, 'b': 2}
  my_dict = {'a': 1, 'b': 2}
  my_dict = dict([('x', 10), ('y', 20)])  # {'x': 10, 'y': 20}
  ```

### Common Methods
1. **`keys()`**  
   Returns a view of all the keys in the dictionary.
   ```python
   my_dict.keys()  # dict_keys(['x', 'y'])
   ```
2. **`values()`**  
   Returns a view of all the values in the dictionary.
   ```python
   my_dict.values()  # dict_values([10, 20])
   ```
3. **`items()`**  
   Returns a view of all the `(key, value)` pairs in the dictionary as list((key,value)).
   ```python
   my_dict.items()  # dict_items([('x', 10), ('y', 20)])
   ```
4. **`get(key[, default])`**  
   Returns the value for `key` if it exists, else returns `default` (or `None` if `default` is not provided).
   ```python
   value = my_dict.get('x')         # 10
   missing = my_dict.get('z', 0)    # 0 if 'z' not found
   ```
5. **`pop(key[, default])`**  
   Removes and returns the value for `key`. If `key` is not found and `default` is not provided, raises `KeyError`.
   ```python
   val = my_dict.pop('x')              # Removes 'x': 10
   val_missing = my_dict.pop('z', 999) # Returns 999 if not found
   ```
6. **`popitem()`**  
   Removes and returns an **arbitrary** `(key, value)` pair from the dictionary. Raises `KeyError` if empty.
   ```python
   key, value = my_dict.popitem()
   ```
7. **`update([other])`**  
   Updates the dictionary with key-value pairs from `other`, overwriting existing keys.
   ```python
   my_dict.update({'a': 100, 'b': 200})
   my_dict.update(dict2)

   ```
8. **`setdefault(key[, default])`**  
   If `key` is in the dictionary, returns its value. If not, inserts `key` with a value of `default` (or `None` if not provided).
   ```python
   my_dict.setdefault('z', 999)
   ```
9. **`clear()`**  
   Removes all items from the dictionary.
   ```python
   my_dict.clear()
   ```
10. **`copy()`**  
    Returns a shallow copy of the dictionary.
    ```python
    new_dict = my_dict.copy()
    ```
11. **`fromkeys(iterable[, value])`** (class method)  
    Creates a new dictionary with keys from `iterable` and values set to `value` (defaults to `None`).
    ```python
    new_dict = dict.fromkeys(['a', 'b', 'c'], 0)
    new_dict = dict.fromkeys(['a', 'b', 'c'], 0)
    # {'a': 0, 'b': 0, 'c': 0}
    ```

### Dictionary Merge & Update Operators (Python 3.9+)
Python 3.9 introduced two additional operators for dictionaries, which are fully available in Python 3.10:

- **Dictionary Merge Operator (`|`)**  
  Creates a **new** dictionary by merging two dictionaries. The second dictionary’s keys overwrite the first dictionary’s keys if there is a conflict.
  ```python
  d1 = {'a': 1, 'b': 2}
  d2 = {'b': 20, 'c': 30}
  d3 = d1 | d2  # {'a': 1, 'b': 20, 'c': 30}
  ```

- **Dictionary Update Operator (`|=`)**  
  Updates a dictionary **in-place**.
  ```python
  d1 |= d2  # Now d1 is {'a': 1, 'b': 20, 'c': 30}
  ```

---



## Python Iterators


## PriorityQueue

In Python, there are **two common ways** to implement a priority queue:

1. **Using the `queue.PriorityQueue` class** from the `queue` module (thread-safe).
2. **Using the `heapq` module** (a lower-level heap-based implementation).

Below is an overview of both approaches.

---

## 1. `queue.PriorityQueue`

The `queue` module provides a **thread-safe** implementation of a priority queue called `PriorityQueue`. It stores items in **ascending order** by priority (the smallest item is retrieved first).

### Basic Usage

```python
import queue

pq = queue.PriorityQueue()

pq.put(5)
pq.put(1)
pq.put(3)

print(pq.get())  # 1 (lowest priority first)
print(pq.get())  # 3
print(pq.get())  # 5
```

#### Key Points
- **Thread-Safe**: Multiple threads can safely use the same `PriorityQueue`.
- Uses **blocking** methods like `put()` and `get()` that can also take timeouts (`put(item, timeout=...)`, `get(timeout=...)`) if needed.
- By default, it’s a **min-priority** queue (the item with the lowest value is considered the highest priority and thus gets popped first).

### Using Priority Tuples

Often, you’ll want to associate **priority** separately from the data. A common pattern is to push tuples of the form **`(priority, data)`**:

```python
import queue

pq = queue.PriorityQueue()

pq.put((2, "Second priority"))
pq.put((1, "First priority"))
pq.put((3, "Third priority"))

while not pq.empty():
    item = pq.get()
    print(item)
    # Outputs in order of increasing priority:
    # (1, 'First priority')
    # (2, 'Second priority')
    # (3, 'Third priority')
```

---




## Max priority Queue

- **No direct setting** exists in Python’s built-in `PriorityQueue` for max priority.
- **Trick**: **store negative** priorities if you want the largest priority to come out first.

## Example: Using `PriorityQueue` as a Max-Priority Queue

```python
from queue import PriorityQueue

# Let's say a higher number means higher priority
tasks = [
    (10, "High priority"),
    (5, "Medium priority"),
    (1, "Low priority"),
]

pq = PriorityQueue()

# Push items with NEGATIVE priority
for priority, data in tasks:
    pq.put((-priority, data))

# The get() now returns items in the order of highest original priority first
while not pq.empty():
    neg_priority, data = pq.get()
    original_priority = -neg_priority
    print(f"Priority: {original_priority}, Data: {data}")

# Output:
# Priority: 10, Data: High priority
# Priority: 5,  Data: Medium priority
# Priority: 1,  Data: Low priority
```




---

# Python examples

```python 
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        seen = {}

        for i, val in enumerate(nums):
            if val in seen and i - seen[val] <= k:
                return True
            else:
                seen[val] = i
        
        return False
```


```python

class Solution(object):
    def wordPattern(self, pattern, s):
        word_list = s.split()

        memo_ps = {}
        memo_sp = {}


        if len(pattern) != len(word_list):
            return False

        for c1,c2 in zip(pattern, word_list):
            if (c1 in memo_ps and memo_ps[c1] != c2) or (c2 in memo_sp and memo_sp[c2] != c1):
                return False

            memo_ps[c1] = c2
            memo_sp[c2] = c1

        return True
```
