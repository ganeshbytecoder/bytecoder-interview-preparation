https://takeuforward.org/strivers-a2z-dsa-course/strivers-a2z-dsa-course-sheet-2 



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
