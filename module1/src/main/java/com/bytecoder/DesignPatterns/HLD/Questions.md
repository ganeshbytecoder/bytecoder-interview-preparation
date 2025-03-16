
# Functional 
- Core business functionalities covering all the cases
- user should be able to book for future, cancel
- Payment system for (one-time, recursive and subscription )
- user click events for analytics 
- should be able to see history
- home feed to show trending and suggestions
- notifications
- aids
- referrals, coupons
- rating system for product and services 
- customer support system

# non-functional requirements
- Microservices architecture for highly scalable, secured , fault-tolerant to support 1M users 
- high throughput and low latency and High Performance
- secured system to handle 1M users
- Devops technologies and strategies for Deployments, monitoring & Security etc
- Analytics Tools and big data processing for business insight 
- streaming data processing to prediction scams/faults/ bot



# Services
- User details and Authentication service
- Core business services 
- Home Feed service:
- Booking service:
- SearchService
- AidsService
- Payments service
- Notification Service(FanOut service)
- rating service
- Referrals and coupon service
- Customer service -> to create tickets and chat with back-office


# Architectural Patterns and Technologies

**Technologies**
- Redis for caches- home feed, available slots, fast fetch booking details, for already searched queries or shows
- Postgres to support ACID and prevents concurrent bookings -> user details, bookings, Coupens, refferals, show_slots, seats_slots , partition by city
- MongoDB for heavy reads -> to show available theaters, movies and other details, partition by city
- Cassandra -> to capture click events, searches, booking history, user activities and other analytics details
- Kafka - to have event drive architecture , to support scale, fault-tolerant

Architectural Patterns:
- centralised authentication
- horizontal scaling using load balancers and auto-scaling
- SAGA or Outbox pattern
- event sourcing 
- Circuit breaker
- CQRS
- API-Gateway - auth, static content like some videos , images etcs
- Distributed and streaming processing using kafka-spark and flink -> fraud predictions preproceesing, analytics, etc

# Discussion on Database Scalability and performance using 
- indexing, Partition, sharding, replication, consistent hashing, caching, CDN , multiple locations deployment for scale

# Trade-offs, Bottlenecks and Edge Cases or missing cases:


### how billions of like and views are updated on db. how this counter works ? for youtube,
### how billions messages are hanlded in whatsapp
### how s3 handle load and support ? and durability, fault-tolerance
### how banks make sure balance is consistent even after billions of transactions per day


### **Q: Why Kafka and not RabbitMQ?**
✔️ **Answer:**
- **Kafka** is built for **high-throughput, fault-tolerant event streaming** and is best suited for real-time data processing, distributed logging, and analytics.
- **RabbitMQ** is better suited for **low-latency, per-message acknowledgments**, and task queues where immediate processing is required.
- Kafka ensures durability via **log-based storage**, whereas RabbitMQ's **message retention is limited** unless explicitly configured.
- **Scaling Kafka** is more efficient due to its **partitioning** mechanism, whereas RabbitMQ **scales horizontally with more effort**.

✅ **Use Kafka** for log aggregation, event sourcing, stream processing.  
✅ **Use RabbitMQ** for request-response messaging, microservices RPC, transactional event processing.

---

### **Q: Why Postgres for bookings instead of DynamoDB?**
✔️ **Answer:**
- **Postgres** provides **ACID compliance** which prevents **double booking** issues.
- **DynamoDB** has high scalability but **lacks strong transactions guarantees** unless you use DynamoDB Transactions, which add complexity.
- **Joins and complex queries** are better handled in Postgres.
- DynamoDB is **eventually consistent**, making it risky for critical booking systems.

✅ **Use Postgres** if the system requires **strict consistency and complex transactions**.  
✅ **Use DynamoDB** if the system is **read-heavy, requires horizontal scaling, and has simpler transactional needs**.

---

### **Q: Why MongoDB Vs ElasticSearch vs DynamoDB for search query?**
✔️ **Answer:**
| Feature       | **MongoDB** | **ElasticSearch** | **DynamoDB** |
|--------------|------------|------------------|-------------|
| **Query Type** | Document search | Full-text search | Key-value lookup |
| **Indexing** | JSON-based | Advanced inverted index | Hash/Range-based |
| **Use Case** | Product catalog, NoSQL queries | Search engine, Log analytics | Key-based lookups, caching |
| **Scalability** | Sharding & Replication | Sharding & Replication | Auto-scaling |
| **Consistency** | Eventual | Eventual | Strong (optional) |

