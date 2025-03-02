
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
      - 
18. 𝗞𝗮𝗳𝗸𝗮 𝗜𝗻𝘁𝗲𝗿𝘃𝗶𝗲𝘄 𝗤𝘂𝗲𝘀𝘁𝗶𝗼𝗻𝘀:
    - Basic Level:
      1. What is Apache Kafka, and what are its core components?
      2. Explain the difference between a topic, partition, and segment.
      3. How does Kafka ensure message ordering?
      4. What is a consumer group in Kafka?

    - Intermediate Level:
       5. How does Kafka achieve fault tolerance?
       6. Explain Kafka's partitioning strategy and how it impacts performance.
       7. Describe Kafka's consumer offset management.

    - Advanced Level:
       8. Explain the concept of exactly-once semantics (EOS) in Kafka.
       9. How would you monitor and optimize Kafka performance in a production environment?
       10. How would you design a Kafka-based system to guarantee data consistency in the event of node failures?
    



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
    - **Encryption:** Data protection (e.g., HTTPS).
    - **Authentication:** Verifying users (e.g., OAuth, JWT).
    - **Authorization:** Defining access permissions.
    - **Use case:** OAuth for third-party app logins.

---




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



Here’s a comprehensive list of **System Design Interview Questions** for a **Senior Backend Software Engineer**, categorized by topic:

---

### **Scalability and Performance**
1. What is the difference between **scalability** and **performance**?
2. How would you design a system to handle **1 million requests per second**?
3. What are **bottlenecks** in a scalable system, and how do you address them?
4. How does **horizontal scaling** differ from **vertical scaling**?
5. What are some ways to optimize **database queries** for performance?
6. How does **load balancing** improve scalability?

---

### **Latency vs. Throughput**
7. What’s the difference between **latency** and **throughput**?
8. How do you reduce **latency** in a high-traffic application?
9. How does a **CDN** help improve latency?
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

### **Real-World System Design Questions**
66. How would you design a **URL shortener like Bitly**?
70. How would you design a **distributed file storage system like Google Drive**?
71. How would you design a **highly available payment processing system**?
72. How would you design a **rate-limiting system for an API**?
73. How would you design a **Anki-Flashcard system for an API**?

---
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


https://github.com/donnemartin/system-design-primer
https://github.com/iluwatar/java-design-patterns/tree/master


Amazon

https://www.amazon.jobs/en/software-development-interview-prep?%20INTCMPID=OAAJAZ100026B%23/lessons/i42fEs1z6xTY6sORn8uAxp5Wz7iXE5Ck%20#/lessons/fxggI6Y3AxoOjvF9oKV_gky-TSFACjCu
https://www.linkedin.com/pulse/how-interview-amazon-leadership-david-anderson/
https://www.techinterviewhandbook.org/grind75/
https://www.amazon.jobs/en/software-development-interview-prep?cmpid=ECOTOT700005B#/lessons/r2ZOm8qwBm3sSRv2xd8Q7K3BA2qIzjPX
https://www.amazon.jobs/en/software-development-interview-prep?cmpid=ECOTOT700005B#/lessons/jmmGonbjV2OJp6iV-UMEoqPB6_3_Y33O

https://www.youtube.com/watch?v=gNQ9-kgyHfo


https://www.interviewhelp.io/blog/posts/amazon-system-design-interview-questions/
https://github.com/donnemartin/system-design-primer/tree/master/solutions/system_design/pastebin

https://www.youtube.com/channel/UC9vLsnF6QPYuH51njmIooCQ
https://www.educative.io/
https://github.com/tssovi/grokking-the-object-oriented-design-interview/blob/master/object-oriented-design-case-studies/design-cricinfo.md
https://www.amazon.com/System-Design-Interview-insiders-Second/dp/B08CMF2CQF/ref=tmm_pap_swatch_0?_encoding=UTF8&sr=
https://www.amazon.com/dp/1736049119/ref=tsm_1_fb_lk
https://www.amazon.com/Cracking-Coding-Interview-Programming-Questions/dp/0984782850/ref=sr_1_3?dchild=1&keywords=cracking+coding+interview&qid=1623709878&sr=8-3
https://www.pramp.com/#/

https://www.digitalocean.com/community/conceptual-articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design
https://www.interviewcake.com/
https://interviewing.io/
