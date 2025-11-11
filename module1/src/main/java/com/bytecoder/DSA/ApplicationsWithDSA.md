## implementation of Max Stack(716), hashmap, hashset, queue and deque and priority queue

- https://leetcode.com/problems/design-hashset/description/?envType=company&envId=facebook&favoriteSlug=facebook-three-months

---

- Rate Limiters
- load balancer
- Caching
- database implementation

### Core System Design Topics

* [ ] Scalability (Horizontal vs Vertical)
* [ ] Load Balancing
* [ ] Caching Strategies
* [ ] Database Design (SQL vs NoSQL)
* [ ] API Design (REST, GraphQL, gRPC)
* [ ] Microservices Architecture
* [ ] CAP Theorem and PACELC Theorem
* [ ] Consistent Hashing (needs more depth)
* [ ] Rate Limiting (needs more implementation details)
* [ ] Data Partitioning/Sharding (needs more examples)
* [ ] Message Queues and Event-Driven Architecture
* [ ] CDN Architecture
* [ ] Pyspark Architecture System

### Quantitative Design Skills

* [ ] Back-of-the-envelope Calculations
* [ ] Capacity Estimation
* [ ] Traffic Estimation
* [ ] Storage Requirements Calculation
* [ ] Bandwidth Estimation
* [ ] Latency Budgeting

### Low-Level Design

* [X] SOLID Principles
* [X] Design Patterns (Creational, Structural, Behavioral)
* [X] OOP Concepts
* [ ] Concurrency Patterns
* [ ] API Design Best Practices
* [ ] Data Structures and Algorithms
* [ ] Database Design (SQL vs NoSQL)
* [ ] Caching Strategies
* [ ] Event-Driven Architecture

### 6. Bloom Filters

**Definition**

- A probabilistic data structure that tells you whether an element **definitely is not** in a set or **possibly is** in a set.

**Characteristics**

- **False positives** are possible, but **false negatives** are not.
- Very space-efficient for large data sets.
- Commonly used to reduce expensive lookups, e.g., checking if an item is in a cache.

**Use Cases**

- Check if an email is in a spam list.
- Check if a request has been processed before.
- Quick membership tests in large datasets (e.g., from disk-based or remote data sources).

**Key Considerations**

- Tuning the **number of hash functions** and **size of the bit array** to minimize false positives.
- Bloom filters cannot remove elements easily (unless using advanced variants like Counting Bloom Filters).

---

### **Types of Rate Limiters**

| Type                     | Description                                                                      | DSA Behind It                         |
| ------------------------ | -------------------------------------------------------------------------------- | ------------------------------------- |
| **Fixed Window**   | Counts requests in a fixed time window (e.g., 10 reqs per minute).               | HashMap + Counter                     |
| **Sliding Window** | Improves fixed window by splitting into smaller intervals for smoother limiting. | HashMap + Deque (Queue of timestamps) |
| **Token Bucket**   | Adds tokens at a fixed rate, each request consumes a token.                      | Queue or counter + time-based refill  |
| **Leaky Bucket**   | Queues requests and processes them at a fixed rate (smooth out bursts).          | Queue with fixed processing interval  |
| **Sliding Log**    | Stores timestamps of all requests, removes those older than the window.          | Queue/Deque                           |

---

### **How to Implement Each Using DSA**

#### **1. Fixed Window Counter (Simple)**

```java
class FixedWindowRateLimiter {
    Map<String, Integer> requestCounts = new HashMap<>();
    long windowStart = System.currentTimeMillis();
    int limit = 100; // max requests per window
    long windowSize = 60_000; // 1 minute

    boolean allowRequest(String user) {
        long now = System.currentTimeMillis();
        if (now - windowStart > windowSize) {
            requestCounts.clear();
            windowStart = now;
        }
        requestCounts.put(user, requestCounts.getOrDefault(user, 0) + 1);
        return requestCounts.get(user) <= limit;
    }
}
```

#### **2. Sliding Window Log**

```java
class SlidingWindowRateLimiter {
    Map<String, Deque<Long>> userTimestamps = new HashMap<>();
    int limit = 100;
    long windowSize = 60_000;

    boolean allowRequest(String user) {
        long now = System.currentTimeMillis();
        userTimestamps.putIfAbsent(user, new ArrayDeque<>());
        Deque<Long> timestamps = userTimestamps.get(user);

        while (!timestamps.isEmpty() && now - timestamps.peekFirst() > windowSize) {
            timestamps.pollFirst();
        }

        if (timestamps.size() < limit) {
            timestamps.addLast(now);
            return true;
        } else {
            return false;
        }
    }
}
```

