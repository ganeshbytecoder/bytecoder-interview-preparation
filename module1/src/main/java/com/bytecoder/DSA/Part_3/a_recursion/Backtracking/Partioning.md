### 4. Advanced Backtracking

1. **Palindrome Partitioning** [LC-131]
```java
public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), s, 0);
    return result;
}

private void backtrack(List<List<String>> result, List<String> current, String s, int start) {
    if (start == s.length()) {
        result.add(new ArrayList<>(current));
        return;
    }
    
    for (int i = start; i < s.length(); i++) {
        if (isPalindrome(s, start, i)) {
            current.add(s.substring(start, i + 1));
            backtrack(result, current, s, i + 1);
            current.remove(current.size() - 1);
        }
    }
}

private boolean isPalindrome(String s, int low, int high) {
    while (low < high) {
        if (s.charAt(low++) != s.charAt(high--)) return false;
    }
    return true;
}
```

2. **Remove Invalid Parentheses** [LC-301]
3. **Word Break II** [LC-140]
3. **Word Break Problem Using Backtracking**
    - Use backtracking to check all possible partitions of the string and see if each partition is a valid word in the dictionary.


7. [Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)
    - Restore all valid IP addresses from a string of digits.
```java
public List<String> restoreIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    backtrack(result, s, "", 0, 0);
    return result;
}

private void backtrack(List<String> result, String s, String current, int start, int segments) {
    if (segments == 4 && start == s.length()) {
        result.add(current.substring(0, current.length() - 1));
        return;
    }
    if (segments == 4 || start == s.length()) return;

    for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
        String part = s.substring(start, start + len);
        if (Integer.parseInt(part) > 255 || (part.length() > 1 && part.startsWith("0"))) continue;
        backtrack(result, s, current + part + ".", start + len, segments + 1);
    }
}

```

