> **Note**: One relevant change that arrived in **Python 3.9** is the **dictionary merge (`|`) and update (`|=`) operators**, which you can use in Python 3.10 as well.
> by-default all the iterable functions will work on dictionary's key only it does not take values 

```python 
print([1] == [1])

print([1] is [1])

print({1:1} == {1:1})

print({1:1} is {1:1})

```


In Python, the `==` operator checks for **value equality**, while the `is` operator checks for **object identity** (whether two objects refer to the same memory location).

### Equivalent Java Code:
Java doesn't have an `is` operator like Python, but you can achieve the same checks using `equals()` for value equality and `==` for reference equality.

#### Equivalent Java Implementation:
```java
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualityCheck {
    public static void main(String[] args) {
        // Checking value and reference equality for lists
        List<Integer> list1 = List.of(1);
        List<Integer> list2 = List.of(1);
        System.out.println(list1.equals(list2)); // Equivalent to Python's [1] == [1]
        System.out.println(list1 == list2); // Equivalent to Python's [1] is [1]

        // Checking value and reference equality for maps
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1, 1);

        Map<Integer, Integer> map2 = new HashMap<>();
        map2.put(1, 1);

        System.out.println(map1.equals(map2)); // Equivalent to Python's {1:1} == {1:1}
        System.out.println(map1 == map2); // Equivalent to Python's {1:1} is {1:1}
    }
}
```

### Explanation:
1. `list1.equals(list2)`: ✅ `true` → Checks **value equality**, so two lists with the same elements are considered equal.
2. `list1 == list2`: ❌ `false` → Checks **reference equality**, and since `List.of(1)` creates new objects, they are different.
3. `map1.equals(map2)`: ✅ `true` → Checks **value equality**, meaning two maps with the same key-value pairs are equal.
4. `map1 == map2`: ❌ `false` → Checks **reference equality**, and since both are separately created instances, they are different.

Would you like a deeper explanation of Java's `equals()` vs. `==`?






Here are detailed notes on **`is`**, **`in`**, **`not in`**, and **`is not`** in Python with examples and use cases:

---

```python
e,f = c = [a,b] =[1,2]
print(a,b, c, e,f)
# 1 2 [1, 2] 1 2
d = {'a': 1}
d.setdefault('b', 2)
name = "ganesh"

print(f'name {name} {d}')
# name ganesh {'a': 1, 'b': 2}

```

## The **`enumerate()`** function in Python is a built-in function that adds a counter to an iterable and returns it as an `enumerate` object. This is especially useful when you need both the index and the value of elements in a loop.

### **Syntax**
```python
enumerate(iterable, start=0)
```

- **`iterable`**: The object you want to enumerate (e.g., list, tuple, string, etc.).
- **`start`** *(optional)*: The starting value of the counter. Defaults to `0`.

---

### **Returns**
An **`enumerate` object**, which is an iterable of tuples. Each tuple contains:
1. The index (starting from `start`).
2. The corresponding item from the iterable.

---

### **Examples**

#### 1. Enumerating a List
```python
fruits = ["apple", "banana", "cherry"]
for index, fruit in enumerate(fruits):
    print(index, fruit)
```

**Output**:
```
0 apple
1 banana
2 cherry
```

---

#### 2. Custom Starting Index
```python
fruits = ["apple", "banana", "cherry"]
for index, fruit in enumerate(fruits, start=1):
    print(index, fruit)
```

**Output**:
```
1 apple
2 banana
3 cherry
```

---

#### 3. Enumerating a String
```python
word = "Python"
for index, char in enumerate(word):
    print(index, char)
```

**Output**:
```
0 P
1 y
2 t
3 h
4 o
5 n
```

---


---

#### 5. Using `enumerate()` with `list()`
You can convert the `enumerate` object into a list of tuples.
```python
fruits = ["apple", "banana", "cherry"]
result = list(enumerate(fruits))
print(result)
```

**Output**:
```
[(0, 'apple'), (1, 'banana'), (2, 'cherry')]
```


```

### 1. **`is`**
- The `is` operator checks if **two variables refer to the same object in memory** (i.e., it compares their identities).
- It does **not compare the values** of the objects, only their memory locations.

#### Syntax:
```python
a is b
```

#### Example:
```python
x = [1, 2, 3]
y = x  # y refers to the same list as x
z = [1, 2, 3]  # z is a different list with the same values