#### **3. Token Bucket**

```java
class TokenBucket {
    int capacity = 100;
    int tokens;
    long lastRefill;
    int refillRate = 10; // tokens per second

    public TokenBucket() {
        this.tokens = capacity;
        this.lastRefill = System.currentTimeMillis();
    }

    boolean allowRequest() {
        long now = System.currentTimeMillis();
        int tokensToAdd = (int)((now - lastRefill) / 1000) * refillRate;
        tokens = Math.min(capacity, tokens + tokensToAdd);
        lastRefill = now;

        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}
```

#### **4. Leaky Bucket (Queue)**

```java
class LeakyBucket {
    Queue<Long> bucket = new LinkedList<>();
    int capacity = 100;
    long leakRate = 1000; // ms between processing
    long lastLeakTime = System.currentTimeMillis();

    boolean allowRequest() {
        long now = System.currentTimeMillis();
        if (now - lastLeakTime >= leakRate && !bucket.isEmpty()) {
            bucket.poll(); // process request
            lastLeakTime = now;
        }

        if (bucket.size() < capacity) {
            bucket.add(now);
            return true;
        } else {
            return false;
        }
    }
}
```

---

### **When to Use Which?**

| Scenario                | Use            |
| ----------------------- | -------------- |
| Simplicity & speed      | Fixed Window   |
| Smooth rate control     | Sliding Window |
| Burstable traffic       | Token Bucket   |
| Guaranteed uniform flow | Leaky Bucket   |
| High accuracy per-user  | Sliding Log    |

---

Here’s a breakdown of **types of load balancers**, what they do, and how you can **implement them using Data Structures and Algorithms (DSA)**.

---

## **Types of Load Balancers**

| Type                               | Description                                                    | DSA Used                                        |
| ---------------------------------- | -------------------------------------------------------------- | ----------------------------------------------- |
| **Round Robin**              | Assigns requests to servers in circular order                  | Circular queue / pointer index                  |
| **Sticky Round Robin**       | Assigns requests to same servers to same user                  | Circular queue / pointer index / hashmap        |
| **Weighted Round Robin**     | Like Round Robin but gives preference to higher-weight servers | Array/List + server weight logic                |
| **IP/URL Hash**              | Uses hash of client IP to route to same server                 | HashMap / Consistent Hashing                    |
| **Random**                   | Picks a random server each time                                | Random with Array/List                          |
| **Consistent Hashing**       | Smoothly handles node additions/removals                       | TreeMap + Hashing (like in Distributed systems) |
| **Least Time (low latency)** | Like Round Robin but gives preference to least time servers    | Array/List + server weight logic                |
| **Least Connections**        | Routes to server with fewest active connections                | Min Heap / PriorityQueue                        |

---

## **DSA Implementations**

### **1. Round Robin**

```java
class RoundRobinLB {
    List<String> servers;
    int index = 0;

    RoundRobinLB(List<String> servers) {
        this.servers = servers;
    }

    String getServer() {
        String server = servers.get(index);
        index = (index + 1) % servers.size();
        return server;
    }
}
```

---

### **2. Weighted Round Robin**

```java
class WeightedServer {
    String name;
    int weight;
    int currentWeight = 0;

    WeightedServer(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
}

class WeightedRoundRobinLB {
    List<WeightedServer> servers;
    int totalWeight = 0;

    WeightedRoundRobinLB(List<WeightedServer> servers) {
        this.servers = servers;
        for (WeightedServer s : servers) totalWeight += s.weight;
    }

    String getServer() {
        WeightedServer best = null;
        for (WeightedServer s : servers) {
            s.currentWeight += s.weight;
            if (best == null || s.currentWeight > best.currentWeight) best = s;
        }
        best.currentWeight -= totalWeight;
        return best.name;
    }
}
```

---

### **3. Least Connections**

```java
class Server implements Comparable<Server> {
    String name;
    int connections;

    Server(String name, int connections) {
        this.name = name;
        this.connections = connections;
    }

    public int compareTo(Server o) {
        return this.connections - o.connections;
    }
}

class LeastConnectionLB {
    PriorityQueue<Server> heap;

    LeastConnectionLB(List<Server> servers) {
        heap = new PriorityQueue<>(servers);
    }

    String getServer() {
        Server s = heap.poll();
        s.connections++;
        heap.offer(s);
        return s.name;
    }

    void releaseConnection(String serverName) {
        for (Server s : heap) {
            if (s.name.equals(serverName)) {
                s.connections--;
                break;
            }
        }
        // Re-heapify is needed
    }
}
```

