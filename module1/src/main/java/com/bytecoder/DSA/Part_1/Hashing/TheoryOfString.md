**Problem**  find the first non-repeating character in a string 

Hint: 
* brute-force solution 
* hash-table solution 


** sort based on key/value and apply filter /map

```python 

from collections import Counter
mapper = Counter(s)
```


https://leetcode.com/problems/isomorphic-strings/description/ 
https://leetcode.com/problems/bulls-and-cows/description/ 
https://leetcode.com/problems/custom-sort-string/description/ 



3. **Valid Anagram**  
   - *Problem*: Check if two strings are anagrams of each other.  
   - *Concept*: Use a HashMap to count character frequencies.  
   - [LeetCode](https://leetcode.com/problems/valid-anagram/)
    
```python
    def isAnagram(self, s: str, t: str) -> bool:
        return sorted(s)==sorted(t)
```

4. **First Unique Character in a String**  
   - *Problem*: Find the first non-repeating character in a string.  
   - *Concept*: Use a HashMap to track character counts and their indices.  
   - [LeetCode](https://leetcode.com/problems/first-unique-character-in-a-string/)

5. **Group Anagrams**  
   - *Problem*: Group strings that are anagrams of each other.  
   - *Concept*: Use a HashMap with sorted strings as keys.  
   - [LeetCode](https://leetcode.com/problems/group-anagrams/)

---



8. **Top K Frequent Elements**  
   - *Problem*: Find the `k` most frequent elements in an array.  
   - *Concept*: Use a HashMap to count frequencies, then use a heap for sorting.  
   - [LeetCode](https://leetcode.com/problems/top-k-frequent-elements/)

9. **Longest Consecutive Sequence**  
   - *Problem*: Find the length of the longest sequence of consecutive integers.  
   - *Concept*: Use a HashSet (or HashMap) to store numbers and check neighbors. 
   - use sorted set and priority queue check previous element
   - [LeetCode](https://leetcode.com/problems/longest-consecutive-sequence/)

```python

class Solution:
    def longestConsecutive(self, nums: list[int]) -> int:
        num_set = set(nums)  # Store all numbers in a set for O(1) lookups
        max_len = 0

        for num in num_set:
            # Check if it's the start of a sequence (num - 1 is not in the set)
            if num - 1 not in num_set:
                current_num = num
                count = 1

                # Expand the sequence
                while current_num + 1 in num_set:
                    current_num += 1
                    count += 1

                max_len = max(max_len, count)

        return max_len

```


10. **Intersection of Two Arrays II**  
    - *Problem*: Find the intersection of two arrays, allowing duplicate elements.  
    - *Concept*: Use a HashMap to count elements in one array and match them in the other.  
    - [LeetCode](https://leetcode.com/problems/intersection-of-two-arrays-ii/)


- ** https://leetcode.com/problems/fraction-to-recurring-decimal/description/ 

- https://leetcode.com/problems/isomorphic-strings/description/?envType=study-plan-v2&envId=top-interview-150

```python

def isIsomorphic(self, s: str, t: str) -> bool:
    if(len(s)!= len(t)):
        return False
    s1 =list(s)
    t1 =list(t)
    memo={}
    for i in range(len(s)):
        if(memo.get(s[i]) is None and t[i] not in t[:i] ):
            memo[s[i]]=t[i]
        elif(memo.get(s[i]) != t[i]):
            return False
    return True 

```

---
### **Hard Problems**
11. **Four Sum II**  
    - *Problem*: Given four lists of integers, count all tuples `(i, j, k, l)` such that `A[i] + B[j] + C[k] + D[l] = 0`.  
    - *Concept*: Use a HashMap to store sums of pairs and check for complements.  
    - [LeetCode](https://leetcode.com/problems/4sum-ii/)

12. **Minimum Window Substring**  
    - *Problem*: Find the smallest substring of `s` containing all characters of `t`.  
    - *Concept*: Use a sliding window and a HashMap to track character counts.  
    - [LeetCode](https://leetcode.com/problems/minimum-window-substring/)

13. **Palindrome Pairs**  
    - *Problem*: Find all pairs of words that form a palindrome when concatenated.  
    - *Concept*: Use a HashMap to store words and reverse indices for lookup.  
    - [LeetCode](https://leetcode.com/problems/palindrome-pairs/)

14. **Substring with Concatenation of All Words**  
    - *Problem*: Find all starting indices of substrings in `s` that are a concatenation of each word in a given list.  
    - *Concept*: Use a HashMap to track word counts and validate substrings.  
    - [LeetCode](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)

15. **Word Break II**  
    - *Problem*: Given a string and a word dictionary, find all possible sentences where the string can be segmented.  
    - *Concept*: Use a HashMap for memoization in dynamic programming.  
    - [LeetCode](https://leetcode.com/problems/word-break-ii/)

Great question! Let's break it down — **LRU**, **LFU**, and **TTL** are popular caching strategies used to manage limited memory resources effectively. Each one decides *which item to evict* when the cache is full, but they do it differently.

---
Perfect — here's how to implement **LRU Cache using a custom Doubly Linked List and HashMap** in Python. This is great for interviews and gives full control over the cache mechanism.

---

## ✅ Custom LRU Cache Using Doubly Linked List

### 🧠 Key Components:
- **HashMap** for O(1) key lookup: `key -> node`
- **Doubly Linked List** to track usage order (head: most recent, tail: least recent)

---

### 🧪 Full Python Code

```python
class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = self.next = None

class DoublyLinkedList:
    def __init__(self):
        self.head = Node(0, 0)  # dummy head
        self.tail = Node(0, 0)  # dummy tail
        self.head.next = self.tail
        self.tail.prev = self.head

    def add_to_front(self, node):
        # Always add right after head
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node

    def remove_node(self, node):
        # Unlink node from list
        prev_node = node.prev
        next_node = node.next
        prev_node.next = next_node
        next_node.prev = prev_node

    def move_to_front(self, node):
        self.remove_node(node)
        self.add_to_front(node)

    def remove_tail(self):
        # Removes and returns LRU node (just before dummy tail)
        if self.tail.prev == self.head:
            return None
        lru = self.tail.prev
        self.remove_node(lru)
        return lru

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = {}  # key -> Node
        self.dll = DoublyLinkedList()

    def get(self, key):
        if key not in self.cache:
            return -1
        node = self.cache[key]
        self.dll.move_to_front(node)
        return node.value

    def put(self, key, value):
        if key in self.cache:
            # Update and move to front
            node = self.cache[key]
            node.value = value
            self.dll.move_to_front(node)
        else:
            if len(self.cache) >= self.capacity:
                # Evict least recently used
                lru_node = self.dll.remove_tail()
                if lru_node:
                    del self.cache[lru_node.key]
            new_node = Node(key, value)
            self.cache[key] = new_node
            self.dll.add_to_front(new_node)

# ✅ Example usage:
lru = LRUCache(2)
lru.put(1, 'A')
lru.put(2, 'B')
print(lru.get(1))  # A (1 becomes MRU)
lru.put(3, 'C')    # Evicts key 2 (LRU)
print(lru.get(2))  # -1
print(lru.get(3))  # C
```

---

## 📉 **2. LFU (Least Frequently Used)**

### 📌 What is it?
Evicts the item that has been used **least frequently**.

### 🧠 How it works?
- Tracks access frequency per key
- Needs a structure to quickly retrieve the least frequently used

### 🧪 Python Implementation (Basic version):

```python
import heapq
from collections import defaultdict

class LFUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.data = {}
        self.freq = defaultdict(int)

    def get(self, key):
        if key in self.data:
            self.freq[key] += 1
            return self.data[key]
        return -1

    def put(self, key, value):
        if self.capacity == 0:
            return
        if key not in self.data and len(self.data) >= self.capacity:
            # Evict least frequently used
            min_freq = min(self.freq.values())
            for k in list(self.data):
                if self.freq[k] == min_freq:
                    del self.data[k]
                    del self.freq[k]
                    break
        self.data[key] = value
        self.freq[key] += 1

# Example
lfu = LFUCache(2)
lfu.put(1, 'X')
lfu.put(2, 'Y')
print(lfu.get(1))  # X
lfu.put(3, 'Z')    # Evicts key 2
print(lfu.get(2))  # -1
```

---

## ⏱ **3. TTL (Time To Live)**

### 📌 What is it?
Each key-value pair has an **expiration time**. If you try to access it after TTL, it's considered invalid.

### 🧠 How it works?
Stores the timestamp when the item should expire and checks that on each `get`.

### 🧪 Python Implementation:

```python
import time

class TTLCache:
    def __init__(self, ttl_seconds: int):
        self.ttl = ttl_seconds
        self.cache = {}

    def put(self, key, value):
        expire_time = time.time() + self.ttl
        self.cache[key] = (value, expire_time)

    def get(self, key):
        if key not in self.cache:
            return -1
        value, expire_time = self.cache[key]
        if time.time() > expire_time:
            del self.cache[key]
            return -1
        return value

# Example
ttl = TTLCache(5)
ttl.put(1, 'Hello')
print(ttl.get(1))  # 'Hello'
time.sleep(6)
print(ttl.get(1))  # -1 (Expired)
```

---

## 🧠 Summary

| Cache Type | Strategy | Use Case |
|------------|----------|----------|
| **LRU** | Evict least recently used | Web sessions, image thumbnails |
| **LFU** | Evict least frequently used | Recommendation systems |
| **TTL** | Expire based on time | API responses, tokens |

---