✅ **Use MongoDB** for document-based storage with filtering.  
✅ **Use Elasticsearch** for **advanced search** (full-text, autocomplete, faceted search).  
✅ **Use DynamoDB** for key-value queries at **high throughput**.

---

### **Q: Why CQRS for search?**
✔️ **Answer:**
- **Command Query Responsibility Segregation (CQRS)** separates **write-heavy workloads from read-heavy workloads**.
- Reduces **read latency** as the system can have **optimized read models** without impacting transactional writes.
- Enables **polyglot persistence**—use **Postgres for commands (writes)** and **Elasticsearch for queries (reads)**.

✅ **Best for:** E-commerce, event-driven systems, analytics dashboards.

---

### **Q: How CQRS helps in scaling and how does it work?**
✔️ **Answer:**
- **Scalability**: Instead of having a single database handling both reads & writes, **CQRS allows scaling reads and writes independently**.
- **How it works?**
    1. **Write Operations** → Go to a transactional database (e.g., Postgres).
    2. **Read Operations** → Go to a search-optimized database (e.g., Elasticsearch).
    3. **Syncing happens** via **event sourcing or asynchronous updates**.

✅ **Prevents read-write contention, making systems more scalable.**

---

### **Q: How SSE (Server-Sent Events) vs REST helps in a robust system and how it works?**
✔️ **Answer:**
- **REST** is **request-response** based; the client must poll for updates.
- **SSE** is **event-driven**, pushing updates **in real-time**.

✅ **Use SSE** for **real-time notifications, stock prices, live dashboards**.  
✅ **Use REST** when updates are not needed frequently.

---

### **Q: How gRPC helps in a robust system and how does it work?**
✔️ **Answer:**
- **gRPC** uses **Protocol Buffers** instead of JSON, making it **lightweight and fast**.
- Supports **bidirectional streaming**, unlike REST.
- Uses **HTTP/2**, reducing network overhead.

✅ **Use gRPC** for **microservices communication with low latency**.

---

### **Q: How Circuit Breaker & Retry help in fault tolerance and how do they work?**
✔️ **Answer:**
- **Circuit Breaker** prevents cascading failures by **blocking requests** to failing services after a threshold.
- **Retry** mechanisms help recover **transient failures** by **re-attempting** failed requests.

✅ **Use Circuit Breaker** for **third-party integrations** to prevent excessive failures.  
✅ **Use Retry** with **exponential backoff** for **network requests**.

---

### **Q: Why SAGA/outbox pattern for async data consistency?**
✔️ **Answer:**
- **Saga** breaks transactions into **multiple compensating transactions** across services.
- Works well in **microservices** where **ACID transactions aren't feasible**.

✅ **Use Saga for** order processing, payments, travel bookings.

---

### **Q: Why Cassandra for events and history?**
✔️ **Answer:**
- **Cassandra** is a **highly available**, distributed NoSQL DB.
- Optimized for **write-heavy workloads**.
- Ideal for **event sourcing** where **append-only logs** are required.

✅ **Use Cassandra for logging, audit trails, and event history.**

---

### **Q: Memcached vs Redis?**
✔️ **Answer:**
| Feature        | **Memcached** | **Redis** |
|---------------|--------------|----------|
| **Data Structure** | Key-Value only | Lists, Sets, Hashes |
| **Persistence** | No persistence | Optional persistence |
| **Use Case** | Simple caching | Advanced caching & pub/sub |

✅ **Use Memcached** for **simple in-memory caching**.  
✅ **Use Redis** for **caching + real-time pub-sub + data structures**.

---

### **Q: Spring Context, Bean Context, Security Context, Multitenancy Handling?**
✔️ **Answer:**
- **Spring Context** → Manages **bean lifecycle**.
- **Bean Context** → Controls **dependency injection**.
- **Security Context** → Stores **user authentication details**.
- **Multitenancy Handling** → Uses **database sharding** or **schema-based separation**.

✅ **Use Multitenancy** in **SaaS applications**.

---

