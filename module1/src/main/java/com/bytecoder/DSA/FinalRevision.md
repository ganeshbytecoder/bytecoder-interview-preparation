```java
public static void main(String[] args) {
  int[] nums = {1,2,3};
  List<Integer> list3 = new ArrayList<>();
  for(int a : nums){
    list3.add(a);
  }

  list3 = Arrays.stream(nums)
          .boxed()
          .toList();
  list3.forEach(System.out::println);


  int[][] nums2 = {{1,2,3},{2,3,4}};
  List<List<Integer>> list4 = Arrays.stream(nums2).map(temp->Arrays.stream(temp).boxed().toList()).toList();
  list4.forEach(System.out::println);


  String[] array = {"new", "String", "array"};
  List<String> stringsList = new ArrayList<>(Arrays.asList(array));
  List<String> stringsList2 = Arrays.stream(array).toList();

  List<String> strings = Arrays.asList("A", "B", "C");
  strings = List.of("A", "B", "C");


  Queue<String> queue = new LinkedList<>();
  stringsList.forEach(s -> queue.add(s));
  queue.addAll(stringsList);



  char[] chars = "list".toCharArray();
  List<Character> list2 = new ArrayList<>();
  for(Character c : chars ){
    list2.add(c);
  }

//        Arrays.stream(char[]) does not exist; it works only for int[], double[], and long[].
//        chars = "list".toCharArray();
//        List<Character> list3 = Arrays.stream(chars)
//                .boxed()
//                .toList();
//        list3.forEach(System.out::println);

  List.of(1, 2, 34, 4).stream().forEach(System.out::println);
  Arrays.asList(1, 2, 34, 2).stream().forEach(System.out::println);

}
```



```python

[//]: # (for subarray)
public int maximumLength(int[] nums) {
    if (nums.length <= 2) {
        return nums.length;
    }

    int maxLen = 1;  // At least one element is always a valid subsequence
    int currentLen = 1;  // Start with a single-element sequence

    for (int i = 1; i < nums.length; i++) {
        if ((nums[i] % 2 == nums[i - 1] % 2)) {  // Same parity as previous number
            currentLen++;
        } else {  // Reset the sequence when parity changes
            currentLen = 1;
        }
        maxLen = Math.max(maxLen, currentLen);
    }

    return maxLen;
}



```
https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/description/
```java
public class Solution {

  int solve(int[] nums, int curr, int prev, int remainder, int[][][] dp){
    if(curr==nums.length){
      return 0;
    }
    if( dp[prev+1][curr][remainder] != -1){
      return  dp[prev+1][curr][remainder];
    }

    int take = solve(nums, curr+1, prev, remainder, dp);
    int notTake = 0;
    if(prev == -1 || (nums[prev]+ nums[curr]) %2 ==remainder){
      notTake = 1 + solve(nums, curr+1, curr,remainder, dp);
    }
    dp[prev+1][curr][remainder] = Math.max(take, notTake);
    return  dp[prev+1][curr][remainder];
  }



  public int maximumLength(int[] nums) {
    int[][][] dp1 = new int[nums.length][nums.length][2];
    for(int i =0; i<nums.length ; i++){
      Arrays.fill(dp1[i], new int[]{-1, -1});
    }
    int a = solve(nums, 0, -1, 0, dp1);

    int b = solve(nums, 0, -1, 1, dp1);

    return Math.max(a, b);


  }
}

```

## **Longest Common Subsequence (LCS) Variants**
1. **[Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)** - Classic DP with memoization.
```python
def longestCommonSubsequence(text1: str, text2: str) -> str:
    m, n = len(text1), len(text2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    # Step 1: Fill DP Table
    for i in range(m - 1, -1, -1):
        for j in range(n - 1, -1, -1): 
            if text1[i] == text2[j]:
                dp[i][j] = 1 + dp[i + 1][j + 1]
            else:
                dp[i][j] = max(dp[i][j + 1], dp[i + 1][j])

    # Step 2: Construct LCS
    i, j = 0, 0  # Start from the beginning
    lcs = []

    while i < m and j < n:
        if text1[i] == text2[j]:  # Characters match, add to LCS
            lcs.append(text1[i])
            i += 1
            j += 1
        elif dp[i + 1][j] > dp[i][j + 1]:  # Move down
            i += 1
        else:  # Move right
            j += 1

    return "".join(lcs)  # Return the LCS string


# Example Usage
text1 = "abcde"
text2 = "ace"
print(longestCommonSubsequence(text1, text2))  # Output: "ace"

 

```

M2

