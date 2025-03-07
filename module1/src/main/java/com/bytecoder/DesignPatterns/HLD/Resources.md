
Handling **high throughput** (e.g., **50GB/sec read, 2GB/sec write**) requires a **scalable, distributed architecture** that optimizes data flow, storage, and processing efficiency. Below is a **detailed approach** to designing such a system.

---

## **1. Challenges in Handling High Throughput**
- **Network Bottlenecks** → Efficient data transfer needed.
- **Storage IOPS Limitations** → Need high-speed distributed storage.
- **Database Scalability** → Reads/Writes need to be optimized across multiple nodes.
- **Processing Latency** → Efficient caching and indexing required.

---

## **2. Architecture Design for High Throughput**
### **A. Load Balancing & Traffic Distribution**
- **API Gateway + Load Balancer (Nginx, AWS ALB, HAProxy)**
    - Distributes load across multiple backend instances.
    - Can implement **rate limiting** and **throttling**.

- **Shard Requests by User or Data Type**
    - Example: If you have **multi-region** users, direct them to the nearest data center.

---

### **B. Optimizing Reads (50GB/sec)**
Handling **50GB/s reads** efficiently requires a combination of **caching, distributed storage, and parallel processing**.

#### **1. Use Multiple Caching Layers**
- **Edge Caching (CDN like Cloudflare, Akamai)**
    - Cache frequently accessed data at the nearest location.
    - Reduces read load on backend servers.
- **In-Memory Caching (Redis, Memcached)**
    - For hot datasets, keep data in **RAM** instead of querying databases.
    - Can be used as **read-through cache** or **write-back cache**.
- **Application-Level Caching**
    - Example: Store **user session** data or frequently queried metadata in **local memory**.

#### **2. Optimize Database Reads**
- **Database Replication (Read Replicas)**
    - Distribute read queries across multiple replicas.
    - Example: **10 replicas each handling 5GB/sec → 50GB/sec throughput**.
- **Sharding / Partitioning**
    - Horizontal sharding by **user ID, region, or timestamp**.
    - Example: Split **data across 50 nodes**, each handling **1GB/sec**.
- **Columnar Databases for Analytics (ClickHouse, Apache Druid)**
    - If doing analytical queries, **use columnar storage** for high-speed aggregation.
- **Precomputed Data Aggregation**
    - If users request aggregated metrics, store precomputed results in **Redis** or **BigQuery**.

#### **3. Asynchronous Read Strategies**
- **Batch Processing with Kafka**
    - Instead of querying 50GB/sec, **stream data via Kafka consumers**.
    - Process data **in micro-batches** and send results back to users.

---

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

## **3. Example Technology Stack**
| **Layer**         | **Technology Choices** |
|-------------------|----------------------|
| **Load Balancer** | AWS ALB, Nginx, HAProxy |
| **Queue** | Kafka, Pulsar, Kinesis |
| **Cache** | Redis, Memcached, CDN |
| **Database (Read)** | PostgreSQL with Read Replicas, ClickHouse |
| **Database (Write)** | Apache Cassandra, DynamoDB, RocksDB |
| **Storage** | S3, HDFS, BigQuery |
| **Processing** | Apache Flink, Spark Streaming |

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



















## Additional Interview Preparation Tips
https://www.linkedin.com/posts/chiraggoswami23_api-webdevelopment-restapi-activity-7300329959120678912-TM1a?utm_source=share&utm_medium=member_desktop&rcm=ACoAABzwIGYBEJI2ZATOVuAxZmBa6zuXW26zQMc
https://www.linkedin.com/posts/chiraggoswami23_cybersecurity-2fa-mfa-activity-7299737336978755584-YyiO?utm_source=share&utm_medium=member_desktop&rcm=ACoAABzwIGYBEJI2ZATOVuAxZmBa6zuXW26zQMc
https://www.linkedin.com/posts/chiraggoswami23_sso-cybersecurity-passwordmanagement-activity-7299242817996918785-6sMK?utm_source=share&utm_medium=member_desktop&rcm=ACoAABzwIGYBEJI2ZATOVuAxZmBa6zuXW26zQMc
https://www.linkedin.com/posts/chiraggoswami23_ssl-tls-websecurity-activity-7297925401027194880-QO_4?utm_source=share&utm_medium=member_desktop&rcm=ACoAABzwIGYBEJI2ZATOVuAxZmBa6zuXW26zQMc

