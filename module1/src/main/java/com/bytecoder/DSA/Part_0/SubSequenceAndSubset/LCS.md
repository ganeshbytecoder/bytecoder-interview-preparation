
1. **[Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)** - Classic DP with memoization.
```python
class Solution:

    def dfs(self, text1, text2, i, j, dp):
        if(i>=len(text1) or j >= len(text2)):
            return 0
        if(dp[i][j] != -1):
            return dp[i][j] 
        if(text1[i]==text2[j]):
            dp[i][j] =  1 + self.dfs(text1, text2, i+1, j+1, dp)
        else:
            dp[i][j]  = max(self.dfs(text1, text2, i, j+1, dp), self.dfs(text1, text2, i+1, j, dp))

        return dp[i][j] 


    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        dp = [[-1] * len(text2) for _ in range(len(text1))]  # Corrected initialization
        return self.dfs(text1, text2, 0, 0, dp)


    def tabulation(self, text1, text2, i, j, dp):
 
        for i in range(len(text1)-1, -1, -1):
            for j in range(len(text2)-1, -1, -1): 
                if(text1[i]==text2[j]):
                    dp[i][j] = 1 + dp[i+1][j+1]
                else:
                    dp[i][j] = max(dp[i][j+1],dp[i+1][j])

        m = len(text1)-1
        n = len(text2)-1
        common = ""
        while(m>0 and n>0):
            if(text1[m]==text2[n]):
                common =text1[m] + common
                m-=1
                n-=1
            elif(dp[m-1][n] > dp[m][n-1]):
                m -=1
            else:
                n -=1
        print(common)


        # for i in dp:
        #     print(i)
        return dp[0][0]
    
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        dp = [[0]*(len(text2)+1) for i in range(len(text1)+1)]

        return self.tabulation(text1, text2, 0, 0, dp)


 

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


## **Longest Common Subsequence (LCS) Variants**
2. **[Edit Distance](https://leetcode.com/problems/edit-distance/)** - DP table to compute min edit operations.
3. **[Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/)** - Count distinct subsequences using DP.
4. **[Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)** - DP to compute minimum delete cost for equal strings.
- 🔹 **[1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) (Medium)**  
  Find the length of the longest subsequence common to two strings.
- 🔹 **[1035. Uncrossed Lines](https://leetcode.com/problems/uncrossed-lines/) (Medium)**  
  Similar to LCS but applied to arrays.
- https://leetcode.com/problems/maximize-number-of-subsequences-in-a-string/description/


