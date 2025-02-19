- array permutation
- string permutations
- duplicate element in array permutations
- duplicate char in string permutations


each will have 2 methods one recursion and other is iteration
check take or not_take work or not ?
do in java and python both



#### 1.2 Permutations
1. **Permutations** [LC-46]
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
M2
```python
  def permute(self, nums: List[int]) -> List[List[int]]:
        result = []
        if(len(nums)==1):
            return [nums[:]]
        for i in range(len(nums)):
            t = nums.pop(0)
            perms = self.permute(nums)
            for perm in perms:
                perm.append(t)
            result.extend(perms)
            nums.append(t)
        return result
```

m2

```python 


def permute(nums, l, r):
    if l == r:
        print(nums[:])  # Print the current permutation
        return

    for i in range(l, r):
        nums[l], nums[i] = nums[i], nums[l]  # Swap
        permute(nums, l + 1, r)  # Recurse for next position
        nums[l], nums[i] = nums[i], nums[l]  # Backtrack (undo swap)

# Driver Code
nums = [1, 2, 3]
permute(nums, 0, len(nums))

```

19. **Find the K-th Permutation Sequence of First N Natural Numbers**
- Use backtracking to generate permutations, and keep track of the `K-th` permutation.
```python
   class Solution:
    def solve(self, nums, used, k, num, result):
        if len(num) == k:
            result.append(num)
            return
        
        for i in range(len(nums)):
            if not used[i]:  # Ensure each number is used only once
                used[i] = True
                self.solve(nums, used, k, num + str(nums[i]), result)
                used[i] = False  # Backtrack
                
    def kthPermutation(self, n: int, k: int) -> str:
        nums = list(range(1, n+1))
        result = []
        used = [False] * n  # Track used numbers
        
        self.solve(nums, used, n, "", result)
        
        return result[k-1] if k <= len(result) else ""
 
   
   ```





2. **Permutations II** [LC-47]
```java
public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
    if(tempList.size() == nums.length){
        list.add(new ArrayList<>(tempList));
    } else{
        for(int i = 0; i < nums.length; i++){
            if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])) continue;
            used[i] = true; 
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, used);
            used[i] = false; 
            tempList.remove(tempList.size() - 1);
        }
    }
}
```

method-2

```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    public void solve(int [] nums, List<Integer> list, int index, boolean[] used){

        if(nums.length == index){
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0 ; i < nums.length ; i++){
            if(used[i] ) continue;
            used[i] = true; 
            list.add(nums[i]);
            solve(nums, list, index+1, used);
            list.remove(list.size()-1);
            used[i] = false; 
        }

    }



    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> list = new ArrayList<>();
        boolean [] used = new boolean[nums.length];
        Arrays.sort(nums);
        solve(nums, list, 0, used);
        List<List<Integer>> result = ans.stream().distinct().collect(Collectors.toList());
        return result;
    }
}
```

m3
🔹 Approach 1: Using a Set to Avoid Duplicates

```python 

def permute_unique(nums, l, r, result):
    if l == r:
        result.add(tuple(nums))  # Store as tuple to avoid duplicate lists
        return

    for i in range(l, r):
        nums[l], nums[i] = nums[i], nums[l]  # Swap
        permute_unique(nums, l + 1, r, result)  # Recur
        nums[l], nums[i] = nums[i], nums[l]  # Backtrack

# Driver Code
nums = [1, 1, 2]
result = set()
permute_unique(nums, 0, len(nums), result)

for perm in result:
    print(list(perm))

```

m4
🔹 Approach 2: Sorting + Skipping Duplicates Efficiently

```python 

def permute_unique(nums, l, r, result):
    if l == r:
        result.append(nums[:])  # Store valid permutation
        return
    
    used = set()  # Track elements used at current recursion level
    for i in range(l, r):
        if nums[i] in used:  # Skip duplicate swaps
            continue
        used.add(nums[i])  
        
        nums[l], nums[i] = nums[i], nums[l]  # Swap
        permute_unique(nums, l + 1, r, result)  # Recur
        nums[l], nums[i] = nums[i], nums[l]  # Backtrack

# Driver Code
nums = [1, 1, 2]
nums.sort()  # Sorting ensures correct duplicate handling
result = []
permute_unique(nums, 0, len(nums), result)

for perm in result:
    print(perm)


```


14. **Print All Permutations of a String**
- Use backtracking to generate all permutations of the string by swapping characters and reverting swaps.


```python 

def permute(s, l, r):
    if l == r:  # Base case: Reached end of permutation
        print("".join(s))
        return
    
    for i in range(l, r):
        s[l], s[i] = s[i], s[l]  # Swap to fix one character
        permute(s, l + 1, r)  # Recur for the rest
        s[l], s[i] = s[i], s[l]  # Backtrack

# Driver Code
s = "ABC"
permute(list(s), 0, len(s))


```

## **7. Power Set & Permutations (Backtracking)**



- 🔹 **[267. Palindrome Permutation II](https://leetcode.com/problems/palindrome-permutation-ii/) (Medium)**  
  Find all palindromic permutations of a string.

17. **[567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)**
- Check if some substring is a permutation of `s2`.
- **Condition:** Substring must be a permutation of another string.
- **Difficulty:** Medium


### 15. **Next Permutation**
- **Hint**: Find the first decreasing element from the right, swap it with the next largest element, and reverse the subsequent part of the array.