### **Q: How real-world failures and concurrency handling will be done?**
✔️ **Answer:**
- **Failures**: Use **failover replicas, retries, and idempotency**.
- **Concurrency**: Use **Optimistic Locking (eTag), Mutexes, Distributed Locks (Redis, Zookeeper)**.

✅ **Use Distributed Locks in event-driven systems to prevent race conditions.**

---

# APIs and Schema- 
this will be discussed based on requirements

# infra requirements to handle 1M users
- Request size , throughput and then number of server required to handle 1M users every seconds and infra requirements



To design a system that can handle **1 million users per second**, we need to calculate key performance metrics:

- **Request size** (how much data is transferred per request)
- **Throughput** (how many requests per second a server can handle)
- **Number of servers required** to handle the expected load

---

## **Step 1: Estimating Request Size**
The **request size** depends on the type of request and response. Let's break it down:

- **Request Headers** (HTTP headers) → ~500 bytes (depends on cookies, auth tokens)
- **Request Payload** (JSON, API data) → ~1 KB (varies based on API request)
- **Response Headers** → ~500 bytes
- **Response Payload** (JSON, HTML, images) → Typically **10 KB** (can be much larger for images, videos)

### **Total Request Size Estimation**
If the system handles mainly API requests:
- **Average request-response size** ≈ **12 KB (500B + 1KB + 500B + 10KB)**

For **static files, images, or video streaming**, the response size can be much larger.

---

## **Step 2: Calculating Throughput**
### **Throughput Formula**
\[
\text{Throughput} = \frac{\text{Total Data Processed Per Second}}{\text{Number of Requests Per Second}}
\]

For **1 million requests per second**, assuming **each request is 12 KB**:

\[
\text{Total Data Processed Per Second} = 1M \times 12 KB = 12 GB/s
\]

This means our system needs to handle **12 gigabytes of data per second**.

---

## **Step 3: Determining Server Capacity**
The number of servers required depends on:
- **Throughput per server** (how many requests a single server can handle per second)
- **CPU, RAM, and Network Bandwidth**

### **Throughput per Server**
Let’s assume:
- **Each server has a 10 Gbps network card**
- **Each request is 12 KB**
- **Each server can handle 50,000 requests per second (based on benchmarked performance of Nginx or an application server)**

If a single **high-performance server** can process **50K requests/sec**, then:

\[
\text{Total Servers Required} = \frac{1,000,000}{50,000} = 20 \text{ servers}
\]

---

## **Step 4: Scaling Considerations**
1. **Network Bandwidth**
    - A **10 Gbps network** can transfer **10,000 MB/s**.
    - With **12 KB per request**, a **single server can handle ~833K requests/sec** before saturating the network.
    - If network is a bottleneck, upgrade to **40 Gbps** or use **load balancers**.

2. **CPU & RAM**
    - High-performance servers (e.g., **32-core CPUs with 128GB RAM**) can handle **50K+ RPS**.
    - More CPU cores help parallelize requests.

3. **Load Balancing**
    - Use **Nginx, HAProxy, or AWS ALB** to distribute requests across servers.

4. **Caching**
    - Use **Redis, Memcached** to cache frequent API responses.
    - Store **static assets in a CDN** to reduce backend load.

---

## **Final Calculation Summary**
| Metric | Calculation | Value |
|---------|------------|-------|
| Request Size | Headers + Payload + Response | ~12 KB |
| Total Throughput | Requests per second × Request Size | **12 GB/s** |
| Server Throughput | 50K RPS per server | **50,000 RPS** |
| Number of Servers | \( \frac{1,000,000}{50,000} \) | **20 servers** |

---


# Deployments, monitoring & Security more over Devops tools for microservices  
- Vault for secrets and configs management
- SSL/TLS certificates for security 
- stateless services , env agnostic so we can scale and migrate to different envs after testing without re-build
- Docker and K8s
- Different VPC and private subnets for deployments
- security groups to not allow outside requests in the system
- Service discovery - for load balancer and service discovery and control over inter service calls
- EFK/ELK for logs monitoring
- services monitoring - using promethious + grafana + heart beats
- actuator integrations in service to check health and other matrics
- Role based access for users
- IAM role for services and thrid party access
- CICD using jenkins -> canary deployments or blue-green
- API Gateway for static and routing 
- reverse proxy and firewall for rate limiting