print(x is y)  # True (same memory location)
print(x is z)  # False (different memory locations)
```

#### Use in DSA:
- Use when you need to check object identity (e.g., singleton patterns, `None` comparisons).

---

### 2. **`is not`**
- The `is not` operator checks if **two variables do not refer to the same object in memory**.

#### Syntax:
```python
a is not b
```

#### Example:
```python
x = [1, 2, 3]
z = [1, 2, 3]

print(x is not z)  # True (different memory locations)
```

#### Common Use Case:
Check if a variable does not refer to a specific object (e.g., `None`):
```python
x = None
if x is not None:
    print("x is not None")
```

---

### 3. **`in`**
- The `in` operator checks if a **value exists in an iterable** (e.g., list, set, string, tuple, dictionary keys).

#### Syntax:
```python
value in iterable
```

#### Example:
```python
lst = [1, 2, 3]
print(2 in lst)  # True (2 is in the list)
print(4 in lst)  # False (4 is not in the list)

string = "hello"
print("h" in string)  # True ('h' is in the string)
```

#### Use in DSA:
- Search for an element in a list, set, or dictionary efficiently.

For **dictionary keys**:
```python
d = {'a': 1, 'b': 2}
print('a' in d)  # True (key 'a' exists)
print(1 in d)    # False (1 is not a key, only values are checked)
```

---

### 4. **`not in`**
- The `not in` operator checks if a **value does not exist in an iterable**.

#### Syntax:
```python
value not in iterable
```

#### Example:
```python
lst = [1, 2, 3]
print(4 not in lst)  # True (4 is not in the list)

string = "hello"
print("z" not in string)  # True ('z' is not in the string)
```

#### Use in DSA:
- Check for the absence of an element in an iterable.

---

### Comparison of `is` vs `==`:
- **`is`**: Checks if two objects are the same in memory (identity comparison).
- **`==`**: Checks if the values of two objects are equal (value comparison).

#### Example:
```python
a = [1, 2, 3]
b = [1, 2, 3]
c = a

