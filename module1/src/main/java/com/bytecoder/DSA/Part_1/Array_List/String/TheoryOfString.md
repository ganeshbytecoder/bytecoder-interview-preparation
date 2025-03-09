
https://leetcode.com/problems/is-subsequence/description/


**Problem**: Check if a string is substring of another
```
Input: S1 = “for”, S2 = “geeksforgeeks”
Output: 5
Explanation: String “for” is present as a substring of s2.


Input: S1 = “practice”, S2 = “geeksforgeeks”
Output: -1.
Explanation: There is no occurrence of “practice” in “geeksforgeeks”
```


[//]: # (get 0.012012012012012012)
```python

def find_smallest_repeating_substring(s):
    n = len(s)
    for k in range(1, n + 1):
        if n % k == 0:  # Check if k is a divisor of n
            substring = s[:k]
            if substring * (n // k) == s:  # Check if it forms the original string
                return substring
    return s  # If no repeating pattern found, return the original string

# Test cases
print(find_smallest_repeating_substring("ABCABCABCABC"))  # Output: "ABC"
print(find_smallest_repeating_substring("ABCDABCDABCD"))  # Output: "ABCD"
print(find_smallest_repeating_substring("AAAAAA"))        # Output: "A"
print(find_smallest_repeating_substring("ABABABAB"))      # Output: "AB"
print(find_smallest_repeating_substring("ABCDE"))         # Output: "ABCDE"

```

Parenthesis problem:-

1.https://leetcode.com/problems/generate-parentheses Medium
2.https://leetcode.com/problems/score-of-parentheses Medium
5.https://leetcode.com/problems/remove-outermost-parentheses Easy
6.https://leetcode.com/problems/different-ways-to-add-parentheses/ Medium
8.https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses Medium
9.https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses Easy
10.https://leetcode.com/problems/longest-valid-parentheses/ Hard
7.https://leetcode.com/problems/remove-invalid-parentheses Hard


Counting of substring based on some condition:-


1.https://leetcode.com/problems/number-of-wonderful-substrings Medium
2.https://leetcode.com/problems/sum-of-beauty-of-all-substrings/ Medium
3.https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring Medium
4.https://leetcode.com/problems/number-of-wonderful-substrings Medium


Check types of string:-


1.https://leetcode.com/problems/isomorphic-strings Easy
2.https://leetcode.com/problems/valid-anagram Easy
3. https://leetcode.com/problems/additive-number Medium
4.https://leetcode.com/problems/buddy-strings Easy
5.https://leetcode.com/problems/longest-happy-prefix Hard
6.https://leetcode.com/problems/increasing-decreasing-string Easy
7.https://leetcode.com/problems/check-if-a-string-can-break-another-string Medium
8.https://leetcode.com/problems/determine-if-two-strings-are-close Medium 
9.https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent Easy
10.https://leetcode.com/problems/check-if-word-equals-summation-of-two-words Easy
11.https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal Easy


Palindromic string:-


1.https://leetcode.com/problems/palindrome-partitioning Medium
2.https://leetcode.com/problems/palindrome-partitioning-ii Hard
3.https://leetcode.com/problems/valid-palindrome Easy
4.https://leetcode.com/problems/shortest-palindrome Hard
5.https://leetcode.com/problems/palindrome-pairs Hard
6.https://leetcode.com/problems/longest-palindrome Easy
7.https://leetcode.com/problems/longest-palindromic-subsequence Medium
8.https://leetcode.com/problems/find-the-closest-palindrome Hard
9.https://leetcode.com/problems/palindromic-substrings Medium
10.https://leetcode.com/problems/valid-palindrome-ii Easy
11.https://leetcode.com/problems/longest-chunked-palindrome-decomposition Hard 
12.https://leetcode.com/problems/break-a-palindrome Medium
13. https://leetcode.com/problems/can-make-palindrome-from-substring Medium
14.https://leetcode.com/problems/palindrome-partitioning-iii Hard
15.https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome Hard
16.https://leetcode.com/problems/remove-palindromic-subsequences Easy
16.https://leetcode.com/problems/construct-k-palindrome-strings Medium
17.https://leetcode.com/problems/split-two-strings-to-make-palindrome Medium


Sorting on String:-
1.https://leetcode.com/problems/sort-characters-by-frequency Medium
2.https://leetcode.com/problems/custom-sort-string


Longest and shortest kind of String Problem :-


https://leetcode.com/problems/longest-duplicate-substring Hard
2.https://leetcode.com/problems/longest-string-chain Medium
3.https://leetcode.com/problems/longest-common-subsequence Medium
4.https://leetcode.com/problems/longest-happy-string Medium
5.https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters Medium
6.https://leetcode.com/problems/find-longest-awesome-substring Hard
7.https://leetcode.com/problems/largest-substring-between-two-equal-characters Easy
8.https://leetcode.com/problems/largest-odd-number-in-string Easy

get all possible substrings 

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

https://leetcode.com/problems/longest-palindromic-substring/description/