# Scalability, fail-over mechanisms and fault-tolerant :
- Circuit breaker, autoscaling, load balancer, K8s, docker, API gateway

# real-world failures and concurrency handling:  
- multiple zone deployments and locking system at DB and cache level

# Distributed processing for business insights: - 
- ETL/ELT service for analtics purpose (pysaprk + Flink)
- streaming data processing to prediction scams/faults bookings/ bot booking

# Future Enhancements:
- what are the features can be implemented


# **Common Problems in Microservices and Distributed Systems**

Microservices and distributed systems bring scalability and flexibility but introduce several **challenges**. Below is a comprehensive list of key problems and their details:

---

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



# **Q: Transactional Outbox Pattern vs. Choreography-Based Saga for Scalable Systems**

Both the **Transactional Outbox Pattern** and **Choreography-Based Saga Pattern** address **data consistency** across microservices in
**event-driven architectures**,

---

## **1. Transactional Outbox Pattern**
### **Overview:**
The **Transactional Outbox Pattern** ensures **atomicity** between database changes and event publishing by storing events in an **outbox table** within the same transaction as the database operation. A background process (poller) reads and publishes events to the message broker.

### **How It Works:**
1. Service **A** writes data into its database and records an event in the **outbox table** within the same transaction.
2. A **background process** reads the outbox table and publishes the event to a message broker (Kafka, RabbitMQ, etc.).
3. Other services consume the event and update their state accordingly.

### **Strengths:**
✅ **Strong Consistency:** Ensures that database operations and event publishing happen atomically. No risk of data loss or out-of-sync issues.  
✅ **Reliable Event Publishing:** Uses the database as a buffer, avoiding dual-write problems.  
✅ **Scalability:** Works well for high-throughput systems as the outbox table can be optimized with partitioning and indexing.  
✅ **Fault Tolerance:** If the event publishing fails, retries can be implemented without losing the original event.

### **Challenges:**
⚠️ **Increased Latency:** Events are not published immediately; they depend on a polling mechanism.  
⚠️ **Additional Infrastructure:** Requires an outbox table and a poller to process and publish events.  
⚠️ **Complexity in Ordering:** May require **event deduplication** and proper ordering mechanisms.

### **Best Use Cases:**
- **Fintech & Banking Systems** (ensures strict consistency in transactions).
- **E-commerce Order Processing** (ensures payment and order status updates remain in sync).
- **Critical Business Workflows** (where event loss is unacceptable).

---

### **2. Choreography-Based Saga**
### **Overview:**
The **Choreography-Based Saga Pattern** manages distributed transactions across microservices using **event-driven interactions**, where each service listens to relevant events and triggers subsequent actions.

### **How It Works:**
1. A microservice (e.g., `Order Service`) initiates a business process and publishes an event (`OrderCreated`).
2. Other microservices (e.g., `Payment Service`, `Inventory Service`) **react** to this event and trigger their own actions.
3. Each service ensures local consistency and publishes subsequent events.
4. If a failure occurs, compensating actions (rollbacks) are triggered.

### **Strengths:**
✅ **High Scalability:** Services remain **loosely coupled** and can independently handle events, making the system **highly scalable**.  
✅ **Real-Time Processing:** Events are processed asynchronously and immediately, reducing **latency** compared to polling mechanisms.  
✅ **No Centralized Coordinator:** Each service **autonomously** decides its actions, making the system resilient.  
✅ **Resilient to Failures:** If a service fails, only that part of the transaction is affected.

### **Challenges:**
⚠️ **Complex Failure Handling:** Requires **compensating transactions** and careful **state management** to handle failures.  
⚠️ **Difficult to Debug:** Since events flow **asynchronously**, debugging and tracing transactions require **distributed tracing** (e.g., OpenTelemetry).  
⚠️ **Event Explosion:** A high number of events can create a **"domino effect"**, leading to **unexpected behavior** if not managed properly.

### **Best Use Cases:**
- **High-Traffic Applications** (e.g., social media, real-time notifications).
- **E-commerce Systems** (order processing across multiple services).
- **Decentralized Workflows** (where each service acts independently).

---