print(a == b)  # True (values are equal)
print(a is b)  # False (different objects)
print(a is c)  # True (same object in memory)
```

---

### Performance Notes:
1. **`in` for `list`**: Time complexity is \(O(n)\) (linear search).
2. **`in` for `set`/`dict`**: Time complexity is \(O(1)\) (hash-based search).
3. **`is`**: Constant time comparison, checks memory location.

---

### Common DSA Use Cases:
1. **`is` and `is not`**: checks for reference equality (`==`)
    - Checking for `None`:
      ```python
      if node is None:
          print("End of the list")
      ```
2. **`in`**:
    - Searching for a key in a dictionary:
      ```python
      if key in hashmap/set/list:
          print("Key exists")
      ```
    - Membership checks in sets (e.g., for duplicates or visited elements).
3. **`not in`**:
    - Finding unique elements:
      ```python
      result = [x for x in arr if x not in seen]
      ```
4. **`is vs ==`**:
    - Use `is` for identity (e.g., `None`) and `==` for value comparison.

---



[//]: # (priorty queue, arrays , Collections)

* len(): Returns the number of elements in an iterable.
* abs()
* pow()

* filter(function, iterable)-> filteredObject: Filters an iterable based on a given function.
* map(function, iterable) : Applies a function to each element of an iterable.


* sum(iterable, key): Retuns sum the elements of an iterable.
* max(iterable, key): Finds the maximum value in an iterable.
* min(iterable, key): Finds the minimum value in an iterable.
* sorted(iterable, key, reverse) -> list: Returns a sorted list of the elements of an iterable.


* range(5) - give list of items (from, to , gap) - don't use as indexing use while to change index 
* enumerate(iterable, start=0): Adds a counter to each element in an iterable, returning tuples of (index, element).

* l = [[-1]*(5)]*10 not good idea since it's copy of references 
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
### 19. **`zip()`**
Combines multiple lists into tuples of corresponding elements.

#### Example:
```python
a = [1, 2]
b = [3, 4]
zipped = list(zip(a, b))  # [(1, 3), (2, 4)]
```

#### Use in DSA:
Pair elements from multiple lists (e.g., for matrix operations).

---

### 20. **`list comprehension`**
A concise way to create lists.

#### Example:
```python
squared = [x**2 for x in range(5)]  # [0, 1, 4, 9, 16]
even = [x for x in range(10) if x % 2 == 0]  # [0, 2, 4, 6, 8]
```

#### Use in DSA:
Efficient initialization of lists.

---

### 21. **`any()` and `all()`**
- **`any()`**: Returns `True` if any element in the list is `True`.
- **`all()`**: Returns `True` if all elements in the list are `True`.

#### Example:
```python
lst = [0, 1, 2]
any(lst)  # True (at least one non-zero value)
all(lst)  # False (not all are non-zero)
```

#### Use in DSA:
Quickly check boolean conditions.

---

### 22. **`reversed()`**
Returns an iterator that iterates through the list in reverse order.

#### Example:
```python
lst = [1, 2, 3]
reversed(lst)  # Iterator
list(reversed(lst))  # [3, 2, 1]
```

#### Use in DSA:
Process elements in reverse.

---

### 23. **`flattening with itertools.chain()`**
Flattens nested lists.

#### Example:
```python
from itertools import chain
nested = [[1, 2], [3, 4]]
flat = list(chain.from_iterable(nested))  # [1, 2, 3, 4]
```

#### Use in DSA:
Simplify nested data structures.



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

### 1. **`get()`**
Retrieves the value for a specified key, but allows a default value if the key is not found.

#### Example:
```python
d = {'a': 1, 'b': 2}
d.get('a')  # 1
d.get('c', 0)  # 0 (default value)
```

#### Use in DSA:
Avoids `KeyError` while accessing keys and allows handling missing keys gracefully.

---

### 10. **`max()` and `min()` with Keys**
Retrieve the key with the maximum or minimum value.

#### Example:
```python
d = {'a': 1, 'b': 5, 'c': 3}
max(d, key=d.get)  # 'b' (key with max value)
min(d, key=d.get)  # 'a' (key with min value)
```

#### Use in DSA:
Find optimal elements based on their values.

---

### 11. **`sorted()` with Dictionaries**
Sort dictionary keys or values.

#### Example:
```python
d = {'a': 3, 'b': 1, 'c': 2}
sorted(d)  # ['a', 'b', 'c'] (keys sorted)
sorted(d.items(), key=lambda x: x[1])  # [('b', 1), ('c', 2), ('a', 3)] (sort by value)
```

#### Use in DSA:
Sort based on custom criteria.



### 15. **`dict comprehension`**
Quickly create dictionaries using comprehensions.

#### Example:
```python
squares = {x: x**2 for x in range(5)}  # {0: 0, 1: 1, 2: 4, 3: 9, 4: 16}
```

#### Use in DSA:
Compact initialization logic.

## 5. String 

Here are some Python string functions essential for DSA:

### 1. `isalnum()`
The `isalnum()` method checks if a string contains only alphanumeric characters (letters and numbers). It returns `True` if the string consists solely of alphanumeric characters, otherwise it returns `False`.

#### Example:
```python
"Hello123".isalnum()  # True
"Hello World".isalnum()  # False (contains space)
"12345".isalnum()  # True
"Hello!".isalnum()  # False (contains special character)
```

### 2. `isdigit()`
The `isdigit()` method checks if a string consists only of digits. It returns `True` if the string contains only numeric characters, and `False` otherwise.

#### Example:
```python
"12345".isdigit()  # True
"Hello123".isdigit()  # False (contains letters)
```

### 3. `isspace()`
The `isspace()` method checks if all characters in the string are whitespace. It returns `True` if the string contains only whitespace characters, otherwise it returns `False`.

#### Example:
```python
"   ".isspace()  # True
"Hello".isspace()  # False
```

### 4. `isupper()`
The `isupper()` method checks if all characters in a string are uppercase. It returns `True` if all characters are uppercase, otherwise `False`.

#### Example:
```python
"HELLO".isupper()  # True
"Hello".isupper()  # False
```

### 5. `islower()`
The `islower()` method checks if all characters in a string are lowercase. It returns `True` if all characters are lowercase, otherwise `False`.

#### Example:
```python
"hello".islower()  # True
"Hello".islower()  # False
```

### 6. `startswith()`
The `startswith()` method checks if a string starts with a specified prefix. It returns `True` if the string starts with the given prefix, otherwise `False`.

#### Example:
```python
"HelloWorld".startswith("Hello")  # True
"HelloWorld".startswith("world")  # False
```

### 7. `endswith()`
The `endswith()` method checks if a string ends with a specified suffix. It returns `True` if the string ends with the given suffix, otherwise `False`.

#### Example:
```python
"HelloWorld".endswith("World")  # True
"HelloWorld".endswith("hello")  # False
```

### 8. `find()`
The `find()` method returns the lowest index in the string where the substring is found. If the substring is not found, it returns `-1`.

#### Example:
```python
"HelloWorld".find("World")  # 5
"HelloWorld".find("Python")  # -1
```

### 9. `replace()`
The `replace()` method replaces a specified substring with another substring.

#### Example:
```python
"HelloWorld".replace("World", "Python")  # "HelloPython"
```

### 10. `split()`
The `split()` method splits a string into a list of substrings based on a specified delimiter (default is space).

#### Example:
```python
"Hello World".split()  # ['Hello', 'World']
"apple,banana,grape".split(",")  # ['apple', 'banana', 'grape']
```


---

### 1. **`join()`**
Combines elements of an iterable (e.g., list) into a single string with a specified separator.

#### Example:
```python
"-".join(["DSA", "is", "fun"])  # "DSA-is-fun"
"".join(["H", "e", "l", "l", "o"])  # "Hello"
```

#### Use in DSA:
Quickly merge strings or array elements into a single string.

---

### 2. **`strip()`**
Removes leading and trailing whitespace or specified characters.

#### Example:
```python
"  Hello World  ".strip()  # "Hello World"
"**Hello**".strip("*")  # "Hello"
```

#### Use in DSA:
Clean strings before processing or comparison.

---

### 3. **`partition()`**
Splits a string into three parts: before, the separator itself, and after the separator. Returns a tuple.

#### Example:
```python
"key:value".partition(":")  # ('key', ':', 'value')
"no-separator".partition(":")  # ('no-separator', '', '')
```

#### Use in DSA:
Helpful when parsing key-value pairs or structured strings.

---

### 4. **`rpartition()`**
Similar to `partition()`, but searches for the separator starting from the end of the string.

#### Example:
```python
"key1:key2:value".rpartition(":")  # ('key1:key2', ':', 'value')
```

#### Use in DSA:
Useful for parsing strings with hierarchical structure.

---

### 5. **`zfill()`**
Pads a numeric string with zeros on the left to fill a specified width.

#### Example:
```python
"42".zfill(5)  # "00042"
"-42".zfill(5)  # "-0042"
```

#### Use in DSA:
Align numeric strings for easier processing or output formatting.

---

### 6. **`translate()`**
Replaces characters in a string based on a translation table created with `str.maketrans()`.

#### Example:
```python
table = str.maketrans("abc", "123")
"abcxyz".translate(table)  # "123xyz"
```

#### Use in DSA:
Quick character substitutions or encoding.

---

### 7. **`count()`**
Counts the occurrences of a substring in the string.

#### Example:
```python
"banana".count("a")  # 3
"banana".count("na")  # 2
```

#### Use in DSA:
Count patterns or elements in a string efficiently.

---

### 8. **`startswith()` and `endswith()`**
Checks if a string starts or ends with specific prefixes or suffixes.

#### Example:
```python
"datastructure".startswith("data")  # True
"datastructure".endswith("algo")  # False
```

#### Use in DSA:
Pattern-matching in strings.

---

### 9. **`expandtabs()`**
Replaces tab characters (`\t`) with spaces. Default tab size is 8.

#### Example:
```python
"Hello\tWorld".expandtabs(4)  # "Hello   World"
```

#### Use in DSA:
Normalize strings with tabs before processing.

---

### 16. upper() and lower()

```python 
print("ganesh".lower())
print('a'.upper())
print("ganesh".upper())