```python

def longestCommonSubsequence(text1: str, text2: str) -> str:
    m, n = len(text1), len(text2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    # Fill DP table
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if text1[i - 1] == text2[j - 1]:
                dp[i][j] = 1 + dp[i - 1][j - 1]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    # Reconstruct LCS
    i, j = m, n
    lcs = []
    
    while i > 0 and j > 0:
        if text1[i - 1] == text2[j - 1]:  # Character is part of LCS
            lcs.append(text1[i - 1])
            i -= 1
            j -= 1
        elif dp[i - 1][j] > dp[i][j - 1]:  # Move up
            i -= 1
        else:  # Move left
            j -= 1

    return "".join(reversed(lcs))  # Reverse the list to get the correct order

# Example Usage
text1 = "abcde"
text2 = "ace"
print(longestCommonSubsequence(text1, text2))  # Output: "ace"

```

2. https://leetcode.com/problems/shortest-common-supersequence/?envType=daily-question&envId=2025-02-28

```python 

class Solution:
    def dfs(self, s1, s2, i, j, memo):
        key = (i, j)

        if i == len(s1):  # If s1 is exhausted, return remaining part of s2
            return s2[j:]
        if j == len(s2):  # If s2 is exhausted, return remaining part of s1
            return s1[i:]

        if key in memo:
            return memo[key]

        if s1[i] == s2[j]:  # If characters match, take one and move both indices
            memo[key] = s1[i] + self.dfs(s1, s2, i + 1, j + 1, memo)
        else:
            t1 = s1[i] + self.dfs(s1, s2, i + 1, j, memo)  # Include s1[i]
            t2 = s2[j] + self.dfs(s1, s2, i, j + 1, memo)  # Include s2[j]

            # Take the shorter string
            memo[key] = t1 if len(t1) < len(t2) else t2

        return memo[key]

    def shortestCommonSupersequence(self, str1: str, str2: str) -> str:
        memo = {}
        return self.dfs(str1, str2, 0, 0, memo)


# Example Usage
sol = Solution()
print(sol.shortestCommonSupersequence("abc", "ac"))  # Output: "abc"


```

M2
```python 

def shortestCommonSupersequence(str1: str, str2: str) -> str:
    m, n = len(str1), len(str2)
    
    # Step 1: Compute LCS using DP
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if str1[i - 1] == str2[j - 1]:
                dp[i][j] = 1 + dp[i - 1][j - 1]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    # Step 2: Reconstruct SCS from LCS DP table
    i, j = m, n
    scs = []

    while i > 0 and j > 0:
        if str1[i - 1] == str2[j - 1]:  # Match found in LCS
            scs.append(str1[i - 1])
            i -= 1
            j -= 1
        elif dp[i - 1][j] > dp[i][j - 1]:  # Move up
            scs.append(str1[i - 1])
            i -= 1
        else:  # Move left
            scs.append(str2[j - 1])
            j -= 1

    # Add remaining characters (if any)
    while i > 0:
        scs.append(str1[i - 1])
        i -= 1
    while j > 0:
        scs.append(str2[j - 1])
        j -= 1

    return "".join(reversed(scs))

# Example Usage
print(shortestCommonSupersequence("abc", "ac"))  # Output: "abc"
print(shortestCommonSupersequence("abac", "cab"))  # Output: "cabac"

```


