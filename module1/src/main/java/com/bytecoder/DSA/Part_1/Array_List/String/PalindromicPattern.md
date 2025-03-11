
## **📌 4. Palindromic Substrings → Expand Around Center**
Use **Expand Around Center** when:
- You need to **count** or **find the longest** palindromic substring.

### **Examples & Approach**
| Problem Type | Approach |
|-------------|----------|
| **Find the longest palindromic substring** | Expand Around Center (`O(n^2)`) |
| **Count palindromic substrings in a string** | Expand Around Center (`O(n^2)`) |
| **Find the longest palindromic subsequence** | Dynamic Programming (`O(n^2)`) |

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
