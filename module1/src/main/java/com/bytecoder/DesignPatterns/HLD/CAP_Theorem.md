## 5. CAP Theorem & PACELC Theorem

**CAP Theorem**
- In a distributed system, you can only guarantee two out of three: **Consistency**, **Availability**, and **Partition tolerance**.
- **Partition Tolerance** is usually non-negotiable in modern distributed systems, so you must choose between Consistency and Availability during a partition.

**PACELC Theorem**
- An extension of CAP that further refines trade-offs during normal operation vs. during a partition.
- **PACELC**: If there is a **P**artition, you choose between **A**vailability and **C**onsistency; **E**lse (in normal operation), you choose between **L**atency and **C**onsistency.
- Real-world systems often prioritize availability and low latency over strict consistency, though it depends heavily on use case.

---



Great! Let’s break this down step by step for **Apache Cassandra** and **MongoDB**, focusing on:

1. **Internal architecture/workflow**
2. **CAP theorem alignment**
3. **Pros and cons**

---

## 🟣 Apache Cassandra

### 1. Internal Workflow
- **Architecture**: Peer-to-peer distributed system (no master-slave).
- **Data Model**: Wide-column store (like a table with rows and columns).
- **Write Path**:
    - Write is sent to a **coordinator node** → distributed to replicas based on the **partition key** using **consistent hashing**.
    - Written to **Commit Log** (for durability).
    - Then to **MemTable** (in-memory).
    - Periodically flushed to disk as **SSTables**.
- **Read Path**:
    - Coordinator contacts replicas (based on consistency level).
    - Fetches data from MemTable and SSTables.
    - May perform **read repair** or **hinted handoff**.

### 2. CAP Theorem
- **AP (Availability + Partition Tolerance)**:
    - Prioritizes **Availability** and **Partition Tolerance**.
    - Allows tuning **Consistency** via consistency levels (`ONE`, `QUORUM`, `ALL`, etc.).
    - Works even when some nodes are unreachable, as long as consistency level is satisfied.

### 3. Pros and Cons
#### ✅ Pros:
- Linear scalability and fault tolerance.
- No single point of failure.
- Tunable consistency levels.
- Fast write-heavy workloads.

#### ❌ Cons:
- Eventually consistent by default.
- Complex data modeling.
- Compaction and tombstones can cause performance issues.

---

## 🟢 MongoDB

### 1. Internal Workflow
- **Architecture**: Replica Set (Primary-Secondary) or Sharded Cluster (for scalability).
- **Data Model**: Document store (BSON format).
- **Write Path**:
    - Sent to **Primary** in a replica set.
    - Written to **oplog** and **WiredTiger cache**.
    - Replicated asynchronously to **Secondaries**.
- **Read Path**:
    - By default, reads from **Primary**.
    - Can configure to read from **Secondaries** (`readPreference`).
    - Uses **write concern** and **read concern** to control consistency.

### 2. CAP Theorem
- **CP (Consistency + Partition Tolerance)**:
    - Prioritizes **Consistency** and **Partition Tolerance** in replica sets.
    - During a partition, secondaries will not accept writes unless a new primary is elected.
    - Can trade off consistency using **write concern** and **read concern**.

### 3. Pros and Cons
#### ✅ Pros:
- Easy to use and flexible schema.
- Strong consistency by default (Replica Set).
- Rich query language and aggregation framework.
- Good tooling and ecosystem.

#### ❌ Cons:
- Less performant for write-heavy workloads compared to Cassandra.
- Sharding adds complexity.
- Default configurations may lead to replica lag or unacknowledged writes.

---

## TL;DR CAP Mapping

| Database   | Consistency | Availability | Partition Tolerance | Type |
|------------|-------------|--------------|----------------------|------|
| Cassandra  | ✳️ Tunable   | ✅ High       | ✅ High               | AP   |
| MongoDB    | ✅ Strong    | ⚠️ Medium     | ✅ High               | CP   |

---


Perfect! Let’s now compare **SQL (RDBMS)** with **Cassandra** and **MongoDB** based on:

1. **Internal Workflow**
2. **CAP Theorem**
3. **Pros and Cons**

This will give you a complete picture of how each system is designed and used depending on your application needs.

