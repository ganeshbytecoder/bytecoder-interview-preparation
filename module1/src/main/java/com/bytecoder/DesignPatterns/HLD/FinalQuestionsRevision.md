
## Week 1

### 1. Scalability

**Definition**
- The ability of a system to handle increased load (traffic or data) by adding resources.
- Two types: **vertical scaling** (increasing the power of a single machine) and **horizontal scaling** (adding more machines/nodes).

**Horizontal vs. Vertical Scalability**
- **Vertical scaling**:
    - Increase CPU, RAM, and storage on a single machine.
    - Often simpler to implement but limited by hardware constraints and can be expensive.
- **Horizontal scaling**:
    - Add more nodes (servers) to the system.
    - More fault-tolerant and can handle very large loads, but introduces complexity in distribution, coordination, and maintenance.

**Key Considerations**
- Scaling trade-offs (e.g., performance vs. complexity, cost vs. operational overhead).
- Common scaling techniques (e.g., partitioning, caching, load balancing).

---

### 2. API Gateway

**Definition**
- A single entry point for all client requests in a microservices architecture.
- Responsible for request routing, composition, protocol translation, and security.

**Benefits**
- **Centralized access control**: Handles authentication, authorization, and usage throttling.
- **Protocol translation**: Supports different communication protocols (HTTP, WebSocket, gRPC, etc.).
- **Request Aggregation**: Combines multiple service calls into one request/response for efficiency.
- **Service abstraction**: Hides internal service details from clients.

**Common Implementations**
- NGINX, Kong, AWS API Gateway, etc.

---

### 3. Load Balancing

**Definition**
- Distributing incoming network traffic (or requests) across multiple servers to prevent any single server from becoming a bottleneck.

**Load Balancing Algorithms**
1. **Round Robin**: Requests are distributed sequentially across the server pool.
2. **Weighted Round Robin**: Each server is assigned a weight based on capacity or other metrics.
3. **Least Connections**: Routes a request to the server with the fewest active connections.
4. **IP Hash**: Allocates requests based on client IP, ensuring consistent routing.
5. **Least Time / Least Load**: Chooses the server that is currently serving the fewest requests or lowest load.

**Key Considerations**
- **Health checks** to ensure the load balancer routes traffic only to healthy servers.
- **Sticky sessions** can be required for stateful sessions, but ideally design stateless services to avoid stickiness.

---

### 4. Caching

**Definition**
- Storing data in a faster storage layer (e.g., in-memory) to reduce data retrieval times and improve performance.

**Types of Caches**
1. **Client-side cache** (browser cache, etc.).
2. **Server-side cache** (e.g., in-memory caches like Redis or Memcached).
3. **Content Delivery Network (CDN)** for static files.

**Caching Strategies**
- **Write-through**: Data is cached and written to the data store simultaneously.
- **Write-back** (or lazy write): Data is written to the cache first, then updated to the underlying database after a delay.
- **Eviction Policies** (e.g., LRU, LFU, FIFO) handle what to do when the cache is full.

**Key Considerations**
- Consistency trade-offs: Could serve stale data.
- Cache invalidation strategy: Ensuring cache coherence with the database.

---

### 5. CAP Theorem & PACELC Theorem

**CAP Theorem**
- In a distributed system, you can only guarantee two out of three: **Consistency**, **Availability**, and **Partition tolerance**.
- **Partition Tolerance** is usually non-negotiable in modern distributed systems, so you must choose between Consistency and Availability during a partition.

**PACELC Theorem**
- An extension of CAP that further refines trade-offs during normal operation vs. during a partition.
- **PACELC**: If there is a **P**artition, you choose between **A**vailability and **C**onsistency; **E**lse (in normal operation), you choose between **L**atency and **C**onsistency.
- Real-world systems often prioritize availability and low latency over strict consistency, though it depends heavily on use case.

---

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

## Week 2

### 1. Databases & Types

**SQL (Relational)**
- Data stored in tables with predefined schemas.
- Strong consistency and ACID transactions.
- Ideal for structured data and complex queries (joins).
- Examples: MySQL, PostgreSQL.

**NoSQL (Non-Relational)**
- Flexible schema, often horizontally scalable.
- Types:
    1. **Key-Value Stores** (Redis, Riak)
    2. **Document Stores** (MongoDB, CouchDB)
    3. **Column-Family Stores** (Cassandra, HBase)
    4. **Graph Databases** (Neo4j)

**Key Considerations**
- Use cases for each type (e.g., high write throughput → NoSQL, advanced joins → relational).
- Consistency vs. availability trade-offs.

---

### 2. Data Modeling

**Definition**
- The process of structuring and organizing data in a database to align with your application’s requirements.

**Principles**
- **Normalization** in relational databases (1NF, 2NF, 3NF, BCNF) vs. **Denormalization** in NoSQL for performance.
- **Entity-Relationship (ER) modeling**: Identifying entities, attributes, and relationships.
- **Schema design** in NoSQL often driven by the query patterns.

**Key Tips**
- Start with your access patterns (queries) and work backward to define your schema.
- Plan for how data will evolve (schema migrations, versioning).

---

