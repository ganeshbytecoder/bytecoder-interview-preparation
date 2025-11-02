### Pattern 7: Word Ladder (State Space BFS) / Implicit Graph / State Space Search


**Use Cases:** Word ladder, minimum genetic mutation, sliding puzzle

**💡 Key Insight:** Graph not explicitly given. Each state is a node, transitions are edges. Use BFS for shortest transformation. Generate neighbors on-the-fly.

**Common Problems:**

* Evaluate Division (399)
* Find Celebrity (277)

**Use Cases:** Transformation sequences, mutation paths, state transitions

**💡 Key Insight:** Treat each word as a node. Edges exist between words differing by one character. BFS finds shortest transformation.

**Time:** O(M² × N) where M = word length, N = wordList size | **Space:** O(M × N)

```python
from collections import deque
from typing import List

class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        wordSet = set(wordList)  # O(1) lookup
        if endWord not in wordSet:
            return 0  # Transformation impossible

        queue = deque([(beginWord, 1)])  # (word, steps)

        while queue:
            currentWord, depth = queue.popleft()

            if currentWord == endWord:
                return depth  # Found shortest path
  
            # Try modifying each character
            for i in range(len(currentWord)):
                for char in 'abcdefghijklmnopqrstuvwxyz':
                    newWord = currentWord[:i] + char + currentWord[i+1:]
  
                    if newWord in wordSet:
                        queue.append((newWord, depth + 1))
                        wordSet.remove(newWord)  # Mark as visited

        return 0  # No transformation found
```

**Real-World Application:** DNA mutation paths in biotechnology