1. **Practice Whiteboarding**
    - Be ready to draw architectures involving these components (load balancers, caches, databases, microservices, etc.).

2. **Explain Trade-offs**
    - Interviewers focus on “why” you pick one approach over another.
    - Mention latency, throughput, cost, and operational complexity trade-offs.

3. **Use Real-World Examples**
    - For example, how to design a URL shortener, chat application, or Instagram-like system.
    - Incorporate the concepts you’ve learned: sharding, replication, consistent hashing, caching, etc.

4. **Ask Clarifying Questions**
    - System design interviews are interactive. Clarify requirements (read/write patterns, latencies, data volumes, etc.) before diving into solutions.

5. **Stay Organized**
    - Structure your answers: **Requirements → High-Level Design → Deep Dive on Key Components → Bottlenecks & Trade-offs → Future Improvements**.

   - functional and non-functional
   - must to have vs good to have (additional )

✅ Improve Scalability – Handle more users & data easily.
✅ Enhance Reliability – Prevent system failures.
✅ Optimize Performance – Ensure fast responses.
✅ Simplify Maintenance – Make updates & debugging easier.
✅ High throughput – .
✅ Low latency – .
✅ Fault Tolerant  – .

---

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





## Baiscs




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









* https://github.com/donnemartin/system-design-primer
* https://github.com/iluwatar/java-design-patterns/tree/master


Amazon

* https://www.amazon.jobs/en/software-development-interview-prep?%20INTCMPID=OAAJAZ100026B%23/lessons/i42fEs1z6xTY6sORn8uAxp5Wz7iXE5Ck%20#/lessons/fxggI6Y3AxoOjvF9oKV_gky-TSFACjCu
* https://www.linkedin.com/pulse/how-interview-amazon-leadership-david-anderson/
* https://www.techinterviewhandbook.org/grind75/
* https://www.amazon.jobs/en/software-development-interview-prep?cmpid=ECOTOT700005B#/lessons/r2ZOm8qwBm3sSRv2xd8Q7K3BA2qIzjPX
* https://www.amazon.jobs/en/software-development-interview-prep?cmpid=ECOTOT700005B#/lessons/jmmGonbjV2OJp6iV-UMEoqPB6_3_Y33O

* https://www.youtube.com/watch?v=gNQ9-kgyHfo


* https://www.interviewhelp.io/blog/posts/amazon-system-design-interview-questions/
* https://github.com/donnemartin/system-design-primer/tree/master/solutions/system_design/pastebin

* https://www.youtube.com/channel/UC9vLsnF6QPYuH51njmIooCQ
* https://www.educative.io/
* https://github.com/tssovi/grokking-the-object-oriented-design-interview/blob/master/object-oriented-design-case-studies/design-cricinfo.md
* https://www.amazon.com/System-Design-Interview-insiders-Second/dp/B08CMF2CQF/ref=tmm_pap_swatch_0?_encoding=UTF8&sr=
* https://www.amazon.com/dp/1736049119/ref=tsm_1_fb_lk
* https://www.amazon.com/Cracking-Coding-Interview-Programming-Questions/dp/0984782850/ref=sr_1_3?dchild=1&keywords=cracking+coding+interview&qid=1623709878&sr=8-3
* https://www.pramp.com/#/

* https://www.digitalocean.com/community/conceptual-articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design
* https://www.interviewcake.com/
* https://interviewing.io/