### 3. Partitioning & Sharding

**Definition**
- **Partitioning / Sharding**: Splitting a large database or dataset into smaller, more manageable pieces (shards/partitions).
- Facilitates horizontal scaling and improved performance.

**Partitioning Methods**
1. **Range-based**: E.g., partition by ID or date range.
2. **Hash-based**: Use a hash function on an attribute (like user_id) to determine the shard.
3. **Directory/Lookup table**: Central table that maps an attribute to a shard.

**Key Considerations**
- **Data skew** or **hot partitions** can occur if partition keys are not chosen carefully.
- **Rebalancing** shards can be tricky.
- Sharding can introduce complexity in cross-shard queries and transactions.

---

### 4. Replication

**Definition**
- Storing copies of data on multiple nodes to improve availability and fault tolerance.

**Types**
1. **Master-Slave** (Leader-Follower): Writes go to the master, reads can be served by slaves.
2. **Master-Master**: Multiple nodes can accept writes, more complex conflict resolution.
3. **Peer-to-Peer** or **Multi-leader**: Similar to master-master, all nodes are equal, often for distributed databases.

**Benefits & Challenges**
- **High Availability**: If one node fails, others can serve data.
- **Read Scalability**: Multiple replicas can serve read traffic.
- **Data Consistency**: Changes must propagate to replicas (eventual vs. strong consistency).
- **Latency**: Replicas can be geographically distributed for lower latencies.

---

## Week 3

### 1. Networking Basics

**OSI Model** (Layer 1 to 7): Physical, Data Link, Network, Transport, Session, Presentation, Application.
- At a high level, system design focuses on Layer 4 (Transport — TCP/UDP) and Layer 7 (Application — HTTP, etc.).

**Common Protocols**
- **TCP**: Reliable, connection-based.
- **UDP**: Faster, connectionless, no guarantee of delivery.

---

### 2. REST (Representational State Transfer)

**Key Principles**
- **Stateless** client-server communication.
- **Uniform Interface** (HTTP verbs: GET, POST, PUT, DELETE).
- **Resource-based** URIs.
- **Layered System**: The client does not need to know if it is connected directly to the end server, or to an intermediary.
- **Cacheable**: Responses can be marked as cacheable or not.

**REST Best Practices**
- Use correct HTTP methods and status codes (200, 201, 400, 404, 500, etc.).
- Design resource URIs around nouns, not verbs.
- Handle pagination, filtering, and sorting for large datasets.

---

### 3. gRPC

**Definition**
- A high-performance, open-source remote procedure call (RPC) framework by Google.
- Uses **Protocol Buffers (protobuf)** for efficient serialization.
- Communicates over HTTP/2, providing features like streaming and bi-directional communication.

**Advantages**
- Lightweight and efficient (small message sizes, binary format).
- Built-in code generation for client/server stubs from `.proto` files.
- Native streaming for real-time updates.

**Use Cases**
- Internal microservice communication where low-latency and high throughput is required.
- Browser support is maturing (gRPC-Web).

---

### 4. GraphQL

**Definition**
- A query language for APIs and a server-side runtime for executing those queries.

**Key Features**
- **Single Endpoint**: Post queries to one endpoint rather than having multiple REST endpoints.
- **Client-driven Queries**: Clients can request exactly the data they need.
- **Schema & Types**: Strongly typed schema defines queries and types.
- **Reduces Overfetching/Underfetching**: Minimizes the inefficiencies of REST when requesting data.

**Considerations**
- **Caching** can be more complex due to flexible query patterns.
- Additional complexity in setting up the server and schema.
- Great for frontend teams that need multiple data slices from different backends.

---

### 5. DNS (Domain Name System)

**Definition**
- The “phonebook” of the internet, mapping domain names to IP addresses.

**Key Components**
- **DNS Resolver**: Resolves a domain name query into an IP address.
- **Root Servers**, **TLD Servers**, **Authoritative DNS** servers.
- **DNS Caching** improves performance by storing responses.

**Important Concepts**
- **TTL (Time to Live)**: How long a DNS record is cached.
- **Load balancing via DNS**: Using DNS round-robin or geolocation-based DNS.

---

### 6. Proxies

**Definition**
- An intermediary server that sits between a client and the main server.

**Types**
1. **Forward Proxy**: Used by clients to access the internet (e.g., corporate proxy).
2. **Reverse Proxy**: Placed in front of servers, used for load balancing, caching, SSL termination (e.g., NGINX, HAProxy).

**Benefits**
- **Security** (hide internal server details, firewall).
- **Caching**.
- **Load Balancing**.
- **SSL termination** (offloads SSL overhead to the proxy).

---

### 7. WebSockets & Long Polling

**WebSockets**
- Full-duplex communication channel over a single, long-lived TCP connection.
- Client and server can send messages to each other at any time (real-time apps).

**Long Polling**
- Client sends a request; server holds the connection until an event occurs or a timeout.
- Simulates real-time updates in environments that do not support WebSockets.

**Use Cases**
- Real-time applications (chat, gaming, collaborative editing).
- Notification services.