### **Comparison Table:**
| Feature               | **Transactional Outbox** | **Choreography-Based Saga** |
|----------------------|---------------------|-----------------------|
| **Consistency** | Stronger (atomicity via DB transactions) | Weaker (eventual consistency) |
| **Scalability** | Moderate (depends on database performance) | High (event-driven, loosely coupled) |
| **Latency** | Higher (polling introduces delay) | Lower (real-time event processing) |
| **Complexity** | Moderate (requires outbox table & poller) | High (requires event orchestration & compensations) |
| **Failure Handling** | Reliable (retries and polling) | Complex (requires compensating transactions) |
| **Best for** | **Fintech, banking, order processing** | **Social media, e-commerce, decentralized workflows** |

---

### **Final Recommendation for Scalable Systems**
- **For high consistency & transactional reliability** → Use **Transactional Outbox Pattern** (ideal for fintech, banking, and critical workflows).
- **For high scalability & real-time responsiveness** → Use **Choreography-Based Saga** (ideal for distributed event-driven systems).
- **Hybrid Approach** → Some systems use a combination: Outbox Pattern for core services + Choreography for non-critical, asynchronous workflows.

### **Q: Outbox Pattern implementation**
#### **3️⃣ Hybrid Approach: Partitioned Outbox Table**
🔹 **How It Works:**
- Use **a single outbox table** but implement **table partitioning** based on `event_type` or `aggregate_type`.
- Example **PostgreSQL partitioning**:
```sql
CREATE TABLE outbox (
    id UUID PRIMARY KEY,
    event_type VARCHAR(255),
    aggregate_type VARCHAR(255),
    aggregate_id UUID,
    payload JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE
) PARTITION BY LIST (event_type);

CREATE TABLE outbox_orders PARTITION OF outbox FOR VALUES IN ('OrderCreated', 'OrderUpdated');
CREATE TABLE outbox_payments PARTITION OF outbox FOR VALUES IN ('PaymentProcessed', 'PaymentFailed');
```
🔹 **Pros:**
✅ **Balances simplicity and performance**.  
✅ **Efficient querying via partitions**.  
✅ **Less schema complexity** compared to separate tables.

🔹 **Cons:**
⚠️ Requires **database support for partitioning**.  
⚠️ Slightly more complex setup than a single table.

🔹 **Best Use Case:**
- **Large-scale applications** that need **efficient query performance** without multiple tables.

---

### **Final Recommendation**
| Approach | Best For | Scalability | Complexity |
|----------|---------|------------|------------|
| **Single Outbox Table** | Small-to-medium workloads, simple architecture | Medium | Low |
| **Separate Outbox Tables** | High-throughput applications, different event priorities | High | Medium-High |
| **Partitioned Outbox Table** | Large-scale systems, optimized performance | Very High | Medium |



# Additional Interview Preparation Tips
- https://www.linkedin.com/posts/chiraggoswami23_api-webdevelopment-restapi-activity-7300329959120678912-TM1a?utm_source=share&utm_medium=member_desktop&rcm=ACoAABzwIGYBEJI2ZATOVuAxZmBa6zuXW26zQMc
- https://www.linkedin.com/posts/chiraggoswami23_cybersecurity-2fa-mfa-activity-7299737336978755584-YyiO?utm_source=share&utm_medium=member_desktop&rcm=ACoAABzwIGYBEJI2ZATOVuAxZmBa6zuXW26zQMc
- https://www.linkedin.com/posts/chiraggoswami23_sso-cybersecurity-passwordmanagement-activity-7299242817996918785-6sMK?utm_source=share&utm_medium=member_desktop&rcm=ACoAABzwIGYBEJI2ZATOVuAxZmBa6zuXW26zQMc
- https://www.linkedin.com/posts/chiraggoswami23_ssl-tls-websecurity-activity-7297925401027194880-QO_4?utm_source=share&utm_medium=member_desktop&rcm=ACoAABzwIGYBEJI2ZATOVuAxZmBa6zuXW26zQMc

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




## Week 6 **Implementing Multi-Tenancy with Dynamic Data Source Switching**

## **1️⃣ Determining the Shard Using a Sharding Strategy**
Before executing database operations, we determine the correct shard using a **sharding strategy** (e.g., **hash-based**, **range-based**, etc.).