3. **[Edit Distance](https://leetcode.com/problems/edit-distance/)** - DP table to compute min edit operations.
3. **[Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/)** - Count distinct subsequences using DP.
4. **[Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)** - DP to compute minimum delete cost for equal strings.


## **Longest Increasing Subsequence (LIS) Variants**
1. **[Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)** - Use DP with binary search (O(n log n)) or DP (O(n^2)).
2. **[Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/)** - DP with sorting, track the longest subset where `nums[j]` divides `nums[i]`.
3. **[Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/)** - Sort by width, apply LIS on height.
4. **[Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/)** - Sort pairs and apply LIS/Greedy.
5. **[Number of Longest Increasing Subsequences](https://leetcode.com/problems/number-of-longest-increasing-subsequence/)** - Use DP to count valid LIS sequences.
6. **[Delete and Earn](https://leetcode.com/problems/delete-and-earn/)** - Convert to a house-robber problem.
7. **[Longest String Chain](https://leetcode.com/problems/longest-string-chain/)** - Sort and use DP with a hashmap for longest chains.

---

## Palindromic Subsequence
1. **[Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii/)** - DP with a cut count for partitions.
2. **[Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)** - DP or center expansion.


## **6. Longest Increasing Subsequence [LC-300]**
### **Find longest increasing subsequence (LIS)**
- **Approach 1**: DP with `O(n^2)` time.

```java
public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int maxLen = 1;
    
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxLen = Math.max(maxLen, dp[i]);
    }
    return maxLen;
}
```


##### 1. **[Longest Increasing Subsequence II](https://leetcode.com/problems/longest-increasing-subsequence-ii/description/)**
- Problem: Find the length of the longest increasing subsequence within a limited difference range.
##### 2. **[Longest Ideal Subsequence](https://leetcode.com/problems/longest-ideal-subsequence/description/)**
- Problem: Find the longest ideal subsequence with a character difference limit.
##### 3. **[Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/)**
- Problem: Find the number of longest increasing subsequences.

##### 4. **[Find the Maximum Length of Valid Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/description/)**
- Problem: Find the maximum length of a valid subsequence from the input.

##### 6. **[Find the Maximum Length of a Good Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-i/description/)**
- Problem: Determine the maximum length of a "good" subsequence based on given conditions.


##### 8. **[Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/description/)**
- Problem: Find the longest chain of pairs such that each pair's second value is less than the next pair's first value.

##### 9. **[Increasing Triplet Subsequence](https://leetcode.com/problems/increasing-triplet-subsequence/description/)**
- Problem: Check if there exists a triplet (i, j, k) such that `nums[i] < nums[j] < nums[k]`.
```java
// 1. Recursion - Time: O(2^n), Space: O(n)
public int lengthOfLIS(int[] nums) {
    return lengthOfLISRecursive(nums, Integer.MIN_VALUE, 0);
}

private int lengthOfLISRecursive(int[] nums, int prev, int curr) {
    if (curr == nums.length) return 0;
    
    int include = 0;
    if (nums[curr] > prev) {
        include = 1 + lengthOfLISRecursive(nums, nums[curr], curr + 1);
    }
    int exclude = lengthOfLISRecursive(nums, prev, curr + 1);
    
    return Math.max(include, exclude);
}

// 2. Memoization - Time: O(n²), Space: O(n²)
public int lengthOfLIS(int[] nums) {
    int[][] memo = new int[nums.length + 1][nums.length];
    for (int[] row : memo) Arrays.fill(row, -1);
    return lengthOfLISMemo(nums, -1, 0, memo);
}

private int lengthOfLISMemo(int[] nums, int prevIndex, int curr, int[][] memo) {
    if (curr == nums.length) return 0;
    if (prevIndex != -1 && memo[prevIndex][curr] != -1) 
        return memo[prevIndex][curr];
    
    int include = 0;
    if (prevIndex == -1 || nums[curr] > nums[prevIndex]) {
        include = 1 + lengthOfLISMemo(nums, curr, curr + 1, memo);
    }
    int exclude = lengthOfLISMemo(nums, prevIndex, curr + 1, memo);
    
    if (prevIndex != -1) memo[prevIndex][curr] = Math.max(include, exclude);
    return Math.max(include, exclude);
}

// 3. Tabulation - Time: O(n²), Space: O(n)
public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int maxLen = 1;
    
    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxLen = Math.max(maxLen, dp[i]);
    }
    return maxLen;
}
```


## **2. Subsequences (String/Array Based)**
- 🔹 **[392. Is Subsequence](https://leetcode.com/problems/is-subsequence/) (Easy)**  
  Check if a string is a subsequence of another.

- 🔹 **[1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) (Medium)**  
  Find the length of the longest subsequence common to two strings.

- 🔹 **[1035. Uncrossed Lines](https://leetcode.com/problems/uncrossed-lines/) (Medium)**  
  Similar to LCS but applied to arrays.

---

## **4. Subarrays & Subsequence Problems**
- 🔹 **[300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) (Medium)**  
  Find the length of the longest increasing subsequence in an array.

- 🔹 **[673. Number of Longest Increasing Subsequences](https://leetcode.com/problems/number-of-longest-increasing-subsequences/) (Medium)**  
  Count how many longest increasing subsequences exist.

- 🔹 **[718. Maximum Length of Repeated Subarray](https://leetcode.com/problems/maximum-length-of-repeated-subarray/) (Medium)**  
  Find the longest repeated subarray in two arrays.

Pacific Atlantic Water Flow: https://leetcode.com/problems/pacific-atlantic-water-flow/description/
House Robber II: https://leetcode.com/problems/house-robber-ii/description/
Container With Most Water : https://leetcode.com/problems/container-with-most-water/description/
Alien Dictionary (Leetcode Premium): https://leetcode.com/problems/alien-dictionary/description/
Graph Valid Tree (Leetcode Premium): https://leetcode.com/problems/graph-valid-tree/description/
Insert Interval: https://leetcode.com/problems/insert-interval/description/
Merge Intervals: https://leetcode.com/problems/merge-intervals/description/
Non-overlapping Intervals: https://leetcode.com/problems/non-overlapping-intervals/description/
Meeting Rooms (Leetcode Premium): https://leetcode.com/problems/meeting-rooms/description/
Meeting Rooms II (Leetcode Premium):https://leetcode.com/problems/meeting-rooms-ii/description/
Merge K Sorted Lists: https://leetcode.com/problems/merge-k-sorted-lists/description/
Reorder List: https://leetcode.com/problems/reorder-list/description/
Longest Repeating Character Replacement: https://leetcode.com/problems/longest-repeating-character-replacement/description/
Minimum Window Substring: https://leetcode.com/problems/minimum-window-substring/description/
Palindromic Substrings: https://leetcode.com/problems/palindromic-substrings/
Encode and Decode Strings (Leetcode Premium): https://leetcode.com/problems/encode-and-decode-strings/description/
Binary Tree Maximum Path Sum: https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
Serialize and Deserialize Binary Tree: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
Word Search II: https://leetcode.com/problems/word-search-ii/description/
Find Median from Data Stream: https://leetcode.com/problems/find-median-from-data-stream/description/















https://leetcode.com/problems/product-of-array-except-self/
https://leetcode.com/problems/maximum-subarray/description/
get above questions array
https://leetcode.com/problems/maximum-product-subarray/description/
Find Minimum in Rotated Sorted Array: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
Search in Rotated Sorted Array: https://leetcode.com/problems/search-in-rotated-sorted-array/description/
3Sum : https://leetcode.com/problems/3sum/description/
Counting Bits: https://leetcode.com/problems/counting-bits/description/
Reverse Bits: https://leetcode.com/problems/reverse-bits/description/
https://leetcode.com/problems/combination-sum-iv/description/
Word Break Problem: https://leetcode.com/problems/word-break/ 
Combination Sum: https://leetcode.com/problems/combination-sum/
House Robber: https://leetcode.com/problems/house-robber/description/
Decode Ways: https://leetcode.com/problems/decode-ways/description/
Unique Paths: https://leetcode.com/problems/unique-paths/description/
Jump Game: https://leetcode.com/problems/jump-game/description/
Clone Graph: https://leetcode.com/problems/clone-graph/
Course Schedule: https://leetcode.com/problems/course-schedule/description/
Number of Islands: https://leetcode.com/problems/number-of-islands/description/
Longest Consecutive Sequence: https://leetcode.com/problems/longest-consecutive-sequence/



Number of Connected Components in an Undirected Graph (Leetcode Premium): https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
Reverse a Linked List: https://leetcode.com/problems/reverse-linked-list/description/
Detect Cycle in a Linked List: https://leetcode.com/problems/linked-list-cycle/description/
Merge Two Sorted Lists: https://leetcode.com/problems/merge-two-sorted-lists/description/
Remove Nth Node From End Of List: https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
Spiral Matrix: https://leetcode.com/problems/spiral-matrix/description/
Rotate Image: https://leetcode.com/problems/rotate-image/description/
Word Search: https://leetcode.com/problems/word-search/description/


Longest Substring Without Repeating Characters: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
Group Anagrams: https://leetcode.com/problems/group-anagrams/description/

Valid Palindrome: https://leetcode.com/problems/valid-palindrome/description/
Longest Palindromic Substring: https://leetcode.com/problems/longest-palindromic-substring/description/

Maximum Depth of Binary Tree: https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
Same Tree: https://leetcode.com/problems/same-tree/description/
Invert/Flip Binary Tree: https://leetcode.com/problems/invert-binary-tree/description/
Subtree of Another Tree: https://leetcode.com/problems/subtree-of-another-tree/description/
Construct Binary Tree from Preorder and Inorder Traversal: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
Validate Binary Search Tree: https://leetcode.com/problems/validate-binary-search-tree/description/
Lowest Common Ancestor of BST: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/



Implement Trie (Prefix Tree): https://leetcode.com/problems/implement-trie-prefix-tree/description/
Add and Search Word: https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
Merge K Sorted Lists: https://leetcode.com/problems/merge-k-sorted-lists/description/
Top K Frequent Elements



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
* Bubble sort and Merge and quick sort
* https://leetcode.com/problems/predict-the-winner/submissions/1423638042/?envType=problem-list-v2&envId=recursion&difficulty=MEDIUM
  https://leetcode.com/problems/max-consecutive-ones-iii/description/
* https://leetcode.com/problems/subarray-sum-equals-k/description/
* https://leetcode.com/problems/count-number-of-nice-subarrays/description/
* https://leetcode.com/problems/subarray-sums-divisible-by-k/description/
* https://leetcode.com/problems/maximum-product-subarray/description/
* https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/description/?envType=daily-question&envId=2025-02-27
* https://leetcode.com/problems/edit-distance/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/1556849379/?envType=study-plan-v2&envId=top-interview-150
* https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/submissions/1557627723/
* https://leetcode.com/problems/set-mismatch/submissions/1557640493/
* https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/description/
* https://leetcode.com/problems/longest-palindromic-subsequence/solutions/