**Key Considerations**
- WebSockets require a persistent connection, slightly more complex to scale (sticky sessions or special routing).
- Long Polling can be easier to scale with standard HTTP infrastructure but less efficient.

---

## Week 4

### 1. Distributed Systems

**Definition**
- A system where components located on networked computers communicate and coordinate their actions by passing messages.

**Challenges**
- **Network Failures**: Partial failures are common.
- **Latency**: Geographically distributed nodes have variable network latency.
- **Consistency** vs. **Availability** trade-offs.
- **Clock Synchronization** is imperfect (e.g., NTP, logical clocks).

---

### 2. Consistency Models

**Strong Consistency**
- After an update completes, any subsequent access will return the updated value.
- E.g., typical RDBMS with ACID transactions.

**Eventual Consistency**
- Given enough time, all updates will propagate to all replicas, so eventually all will be consistent.
- Common in NoSQL systems (e.g., Dynamo, Cassandra).

**Weak / Causal / Read-after-write**
- Variations that define “when” an update should be visible and to whom (e.g., causality in concurrent updates).

---

### 3. Quorum

**Definition**
- A minimum number of nodes that must respond to consider an operation successful (for reads/writes).
- Example: In a system with **N** replicas, using quorum **R** for reads and **W** for writes ensures consistency if **R + W > N**.

**Formula**: Often you see configurations like \( R + W > N \).
- **Trade-off**: Larger W → safer writes but higher write latency; smaller R → faster reads but less assurance.

---

### 4. Leader-Follower (Master-Slave) Replication

**Definition**
- One node is designated the “leader” (or master) and is responsible for write operations.
- Other nodes follow (replicate changes from) the leader.

**Workflow**
1. Client sends write requests to the leader.
2. Leader updates its state and logs the change.
3. Followers pull or receive updates from the leader.
4. Clients can read from followers (eventual consistency) or from the leader (strong consistency).

**Pros & Cons**
- **Pros**: Simplified conflict resolution, easy to implement.
- **Cons**: Single point of failure if the leader goes down (unless leader election is implemented).

---

### 5. Merkle Tree

**Definition**
- A tree of hashes used to quickly compare large data sets for differences.

**Structure**
- Leaves contain hashes of individual data blocks.
- Parent nodes are hashes of their children.
- Root hash (Merkle root) represents the entire data set.

**Use Cases**
- **Data synchronization**: Quickly identify which parts of a data set differ between replicas.
- Common in **blockchain** (Bitcoin, etc.) to ensure data integrity.

---

### 6. Consistent Hashing

**Definition**
- A hashing technique that minimizes the number of keys that need to be remapped when a node is added or removed.

**How It Works**
- Hash ring: Nodes and data items are placed on a ring by hashing their IDs.
- A data item is stored on the first node encountered going clockwise.
- Adding a node or removing a node causes minimal re-distribution of items.

**Benefits**
- Great for horizontally scalable systems (e.g., distributed caches, partitioned data).
- Reduces re-hashing overhead compared to traditional hashing with mod operations.

---

## Week 5
1. B-Trees vs LSM Trees: https://lnkd.in/gvNmyeUK

2. Database Replication: https://lnkd.in/gCS8Ydbk

3. SQL vs NoSQL databases: https://lnkd.in/g6ACc2hV

4. Consistent Hashing: https://lnkd.in/gHHrnahN

5. Cache Writing Policies: https://lnkd.in/gYJ5shjK

6. Cache Eviction Policies: https://lnkd.in/ggeNgDCd

7. Content Delivery Network (CDN): https://lnkd.in/g9YBHC4s

8. Batch Processing vs Stream Processing: https://lnkd.in/g36_q-S8

9. Long Polling vs WebSockets vs Server-Sent Events: https://lnkd.in/gkzXuKrJ

10. Search Index and ElasticSearch: https://lnkd.in/gW4FUuru







RTSP & GStreamer Architecture for Processing Video & Audio Streams for LLM Insights (1000 Cameras)
To ingest, process, and analyze video/audio streams from 1000 RTSP cameras, we need a scalable GStreamer pipeline with efficient storage, processing, and LLM integration for real-time insights.

🟢 High-Level Architecture
RTSP Ingestion: Capture live video/audio streams from 1000 cameras.
Preprocessing & Transcoding: Convert and process video/audio for analysis.
Edge Processing: Use FFmpeg/GStreamer for lightweight transformations.
Message Queue (Kafka/NATS): Buffer frames and send them to consumers.
LLM Analysis: Extract insights via OCR, ASR, Object Detection, and other AI models.
Storage & Retrieval: Store processed data (frames, embeddings) in a vector database.
Visualization & API Layer: Provide an API for querying insights.


---


# Yes, Cassandra is optimized for **high write throughput** due to its **distributed partitioned storage model**. Here’s why:

### **1. Writes are Append-Only and Sequential**
- Unlike traditional databases that perform **random disk I/O**, Cassandra **writes data sequentially** to the commit log and **MemTable** before flushing to SSTables.
- This means **writes don’t require disk seeks**, making them **fast and efficient**.

### **2. Partition-Based Storage**
- Data in Cassandra is **partitioned based on a partition key**.
- Each partition is stored on specific nodes (based on the consistent hashing algorithm).
- Writes are **spread across multiple nodes**, reducing contention and improving parallelism.

