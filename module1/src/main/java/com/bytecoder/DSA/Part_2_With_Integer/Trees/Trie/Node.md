https://leetcode.com/problems/implement-trie-prefix-tree/description/
https://leetcode.com/problems/design-add-and-search-words-data-structure/submissions/1545395837/?envType=study-plan-v2&envId=top-interview-150



## **8️⃣ Trie (Prefix Tree)**

Used for:
- Prefix-based search
- Auto-complete
- Dictionary word search

### 🔹 Implement Trie
```python
class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str):
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]
        node.is_end = True

    def search(self, word: str) -> bool:
        node = self.root
        for c in word:
            if c not in node.children:
                return False
            node = node.children[c]
        return node.is_end
```

---

