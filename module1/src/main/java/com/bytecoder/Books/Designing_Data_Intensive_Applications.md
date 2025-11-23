### Core components

- Rate Limiters
- Firewall
- load balancer/API gateway
- Caching
- databases (OLTP vs OLAP)
- Servers
- Service mesh / service discovery/ K8s

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

* [ ] SOLID Principles
* [ ] Design Patterns (Creational, Structural, Behavioral)
* [ ] OOP Concepts
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

### **Types of Rate Limiters**

| Type                     | Description                                                                      | DSA Behind It                         |
| ------------------------ | -------------------------------------------------------------------------------- | ------------------------------------- |
| **Fixed Window**   | Counts requests in a fixed time window (e.g., 10 reqs per minute).               | HashMap + Counter                     |
| **Sliding Window** | Improves fixed window by splitting into smaller intervals for smoother limiting. | HashMap + Deque (Queue of timestamps) |
| **Token Bucket**   | Adds tokens at a fixed rate, each request consumes a token.                      | Queue or counter + time-based refill  |
| **Leaky Bucket**   | Queues requests and processes them at a fixed rate (smooth out bursts).          | Queue with fixed processing interval  |
| **Sliding Log**    | Stores timestamps of all requests, removes those older than the window.          | Queue/Deque                           |

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

---

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

## Detailed Summary of Chapter 1: *Reliable, Scalable, and Maintainable Applications*

### **1. What Are Data-Intensive Applications?**

- Modern applications often deal with **large volumes of data** and prioritize efficient data management over computational tasks.
- Components of data-intensive systems include:
  - **Databases**: Store and retrieve data.
  - **Caches**: Provide faster access to frequently used data.
  - **Search indexes**: Support full-text searches.
  - **Message queues**: Enable communication between different parts of the system.
  - **Stream processing**: Handle continuous data streams for real-time analytics.

---

### **2. The Goals of Software Systems**

Kleppmann identifies three primary goals of well-designed systems:

#### **Reliability**

**Definition**: The system performs correctly even in the face of:

- **Hardware failures**: Disk crashes, network interruptions, etc.
- **Software bugs**: Faulty code or configuration errors.
- **Human errors**: Mistakes made during operations or maintenance.

**Faults and Failures**:

- A **fault** refers to a cause (e.g., network interruption), while a **failure** is the system’s inability to perform correctly (e.g., unresponsive application).
- Fault-tolerant systems prevent faults from causing failures.

**Approaches to Achieve Reliability**:

1. **Redundancy**:
   - Duplicate data (e.g., replication) to prevent data loss during hardware failures.
   - Example: RAID storage systems or database replicas.
2. **Automated Recovery**:
   - Self-healing mechanisms that restart failed components or switch to backups automatically.
   - Example: Load balancers rerouting traffic to healthy servers.
3. **Preventive Measures**:
   - Thorough testing, monitoring, and using battle-tested libraries.

#### **Scalability**

**Definition**: The system’s ability to handle increased load gracefully.

**Measuring Load**:

- **Load parameters** vary depending on the application:
  - For social media: Number of requests per second.
  - For data analytics: Volume of data processed.
- Understand the system’s capacity to handle specific workloads.

**Scaling Strategies**:

1. **Vertical Scaling (Scaling Up)**:
   - Add more resources (e.g., CPU, memory) to a single machine.
   - Limited by the physical constraints of the hardware.
2. **Horizontal Scaling (Scaling Out)**:
   - Distribute the workload across multiple machines (e.g., clusters).
   - Challenges include handling distributed systems, partitioning data, and maintaining consistency.

**Bottlenecks**:

- Identify bottlenecks (e.g., database queries, network latency) using performance monitoring tools and optimize accordingly.

#### **Maintainability**

**Definition**: The ease of keeping a system operational, updating it, and adapting to new requirements.

**Key Considerations**:

1. **Operability**:
   - Simplify operational tasks, such as deployment, monitoring, and issue resolution.
   - Example: Automated backups, robust logging, and meaningful alerts.
2. **Simplicity**:
   - Avoid unnecessary complexity that makes the system harder to understand and debug.
   - Example: Use clear interfaces and avoid over-engineering.
3. **Evolvability**:
   - Design systems to accommodate changing requirements and new features.
   - Example: Modular architectures and backward-compatible APIs.

---

### **3. Balancing the Trade-offs**

- Balancing reliability, scalability, and maintainability often involves trade-offs.
  - Example: Adding distributed systems for scalability increases complexity, potentially reducing maintainability.

**Key Questions**:

- Which features are most critical for your application? (e.g., consistency, latency, availability)
- How can you balance trade-offs to meet your system’s goals?

---

### **4. Faults vs. Failures**

- **Faults** are inevitable in any system due to hardware, software, or human factors.
- **Failures** occur when faults disrupt the system’s operation.
- The goal is to design systems that can **tolerate faults** without resulting in failures.

**Example**:

- Fault: A server crashes.
- Failure: If the application depends solely on the crashed server and cannot function, this is a failure.
- Solution: Add redundancy, like load balancers and replicated databases.

---

### **5. Approaches to Scalability**

- Scaling strategies depend on the type of application and its bottlenecks:
  - For web servers: Distribute traffic across multiple servers using load balancers.
  - For databases: Partition (shard) data across multiple nodes.
- Use **benchmarking** to predict how the system behaves under different load scenarios.

---

### **6. Case Study: Data-Intensive Applications**

The chapter closes with examples of real-world systems:

- Web applications like Twitter handle high traffic by **scaling horizontally** and using **caches** for fast access.
- Batch processing systems like Hadoop handle large volumes of data by breaking it into smaller pieces and processing them in parallel.

---

### **Conclusion**

Chapter 1 sets the stage for the rest of the book by outlining the core principles and challenges of building reliable, scalable, and maintainable systems. It emphasizes the importance of understanding trade-offs and designing systems that can handle faults gracefully, scale effectively, and remain maintainable as they grow.

---

# **Detailed Summary of Chapter 2: Data Models and Query Languages**

(*From* *Designing Data-Intensive Applications* *by Martin Kleppmann*)

---

## **Introduction**

Chapter 2 explores **data models** and **query languages**, which are crucial for designing how data is structured, stored, and retrieved in modern applications. The choice of data model affects **application performance, scalability, and maintainability**.

The chapter focuses on:

1. **Types of Data Models** (Relational, Document, Graph)
2. **Query Languages** (SQL vs. NoSQL)
3. **Data Model Trade-offs**

---

## **1. What is a Data Model?**

A **data model** defines how data is organized, structured, and processed. It serves two main purposes:

1. **Storage Structure** – Determines how data is stored.
2. **Query Interface** – Defines how data is retrieved and modified.

A well-designed data model makes applications **easier to build and maintain**, while a poor data model can create inefficiencies and inconsistencies.

### **Three Types of Data Models**

1. **Relational Model (SQL Databases)**
2. **Document Model (NoSQL Document Databases)**
3. **Graph Model (Graph Databases)**

---

## **2. Relational Model (SQL Databases)**

### **Overview**

- Data is stored in **tables** (relations) with **rows** and **columns**.
- Each row represents a record, and each column represents an attribute.
- Uses **structured schemas** that define data types and constraints.

### **Example Table: Customers**

| ID | Name  | Email           |
| -- | ----- | --------------- |
| 1  | Alice | alice@email.com |
| 2  | Bob   | bob@email.com   |

### **Strengths**

✅ **Data Integrity & Consistency** – Uses constraints (e.g., primary keys, foreign keys) to enforce data correctness.
✅ **ACID Transactions** – Ensures Atomicity, Consistency, Isolation, and Durability.
✅ **Powerful Querying (SQL)** – Supports complex queries, aggregations, and joins.

### **Weaknesses**

❌ **Schema Rigidity** – Changes require altering the schema, which can be complex.
❌ **Expensive Joins** – Querying multiple tables via joins can slow performance for large datasets.
❌ **Scalability Limits** – Scaling relational databases **horizontally** (across multiple machines) is difficult.
❌ **impedance mismatch** – translation layer is required between the objects in the application code and the database model of tables, rows, and columns. ORM frameworks like hibernate has reduced it

### **When to Use the Relational Model?**

- **Transactional systems** (e.g., banking, finance).
- **Applications requiring strong consistency** (e.g., e-commerce, inventory management).

---

## **3. Document Model (NoSQL Document Databases)**

### **Overview**

- Stores data as **JSON-like documents** instead of tables.
- Each document contains nested key-value pairs.
- Flexible, allowing each document to have a different structure.

### **Example (MongoDB Document)**

```json
{
  "_id": 1,
  "name": "Alice",
  "email": "alice@email.com",
  "orders": [
    {"product": "Laptop", "price": 1000},
    {"product": "Mouse", "price": 50}
  ]
}
```

### **Strengths**