### **3. No Read-before-Write Overhead**
- Unlike some databases that require checking for existing data before updating, Cassandra simply **appends new data**.
- This avoids the performance hit of read-before-write operations.

### **4. Writes are Distributed Across Nodes**
- Since Cassandra is **distributed**, a single write request is **replicated across multiple nodes** asynchronously.
- This ensures **high availability and fault tolerance** while allowing **concurrent high-volume writes**.

### **5. Tunable Consistency**
- Cassandra allows configuring **write consistency levels** (e.g., `ONE`, `QUORUM`, `ALL`).
- Lower consistency levels (e.g., `ONE`) ensure **faster writes** by reducing the number of acknowledgments needed.

### **6. Background Compaction (Not Immediate Updates)**
- Data is written to immutable SSTables.
- Old SSTables are **compacted in the background**, reducing the need for in-place updates.

### **Conclusion**
Yes, Cassandra is **write-optimized** due to its **append-only, partitioned, and distributed nature**. However, this design also makes **reads more complex** due to potential multiple SSTable lookups and compactions.

# CQRS Pattern
- Implemented in Architectural Patterns

# SAGA Pattern using Kafka
- Implemented in Architectural Patterns

# **Q: Transactional Outbox Pattern vs. Choreography-Based Saga for Scalable Systems**
- implemented in spring notes

# **Event Sourcing** :

# **Circuit Breaker** :
- implemented in spring notes


# High-Level Architectural flows

## ✅ **Model 1 (Standard Microservices Flow)**

```text
Users → API Gateway → Auth → Microservices → DBs / MQs
```

### 👍 Pros:
- **Simple and synchronous**
- Best for **CRUD operations** or quick responses (e.g., login, profile fetch)
- **Easy to debug** and trace
- Well-suited for low-latency, small-scale use cases

### 👎 Cons:
- Can **slow down under heavy load**
- Doesn’t scale well with **heavy computation or I/O**
- User request is **blocked** until processing completes
- Any service crash can disrupt user experience

---

## ✅✅ **Model 2 (Async Event-Driven with Kafka + WebSocket/Polling)**

```text
Users → API Gateway → Auth → Consumer → Kafka Queue → Microservices → DBs/MQs → Return status via WebSocket or Polling
```

### 👍 Pros:
- **Highly scalable and resilient**
- Decouples request-receive flow from processing
- **Non-blocking**: User gets an immediate response (like "Request Received") without waiting for full processing
- Ideal for:
    - High-traffic **booking systems**
    - **Payment processing**
    - Analytics events (e.g., likes, views, purchases)
    - **Rate-limited operations**
- Supports **retry, delay queues**, and **failure recovery** via Kafka

### 🔁 Real-time Feedback:
- You can:
    - Use **WebSockets/SSE** to push booking status
    - Or fallback to **polling** every 5 seconds

### 👎 Cons:
- Slightly more complex to implement (WebSocket infra, message ID tracking)
- Must ensure **exactly-once processing** or **idempotency**
- Monitoring and debugging is trickier (needs tracing across Kafka)

---


# **Common Problems in Microservices and Distributed Systems**

## **1. Distributed Data Management Problems**
### **1.1 Dual Write Problem**
- **Issue**: Writing data to multiple systems (e.g., DB + Kafka) without atomicity causes **inconsistencies**.
- **Solution**: **Transactional Outbox Pattern**, **Saga Pattern**, **2PC (not recommended for scalability)**.

### **1.2 Data Consistency (Eventual vs Strong)**
- **Issue**: In distributed databases, changes may take time to propagate, leading to **stale data issues**.
- **Solution**: **CQRS**, **Event Sourcing**, **Saga**, **Database Replication Strategies**.

### **1.3 Data Synchronization Across Microservices**
- **Issue**: Services maintain their own databases, but keeping data in sync is hard.
- **Solution**: **Change Data Capture (CDC)**, **Event-Driven Architecture**, **Outbox Pattern**.

### **1.4 Distributed Transactions Problem**
- **Issue**: No global transaction manager across microservices.
- **Solution**: **Saga Pattern** (Choreography or Orchestration), **Compensating Transactions**.

---

## **2. Network & Communication Problems**
### **2.1 Latency & Performance Issues**
- **Issue**: Remote calls between services increase latency.
- **Solution**: **Caching (Redis, Memcached)**, **gRPC over REST**, **Async Messaging (Kafka, RabbitMQ)**.

### **2.2 Partial Failures**
- **Issue**: If one service fails, it can block others.
- **Solution**: **Circuit Breaker (Resilience4j, Hystrix)**, **Retries with Exponential Backoff**.

### **2.3 Request Amplification Problem**
- **Issue**: One request to a service triggers many downstream calls, increasing load.
- **Solution**: **API Gateway Aggregation**, **GraphQL instead of REST**, **CQRS**.

### **2.4 Rate Limiting & Throttling**
- **Issue**: High traffic can overload a microservice.
- **Solution**: **API Gateway (Rate Limiting)**, **Token Buckets**, **Leaky Bucket Algorithm**.