# ganesh
# A
# GANESH
```

---

### 10. **`capitalize()`**
Capitalizes the first character of a string and makes the rest lowercase.

#### Example:
```python
"hello world".capitalize()  # "Hello world"
```

#### Use in DSA:
Format strings for better readability.

---

### 11. **`swapcase()`**
Swaps the case of all characters in the string.

#### Example:
```python
"Hello World".swapcase()  # "hELLO wORLD"
```

#### Use in DSA:
Quick transformations during text-based challenges.

---

### 12. **`index()`**
Returns the index of the first occurrence of a substring. Raises `ValueError` if the substring is not found.

#### Example:
```python
"datastructure".index("str")  # 4
```

#### Use in DSA:
Locate substrings for further operations (throws error if not found).

---

### 13. **`rindex()`**
Like `index()`, but searches for the substring starting from the end of the string.

#### Example:
```python
"datastructure".rindex("a")  # 7
```

#### Use in DSA:
Find the last occurrence of a substring.

---

### 14. **`format()`**
Formats strings dynamically with placeholders.

#### Example:
```python
"Hello, {}!".format("World")  # "Hello, World!"
"{0} + {1} = {2}".format(2, 3, 5)  # "2 + 3 = 5"

f'name {variable}'
```

#### Use in DSA:
Build output strings in coding contests.

---

### 15. **`splitlines()`**
Splits a string into a list at line breaks.

#### Example:
```python
"Hello\nWorld".splitlines()  # ['Hello', 'World']
```

#### Use in DSA:
Efficiently parse multiline input.




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


### **Counter for Dictionary in Python**
The `collections.Counter` class in Python provides a convenient way to count elements in an iterable and manage frequency-based data structures efficiently.

#### **1. Importing and Creating a Counter**
The `Counter` class is part of the `collections` module. You can create a `Counter` from:
- A list or iterable
- A dictionary with keys and counts

```python
from collections import Counter