✅ **Schema Flexibility** – No predefined schema; documents can evolve dynamically.
✅ **Better for Nested Data** – Can store hierarchical relationships within a single document.
✅ **Better scalability** – Can easily achieve , including very large datasets or  vey high throughput.
✅ **Optimized for Read Performance** – Retrieves all relevant data in one query (avoids joins, not good with many-to-one and many-to-many relations). in this application has handle that joins to keep consistency
✅ **Reduces the impedance mismatch** – it reduces the impedance mismatch between the application code and the storage layer

### **Weaknesses**

❌ **Data Duplication** – Same data may be stored in multiple places, leading to inconsistency.
❌ **Limited Transaction Support** – Lacks ACID properties like relational databases.
❌ **Complex Querying** – Joins and aggregations are harder than in SQL databases.

### **When to Use the Document Model?**

- **Content management systems (CMS), catalogs, user profiles**.
- **Applications requiring flexible schemas** (e.g., social media, IoT data).

---

## **4. Graph Model (Graph Databases)**

### **Overview**

- Represents data as **nodes** (entities) and **edges** (relationships).
- Used for applications where relationships are **complex and interconnected**.

### **Example: Social Network (Friendship Graph)**

```
Alice → Bob → Charlie
```

### **Querying Example (Cypher in Neo4j)**

```cypher
MATCH (a:Person)-[:FRIEND]->(b:Person) 
WHERE a.name = "Alice" 
RETURN b.name
```

### **Strengths**

✅ **Efficient for Highly Connected Data** – Faster than SQL joins for relationship-heavy queries.
✅ **More Natural Representation of Relationships** – Unlike relational databases, it doesn’t need foreign keys.
✅ **Flexible Schema** – Can evolve easily over time.

### **Weaknesses**

❌ **Storage Overhead** – Graph databases require more space due to their relationship structures.
❌ **Limited Adoption** – Smaller ecosystem compared to SQL and NoSQL databases.

### **When to Use the Graph Model?**

- **Social networks, recommendation engines, fraud detection**.
- **Applications with many complex relationships (e.g., supply chain management).**

---

## **5. Query Languages: SQL vs. NoSQL**

### **SQL (Structured Query Language)**

- Used in relational databases.
- Declarative (specifies *what* data is needed, not *how* to retrieve it).
- Example Query:
  ```sql
  SELECT name, email FROM customers WHERE id = 1;
  ```

### **NoSQL Query Languages**

- Varies by database (e.g., MongoDB, Cassandra, Redis).
- Example MongoDB Query:
  ```json
  db.customers.find({ "name": "Alice" })
  ```
- More procedural and database-specific.

### **Choosing the Right Query Language**

| Feature            | SQL (Relational)                | NoSQL (Document, Key-Value)       |
| ------------------ | ------------------------------- | --------------------------------- |
| Query Complexity   | Strong for joins & aggregations | Weaker joins, relies on embedding |
| Schema Flexibility | Rigid schema                    | Dynamic schema                    |
| Scalability        | Vertical scaling (limited)      | Horizontal scaling (easier)       |
| ACID Transactions  | Strong                          | Weak or eventual consistency      |

---

## **6. Choosing the Right Data Model**

The **best data model** depends on the **application’s needs**:

| Requirement                              | Best Data Model  | Example Use Case                 |
| ---------------------------------------- | ---------------- | -------------------------------- |
| **Structured, transactional data** | Relational (SQL) | Banking, ERP                     |
| **Flexible, semi-structured data** | Document (NoSQL) | Content management               |
| **Complex relationships**          | Graph            | Social networks, fraud detection |

---

## **7. Polyglot Persistence (Using Multiple Databases)**

Instead of using one database for everything, modern applications often use **multiple databases** for different tasks.

### **Example: E-Commerce System**

| Data Type              | Best Database Type | Example    |
| ---------------------- | ------------------ | ---------- |
| User Accounts          | Relational (SQL)   | PostgreSQL |
| Product Catalog        | Document (NoSQL)   | MongoDB    |
| Social Network Friends | Graph              | Neo4j      |
| Session Data           | Key-Value Store    | Redis      |

---

## **Conclusion**

1. **Data models define how applications store and retrieve data.**
2. **Relational databases** provide strong consistency but can be rigid.
3. **Document databases** offer flexibility but may lead to data duplication.
4. **Graph databases** are best for applications with complex relationships.
5. **Query languages (SQL vs. NoSQL) affect how efficiently data can be retrieved.**
6. **Polyglot persistence allows applications to use different databases for different needs.**

This chapter sets the foundation for understanding **how to structure data effectively** and how different databases **optimize for performance, scalability, and flexibility**.

Would you like a **more detailed breakdown of any section**? 🚀