### **2.5 Service Discovery & Load Balancing**
- **Issue**: Microservices should know where other services are running.
- **Solution**: **Service Discovery (Eureka, Consul, etcd)**, **Load Balancers (NGINX, HAProxy)**.

---

## **3. Fault Tolerance & Reliability**
### **3.1 Distributed System Failures (CAP Theorem)**
- **Issue**: Cannot achieve **Consistency, Availability, and Partition Tolerance** at the same time.
- **Solution**: **Choose trade-offs based on requirements (e.g., CP vs AP systems)**.

### **3.2 Circuit Breaker Failure**
- **Issue**: If a service keeps failing, it can cause cascading failures.
- **Solution**: **Circuit Breaker Pattern** (Resilience4j, Hystrix).

### **3.3 Retries & Exponential Backoff**
- **Issue**: Blind retries can overload a system.
- **Solution**: **Use retry libraries with backoff strategy (Jitter, Exponential Backoff)**.

---

## **4. Event-Driven Architecture Issues**
### **4.1 Eventual Consistency Problems**
- **Issue**: Events arrive at different times, causing inconsistencies.
- **Solution**: **Event Sourcing**, **Idempotent Consumers**, **Compensating Transactions**.

### **4.2 Duplicate Message Processing**
- **Issue**: At-least-once delivery guarantees may cause duplicate event processing.
- **Solution**: **Idempotent Consumers**, **Deduplication via Unique Event ID**.

### **4.3 Out-of-Order Events**
- **Issue**: Events may be processed in the wrong sequence.
- **Solution**: **Event Versioning**, **Kafka Consumer Offsets**, **Watermarking**.

---

## **5. Security Challenges**
### **5.1 Authentication & Authorization**
- **Issue**: Managing user access across services.
- **Solution**: **OAuth2, OpenID Connect, JWT**, **API Gateway Authentication**.

### **5.2 API Security**
- **Issue**: Microservices expose APIs that can be attacked.
- **Solution**: **WAF (Web Application Firewall), API Gateway Security, Rate Limiting**.

### **5.3 Data Privacy & Compliance**
- **Issue**: Ensuring GDPR, HIPAA compliance.
- **Solution**: **Data encryption, masking, secure logging**.

---

## **6. Observability & Monitoring**
### **6.1 Distributed Logging**
- **Issue**: Logs are scattered across services.
- **Solution**: **Centralized Logging (ELK Stack, Fluentd, Loki, Graylog)**.

### **6.2 Distributed Tracing**
- **Issue**: Tracking requests across services is hard.
- **Solution**: **OpenTelemetry, Jaeger, Zipkin**.

### **6.3 Metrics & Monitoring**
- **Issue**: Lack of visibility into system health.
- **Solution**: **Prometheus + Grafana, CloudWatch, Datadog, New Relic**.

---

## **7. Deployment & DevOps Issues**
### **7.1 Configuration Management**
- **Issue**: Each microservice has its own configuration.
- **Solution**: **Spring Cloud Config, Consul, etcd**.

### **7.2 Blue-Green & Canary Deployments**
- **Issue**: Deploying new versions can cause downtime.
- **Solution**: **Kubernetes Rolling Updates, Canary Deployments**.

### **7.3 Database Migrations**
- **Issue**: Schema changes in microservices are hard.
- **Solution**: **Liquibase, Flyway (DB versioning tools)**.

---

## **8. Scalability & Resource Optimization**
### **8.1 Auto-scaling & Load Balancing**
- **Issue**: Scaling services dynamically.
- **Solution**: **Kubernetes HPA, AWS Auto Scaling, Load Balancers**.

### **8.2 Cold Start Problem**
- **Issue**: Serverless functions (AWS Lambda) take time to initialize.
- **Solution**: **Provisioned Concurrency, Warm Start Techniques**.


### **C. Optimizing Writes (2GB/sec)**
Handling **2GB/sec writes** requires **high-speed ingestion mechanisms**.

#### **1. Parallel Writes with Partitioned Storage**
- **Kafka, Pulsar, or Kinesis for Write Buffering**
    - Stream writes to a distributed queue **(Kafka, Kinesis, Pulsar)**.
    - Consumers pick up data **asynchronously** to write into databases/storage.
- **Partition Writes by User, Time, or Entity**
    - Instead of writing all to one place, **split writes across multiple nodes**.
    - Example: **20 partitions, each handling 100MB/sec**.

#### **2. Database Write Optimization**
- **Sharded Databases**
    - Split the database into multiple shards based on **user ID, product ID, etc.**.
    - Example: If **one node handles 100MB/sec**, then **20 nodes → 2GB/sec**.
- **Bulk Inserts Instead of Single Inserts**
    - Instead of writing every request immediately, **batch writes** together.
- **Log-Structured Storage (LSM-Trees)**
    - Use databases like **Apache Cassandra, RocksDB**, which are optimized for high write speeds.
- **NoSQL for High-Speed Writes (Cassandra, DynamoDB)**
    - NoSQL databases allow **highly parallel writes** without transactional overhead.

---