```java
public class ShardContextHolder {
    private static final ThreadLocal<String> currentShard = new ThreadLocal<>();

    public static void setCurrentShard(String shardId) {
        currentShard.set(shardId);
    }

    public static String getCurrentShard() {
        return currentShard.get();
    }

    public static void clear() {
        currentShard.remove();
    }
}
```
### **Why Use `ThreadLocal`?**
✅ Ensures each request gets the correct shard in a **multi-threaded environment**.  
✅ Prevents **mixing data sources** between requests.

---

## **2️⃣ Dynamic Shard Routing with `AbstractRoutingDataSource`**
Spring Boot provides `AbstractRoutingDataSource` to dynamically route queries to the correct database.

```java
public class DynamicShardRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ShardContextHolder.getCurrentShard(); // Retrieves the shard for the current request
    }
}
```
### **How It Works:**
1. **Extends `AbstractRoutingDataSource`** to dynamically select the data source at runtime.
2. **Overrides `determineCurrentLookupKey()`**, which fetches the shard identifier from `ShardContextHolder`.

---

## **3️⃣ Configuring Multiple Data Sources in Spring Boot**
We define multiple database configurations and register them in `DynamicShardRoutingDataSource`.

```java
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dynamicDataSource() {
        DynamicShardRoutingDataSource routingDataSource = new DynamicShardRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("shard1", createDataSource("jdbc:mysql://localhost:3306/db_shard1"));
        dataSourceMap.put("shard2", createDataSource("jdbc:mysql://localhost:3306/db_shard2"));

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(dataSourceMap.get("shard1")); // Default DB
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    private DataSource createDataSource(String url) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        return dataSource;
    }
}
```
### **Key Highlights**
✅ **Multiple shards configured** with different database connections.  
✅ **Spring Boot dynamically selects the correct shard** using `DynamicShardRoutingDataSource`.  
✅ **Uses HikariCP** for efficient connection pooling.

---

## **4️⃣ Handling Shard Selection in API Requests**
To ensure each request is routed correctly, we use an **interceptor to extract the shard key**.

```java
@Component
public class ShardInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userIdStr = request.getHeader("X-User-ID");
        if (userIdStr != null) {
            Long userId = Long.parseLong(userIdStr);
            String shard = ShardResolver.getShardForUser(userId);
            ShardContextHolder.setCurrentShard(shard);  // Set the shard before the request is processed
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ShardContextHolder.clear();  // Clear the context after request completes
    }
}
```
### **How It Works**
1. Extracts `X-User-ID` from request headers.
2. Determines the correct **shard** based on the user ID.
3. Sets the **current shard** in `ThreadLocal` before processing the request.
4. Clears the shard context after request completion.

---

## **5️⃣ Registering the Interceptor in Spring**
To activate shard selection for all requests, register the interceptor in `WebMvcConfigurer`.

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private ShardInterceptor shardInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shardInterceptor);
    }
}
```

---

## **6️⃣ Example: Request Flow with Sharding**
### **📌 Scenario: Fetching User Profile from the Correct Shard**
```plaintext
Client → API Gateway → Shard Resolver → Correct DB → Query Execution → Response
```
✅ **Step 1: User Makes API Request**
```plaintext
GET /api/v1/users/profile
Headers: { "X-User-ID": "202" }
```
✅ **Step 2: Spring Interceptor Determines the Shard**
- Extracts `X-User-ID = 202`
- Computes **shard (`202 % 3 = 1` → `shard2`)**
- Calls `ShardContextHolder.setCurrentShard("shard2")`

✅ **Step 3: Query is Routed to the Correct Shard**
```sql
SELECT * FROM users WHERE user_id = 202;
```
✅ **Step 4: Response is Sent Back**
```json
{
  "user_id": "202",
  "name": "John Doe",
  "email": "johndoe@email.com"
}
```

---

## **🔹 Why Use This Approach?**
✅ **Dynamic Data Source Routing** → Queries are automatically routed to the correct database.  
✅ **Thread-Safe** → Uses `ThreadLocal` to isolate shard selection per request.  
✅ **Scalability** → Easily add new shards without modifying core logic.  
✅ **Efficient Resource Utilization** → Leverages `AbstractRoutingDataSource` for optimal performance.

---



Here are detailed answers to your system design interview questions along with additional important trade-offs and questions that might be asked:

---

## ** partitions and Sharding Cases**

### partitions works
```sql
No, table partitioning does not create separate tables in the traditional sense, but rather logical divisions of a single table under the hood. The database internally manages partitions as smaller storage units within a single parent table.