---

### **4. Random Load Balancer**

```java
class RandomLB {
    List<String> servers;
    Random rand = new Random();

    RandomLB(List<String> servers) {
        this.servers = servers;
    }

    String getServer() {
        int idx = rand.nextInt(servers.size());
        return servers.get(idx);
    }
}
```

---

### **5. IP Hash**

```java
class IPHashLB {
    List<String> servers;

    IPHashLB(List<String> servers) {
        this.servers = servers;
    }

    String getServer(String ip) {
        int hash = ip.hashCode();
        int index = Math.abs(hash) % servers.size();
        return servers.get(index);
    }
}
```

---

### **6. Consistent Hashing (Advanced)**

```java
class ConsistentHashLB {
    TreeMap<Integer, String> ring = new TreeMap<>();
    int numVirtualNodes = 3;

    ConsistentHashLB(List<String> servers) {
        for (String server : servers) {
            for (int i = 0; i < numVirtualNodes; i++) {
                int hash = (server + i).hashCode();
                ring.put(hash, server);
            }
        }
    }

    String getServer(String clientKey) {
        int hash = clientKey.hashCode();
        SortedMap<Integer, String> tailMap = ring.tailMap(hash);
        return tailMap.isEmpty() ? ring.firstEntry().getValue() : tailMap.get(tailMap.firstKey());
    }
}
```

---

## **When to Use What**

| Use Case                      | Load Balancer        |
| ----------------------------- | -------------------- |
| Simple round distribution     | Round Robin          |
| Different server capacities   | Weighted Round Robin |
| Handle long connections (DBs) | Least Connections    |
| Client affinity               | IP Hash              |
| High availability & scale     | Consistent Hashing   |
| Stateless microservices       | Random               |

---

Here’s a full breakdown of **cache types**, how they work, and how to **implement them using Data Structures and Algorithms (DSA)**.

---

## **Types of Caches**

| Cache Type                            | Description                                           | DSA Used                                              |
| ------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------- |
| **LRU (Least Recently Used)**   | Removes least recently accessed item when full        | HashMap + Doubly Linked List                          |
| **LFU (Least Frequently Used)** | Removes least frequently used item                    | HashMap + Min Heap or Frequency Map + LinkedHashSet   |
| **MRU (Most Recently Used)**    | Opposite of LRU, removes most recently accessed       | HashMap + Stack/Deque                                 |
| **FIFO (First In First Out)**   | Removes the oldest inserted item                      | Queue + HashMap                                       |
| **Write-through Cache**         | Writes data to cache and backing store simultaneously | No eviction logic; simulate DB write + HashMap        |
| **Write-back Cache**            | Writes to cache first, then DB later (lazy write)     | HashMap + Dirty Bit + Write queue                     |
| **Time-based Expiry Cache**     | Removes entries after a TTL                           | HashMap + PriorityQueue (min-heap on expiration time) |

---

- how what's handles million of requests and connections
- Trillions of Web Pages: Where Does Google Store Them?
- bloom filter

## **DSA Implementations**

### **1. LRU Cache**

```java
class LRUCache {
    class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    int capacity;
    Map<Integer, Node> map = new HashMap<>();
    Node head = new Node(0, 0), tail = new Node(0, 0);

    public LRUCache(int cap) {
        capacity = cap;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        insertToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) remove(map.get(key));
        if (map.size() == capacity) remove(tail.prev);
        insertToFront(new Node(key, value));
    }

    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertToFront(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
```

---

### **2. LFU Cache (Using Frequency Map + LinkedHashSet)**