### **D. Storage Strategy**
- **Cold Storage (S3, HDFS) for Historical Data**
    - If you don’t need real-time access, archive data in **S3, HDFS, or Glacier**.
- **SSD/NVMe for Hot Data**
    - Store frequently accessed data on **high-speed SSDs**.
- **Hybrid Approach**
    - **Recent Data → Redis, Kafka, Cassandra**
    - **Older Data → S3, HDFS, BigQuery**

---

### **E. Network & Infrastructure Scaling**
- **High-Performance Networking**
    - Use **100Gbps network** or higher.
    - Load balance using **gRPC or HTTP/2** for efficient communication.
- **Horizontal Scaling**
    - Instead of upgrading servers, **scale out** by adding more instances.
- **Autoscaling**
    - Use **Kubernetes or AWS Auto Scaling** to dynamically adjust capacity.

---

---

## **4. Scaling for Future Growth**
- **Start with smaller scale → gradually add nodes as traffic increases.**
- **Implement Observability (Prometheus, Grafana, ELK) to monitor performance.**
- **Introduce Failover Mechanisms to prevent downtime.**

---

## **Final Thoughts**
- **Reads (50GB/s)** → **Caching (Redis, CDN), Sharded DBs, Read Replicas**.
- **Writes (2GB/s)** → **Kafka buffering, Partitioning, NoSQL (Cassandra, DynamoDB)**.
- **Storage** → **Cold Storage for historical data, SSDs for hot data**.
- **Scale-out instead of Scale-up** → Add **more distributed nodes** instead of upgrading single instances.



### **Fundamentals of Scalability**
1. **Scalability** – The ability of a system to handle increased load by adding resources (vertical scaling or horizontal scaling).
    - **Use case:** Handling growing user traffic in web applications.

2. **Performance vs. Scalability** – Performance refers to how fast a system is, while scalability refers to how well a system grows to handle increased demand.
    - **Use case:** A well-performing system may not scale well if it has a single-threaded bottleneck.

3. **Latency vs. Throughput**
    - **Latency:** The time taken to process a request.
    - **Throughput:** The number of requests processed per unit time.
    - **Use case:** Video streaming services optimize both to provide smooth playback.

---

### **Consistency, Availability, and Partition Tolerance**
4. **CAP Theorem** – A distributed system can only achieve two of three: Consistency, Availability, and Partition Tolerance.
    - **Use case:** Databases like Cassandra prioritize AP, while MongoDB can be CP.

5. **Consistency Patterns**
    - **Weak Consistency:** Updates might not be immediately visible.
    - **Eventual Consistency:** Data becomes consistent over time (e.g., DNS propagation).
    - **Strong Consistency:** Guarantees immediate visibility of updates.
    - **Use case:** Banking transactions require strong consistency.

6. **Availability Patterns**
    - **Failover:** Switching to a standby server if the primary fails.
    - **Replication:** Keeping multiple copies of data for redundancy.
    - **Use case:** Cloud services use replication to prevent data loss.

---

### **Scaling and Networking**
7. **Domain Name System (DNS)** – Translates domain names to IP addresses.
    - **Use case:** Global website accessibility.

8. **Content Delivery Network (CDN)** – Distributes cached content across geographically dispersed servers.
    - **Use case:** Accelerating web page load times.

9. **Load Balancer**
    - **Active-Passive:** One instance is active; another is standby.
    - **Active-Active:** All instances handle requests simultaneously.
    - **Layer 4 vs. Layer 7:** Layer 4 balances based on IP/port, Layer 7 on HTTP headers.
    - **Use case:** Distributing incoming requests across multiple servers.

10. **Reverse Proxy** – Sits in front of web servers to handle requests, improving security and caching.
    - **Use case:** Nginx acting as a reverse proxy for API requests.

11. **Proxy server** – Hides users IP

---

### **Databases**
11. **RDBMS vs. NoSQL**
    - **RDBMS:** Structured schema, ACID compliance (e.g., MySQL, PostgreSQL).
    - **NoSQL:** Flexible schema, high scalability (e.g., MongoDB, Cassandra).
    - **Use case:** Banking systems prefer RDBMS; social networks use NoSQL.

12. **Replication Strategies**
    - **Master-Slave:** Read replicas improve read performance.
    - **Master-Master:** Both nodes can handle writes.
    - **Sharding:** Splitting data across multiple databases.
    - **Use case:** Facebook uses sharding for user data.

---

### **Caching**
13. **Types of Caching**
    - **Client Caching:** Browser storing responses.
    - **CDN Caching:** Edge servers caching static files.
    - **Database Caching:** Caching query results (e.g., Redis, Memcached).
    - **Application Caching:** Storing frequently accessed data in memory.
    - **Use case:** E-commerce sites cache product pages to reduce load.

14. **Cache Invalidation Strategies**
    - **Cache-aside:** App checks cache before hitting DB.
    - **Write-through:** Cache updated on every write.
    - **Write-behind:** Writes happen asynchronously.
    - **Refresh-ahead:** Cache preloads anticipated data.
    - **Use case:** Write-through caching is used in financial transactions.

---

