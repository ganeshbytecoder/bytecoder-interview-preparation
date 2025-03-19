
## **📌 4. Palindromic Substrings → Expand Around Center**
Use **Expand Around Center** when:
- You need to **count** or **find the longest** palindromic substring.

### **Examples & Approach**
| Problem Type | Approach |
|-------------|----------|
| **Find the longest palindromic substring** | Expand Around Center (`O(n^2)`) |
| **Count palindromic substrings in a string** | Expand Around Center (`O(n^2)`) |
| **Find the longest palindromic subsequence** | Dynamic Programming (`O(n^2)`) |
### **📌 Palindrome & Reverse Conditions**
11. **[5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)**
- Find the longest contiguous palindromic substring.
- **Condition:** Substring must be a palindrome.
- **Difficulty:** Medium

12. **[647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)**
- Count all substrings in a string that are palindromic.
- **Condition:** Count substrings meeting the palindrome condition.
- **Difficulty:** Medium

* https://www.geeksforgeeks.org/longest-substring-whose-characters-can-be-rearranged-to-form-a-palindrome/?ref=asr29

* https://www.geeksforgeeks.org/longest-substring-that-can-be-made-a-palindrome-by-swapping-of-characters/?ref=asr27


    
### **Example 4: Longest Palindromic Substring**
✅ **Problem**: Find the longest palindromic substring.
```python
def longest_palindrome(s: str) -> str:
    def expand(l, r):
        while l >= 0 and r < len(s) and s[l] == s[r]:
            l -= 1
            r += 1
        return s[l + 1:r]
    
    res = ""
    for i in range(len(s)):
        odd = expand(i, i)
        even = expand(i, i + 1)
        res = max(res, odd, even, key=len)
    
    return res
```
📌 **Key Idea**: Expand around each center (odd & even length).

---
## Palindromic Subsequence
1. **[Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii/)** - DP with a cut count for partitions.