```java
class LFUCache {
    class Node {
        int key, val, freq = 1;
        Node(int k, int v) { key = k; val = v; }
    }

    int cap, minFreq = 0;
    Map<Integer, Node> cache = new HashMap<>();
    Map<Integer, LinkedHashSet<Integer>> freqMap = new HashMap<>();

    public LFUCache(int capacity) { this.cap = capacity; }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        increaseFreq(key);
        return cache.get(key).val;
    }

    public void put(int key, int value) {
        if (cap == 0) return;

        if (cache.containsKey(key)) {
            cache.get(key).val = value;
            increaseFreq(key);
        } else {
            if (cache.size() == cap) evictLFU();
            Node node = new Node(key, value);
            cache.put(key, node);
            freqMap.computeIfAbsent(1, f -> new LinkedHashSet<>()).add(key);
            minFreq = 1;
        }
    }

    private void increaseFreq(int key) {
        Node node = cache.get(key);
        int freq = node.freq;
        freqMap.get(freq).remove(key);
        if (freqMap.get(freq).isEmpty() && freq == minFreq) minFreq++;
        node.freq++;
        freqMap.computeIfAbsent(node.freq, f -> new LinkedHashSet<>()).add(key);
    }

    private void evictLFU() {
        int key = freqMap.get(minFreq).iterator().next();
        freqMap.get(minFreq).remove(key);
        cache.remove(key);
    }
}
```

---

### **3. MRU Cache (Most Recently Used)**

```java
class MRUCache {
    Deque<Integer> stack = new ArrayDeque<>();
    Map<Integer, Integer> map = new HashMap<>();
    int cap;

    MRUCache(int capacity) {
        cap = capacity;
    }

    void put(int key, int val) {
        if (map.containsKey(key)) stack.remove(key);
        else if (map.size() == cap) {
            int mostRecent = stack.removeLast();
            map.remove(mostRecent);
        }
        stack.addLast(key);
        map.put(key, val);
    }

    int get(int key) {
        if (!map.containsKey(key)) return -1;
        stack.remove(key);
        stack.addLast(key);
        return map.get(key);
    }
}
```

---

### **4. FIFO Cache**

```java
class FIFOCache {
    Queue<Integer> queue = new LinkedList<>();
    Map<Integer, Integer> map = new HashMap<>();
    int cap;

    FIFOCache(int cap) { this.cap = cap; }

    void put(int key, int val) {
        if (!map.containsKey(key)) {
            if (map.size() == cap) {
                int old = queue.poll();
                map.remove(old);
            }
            queue.add(key);
        }
        map.put(key, val);
    }

    int get(int key) {
        return map.getOrDefault(key, -1);
    }
}
```

---

### **5. Time-Based Expiry Cache**

```java
class TimeCache {
    class Entry {
        int value;
        long expiryTime;

        Entry(int v, long ttl) {
            value = v;
            expiryTime = System.currentTimeMillis() + ttl;
        }
    }

    Map<Integer, Entry> map = new HashMap<>();

    void put(int key, int value, long ttlMillis) {
        map.put(key, new Entry(value, ttlMillis));
    }

    Integer get(int key) {
        Entry entry = map.get(key);
        if (entry == null || entry.expiryTime < System.currentTimeMillis()) {
            map.remove(key);
            return -1;
        }
        return entry.value;
    }
}
```

---

## **When to Use What?**

| Use Case                                 | Use                     |
| ---------------------------------------- | ----------------------- |
| Most recent access is valuable           | **LRU**           |
| Frequency of access matters              | **LFU**           |
| You want to evict new data first         | **MRU**           |
| FIFO strategy needed (e.g., queue logic) | **FIFO**          |
| Expiring sessions or tokens              | **TTL Cache**     |
| Cache + always write to DB               | **Write-through** |
| Cache first, write later                 | **Write-back**    |

---

Here’s a deep dive into **types of internal database systems** (like B-Trees, LSM Trees, SSTables, etc.), their use cases, and how to **implement their core concepts using DSA**.

---

## **Types of Internal Database Systems**

| Data Structure                                 | Used In              | Description                                         | Commonly Used In                        |
| ---------------------------------------------- | -------------------- | --------------------------------------------------- | --------------------------------------- |
| **B-Tree / B+ Tree**                     | Indexing             | Balanced tree for ordered data, fast lookups        | Relational DBs (MySQL, Postgres)        |
| **LSM Tree (Log-Structured Merge Tree)** | Write-heavy systems  | Write-optimized structure using memory + disk trees | NoSQL DBs (LevelDB, RocksDB, Cassandra) |
| **SSTable (Sorted String Table)**        | Disk storage for LSM | Immutable, sorted files on disk with indexes        | Cassandra, HBase                        |
| **Trie / Radix Tree**                    | Prefix-based search  | Fast string or IP prefix lookups                    | DNS, Redis, IP routing                  |
| **Hash Index**                           | Key-value storage    | Fast access via hashing                             | MongoDB, DynamoDB                       |
| **Heap File**                            | Unordered storage    | Appends new records, full scan needed               | Used in simple file-based DBs           |
| **Bitmaps / Bit Index**                  | Boolean columns      | Fast filtering on categorical data                  | Columnar DBs like ClickHouse            |
| **Inverted Index**                       | Text search          | Maps words to document IDs                          | Search engines (Elasticsearch, Lucene)  |