---

## 🟢 SQL Databases (e.g., MySQL, PostgreSQL)

### 1. Internal Workflow
- **Architecture**: Traditional **monolithic** or **master-slave** setup. Some newer systems support **multi-master** or **clustered replication** (e.g., Galera, Aurora).
- **Data Model**: **Relational** (tables with strict schemas, joins, ACID transactions).
- **Write Path**:
  - Writes go to the **Primary (Master)** in most setups.
  - Written to **WAL (Write-Ahead Log)** or **redo log** for durability.
  - Data then flushed to disk (buffer pool, data pages).
  - Replication to read replicas (if configured).
- **Read Path**:
  - Read from the primary or replicas (with possible replication lag).
  - Optimized using **indexes**, **caching**, and **query optimizers**.

### 2. CAP Theorem
- **CP (Consistency + Partition Tolerance)**:
  - Traditional SQL databases prioritize **Consistency**.
  - In distributed setups, they may trade **Availability** under network partitions to maintain consistency (e.g., failover delay).
  - Can be made highly available via clustering, but it adds complexity.

### 3. Pros and Cons

#### ✅ Pros:
- **Strong ACID guarantees**
- Mature ecosystem and tools
- Well-suited for complex queries and joins
- Strong transactional support

#### ❌ Cons:
- Harder to scale horizontally
- Schema rigidity
- Performance bottlenecks with massive writes or distributed deployments

---

## 🟣 Cassandra

(Recap with SQL in view)

### 1. Internal Workflow
- **Peer-to-peer**, **partitioned**, **distributed**
- Any node can handle writes (coordinator)
- Data written to **commit log + memtable → SSTables**
- Supports **tunable consistency**

### 2. CAP Theorem
- **AP (Availability + Partition Tolerance)** by default
- Trades off strict consistency for availability
- You can configure consistency levels (e.g., `QUORUM`, `ALL`)

### 3. Pros and Cons

#### ✅ Pros:
- Highly scalable (linear write throughput)
- Fault-tolerant with no SPOF
- Great for write-heavy workloads

#### ❌ Cons:
- Eventually consistent (unless tuned)
- No support for joins, subqueries, or transactions across rows
- Data modeling is more complex (query-first)

---

## 🟢 MongoDB

(Recap with SQL in view)

### 1. Internal Workflow
- **Replica Set** (Primary/Secondary)
- Writes go to **Primary** → **oplog** → replicated to Secondaries
- Sharding for horizontal scaling

### 2. CAP Theorem
- **CP (Consistency + Partition Tolerance)**
- Prioritizes **strong consistency**
- Write and read concern can be configured for trade-offs

### 3. Pros and Cons

#### ✅ Pros:
- Flexible schema (JSON/BSON)
- Developer-friendly with rich query capabilities
- Strong consistency by default
- Good for rapid development

#### ❌ Cons:
- Not ideal for write-heavy use cases like Cassandra
- Scaling and sharding add complexity
- Joins and multi-document transactions are limited (though improving)

---

## 🔄 Quick Comparison Table

| Feature              | SQL (MySQL/Postgres)    | MongoDB                   | Cassandra                  |
|----------------------|--------------------------|----------------------------|----------------------------|
| Data Model           | Relational (strict)      | Document (flexible JSON)  | Wide-Column (denormalized)|
| Architecture         | Master-Slave / Clustered | Replica Set / Sharded     | Peer-to-Peer Distributed   |
| CAP Theorem          | **CP**                   | **CP (tunable)**          | **AP (tunable)**           |
| Consistency          | Strong (ACID)            | Strong (by default)       | Tunable / Eventual         |
| Scalability          | Vertical / Some Horizontal | Good horizontal via sharding | Excellent horizontal       |
| Write Target         | Master only              | Primary only              | Any node (coordinator)     |
| Use Case Fit         | OLTP, ACID apps          | Semi-structured, JSON APIs| High-volume, IoT, time-series |
| Schema               | Strict schema            | Dynamic schema            | Query-first schema         |

---

Let me know if you'd like a **visual cheat sheet**, or if you want to go deeper into specific real-world use cases (e.g., fintech, analytics, IoT, SaaS platforms).