Basics:
* https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/?envType=problem-list-v2&envId=recursion&difficulty=EASY
  https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
  https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/

## 🔹 **Substring Problems**
(A contiguous sequence of characters in a string)
10. **[424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)**
- Find the longest contiguous substring where you can replace at most `k` characters.
- **Condition:** Subarray length maximization with `≤ k` changes.
- **Difficulty:** Medium


### **📌 Palindrome & Reverse Conditions**
11. **[5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)**
- Find the longest contiguous palindromic substring.
- **Condition:** Substring must be a palindrome.
- **Difficulty:** Medium

12. **[647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)**
- Count all substrings in a string that are palindromic.
- **Condition:** Count substrings meeting the palindrome condition.
- **Difficulty:** Medium

---

### **📌 K Distinct or Unique Character Conditions**
13. **[340. Longest Substring with At Most K Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/)**
- Find the longest contiguous substring with at most `k` distinct characters.
- **Condition:** Substring must contain ≤ `k` unique characters.
- **Difficulty:** Medium

14. **[159. Longest Substring with At Most Two Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/)**
- Same as above, but with **exactly two** distinct characters.
- **Condition:** Substring must contain exactly `2` unique characters.
- **Difficulty:** Medium

15. **[3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)**
- Find the longest contiguous substring where no character repeats.
- **Condition:** Substring must have all unique characters.
- **Difficulty:** Medium

---

### **📌 Sliding Window & Frequency Conditions**
16. **[76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)**
- Find the smallest substring containing all characters of `t`.
- **Condition:** Substring must contain all of `t`'s characters at least once.
- **Difficulty:** Hard



18. **[30. Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)**
- Find all starting indices where a substring is a concatenation of all words in a given list.
- **Condition:** Substring must be a concatenation of a word list.
- **Difficulty:** Hard

---

### **🔥 Bonus: Hard Problems**
19. **[727. Minimum Window Subsequence](https://leetcode.com/problems/minimum-window-subsequence/)**
- Find the minimum contiguous substring in `s` where `t` appears as a subsequence.
- **Condition:** Substring must contain `t` as a subsequence.
- **Difficulty:** Hard

20. **[1062. Longest Repeating Substring](https://leetcode.com/problems/longest-repeating-substring/)**
- Find the longest contiguous substring that appears at least twice.
- **Condition:** Substring must be **repeating** at least twice.
- **Difficulty:** Hard

---



https://leetcode.com/problems/decode-string/description/

https://www.geeksforgeeks.org/roman-number-to-integer/

https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/

https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/

https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/?ref=asr30

https://www.geeksforgeeks.org/longest-substring-whose-characters-can-be-rearranged-to-form-a-palindrome/?ref=asr29

https://www.geeksforgeeks.org/print-longest-substring-without-repeating-characters/?ref=asr28

https://www.geeksforgeeks.org/longest-substring-that-can-be-made-a-palindrome-by-swapping-of-characters/?ref=asr27

https://www.geeksforgeeks.org/longest-substring-with-no-pair-of-adjacent-characters-are-adjacent-english-alphabets/?ref=asr26

https://www.geeksforgeeks.org/longest-substring-where-all-the-characters-appear-at-least-k-times-set-3/?ref=asr25

https://www.geeksforgeeks.org/longest-prefix-also-suffix/

https://www.geeksforgeeks.org/problems/length-of-the-longest-substring3036/1

```python
    
    def longestUniqueSubstring(self, s):
        charSet = set()  # HashSet to store unique characters
        i = 0
        ans = 0
        
        for j in range(len(s)):
            while s[j] in charSet:  # If duplicate found, remove leftmost character
                charSet.remove(s[i])
                i += 1
            charSet.add(s[j])  # Add current character
            ans = max(ans, j - i + 1)  # Update max length
        
        return ans
```


https://leetcode.com/problems/longest-palindromic-substring/description/

https://leetcode.com/problems/longest-palindromic-subsequence/