CREATE TABLE bookings_2025_01 PARTITION OF bookings
FOR VALUES FROM ('2025-01-01') TO ('2025-02-01');


2. Hash Partitioning (For Load Balancing)
Distributes data evenly across partitions (good for high-traffic tables).
Example: Partitioning users by user_id hash.


CREATE TABLE userDetails (
  id UUID PRIMARY KEY,
  name TEXT,
  email TEXT UNIQUE NOT NULL
) PARTITION BY HASH (id);


```

### **🚀 Does Sharding Create Different Tables Based on Columns?**
No, **sharding does not create different tables based on columns**. Instead, **sharding horizontally distributes rows across multiple databases or physical machines**, while the **table schema remains the same** in each shard.

---

## **📌 Key Differences Between Sharding & Partitioning**
| Feature  | **Sharding** | **Partitioning** |
|----------|------------|----------------|
| **Definition** | Splits data across multiple databases (horizontal scaling). | Splits data within the same database into logical partitions. |
| **How It Works** | Each shard contains a subset of the rows. | Partitions divide a table within the same database. |
| **Managed by** | Application logic or a database sharding service. | The database engine itself (e.g., PostgreSQL, MySQL). |
| **Performance Impact** | 🚀 Scales infinitely by adding more shards. | 🚀 Improves queries but still within a single DB instance. |
| **Complexity** | ❌ Requires distributed query handling & cross-shard joins. | ✅ Easier to implement & query within the same database. |
| **Use Case** | Multi-region applications, very large datasets. | High-volume tables that need optimization. |

---

## **📌 How Sharding Works**
Sharding **divides tables by rows, not columns**. The **schema stays the same**, but **each shard stores only a subset of rows**.

✅ **Example: Sharding a `users` Table Across 3 Databases**
```plaintext
Shard 1 (users_1): Users with user_id 1 - 10M
Shard 2 (users_2): Users with user_id 10M - 20M
Shard 3 (users_3): Users with user_id 20M - 30M
```
- **Query on User 15M → Sent to Shard 2**
- **Query on User 25M → Sent to Shard 3**

🔹 **Schema stays the same across all shards:**
```sql
CREATE TABLE users (
  user_id UUID PRIMARY KEY,
  name TEXT,
  email TEXT UNIQUE,
  created_at TIMESTAMP
);
```
Each shard contains **a different subset of rows**, but the **same table structure**.

---

## **📌 Types of Sharding**
### **1️⃣ Key-Based (Hash) Sharding**
- **Divides rows based on a hash function.**
- Used when **data needs to be evenly distributed.**
- Example:
  ```sql
  SHARD_KEY = user_id % TOTAL_SHARDS;
  ```
    - `user_id 101 → goes to Shard 1`
    - `user_id 204 → goes to Shard 2`

✅ **Best For:** General use cases where **even data distribution** is needed.  
❌ **Downside:** Hard to move data if shard size grows unevenly.

---

### **2️⃣ Range-Based Sharding**
- Divides data **by range of values** (e.g., `user_id`, `created_at`).
- Example:
  ```plaintext
  Shard 1 → user_id 1 - 1M
  Shard 2 → user_id 1M - 2M
  ```
✅ **Best For:** **Time-series data (logs, transactions, analytics).**  
❌ **Downside:** Uneven shard sizes if some ranges have more data.

---

### **3️⃣ Geo-Based (Directory) Sharding**
- Divides data by **geographical location** (e.g., country, region).
- Example:
  ```plaintext
  Shard 1 → Users in USA
  Shard 2 → Users in Europe
  ```
✅ **Best For:** Multi-region applications, social networks.  
❌ **Downside:** Some shards may become too large if one region has more users.

---

## **🔥 Key Takeaways**
✅ **Sharding splits rows into different databases, not columns.**  
✅ **Each shard keeps the same table schema.**  
✅ **Queries are directed to the correct shard based on a sharding key.**  
✅ **Sharding improves scalability but adds complexity.**  
✅ **Partitioning works within a single DB, while sharding spans multiple DBs.**

---


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






