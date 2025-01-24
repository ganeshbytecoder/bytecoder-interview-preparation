**Problem**  find the first non-repeating character in a string 

Hint: 
* brute-force solution 
* hash-table solution 


** sort based on key/value and apply filter /map

https://leetcode.com/problems/isomorphic-strings/description/ 
https://leetcode.com/problems/bulls-and-cows/description/ 
https://leetcode.com/problems/custom-sort-string/description/ 

### **Easy Problems**
1. **Two Sum**  
   - *Problem*: Find two numbers in an array that add up to a given target.
   - *Concept*: Use a HashMap to store the difference between the target and each number.  
   - [LeetCode](https://leetcode.com/problems/two-sum/)

2. **Find the Difference**  
   - *Problem*: Given two strings, find the extra character in the second string.  
   - *Concept*: Use a HashMap to count characters in the strings.  
   - [LeetCode](https://leetcode.com/problems/find-the-difference/)

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

### **Medium Problems**
6. **Subarray Sum Equals K**  
   - *Problem*: Find the number of subarrays whose sum equals a given `k`.  
   - *Concept*: Use a HashMap to store cumulative sums and their frequencies
   - [LeetCode](https://leetcode.com/problems/subarray-sum-equals-k/)

7. **Longest Substring Without Repeating Characters**  
   - *Problem*: Find the length of the longest substring with unique characters.  
   - *Concept*:
     - Use a sliding window and HashMap to track indices of characters. 
     - use sliding window
   - [LeetCode](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

8. **Top K Frequent Elements**  
   - *Problem*: Find the `k` most frequent elements in an array.  
   - *Concept*: Use a HashMap to count frequencies, then use a heap for sorting.  
   - [LeetCode](https://leetcode.com/problems/top-k-frequent-elements/)

9. **Longest Consecutive Sequence**  
   - *Problem*: Find the length of the longest sequence of consecutive integers.  
   - *Concept*: Use a HashSet (or HashMap) to store numbers and check neighbors. 
   - use sorted set and priority queue check previous element
   - [LeetCode](https://leetcode.com/problems/longest-consecutive-sequence/)


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