# Example 1: Creating a Counter from a list
nums = [1, 2, 2, 3, 3, 3, 4, 4, 4, 4]
counter = Counter(nums)
print(counter)  
# Output: Counter({4: 4, 3: 3, 2: 2, 1: 1})

# Example 2: Creating a Counter from a dictionary
word_freq = {'apple': 3, 'banana': 2, 'cherry': 5}
counter_dict = Counter(word_freq)
print(counter_dict)  
# Output: Counter({'cherry': 5, 'apple': 3, 'banana': 2})
```

#### **2. Accessing Elements**
You can access counts directly using dictionary-like syntax.

```python
print(counter[3])  # Output: 3 (since 3 appears three times)
print(counter[5])  # Output: 0 (not present in Counter)
```

#### **3. Most Common Elements**
The `most_common(n)` method retrieves the `n` most frequent elements.

```python
print(counter.most_common(2))  
# Output: [(4, 4), (3, 3)] - The two most common elements
```

#### **4. Updating a Counter**
You can update a `Counter` with new elements.

```python
counter.update([3, 4, 4, 5])
print(counter)  
# Output: Counter({4: 6, 3: 4, 2: 2, 1: 1, 5: 1})
```

#### **5. Arithmetic Operations**
You can perform addition, subtraction, intersection, and union on two `Counter` objects.

```python
c1 = Counter(a=3, b=1)
c2 = Counter(a=1, b=2, c=1)

# Addition (sums counts)
print(c1 + c2)  
# Output: Counter({'a': 4, 'b': 3, 'c': 1})

# Subtraction (only keeps positive counts)
print(c1 - c2)  
# Output: Counter({'a': 2})  

# Intersection (min count of shared keys)
print(c1 & c2)  
# Output: Counter({'a': 1, 'b': 1})

# Union (max count of shared keys)
print(c1 | c2)  
# Output: Counter({'a': 3, 'b': 2, 'c': 1})
```

---

### **Using `heapq.nlargest` to Retrieve Top Elements**
The `heapq` module provides efficient heap-based operations. The function `heapq.nlargest(n, iterable, key)` is used to retrieve the `n` largest elements from an iterable based on a given key.

#### **1. Finding Top N Elements from a Dictionary**
We can use `heapq.nlargest` to extract the highest values from a dictionary.

```python
from heapq import nlargest

word_freq = {'apple': 3, 'banana': 2, 'cherry': 5, 'date': 4}
top_2 = nlargest(2, word_freq, key=word_freq.get)
print(top_2)  
# Output: ['cherry', 'date'] (words with highest frequencies)
```

#### **2. Using with Counter**
Since `Counter` behaves like a dictionary, we can use `nlargest` with it.

```python
from collections import Counter
from heapq import nlargest

words = ['apple', 'banana', 'apple', 'cherry', 'banana', 'apple', 'cherry', 'cherry', 'cherry']
word_counter = Counter(words)

# Finding the top 2 most frequent words
top_2_words = nlargest(2, word_counter, key=word_counter.get)
print(top_2_words)  
# Output: ['cherry', 'apple']
```

#### **3. Finding Top N Numbers in a List**
We can also apply `heapq.nlargest` on a list.

```python
nums = [10, 3, 5, 20, 8, 25, 15]
top_3 = nlargest(3, nums)
print(top_3)  
# Output: [25, 20, 15]
```

#### **4. Finding Top N Tuples Based on a Specific Key**
If we have a list of tuples, we can extract the top `n` elements based on a specific attribute.

```python
students = [
    ('Alice', 85),
    ('Bob', 75),
    ('Charlie', 90),
    ('David', 80)
]

top_students = nlargest(2, students, key=lambda x: x[1])
print(top_students)  
# Output: [('Charlie', 90), ('Alice', 85)]
```

---

### **Key Takeaways**
1. `Counter` is useful for counting elements efficiently.
2. It behaves like a dictionary but supports additional operations like `most_common()` and arithmetic operations.
3. `heapq.nlargest(n, iterable, key)` helps extract the top `n` elements efficiently.
4. It is particularly useful for sorting dictionaries or lists with custom sorting criteria.

This combination of `Counter` and `heapq.nlargest` is frequently used in competitive programming, data analysis, and building recommendation systems.