---

### **1. B-Tree (Simplified)**

- Used in file systems and RDBMS for ordered indexing.

```java
class BTreeNode {
    int[] keys;
    int t;  // Minimum degree
    BTreeNode[] children;
    int n;
    boolean leaf;

    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        keys = new int[2 * t - 1];
        children = new BTreeNode[2 * t];
        n = 0;
    }

    void traverse() {
        for (int i = 0; i < n; i++) {
            if (!leaf) children[i].traverse();
            System.out.print(keys[i] + " ");
        }
        if (!leaf) children[n].traverse();
    }

    // Search & Insert logic here (omitted for brevity)
}
```

---

### **2. LSM Tree (Log-Structured Merge Tree) - Simplified View**

- **MemTable (in-memory)**: Red-Black Tree or Sorted Map
- **SSTables (on-disk)**: Immutable sorted files
- **Background merge** to combine SSTables

```java
class LSMTree {
    TreeMap<String, String> memTable = new TreeMap<>();
    List<Map<String, String>> ssTables = new ArrayList<>();
    final int THRESHOLD = 100;

    void put(String key, String value) {
        memTable.put(key, value);
        if (memTable.size() >= THRESHOLD) flushToDisk();
    }

    String get(String key) {
        if (memTable.containsKey(key)) return memTable.get(key);
        for (Map<String, String> sst : ssTables)
            if (sst.containsKey(key)) return sst.get(key);
        return null;
    }

    void flushToDisk() {
        ssTables.add(0, new TreeMap<>(memTable)); // Newest on top
        memTable.clear();
    }
}
```

---

### **3. SSTable (Immutable, Sorted Table on Disk - In-memory Sim)**

```java
class SSTable {
    TreeMap<String, String> data = new TreeMap<>();

    SSTable(Map<String, String> inputData) {
        data.putAll(inputData); // write-sorted
    }

    String search(String key) {
        return data.get(key);
    }

    List<String> prefixSearch(String prefix) {
        return data.subMap(prefix, prefix + Character.MAX_VALUE)
                   .values().stream().toList();
    }
}
```

---

### **4. Trie (Prefix Tree)**

- Used for autocomplete, DNS, IP routing

```java
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEnd = false;
}

class Trie {
    TrieNode root = new TrieNode();

    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray())
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        node.isEnd = true;
    }

    boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray())
            if (!node.children.containsKey(c)) return false;
            else node = node.children.get(c);
        return node.isEnd;
    }
}
```

---

### **5. Inverted Index**

- Used for text search (full-text search)

```java
class InvertedIndex {
    Map<String, List<Integer>> index = new HashMap<>();

    void addDocument(int docId, String content) {
        for (String word : content.split("\\s+")) {
            index.computeIfAbsent(word, k -> new ArrayList<>()).add(docId);
        }
    }

    List<Integer> search(String word) {
        return index.getOrDefault(word, new ArrayList<>());
    }
}
```

---

### **6. Hash Index**

- Used in key-value databases like DynamoDB

```java
class HashIndex {
    Map<String, String> index = new HashMap<>();

    void put(String key, String value) {
        index.put(key, value);
    }

    String get(String key) {
        return index.getOrDefault(key, null);
    }
}
```

---

## **Quick Comparison Table**

| Structure      | Read                 | Write      | Ordered?         | Use Case               |
| -------------- | -------------------- | ---------- | ---------------- | ---------------------- |
| B-Tree         | Fast                 | Fast       | Yes              | Relational DB          |
| LSM Tree       | Moderate             | Very Fast  | Yes (eventually) | Write-heavy NoSQL      |
| SSTable        | Fast (binary search) | Write-once | Yes              | Immutable disk storage |
| Trie           | Fast prefix          | Fast       | Yes              | DNS, search            |
| Hash Index     | O(1)                 | O(1)       | No               | Key-value              |
| Inverted Index | Fast (search term)   | Moderate   | N/A              | Full-text search       |

---
