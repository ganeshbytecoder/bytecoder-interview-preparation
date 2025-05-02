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
    class Solution:
    def permuteUnique(self, nums):
        self.ans = []
        nums.sort()  # Sort to handle duplicates
        used = [False] * len(nums)  # Track usage of each element
        self.solve(nums, [], used)
        return self.ans

    def solve(self, nums, result, used):
        if len(result) == len(nums):
            self.ans.append(list(result))
            return
        
        for i in range(len(nums)):
            # Skip used elements
            if used[i]:
                continue
            
            # Skip duplicates: If nums[i] == nums[i-1] and nums[i-1] wasn't used in this recursion level
            if i > 0 and nums[i] == nums[i - 1] and not used[i - 1]:
                continue

            # Mark as used
            used[i] = True
            result.append(nums[i])

            # Recurse
            self.solve(nums, result, used)

            # Backtrack
            used[i] = False
            result.pop()

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
//            We skip the second occurrence (nums[i]) unless the first one was already used.

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


17. **[567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)**
- Check if some substring is a permutation of `s2`.
- **Condition:** Substring must be a permutation of another string.
- **Difficulty:** Medium


Absolutely! Let’s go through each **use case** in that table and explain **why or why not** you should use **permutations** in that context.

---

## ✅ Use Case 1: "You want every possible order of elements"
**Use Permutations? → ✅ Yes**

### 🔹 Why:
This is the **core definition** of permutations.

If you have `[A, B, C]` and you want **all possible ways to arrange them**, you're asking for:
```
[A, B, C], [A, C, B], [B, A, C], [B, C, A], [C, A, B], [C, B, A]
```
That’s **exactly what permutations are** — they generate **all orderings** of elements.

---

## ✅ Use Case 2: "Solving traveling salesman, routing"
**Use Permutations? → ✅ Yes**

### 🔹 Why:
In problems like the **Traveling Salesman Problem (TSP)**, you're trying to find:
- The **shortest path** that visits **every city once**
- **Order** in which cities are visited **matters** for total distance

So you want to generate **every possible route**:
- A ➝ B ➝ C
- A ➝ C ➝ B
- ...

These are **permutations** of cities.

✅ Use permutations here to test all route orders (in brute-force solutions).

---

## ✅ Use Case 3: "Need to reorder items in every way"
**Use Permutation? → ✅ Yes**

### 🔹 Why:
If your goal is to **rearrange** a group of items in **all possible orders**, this is a textbook permutation use case.

Example: testing **shuffles** of cards, **reordering tasks**, or generating **anagrams** of a word.

Permutations will give you:
- All 6 ways to arrange 3 items
- All `n!` arrangements for `n` items

---

## ❌ Use Case 4: "Finding combinations of items where order doesn't matter"
**Use Permutation? → ❌ No**  
👉 Use **combinations**

### 🔹 Why:
Combinations are about **choosing items**, not **arranging them**.

Example:
From `[A, B, C]`, combinations of 2 are:
```
[A, B], [A, C], [B, C]
```

But permutations of 2 are:
```
[A, B], [B, A], [A, C], [C, A], [B, C], [C, B]
```

If you **don’t care whether it’s [A,B] or [B,A]**, then use combinations, not permutations — permutations would give you **redundant results**.

---

## ❌ Use Case 5: "Skipping elements or checking subsets"
**Use Permutation? → ❌ No**  
👉 Use **subsets** or **subsequences**

### 🔹 Why:
If you want to **pick some elements** (not all), you're working with **subsets** or **subsequences**.

- Subsets: any group of elements, **ignoring order**
- Subsequences: keep order, but you can **skip elements**

Permutation, by contrast:
- Uses **all elements**
- In **all possible orders**

So if your goal is to:
- Skip some elements (e.g., `[1,3]` from `[1,2,3]`)
- Or just test for presence (e.g., "Does this subset add to X?")

Then permutations are **overkill** and **inefficient**.

---

## ✅ Summary (in plain terms)

| Scenario | Do you care about order? | Do you use all elements? | Use Permutations? |
|----------|---------------------------|--------------------------|--------------------|
| Want every possible order | ✅ Yes | ✅ Yes or fixed k | ✅ Yes |
| Just want to choose some items (order irrelevant) | ❌ No | ❌ No | ❌ No (use combinations) |
| Want to explore some items while keeping order | ✅ Yes | ❌ No | ❌ No (use subsequences) |
| Want to test groupings (no order, can skip) | ❌ No | ❌ No | ❌ No (use subsets) |

---