### **Asynchronous Processing**
15. **Message Queues** – Stores messages for asynchronous processing (e.g., Kafka, RabbitMQ).
    - **Use case:** Order processing in e-commerce.

16. **Task Queues** – Manages background tasks (e.g., Celery, AWS SQS).
    - **Use case:** Sending emails asynchronously.

17. **Back Pressure** – Throttling system load when overwhelmed.
    - **Use case:** API rate limiting.

---

### **Communication Protocols**
18. **TCP vs. UDP**
    - **TCP:** Reliable, ordered delivery (e.g., HTTP, file transfer).
    - **UDP:** Faster, no reliability guarantee (e.g., video streaming, gaming).

19. **RPC vs. REST**
    - **RPC:** Remote function calls over a network (e.g., gRPC).
    - **REST:** Uses HTTP methods (GET, POST) and is stateless.
    - **Use case:** Microservices often use REST or gRPC.

---

### **Security Considerations**
20. **Common security measures**
    - **Encryption:** Data protection (e.g., HTTPS, SSL/TSL/RSA).
    - **Authentication:** Verifying users (e.g., OAuth, JWT).
    - **Authorization:** Defining access permissions.
    - **Use case:** OAuth for third-party app logins.

21. **Data security measures**
    - data encryption Bcrypt
    - hashing -> MD5,
    - base64 encoding

---


*  Failover Mechanisms: Ensuring High Availability : Failover mechanisms automatically switch to a backup system when a primary system fails, ensuring high availability and minimal downtime.
*  Redundancy ≠ Fault Tolerance (Redundancy is a prerequisite for fault tolerance).
*  Redundancy alone is not enough—automated failover is required for fault tolerance.
*  For mission-critical systems, high availability (HA) requires both.





# Importatnt Questions

### there are third party db service to do automically switch db based on  sharding
### write own configs and service to switch db service to do automically switch db based on  sharding
### write own configs and service to switch db service to do automically switch db based on  multitenet
### how same amazon users switches location and search products for same account link india and USA?

### **Scalability and Performance**
1. What is the difference between **scalability** and **performance**?
2. How would you design a system to handle **1 million requests per second**?
3. What are **bottlenecks** in a scalable system, and how do you address them?
4. How does **horizontal scaling** differ from **vertical scaling**?
5. What are some ways to optimize **database queries** for performance?
6. How does **load balancing** improve scalability? and what is load balancer is down

---

### **Latency vs. Throughput**
7. What’s the difference between **latency** and **throughput**?
8. How do you reduce **latency** in a high-traffic application?
9. How does a **CDN** help improve latency? and aws cloud-front handles user requests?
10. When would you choose **batch processing** vs. **real-time processing**?

---

### **Availability & Consistency (CAP Theorem)**
11. Explain the **CAP theorem** and its implications in distributed systems.
12. Can a system be **CA (Consistency & Availability) but not Partition Tolerant**? Why or why not?
13. When would you choose a **CP (Consistency-Partition Tolerant) system** over an **AP (Availability-Partition Tolerant) system**?
14. How does **eventual consistency** work in distributed databases?
15. What strategies can improve a system’s **availability**?
16. Explain the concept of **failover** and how it is implemented.
17. How do you handle **network partitions** in a distributed system?

---

### **Load Balancing & Traffic Management**
18. How does a **load balancer** work?
19. What’s the difference between **Layer 4** and **Layer 7 load balancing**?
20. What’s the difference between **active-passive** and **active-active** load balancing?
21. How would you implement **global load balancing** across multiple regions?
22. How does a **reverse proxy** differ from a load balancer?
23. Explain the concept of **sticky sessions** in load balancing.
24. How would you handle **server failures** in a load-balanced system?

---

### **Database Scaling & Design**
25. When would you choose a **relational database** vs. a **NoSQL database**?
26. How does **sharding** improve database performance?
26. How does **partitioning** improve database performance?
27. What are the downsides of **sharding**?
28. Explain **master-slave replication** and **master-master replication**.
29. How does **database partitioning** work?
30. What’s the tradeoff between **denormalization** and **data consistency**?
31. How would you design a database for **a high-write workload**?
32. How would you design a **time-series database**?

---

### **Caching Strategies**
33. What are the different types of **caching**?
34. What is the difference between **cache-aside** and **write-through** caching?
35. How does **write-behind caching** work?
36. What’s the risk of **cache inconsistency**, and how do you handle it?
37. What are **LRU, LFU, and TTL** in caching?
38. How does **CDN caching** work?
39. When would you use **distributed caching** (e.g., Redis, Memcached)?
40. How would you decide **what data to cache**?

---

### **Messaging & Asynchronous Processing**
41. What’s the difference between a **message queue** and a **task queue**?
42. How does **Kafka** compare to **RabbitMQ**?
43. How would you implement **event-driven architecture**?
44. What is **back pressure**, and how do you handle it in a system?
45. How would you process **millions of messages per second** in a queue?
46. What are **at-least-once**, **at-most-once**, and **exactly-once** message delivery semantics?
47. How do you ensure **message ordering** in a distributed system?

---

