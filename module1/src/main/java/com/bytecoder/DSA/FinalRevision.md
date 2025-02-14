
## important leetcode problems

* https://leetcode.com/problems/product-of-array-except-self/description/
* https://leetcode.com/problems/search-a-2d-matrix/description/
* https://leetcode.com/problems/find-the-duplicate-number/description/
* https://leetcode.com/problems/search-a-2d-matrix-ii/
* https://leetcode.com/problems/single-number/description/
* https://leetcode.com/problems/linked-list-cycle-ii/description/
* https://leetcode.com/problems/missing-number/description/
* https://leetcode.com/problems/set-mismatch/description/
* https://leetcode.com/problems/valid-parentheses/
* https://leetcode.com/tag/matrix/
* https://leetcode.com/problems/evaluate-reverse-polish-notation/
* https://leetcode.com/problems/rotate-array/submissions/1335036018/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/remove-element/submissions/1335066159/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/submissions/1339378747/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/product-of-array-except-self/solutions/1342916/3-minute-read-mimicking-an-interview/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
* https://leetcode.com/problems/path-sum/description/
* https://leetcode.com/problems/merge-two-binary-trees/
* https://leetcode.com/problems/pascals-triangle-ii/description/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
* https://leetcode.com/problems/is-subsequence/submissions/1417429417/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
* https://leetcode.com/problems/increasing-order-search-tree/description/
* https://leetcode.com/problems/n-ary-tree-postorder-traversal/description/
* https://leetcode.com/problems/invert-binary-tree/description/
* https://leetcode.com/problems/maximum-depth-of-n-ary-tree/description/
* https://leetcode.com/problems/balanced-binary-tree/submissions/1327991003/
* https://leetcode.com/problems/sum-root-to-leaf-numbers/submissions/1339140254/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/populating-next-right-pointers-in-each-node/submissions/1328063250/
* https://leetcode.com/problems/sum-root-to-leaf-numbers/submissions/1328155077/
* https://leetcode.com/problems/unique-binary-search-trees-ii/?envType=problem-list-v2&envId=binary-tree
* https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/description/
* https://leetcode.com/problems/jump-game-ii/description/
* https://leetcode.com/problems/number-of-1-bits/submissions/1423436038/?envType=problem-list-v2&envId=divide-and-conquer&difficulty=EASY
* https://leetcode.com/problems/word-break/?envType=problem-list-v2&envId=dynamic-programming
* https://leetcode.com/problems/odd-even-linked-list/description/
* https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/description/
* https://leetcode.com/problems/check-whether-two-strings-are-almost-equivalent/
* https://leetcode.com/problems/count-vowel-substrings-of-a-string/description/
* https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/description/
* https://leetcode.com/problems/word-break/description/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/number-of-longest-increasing-subsequence/


## recursion or iteration problems

playlist: https://www.youtube.com/watch?v=_-2u4EPHD88&list=PLDzeHZWIZsTqBmRGnsCOGNDG5FY0G04Td

* check is given array sorted. return true/false
* return sum of given array
* using Linear search in find a value given unsorted array
* Reverse string or array
* Check palindrome in given string or array
* find the power of a on b or a^b
* binary search to find the given value
* find all possible sets of given set
*
* Bubble sort and Merge and quick sort
* https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/?envType=problem-list-v2&envId=recursion&difficulty=EASY
* https://leetcode.com/problems/predict-the-winner/submissions/1423638042/?envType=problem-list-v2&envId=recursion&difficulty=MEDIUM




## educative coding patterns

### Two pointers
- reverse string/array
- palindrome check 
- 3 sum 
- sum in sorted array


### Fast and slow pointers
- find middle element in Linked list
- 

### Sliding window 

### Merge intervals

### In place Manipulation of Linked list

- reverse linked list 

### Two pointers



## Permutation of array (for all distinct digits):

```python
class Solution:
    ans = []
    def solve(self, nums, result):
        if(len(result) == len(nums)):
            self.ans.append(list(result))
            return
        
        for i in range(0, len(nums)):
            if(nums[i] not in result):
                result.append(nums[i])
                self.solve(nums,result)
                result.remove(nums[i])
        

    def permute(self, nums: List[int]) -> List[List[int]]:
        self.ans=[]
        self.solve(nums, [])
        
        print(self.ans)
        return self.ans
```


## Permutation of array (for duplicate digits): https://leetcode.com/problems/permutations-ii/ 

Two Sum
Best Time to Buy and Sell Stock
Contains Duplicate
Product of Array Except Self
Maximum Subarray
Maximum Product Subarray
Find Minimum in Rotated Sorted Array
Search in Rotated Sorted Array
3Sum
Container With Most Water
Sum of Two Integers
Number of 1 Bits
Counting Bits
Missing Number
Reverse Bits
Climbing Stairs
Coin Change
Longest Increasing Subsequence
Longest Common Subsequence
Word Break Problem
Combination Sum
House Robber
House Robber II
Decode Ways
Unique Paths
Jump Game
Clone Graph
Course Schedule
Pacific Atlantic Water Flow
Number of Islands
Longest Consecutive Sequence
Alien Dictionary (Leetcode Premium)
Graph Valid Tree (Leetcode Premium)
Number of Connected Components in an Undirected Graph (Leetcode Premium)
Insert Interval
Merge Intervals
Non-overlapping Intervals
Meeting Rooms (Leetcode Premium)
Meeting Rooms II (Leetcode Premium)
Reverse a Linked List
Detect Cycle in a Linked List
Merge Two Sorted Lists
Merge K Sorted Lists
Remove Nth Node From End Of List
Reorder List
Set Matrix Zeroes
Spiral Matrix
Rotate Image
Word Search
Longest Substring Without Repeating Characters
Longest Repeating Character Replacement
Minimum Window Substring
Valid Anagram
Group Anagrams
Valid Parentheses
Valid Palindrome
Longest Palindromic Substring
Palindromic Substrings
Encode and Decode Strings (Leetcode Premium)
Maximum Depth of Binary Tree
Same Tree
Invert/Flip Binary Tree
Binary Tree Maximum Path Sum
Binary Tree Level Order Traversal
Serialize and Deserialize Binary Tree
Subtree of Another Tree
Construct Binary Tree from Preorder and Inorder Traversal
Validate Binary Search Tree
Kth Smallest Element in a BST
Lowest Common Ancestor of BST
Implement Trie (Prefix Tree)
Add and Search Word
Word Search II
Merge K Sorted Lists
Top K Frequent Elements
Find Median from Data Stream