### **Networking & Communication Protocols**
48. What’s the difference between **TCP and UDP**?
49. When would you choose **gRPC** over **REST**?
50. How does **HTTP/2** improve upon **HTTP/1.1**?
51. How would you implement **real-time communication** (e.g., WebSockets, SSE)?
52. How do you reduce **network latency** in a microservices architecture?

---

### **Security & Authentication**
53. What is **OAuth2**, and how does it work?
54. How do **JWTs** compare to **session-based authentication**?
55. How would you secure **REST APIs**?
56. What are **SQL Injection** and **Cross-Site Scripting (XSS)**, and how do you prevent them?
57. How would you handle **DDoS attacks**?
58. How does **rate limiting** work in APIs?

---

### **Microservices & Service Discovery**
59. What are the **benefits and challenges of microservices**?
60. How do you implement **service discovery** in microservices?
61. What’s the difference between **synchronous and asynchronous communication** in microservices?
62. How would you design a **resilient microservices architecture**?
63. How do you handle **data consistency** in a microservices setup?
64. What is the **Saga pattern**, and how is it used?
65. How do you manage **distributed transactions** in microservices?

---

### 𝗞𝗮𝗳𝗸𝗮 𝗜𝗻𝘁𝗲𝗿𝘃𝗶𝗲𝘄 𝗤𝘂𝗲𝘀𝘁𝗶𝗼𝗻𝘀:
    - Basic Level:
        1. What is Apache Kafka, and what are its core components?
        2. Explain the difference between a topic, partition, and segment.
        3. How does Kafka ensure message ordering?
        4. What is a consumer group in Kafka?
    - Ktables and kafka streams
        examplain in details 

    - Intermediate Level:
        5. How does Kafka achieve fault tolerance?
        6. Explain Kafka's partitioning strategy and how it impacts performance.
        7. Describe Kafka's consumer offset management.

    - Advanced Level:
        8. Explain the concept of exactly-once semantics (EOS) in Kafka.
        9. How would you monitor and optimize Kafka performance in a production environment?
        10. How would you design a Kafka-based system to guarantee data consistency in the event of node failures?

### questions on multi-tenant
✔ Database-per-tenant offers better isolation but increases complexity.
✔ Table-per-tenant reduces overhead but requires dynamic query handling.
✔ Use Spring Boot with Multi-Tenancy Filters to route database connections dynamically.




### **LSM Tree vs. B-Tree: A Comparative Analysis**
Log-Structured Merge (LSM) Trees and B-Trees are two **fundamentally different data structures** used in databases and storage engines. They optimize for different workloads:

- **LSM Trees** → Optimized for **high write throughput**.
- **B-Trees** → Optimized for **fast reads and indexed lookups**.

---

## **1. What is a B-Tree?**
A **B-Tree** is a **self-balancing tree** data structure where:
- Data is **stored in nodes**, and each node contains a sorted list of keys.
- Reads, writes, and updates are **O(log N)** due to balanced branching.
- Updates occur **in place**, meaning disk I/O is frequent.

### **B-Tree Characteristics**
✅ **Efficient for Random Reads** → Direct key lookups are fast.  
✅ **Good for Mixed Workloads** → Supports both reads and writes efficiently.  
❌ **Write Amplification** → Small updates require modifying multiple disk pages.  
❌ **Expensive Disk Seeks** → As data grows, maintaining balance requires disk I/O.

**Example Uses**: Traditional relational databases like **MySQL (InnoDB), PostgreSQL**.

---

## **2. What is an LSM Tree?**
A **Log-Structured Merge Tree (LSM Tree)** is designed for **high write performance** by:
- **Buffering writes in memory (MemTable)** instead of modifying disk pages directly.
- Periodically **flushing data to immutable SSTables** on disk.
- **Merging SSTables via compaction** to remove obsolete data.

### **LSM Tree Characteristics**
✅ **Write-Optimized** → Writes are sequential and batched.  
✅ **Reduced Disk Writes** → No in-place updates, reducing random I/O.  
✅ **Better Storage Utilization** → Old/deleted data is removed via compaction.  
❌ **Slower Reads** → Data may be spread across multiple SSTables.  
❌ **Compaction Overhead** → Periodic merges consume CPU and I/O.

**Example Uses**: NoSQL databases like **Cassandra, LevelDB, RocksDB, HBase**.

---

## **3. Comparison Table: LSM Tree vs. B-Tree**

| Feature | **B-Tree** | **LSM Tree** |
|---------|-----------|-------------|
| **Write Performance** | Slower (random disk writes) | Faster (append-only writes) |
| **Read Performance** | Faster (single lookup) | Slower (multiple SSTables) |
| **Update Overhead** | In-place updates (high I/O) | New writes, old data compacted |
| **Disk Usage** | Efficient | Can be higher due to compaction |
| **Latency** | Low for reads, moderate for writes | Low for writes, higher for reads |
| **Best For** | OLTP, indexed lookups | High write workloads, logs, time-series |

---

## **4. When to Use Which?**
- **Use B-Trees** if you need **fast indexed lookups and balanced read-write performance** (e.g., MySQL, PostgreSQL).
- **Use LSM Trees** if your workload is **write-heavy** (e.g., logging, event storage, NoSQL databases like Cassandra).

---
